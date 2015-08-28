/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import java.util.Arrays;
import java.util.List;

import architecture.data.DataManagementSystem;
import architecture.data.SingletonStoredSource;
import architecture.request.RequestSystem;
import graphics.JavaFx;
import graphics.event.JavaFxEventSystem;
import graphics.node.DualListView;
import graphics.wizard.Wizard;
import graphics.wizard.WizardPage;
import graphs.graph.Graph;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import menu.ObeGuiFunctions;
import search.Search;
import search.SearchSpace;
import wizard.common.WizardConfiguration;
import wizard.search.SearchSpaceWizard;

/**
 * The {@link GraphDataSeriesPage} is responsible for showing the user how to configure
 * the {@link Search}s to use in the {@link Graph}.
 */
public class GraphDataSeriesPage extends VBox implements WizardPage< Graph >{

   private static final String ERROR_HEADER = "No data has been added to the graph.";
   private DualListView< Search > searches;
   
   /**
    * Constructs a new {@link GraphDataSeriesPage}.
    */
   public GraphDataSeriesPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
         JavaFx.wrappedLabel( 
            "Now let's add some data to the graph!"
         ),
         JavaFx.wrappedLabel( 
               "Below you can choose any number of searches to add to the graph, each as their own "
             + "series. Any search can be used and all of its matching objects will be plotted in "
             + "the graph so long as they have the properties set in the previous steps. "
         ),
         JavaFx.wrappedLabel( 
               "If there are no searches created, or you'd like a different set of data, you can launch "
             + "the search wizard here."
         )
      ) );
      
      Button searchWizardButton = new Button();
      getChildren().add( searchWizardButton );
      ObeGuiFunctions.getSearchSpaceWizardFunction().configure( searchWizardButton );
      searchWizardButton.setText( "Launch Search Wizard" );
      
      getChildren().addAll( Arrays.asList( 
          JavaFx.wrappedLabel( 
              "Please choose the search spaces to include in the graph."
         )
      ) );
      
      searches = new DualListView<>( false );
      searches.setListWidth( WizardConfiguration.dualListWidth() );
      searches.setPrefHeight( WizardConfiguration.tableHeight() );
      refresh();
      JavaFxEventSystem.registerForEvent( DataManagementSystem.Events.ObjectStored, ( event, source ) -> { 
         SingletonStoredSource wrapper = ( SingletonStoredSource )source;
         if ( wrapper.classes.contains( SearchSpace.class ) ) {
            refresh();
         }
      } );
      getChildren().add( searches );
      
      setPrefWidth( WizardConfiguration.wizardWidth() );
      setPrefHeight( WizardConfiguration.wizardHeight() + 150 );
   }// End Constructor
   
   /**
    * Method to refresh the table of {@link Search}s available. This captures any changes
    * made by a launched {@link SearchSpaceWizard}.
    */
   private void refresh(){
      List< Search > searchObjects = RequestSystem.retrieveAll( 
               Search.class 
      );
      searches.setChoices( searchObjects );
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Add data series";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( Graph input ) {
      refresh();
      List< Search > dataSeries = input.getDataSeries();
      if ( !dataSeries.isEmpty() ) {
         searches.setChosenItems( dataSeries );
      }
      return this;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      List< Search > chosen = searches.getChosenItems();
      if ( chosen.isEmpty() ) {
         Wizard.error( ERROR_HEADER, "There are no searces selected." );
         return false;
      }
      
      return true;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Graph configure( Graph configurable ) {
      configurable.clearDataSeries();
      List< Search > chosen = searches.getChosenItems();
      chosen.forEach( item -> configurable.addDataSeries( item ) );
      return configurable;
   }// End Method

}// End Class
