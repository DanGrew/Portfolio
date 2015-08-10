/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import graphics.JavaFx;
import graphics.node.DualListView;
import graphics.wizard.Wizard;
import graphics.wizard.WizardPage;
import graphs.graph.Graph;

import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import search.Search;
import architecture.request.RequestSystem;

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
             + "series. Any search can be used and all of its matching objects will be plotted in"
             + "the graph so long as they have the properties set in the previous steps. Please "
             + "choose the data below."
          )
      ) );
      
      searches = new DualListView<>( false );
      searches.setListWidth( GraphWizardConfiguration.dualListWidth() );
      searches.setPrefHeight( GraphWizardConfiguration.dualListHeight() );
      List< Search > searchObjects = RequestSystem.retrieveAll( 
               Search.class 
      );
      searches.setChoices( searchObjects );
      getChildren().add( searches );
      
      setPrefWidth( GraphWizardConfiguration.wizardWidth() );
      setPrefHeight( GraphWizardConfiguration.wizardHeight() + 100 );
   }// End Constructor
   
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
