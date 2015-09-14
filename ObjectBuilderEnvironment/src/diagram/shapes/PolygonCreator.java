/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

/**
 * The {@link PolygonCreator} is responsible for providing a functional interface to create
 * a {@link SidedPolygon} of the appropriate type.
 */
public interface PolygonCreator {
   
   /**
    * Method to create a {@link SidedPolygon} with the given parameters.
    * @param numberOfSides the number of sides to the underlying {@link SidedPolygon}.
    * @param centreX the centre x position.
    * @param centreY the centre y position.
    * @return the constructed {@link SidedPolygon}.
    */
   public SidedPolygon createPolygon( int numberOfSides, double centreX, double centreY );

}//End Interface
