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
 * a {@link EllipticPolygon} of the appropriate type.
 */
public interface PolygonCreator {
   
   /**
    * Method to create a {@link EllipticPolygon} with the given parameters.
    * @param numberOfSides the number of sides to the underlying {@link EllipticPolygon}.
    * @param centreX the centre x position.
    * @param centreY the centre y position.
    * @return the constructed {@link EllipticPolygon}.
    */
   public EllipticPolygon createPolygon( int numberOfSides, double centreX, double centreY );

}//End Interface
