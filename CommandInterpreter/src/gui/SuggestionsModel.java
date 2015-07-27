/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.util.List;

import javafx.scene.control.ListView;

import javax.swing.JList;

import architecture.event.EventSystem;
import architecture.request.RequestSystem;

import command.Command;

/**
 * The {@link SuggestionsModel} supports the {@link JList} providing suggestions to the user
 * based on their input.
 */
public class SuggestionsModel {

   private ListView< CommandSuggestion > parent;
   private List< CommandSuggestion > data;
   
   /**
    * Constructs a new {@link SuggestionsModel}.
    * @param parent the {@link ListView} being modelled.
    */
   public SuggestionsModel( ListView< CommandSuggestion > parent ) {
      data = parent.getItems();
      this.parent = parent;
      
      EventSystem.registerForEvent( CommandInput.Events.TextInput, ( object, source ) -> {
         inputChanged( source.toString() );
      } );
   }// End Constructor
   
   /**
    * Method to handle the changing of the input.
    * @param input the new input {@link String}.
    */
   private void inputChanged( String input ) {
      CommandSuggestion selected = parent.getSelectionModel().getSelectedItem();
      
      data.clear();
      @SuppressWarnings("rawtypes") List< Command > commands = RequestSystem.retrieveAll( 
               Command.class, 
               command -> { return command.partialMatches( input ); } 
      );
      commands.forEach( command -> {
         List< String > suggestions = command.getSuggestions( input );
         suggestions.forEach( suggestion -> {
            data.add( new CommandSuggestion( command, suggestion ) );
         } );
      } );
      
      parent.getSelectionModel().clearSelection();
      if ( data.contains( selected ) ) {
         parent.getSelectionModel().select( selected );
      } else {
         parent.getSelectionModel().select( 0 );
      }
   }// End Method

}// End Class
