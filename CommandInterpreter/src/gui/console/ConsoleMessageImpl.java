/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.console;

/**
 * Implementation of the {@link ConsoleMessage}.
 */
public class ConsoleMessageImpl implements ConsoleMessage {

   private String message;
   
   /**
    * Constructs a new {@link ConsoleMessageImpl}.
    * @param message the {@link String} message to the {@link Console}.
    */
   public ConsoleMessageImpl( String message ) {
      this.message = message;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getDisplayableMessage() {
      return message;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      return getDisplayableMessage();
   }// End Method

}// End Class
