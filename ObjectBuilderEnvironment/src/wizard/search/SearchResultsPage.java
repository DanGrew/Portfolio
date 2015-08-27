/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.search;

import java.util.Arrays;

import graphics.JavaFx;
import graphics.wizard.WizardPage;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import object.BuilderObject;
import search.SearchSpace;
import search.SearchSpace.SearchCriteria;
import wizard.common.WizardConfiguration;

/**
 * {@link SearchResultsPage} is responsible for showing the user the results of their
 * {@link SearchCriteria} changes to the {@link SearchSpace}.
 */
public class SearchResultsPage extends VBox implements WizardPage< SearchSpace >{
   
   private TableView< BuilderObject > table;
   
   /**
    * Constructs a new {@link SearchResultsPage}.
    */
   public SearchResultsPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
         JavaFx.wrappedLabel( 
            "Here we have a preview of the matching objects in the system. This table holds all the current matches "
          + "and may change as items are added to the system."
         )
      ) );
      
      table = new TableView<>();
      table.setEditable( false );
      table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      table.setPrefHeight( WizardConfiguration.tableHeight() );
      TableColumn< BuilderObject, String > column = new TableColumn<>( "Matches" );
      column.setCellValueFactory( ( item ) -> {
         return new ReadOnlyStringWrapper( item.getValue().getIdentification() );
      } );
      table.getColumns().add( column );
      
      VBox constrainedWrapper = new VBox();
      constrainedWrapper.getChildren().add( table );
      getChildren().add( constrainedWrapper );
      
      setPrefWidth( WizardConfiguration.wizardWidth() );
      setPrefHeight( WizardConfiguration.wizardHeight() + 100 );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Results";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( SearchSpace input ) {
      table.getItems().clear();
      table.getItems().addAll( input.getMatches() );
      return this;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      return true;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public SearchSpace configure( SearchSpace configurable ) {
      return configurable;
   }// End Method
}//End Class
