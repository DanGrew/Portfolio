/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.function.GuiFunctions;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import architecture.event.EventSystem;

/**
 * The {@link CommandInput} represents the element of the gui that accepts input from the user.
 */
public class CommandInput extends BorderPane { 

   public enum Events {
      /** Text has been entered.**/
      TextInput;
   }// End Enum

   /**
    * Constructs a new {@link CommandInput}.
    */
   public CommandInput(){
      TextField textArea = new TextField();
      textArea.setText( "Type stuff here." );
      textArea.textProperty().addListener( ( event, old, newValue ) -> {
         EventSystem.raiseEvent( Events.TextInput, textArea.getText() );
      } );
      setCenter( textArea );
      
      EventSystem.registerForEvent( 
               CommandAutoComplete.Events.AutoCompletSuggestion, 
               ( object, source ) -> {
                  textArea.setText( source.toString() );
                  textArea.end();
               } 
      );
      
      Button executeButton = new Button();
      GuiFunctions.getExecuteFunction().configure( executeButton );
      setRight( executeButton );
   }// End Constructor
}// End Class
