/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import diagram.controls.ellipticpolygon.ColourItems;
import diagram.controls.ellipticpolygon.InversionItems;
import diagram.controls.ellipticpolygon.NumberOfSidesItems;
import diagram.controls.ellipticpolygon.RadiusItems;
import diagram.controls.ellipticpolygon.RotationItems;
import diagram.shapes.EllipticPolygon;
import diagram.shapes.EllipticPolygonGraphics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The {@link DiagramAccordian} is an {@link Accordion} that is used for configuring
 * {@link EllipticPolygon}s.
 */
public class DiagramAccordian extends Application {

   public static void main( String[] args ) {
      DiagramAccordian.launch();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage stage ) throws Exception {
      BorderPane pane = new BorderPane();
      EllipticPolygon polygon = EllipticPolygonGraphics.getStarredTriangle();
      pane.setTop( polygon );
      
      Accordion window = new Accordion();
      window.getPanes().addAll( 
               new TitledPane( "Sides", new NumberOfSidesItems( polygon ) ),
               new TitledPane( "Inversion", new InversionItems( polygon ) ),
               new TitledPane( "Rotation", new RotationItems( polygon ) ),
               new TitledPane( "Size", new RadiusItems( polygon ) ),
               new TitledPane( "Colour", new ColourItems( polygon ) )
      );
      pane.setCenter( window );
      
      Scene scene = new Scene( pane, 220, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
}//End Class
