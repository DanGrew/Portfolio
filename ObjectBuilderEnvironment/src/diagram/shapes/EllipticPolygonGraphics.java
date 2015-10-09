/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

/**
 * Convenience class for creating graphical representations of {@link EllipticPolygon}s.
 */
public class EllipticPolygonGraphics {

   public static EllipticPolygon getTriangle() {
      return getPolygon( 3, PolygonType.Regular );
   }
   
   public static EllipticPolygon getDiamond() {
      return getPolygon( 4, PolygonType.Regular );
   }

   public static EllipticPolygon getPentagon() {
      return getPolygon( 5, PolygonType.Regular );
   }
   
   public static EllipticPolygon getHexagon() {
      return getPolygon( 6, PolygonType.Regular );
   }
   
   public static EllipticPolygon getHeptagon() {
      return getPolygon( 7, PolygonType.Regular );
   }
   
   public static EllipticPolygon getOctagon() {
      return getPolygon( 8, PolygonType.Regular );
   }
   
   public static EllipticPolygon getNonagon() {
      return getPolygon( 9, PolygonType.Regular );
   }
   
   public static EllipticPolygon getDecagon() {
      return getPolygon( 10, PolygonType.Regular );
   }
   
   public static EllipticPolygon getRegularPolygon( int sides ) {
      return getPolygon( sides, PolygonType.Regular );
   }
   
   public static EllipticPolygon getStarredTriangle() {
      return getPolygon( 3, PolygonType.Starred );
   }
   
   public static EllipticPolygon getStarredDiamond() {
      return getPolygon( 4, PolygonType.Starred );
   }

   public static EllipticPolygon getStarredPentagon() {
      return getPolygon( 5, PolygonType.Starred );
   }
   
   public static EllipticPolygon getStarredHexagon() {
      return getPolygon( 6, PolygonType.Starred );
   }
   
   public static EllipticPolygon getStarredHeptagon() {
      return getPolygon( 7, PolygonType.Starred );
   }
   
   public static EllipticPolygon getStarredOctagon() {
      return getPolygon( 8, PolygonType.Starred );
   }
   
   public static EllipticPolygon getStarredNonagon() {
      return getPolygon( 9, PolygonType.Starred );
   }
   
   public static EllipticPolygon getStarredDecagon() {
      return getPolygon( 10, PolygonType.Starred );
   }
   
   public static EllipticPolygon getStarredPolygon( int sides ) {
      return getPolygon( sides, PolygonType.Starred );
   }
   
   public static EllipticPolygon getPolygon( int sides, PolygonType type ) {
      return type.createPolygon( sides, 0, 0, 25, 25 );
   }
   
   public static EllipticPolygon getPolygon( EllipticPolygon polygon ) {
      return getPolygon( polygon.getNumberOfSides(), polygon.polygonTypeProperty().get() );
   }
}//End Class
