/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui.console;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;

/**
 * The {@link Console} provides output from the interpreter to the user.
 */
public class Console extends BorderPane {
   
   /**
    * Constructs a new {@link Console}.
    */
   public Console() {
      ListView< ConsoleMessage > list = new ListView< ConsoleMessage >();
      list.getSelectionModel().setSelectionMode( SelectionMode.SINGLE );
      new ConsoleModel( list );
      setCenter( list );
   }// End Constructor

}// End Class
