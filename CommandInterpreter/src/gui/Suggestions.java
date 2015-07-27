/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import gui.function.GuiFunctions;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;

import javax.swing.JList;

import architecture.event.EventSystem;

import command.Command;

/**
 * The {@link Suggestions} represents a {@link JList} of possible {@link Command}s based on the 
 * input the user has provided.
 */
public class Suggestions extends BorderPane {

   public enum Events {
      /** Event raised when a {@link Command} has been selected.**/
      CommandSelected;
   }// End Enum
   
   /**
    * Constructs a new {@link Suggestions} panel.
    */
   public Suggestions() {
      ListView< CommandSuggestion > list = new ListView< CommandSuggestion >();
      list.getSelectionModel().setSelectionMode( SelectionMode.SINGLE );
      new SuggestionsModel( list );
      
      list.getSelectionModel().getSelectedItems().addListener( ( Change< ? extends CommandSuggestion > change ) -> {
         refreshSelection( list );
      } );
      setCenter( list );
      
      EventSystem.registerForEvent( 
         GuiFunctions.Events.ScrollUp, 
         ( event, source ) -> {
            int currentSelection = list.getSelectionModel().getSelectedIndex();
            if ( currentSelection > 0 ) {
               list.getSelectionModel().clearAndSelect( currentSelection - 1 );
            }
         }
      );
      EventSystem.registerForEvent( 
         GuiFunctions.Events.ScrollDown, 
         ( event, source ) -> {
            int currentSelection = list.getSelectionModel().getSelectedIndex();
            if ( currentSelection < list.getItems().size() - 1 ) {
               list.getSelectionModel().clearAndSelect( currentSelection + 1 );
            }
         }
      );
   }// End Constructor
   
   /**
    * Method to refresh the current selection in the {@link JList}.
    * @param list the {@link ListView} holding the {@link CommandSuggestion}s.
    */
   private void refreshSelection( ListView< CommandSuggestion > list ){
      CommandSuggestion selected = list.getSelectionModel().getSelectedItem();
      if ( selected != null ) {
         EventSystem.raiseEvent( Events.CommandSelected, selected.getCommand() );
      } else {
         EventSystem.raiseEvent( Events.CommandSelected, null );
      }
   }// End Method

}// End Class
