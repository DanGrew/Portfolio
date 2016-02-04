/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import architecture.request.RequestSystem;
import command.Command;
import defaults.CommonCommands;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The {@link CommandInterpreter} provides a {@link JFrame} and {@link JMenuBar} for the 
 * {@link CommandPrompt}.
 */
public class CommandInterpreter extends Application {
   
   /**
    * Constructs a new {@link CommandInterpreter}.
    */
   public CommandInterpreter() {}// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage primaryStage ) throws Exception {
      BorderPane pane = new BorderPane();
      pane.setTop( new CommandInterpreterMenuBar() );
      
      pane.setCenter( new CommandPrompt() );

      Scene scene = new Scene( pane, 1000, 400 );
      primaryStage.setScene( scene );
      primaryStage.show();
   }// End Method
   
   
   public static void main( String[] args ) {
      RequestSystem.store( CommonCommands.TRUE_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.INVERT_BOOLEAN_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.INVERT_STRING_CASE_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.BINARY_OR_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.BINARY_XOR_COMMAND, Command.class );
      RequestSystem.store( CommonCommands.ADDITION_COMMAND, Command.class );

      launch( args );
   }// End Method

}// End Class
