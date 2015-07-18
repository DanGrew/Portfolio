/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph;

import gui.ObjectBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import model.singleton.SingletonImpl;
import object.BuilderObject;
import parameter.classparameter.ClassParameterTypes;
import property.Property;
import propertytype.PropertyType;
import search.Search;
import annotation.Cali;

/**
 * The {@link Graph} represents a configuration for an {@link XYChart}
 * that can be written to file and saved.
 */
@Cali public class Graph extends SingletonImpl< SerializableGraph > {
   
   private static final Number DEFAULT_UNDEFINED_NUMBER = 0.0;
   private static final String DEFAULT_UNDEFINED_STRING = "Unknown";
   private static final Dimension2D DEFAULT_DIMENSION = new Dimension2D( 800, 800 );
   private List< Search > dataSeries;
   private List< PropertyType > verticalProperties;
   private String verticalAxisLabel;
   private PropertyType horizontalProperty;
   private String horizontalAxisLabel;
   private Number undefinedNumber = DEFAULT_UNDEFINED_NUMBER;
   private String undefinedString = DEFAULT_UNDEFINED_STRING;
   private Dimension2D dimension = DEFAULT_DIMENSION;
   
   /**
    * Constructs a new {@link Graph}.
    * @param identification unique identifier for reference.
    */
   @Cali public Graph( String identification ) {
      super( identification );
      verticalProperties = new ArrayList< PropertyType >();
      dataSeries = new ArrayList<>();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializableGraph serializable ) {
      dataSeries.forEach( search -> serializable.addSearch( search ) );
      verticalProperties.forEach( property -> serializable.addVerticalProperty( property ) );
      serializable.setVerticalAxisLabel( verticalAxisLabel );
      serializable.setHorizontalProperty( horizontalProperty );
      serializable.setHorizontalAxisLabel( horizontalAxisLabel );
      serializable.setUndefinedNumber( undefinedNumber );
      serializable.setUndefinedString( undefinedString );
      serializable.setDimension( dimension );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableGraph serialized ) {
      serialized.resolveSearches().forEach( search -> addDataSeries( search ) );
      serialized.resolveVerticalProperties().forEach( property -> addVerticalProperty( property ) );
      verticalAxisLabel = serialized.getVerticalAxisLabel();
      horizontalProperty = serialized.getHorizontalProperty();
      horizontalAxisLabel = serialized.getHorizontalAxisLabel();
      undefinedNumber = serialized.getUndefinedNumber();
      undefinedString = serialized.getUndefinedString();
      dimension = serialized.getDimension();
   }// End Method

   /**
    * Getter for the {@link List} of {@link Search}es associated.
    * @return a {@link List} of {@link Search}es providing data for the {@link Graph}.
    */
   public List< Search > getDataSeries() {
      return dataSeries;
   }// End Method
   
   /**
    * Method to specify the {@link Search} used to identify results.
    * @param search the {@link Search} used to collect information for the {@link Graph}.
    */
   @Cali public void addDataSeries( Search search ) {
      this.dataSeries.add( search );
   }// End Method

   /**
    * Getter for the horizontal axis' label.
    * @return the {@link String} label.
    */
   public String getHorizontalAxisLabel() {
      return horizontalAxisLabel;
   }// End Method
   
   /**
    * Setter for the label of the horizontal axis.
    * @param horizontalAxisLabel the label.
    */
   @Cali public void setHorizontalAxisLabel( String horizontalAxisLabel ) {
      this.horizontalAxisLabel = horizontalAxisLabel;
   }// End Method
   
   /**
    * Getter for the horizontal {@link PropertyType}.
    * @return the {@link PropertyType} used for the horizontal axis.
    */
   public PropertyType getHorizontalProperty() {
      return horizontalProperty;
   }// End Method
   
   /**
    * Method to set the {@link PropertyType} for the information displayed on the
    * horizontal axis.
    * @param property the {@link PropertyType} to use for the horizontal axis.
    */
   @Cali public void setHorizontalProperty( PropertyType property ) {
      horizontalProperty = property;
   }// End Method
   
   /**
    * Getter for the {@link PropertyType}s used for the vertical axis.
    * @return a {@link List} of {@link PropertyType}s on the vertical axis.
    */
   public List< PropertyType > getVerticalProperties() {
      return verticalProperties;
   }// End Method
   
   /**
    * Method to add a vertical series to the graph.
    * @param property the {@link PropertyType}, required to be {@link ClassParameterTypes#NUMBER_PARAMETER_TYPE}.
    * @return a {@link GraphResult} indicating the result of the add since this can fail if
    * a non number type is supplied.
    */
   @Cali public GraphResult addVerticalProperty( PropertyType property ) {
      if ( property.getParameterType().equals( ClassParameterTypes.NUMBER_PARAMETER_TYPE ) ) {
         verticalProperties.add( property );
         return GraphResult.SUCCESS;
      } else {
         return new GraphResult( GraphError.NonNumericalVerticalAxis, property );
      }
   }// End Method
   
   /**
    * Getter for the label used for the vertical axis.
    * @return the {@link String} label.
    */
   public String getVerticalAxisLabel() {
      return verticalAxisLabel;
   }// End Method
   
   /**
    * Setter for the label of the vertical axis.
    * @param verticalAxisLabel the label.
    */
   @Cali public void setVerticalAxisLabel( String verticalAxisLabel ) {
      this.verticalAxisLabel = verticalAxisLabel;
   }// End Method
   
   /**
    * Getter for the number to use when no value is defined.
    * @return the {@link Number} used by default.
    */
   public Number getDefaultValueForUndefinedNumber() {
      return undefinedNumber;
   }// End Method
   
   /**
    * Setter for the default {@link Number} value to be used for a {@link Property} that has no value, or
    * a non number value.
    * @param undefinedNumber the {@link Number} to use by default.
    */
   @Cali public void setDefaultValueForUndefinedNumber( Number undefinedNumber ) {
      this.undefinedNumber = undefinedNumber;
   }// End Method
   
   /**
    * Getter for the default value used when a value is not specified for a {@link String}.
    * @return the {@link String} default.
    */
   public String getDefaultValueForUndefinedString() {
      return undefinedString;
   }// End Method
   
   /**
    * Setter for the default {@link String} value to be used for a {@link Property} that has no value.
    * @param undefinedString the {@link String} to use by default.
    */
   @Cali public void setDefaultValueForUndefinedString( String undefinedString ) {
      this.undefinedString = undefinedString;
   }// End Method
   
   /**
    * Getter for the {@link Dimension2D} of the {@link Graph}.
    * @return the {@link Dimension2D}.
    */
   public Dimension2D getDimension() {
      return dimension;
   }// End Method
   
   /**
    * Method to set the {@link Dimension2D} of the graph.
    * @param width the width.
    * @param height the height.
    */
   @Cali public void setDimension( double width, double height ) {
      this.dimension = new Dimension2D( width, height );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link BarChart}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult barChart(){
      return show( ChartType.BarChart );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link StackedBarChart}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult stackedBarChart(){
      return show( ChartType.StackedBarChart );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link LineChart}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult lineChart(){
      return show( ChartType.LineChart );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link ScatterChart}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult scatterChart(){
      return show( ChartType.ScatterChart );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link AreaChart}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult areaChart(){
      return show( ChartType.AreaChart );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link StackedAreaChart}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult stackedAreaChart(){
      return show( ChartType.StackedAreaChart );
   }// End Method
   
   /**
    * Method to show the graph. This launches a new window with the {@link Graph} inside.
    * @return the {@link GraphResult} of the launch, {@link GraphError}s provided if not
    * configured correctly.
    */
   private GraphResult show( ChartType type ) {
      GraphResult result = verifySearch();
      if ( result != null ) return result;
      
      result = verifyHorizontalAxis();
      if ( result != null ) return result;
      
      result = verifyVerticalAxes();
      if ( result != null ) return result;
      
      ObjectBuilder.launchJavaFxForSwingEnvironment();
      Platform.runLater( new Runnable() {
         
         @Override public void run() {
            Stage stage = new Stage();
            
            final CategoryAxis horizontalAxis = new CategoryAxis();
            final NumberAxis verticalAxis = new NumberAxis();
            
            final XYChart< String, Number > graph = createChart( type, horizontalAxis, verticalAxis );
            horizontalAxis.setLabel( horizontalAxisLabel );
            verticalAxis.setLabel( verticalAxisLabel );

            for ( Search search : dataSeries ) {
               Collection< BuilderObject > matches = search.getMatches();
               
               for ( PropertyType type : verticalProperties ) {
                  Series< String, Number > series = new Series<>();
                  series.setName( type.getDisplayName() );
                  for ( BuilderObject object : matches ) {
                     Number verticalValue = defendNumber( object.get( type ) );
                     String horizontalValue = defendString( object.get( horizontalProperty ) );
                     Data< String, Number > data = new Data<>( horizontalValue, verticalValue );
                     series.getData().add( data );
                  }
                  graph.getData().add( series );
               }
            }

            Scene scene = new Scene( graph, dimension.getWidth(), dimension.getHeight() );
            stage.setScene( scene );
            stage.show();
         }
      } );
      return GraphResult.SUCCESS;
   }// End Method
   
   /**
    * Method to create and instance of the appropriate {@link XYChart} given the {@link ChartType}.
    * This method exists in place of using a factory method to avoid generic issues.
    * @param type the {@link ChartType} to create.
    * @param horizontalAxis the {@link Axis} to use for the horizontal.
    * @param verticalAxis the {@link Axis} to use for the vertical.
    * @return the constructed {@link XYChart}.
    */
   private XYChart< String, Number > createChart( ChartType type, Axis< String > horizontalAxis, Axis< Number > verticalAxis ) {
      switch ( type ) {
         case AreaChart:
            return new AreaChart<>( horizontalAxis, verticalAxis );
         case BarChart:
            return new BarChart<>( horizontalAxis, verticalAxis );
         case LineChart:
            return new LineChart<>( horizontalAxis, verticalAxis );
         case ScatterChart:
            return new ScatterChart<>( horizontalAxis, verticalAxis );
         case StackedAreaChart:
            return new StackedAreaChart<>( horizontalAxis, verticalAxis );
         case StackedBarChart:
            return new StackedBarChart<>( horizontalAxis, verticalAxis );
         default:
            return null;
      }
   }// End Method
   
   /**
    * Method to defend {@link Number} values.
    * @param object the {@link Object} being added to the graph.
    * @return a sensible {@link Number}, not null.
    */
   private Number defendNumber( Object object ) {
      if ( object == null ) {
         return undefinedNumber;
      } else if ( object instanceof Number ) {
         return ( Number )object;
      } else {
         return undefinedNumber;
      }
   }// End Method
   
   /**
    * Method to defend {@link String} values.
    * @param object the {@link Object} being added to the graph.
    * @return a sensible {@link String}, not null.
    */
   private String defendString( Object object ) {
      if ( object == null ) {
         return undefinedString;
      } else {
         return object.toString();
      }
   }// End Method
   
   /**
    * Validation method for the horizontal axis of the graph.
    * @return a {@link GraphResult} with the error, if an error exists, null otherwise.
    */
   private GraphResult verifyHorizontalAxis(){
      if ( horizontalProperty == null ) {
         return new GraphResult( GraphError.MissingHorizontalAxis, null );
      } else {
         return null;
      }
   }// End Method
   
   /**
    * Validation method for the vertical axes of the graph.
    * @return a {@link GraphResult} with the error, if an error exists, null otherwise.
    */
   private GraphResult verifyVerticalAxes(){
      if ( verticalProperties.isEmpty() ) {
         return new GraphResult( GraphError.MissingVerticalSeries, null );
      }
      for ( PropertyType type : verticalProperties ) {
         if ( !type.getParameterType().equals( ClassParameterTypes.NUMBER_PARAMETER_TYPE ) ) {
            return new GraphResult( GraphError.NonNumericalVerticalAxis, type );
         }
      }
      return null;
   }// End Method

   /**
    * Validation method for the {@link Search} associated with the graph.
    * @return a {@link GraphResult} with the error, if an error exists, null otherwise.
    */
   private GraphResult verifySearch(){
      if ( dataSeries == null ) {
         return new GraphResult( GraphError.MissingSearchCriteria, null );
      } else {
         return null;
      }
   }// End Method
   
}// End Class