/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import graphics.JavaFx;
import graphics.wizard.Wizard;
import graphics.wizard.WizardPage;
import graphs.graph.Graph;

import java.util.Arrays;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import architecture.request.RequestSystem;

/**
 * The {@link GraphIntroductionPage} is responsible for introducing the user to the
 * {@link GraphWizard} allowing them to create a new {@link Graph}, or select an existing.
 */
public class GraphIntroductionPage extends VBox implements WizardPage< Graph > {

   private static final String ERROR_HEADER = "Graph name needs some work!";
   private TextField nameField;
   
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
      
      nameField = new TextField();
      getChildren().add( nameField );
      
      setPrefWidth( GraphWizardConfiguration.wizardWidth() );
      setPrefHeight( GraphWizardConfiguration.wizardHeight() );
   }// End Constructor

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
      if ( graph != null ) {
         nameField.setText( graph.getIdentification() );
      }
      return this;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isContentAcceptable() {
      String name = nameField.getText();
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
      String name = nameField.getText();
      Graph graph = RequestSystem.retrieve( Graph.class, name );
      if ( graph == null ) {
         graph = new Graph( name );
         RequestSystem.store( graph, Graph.class );
      }
      return graph;
   }// End Method
   
}// End Class
