/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.console;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import architecture.event.EventSystem;

/**
 * The {@link ConsoleModel} provides the {@link ListModel} for the {@link Console} {@link JList}.
 */
public class ConsoleModel extends AbstractListModel< ConsoleMessage >{

   private static final long serialVersionUID = 1L;

   public enum Events {
      /** Event raised when a new message is communicated to the {@link Console}. **/
      NewMessage;
   }// End Enum
   
   private List< ConsoleMessage > console;
   
   /**
    * Constructs a new {@link ConsoleModel}.
    */
   public ConsoleModel() {
      console = new ArrayList< ConsoleMessage >();
      console.add( new ConsoleMessageImpl( "Welcome to the Command Interpreter!" ) );
      
      EventSystem.registerForEvent( 
               Events.NewMessage, 
               ( event, source ) -> {
                  console.add( 0, ( ConsoleMessage )source );
                  fireContentsChanged( this, 0, console.size() );
               }
      );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public int getSize() {
      return console.size();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public ConsoleMessage getElementAt( int index ) {
      if ( index < 0 || index >= console.size() ) {
         return null;
      } else {
         return console.get( index );
      }
   }// End Method

}// End Class
