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

import parameter.ClassParameterImpl;
import parameter.CommandParameter;
import parameter.classparameter.ClassParameterTypes;

/**
 * Test for the {@link ClassParameterImpl}.
 */
public class ClassParameterTest {
   
   private CommandParameter parameter;
   
   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      parameter = new ClassParameterImpl();
   }// End Method
   
   /**
    * {@link ClassParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( "St" ) );
      Assert.assertTrue( parameter.partialMatches( "Num" ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( "String" ) );
      Assert.assertTrue( parameter.completeMatches( "Number" ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      final String testRemainder = "anything";
      Assert.assertEquals( testRemainder, parameter.extractInput( "String " + testRemainder ) );
      Assert.assertEquals( "", parameter.extractInput( "String " ) );
      Assert.assertEquals( "", parameter.extractInput( "String" ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#parseObject(String)} acceptance test.
    */
   @Test public void shouldParse() {
      Assert.assertEquals( ClassParameterTypes.STRING_PARAMETER_TYPE, parameter.parseObject( "String" ) );
      Assert.assertEquals( ClassParameterTypes.STRING_PARAMETER_TYPE, parameter.parseObject( "String   " ) );
      Assert.assertEquals( ClassParameterTypes.STRING_PARAMETER_TYPE, parameter.parseObject( "   String" ) );
      Assert.assertEquals( ClassParameterTypes.STRING_PARAMETER_TYPE, parameter.parseObject( "String something else" ) );
      
      Assert.assertEquals( ClassParameterTypes.NUMBER_PARAMETER_TYPE, parameter.parseObject( "Number" ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      Assert.assertEquals( "String", parameter.autoComplete( "Str" ) );
      Assert.assertEquals( "Number", parameter.autoComplete( "Num" ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( " " ) );
   }// End Method
   
   /**
    * {@link ClassParameterImpl#getSuggestions(String)} test.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( 
               Arrays.asList( ClassParameterTypes.STRING_PARAMETER_TYPE.getName(), ClassParameterTypes.NUMBER_PARAMETER_TYPE.getName() ), 
               parameter.getSuggestions( "" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( ClassParameterTypes.STRING_PARAMETER_TYPE.getName() ), 
               parameter.getSuggestions( "STR" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( ClassParameterTypes.STRING_PARAMETER_TYPE.getName() ), 
               parameter.getSuggestions( "strin" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( ClassParameterTypes.STRING_PARAMETER_TYPE.getName() ), 
               parameter.getSuggestions( "String" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( ClassParameterTypes.NUMBER_PARAMETER_TYPE.getName() ), 
               parameter.getSuggestions( "Num" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( ClassParameterTypes.NUMBER_PARAMETER_TYPE.getName() ), 
               parameter.getSuggestions( "NUM" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( ClassParameterTypes.NUMBER_PARAMETER_TYPE.getName() ), 
               parameter.getSuggestions( "numbe" ) 
      );
      Assert.assertEquals( 
               Arrays.asList(), 
               parameter.getSuggestions( "anything" ) 
      );
   }// End Method
}// End Class
