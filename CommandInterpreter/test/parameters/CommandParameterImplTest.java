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

import parameter.CommandParameter;
import parameter.CommandParameterImpl;

/**
 * Test for the {@link CommandParameterImpl}.
 */
public class CommandParameterImplTest {
   
   private CommandParameter parameter;
   
   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      parameter = new CommandParameterImpl();
   }// End Method
   
   /**
    * {@link CommandParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( "anything" ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
      Assert.assertTrue( parameter.partialMatches( "antything twice" ) );
   }// End Method
   
   /**
    * {@link CommandParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      //Matches all.
   }// End Method
   
   /**
    * {@link CommandParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( "anything" ) );
      Assert.assertTrue( parameter.completeMatches( "anything twice" ) );
   }// End Method
   
   /**
    * {@link CommandParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      //Matches all.
   }// End Method
   
   /**
    * {@link CommandParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      final String testRemainder = "anything";
      Assert.assertEquals( testRemainder, parameter.extractInput( "anything " + testRemainder ) );
      Assert.assertEquals( "", parameter.extractInput( "anything " ) );
      Assert.assertEquals( "", parameter.extractInput( "anything" ) );
   }// End Method
   
   /**
    * {@link CommandParameterImpl#parseObject(String)} acceptance test.
    */
   @Test public void shouldParse() {
      Assert.assertEquals( "anything", parameter.parseObject( "anything" ) );
      Assert.assertEquals( "anything", parameter.parseObject( "anything   " ) );
      Assert.assertEquals( "anything", parameter.parseObject( "   anything" ) );
      Assert.assertEquals( "anything", parameter.parseObject( "anything something else" ) );
   }// End Method
   
   /**
    * {@link CommandParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      //Parses all.
   }// End Method
   
   /**
    * {@link CommandParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      //Auto completes nothing.
   }// End Method
   
   /**
    * {@link CommandParameterImpl#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "anything" ) );
   }// End Method
   
   /**
    * {@link CommandParameterImpl#getSuggestions(String)} test.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( Arrays.asList(), parameter.getSuggestions( "anything" ) );
   }// End Method
}// End Class
