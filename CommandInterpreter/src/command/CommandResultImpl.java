/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import gui.console.ConsoleMessage;
import gui.console.ConsoleMessageImpl;

/**
 * Implementation of the {@link CommandResult}.
 */
public class CommandResultImpl< ReturnT > implements CommandResult< ReturnT >{

   private static final String SUCCESS = "Success";
   private ConsoleMessage message;
   private ReturnT result;
   
   /**
    * Constructs a new {@link CommandResultImpl}.
    * @param message the {@link ConsoleMessage} describing the result.
    * @param result the result of the {@link Command}.
    */
   public CommandResultImpl( ConsoleMessage message, ReturnT result ) {
      this.message = message;
      this.result = result;
   }// End Constructor
   
   /**
    * Constructs a new {@link CommandResultImpl}.
    * @param message the {@link ConsoleMessage} describing the result which is assumed null.
    */
   public CommandResultImpl( ConsoleMessage message ) {
      this( message , null );
   }// End Constructor
   
   /**
    * Constructs a new {@link CommandResultImpl}.
    * @param message the {@link String} message to convert into a {@link ConsoleMessageImpl}.
    * @param result the result of the {@link Command}.
    */
   public CommandResultImpl( String message, ReturnT result ) {
      this( new ConsoleMessageImpl( message ), result );
   }// End Constructor
   
   /**
    * Constructs a new {@link CommandResultImpl}.
    * @param result the result of the {@link Command}, assuming the {@link ConsoleMessage} to
    * be successful providing a default message.
    */
   public CommandResultImpl( ReturnT result ) {
      this( new ConsoleMessageImpl( SUCCESS ), result );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public ConsoleMessage getConsoleMessage() {
      return message;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public ReturnT getResult() {
      return result;
   }// End Method

}// End Class
