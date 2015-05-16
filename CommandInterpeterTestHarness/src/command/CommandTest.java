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
import parameter.CommandParameterImpl;
import parameter.CommandParameters;

public class CommandTest {

   @Test public void basicInstructionCommand() {
      Command< Boolean > command = new InstructionCommandImpl< Boolean >( 
               "Test", 
               ( CommandParameters params ) -> { 
                  return true;
               } 
      );
      Assert.assertTrue( command.matches( "   Test" ) );
      Assert.assertTrue( command.matches( "Test" ) );
      Assert.assertFalse( command.matches( "Something Else" ) );
      Assert.assertTrue( command.execute() );
   }
   
   @Test public void basicParameterizedCommand() {
      CommandParameter parameter = new CommandParameterImpl();
      Command< Boolean > command = new ParameterizedCommandImpl< Boolean >(
               "Invert",
               ( CommandParameters params ) -> {
                  return !params.getExpectedParameter( parameter, Boolean.class );
               },
               parameter
      );
      Assert.assertTrue( command.matches( "   Invert" ) );
      Assert.assertTrue( command.matches( "Invert" ) );
      Assert.assertFalse( command.matches( "Something Else" ) );
      command.parameterize( parameter, false );
      Assert.assertTrue( command.execute() );
      command.parameterize( parameter, true );
      Assert.assertFalse( command.execute() );
   }

}
