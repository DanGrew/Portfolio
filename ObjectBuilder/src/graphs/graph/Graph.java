/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import annotation.Cali;
import graphs.graph.sorting.GraphDataPolicy;
import graphs.graph.sorting.GraphSort;
import graphs.series.GroupEvaluation;
import graphs.series.SeriesExtractions;
import graphs.series.SeriesExtractor;
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
import javafx.scene.chart.XYChart.Series;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.singleton.SingletonImpl;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import search.Search;

/**
 * The {@link Graph} represents a configuration for an {@link XYChart}
 * that can be written to file and saved.
 */
@Cali public class Graph extends SingletonImpl< SerializableGraph > {
   
   private static final Number DEFAULT_UNDEFINED_NUMBER = 0.0;
   private static final String DEFAULT_UNDEFINED_STRING = "Unknown";
   private static final Dimension2D DEFAULT_DIMENSION = new Dimension2D( 800, 800 );
   private List< Search > dataSeries;
   private SeriesExtractions extractions;
   private String verticalAxisLabel;
   private PropertyType horizontalProperty;
   private GraphSort sorting;
   private GraphDataPolicy dataPolicy;
   private String horizontalAxisLabel;
   private Double horizontalFontSize;
   private Number undefinedNumber = DEFAULT_UNDEFINED_NUMBER;
   private String undefinedString = DEFAULT_UNDEFINED_STRING;
   private Dimension2D dimension = DEFAULT_DIMENSION;
   
   /**
    * Constructs a new {@link Graph}.
    * @param identification unique identifier for reference.
    */
   @Cali public Graph( String identification ) {
      super( identification );
      extractions = new SeriesExtractions();
      dataSeries = new ArrayList<>();
      dataPolicy = GraphDataPolicy.Discrete;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializableGraph serializable ) {
      dataSeries.forEach( search -> serializable.addSearch( search ) );
      extractions.getPropertyPlots().forEach( property -> serializable.addVerticalProperty( property ) );
      extractions.getGroupEvaluations().forEach( entry -> serializable.addGroupEvaluation( entry ) );
      serializable.setVerticalAxisLabel( verticalAxisLabel );
      serializable.setHorizontalProperty( horizontalProperty );
      serializable.setHorizontalSort( sorting );
      serializable.setDataPolicy( dataPolicy );
      serializable.setHorizontalAxisLabel( horizontalAxisLabel );
      serializable.setHorizontalFontSize( horizontalFontSize );
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
      serialized.resolveGroupEvaluations().forEach( entry -> addGroupEvaluation( entry.getKey(), entry.getValue() ) );
      verticalAxisLabel = serialized.getVerticalAxisLabel();
      horizontalProperty = serialized.getHorizontalProperty();
      sorting = serialized.getHorizontalSort();
      dataPolicy = serialized.getDataPolicy();
      horizontalAxisLabel = serialized.getHorizontalAxisLabel();
      horizontalFontSize = serialized.getHorizontalFontSize();
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
      if ( search == null ) {
         return;
      }
      this.dataSeries.add( search );
   }// End Method
   
   /**
    * Method to clear the {@link Search}s specified for the {@link Graph}.
    */
   @Cali public void clearDataSeries() {
      dataSeries.clear();
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
    * Getter for the {@link Font} size to use on the horizontal axis.
    * @return the size, can be null.
    */
   @Cali public Double getHorizontalFontSize() {
      return horizontalFontSize;
   }//End Method
   
   /**
    * Setter for the {@link Font} size to use on the horizontal axis. This can avoid positioning issues
    * when there a large number of entries on a rotated {@link Graph}.
    * @param fontSize the size.
    */
   @Cali public void setHorizontalFontSize( Double fontSize ) {
      this.horizontalFontSize = fontSize;
   }//End Method
   
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
    * Method to set the {@link GraphSort} to use for the horizontal axis.
    * @param sort the {@link GraphSort} to use.
    */
   @Cali public void setHorizontalSort( GraphSort sort ) {
      this.sorting = sort;
   }// End Method
   
   /**
    * Getter for the {@link GraphSort} to use for the horizontal axis.
    * @return the {@link GraphSort} to use, can be null.
    */
   @Cali public GraphSort getHorizontalSort(){
      return sorting;
   }// End Method
   
   /**
    * Method to set the {@link GraphDataPolicy} to use for the horizontal axis.
    * @param policy the {@link GraphDataPolicy}, must not be null.
    */
   @Cali public void setDataPolicy( GraphDataPolicy policy ) {
      if ( policy != null ) {
         dataPolicy = policy;
      } else {
         dataPolicy = GraphDataPolicy.Discrete;
      }
   }//End Method
   
   /**
    * Getter for the current {@link GraphDataPolicy}.
    * @return the {@link GraphDataPolicy}.
    */
   @Cali public GraphDataPolicy getDataPolicy(){
      return dataPolicy;
   }//End Method
   
   /**
    * Getter for the {@link PropertyType}s used for the vertical axis.
    * @return a {@link List} of {@link PropertyType}s on the vertical axis.
    */
   public List< PropertyType > getVerticalProperties() {
      return new ArrayList<>( extractions.getPropertyPlots() );
   }// End Method
   
   /**
    * Method to add a vertical series to the graph.
    * @param property the {@link PropertyType}, required to be {@link ClassParameterTypes#NUMBER_PARAMETER_TYPE}.
    * @return a {@link GraphResult} indicating the result of the add since this can fail if
    * a non number type is supplied.
    */
   @Cali public GraphResult addVerticalProperty( PropertyType property ) {
      if ( property.getParameterType().equals( ClassParameterTypes.NUMBER_PARAMETER_TYPE ) ) {
         extractions.addPropertyPlot( property );
         return GraphResult.SUCCESS;
      } else {
         return new GraphResult( GraphError.NonNumericalVerticalAxis, property );
      }
   }// End Method
   
   /**
    * Method to clear the vertical {@link PropertyType}s set on the {@link Graph}.
    */
   @Cali public void clearVerticalProperties() {
      extractions.removeAllPropertyPlots();
   }// End Method
   
   /**
    * Getter for the {@link GroupEvaluation}s used for the vertical axis.
    * @return a {@link List} of {@link GroupEvaluation}s on the vertical axis.
    */
   public List< Entry< PropertyType, GroupEvaluation > > getGroupEvaluations() {
      return new ArrayList<>( extractions.getGroupEvaluations() );
   }// End Method
   
   /**
    * Method to add a {@link GroupEvaluation} series to the graph.
    * @param property the {@link PropertyType}, required to be {@link ClassParameterTypes#NUMBER_PARAMETER_TYPE}.
    * @return a {@link GraphResult} indicating the result of the add since this can fail if
    * a non number type is supplied.
    */
   @Cali public GraphResult addGroupEvaluation( PropertyType property, GroupEvaluation evaluation ) {
      if ( evaluation.isCompatible( property.getParameterType() ) ) {
         extractions.addGroupEvaluation( property, evaluation );
         return GraphResult.SUCCESS;
      } else {
         return new GraphResult( GraphError.NonNumericalVerticalAxis, property );
      }
   }// End Method
   
   /**
    * Method to clear the vertical {@link PropertyType}s set on the {@link Graph}.
    */
   @Cali public void clearGroupEvaluations() {
      extractions.removeAllGroupEvaluations();
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
      if ( undefinedNumber == null ) {
         return;
      }
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
    * @param orienation the {@link GraphOrientation}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult barChart( GraphOrientation orienation ){
      return show( ChartType.BarChart, orienation );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link StackedBarChart}.
    * @param orienation the {@link GraphOrientation}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult stackedBarChart( GraphOrientation orienation ){
      return show( ChartType.StackedBarChart, orienation );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link LineChart}.
    * @param orienation the {@link GraphOrientation}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult lineChart( GraphOrientation orienation ){
      return show( ChartType.LineChart, orienation );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link ScatterChart}.
    * @param orienation the {@link GraphOrientation}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult scatterChart( GraphOrientation orienation ){
      return show( ChartType.ScatterChart, orienation );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link AreaChart}.
    * @param orienation the {@link GraphOrientation}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult areaChart( GraphOrientation orienation ){
      return show( ChartType.AreaChart, orienation );
   }// End Method
   
   /**
    * Method to display the {@link Graph} as a {@link StackedAreaChart}.
    * @param orienation the {@link GraphOrientation}.
    * @return the {@link GraphResult} of the graphical construction.
    */
   @Cali public GraphResult stackedAreaChart( GraphOrientation orienation ){
      return show( ChartType.StackedAreaChart, orienation );
   }// End Method
   
   /**
    * Method to show the graph. This launches a new window with the {@link Graph} inside.
    * @param orienation the {@link GraphOrientation}.
    * @return the {@link GraphResult} of the launch, {@link GraphError}s provided if not
    * configured correctly.
    */
   private GraphResult show( ChartType type, GraphOrientation orienation ) {
      GraphResult result = verifySearch();
      if ( result != null ) return result;
      
      result = verifyHorizontalAxis();
      if ( result != null ) return result;
      
      result = verifyVerticalAxes();
      if ( result != null ) return result;
      
      Platform.runLater( new Runnable() {
         
         @Override public void run() {
            Stage stage = new Stage();
            Scene scene = null;
            
            switch ( dataPolicy ) {
               case ContinuousDates:
               case ContinuousNumbers:
                  scene = createContinuousGraph( type, orienation );
                  break;
               case Discrete:
                  switch ( orienation ) {
                     case Horizontal:
                        scene = createHorizontalGraph( type );
                        break;
                     case Vertical:
                        scene = createVerticalGraph( type );
                        break;
                     default:
                        return;
                  }
                  break;
               default:
                  break;
               
            }
            if ( scene == null ) {
               return;
            }
            stage.setScene( scene );
            stage.show();
         }
      } );
      return GraphResult.SUCCESS;
   }// End Method
   
   /**
    * Method dedicated to creating a vertical {@link Chart}, {@link String} to {@link Number}.
    * @param type the {@link ChartType}.
    * @return a {@link Scene} constructed with the {@link Chart}.
    */
   private Scene createVerticalGraph( ChartType type ){
      final CategoryAxis horizontalAxis = new CategoryAxis();
      if ( horizontalFontSize != null ) {
         horizontalAxis.setTickLabelFont( Font.font( horizontalFontSize ) );
      }
      final NumberAxis verticalAxis = new NumberAxis();
      
      XYChart< String, Number > verticalGraph = createChart( type, horizontalAxis, verticalAxis );
      if ( verticalGraph == null ) {
         return null;
      }
      horizontalAxis.setLabel( horizontalAxisLabel );
      verticalAxis.setLabel( verticalAxisLabel );

      for ( Search search : dataSeries ) {
         for ( SeriesExtractor extractor : extractions.getExtractors() ) {
            Series< String, Number > series = extractor.constructSeries( 
                     search, horizontalProperty, sorting, undefinedString, undefinedNumber 
            ); 

            verticalGraph.getData().add( series );
         }
      }

      Scene scene = new Scene( 
         verticalGraph,
         dimension.getWidth(), 
         dimension.getHeight() 
      );
      return scene;
   }//End Method
   
   /**
    * Method dedicated to creating a horizontal {@link Chart}, {@link Number} to {@link String}.
    * @param type the {@link ChartType}.
    * @return a {@link Scene} constructed with the {@link Chart}.
    */
   private Scene createHorizontalGraph( ChartType type ){
      final CategoryAxis horizontalAxis = new CategoryAxis();
      if ( horizontalFontSize != null ) {
         horizontalAxis.setTickLabelFont( Font.font( horizontalFontSize ) );
      }
      final NumberAxis verticalAxis = new NumberAxis();
      
      XYChart< Number, String > horizontalGraph = createChart( type, verticalAxis, horizontalAxis );
      if ( horizontalGraph == null ) {
         return null;
      }
      horizontalAxis.setLabel( horizontalAxisLabel );
      verticalAxis.setLabel( verticalAxisLabel );

      for ( Search search : dataSeries ) {
         for ( SeriesExtractor extractor : extractions.getExtractors() ) {
            Series< String, Number > series = extractor.constructSeries( 
                     search, horizontalProperty, sorting, undefinedString, undefinedNumber 
            ); 

            Series< Number, String > reversed = SeriesExtractor.reverseParameters( series );
            horizontalGraph.getData().add( reversed );
         }
      }

      Scene scene = new Scene( 
         horizontalGraph,
         dimension.getWidth(), 
         dimension.getHeight() 
      );
      return scene;
   }//End Method
   
   /**
    * Method dedicated to creating a continuous axis {@link Chart}, {@link Number} to {@link Number}.
    * @param type the {@link ChartType}.
    * @param orientation the {@link GraphOrientation}.
    * @return a {@link Scene} constructed with the {@link Chart}.
    */
   private Scene createContinuousGraph( ChartType type, GraphOrientation orientation ){
      final NumberAxis horizontalAxis = new NumberAxis();
      horizontalAxis.setAutoRanging( true );
      horizontalAxis.setForceZeroInRange( false );
      horizontalAxis.setTickLabelFormatter( dataPolicy.getConverter() );
      if ( horizontalFontSize != null ) {
         horizontalAxis.setTickLabelFont( Font.font( horizontalFontSize ) );
      }
      final NumberAxis verticalAxis = new NumberAxis();
      
      XYChart< Number, Number > graph = null;
      switch ( orientation ) {
         case Vertical:
            graph = createChart( type, horizontalAxis, verticalAxis );
            break;
         case Horizontal:
            graph = createChart( type, verticalAxis, horizontalAxis );
            break;
         default:
            return null;
      }
      if ( graph == null ) {
         return null;
      }
      horizontalAxis.setLabel( horizontalAxisLabel );
      verticalAxis.setLabel( verticalAxisLabel );

      for ( Search search : dataSeries ) {
         for ( SeriesExtractor extractor : extractions.getExtractors() ) {
            Series< String, Number > series = extractor.constructSeries( 
                     search, horizontalProperty, sorting, undefinedString, undefinedNumber 
            ); 
            
            Series< Number, Number > converted = null;
            switch ( orientation ) {
               case Vertical:
                  converted = dataPolicy.convertHorizontalSeries( series, undefinedString );
                  break;
               case Horizontal:
                  Series< Number, String > reversed = SeriesExtractor.reverseParameters( series );
                  converted = dataPolicy.convertVerticalSeries( reversed, undefinedString );
                  break;
               default:
                  return null;
            }
            graph.getData().add( converted );
         }
      }

      Scene scene = new Scene( 
         graph,
         dimension.getWidth(), 
         dimension.getHeight() 
      );
      return scene;
   }//End Method
   
   /**
    * Method to create and instance of the appropriate {@link XYChart} given the {@link ChartType}.
    * This method exists in place of using a factory method to avoid generic issues.
    * @param type the {@link ChartType} to create.
    * @param horizontalAxis the {@link Axis} to use for the horizontal.
    * @param verticalAxis the {@link Axis} to use for the vertical.
    * @return the constructed {@link XYChart}.
    */
   private < X, Y > XYChart< X, Y > createChart( ChartType type, Axis< X > horizontalAxis, Axis< Y > verticalAxis ) {
      try {
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
      } catch ( IllegalArgumentException exception ) {
         /* Java has hardcoded exceptions that aren't very friendly for dynamically creating
          * charts. This catch safely returns if these are unexpectedly hit.*/
         return null;
      }
   }// End Method
   
   /**
    * Validation method for the horizontal axis of the graph.
    * @return a {@link GraphResult} with the error, if an error exists, null otherwise.
    */
   private GraphResult verifyHorizontalAxis(){
      if ( horizontalProperty == null ) {
         return new GraphResult( GraphError.MissingHorizontalAxis, null );
      } else if ( dataPolicy == null ) {
         dataPolicy = GraphDataPolicy.Discrete;
         return null;
      } else {
         return null;
      }
   }// End Method
   
   /**
    * Validation method for the vertical axes of the graph.
    * @return a {@link GraphResult} with the error, if an error exists, null otherwise.
    */
   private GraphResult verifyVerticalAxes(){
      if ( !extractions.hasPropertyPlots() && !extractions.hasGroupEvaluations() ) {
         return new GraphResult( GraphError.MissingVerticalSeries, null );
      }
      for ( PropertyType type : extractions.getPropertyPlots() ) {
         if ( !type.getParameterType().equals( ClassParameterTypes.NUMBER_PARAMETER_TYPE ) ) {
            return new GraphResult( GraphError.NonNumericalVerticalAxis, type );
         }
      }
      
      for ( Entry< PropertyType, GroupEvaluation > entry : extractions.getGroupEvaluations() ) {
         if ( !entry.getValue().isCompatible( entry.getKey().getParameterType() ) ) {
            return new GraphResult( GraphError.NonNumericalVerticalAxis, entry );
         }
      }
      return null;
   }// End Method

   /**
    * Validation method for the {@link Search} associated with the graph.
    * @return a {@link GraphResult} with the error, if an error exists, null otherwise.
    */
   private GraphResult verifySearch(){
      if ( dataSeries.isEmpty() ) {
         return new GraphResult( GraphError.MissingSearchCriteria, null );
      } else {
         return null;
      }
   }// End Method

}// End Class
