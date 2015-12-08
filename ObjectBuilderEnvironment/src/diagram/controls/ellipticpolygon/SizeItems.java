/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.GridItemSelection;
import diagram.controls.NumberSpinnerItemImpl;
import diagram.shapes.ellipticpolygon.EllipticPolygon;

/**
 * {@link SizeItems} provides the {@link GridItemSelection} for the size properties
 * of an {@link EllipticPolygon}.
 */
public class SizeItems extends GridItemSelection {

   /**
    * Constructs a new {@link SizeItems}.
    * @param polygon the {@link EllipticPolygon} to control.
    */
   public SizeItems( EllipticPolygon polygon ) {
      populateGrid( 
               3, 1, 
               new NumberSpinnerItemImpl( "Horizontal Radius", 1, Integer.MAX_VALUE, polygon.horizontalRadiusProperty() ),
               new NumberSpinnerItemImpl( "Vertical Radius", 1, Integer.MAX_VALUE, polygon.verticalRadiusProperty() ),
               new NumberSpinnerItemImpl( "Stroke Thickness", 1, Integer.MAX_VALUE, polygon.strokeWidthProperty() )
      );
   }//End Constructor

}//End Class
