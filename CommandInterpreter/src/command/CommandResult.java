/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import gui.console.ConsoleMessage;

/**
 * The {@link CommandResult} represents the output of a {@link Command} execution.
 * @param <ReturnT> the return type of the {@link Command}.
 */
public interface CommandResult< ReturnT > {
   
   /**
    * Getter for the {@link ConsoleMessage} describing the output.
    * @return the {@link ConsoleMessage}.
    */
   public ConsoleMessage getConsoleMessage();
   
   /**
    * Getter for the result of the {@link Command}, if defined, of the correct type.
    * @return the result of the {@link Command}.
    */
   public ReturnT getResult();

}// End Interface
