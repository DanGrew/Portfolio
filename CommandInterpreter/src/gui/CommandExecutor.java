/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.action.ExecuteAction;

import javax.swing.JOptionPane;

import command.Command;

import architecture.event.EventSystem;

/**
 * The {@link CommandExecutor} is responsible for executing {@link Command}s in response to 
 * user input.
 */
public class CommandExecutor extends CommandMonitor{
   
   public enum Events {
      /** Event fired when a {@link Command} has been executed.**/
      CommandExecuted;
   }// End Enum
   
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
         EventSystem.raiseEvent( Events.CommandExecuted, CommandExecutorResult.INVALID_COMMAND );
         JOptionPane.showMessageDialog( null, CommandExecutorResult.INVALID_COMMAND.getResultString() );
      } else if ( currentInput == null || currentInput.length() == 0 || !currentCommand.completeMatches( currentInput ) ) {
         EventSystem.raiseEvent( Events.CommandExecuted, CommandExecutorResult.INVALID_INPUT );
         JOptionPane.showMessageDialog( null, CommandExecutorResult.INVALID_INPUT.getResultString() );
      } else {
         Object result = currentCommand.execute( currentInput );
         currentCommand.resetParameters();
         if ( result == null ) {
            toString();
         }
         CommandExecutorResult executorResult = new CommandExecutorResult( result );
         EventSystem.raiseEvent( Events.CommandExecuted, executorResult );
         
         JOptionPane.showMessageDialog( null, executorResult.getResultString() );
      }
   }// End Method
   
}// End Class
