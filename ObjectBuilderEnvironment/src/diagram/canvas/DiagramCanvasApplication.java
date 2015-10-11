/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@link DiagramCanvasApplication} is an {@link Application} for launching the {@link DiagramCanvas}.
 */
public class DiagramCanvasApplication extends Application {

   public static void main( String[] args ) {
      DiagramCanvasApplication.launch();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage stage ) throws Exception {
      DiagramCanvas canvas = new DiagramCanvas();
      
      Scene scene = new Scene( canvas, 1200, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
}//End Class
