/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

/**
 * The {@link PolygonType} represents the different types of {@link SidedPolygon}s that can be created.
 */
public enum PolygonType implements PolygonCreator {
   
   Regular( 
            ( sides, x, y ) -> {
               return new SidedPolygon( sides, x, y );
            }
   ),
   Starred( 
            ( sides, x, y ) -> {
               SidedPolygon innerPolygon = new SidedPolygon( sides, x, y );
               MultiLayeredPolygon polygon = new MultiLayeredPolygon( innerPolygon, x, y );
               return polygon;
            }
   );
   
   private PolygonCreator creator;

   /**
    * Constructs a new {@link PolygonType}.
    * @param creator the {@link PolygonCreator} function to construct the {@link SidedPolygon}.
    */
   private PolygonType( PolygonCreator creator ) {
      this.creator = creator;
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public SidedPolygon createPolygon( int numberOfSides, double centreX, double centreY ) {
      return creator.createPolygon( numberOfSides, centreX, centreY );
   }//End Method

}//End Enum
