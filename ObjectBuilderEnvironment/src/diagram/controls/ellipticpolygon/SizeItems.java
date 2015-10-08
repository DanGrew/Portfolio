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
 * {@link SizeItems} provides the {@link GridItemSelection} for the size properties
 * of an {@link EllipticPolygon}.
 */
public class SizeItems extends GridItemSelection {

   /**
    * Constructs a new {@link SizeItems}.
    * @param polygon the {@link EllipticPolygon} to control.
    */
   public SizeItems( EllipticPolygon polygon ) {
      super( 
               3, 1, 
               new NumberSpinnerItemImpl( "Horizontal Radius", value -> polygon.horizontalRadiusProperty().set( value ) )
                  .setRange( 1, Integer.MAX_VALUE ),
               new NumberSpinnerItemImpl( "Vertical Radius", value -> polygon.verticalRadiusProperty().set( value ) )
                  .setRange( 1, Integer.MAX_VALUE ),
               new NumberSpinnerItemImpl( "Stroke Thickness", value -> polygon.setStrokeWidth( value ) )
                  .setRange( 1, Integer.MAX_VALUE )
      );
   }//End Constructor

}//End Class
