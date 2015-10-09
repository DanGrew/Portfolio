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
               this, 
               numberOfSides, 
               centreX, 
               centreY, 
               horizontalRadius, 
               verticalRadius, 
               3, 
               true 
      );
   }//End Method

}//End Enum
