/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package launch;

import gui.CommandInterpreterMenuBar;
import gui.CommandPrompt;
import gui.ObjectBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import outline.SystemOutline;

/**
 * The {@link ObjectBuilderEnvironment} launches {@link ObjectBuilder} with the full IDE.
 */
public class ObjectBuilderEnvironment extends Application {

   public static void main( String[] args ) {
      ObjectBuilder.launch();
      launch( args );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage stage ) throws Exception {
      BorderPane view = new BorderPane();
      view.setTop( new CommandInterpreterMenuBar() );
      view.setCenter( new SystemOutline() );
      view.setBottom( new CommandPrompt() );
      
      Scene scene = new Scene( view, 800, 400 );
      stage.setScene( scene );
      stage.setFullScreen( true );
      stage.show();
   }// End Method
   
}// End Class
