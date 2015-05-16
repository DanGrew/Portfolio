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
   private List< Command< ? > > data;
   
   /**
    * Constructs a new {@link SuggestionsModel}.
    */
   public SuggestionsModel() {
      data = new ArrayList< Command< ? > >();
      
      RequestSystem.store( CommonCommands.TRUE_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.INVERT_BOOLEAN_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.INVERT_STRING_CASE_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.BINARY_OR_COMMAND, Command.class );
      
      EventSystem.registerForEvent( CommandInput.Events.TextInput, ( object, source ) -> {
         data.clear();
         Stream< Command > commands = RequestSystem.retrieveAll( Command.class, command -> { return command.matches( source.toString() ); } );
         commands.forEach( command -> data.add( command ) );
         fireContentsChanged( this, 0, data.size() );
      } );
   }// End Constructor
   
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
      return data.get( index );
   }// End Method

}// End Class
