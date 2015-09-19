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
   
   Regular( 
            ( sides, x, y, h, v ) -> {
               return new EllipticPolygon( sides, x, y, h, v );
            }
   ),
   Starred( 
            ( sides, x, y, h, v ) -> {
               StarredPolygon polygon = new StarredPolygon( 
                        sides, x, y, h, v 
               );
               return polygon;
            }
   ),
   Fractal( 
            ( sides, x, y, h, v ) -> {
               FractalPolygon polygon = new FractalPolygon( 
                        sides, x, y, h, v, 3 
               );
               return polygon;
            }
   );
   
   private PolygonCreator creator;

   /**
    * Constructs a new {@link PolygonType}.
    * @param creator the {@link PolygonCreator} function to construct the {@link EllipticPolygon}.
    */
   private PolygonType( PolygonCreator creator ) {
      this.creator = creator;
   }//End Constructor
   
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
      return creator.createPolygon( numberOfSides, centreX, centreY, horizontalRadius, verticalRadius );
   }//End Method

}//End Enum
