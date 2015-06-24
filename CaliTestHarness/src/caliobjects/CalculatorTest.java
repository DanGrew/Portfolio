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
      calculator.add( "34", "10" );
      Assert.assertEquals( "44.0", calculator.getResult() );
   }// End Method
   
   /**
    * {@link Calculator#add(String)} acceptance test.
    */
   @Test public void shouldAddCumulatively(){
      Calculator calculator = new Calculator( "anything" );
      
      calculator.add( "34" );
      Assert.assertEquals( "34.0", calculator.getResult() );
      
      calculator.add( "56" );
      Assert.assertEquals( "90.0", calculator.getResult() );
      
      calculator.add( "734.7" );
      Assert.assertEquals( "824.7", calculator.getResult() );
   }// End Method

}// End Class
