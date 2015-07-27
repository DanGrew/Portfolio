/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.console;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javax.swing.JList;
import javax.swing.ListModel;

import architecture.event.EventSystem;

/**
 * The {@link ConsoleModel} provides the {@link ListModel} for the {@link Console} {@link JList}.
 */
public class ConsoleModel {

   public enum Events {
      /** Event raised when a new message is communicated to the {@link Console}. **/
      NewMessage;
   }// End Enum
   
   private ObservableList< ConsoleMessage > console;
   
   /**
    * Constructs a new {@link ConsoleModel}.
    * @param listView the {@link ListView} of {@link ConsoleMessage}s to control.
    */
   public ConsoleModel( ListView< ConsoleMessage > listView ) {
      console = listView.getItems();
      console.add( new ConsoleMessageImpl( "Welcome to the Command Interpreter!" ) );
      
      EventSystem.registerForEvent( 
               Events.NewMessage, 
               ( event, source ) -> {
                  console.add( 0, ( ConsoleMessage )source );
               }
      );
   }// End Constructor

}// End Class
