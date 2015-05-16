/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.action.AutoCompleteAction;
import architecture.event.EventSystem;
import command.Command;

/**
 * The {@link CommandAutoComplete} is responsible for processing the auto completion of the
 * current input for the current {@link Command}.
 */
public class CommandAutoComplete extends CommandMonitor{

   public enum Events {
      /** Event fired when a suggestion has been made.**/
      AutoCompletSuggestion;
   }// End Enum
   
   /**
    * Constructs a new {@link CommandAutoComplete}.
    */
   public CommandAutoComplete() {
      super();
      EventSystem.registerForEvent( 
               AutoCompleteAction.Events.AutoComplete, 
               ( event, object ) -> autoComplete()
      );
   }// End Constructor
   
   /**
    * Method to identify the auto completion.
    */
   private void autoComplete(){
      if ( currentCommand != null ) {
         String suggestion = currentCommand.autoComplete( currentInput );
         EventSystem.raiseEvent( Events.AutoCompletSuggestion, suggestion );
      }
   }// End Method
}// End Class
