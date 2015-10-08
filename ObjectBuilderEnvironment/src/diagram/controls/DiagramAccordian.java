/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import diagram.controls.ellipticpolygon.InversionItems;
import diagram.controls.ellipticpolygon.NumberOfSidesItems;
import diagram.shapes.EllipticPolygon;
import diagram.shapes.EllipticPolygonGraphics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
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
               new TitledPane( "Inversion", new InversionItems( polygon ) )
      );
      pane.setCenter( window );
      
      GridPane grid = new GridPane();
      grid.add( new Circle( 2 ), 1, 1 );
      window.getPanes().add( new TitledPane( "Test2", grid ) );
      
      Scene scene = new Scene( pane, 220, 800 );
      stage.setScene( scene );
      stage.show();
   }// End Method
   
}//End Class
