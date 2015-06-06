/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parameter.CommandParameter;
import parameter.NumberParameterImpl;

/**
 * Test for the {@link NumberParameterImpl}.
 */
public class NumberParameterTest {
   
   private CommandParameter parameter;
   
   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      parameter = new NumberParameterImpl();
   }// End Method
   
   /**
    * {@link NumberParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( "123" ) );
      Assert.assertTrue( parameter.partialMatches( "78.984" ) );
   }// End Method
   
   /**
    * {@link NumberParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
   }// End Method
   
   /**
    * {@link NumberParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( "1234" ) );
      Assert.assertTrue( parameter.completeMatches( "78.4937" ) );
   }// End Method
   
   /**
    * {@link NumberParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "" ) );
      Assert.assertFalse( parameter.completeMatches( " " ) );
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
   }// End Method
   
   /**
    * {@link NumberParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      final String testRemainder = "anything";
      Assert.assertEquals( testRemainder, parameter.extractInput( "1234 " + testRemainder ) );
      Assert.assertEquals( "", parameter.extractInput( "1234 " ) );
      Assert.assertEquals( "", parameter.extractInput( "1234" ) );
   }// End Method
   
   /**
    * {@link NumberParameterImpl#parseObject(String)} acceptance test.
    */
   @Test public void shouldParse() {
      Assert.assertEquals( 1234.0, parameter.parseObject( "1234" ) );
      Assert.assertEquals( 34.567, parameter.parseObject( "34.567   " ) );
      Assert.assertEquals( 0.45, parameter.parseObject( "   0.45" ) );
      Assert.assertEquals( 56.0, parameter.parseObject( "56 something else" ) );
   }// End Method
   
   /**
    * {@link NumberParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
   }// End Method
   
   /**
    * {@link NumberParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      //Auto completes nothing.
   }// End Method
   
   /**
    * {@link NumberParameterImpl#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( " " ) );
   }// End Method
}// End Class
