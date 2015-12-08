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
 * {@link LocationItems} provides the {@link GridItemSelection} for the translation properties
 * of an {@link EllipticPolygon}.
 */
public class LocationItems extends GridItemSelection {

   /**
    * Constructs a new {@link LocationItems}.
    * @param polygon the {@link EllipticPolygon} to control.
    */
   public LocationItems( EllipticPolygon polygon ) {
      populateGrid( 
               2, 1, 
               new NumberSpinnerItemImpl( "X", Integer.MIN_VALUE, Integer.MAX_VALUE, polygon.translateXProperty() ),
               new NumberSpinnerItemImpl( "Y", Integer.MIN_VALUE, Integer.MAX_VALUE, polygon.translateYProperty() )
      );
   }//End Constructor
   
}//End Class
