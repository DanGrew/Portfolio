/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.search.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import architecture.request.RequestSystem;
import graphics.utility.EnumStringConverter;
import graphics.utility.SingletonStringConverter;
import graphs.series.GroupEvaluation;
import javafx.Workarounds;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import propertytype.PropertyType;
import search.SearchPolicy;
import search.SearchSpace.SearchCriteria;
import wizard.common.WizardConfiguration;

/**
 * The {@link SearchCriteriaTableView} is responsible for displaying the {@link SearchCriteria}s
 * and allowing the user to enter/edit them in a {@link TableView} form.
 */
public class SearchCriteriaTableView extends VBox {
   
   private ObservableList< PropertyType > availablePropertyTypes;
   private TableView< SearchCriteriaRow > table;
   
   /** Class for how the information of a {@link SearchCriteria}.**/
   private static class SearchCriteriaRow {
      private ReadOnlyObjectWrapper< SearchPolicy > policy;
      private ReadOnlyObjectWrapper< PropertyType > type;
      private ReadOnlyStringWrapper value;
      
      /**
       * Constructs a new {@link SearchCriteriaRow}.
       */
      private SearchCriteriaRow() {
         policy = new ReadOnlyObjectWrapper<>();
         type = new ReadOnlyObjectWrapper<>();
         value = new ReadOnlyStringWrapper();
      }//End Constructor
      
      /**
       * Constructs a new {@link SearchCriteriaRow}.
       * @param type the {@link PropertyType}.
       * @param evaluation the {@link GroupEvaluation}.
       */
      private SearchCriteriaRow( SearchPolicy policy, PropertyType type, String value ) {
         this.policy = new ReadOnlyObjectWrapper<>( policy );
         this.type = new ReadOnlyObjectWrapper<>( type );
         this.value = new ReadOnlyStringWrapper( value );
      }//End Constructor
   }//End Class
   
   /**
    * Constructs a new {@link SearchCriteriaTableView}.
    */
   public SearchCriteriaTableView() {
      super();
      availablePropertyTypes = FXCollections.observableArrayList();
      refresh();
      createTable();
      createPolicyColumn();
      createPropertyColumn();
      createValueColumn();
      createRows();
      getChildren().add( table );
   }//End Constructor
   
   /**
    * Method to refresh the {@link PropertyType}s they may have been changed outside
    * the {@link TableView}.
    */
   private void refresh(){
      availablePropertyTypes.clear();
      availablePropertyTypes.addAll( RequestSystem.retrieveAll( PropertyType.class ) );
   }//End Method
   
   /**
    * Method to create the {@link TableView}.
    */
   private void createTable(){
      table = new TableView<>();
      table.setEditable( true );
      table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
      table.setPrefHeight( WizardConfiguration.tableHeight() );
   }//End Method
   
   /**
    * Method to create the {@link SearchPolicy} {@link TableColumn}.
    */
   private void createPolicyColumn(){
      TableColumn< SearchCriteriaRow, SearchPolicy > column = new TableColumn<>( "Policy" );
      column.setCellFactory( ComboBoxTableCell.forTableColumn( 
               new EnumStringConverter< SearchPolicy >( SearchPolicy.class ), 
               SearchPolicy.values() 
      ) );
      column.setCellValueFactory( value -> {
         return value.getValue().policy; 
      } );
      column.setOnEditCommit( event -> {
         event.getRowValue().policy.set( event.getNewValue() );
         
         List< PropertyType > appropriateTypes = RequestSystem.retrieveAll( PropertyType.class, type -> {
            return event.getNewValue().isCompatible( type );
         } );
         
         PropertyType selected = event.getRowValue().type.get();
         availablePropertyTypes.clear();
         availablePropertyTypes.addAll( appropriateTypes );
         if ( selected != null && !availablePropertyTypes.contains( selected ) ) {
            event.getRowValue().type.set( null );
         }
      } );
      column.setPrefWidth( WizardConfiguration.dualListWidth() );
      Workarounds.disableColumnDragging( column );
      table.getColumns().add( column );
   }//End Method
   
   /**
    * Method to create the {@link TableColumn} for the {@link PropertyType}.
    */
   private void createPropertyColumn() {
      TableColumn< SearchCriteriaRow, PropertyType > column = new TableColumn<>( "Property" );
      column.setCellFactory( ComboBoxTableCell.forTableColumn( 
               new SingletonStringConverter< PropertyType >( PropertyType.class ), 
               availablePropertyTypes
      ) );
      column.setCellValueFactory( value -> {
         return value.getValue().type; 
      } );
      column.setOnEditCommit( event -> {
         ReadOnlyObjectWrapper< PropertyType > propertyWrapper = event.getRowValue().type;
         propertyWrapper.set( event.getNewValue() );
         SearchPolicy evaluation = event.getRowValue().policy.get();
         if ( evaluation == null ) {
            return;
         }
         if ( propertyWrapper.get() == null ) {
            return;
         }
         if ( evaluation.isCompatible( propertyWrapper.get() ) ){
            return;
         }
         event.getRowValue().type.set( null );
      } );
      Workarounds.disableColumnDragging( column );
      table.getColumns().add( column );
   }//End Method
   
   /**
    * Method to create the {@link TableColumn} for the value.
    */
   private void createValueColumn() {
      TableColumn< SearchCriteriaRow, String > column = new TableColumn<>( "Value" );
      column.setEditable( true );
      column.setCellFactory( TextFieldTableCell.forTableColumn() );
      column.setCellValueFactory( value -> {
         return value.getValue().value; 
      } );
      column.setOnEditCommit( event -> {
         event.getRowValue().value.set( event.getNewValue() );
      } );
      Workarounds.disableColumnDragging( column );
      table.getColumns().add( column );
   }//End Method
   
   /**
    * Method to create some blank rows in the table for populating by the user.
    */
   private void createRows() {
      for ( int i = 0; i < 10; i ++ ) {
         table.getItems().add( new SearchCriteriaRow() );
      }
   }//End Method
   
   /**
    * Method to populate the {@link TableView} with the given data for {@link SearchCriteriaRow}s.
    * @param evaluations the {@link Collection} of data to populate with.
    */
   public void populateTable( Collection< SearchCriteria > evaluations ) {
      table.getItems().clear();
      evaluations.forEach( entry -> {
         table.getItems().add( new SearchCriteriaRow( entry.getPolicy(), entry.getType(), entry.getValue() ) );
      } );
   }//End Method
   
   /**
    * Method to retrieve the data configured in the {@link TableView}.
    * @return a {@link Collection} of {@link SearchCriteria}s configured.
    */
   public Collection< SearchCriteria > retrieveSelection(){
      List< SearchCriteria > criteria = new ArrayList<>();
      table.getItems().forEach( item -> {
         criteria.add( new SearchCriteria( 
                  item.policy.get(),
                  item.type.get(), 
                  item.value.get() 
         ) );
      } );
      return criteria;
   }//End Method

}//End Class