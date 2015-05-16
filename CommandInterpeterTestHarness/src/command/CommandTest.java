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
      Assert.assertTrue( command.matches( "   Test" ) );
      Assert.assertTrue( command.matches( "   Te" ) );
      Assert.assertTrue( command.matches( "Test" ) );
      Assert.assertTrue( command.matches( "Te" ) );
      Assert.assertFalse( command.matches( "Something Else" ) );
      Assert.assertTrue( command.matches( "Test something" ) );
      Assert.assertTrue( command.execute() );
   }// End Method
   
   /**
    * Method to test a basic {@link ParameterizedCommandImpl}.
    */
   @Test public void basicParameterizedCommand() {
      CommandParameter parameter = CommonCommands.DEFAULT_PARAMETER;
      Command< Boolean > command = CommonCommands.INVERT_BOOLEAN_COMMAND;
      Assert.assertTrue( command.matches( "   " ) );
      Assert.assertTrue( command.matches( "   Invert" ) );
      Assert.assertTrue( command.matches( "   In" ) );
      Assert.assertTrue( command.matches( "Invert" ) );
      Assert.assertTrue( command.matches( "In" ) );
      Assert.assertFalse( command.matches( "Something Else" ) );
      Assert.assertTrue( command.matches( "Invert something" ) );
      command.parameterize( parameter, false );
      Assert.assertTrue( command.execute() );
      command.parameterize( parameter, true );
      Assert.assertFalse( command.execute() );
   }// End Method

}// End Class
