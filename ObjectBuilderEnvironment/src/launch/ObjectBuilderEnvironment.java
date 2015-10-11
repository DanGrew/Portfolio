/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package launch;

import gui.ObjectBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import menu.ObeMenuBar;

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
      
      BorderPane header = new BorderPane();
      header.setLeft( new ObeMenuBar() );
      view.setTop( header );
      
      Perspectives perspectives = new Perspectives( view );
      header.setRight( perspectives );
      
      Scene scene = new Scene( view, 1200, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
}// End Class
