/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.action.AutoCompleteAction;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.CommandParameter;
import architecture.event.EventSystem;
import command.Command;
import defaults.CommonCommands;

/**
 * Test for the {@link CommandAutoComplete} function.
 */
public class CommandAutoCompleteTest {

   private static List< Object > results = new ArrayList<>();
   private static CommandAutoComplete autoComplete = new CommandAutoComplete();
   
   /**
    * Method to initialise the registration for the appropriate event in the tests.
    */
   @BeforeClass public static void registration(){
      EventSystem.registerForEvent( CommandAutoComplete.Events.AutoCompletSuggestion, ( event, object ) ->
         results.add( object )
      );
   }// End Method
   
   /**
    * Method to reset the collected data between tests.
    */
   @Before public void reset(){
      results.clear();
      autoComplete.reset();
   }// End Method
   
   /**
    * Method to test that the key to a {@link Command} is auto completed correctly.
    */
   @Test public void keyCompletionTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      EventSystem.raiseEvent( Suggestions.Events.CommandSelected, command );
      EventSystem.raiseEvent( CommandInput.Events.TextInput, "bin" );
      EventSystem.raiseEvent( AutoCompleteAction.Events.AutoComplete, null );
      
      Assert.assertEquals( 1, results.size() );
      Assert.assertEquals( "BinaryOr", results.get( 0 ) );
   }// End Method
   
   /**
    * Method to test that the first {@link CommandParameter} can be auto completed correctly.
    */
   @Test public void firstParameterCompletionTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      EventSystem.raiseEvent( Suggestions.Events.CommandSelected, command );
      EventSystem.raiseEvent( CommandInput.Events.TextInput, "binaryor t" );
      EventSystem.raiseEvent( AutoCompleteAction.Events.AutoComplete, null );
      
      Assert.assertEquals( 1, results.size() );
      Assert.assertEquals( "binaryor true", results.get( 0 ) );
   }// End Method
   
   /**
    * Method to test that the second {@link CommandParameter} can be auto completed correctly.
    */
   @Test public void secondParameterCompletionTest() {
      Command< Boolean > command = CommonCommands.BINARY_OR_COMMAND;
      EventSystem.raiseEvent( Suggestions.Events.CommandSelected, command );
      EventSystem.raiseEvent( CommandInput.Events.TextInput, "binaryor true fa" );
      EventSystem.raiseEvent( AutoCompleteAction.Events.AutoComplete, null );
      
      Assert.assertEquals( 1, results.size() );
      Assert.assertEquals( "binaryor true false", results.get( 0 ) );
   }// End Method
   
   /**
    * Method to test the result when no suggestion is provided.
    */
   @Test public void noSuggestionTest() {
      Command< String > command = CommonCommands.INVERT_STRING_CASE_COMMAND;
      EventSystem.raiseEvent( Suggestions.Events.CommandSelected, command );
      EventSystem.raiseEvent( CommandInput.Events.TextInput, "invertString test" );
      EventSystem.raiseEvent( AutoCompleteAction.Events.AutoComplete, null );
      
      Assert.assertEquals( 1, results.size() );
      Assert.assertEquals( "invertString test", results.get( 0 ) );
   }// End Method

}// End Class
