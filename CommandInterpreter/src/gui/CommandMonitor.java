/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import architecture.event.EventSystem;

import command.Command;

/**
 * The {@link CommandMonitor} provides a basic object to monitor the input and current {@link Command}.
 */
public class CommandMonitor {

   protected String currentInput;
   protected Command< ? > currentCommand;
   
   /**
    * Constructs a {@link CommandMonitor} and registers for appropriate events.
    */
   public CommandMonitor() {
      EventSystem.registerForEvent( 
               CommandInput.Events.TextInput, 
               ( event, object ) -> currentInput = ( String )object
      );
      EventSystem.registerForEvent( 
               Suggestions.Events.CommandSelected, 
               ( event, object ) -> currentCommand = ( Command< ? > ) object
      );
   }// End Constructor
   
   /**
    * Method to reset the monitored input and {@link Command}.
    */
   public void reset(){
      currentCommand = null;
      currentInput = null;
   }// End Method
}// End Class
