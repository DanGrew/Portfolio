/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph.components;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import architecture.request.RequestSystem;
import graphics.utility.EnumStringConverter;
import graphics.utility.SingletonStringConverter;
import graphs.series.GroupEvaluation;
import javafx.Workarounds;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.VBox;
import propertytype.PropertyType;
import wizard.common.WizardConfiguration;

/**
 * The {@link GroupEvaluationsTableView} is responsible for displaying the {@link GroupEvaluation}s
 * and allowing the user to enter/edit them in a {@link TableView} form.
 */
public class GroupEvaluationsTableView extends VBox {
   
   private ObservableList< PropertyType > availablePropertyTypes;
   private ObservableList< GroupEvaluation > availableGroupEvaluations;
   private TableView< GroupEvaluationRow > table;
   
   /** Class for how the information of a GroupEvaluation.**/
   private static class GroupEvaluationRow {
      private ReadOnlyObjectWrapper< PropertyType > type;
      private ReadOnlyObjectWrapper< GroupEvaluation > evaluation;
      
      /**
       * Constructs a new {@link GroupEvaluationRow}.
       */
      private GroupEvaluationRow() {
         type = new ReadOnlyObjectWrapper<>();
         evaluation = new ReadOnlyObjectWrapper<>();
      }//End Constructor
      
      /**
       * Constructs a new {@link GroupEvaluationRow}.
       * @param type the {@link PropertyType}.
       * @param evaluation the {@link GroupEvaluation}.
       */
      private GroupEvaluationRow( PropertyType type, GroupEvaluation evaluation ) {
         this.type = new ReadOnlyObjectWrapper<>( type );
         this.evaluation = new ReadOnlyObjectWrapper<>( evaluation );
      }//End Constructor
   }//End Class
   
   /**
    * Constructs a new {@link GroupEvaluationsTableView}.
    */
   public GroupEvaluationsTableView() {
      super();
      availablePropertyTypes = FXCollections.observableArrayList();
      availableGroupEvaluations = FXCollections.observableArrayList();
      refresh();
      createTable();
      createPropertyColumn();
      createEvaluationColumn();
      createRows( 10 );
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
    * Method to create the {@link TableColumn} for the {@link PropertyType}.
    */
   private void createPropertyColumn() {
      TableColumn< GroupEvaluationRow, PropertyType > column = new TableColumn<>( "Property" );
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
         GroupEvaluation evaluation = event.getRowValue().evaluation.get();
         if ( evaluation == null ) {
            return;
         }
         if ( propertyWrapper.get() == null ) {
            return;
         }
         if ( evaluation.isCompatible( propertyWrapper.get().getParameterType() ) ){
            return;
         }
         event.getRowValue().evaluation.set( null );
      } );
      Workarounds.disableColumnDragging( column );
      table.getColumns().add( column );
   }//End Method
   
   /**
    * Method to create the {@link GroupEvaluation} {@link TableColumn}.
    */
   private void createEvaluationColumn(){
      TableColumn< GroupEvaluationRow, GroupEvaluation > column = new TableColumn<>( "Evaluation" );
      column.setCellFactory( ComboBoxTableCell.forTableColumn( 
               new EnumStringConverter< GroupEvaluation >( GroupEvaluation.class ), 
               availableGroupEvaluations 
      ) );
      column.setCellValueFactory( value -> {
         return value.getValue().evaluation; 
      } );
      column.setOnEditCommit( event -> {
         event.getRowValue().evaluation.set( event.getNewValue() ); 
      } );
      column.setOnEditStart( event -> {
         PropertyType type = event.getRowValue().type.get();
         if ( type == null ) {
            availableGroupEvaluations.clear();
            return;
         }
         for ( GroupEvaluation evaluation : GroupEvaluation.values() ) {
            if ( evaluation.isCompatible( type.getParameterType() ) ) {
               if ( !availableGroupEvaluations.contains( evaluation ) ) {
                  availableGroupEvaluations.add( evaluation );
               }
            } else {
               availableGroupEvaluations.remove( evaluation );
            }
         }
      } );
      column.setPrefWidth( WizardConfiguration.dualListWidth() );
      Workarounds.disableColumnDragging( column );
      table.getColumns().add( column );
   }//End Method
   
   /**
    * Method to create some blank rows in the table for populating by the user.
    * @param count the number of rows to create.
    */
   private void createRows( int count ) {
      for ( int i = 0; i < count; i ++ ) {
         table.getItems().add( new GroupEvaluationRow() );
      }
   }//End Method
   
   /**
    * Method to populate the {@link TableView} with the given data for {@link GroupEvaluationRow}s.
    * @param evaluations the {@link Collection} of data to populate with.
    */
   public void populateTable( Collection< Entry< PropertyType, GroupEvaluation > > evaluations ) {
      table.getItems().clear();
      
      int count = 0;
      for ( Entry< PropertyType, GroupEvaluation > entry : evaluations ) {
         table.getItems().add( new GroupEvaluationRow( entry.getKey(), entry.getValue() ) );
         count++;
      }
      
      createRows( 10 - count );
   }//End Method
   
   /**
    * Method to retrieve the data configured in the {@link TableView}.
    * @return a {@link Collection} of {@link PropertyType}s to {@link GroupEvaluation}s, either can be null.
    */
   public Collection< Entry< PropertyType, GroupEvaluation > > retrieveSelection(){
      List< Entry< PropertyType, GroupEvaluation > > plots = new ArrayList<>();
      table.getItems().forEach( item -> {
         plots.add( new SimpleEntry< PropertyType, GroupEvaluation >( 
                  item.type.get(), 
                  item.evaluation.get() 
         ) );
      } );
      return plots;
   }//End Method

}//End Class