/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import architecture.event.EventSystem;
import architecture.request.RequestSystem;
import command.Command;
import javafx.scene.control.ListView;

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
         //Remove unsafe use by re-attaching the generic.
         Command< ? > commandA = ( Command< ? > )command;
         List< String > suggestions = commandA.getSuggestions( input );
         suggestions.forEach( suggestion -> {
            data.add( new CommandSuggestion( command, suggestion ) );
         } );
      } );
      
      //Sort all suggestions alphabetically.
      Collections.sort( data, new Comparator< CommandSuggestion >() {
         @Override public int compare( CommandSuggestion o1, CommandSuggestion o2 ) {
            return o1.getSuggestion().toLowerCase().compareTo( o2.getSuggestion().toLowerCase() );
         }// End Method
      } );
      parent.getSelectionModel().clearSelection();
      if ( data.contains( selected ) ) {
         parent.getSelectionModel().select( selected );
      } else {
         parent.getSelectionModel().select( 0 );
      }
   }// End Method

}// End Class
