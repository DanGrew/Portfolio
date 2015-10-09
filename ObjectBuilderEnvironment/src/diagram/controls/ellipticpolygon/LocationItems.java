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
      super( 
               2, 1, 
               new NumberSpinnerItemImpl( "X", value -> polygon.translateXProperty().set( value ) )
                  .setRange( Integer.MIN_VALUE, Integer.MAX_VALUE ),
               new NumberSpinnerItemImpl( "Y", value -> polygon.translateYProperty().set( value ) )
                  .setRange( Integer.MIN_VALUE, Integer.MAX_VALUE )
      );
   }//End Constructor

}//End Class
