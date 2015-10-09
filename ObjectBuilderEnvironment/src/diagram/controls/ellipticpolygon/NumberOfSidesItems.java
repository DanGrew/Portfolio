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
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonGraphics;

/**
 * {@link NumberOfSidesItems} provides a custom extension of {@link GridItemSelection} to 
 * define the options for configuring the number of sides on an {@link EllipticPolygon}.
 */
public class NumberOfSidesItems extends GridItemSelection {

   public NumberOfSidesItems( EllipticPolygon polygon ) {
      super( 
               4, 2,
               new ButtonItemImpl( "3 Sides", EllipticPolygonGraphics.getTriangle(), () -> polygon.numberOfSidesProperty().set( 3 ) ),
               new ButtonItemImpl( "4 Sides", EllipticPolygonGraphics.getDiamond(), () -> polygon.numberOfSidesProperty().set( 4 ) ),
               new ButtonItemImpl( "5 Sides", EllipticPolygonGraphics.getPentagon(), () -> polygon.numberOfSidesProperty().set( 5 ) ),
               new ButtonItemImpl( "6 Sides", EllipticPolygonGraphics.getHexagon(), () -> polygon.numberOfSidesProperty().set( 6 ) ),
               new ButtonItemImpl( "7 Sides", EllipticPolygonGraphics.getHeptagon(), () -> polygon.numberOfSidesProperty().set( 7 ) ),
               new ButtonItemImpl( "8 Sides", EllipticPolygonGraphics.getOctagon(), () -> polygon.numberOfSidesProperty().set( 8 ) ),
               new ButtonItemImpl( "9 Sides", EllipticPolygonGraphics.getNonagon(), () -> polygon.numberOfSidesProperty().set( 9 ) ),
               new ButtonItemImpl( "10 Sides", EllipticPolygonGraphics.getDecagon(), () -> polygon.numberOfSidesProperty().set( 10 ) )
      );
   }//End Constructor

}//End Class
