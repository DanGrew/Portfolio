/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import diagram.canvas.DiagramCanvas;
import diagram.controls.ellipticpolygon.ColourItems;
import diagram.controls.ellipticpolygon.InversionItems;
import diagram.controls.ellipticpolygon.LocationItems;
import diagram.controls.ellipticpolygon.NumberOfSidesItems;
import diagram.controls.ellipticpolygon.PolygonTypeItems;
import diagram.controls.ellipticpolygon.SizeItems;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.controls.ellipticpolygon.RotationItems;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

/**
 * The {@link DiagramAccordion} provides an {@link Accordion} for {@link EllipticPolygon}s specifically
 * manipulated in the {@link DiagramCanvas}.
 */
public class DiagramAccordion extends BorderPane {

   /**
    * Constructs a new {@link DiagramAccordion}.
    * @param polygon the {@link EllipticPolygon} to configure.
    */
   public DiagramAccordion( EllipticPolygon polygon ) {
      Accordion window = new Accordion();
      window.getPanes().addAll( 
               new TitledPane( "Type", new PolygonTypeItems( polygon ) ),
               new TitledPane( "Sides", new NumberOfSidesItems( polygon ) ),
               new TitledPane( "Inversion", new InversionItems( polygon ) ),
               new TitledPane( "Rotation", new RotationItems( polygon ) ),
               new TitledPane( "Size", new SizeItems( polygon ) ),
               new TitledPane( "Location", new LocationItems( polygon ) ),
               new TitledPane( "Colour", new ColourItems( polygon ) )
      );
      setCenter( window );
      
      setPrefWidth( 220 );
   }//End Constructor
   
}//End Class
