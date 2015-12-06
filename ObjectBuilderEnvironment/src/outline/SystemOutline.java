/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline;

import java.util.List;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import javafx.Workarounds;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.BorderPane;
import model.singleton.Singleton;
import objecttype.Definition;
import outline.configuration.SystemOutlineDetail;
import outline.describer.OutlineDescriber;
import outline.describer.OutlineDescriberFactory;
import outline.describer.OutlineDescriberFactory.OutlineDescribables;
import outline.describer.OutlineDescriberImpl;

/**
 * The {@link SystemOutline} provides an interactive {@link TreeTableView} displaying
 * all data in the system.
 */
public class SystemOutline extends BorderPane {

   private static final String ELEMENTS = "Elements";
   private static final String ROOT = "ROOT";
   private static final String MODEL = "Model";
   private static final String PROPERTY_TYPES = "Property Types";
   private static final String DEFINITIONS = "Definitions";
   private static final String DATA = "Data";
   
   /**
    * Constructs a new {@link SystemOutline}.
    * @param detail the {@link SystemOutlineDetail} configuration object for displaying different
    * levels of detail.
    */
   public SystemOutline( SystemOutlineDetail detail ) {
      initialiseOutlineData();
      constructLayout( detail );
   }// End Constructor
   
   /**
    * Method to initialise the data required for the outline.
    */
   private void initialiseOutlineData(){}// End Method
   
   /**
    * Method to create a branch in the tree for the given data.
    * @param parent the parent {@link TreeItem} in the tree.
    * @param branchName the nae of the branch.
    * @param data the {@link Singleton}s to populate the branch with.
    * @param describable the {@link OutlineDescribables} for the items.
    * @param <SingletonT> the type of {@link Singleton} being added to the outline.
    * @return the constructed {@link TreeItem} representing the branch.
    */
   private < SingletonT extends Singleton > TreeItem< OutlineDescriber > createBranch( 
            TreeItem< OutlineDescriber > parent, 
            String branchName, 
            List< SingletonT > data,
            OutlineDescribables describable
   ) {
      OutlineDescriber branchItem = OutlineDescriberFactory.newDescriber( branchName );
      TreeItem< OutlineDescriber > branch = new TreeItem<>( branchItem );
      if ( data != null ) {
         data.stream().forEach( object -> {
            OutlineDescriber itemDescriber = OutlineDescriberFactory.newDescriber( describable, object );
            branch.getChildren().add( new TreeItem<>( itemDescriber ) );
         } );
      }
      parent.getChildren().add( branch );
      return branch;
   }// End Method
   
   /**
    * Method to create a column in the table in the style of a spreadsheet.
    * @param parent the {@link TreeTableView} parent to add the column to.
    * @param columnReference the int column number.
    */
   private void createSpreadsheetColumn( TreeTableView< OutlineDescriber > parent, int columnReference ) {
      TreeTableColumn< OutlineDescriber, String > spreadsheetColumn = new TreeTableColumn<>();
      spreadsheetColumn.setPrefWidth( 100 );
      spreadsheetColumn.setEditable( true );
      spreadsheetColumn.setCellFactory( TextFieldTreeTableCell.forTreeTableColumn() );
      spreadsheetColumn.setCellValueFactory( object -> 
         new ReadOnlyObjectWrapper<>( object.getValue().getValue().getColumnEntry( columnReference ) ) 
      );
      Workarounds.disableColumnDragging( spreadsheetColumn );
      parent.getColumns().add( spreadsheetColumn );
   }// End Method
   
   /**
    * Method to create the first column representing the {@link Singleton} structure in the system.
    * @param parent the {@link TreeTableView} to add the column to.
    * @param selection the {@link ObservableList} for the selection from the {@link TreeTableView}.
    */
   private void createElementsColumn( 
            TreeTableView< OutlineDescriber > parent, 
            ObservableList< TreeItem< OutlineDescriber > > selection 
   ) {
      TreeTableColumn< OutlineDescriber, String > elementsColumn = new TreeTableColumn<>( ELEMENTS );
      elementsColumn.setPrefWidth( 200 );
      elementsColumn.setCellFactory( list -> {
         TreeTableCell< OutlineDescriber, String > cell = new TreeTableCell< OutlineDescriber, String >() {
            @Override protected void updateItem(String item, boolean empty) {
                if (item == getItem()) return;

                super.updateItem(item, empty);

                if (item == null) {
                    super.setText(null);
                    super.setGraphic(null);
                } else {
                    super.setText(item.toString());
                    super.setGraphic(null);
                }
            }
        };
        cell.setOnDragDetected( new SystemOutlineDragger( cell, selection ) );
        return cell;
      } );
      elementsColumn.setCellValueFactory( param -> 
         new ReadOnlyObjectWrapper<>( param.getValue().getValue().getColumnEntry( 0 ) )
      );
      Workarounds.disableColumnDragging( elementsColumn );
      parent.getColumns().add( elementsColumn );
   }// End Method
   
   /**
    * Method to update the column headers based on the item at the top of the view port.
    * @param treeTableView the {@link TreeTableView} to update.
    */
   private void updateColumnHeadersForView( TreeTableView< OutlineDescriber > treeTableView ) {
      Object foundObject = treeTableView.lookup( "VirtualFlow" );
      VirtualFlow< TreeTableRow< OutlineDescriber > > virtualFlow = null;
      try {
         @SuppressWarnings("unchecked") //Cannot be entirely safe so try catch prevents crashes. 
         VirtualFlow< TreeTableRow< OutlineDescriber > > temporaryAssignment = ( VirtualFlow< TreeTableRow< OutlineDescriber > > )foundObject;
         virtualFlow = temporaryAssignment;
      } catch ( ClassCastException exception ) {
         //Safest to return and avoid updating any graphics.
         return;
      }
      
      TreeTableRow< OutlineDescriber > object = virtualFlow.getFirstVisibleCell();
      OutlineDescriber itemAtTopOfPage = object.getItem();
      
      for ( int i = 0; i < treeTableView.getColumns().size(); i++ ) {
         TreeTableColumn< OutlineDescriber, ? > column = treeTableView.getColumns().get( i );
         column.setText( itemAtTopOfPage.getColumnDescription( i ) );
      }
   }// End Method
   
   /**
    * Method to display the {@link SystemOutline}.
    */
   public void constructLayout( SystemOutlineDetail detail ) {
      TreeItem< OutlineDescriber > root = new TreeItem<>( new OutlineDescriberImpl( ROOT ) );
      root.setExpanded( true );
      
      if ( detail.isPropertyTypesShown() || detail.isDefinitionsShown() ) {
         TreeItem< OutlineDescriber > modelBranch = createBranch( root, MODEL, null, null );
         
         if ( detail.isPropertyTypesShown() ) {
            TreeItem< OutlineDescriber > propertyTypeBranch = createBranch( modelBranch, PROPERTY_TYPES, null, null );
            new TreeItemListModel( OutlineDescribables.PropertyType, propertyTypeBranch );
         }
         
         if ( detail.isDefinitionsShown() ) {
            TreeItem< OutlineDescriber > definitionBranch = createBranch( modelBranch, DEFINITIONS, null, null );
            TreeItemListModel definitionModel = new TreeItemListModel( OutlineDescribables.Definition, definitionBranch );
            definitionModel.addUpdateEvent( Definition.Events.PropertyAdded );
         }
      }
      
      if ( detail.isBuilderObjectsShown() ) {
         TreeItem< OutlineDescriber > dataBranch = createBranch( root, DATA, null, null );
         new BuilderObjectTreeItemModel( dataBranch );
      }

      TreeTableView< OutlineDescriber > treeTableView = new TreeTableView<>( root );
      treeTableView.setEditable( true );
      treeTableView.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
      
      createElementsColumn( treeTableView, treeTableView.getSelectionModel().getSelectedItems() );
      
      if ( detail.isValueColumnsShown() ) {
         for ( int i = 1; i < 20; i++ ) {
            createSpreadsheetColumn( treeTableView, i );
         }
      }

      treeTableView.setShowRoot( false );
      treeTableView.setTableMenuButtonVisible( true );
      treeTableView.setOnScrollFinished( event -> updateColumnHeadersForView( treeTableView ) );
      if ( detail.getSelectionHandler() != null ) {
         detail.getSelectionHandler().monitorSelection( treeTableView.getSelectionModel().getSelectedItems() );
      }
      setCenter( treeTableView );
   }// End Method

}// End Class
