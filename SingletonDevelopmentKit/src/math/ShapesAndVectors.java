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
    * Method to calculate the {@link Point2D} on the ellispe given by the parameters.
    * @param originX the centre x of the ellispe.
    * @param originY the centre y of the ellispe.
    * @param horizontalRadius the horizontal radius.
    * @param verticalRadius the vertical radius.
    * @param angle the angle the point is at, in degrees.
    * @return the calculated {@link Point2D}.
    */
   public static final Point2D calculatePointOnCircle( 
            double originX, 
            double originY, 
            double horizontalRadius, 
            double verticalRadius, 
            double angle 
   ){
      angle = Math.toRadians( angle );
      double x = originX + horizontalRadius * Math.cos( angle );
      double y = originY + verticalRadius * Math.sin( angle );
      return new Point2D( x, y );
   }//End Method
   
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
   
   /**
    * Method to rotate the given {@link Point2D} by the given angle, in degrees, about the given point as a vector to the point.
    * @param pointToRotate the {@link Point2D} representing a vector from (0,0).
    * @param pointAbout the {@link Point2D} to rotate about.
    * @param angle the angle to rotate by.
    * @return the {@link Point2D} representing the vector of the rotation.
    */
   public static Point2D rotateAsVectorAbout( Point2D pointToRotate, Point2D pointAbout, double angle ) {
      Point2D relativeToZero = pointToRotate.subtract( pointAbout );
      Point2D rotated = rotateAsVector( relativeToZero, angle );
      return rotated.add( pointAbout );
   }//End Method
}//End Class
