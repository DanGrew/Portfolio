/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.GridItemSelection;
import diagram.controls.SliderItemImpl;
import diagram.shapes.ellipticpolygon.EllipticPolygon;

/**
 * {@link RotationItems} provides the {@link GridItemSelection} for the properties
 * controlling the rotation of an {@link EllipticPolygon}.
 */
public class RotationItems extends GridItemSelection {

   /**
    * Constructs a new {@link RotationItems}.
    * @param polygon the {@link EllipticPolygon} to control.
    */
   public RotationItems( EllipticPolygon polygon ) {
      super(   
               1, 1, 
               new SliderItemImpl( "Degrees", rotation -> polygon.rotateProperty().set( rotation ) )
                  .setRange( 0, 360 )
                  .setRangeLabels( 45, 90, 15 )
      );
   }//End Constructor

}//End Class
