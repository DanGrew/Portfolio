/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline;

import graphs.graph.Graph;

import java.util.List;

import javafx.Workarounds;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.BorderPane;
import model.singleton.Singleton;
import objecttype.Definition;
import outline.describer.OutlineDescriber;
import outline.describer.OutlineDescriberFactory;
import outline.describer.OutlineDescriberFactory.OutlineDescribables;
import outline.describer.OutlineDescriberImpl;
import search.SearchOnly;
import architecture.request.RequestSystem;

import com.sun.javafx.scene.control.skin.VirtualFlow;

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
   private static final String ANALYSIS = "Analysis";
   private static final String SEARCHES = "Searches";
   private static final String GRAPHS = "Graphs";
   private List< SearchOnly > searchOnlys;
   private List< Graph > graphs;

   /**
    * Constructs a new {@link SystemOutline}
    */
   public SystemOutline() {
      initialiseOutlineData();
      constructLayout();
   }// End Constructor
   
   /**
    * Method to initialise the data required for the outline.
    */
   private void initialiseOutlineData(){
      searchOnlys = RequestSystem.retrieveAll( SearchOnly.class );
      graphs = RequestSystem.retrieveAll( Graph.class );
   }// End Method
   
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
    */
   private void createElementsColumn( TreeTableView< OutlineDescriber > parent ) {
      TreeTableColumn< OutlineDescriber, String > elementsColumn = new TreeTableColumn<>( ELEMENTS );
      elementsColumn.setPrefWidth( 200 );
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
   public void constructLayout() {
      TreeItem< OutlineDescriber > root = new TreeItem<>( new OutlineDescriberImpl( ROOT ) );
      root.setExpanded( true );
      TreeItem< OutlineDescriber > modelBranch = createBranch( root, MODEL, null, null );
      TreeItem< OutlineDescriber > propertyTypeBranch = createBranch( modelBranch, PROPERTY_TYPES, null, null );
      new TreeItemListModel( OutlineDescribables.PropertyType, propertyTypeBranch );
      TreeItem< OutlineDescriber > definitionBranch = createBranch( modelBranch, DEFINITIONS, null, null );
      TreeItemListModel definitionModel = new TreeItemListModel( OutlineDescribables.Definition, definitionBranch );
      definitionModel.addUpdateEvent( Definition.Events.PropertyAdded );
      
      TreeItem< OutlineDescriber > dataBranch = createBranch( root, DATA, null, null );
      new BuilderObjectTreeItemModel( dataBranch );
      
      TreeItem< OutlineDescriber > analysisBranch = createBranch( root, ANALYSIS, null, null );
      createBranch( analysisBranch, SEARCHES, searchOnlys, null );
      createBranch( analysisBranch, GRAPHS, graphs, null );

      TreeTableView< OutlineDescriber > treeTableView = new TreeTableView<>( root );
      treeTableView.setEditable( true );
      
      createElementsColumn( treeTableView );
      for ( int i = 1; i < 20; i++ ) {
         createSpreadsheetColumn( treeTableView, i );
      }

      treeTableView.setShowRoot( false );
      treeTableView.setTableMenuButtonVisible( true );
      treeTableView.setOnScrollFinished( event -> updateColumnHeadersForView( treeTableView ) );
      
      setCenter( treeTableView );
   }// End Method

}// End Class
