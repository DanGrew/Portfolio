/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import architecture.event.EventSystem;
import architecture.request.RequestSystem;

import command.Command;

/**
 * The {@link SuggestionsModel} supports the {@link JList} providing suggestions to the user
 * based on their input.
 */
public class SuggestionsModel extends AbstractListModel< CommandSuggestion >{

   private static final long serialVersionUID = 1L;
   private JList< CommandSuggestion > parent;
   private List< CommandSuggestion > data;
   
   /**
    * Constructs a new {@link SuggestionsModel}.
    * @param parent the {@link JList} being modelled.
    */
   public SuggestionsModel( JList< CommandSuggestion > parent ) {
      data = new ArrayList< CommandSuggestion >();
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
      CommandSuggestion selected = parent.getSelectedValue();
      
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
      fireContentsChanged( this, 0, data.size() );
      
      parent.setSelectedIndex( -1 );
      if ( data.contains( selected ) ) {
         parent.setSelectedValue( selected, true );
      } else {
         parent.setSelectedIndex( 0 );
      }
   }// End Method
   
   /**
    * {@inheritDoc} 
    */
   @Override public int getSize() {
      return data.size();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public CommandSuggestion getElementAt( int index ) {
      if ( index < 0 || index >= data.size() ) {
         return null;
      } else {
         return data.get( index );
      }
   }// End Method

}// End Class
