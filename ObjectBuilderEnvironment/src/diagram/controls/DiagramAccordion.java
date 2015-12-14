/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import diagram.canvas.DiagramCanvasApplication;
import diagram.controls.ellipticpolygon.ColourItems;
import diagram.controls.ellipticpolygon.NumberOfFractalsItems;
import diagram.controls.ellipticpolygon.NumberOfSidesItems;
import diagram.controls.ellipticpolygon.PolygonTypeItems;
import diagram.selection.SelectionController;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

/**
 * The {@link DiagramAccordion} provides an {@link Accordion} for {@link EllipticPolygon}s specifically
 * manipulated in the {@link DiagramCanvasApplication}.
 */
public class DiagramAccordion extends BorderPane {

   /**
    * Constructs a new {@link DiagramAccordion}.
    * @param controller the {@link SelectionController} for controlling the selection.
    */
   public DiagramAccordion( SelectionController controller ) {
      Accordion window = new Accordion();
      window.getPanes().addAll( 
               new TitledPane( "Type", new PolygonTypeItems( controller ) ),
               new TitledPane( "Sides", new NumberOfSidesItems( controller ) ),
               new TitledPane( "Fractals", new NumberOfFractalsItems( controller ) ),
//               new TitledPane( "Inversion", new InversionItems( polygon ) ),
//               new TitledPane( "Rotation", new RotationItems( polygon ) ),
//               new TitledPane( "Size", new SizeItems( polygon ) ),
//               new TitledPane( "Location", new LocationItems( polygon ) ),
               new TitledPane( "Colour", new ColourItems( controller ) )
      );
      setCenter( window );
      
      setPrefWidth( 220 );
   }//End Constructor
   
}//End Class
