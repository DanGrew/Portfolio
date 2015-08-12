/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph.sorting;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the {@link GraphSort}.
 */
public class GraphSortTest {

   /**
    * {@link GraphSort#StringAlphabetical} test.
    */
   @Test public void shouldStringAlphabeticSortWithSafeData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "not", 1 ) );
      series.getData().add( new Data< String, Number >( "in", 2 ) );
      series.getData().add( new Data< String, Number >( "order", 3 ) );
      series.getData().add( new Data< String, Number >( "of", 4 ) );
      series.getData().add( new Data< String, Number >( "any", 5 ) );
      series.getData().add( new Data< String, Number >( "sort", 6 ) );
      
      GraphSort.StringAlphabetical.sort( series.getData() );
      
      Assert.assertEquals( "any", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "in", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "not", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "of", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "order", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "sort", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#StringAlphabetical} test.
    */
   @Test public void shouldStringAlphabeticSortWithMissingData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( null, 1 ) );
      series.getData().add( new Data< String, Number >( "of", 2 ) );
      series.getData().add( new Data< String, Number >( null, 3 ) );
      series.getData().add( new Data< String, Number >( "in", 4 ) );
      series.getData().add( new Data< String, Number >( null, 5 ) );
      series.getData().add( new Data< String, Number >( "sort", 6 ) );
      
      GraphSort.StringAlphabetical.sort( series.getData() );
      
      Assert.assertEquals( null, series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "in", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "of", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "sort", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#StringReverseAlphabetical} test.
    */
   @Test public void shouldStringAlphabeticSortWithEqualData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "same", 1 ) );
      series.getData().add( new Data< String, Number >( "of", 2 ) );
      series.getData().add( new Data< String, Number >( "same", 3 ) );
      series.getData().add( new Data< String, Number >( "in", 4 ) );
      series.getData().add( new Data< String, Number >( "same", 5 ) );
      series.getData().add( new Data< String, Number >( "sort", 6 ) );
      
      GraphSort.StringAlphabetical.sort( series.getData() );
      
      Assert.assertEquals( "in", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "of", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "same", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "same", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "same", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "sort", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method

   /**
    * {@link GraphSort#StringReverseAlphabetical} test.
    */
   @Test public void shouldStringReverseAlphabeticSortWithSafeData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "not", 1 ) );
      series.getData().add( new Data< String, Number >( "in", 2 ) );
      series.getData().add( new Data< String, Number >( "order", 3 ) );
      series.getData().add( new Data< String, Number >( "of", 4 ) );
      series.getData().add( new Data< String, Number >( "any", 5 ) );
      series.getData().add( new Data< String, Number >( "sort", 6 ) );
      
      GraphSort.StringReverseAlphabetical.sort( series.getData() );
      
      Assert.assertEquals( "sort", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "order", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "of", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "not", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "in", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "any", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#StringReverseAlphabetical} test.
    */
   @Test public void shouldStringReverseAlphabeticSortWithMissingData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( null, 1 ) );
      series.getData().add( new Data< String, Number >( "of", 2 ) );
      series.getData().add( new Data< String, Number >( null, 3 ) );
      series.getData().add( new Data< String, Number >( "in", 4 ) );
      series.getData().add( new Data< String, Number >( null, 5 ) );
      series.getData().add( new Data< String, Number >( "sort", 6 ) );
      
      GraphSort.StringReverseAlphabetical.sort( series.getData() );
      
      Assert.assertEquals( "sort", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "of", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "in", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#StringReverseAlphabetical} test.
    */
   @Test public void shouldStringReverseAlphabeticSortWithEqualData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "same", 1 ) );
      series.getData().add( new Data< String, Number >( "of", 2 ) );
      series.getData().add( new Data< String, Number >( "same", 3 ) );
      series.getData().add( new Data< String, Number >( "in", 4 ) );
      series.getData().add( new Data< String, Number >( "same", 5 ) );
      series.getData().add( new Data< String, Number >( "sort", 6 ) );
      
      GraphSort.StringReverseAlphabetical.sort( series.getData() );
      
      Assert.assertEquals( "sort", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "same", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "same", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "same", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "of", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "in", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 5 ).getYValue() );
   }// End Method
}// End Class
