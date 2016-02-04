/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import diagram.controls.ellipticpolygon.BindingItems;
import diagram.controls.ellipticpolygon.ColourItems;
import diagram.controls.ellipticpolygon.InversionItems;
import diagram.controls.ellipticpolygon.LocationItems;
import diagram.controls.ellipticpolygon.NumberOfFractalsItems;
import diagram.controls.ellipticpolygon.NumberOfSidesItems;
import diagram.controls.ellipticpolygon.PolygonTypeItems;
import diagram.controls.ellipticpolygon.RotationItems;
import diagram.controls.ellipticpolygon.SizeItems;
import diagram.selection.SelectionController;
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
               new TitledPane( "Inversion", new InversionItems( controller ) ),
               new TitledPane( "Rotation", new RotationItems( controller ) ),
               new TitledPane( "Size", new SizeItems( controller ) ),
               new TitledPane( "Location", new LocationItems( controller ) ),
               new TitledPane( "Colour", new ColourItems( controller ) ),
               new TitledPane( "Bindings", new BindingItems( controller ) )
      );
      setCenter( window );
      
      setPrefWidth( 220 );
   }//End Constructor
   
}//End Class
