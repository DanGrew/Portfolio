/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package caliobjects;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the {@link Calculator}.
 */
public class CalculatorTest {

   /**
    * {@link Calculator} constructor test.
    */
   @Test public void shouldConstruct() {
      final String name = "name";
      Calculator calculator = new Calculator( name );
      Assert.assertEquals( name, calculator.getIdentification() );
   }// End Method
   
   /**
    * {@link Calculator#add(String, String)} acceptance test.
    */
   @Test public void shouldAdd(){
      Calculator calculator = new Calculator( "anything" );
      Assert.assertEquals( "44.0", calculator.add( "34", "10" ) );
      Assert.assertEquals( "0.0", calculator.getResult() );
   }// End Method
   
   /**
    * {@link Calculator#add(String)} acceptance test.
    */
   @Test public void shouldAddCumulatively(){
      Calculator calculator = new Calculator( "anything" );
      
      Assert.assertEquals( "34.0", calculator.add( "34" ) );
      Assert.assertEquals( "34.0", calculator.getResult() );
      
      Assert.assertEquals( "90.0", calculator.add( "56" ) );
      Assert.assertEquals( "90.0", calculator.getResult() );
      
      Assert.assertEquals( "824.7", calculator.add( "734.7" ) );
      Assert.assertEquals( "824.7", calculator.getResult() );
   }// End Method
   
   /**
    * {@link Calculator#subtract(String, String)} acceptance test.
    */
   @Test public void shouldSubtract(){
      Calculator calculator = new Calculator( "anything" );
      Assert.assertEquals( "24.0", calculator.subtract( "34", "10" ) );
      Assert.assertEquals( "0.0", calculator.getResult() );
   }// End Method
   
   /**
    * {@link Calculator#subtract(String)} acceptance test.
    */
   @Test public void shouldSubtractFromResult(){
      Calculator calculator = new Calculator( "anything" );
      
      Assert.assertEquals( "-34.0", calculator.subtract( "34" ) );
      Assert.assertEquals( "-34.0", calculator.getResult() );
      
      Assert.assertEquals( "-90.0", calculator.subtract( "56" ) );
      Assert.assertEquals( "-90.0", calculator.getResult() );
      
      Assert.assertEquals( "-824.7", calculator.subtract( "734.7" ) );
      Assert.assertEquals( "-824.7", calculator.getResult() );
   }// End Method
   
   /**
    * {@link Calculator#clear()} acceptance test.
    */
   @Test public void shouldClearToZero(){
      Calculator calculator = new Calculator( "anything" );
      
      calculator.add( "34" );
      Assert.assertEquals( "34.0", calculator.getResult() );
      
      Assert.assertEquals( "0.0", calculator.clear() );
      Assert.assertEquals( "0.0", calculator.getResult() );
   }// End Method
   
   /**
    * {@link Calculator#clear(String)} acceptance test.
    */
   @Test public void shouldClearToValue(){
      Calculator calculator = new Calculator( "anything" );
      
      calculator.add( "34" );
      Assert.assertEquals( "34.0", calculator.getResult() );
      
      Assert.assertEquals( "20.0", calculator.clear( "20.0" ) );
      Assert.assertEquals( "20.0", calculator.getResult() );
   }// End Method
   
   /**
    * {@link Calculator#multiply(String, String)} acceptance test.
    */
   @Test public void shouldMultiply(){
      Calculator calculator = new Calculator( "anything" );
      Assert.assertEquals( "340.0", calculator.multiply( "34", "10" ) );
      Assert.assertEquals( "0.0", calculator.getResult() );
   }// End Method
   
   /**
    * {@link Calculator#multiply(String)} acceptance test.
    */
   @Test public void shouldMultiplyResult(){
      Calculator calculator = new Calculator( "anything" );
      
      calculator.clear( "4.0" );
      
      Assert.assertEquals( "8.0", calculator.multiply( "2" ) );
      Assert.assertEquals( "8.0", calculator.getResult() );
      
      Assert.assertEquals( "16.0", calculator.multiply( "2" ) );
      Assert.assertEquals( "16.0", calculator.getResult() );
      
      Assert.assertEquals( "64.0", calculator.multiply( "4" ) );
      Assert.assertEquals( "64.0", calculator.getResult() );
   }// End Method

}// End Class
