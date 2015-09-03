/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph.sorting;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import parameter.classparameter.DateClassParameterTypeImpl;

/**
 * Test for {@link GraphDataPolicy}.
 */
public class GraphDataPolicyTest {

   /**
    * {@link GraphDataPolicy#ContinuousNumbers#convertStringToNumber(String, String)} test.
    */
   @Test public void shouldConvertStringToNumber(){
      Assert.assertEquals( 34.0, GraphDataPolicy.ContinuousNumbers.convertStringToNumber( "34", null ) );
      Assert.assertEquals( 34.0, GraphDataPolicy.ContinuousNumbers.convertStringToNumber( "34.0", null ) );
      Assert.assertEquals( 0.938, GraphDataPolicy.ContinuousNumbers.convertStringToNumber( "0.938", null ) );
      Assert.assertEquals( 0.938, GraphDataPolicy.ContinuousNumbers.convertStringToNumber( "0.938", null ) );
      Assert.assertEquals( 100.0, GraphDataPolicy.ContinuousNumbers.convertStringToNumber( "freddy", "100.0" ) );
      Assert.assertEquals( 0.0, GraphDataPolicy.ContinuousNumbers.convertStringToNumber( "freddy", "mercury" ) );
   }//End Method
   
   /**
    * {@link GraphDataPolicy#ContinuousNumbers#convertNumberToString(Number)} test.
    */
   @Test public void shouldConvertNumberToString(){
      Assert.assertEquals( "34.0", GraphDataPolicy.ContinuousNumbers.convertNumberToString( 34 ) );
      Assert.assertEquals( "34.0", GraphDataPolicy.ContinuousNumbers.convertNumberToString( 34.0 ) );
      Assert.assertEquals( null, GraphDataPolicy.ContinuousNumbers.convertNumberToString( null ) );
   }//End Method
   
   /**
    * {@link GraphDataPolicy#ContinuousDates#convertStringToNumber(String, String)} test.
    */
   @Test public void shouldConvertDateToNumber(){
      Assert.assertEquals( 
               DateClassParameterTypeImpl.parse( "01/01/2015" ).toEpochDay(), 
               GraphDataPolicy.ContinuousDates.convertStringToNumber( "01/01/2015", null ) 
      );
      Assert.assertEquals( 
               DateClassParameterTypeImpl.parse( "01/01/4015" ).toEpochDay(), 
               GraphDataPolicy.ContinuousDates.convertStringToNumber( "01/01/4015", null ) 
      );
      Assert.assertEquals( 
               DateClassParameterTypeImpl.parse( "01/01/1015" ).toEpochDay(), 
               GraphDataPolicy.ContinuousDates.convertStringToNumber( "01/01/1015", null ) 
      );
      Assert.assertEquals( 
               DateClassParameterTypeImpl.parse( "09/10/1988" ).toEpochDay(), 
               GraphDataPolicy.ContinuousDates.convertStringToNumber( "anything", "09/10/1988" ) 
      );
      Assert.assertEquals( 
               0.0, 
               GraphDataPolicy.ContinuousDates.convertStringToNumber( "anything", "else" ) 
      );
   }//End Method
   
   /**
    * {@link GraphDataPolicy#ContinuousDates#convertNumberToString(Number)} test.
    */
   @Test public void shouldConvertNumberToDate(){
      Assert.assertEquals( 
               "2015-01-01", 
               GraphDataPolicy.ContinuousDates.convertNumberToString( DateClassParameterTypeImpl.parse( "01/01/2015" ).toEpochDay() ) 
      );
      Assert.assertEquals( 
               null, 
               GraphDataPolicy.ContinuousDates.convertNumberToString( null ) 
      );
   }//End Method
   
   /**
    * {@link GraphDataPolicy#ContinuousNumbers#convertStringSeries(Series, String)} test.
    */
   @Test public void shouldSupportContinuousNumberData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "34", 1 ) );
      series.getData().add( new Data< String, Number >( "14.5", 2 ) );
      series.getData().add( new Data< String, Number >( "87", 3 ) );
      series.getData().add( new Data< String, Number >( "0.00023476", 4 ) );
      series.getData().add( new Data< String, Number >( null, 5 ) );
      series.getData().add( new Data< String, Number >( "34", 6 ) );
      
      Series< Number, Number > converted = GraphDataPolicy.ContinuousNumbers.convertStringSeries( series, "100" );
      
      Assert.assertEquals( 34.0, converted.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 1, converted.getData().get( 0 ).getYValue() );
      Assert.assertEquals( 14.5, converted.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2, converted.getData().get( 1 ).getYValue() );
      Assert.assertEquals( 87.0, converted.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 3, converted.getData().get( 2 ).getYValue() );
      Assert.assertEquals( 0.00023476, converted.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 4, converted.getData().get( 3 ).getYValue() );
      Assert.assertEquals( 100.0, converted.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 5, converted.getData().get( 4 ).getYValue() );
      Assert.assertEquals( 34.0, converted.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, converted.getData().get( 5 ).getYValue() );
   }//End Method
   
   /**
    * {@link GraphDataPolicy#ContinuousDates#convertStringSeries(Series, String)} test.
    */
   @Test public void shouldSupportContinuousDateData() {
      Series< String, Number > series = new Series<>();
      series.getData().add( new Data< String, Number >( "01/01/2015", 1 ) );
      series.getData().add( new Data< String, Number >( "34876", 2 ) );
      series.getData().add( new Data< String, Number >( "21/09/1915", 3 ) );
      series.getData().add( new Data< String, Number >( "01/01/4015", 4 ) );
      series.getData().add( new Data< String, Number >( null, 5 ) );
      series.getData().add( new Data< String, Number >( "01/01/2015", 6 ) );
      
      Series< Number, Number > converted = GraphDataPolicy.ContinuousDates.convertStringSeries( series, "09/10/1988" );
      
      Assert.assertEquals( DateClassParameterTypeImpl.parse( "01/01/2015" ).toEpochDay(), converted.getData().get( 0 ).getXValue() );
      Assert.assertEquals( 1, converted.getData().get( 0 ).getYValue() );
      Assert.assertEquals( DateClassParameterTypeImpl.parse( "09/10/1988" ).toEpochDay(), converted.getData().get( 1 ).getXValue() );
      Assert.assertEquals( 2, converted.getData().get( 1 ).getYValue() );
      Assert.assertEquals( DateClassParameterTypeImpl.parse( "21/09/1915" ).toEpochDay(), converted.getData().get( 2 ).getXValue() );
      Assert.assertEquals( 3, converted.getData().get( 2 ).getYValue() );
      Assert.assertEquals( DateClassParameterTypeImpl.parse( "01/01/4015" ).toEpochDay(), converted.getData().get( 3 ).getXValue() );
      Assert.assertEquals( 4, converted.getData().get( 3 ).getYValue() );
      Assert.assertEquals( DateClassParameterTypeImpl.parse( "09/10/1988" ).toEpochDay(), converted.getData().get( 4 ).getXValue() );
      Assert.assertEquals( 5, converted.getData().get( 4 ).getYValue() );
      Assert.assertEquals( DateClassParameterTypeImpl.parse( "01/01/2015" ).toEpochDay(), converted.getData().get( 5 ).getXValue() );
      Assert.assertEquals( 6, converted.getData().get( 5 ).getYValue() );
   }//End Method

}//End Class
