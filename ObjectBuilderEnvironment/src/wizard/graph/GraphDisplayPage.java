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
import graphs.graph.GraphOrientation;

import java.util.Arrays;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import wizard.common.WizardConfiguration;

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
      
      GridPane grid = new GridPane();
      ColumnConstraints constraints = new ColumnConstraints();
      constraints.setPercentWidth( 50 );
      grid.getColumnConstraints().add( constraints );
      getChildren().add( grid );
      
      grid.add( new Label( "Vertical Charts" ), 0, 0 );
      grid.add( new Label( "Horizontal Charts" ), 1, 0 );
      
      Button barChart = new Button( "Bar Chart" );
      barChart.setOnAction( event -> graph.barChart( GraphOrientation.Vertical ) );
      grid.add( barChart, 0, 1 );
      
      Button stackedBarChart = new Button( "Stacked Bar Chart" );
      stackedBarChart.setOnAction( event -> graph.stackedBarChart( GraphOrientation.Vertical ) );
      grid.add( stackedBarChart, 0, 2 );
      
      Button lineChart = new Button( "Line Chart" );
      lineChart.setOnAction( event -> graph.lineChart( GraphOrientation.Vertical ) );
      grid.add( lineChart, 0, 3 );
      
      Button scatterChart = new Button( "Scatter Chart" );
      scatterChart.setOnAction( event -> graph.scatterChart( GraphOrientation.Vertical ) );
      grid.add( scatterChart, 0, 4 );
      
      Button areaChart = new Button( "Area Chart" );
      areaChart.setOnAction( event -> graph.areaChart( GraphOrientation.Vertical ) );
      grid.add( areaChart, 0, 5 );
      
      Button stackedAreaChart = new Button( "Stacked Area Chart" );
      stackedAreaChart.setOnAction( event -> graph.stackedAreaChart( GraphOrientation.Horizontal ) );
      grid.add( stackedAreaChart, 0, 6 );
      
      Button barChartHorizontal = new Button( "Bar Chart" );
      barChartHorizontal.setOnAction( event -> graph.barChart( GraphOrientation.Horizontal ) );
      grid.add( barChartHorizontal, 1, 1 );
      
      Button stackedBarChartHorizontal = new Button( "Stacked Bar Chart" );
      stackedBarChartHorizontal.setOnAction( event -> graph.stackedBarChart( GraphOrientation.Horizontal ) );
      grid.add( stackedBarChartHorizontal, 1, 2 );
      
      Button lineChartHorizontal = new Button( "Line Chart" );
      lineChartHorizontal.setOnAction( event -> graph.lineChart( GraphOrientation.Horizontal ) );
      grid.add( lineChartHorizontal, 1, 3 );
      
      Button scatterChartHorizontal = new Button( "Scatter Chart" );
      scatterChartHorizontal.setOnAction( event -> graph.scatterChart( GraphOrientation.Horizontal ) );
      grid.add( scatterChartHorizontal, 1, 4 );
      
      Button areaChartHorizontal = new Button( "Area Chart" );
      areaChartHorizontal.setOnAction( event -> graph.areaChart( GraphOrientation.Horizontal ) );
      grid.add( areaChartHorizontal, 1, 5 );
      
//      ISSUE#44
//      Button stackedAreaChartHorizontal = new Button( "Stacked Area Chart" );
//      stackedAreaChartHorizontal.setOnAction( event -> graph.stackedAreaChart( GraphOrientation.Horizontal ) );
//      grid.add( stackedAreaChartHorizontal, 1, 6 );
      
      setPrefWidth( WizardConfiguration.wizardWidth() );
      setPrefHeight( WizardConfiguration.wizardHeight() );
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
