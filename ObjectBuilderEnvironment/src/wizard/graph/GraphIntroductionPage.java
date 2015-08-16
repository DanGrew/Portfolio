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

import architecture.request.RequestSystem;
import graphics.JavaFx;
import graphics.wizard.Wizard;
import graphics.wizard.WizardPage;
import graphs.graph.Graph;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * The {@link GraphIntroductionPage} is responsible for introducing the user to the
 * {@link GraphWizard} allowing them to create a new {@link Graph}, or select an existing.
 */
public class GraphIntroductionPage extends VBox implements WizardPage< Graph > {

   private static final String ERROR_HEADER = "Graph name needs some work!";
   private ComboBox< String > graphBox;
   
   /**
    * Constructs a new {@link GraphIntroductionPage}.
    */
   public GraphIntroductionPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList(  
         JavaFx.wrappedLabel(
               "Welcome to the Graph Wizard!"
         ), 
         JavaFx.wrappedLabel(
               "Here you will be able to create a new graph and configure how the graph should work. "
             + "Each page will present a number of configurable items that will be applied to the graph. "
             + "These can be changed at any time by navigating back and forth through the wizard." 
         ),
         JavaFx.wrappedLabel(
               "Let's get started! Please enter a name for your graph:"
         )
      ) );
      
      graphBox = new ComboBox<>();
      graphBox.setPrefWidth( GraphWizardConfiguration.wizardWidth() );
      graphBox.setEditable( true );
      refreshGraphs();
      getChildren().add( graphBox );
      
      setPrefWidth( GraphWizardConfiguration.wizardWidth() );
      setPrefHeight( GraphWizardConfiguration.wizardHeight() );
   }// End Constructor
   
   /**
    * Method to refresh the items in the {@link Graph} {@link ComboBox}.
    */
   private void refreshGraphs(){
      graphBox.getItems().clear();
      List< Graph > graphs = RequestSystem.retrieveAll( Graph.class );
      graphs.forEach( graph -> graphBox.getItems().add( graph.getIdentification() ) );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Introduction";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( Graph graph ) {
      refreshGraphs();
      if ( graph != null ) {
         graphBox.getSelectionModel().select( graph.getIdentification() );
      }
      return this;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      String name = graphBox.getSelectionModel().getSelectedItem();
      if ( name == null || name.isEmpty() ) {
         Wizard.error( ERROR_HEADER, "No name has been specified." );
         return false;
      }
      
      Graph graph = RequestSystem.retrieve( Graph.class, name );
      if ( graph != null ) {
         if ( !JavaFx.happyWithThis( 
                  "Name clash", 
                  "Graph already exists.", 
                  "Continuing with this name will edit the existing graph." ) 
         ){
            return false;
         }
      } 
      return true;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Graph configure( Graph configurable ) {
      String name = graphBox.getSelectionModel().getSelectedItem();
      Graph graph = RequestSystem.retrieve( Graph.class, name );
      if ( graph == null ) {
         graph = new Graph( name );
         RequestSystem.store( graph, Graph.class );
      }
      return graph;
   }// End Method
   
}// End Class
