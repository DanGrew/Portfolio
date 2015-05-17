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

/**
 * The {@link CommandExecutorResult} is responsible for holding the result of the execution
 * of a {@link Command}.
 */
public class CommandExecutorResult implements ConsoleMessage {
   
   private Command< ? > command;
   private String input;
   private Object result;
   
   /**
    * Constructs a new {@link CommandExecutorResult}.
    * @param command the {@link Command} executed.
    * @param input the {@link String} input.
    * @param result the {@link Object} result.
    */
   public CommandExecutorResult( Command< ? > command, String input, Object result ) {
      this.command = command;
      this.input = input;
      this.result = result;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getDisplayableMessage(){
      return "Execution Result: " + result.toString() + " [" + command.getKey() + " with \"" + input + "\"]" ;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      return getDisplayableMessage();
   }// End Method
}// End Class
