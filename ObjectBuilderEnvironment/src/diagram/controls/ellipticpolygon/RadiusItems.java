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
import diagram.shapes.EllipticPolygon;

/**
 * {@link RadiusItems} provides the {@link GridItemSelection} for the radius properties
 * of an {@link EllipticPolygon}.
 */
public class RadiusItems extends GridItemSelection {

   /**
    * Constructs a new {@link RadiusItems}.
    * @param polygon the {@link EllipticPolygon} to control.
    */
   public RadiusItems( EllipticPolygon polygon ) {
      super( 
               2, 1, 
               new NumberSpinnerItemImpl( "Horizontal Radius", value -> polygon.horizontalRadiusProperty().set( value ) )
                  .setRange( 1, Integer.MAX_VALUE ),
               new NumberSpinnerItemImpl( "Vertical Radius", value -> polygon.verticalRadiusProperty().set( value ) )
                  .setRange( 1, Integer.MAX_VALUE )
      );
   }//End Constructor

}//End Class
