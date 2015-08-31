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
      
      GraphSort.StringAlphabetical.sortSeries( series.getData() );
      
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
      
      GraphSort.StringAlphabetical.sortSeries( series.getData() );
      
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
      
      GraphSort.StringAlphabetical.sortSeries( series.getData() );
      
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
      
      GraphSort.StringReverseAlphabetical.sortSeries( series.getData() );
      
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
      
      GraphSort.StringReverseAlphabetical.sortSeries( series.getData() );
      
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
      
      GraphSort.StringReverseAlphabetical.sortSeries( series.getData() );
      
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
   
   /**
    * {@link GraphSort#NumberAscending} test.
    */
   @Test public void shouldNumberAscendingSortWithSafeData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "12", 1 ) );
      series.getData().add( new Data< String, Number >( "5", 2 ) );
      series.getData().add( new Data< String, Number >( "13", 3 ) );
      series.getData().add( new Data< String, Number >( "12.001", 4 ) );
      series.getData().add( new Data< String, Number >( "1", 5 ) );
      series.getData().add( new Data< String, Number >( "1765432", 6 ) );
      
      GraphSort.NumberAscending.sortSeries( series.getData() );
      
      Assert.assertEquals( "1", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "5", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "12", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "12.001", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "13", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "1765432", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#NumberAscending} test.
    */
   @Test public void shouldNumberAscendingSortWithMissingData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( null, 1 ) );
      series.getData().add( new Data< String, Number >( "5", 2 ) );
      series.getData().add( new Data< String, Number >( null, 3 ) );
      series.getData().add( new Data< String, Number >( "13", 4 ) );
      series.getData().add( new Data< String, Number >( null, 5 ) );
      series.getData().add( new Data< String, Number >( "1765432", 6 ) );
      
      GraphSort.NumberAscending.sortSeries( series.getData() );
      
      Assert.assertEquals( null, series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "5", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "13", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "1765432", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#NumberAscending} test.
    */
   @Test public void shouldNumberAscendingSortWithEqualData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "8", 1 ) );
      series.getData().add( new Data< String, Number >( "5", 2 ) );
      series.getData().add( new Data< String, Number >( "8", 3 ) );
      series.getData().add( new Data< String, Number >( "1", 4 ) );
      series.getData().add( new Data< String, Number >( "8", 5 ) );
      series.getData().add( new Data< String, Number >( "1765432", 6 ) );
      
      GraphSort.NumberAscending.sortSeries( series.getData() );
      
      Assert.assertEquals( "1", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "5", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "8", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "8", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "8", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "1765432", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method

   /**
    * {@link GraphSort#NumberDescending} test.
    */
   @Test public void shouldNumberDescendingAlphabeticSortWithSafeData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "12", 1 ) );
      series.getData().add( new Data< String, Number >( "5", 2 ) );
      series.getData().add( new Data< String, Number >( "13", 3 ) );
      series.getData().add( new Data< String, Number >( "12.001", 4 ) );
      series.getData().add( new Data< String, Number >( "1", 5 ) );
      series.getData().add( new Data< String, Number >( "1765432", 6 ) );
      
      GraphSort.NumberDescending.sortSeries( series.getData() );
      
      Assert.assertEquals( "1765432", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "13", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "12.001", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "12", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "5", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "1", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#NumberDescending} test.
    */
   @Test public void shouldNumberDescendingAlphabeticSortWithMissingData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( null, 1 ) );
      series.getData().add( new Data< String, Number >( "5", 2 ) );
      series.getData().add( new Data< String, Number >( null, 3 ) );
      series.getData().add( new Data< String, Number >( "13", 4 ) );
      series.getData().add( new Data< String, Number >( null, 5 ) );
      series.getData().add( new Data< String, Number >( "1765432", 6 ) );
      
      GraphSort.NumberDescending.sortSeries( series.getData() );
      
      Assert.assertEquals( "1765432", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "13", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "5", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#NumberDescending} test.
    */
   @Test public void shouldNumberDescendingAlphabeticSortWithEqualData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "8", 1 ) );
      series.getData().add( new Data< String, Number >( "5", 2 ) );
      series.getData().add( new Data< String, Number >( "8", 3 ) );
      series.getData().add( new Data< String, Number >( "1", 4 ) );
      series.getData().add( new Data< String, Number >( "8", 5 ) );
      series.getData().add( new Data< String, Number >( "1765432", 6 ) );
      
      GraphSort.NumberDescending.sortSeries( series.getData() );
      
      Assert.assertEquals( "1765432", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "8", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "8", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "8", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "5", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "1", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#DateAscending} test.
    */
   @Test public void shouldDateAscendingSortWithSafeData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "27/01/2016", 1 ) );
      series.getData().add( new Data< String, Number >( "15/10/2015", 2 ) );
      series.getData().add( new Data< String, Number >( "27/02/2016", 3 ) );
      series.getData().add( new Data< String, Number >( "28/01/2016", 4 ) );
      series.getData().add( new Data< String, Number >( "15/07/2015", 5 ) );
      series.getData().add( new Data< String, Number >( "27/01/4016", 6 ) );
      
      GraphSort.DateAscending.sortSeries( series.getData() );
      
      Assert.assertEquals( "15/07/2015", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "15/10/2015", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "27/01/2016", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "28/01/2016", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "27/02/2016", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "27/01/4016", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#DateAscending} test.
    */
   @Test public void shouldDateAscendingSortWithMissingData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( null, 1 ) );
      series.getData().add( new Data< String, Number >( "15/07/2015", 2 ) );
      series.getData().add( new Data< String, Number >( null, 3 ) );
      series.getData().add( new Data< String, Number >( "18/07/2015", 4 ) );
      series.getData().add( new Data< String, Number >( null, 5 ) );
      series.getData().add( new Data< String, Number >( "15/07/4015", 6 ) );
      
      GraphSort.DateAscending.sortSeries( series.getData() );
      
      Assert.assertEquals( null, series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "15/07/2015", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "18/07/2015", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "15/07/4015", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#DateAscending} test.
    */
   @Test public void shouldDateAscendingSortWithEqualData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "18/08/2015", 1 ) );
      series.getData().add( new Data< String, Number >( "15/07/2015", 2 ) );
      series.getData().add( new Data< String, Number >( "18/08/2015", 3 ) );
      series.getData().add( new Data< String, Number >( "15/08/2015", 4 ) );
      series.getData().add( new Data< String, Number >( "18/08/2015", 5 ) );
      series.getData().add( new Data< String, Number >( "20/08/4015", 6 ) );
      
      GraphSort.DateAscending.sortSeries( series.getData() );
      
      Assert.assertEquals( "15/07/2015", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 0 ).getYValue() );
      Assert.assertEquals( "15/08/2015", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "18/08/2015", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "18/08/2015", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "18/08/2015", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "20/08/4015", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 5 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#DateDescending} test.
    */
   @Test public void shouldDateDescendingSortWithSafeData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "27/01/2016", 1 ) );
      series.getData().add( new Data< String, Number >( "15/10/2015", 2 ) );
      series.getData().add( new Data< String, Number >( "27/02/2016", 3 ) );
      series.getData().add( new Data< String, Number >( "28/01/2016", 4 ) );
      series.getData().add( new Data< String, Number >( "15/07/2015", 5 ) );
      series.getData().add( new Data< String, Number >( "27/01/4016", 6 ) );
      
      GraphSort.DateDescending.sortSeries( series.getData() );
      
      Assert.assertEquals( "15/07/2015", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 5 ).getYValue() );
      Assert.assertEquals( "15/10/2015", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "27/01/2016", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "28/01/2016", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "27/02/2016", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "27/01/4016", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#DateDescending} test.
    */
   @Test public void shouldDateDescendingSortWithMissingData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( null, 1 ) );
      series.getData().add( new Data< String, Number >( "15/07/2015", 2 ) );
      series.getData().add( new Data< String, Number >( null, 3 ) );
      series.getData().add( new Data< String, Number >( "18/07/2015", 4 ) );
      series.getData().add( new Data< String, Number >( null, 5 ) );
      series.getData().add( new Data< String, Number >( "15/07/4015", 6 ) );
      
      GraphSort.DateDescending.sortSeries( series.getData() );
      
      Assert.assertEquals( null, series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( null, series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 5 ).getYValue() );
      Assert.assertEquals( "15/07/2015", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "18/07/2015", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "15/07/4015", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
   }// End Method
   
   /**
    * {@link GraphSort#DateDescending} test.
    */
   @Test public void shouldDateDescendingSortWithEqualData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "18/08/2015", 1 ) );
      series.getData().add( new Data< String, Number >( "15/07/2015", 2 ) );
      series.getData().add( new Data< String, Number >( "18/08/2015", 3 ) );
      series.getData().add( new Data< String, Number >( "15/08/2015", 4 ) );
      series.getData().add( new Data< String, Number >( "18/08/2015", 5 ) );
      series.getData().add( new Data< String, Number >( "20/08/4015", 6 ) );
      
      GraphSort.DateDescending.sortSeries( series.getData() );
      
      Assert.assertEquals( "15/07/2015", series.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 2, series.getData().get( 5 ).getYValue() );
      Assert.assertEquals( "15/08/2015", series.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 4, series.getData().get( 4 ).getYValue() );
      Assert.assertEquals( "18/08/2015", series.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 1, series.getData().get( 1 ).getYValue() );
      Assert.assertEquals( "18/08/2015", series.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 3, series.getData().get( 2 ).getYValue() );
      Assert.assertEquals( "18/08/2015", series.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 5, series.getData().get( 3 ).getYValue() );
      Assert.assertEquals( "20/08/4015", series.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 6, series.getData().get( 0 ).getYValue() );
   }// End Method

}// End Class
