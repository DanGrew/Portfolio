/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package wizard.graph;

import graphics.JavaFx;
import graphics.wizard.WizardPage;
import graphs.graph.Graph;

import java.util.Arrays;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The {@link GraphDisplayPage} is responsible for showing the user which types of
 * {@link Graph} can be created and finishing the configuration.
 */
public class GraphDisplayPage extends VBox implements WizardPage< Graph >{

   private Graph graph;
   
   /**
    * Constructs a new {@link GraphDisplayPage}.
    */
   public GraphDisplayPage() {
      super( 10 );
      getChildren().addAll( Arrays.asList( 
            JavaFx.wrappedLabel( 
               "That's it! We're all done! Now all that's left is to view your graph. "
               + "Choose from the following types."
            )
      ) );
      
      Button barChart = new Button( "Bar Chart" );
      barChart.setOnAction( event -> graph.barChart() );
      getChildren().add( barChart );
      
      Button stackedBarChart = new Button( "Stacked Bar Chart" );
      stackedBarChart.setOnAction( event -> graph.stackedBarChart() );
      getChildren().add( stackedBarChart );
      
      Button lineChart = new Button( "Line Chart" );
      lineChart.setOnAction( event -> graph.lineChart() );
      getChildren().add( lineChart );
      
      Button scatterChart = new Button( "Scatter Chart" );
      scatterChart.setOnAction( event -> graph.scatterChart() );
      getChildren().add( scatterChart );
      
      Button areaChart = new Button( "Area Chart" );
      areaChart.setOnAction( event -> graph.areaChart() );
      getChildren().add( areaChart );
      
      Button stackedAreaChart = new Button( "Stacked Area Chart" );
      stackedAreaChart.setOnAction( event -> graph.stackedAreaChart() );
      getChildren().add( stackedAreaChart );
      
      setPrefWidth( GraphWizardConfiguration.wizardWidth() );
      setPrefHeight( GraphWizardConfiguration.wizardHeight() );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getPageDescription() {
      return "Choosing the Horizontal axis";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Node getContent( Graph input ) {
      this.graph = input;
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
   @Override public Graph configure( Graph configurable ) {
      return configurable;
   }// End Method

}// End Class
