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
 * Test for {@link Command} functions.
 */
public class CommandTest {

   /**
    * Method to test a basic {@link InstructionCommandImpl}.
    */
   @Test public void basicInstructionCommand() {
      Command< Boolean > command = CommonCommands.TRUE_COMMAND;
      Assert.assertTrue( command.partialMatches( "   " ) );
      Assert.assertTrue( command.partialMatches( "   True" ) );
      Assert.assertTrue( command.partialMatches( "   Tr" ) );
      Assert.assertTrue( command.partialMatches( "True" ) );
      Assert.assertTrue( command.partialMatches( "true" ) );
      Assert.assertTrue( command.partialMatches( "Tr" ) );
      Assert.assertFalse( command.partialMatches( "Something Else" ) );
      Assert.assertTrue( command.partialMatches( "True something" ) );
      Assert.assertTrue( command.execute().getResult() );
   }// End Method
   
   /**
    * Method to test a basic {@link ParameterizedCommandImpl}.
    */
   @Test public void basicParameterizedCommand() {
      Command< Boolean > command = CommonCommands.INVERT_BOOLEAN_COMMAND;
      Assert.assertTrue( command.partialMatches( "   " ) );
      Assert.assertTrue( command.partialMatches( "   Invert" ) );
      Assert.assertTrue( command.partialMatches( "   In" ) );
      Assert.assertTrue( command.partialMatches( "Invert" ) );
      Assert.assertTrue( command.partialMatches( "In" ) );
      Assert.assertFalse( command.partialMatches( "Something Else" ) );
      Assert.assertFalse( command.partialMatches( "Invert something" ) );
   }// End Method

}// End Class
