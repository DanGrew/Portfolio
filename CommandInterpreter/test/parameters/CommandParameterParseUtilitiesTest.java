/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import org.junit.Assert;
import org.junit.Test;

import parameter.CommandParameterParseUtilities;

/**
 * Test for the {@link CommandParameterParseUtilitiesTest}.
 */
public class CommandParameterParseUtilitiesTest {

   /**
    * Method to test that the correct parameters are parsed from input.
    */
   @Test public void shouldParseParameters() {
      testParametersFound( 1, "" );
      testParametersFound( 1, "Test" );
      testParametersFound( 1, "Test", " " );
      testParametersFound( 1, "Test", "anything" );
      testParametersFound( 2, "Test", "Something" );
      testParametersFound( 10, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" );
      testParametersFound( 10, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "antyhing", "else", "defined" );
   }// End Method
   
   /**
    * Method to test that incorrect input will not parse parameters.
    */
   @Test public void shouldNotParseParameters() {
      testParametersNotFound( 2, "First" );
      testParametersNotFound( 5, "Not", "enough" );
   }// End Method
   
   /**
    * Method to test that white space is ignored in the input.
    */
   @Test public void shouldIgnoreWhiteSpace(){
      String[] result = CommandParameterParseUtilities.parseParameters( 
               CommandParameterParseUtilities.delimiter(), 
               5, 
               "something     with          lots of space"
      );
      Assert.assertNotNull( result );
      Assert.assertEquals( 5, result.length );
      Assert.assertEquals( "something", result[ 0 ] );
      Assert.assertEquals( "with", result[ 1 ] );
      Assert.assertEquals( "lots", result[ 2 ] );
      Assert.assertEquals( "of", result[ 3 ] );
      Assert.assertEquals( "space", result[ 4 ] );
   }// End Method
   
   /**
    * Method to test that leading white space is ignored in the input.
    */
   @Test public void shouldIgnoreLeadingWhiteSpace(){
      String[] result = CommandParameterParseUtilities.parseParameters( 
               CommandParameterParseUtilities.delimiter(), 
               1, 
               "     something"
      );
      Assert.assertNotNull( result );
      Assert.assertEquals( 1, result.length );
      Assert.assertEquals( "something", result[ 0 ] );
   }// End Method
   
   /**
    * Method to test that an expression is reduced based on the identified parameters.
    */
   @Test public void shouldReduceExpression() {
      Assert.assertEquals( "this left", CommandParameterParseUtilities.reduce( "only this left", "only" ) );
      Assert.assertEquals( "this    left", CommandParameterParseUtilities.reduce( "   only     this    left", "only" ) );
      Assert.assertEquals( "left", CommandParameterParseUtilities.reduce( "only this left", "only", "this" ) );
      Assert.assertEquals( "this left", CommandParameterParseUtilities.reduce( "onlythis left", "only" ) );
   }// End Method
   
   /**
    * Method to test that the parameters available should be identified even when not all are available.
    */
   @Test public void shouldIdentifyParametersWhenNotAllProvided(){
      String[] result = CommandParameterParseUtilities.parseParameters( 
               CommandParameterParseUtilities.delimiter(), 
               4, 
               "not enough parameters"
      );
      Assert.assertNotNull( result );
      Assert.assertEquals( 4, result.length );
      Assert.assertEquals( "not", result[ 0 ] );
      Assert.assertEquals( "enough", result[ 1 ] );
      Assert.assertEquals( "parameters", result[ 2 ] );
      Assert.assertEquals( "", result[ 3 ] );
   }// End Method
   
   /**
    * {@link CommandParameterParseUtilities#parseUpTo(String, String, String)} acceptance test.
    */
   @Test public void shouldParseUpTo(){
      String[] result = CommandParameterParseUtilities.parseUpTo( 
               "anything stuff filled here )",
               "\\)",
               CommandParameterParseUtilities.delimiter()
      );
      Assert.assertEquals( "anything", result[ 0 ] );
      Assert.assertEquals( "stuff", result[ 1 ] );
      Assert.assertEquals( "filled", result[ 2 ] );
      Assert.assertEquals( "here", result[ 3 ] );
      
      result = CommandParameterParseUtilities.parseUpTo( 
               "anything stuff fi)lled here )",
               "\\)",
               CommandParameterParseUtilities.delimiter()
      );
      Assert.assertEquals( "anything", result[ 0 ] );
      Assert.assertEquals( "stuff", result[ 1 ] );
      Assert.assertEquals( "fi", result[ 2 ] );
      
      result = CommandParameterParseUtilities.parseUpTo( 
               "anything stuff filled here ",
               "\\)",
               CommandParameterParseUtilities.delimiter()
      );
      Assert.assertEquals( "anything", result[ 0 ] );
      Assert.assertEquals( "stuff", result[ 1 ] );
      Assert.assertEquals( "filled", result[ 2 ] );
      Assert.assertEquals( "here", result[ 3 ] );
      
      result = CommandParameterParseUtilities.parseUpTo( 
               ".",
               "anything",
               "."
      );
      Assert.assertEquals( 0, result.length );
   }// End Method
   
   /**
    * Method to test that the parameters parsed match the input.
    * @param numberOfParameters the number of parameters to parse.
    * @param parameters the parameters in the input.
    */
   private void testParametersFound( int numberOfParameters, String... parameters ) {
      String[] result = CommandParameterParseUtilities.parseParameters( 
               CommandParameterParseUtilities.delimiter(), 
               numberOfParameters, 
               String.join( CommandParameterParseUtilities.delimiter(), parameters ) 
      );
      Assert.assertNotNull( result );
      Assert.assertEquals( numberOfParameters, result.length );
      for ( int i = 0; i < numberOfParameters; i++ ) {
         Assert.assertEquals( parameters[ i ], result[ i ] );
      }
   }// End Method
   
   /**
    * Method to test that the parameters are not parsed and the result is appropriate.
    * @param numberOfParameters the number of parameters to parse.
    * @param parameters the parameters in the input.
    */
   private void testParametersNotFound( int numberOfParameters, String... parameters ) {
      String[] result = CommandParameterParseUtilities.parseParameters( 
               CommandParameterParseUtilities.delimiter(), 
               numberOfParameters, 
               String.join( CommandParameterParseUtilities.delimiter(), parameters ) 
      );
      Assert.assertNotNull( result );
      Assert.assertEquals( numberOfParameters, result.length );
      
      int count = 0;
      for ( ; count < parameters.length; count++ ) {
         Assert.assertEquals( parameters[ count ], result[ count ] );
      }
      for ( ; count < numberOfParameters; count ++ ) {
         Assert.assertEquals( "", result[ count ] );
      }
   }// End Method

}// End Class
