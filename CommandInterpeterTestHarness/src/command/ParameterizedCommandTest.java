/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import org.junit.Assert;
import org.junit.Test;

import defaults.CommonCommands;

/**
 * Test to prove that the {@link ParameterizedCommandImpl} functions correctly with specific
 * example functions.
 */
public class ParameterizedCommandTest {

   /**
    * Method to test that {@link CommonCommands#INVERT_BOOLEAN_COMMAND} accepts and matches
    * the correct input.
    */
   @Test public void invertParameterAcceptanceTest() {
      Command< Boolean > command = CommonCommands.INVERT_BOOLEAN_COMMAND;
      Assert.assertTrue( command.partialMatches( "invert " ) );
      Assert.assertTrue( command.partialMatches( "invert t" ) );
      Assert.assertTrue( command.partialMatches( "invert true" ) );
      Assert.assertFalse( command.partialMatches( "invert truel" ) );
      Assert.assertTrue( command.partialMatches( "invert fa" ) );
      Assert.assertTrue( command.partialMatches( "invert false" ) );
      Assert.assertFalse( command.partialMatches( "invert falser" ) );
      Assert.assertFalse( command.partialMatches( "invert anything" ) );
      Assert.assertFalse( command.partialMatches( "invert 1234" ) );
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#INVERT_BOOLEAN_COMMAND} executes the correct input
    * and provides the correct result.
    */
   @Test public void executeInvertTest(){
      Command< Boolean > command = CommonCommands.INVERT_BOOLEAN_COMMAND;
      Assert.assertFalse( command.execute( "invert true" ) );
      command.resetParameters();
      Assert.assertTrue( command.execute( "invert false" ) );
      command.resetParameters();
      
      Assert.assertNull( command.execute( "invert " ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invert t" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invert truel" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invert fa" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invert falser" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invert anything" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invert 1234" ) );
      command.resetParameters();
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#BINARY_OR_COMMAND} accepts and matches
    * the correct input.
    */
   @Test public void binaryOrParameterAcceptanceTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      Assert.assertTrue( command.partialMatches( "BinaryOr true" ) );
      Assert.assertTrue( command.partialMatches( "BinaryOr true false" ) );
      Assert.assertTrue( command.partialMatches( "BinaryOr tr" ) );
      Assert.assertFalse( command.partialMatches( "BinaryOr tr fa" ) );
      Assert.assertFalse( command.partialMatches( "BinaryOr anything" ) );
      Assert.assertFalse( command.partialMatches( "BinaryOr anything else" ) );
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#BINARY_OR_COMMAND} executes the correct data
    * and produces the correct result. 
    */
   @Test public void executeBinaryOrTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      Assert.assertTrue( command.execute( "BinaryOr true false" ) );
      command.resetParameters();
      Assert.assertTrue( command.execute( "BinaryOr true true" ) );
      command.resetParameters();
      Assert.assertFalse( command.execute( "BinaryOr false false" ) );
      command.resetParameters();
      Assert.assertTrue( command.execute( "BinaryOr false true" ) );
      command.resetParameters();
      
      Assert.assertNull( command.execute( "BinaryOr true" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "BinaryOr tr" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "BinaryOr tr fa" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "BinaryOr anything" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "BinaryOr anything else" ) );
      command.resetParameters();
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#INVERT_STRING_CASE_COMMAND} accepts and matches
    * the correct input.
    */
   @Test public void invertStringParameterAcceptanceTest() {
      Command< String > command = CommonCommands.INVERT_STRING_CASE_COMMAND;
      Assert.assertTrue( command.partialMatches( "invertstring test" ) );
      Assert.assertTrue( command.partialMatches( "invertstring " ) );
      Assert.assertFalse( command.partialMatches( "invertstring tr fa" ) );
      Assert.assertFalse( command.partialMatches( "invertstring anything else" ) );
   }// End Method
   
   /**
    * Method to test that {@link CommonCommands#INVERT_STRING_CASE_COMMAND} executes the correct data
    * and produces the correct result. 
    */
   @Test public void executeInvertStringTest() {
      Command< String > command = CommonCommands.INVERT_STRING_CASE_COMMAND;
      Assert.assertEquals( "TEST", command.execute( "invertstring test" ) );
      command.resetParameters();
      Assert.assertEquals( "test", command.execute( "invertstring TEST" ) );
      command.resetParameters();
      Assert.assertEquals( "1234", command.execute( "invertstring 1234" ) );
      command.resetParameters();
      Assert.assertEquals( "TEST", command.execute( "invertstring tEST" ) );
      command.resetParameters();
      Assert.assertEquals( "test", command.execute( "invertstring Test" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invertstring " ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invertstring tr fa" ) );
      command.resetParameters();
      Assert.assertNull( command.execute( "invertstring anything else" ) );
      command.resetParameters();
   }// End Method

}// End Class
