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

import parameter.CommandParameter;
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
      Assert.assertTrue( command.matches( "   " ) );
      Assert.assertTrue( command.matches( "   True" ) );
      Assert.assertTrue( command.matches( "   Tr" ) );
      Assert.assertTrue( command.matches( "True" ) );
      Assert.assertTrue( command.matches( "true" ) );
      Assert.assertTrue( command.matches( "Tr" ) );
      Assert.assertFalse( command.matches( "Something Else" ) );
      Assert.assertTrue( command.matches( "True something" ) );
      Assert.assertTrue( command.execute() );
   }// End Method
   
   /**
    * Method to test a basic {@link ParameterizedCommandImpl}.
    */
   @Test public void basicParameterizedCommand() {
      Command< Boolean > command = CommonCommands.INVERT_BOOLEAN_COMMAND;
      Assert.assertTrue( command.matches( "   " ) );
      Assert.assertTrue( command.matches( "   Invert" ) );
      Assert.assertTrue( command.matches( "   In" ) );
      Assert.assertTrue( command.matches( "Invert" ) );
      Assert.assertTrue( command.matches( "In" ) );
      Assert.assertFalse( command.matches( "Something Else" ) );
      Assert.assertFalse( command.matches( "Invert something" ) );
   }// End Method

}// End Class
