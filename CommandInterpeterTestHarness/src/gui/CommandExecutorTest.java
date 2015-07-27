/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.console.ConsoleMessage;
import gui.console.ConsoleModel;
import gui.function.GuiFunctions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.event.EventSystem;

import command.Command;

import defaults.CommonCommands;

/**
 * Test for the {@link CommandExecutor} function.
 */
public class CommandExecutorTest {

   private static List< Object > results = new ArrayList<>();
   private static CommandExecutor executor = new CommandExecutor();
   
   /**
    * Method to initialise the registration for the test.
    */
   @BeforeClass public static void registration(){
      EventSystem.registerForEvent( ConsoleModel.Events.NewMessage, ( event, object ) ->
         results.add( object )
      );
   }// End Method
   
   /**
    * Method to reset the collected data between tests.
    */
   @Before public void reset(){
      results.clear();
      executor.reset();
   }// End Method
   
   /**
    * Method to test the basic exectuion of a {@link Command}.
    */
   @Test public void basicExecutionTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      EventSystem.raiseEvent( Suggestions.Events.CommandSelected, command );
      EventSystem.raiseEvent( CommandInput.Events.TextInput, "binaryor true true" );
      EventSystem.raiseEvent( GuiFunctions.Events.ExecuteAction, null );
      
      Assert.assertEquals( 1, results.size() );
      ConsoleMessage result = ( ConsoleMessage )results.get( 0 );
      Assert.assertNotNull( result );
      Assert.assertNotNull( result.getDisplayableMessage() );
   }// End Method
   
   /**
    * Method to test the invalid result when input is not sufficient.
    */
   @Test public void invalidInputTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      EventSystem.raiseEvent( Suggestions.Events.CommandSelected, command );
      EventSystem.raiseEvent( GuiFunctions.Events.ExecuteAction, null );
      
      Assert.assertEquals( 1, results.size() );
      ConsoleMessage result = ( ConsoleMessage )results.get( 0 );
      Assert.assertEquals( CommandExecutor.INVALID_INPUT.getDisplayableMessage(), result.getDisplayableMessage() );
   }// End Method
   
   /**
    * Method to test the invalid result when the {@link Command} is not specified.
    */
   @Test public void invalidCommandTest() {
      EventSystem.raiseEvent( CommandInput.Events.TextInput, "binaryor true true" );
      EventSystem.raiseEvent( GuiFunctions.Events.ExecuteAction, null );
      
      Assert.assertEquals( 1, results.size() );
      ConsoleMessage result = ( ConsoleMessage )results.get( 0 );
      Assert.assertEquals( CommandExecutor.INVALID_COMMAND.getDisplayableMessage(), result.getDisplayableMessage() );
   }// End Method

}// End Class
