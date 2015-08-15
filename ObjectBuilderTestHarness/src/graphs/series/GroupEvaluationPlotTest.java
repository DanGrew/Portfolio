/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import graphs.series.GroupEvaluationPlot.GroupEvaluation;

import java.util.Arrays;

import javafx.scene.chart.XYChart.Series;
import object.BuilderObject;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import search.Search;

/**
 * Test for the {@link GroupEvaluation}.
 */
public class GroupEvaluationPlotTest {

   /**
    * {@link GroupEvaluation#Count} test.
    */
   @Test public void shouldCount() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.Count, sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 2.0 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", -9.0 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 16.0 );
      BuilderObject objectF = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", null );
      BuilderObject objectG = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 16.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE, objectF, objectG ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 4.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2.0, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 1.0, series.getData().get( 2 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#Maximum} test.
    */
   @Test public void shouldFindMaximum() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.Maximum, sampleVertical );
      
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
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 25.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 234.4355, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "third", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( -10.0, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "fourth", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 0.0, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 16.0, series.getData().get( 4 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#Minimum} test.
    */
   @Test public void shouldFindMinimum() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.Minimum, sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 2.0 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "second", -9.0 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", 16.0 );
      BuilderObject objectF = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "first", null );
      BuilderObject objectG = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 16.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE, objectF, objectG ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 0.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( -9.0, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 16.0, series.getData().get( 2 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#Sum} test.
    */
   @Test public void shouldCalculateSum() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.Sum, sampleVertical );
      
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
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 43.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 225.4355, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 17.0, series.getData().get( 2 ).getYValue() );
   }// End Method
   
   /**
    * {@link GroupEvaluation#Average} test.
    */
   @Test public void shouldCalculateAverage() {
      PropertyType sampleVertical = new PropertyTypeImpl( "taskLength", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new GroupEvaluationPlot( GroupEvaluation.Average, sampleVertical );
      
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
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, "x", 0.0 );
      Assert.assertEquals( "first", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 43.0/4, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "second", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 225.4355 / 2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "x", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 8.5, series.getData().get( 2 ).getYValue() );
   }// End Method

}// End Class
