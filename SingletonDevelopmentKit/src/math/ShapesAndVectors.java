/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package math;

import javafx.geometry.Point2D;

/**
 * {@link Class} for defining common math functions for shapes and vectors.
 */
public class ShapesAndVectors {

   /**
    * Method to calculate the radius of a regular polygon from the number of sides and length of side.
    * @param numberOfSides the number of sides in the polygon.
    * @param lengthOfSide the length of a side in the polygon.
    * @return the radius in degrees.
    */
   public static double calculateRadiusOfRegularPolygon( int numberOfSides, double lengthOfSide ) {
      double angle = 180.0 / numberOfSides;
      double demoninator = 2 * Math.sin( Math.toRadians( angle ) );
      return lengthOfSide / demoninator;
   }//End Method
   
   /**
    * Method to calculate the length of the missing side for a right angle triangle.
    * @param aLength the length of the a side of the triangle.
    * @param cLength the length of the c side of the triangle, the hypotenuse.
    * @return the length of the missing, b, side.
    */
   public static double calculateLengthOfSideForRightAngleTriangle( double aLength, double cLength ) {
      return Math.sqrt( ( cLength * cLength ) - ( aLength * aLength ) );
   }//End Method
   
   /**
    * Method to calculate the angle of the line between two points.
    * @param p1 the first {@link Point2D}.
    * @param p2 the second {@link Point2D}.
    * @return the angle, in degrees, between the two.
    */
   public static double getAngleOfLineBetweenTwoPoints(Point2D p1, Point2D p2) { 
      double xDiff = p2.getX() - p1.getX();
      double yDiff = p2.getY() - p1.getY();
      double angle = Math.toDegrees( Math.atan2( yDiff, xDiff ) );
      while ( angle < 0 ) {
         angle += 360.0;
      }
      return angle;
   }//End Method
   
   /**
    * Method to extend the vector between the given {@link Point2D}s, by the given length.
    * @param length the length to extend by.
    * @param from the {@link Point2D} from.
    * @param to the {@link Point2D} to.
    * @return the {@link Point2D} beyond that it is extended to.
    */
   public static Point2D extendVectorBy( double length, Point2D from, Point2D to ) {
      double vectorLength = from.distance( to );
      length = vectorLength - length;
      Point2D end = new Point2D( 
         to.getX() + ( to.getX() - from.getX() ) / vectorLength * length,
         to.getY() + ( to.getY() - from.getY() ) / vectorLength * length
      );
      return end;
   }//End Method
   
   /**
    * Method to rotate the given {@link Point2D} by the given angle, in degrees, as a vector to the point.
    * @param point the {@link Point2D} representing a vector from (0,0).
    * @param angle the angle to rotate by.
    * @return the {@link Point2D} representing the vector of the rotation.
    */
   public static Point2D rotateAsVector( Point2D point, double angle ) {
      angle = Math.toRadians( angle );
      return new Point2D( 
         point.getX() * Math.cos( angle ) - point.getY() * Math.sin( angle ),
         point.getX() * Math.sin( angle ) + point.getY() * Math.cos( angle )
      );
   }//End Method
}//End Class
