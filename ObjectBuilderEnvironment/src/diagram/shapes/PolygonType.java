/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;

/**
 * The {@link PolygonType} represents the different types of {@link EllipticPolygon}s that can be created.
 */
public enum PolygonType {
   
   Regular,
   Starred,
   Fractal;
   
   /**
    * {@inheritDoc}
    */
   public EllipticPolygon createPolygon( 
            int numberOfSides, 
            double centreX, 
            double centreY, 
            double horizontalRadius, 
            double verticalRadius 
   ) {
      return new EllipticPolygon( 
            new EllipticPolygonBuilder( this, numberOfSides )
               .centreXProperty( centreX )
               .centreYProperty( centreY )
               .horizontalRadiusProperty( horizontalRadius )
               .verticalRadiusProperty( verticalRadius )
               .numberOfFractals( 3 ) 
      );
   }//End Method

}//End Enum
