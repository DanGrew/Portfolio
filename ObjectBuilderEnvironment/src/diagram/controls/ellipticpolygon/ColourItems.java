/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.ColourPickerItemImpl;
import diagram.controls.GridItem;
import diagram.controls.GridItemSelection;
import diagram.shapes.EllipticPolygon;
import javafx.scene.paint.Color;

/**
 * {@link ColourItems} provides the {@link GridItem}s to control the {@link Color} of 
 * an {@link EllipticPolygon}.
 */
public class ColourItems extends GridItemSelection {

   /**
    * Constructs a new {@link ColourItems}.
    * @param polygon {@link EllipticPolygon} to colour.
    */
   public ColourItems( EllipticPolygon polygon ) {
      super( 
               1, 1, 
               new ColourPickerItemImpl( "Shape Fill", colour -> polygon.setFill( colour ) ) 
      );
   }//End Constructor

}//End Class
