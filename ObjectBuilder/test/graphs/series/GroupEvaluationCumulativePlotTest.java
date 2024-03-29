/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javafx.scene.chart.XYChart.Series;
import object.BuilderObject;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import search.Search;

/**
 * Test for the {@link GroupEvaluation}.
 */
public class GroupEvaluationCumulativePlotTest {

   /**
    * {@link GroupEvaluation#CumulativeCount} test.
    */
   @Test public void shouldCumulativeCount() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.CumulativeCount, sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 2.0 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", -9.0 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 16.0 );
      BuilderObject objectF = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", null );
      BuilderObject objectG = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 16.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE, objectF, objectG ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, null, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 4.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 6.0, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 7.0, series.getData().get( 2 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#CumulativeCount} test.
    */
   @Test public void shouldCumulativeCountNonNumber() {
      PropertyType sampleVertical = new PropertyTypeImpl( "anything", ClassParameterTypes.STRING_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.CumulativeCount, sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", "value" );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", "doesn't" );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", "matter" );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", "at" );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", "all" );
      BuilderObject objectF = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", null );
      BuilderObject objectG = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, "anything" );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE, objectF, objectG ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, null, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 4.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 6.0, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 7.0, series.getData().get( 2 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#CumulativeMaximum} test.
    */
   @Test public void shouldFindCumulativeMaximum() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.CumulativeMaximum, sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 2.0 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", -9.0 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "third", -10.0 );
      BuilderObject objectF = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "third", -3498.0 );
      BuilderObject objectG = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "fourth", null );
      BuilderObject objectH = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 16.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE, objectF, objectG, objectH ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, null, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 25.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 234.4355, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "third", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 234.4355, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "fourth", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 234.4355, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 234.4355, series.getData().get( 4 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#CumulativeMinimum} test.
    */
   @Test public void shouldFindCumulativeMinimum() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.CumulativeMinimum, sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 2.0 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", -9.0 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 16.0 );
      BuilderObject objectF = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", null );
      BuilderObject objectG = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 16.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE, objectF, objectG ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, null, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 0.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( -9.0, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( -9.0, series.getData().get( 2 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#CumulativeSum} test.
    */
   @Test public void shouldCalculateCumulativeSum() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.CumulativeSum, sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 2.0 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", -9.0 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 16.0 );
      BuilderObject objectF = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", null );
      BuilderObject objectG = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 16.0 );
      BuilderObject objectH = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 1.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE, objectF, objectG, objectH ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, null, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 43.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 268.4355, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 285.4355, series.getData().get( 2 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#CumulativeAverage} test.
    */
   @Test public void shouldCalculateCumulativeAverage() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.CumulativeAverage, sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 2.0 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", -9.0 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 16.0 );
      BuilderObject objectF = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", null );
      BuilderObject objectG = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 16.0 );
      BuilderObject objectH = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 1.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE, objectF, objectG, objectH ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, null, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 43.0/4, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( (43.0/4 + 225.4355/2) / 2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( (43.0/4 + 225.4355/2 + 8.5) / 3, series.getData().get( 2 ).getYValue() );
   }// End Method

}// End Class
