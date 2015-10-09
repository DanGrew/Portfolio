/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.ButtonItemImpl;
import diagram.controls.GridItemSelection;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonGraphics;

/**
 * {@link GridItemSelection} for the items to configure the {@link PolygonType} of an
 * {@link EllipticPolygon}.
 */
public class PolygonTypeItems extends GridItemSelection {

   /**
    * Constructs a new {@link PolygonTypeItems}.
    * @param polygon the {@link EllipticPolygon} to configure.
    */
   public PolygonTypeItems( EllipticPolygon polygon ) {
      super( 
               2, 2,
               new ButtonItemImpl( 
                        "Regular", 
                        EllipticPolygonGraphics.getPolygon( polygon.getNumberOfSides(), PolygonType.Regular ), 
                        () -> polygon.polygonTypeProperty().set( PolygonType.Regular ) 
               ),
               new ButtonItemImpl( 
                        "Starred", 
                        EllipticPolygonGraphics.getPolygon( polygon.getNumberOfSides(), PolygonType.Starred ), 
                        () -> polygon.polygonTypeProperty().set( PolygonType.Starred ) 
               ),
               new ButtonItemImpl( 
                        "Fractal", 
                        EllipticPolygonGraphics.getPolygon( polygon.getNumberOfSides(), PolygonType.Fractal ), 
                        () -> polygon.polygonTypeProperty().set( PolygonType.Fractal ) 
               )
            );
   }//End Constructor

}//End Class
