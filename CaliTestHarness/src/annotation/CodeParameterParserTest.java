/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import org.junit.Assert;
import org.junit.Test;

import annotation.CodeParametersResult.Result;

/**
 * Test for the {@link CodeParameterParserTest}.
 */
public class CodeParameterParserTest {

   /**
    * Test for the {@link Result#EMPTY_NO_OPEN} result.
    */
   @Test public void shouldEmptyNoOpen() {
      CodeParametersResult result = CodeParameterParser.parseCodeParameters( "" );
      Assert.assertNotNull( result );
      Assert.assertEquals( "", result.getResultingExpression() );
      Assert.assertEquals( Result.EMPTY_NO_OPEN, result.getResult() );
      Assert.assertNull( result.getParameters() );
   }// End Method
   
   /**
    * Test for the {@link Result#DOES_NOT_OPEN} result.
    */
   @Test public void shouldDoesNotOpen() {
      CodeParametersResult result = CodeParameterParser.parseCodeParameters( "anything" );
      Assert.assertNotNull( result );
      Assert.assertEquals( "anything", result.getResultingExpression() );
      Assert.assertEquals( Result.DOES_NOT_OPEN, result.getResult() );
      Assert.assertNull( result.getParameters() );
   }// End Method
   
   /**
    * Test for the {@link Result#OPEN_NO_PARAMETERS} result.
    */
   @Test public void shouldOpenNoParameters() {
      CodeParametersResult result = CodeParameterParser.parseCodeParameters( "(   " );
      Assert.assertNotNull( result );
      Assert.assertEquals( "", result.getResultingExpression() );
      Assert.assertEquals( Result.OPEN_NO_PARAMETERS, result.getResult() );
      Assert.assertNull( result.getParameters() );
   }// End Method
   
   /**
    * Test for the {@link Result#PARAMETERS_NO_CLOSE} result.
    */
   @Test public void shouldParametersNoClose() {
      CodeParametersResult result = CodeParameterParser.parseCodeParameters( "( anything twice" );
      Assert.assertNotNull( result );
      Assert.assertEquals( "anything twice", result.getResultingExpression() );
      Assert.assertEquals( Result.PARAMETERS_NO_CLOSE, result.getResult() );
      Assert.assertNull( result.getParameters() );
   }// End Method
   
   /**
    * Test for the {@link Result#SUCCESS} result.
    */
   @Test public void shouldSuccess() {
      CodeParametersResult result = CodeParameterParser.parseCodeParameters( "( anything, twice )" );
      Assert.assertNotNull( result );
      Assert.assertEquals( "", result.getResultingExpression() );
      Assert.assertEquals( Result.SUCCESS, result.getResult() );
      Assert.assertArrayEquals( new String[]{ "anything", "twice" }, result.getParameters() );
   }// End Method

}// End Class
