/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.barchart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import model.singleton.SingletonImpl;
import object.BuilderObject;
import parameter.classparameter.ClassParameterTypes;
import property.Property;
import propertytype.PropertyType;
import search.Search;

/**
 * The {@link BarChartGraph} represents a configuration for a {@link BarChart}
 * that can be written to file and saved.
 */
public class BarChartGraph extends SingletonImpl< SerializableBarChart > {
   
   private static final Number DEFAULT_UNDEFINED_NUMBER = 0.0;
   private static final String DEFAULT_UNDEFINED_STRING = "Unknown";
   private static final Dimension2D DEFAULT_DIMENSION = new Dimension2D( 800, 800 );
   private Search search;
   private List< PropertyType > verticalSeriesProperties;
   private String verticalAxisLabel;
   private PropertyType horizontalProperty;
   private String horizontalAxisLabel;
   private Number undefinedNumber = DEFAULT_UNDEFINED_NUMBER;
   private String undefinedString = DEFAULT_UNDEFINED_STRING;
   private Dimension2D dimension = DEFAULT_DIMENSION;
   
   /**
    * Constructs a new {@link BarChartGraph}.
    * @param identification unique identifier for reference.
    */
   public BarChartGraph( String identification ) {
      super( identification );
      verticalSeriesProperties = new ArrayList< PropertyType >();
   }// End Constructor
   
   @Override protected void writeSingleton( SerializableBarChart serializable ) {}
   @Override protected void readSingleton( SerializableBarChart serialized ) {}

   /**
    * Method to specify the {@link Search} used to identify results.
    * @param search the {@link Search} used to collect information for the {@link BarChart}.
    */
   public void useSearch( Search search ) {
      this.search = search;
   }// End Method

   /**
    * Setter for the label of the horizontal axis.
    * @param horizontalAxisLabel the label.
    */
   public void setHorizontalAxisLabel( String horizontalAxisLabel ) {
      this.horizontalAxisLabel = horizontalAxisLabel;
   }// End Method
   
   /**
    * Method to set the {@link PropertyType} for the information displayed on the
    * horizontal axis.
    * @param property the {@link PropertyType} to use for the horizontal axis.
    */
   public void setHorizontalProperty( PropertyType property ) {
      horizontalProperty = property;
   }// End Method
   
   /**
    * Method to add a vertical series to the graph.
    * @param property the {@link PropertyType}, required to be {@link ClassParameterTypes#NUMBER_PARAMETER_TYPE}.
    * @return a {@link GraphResult} indicating the result of the add since this can fail if
    * a non number type is supplied.
    */
   public GraphResult addVerticalSeries( PropertyType property ) {
      if ( property.getParameterType().equals( ClassParameterTypes.NUMBER_PARAMETER_TYPE ) ) {
         verticalSeriesProperties.add( property );
         return GraphResult.SUCCESS;
      } else {
         return new GraphResult( BarChartError.NonNumericalVerticalAxis, property );
      }
   }// End Method
   
   /**
    * Setter for the label of the vertical axis.
    * @param verticalAxisLabel the label.
    */
   public void setVerticalAxisLabel( String verticalAxisLabel ) {
      this.verticalAxisLabel = verticalAxisLabel;
   }// End Method
   
   /**
    * Setter for the default {@link Number} value to be used for a {@link Property} that has no value, or
    * a non number value.
    * @param undefinedNumber the {@link Number} to use by default.
    */
   public void setDefaultValueForUndefinedNumber( Number undefinedNumber ) {
      this.undefinedNumber = undefinedNumber;
   }// End Method
   
   /**
    * Setter for the default {@link String} value to be used for a {@link Property} that has no value.
    * @param undefinedString the {@link String} to use by default.
    */
   public void setDefaultValueForUndefinedString( String undefinedString ) {
      this.undefinedString = undefinedString;
   }// End Method
   
   /**
    * Method to set the {@link Dimension2D} of the graph.
    * @param width the width.
    * @param height the height.
    */
   public void setDimension( double width, double height ) {
      this.dimension = new Dimension2D( width, height );
   }// End Method
   
   /**
    * Method to show the graph. This launches a new window with the {@link BarChart} inside.
    * @return the {@link GraphResult} of the launch, {@link BarChartError}s provided if not
    * configured correctly.
    */
   public GraphResult show() {
      GraphResult result = verifySearch();
      if ( result != null ) return result;
      
      result = verifyHorizontalAxis();
      if ( result != null ) return result;
      
      result = verifyVerticalAxes();
      if ( result != null ) return result;
      
      new JFXPanel();
      Platform.runLater( new Runnable() {
         
         @Override public void run() {
            Stage stage = new Stage();
            
            final CategoryAxis horizontalAxis = new CategoryAxis();
            final NumberAxis verticalAxis = new NumberAxis();
            
            final BarChart< String, Number > barChart = new BarChart<>( horizontalAxis, verticalAxis );
            horizontalAxis.setLabel( horizontalAxisLabel );
            verticalAxis.setLabel( verticalAxisLabel );

            Collection< BuilderObject > matches = search.getMostResultMatches();
            
            for ( PropertyType type : verticalSeriesProperties ) {
               Series< String, Number > series = new Series<>();
               series.setName( type.getDisplayName() );
               for ( BuilderObject object : matches ) {
                  Number verticalValue = defendNumber( object.get( type ) );
                  String horizontalValue = defendString( object.get( horizontalProperty ) );
                  Data< String, Number > data = new Data<>( horizontalValue, verticalValue );
                  series.getData().add( data );
               }
               barChart.getData().add( series );
            }

            Scene scene = new Scene( barChart, dimension.getWidth(), dimension.getHeight() );
            stage.setScene( scene );
            stage.show();
         }
      } );
      return GraphResult.SUCCESS;
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
         return new GraphResult( BarChartError.MissingHorizontalAxis, null );
      } else {
         return null;
      }
   }// End Method
   
   /**
    * Validation method for the vertical axes of the graph.
    * @return a {@link GraphResult} with the error, if an error exists, null otherwise.
    */
   private GraphResult verifyVerticalAxes(){
      if ( verticalSeriesProperties.isEmpty() ) {
         return new GraphResult( BarChartError.MissingVerticalSeries, null );
      }
      for ( PropertyType type : verticalSeriesProperties ) {
         if ( !type.getParameterType().equals( ClassParameterTypes.NUMBER_PARAMETER_TYPE ) ) {
            return new GraphResult( BarChartError.NonNumericalVerticalAxis, type );
         }
      }
      return null;
   }// End Method

   /**
    * Validation method for the {@link Search} associated with the graph.
    * @return a {@link GraphResult} with the error, if an error exists, null otherwise.
    */
   private GraphResult verifySearch(){
      if ( search == null ) {
         return new GraphResult( BarChartError.MissingSearchCriteria, null );
      } else {
         return null;
      }
   }// End Method
   
}// End Class
