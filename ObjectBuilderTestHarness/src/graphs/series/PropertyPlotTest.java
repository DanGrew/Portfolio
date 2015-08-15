/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

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
 * {@link PropertyPlot} test.
 */
public class PropertyPlotTest {

   /**
    * {@link PropertyPlot#constructSeries(Search, PropertyType, String, Number)} test.
    */
   @Test public void shouldConstructWithBasicSafeData() {
      PropertyType sampleVertical = new PropertyTypeImpl( "name", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new PropertyPlot( sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "anything", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "another", 2.0 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "else", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "blank", -9.0 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "something", 16.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, null, null );
      Assert.assertEquals( "anything", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 25.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "another", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2.0, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "else", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 234.4355, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "blank", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( -9.0, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "something", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 16.0, series.getData().get( 4 ).getYValue() );
   }// End Method
   
   /**
    * {@link PropertyPlot#constructSeries(Search, PropertyType, String, Number)} test.
    */
   @Test public void shouldConstructWithBasicMissingData() {
      PropertyType sampleVertical = new PropertyTypeImpl( "name", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new PropertyPlot( sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "another", null );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "else", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "blank", null );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, null, 16.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, "DEFAULT", -202 );
      Assert.assertEquals( "DEFAULT", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 25.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "another", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( -202, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "else", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 234.4355, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "blank", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( -202, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "DEFAULT", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 16.0, series.getData().get( 4 ).getYValue() );
   }// End Method

   /**
    * {@link PropertyPlot#constructSeries(Search, PropertyType, String, Number)} test.
    */
   @Test public void shouldConstructWithBasicDuplicateData() {
      PropertyType sampleVertical = new PropertyTypeImpl( "name", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      PropertyType sampleHorizontal = new PropertyTypeImpl( "horizontal", ClassParameterTypes.STRING_PARAMETER_TYPE );
      SeriesExtractor plot = new PropertyPlot( sampleVertical );
      
      BuilderObject objectA = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "another", 25.0 );
      BuilderObject objectB = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "another", -2 );
      BuilderObject objectC = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "else", 234.4355 );
      BuilderObject objectD = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "blank", -2 );
      BuilderObject objectE = SeriesExtractorFunctions.createBuilderObject( sampleHorizontal, sampleVertical, "another", 16.0 );
      
      Search search = Mockito.mock( Search.class );
      Mockito.when( search.getMatches() ).thenReturn( Arrays.asList( objectA, objectB, objectC, objectD, objectE ) );
      
      Series< String, Number > series = plot.constructSeries( search, sampleHorizontal, "DEFAULT", -202 );
      Assert.assertEquals( "another", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 25.0, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "another", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( -2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "else", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 234.4355, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "blank", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( -2, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "another", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 16.0, series.getData().get( 4 ).getYValue() );
   }// End Method
   
}// End Class
