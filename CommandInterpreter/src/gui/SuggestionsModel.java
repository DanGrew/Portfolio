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
import java.util.stream.Stream;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import architecture.event.EventSystem;
import architecture.request.RequestSystem;
import command.Command;
import defaults.CommonCommands;

/**
 * The {@link SuggestionsModel} supports the {@link JList} providing suggestions to the user
 * based on their input.
 */
public class SuggestionsModel extends AbstractListModel< Command< ? > >{

   private static final long serialVersionUID = 1L;
   private JList< Command< ? > > parent;
   private List< Command< ? > > data;
   
   /**
    * Constructs a new {@link SuggestionsModel}.
    * @param parent the {@link JList} being modelled.
    */
   public SuggestionsModel( JList< Command< ? > > parent ) {
      data = new ArrayList< Command< ? > >();
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
      Command< ? > selected = parent.getSelectedValue();
      
      data.clear();
      List< Command > commands = RequestSystem.retrieveAll( 
               Command.class, 
               command -> { return command.partialMatches( input ); } 
      );
      commands.forEach( command -> data.add( command ) );
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
   @Override public Command< ? > getElementAt( int index ) {
      if ( index < 0 || index >= data.size() ) {
         return null;
      } else {
         return data.get( index );
      }
   }// End Method

}// End Class
