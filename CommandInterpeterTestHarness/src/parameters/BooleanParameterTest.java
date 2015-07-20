/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parameter.BooleanParameterImpl;
import parameter.CommandParameter;

/**
 * Test for the {@link BooleanParameterImpl}.
 */
public class BooleanParameterTest {
   
   private CommandParameter parameter;
   
   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      parameter = new BooleanParameterImpl();
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( "Tr" ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
      Assert.assertTrue( parameter.partialMatches( "True antything" ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "hi" ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( "True" ) );
      Assert.assertTrue( parameter.completeMatches( "true" ) );
      Assert.assertTrue( parameter.completeMatches( "true anything" ) );
      Assert.assertTrue( parameter.completeMatches( "True" ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "" ) );
      Assert.assertFalse( parameter.completeMatches( " " ) );
      Assert.assertFalse( parameter.completeMatches( "alse" ) );
      Assert.assertFalse( parameter.completeMatches( "alse anything" ) );
      Assert.assertFalse( parameter.completeMatches( "Fjalse" ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      final String testRemainder = "anything";
      Assert.assertEquals( testRemainder, parameter.extractInput( "TRUE " + testRemainder ) );
      Assert.assertEquals( "", parameter.extractInput( "TRUE " ) );
      Assert.assertEquals( "", parameter.extractInput( "TRUE" ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#parseObject(String)} acceptance test.
    */
   @Test public void shouldParse() {
      Assert.assertEquals( true, parameter.parseObject( "TRUE" ) );
      Assert.assertEquals( true, parameter.parseObject( "TRUE   " ) );
      Assert.assertEquals( true, parameter.parseObject( "   TRUE" ) );
      Assert.assertEquals( true, parameter.parseObject( "TRUE something else" ) );
      
      Assert.assertEquals( false, parameter.parseObject( "FALSE" ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      Assert.assertEquals( "true", parameter.autoComplete( "TR" ) );
      Assert.assertEquals( "false", parameter.autoComplete( "FA" ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( " " ) );
   }// End Method
   
   /**
    * {@link BooleanParameterImpl#getSuggestions(String)} test.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( Arrays.asList( "true", "false" ), parameter.getSuggestions( "" ) );
      Assert.assertEquals( Arrays.asList( "true" ), parameter.getSuggestions( "TR" ) );
      Assert.assertEquals( Arrays.asList( "true" ), parameter.getSuggestions( "tr" ) );
      Assert.assertEquals( Arrays.asList( "true" ), parameter.getSuggestions( "TRUE" ) );
      Assert.assertEquals( Arrays.asList(), parameter.getSuggestions( "TRUET" ) );
      Assert.assertEquals( Arrays.asList( "false" ), parameter.getSuggestions( "FA" ) );
      Assert.assertEquals( Arrays.asList( "false" ), parameter.getSuggestions( "fal" ) );
      Assert.assertEquals( Arrays.asList(), parameter.getSuggestions( "anything" ) );
   }// End Method
}// End Class
