/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

/**
 * The {@link PolygonType} represents the different types of {@link EllipticPolygon}s that can be created.
 */
public enum PolygonType implements PolygonCreator {
   
   Regular,
   Starred,
   Fractal;
   
   /**
    * {@inheritDoc}
    */
   @Override public EllipticPolygon createPolygon( 
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
