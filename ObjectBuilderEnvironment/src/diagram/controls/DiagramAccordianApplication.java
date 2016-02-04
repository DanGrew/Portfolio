/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import diagram.selection.ShapeManagerSelectionControllerImpl;
import diagram.selection.ShapesManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@link DiagramAccordianApplication} is an {@link Accordion} that is used for configuring
 * {@link EllipticPolygon}s.
 */
public class DiagramAccordianApplication extends Application {

   public static void main( String[] args ) {
      DiagramAccordianApplication.launch();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage stage ) throws Exception {
      ShapesManager manager = new ShapesManager();
      DiagramAccordion accordion = new DiagramAccordion( 
               new ShapeManagerSelectionControllerImpl( manager ) );
      Scene scene = new Scene( accordion, 220, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
}//End Class
