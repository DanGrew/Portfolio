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
      Assert.assertTrue( command.matches( "invert " ) );
      Assert.assertTrue( command.matches( "invert t" ) );
      Assert.assertTrue( command.matches( "invert true" ) );
      Assert.assertFalse( command.matches( "invert truel" ) );
      Assert.assertTrue( command.matches( "invert fa" ) );
      Assert.assertTrue( command.matches( "invert false" ) );
      Assert.assertFalse( command.matches( "invert falser" ) );
      Assert.assertFalse( command.matches( "invert anything" ) );
      Assert.assertFalse( command.matches( "invert 1234" ) );
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
      Assert.assertTrue( command.matches( "BinaryOr true" ) );
      command.resetParameters();
      Assert.assertTrue( command.matches( "BinaryOr true false" ) );
      command.resetParameters();
      Assert.assertTrue( command.matches( "BinaryOr tr" ) );
      command.resetParameters();
      Assert.assertFalse( command.matches( "BinaryOr tr fa" ) );
      command.resetParameters();
      Assert.assertFalse( command.matches( "BinaryOr anything" ) );
      command.resetParameters();
      Assert.assertFalse( command.matches( "or anything else" ) );
      command.resetParameters();
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
      Assert.assertNull( command.execute( "or anything else" ) );
      command.resetParameters();
   }// End Method

}// End Class
