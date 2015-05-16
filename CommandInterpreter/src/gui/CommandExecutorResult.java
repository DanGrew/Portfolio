/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import command.Command;

/**
 * The {@link CommandExecutorResult} is responsible for holding the result of the execution
 * of a {@link Command}.
 */
public class CommandExecutorResult {
   
   public static final CommandExecutorResult INVALID_COMMAND = new CommandExecutorResult( 
            "Command not specified." );
   public static final CommandExecutorResult INVALID_INPUT = new CommandExecutorResult( 
            "Input not valid for command." );

   private Object result;
   
   /**
    * Constructs a new {@link CommandExecutorResult}.
    * @param result the {@link Object} result.
    */
   public CommandExecutorResult( Object result ) {
      this.result = result;
   }// End Constructor
   
   /**
    * Getter for the {@link String} representation of the result.
    * @return a {@link String} respresentation.
    */
   public String getResultString(){
      return result.toString();
   }// End Method
}// End Class
