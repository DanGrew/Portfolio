/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import diagram.layer.Content;
import diagram.toolbox.ContentToolBox;
import diagram.toolbox.ShapeToolBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import outline.SystemOutline;
import outline.configuration.SystemOutlineDetail;

/**
 * The {@link DiagramCanvas} is a graphical interface for creating abstract diagrams.
 */
public class DiagramCanvas extends Application {

   public static void main( String[] args ) {
      DiagramCanvas.launch();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage stage ) throws Exception {
      DiagramSettings canvasSettings = new DiagramSettings();
      
      BorderPane window = new BorderPane();
      
      Content contentLayer = new Content( canvasSettings );
      CanvasViewport viewPort = new CanvasViewport( contentLayer );
      window.setCenter( viewPort );
      
      ContentToolBox contentToolBox = new ContentToolBox();
      window.setRight( contentToolBox );
      
      window.setTop( new ShapeToolBox( canvasSettings ) );
      
      SystemOutline outline = new SystemOutline( SystemOutlineDetail.systemReferenceOutline() );
      window.setLeft( outline );
      
      Scene scene = new Scene( window, 1200, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
}//End Class
