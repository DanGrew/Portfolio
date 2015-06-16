/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.console.ConsoleMessage;

import command.Command;
import command.CommandResult;

/**
 * The {@link CommandExecutorResult} is responsible for holding the result of the execution
 * of a {@link Command}.
 */
public class CommandExecutorResult implements ConsoleMessage {
   
   private Command< ? > command;
   private String input;
   private CommandResult< ? > result;
   
   /**
    * Constructs a new {@link CommandExecutorResult}.
    * @param command the {@link Command} executed.
    * @param input the {@link String} input.
    * @param result the {@link CommandResult} from the execution.
    */
   public CommandExecutorResult( Command< ? > command, String input, CommandResult< ? > result ) {
      this.command = command;
      this.input = input;
      this.result = result;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getDisplayableMessage(){
      return "Execution Result: " + 
               result.getConsoleMessage().getDisplayableMessage() + 
                  " [" + command.getDescription() + " with \"" + input + "\"]";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      return getDisplayableMessage();
   }// End Method
}// End Class
