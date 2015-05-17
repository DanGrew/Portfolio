/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.console;

/**
 * The {@link ConsoleMessage} provides an interface for interacting with an object
 * the can be displayed in the {@link Console}.
 */
public interface ConsoleMessage {
   
   /**
    * Method to get the displayable {@link String}.
    * @return a {@link String} message.
    */
   public String getDisplayableMessage();

}// End Class
