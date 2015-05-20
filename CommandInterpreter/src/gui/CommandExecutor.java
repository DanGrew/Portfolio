/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.action.ExecuteAction;
import gui.console.ConsoleMessage;
import gui.console.ConsoleMessageImpl;
import gui.console.ConsoleModel;
import architecture.event.EventSystem;
import command.Command;
import command.CommandResult;

/**
 * The {@link CommandExecutor} is responsible for executing {@link Command}s in response to 
 * user input.
 */
public class CommandExecutor extends CommandMonitor{
   
   public static final ConsoleMessage INVALID_COMMAND = new ConsoleMessageImpl( 
            "Command not specified." );
   public static final ConsoleMessage INVALID_INPUT = new ConsoleMessageImpl( 
            "Input not valid for command." );
     
   /**
    * Constructs a {@link CommandExecutor}.
    */
   public CommandExecutor() {
      super();
      EventSystem.registerForEvent( 
               ExecuteAction.Events.ExecuteAction, 
               ( event, object ) -> execute()
      );
   }// End Constructor
   
   /**
    * Method to execute the current {@link Command} and input.
    */
   private void execute(){
      if ( currentCommand == null ) {
         EventSystem.raiseEvent( ConsoleModel.Events.NewMessage, INVALID_COMMAND );
      } else if ( currentInput == null || currentInput.length() == 0 || !currentCommand.completeMatches( currentInput ) ) {
         EventSystem.raiseEvent( ConsoleModel.Events.NewMessage, INVALID_INPUT );
      } else {
         CommandResult< ? > result = currentCommand.execute( currentInput );
         CommandExecutorResult executorResult = new CommandExecutorResult( currentCommand, currentInput, result );
         currentCommand.resetParameters();
         EventSystem.raiseEvent( ConsoleModel.Events.NewMessage, executorResult );
      }
   }// End Method
   
}// End Class
