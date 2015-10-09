/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonGraphics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
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
      EllipticPolygon polygon = EllipticPolygonGraphics.getStarredTriangle();
      DiagramAccordion accordion = new DiagramAccordion( polygon );
      Scene scene = new Scene( accordion, 220, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
}//End Class
