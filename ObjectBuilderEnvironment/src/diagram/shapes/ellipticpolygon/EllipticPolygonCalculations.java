/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes.ellipticpolygon;

import java.util.ArrayList;
import java.util.List;

import diagram.shapes.PolygonType;
import javafx.geometry.Point2D;
import math.ShapesAndVectors;

/**
 * {@link EllipticPolygonCalculations} is responsible for providing the calculations used to 
 * generate points in an {@link EllipticPolygon}.
 */
class EllipticPolygonCalculations {
   
   /**
    * Method to calculate the fractal pattern for the edge given by the two {@link Point2D}s.
    * @param sides the number of sides in the shape to be reproduced in the fractal.
    * @param from the {@link Point2D} from.
    * @param to the {@link Point2D} to.
    * @return a {@link List} of {@link Double}s representing the points in the {@link Polygon}.
    */
   static List< Double > calculateFractal( int sides, Point2D from, Point2D to ) {
      List< Double > points = new ArrayList<>();
      points.add( from.getX() );
      points.add( from.getY() );
      
      double length = from.distance( to );
      double sideLength = length / 3;
      double xThird = ( to.getX() - from.getX() ) / 3;
      double yThird = ( to.getY() - from.getY() ) / 3;
      
      //Navigate to anchor for shape.
      double secondPointX = from.getX() + xThird;
      double secondPointY = from.getY() + yThird;
      
      double radius = ShapesAndVectors.calculateRadiusOfRegularPolygon( sides, sideLength );
      EllipticPolygon polygon = new EllipticPolygon( 
               new EllipticPolygonBuilder( PolygonType.Regular, sides )
                  .centreXProperty( 0 )
                  .centreYProperty( 0 )
                  .horizontalRadiusProperty( radius )
                  .verticalRadiusProperty( radius )
      );
      
      double rotationForAlignment = 360.0 / ( sides * 2 );
      double angleOfLine = ShapesAndVectors.getAngleOfLineBetweenTwoPoints( from, to );
      rotationForAlignment = angleOfLine + 90 + rotationForAlignment;
      
      List< Double > fractalPolygonPoints = calculateSidePoints( polygon, rotationForAlignment );
      
      Point2D firstInPolygon = new Point2D( fractalPolygonPoints.get( 0 ), fractalPolygonPoints.get( 1 ) );
      double xOffset = firstInPolygon.getX() - secondPointX;
      double yOffset = firstInPolygon.getY() - secondPointY;
      
      while ( !fractalPolygonPoints.isEmpty() ) {
         double x = fractalPolygonPoints.remove( 0 );
         x -= xOffset;
         points.add( x );
         
         double y = fractalPolygonPoints.remove( 0 );
         y -= yOffset;
         points.add( y );
      }
      points.add( to.getX() );
      points.add( to.getY() );
      
      return points;
   }//End Method
   
   /**
    * Method to calculate the points of the {@link EllipticPolygon} given with the angle offset.
    * @param polygon the {@link EllipticPolygon} to calculate for.
    * @param angleOffset the offset to apply to the angle.
    * @return the calculated points.
    */
   static List< Double > calculateSidePoints( EllipticPolygon polygon, double angleOffset ){
      List< Double > calculatedPoints = new ArrayList<>();
      for ( int i = 0; i < polygon.numberOfSidesProperty().doubleValue(); i++ ) {
         double angle = ( 360.0 / polygon.numberOfSidesProperty().doubleValue() ) * i;
         Point2D point = ShapesAndVectors.calculatePointOnCircle( 
                  polygon.centreXProperty().doubleValue(), 
                  polygon.centreYProperty().doubleValue(), 
                  calculateAppliedHorizontalRadius( polygon ), 
                  calculateAppliedVerticalRadius( polygon ), 
                  angle + angleOffset
         );
         calculatedPoints.add( point.getX() );
         calculatedPoints.add( point.getY() );
      }
      return calculatedPoints;
   }//End Method
   
   /**
    * Method to calculate the points in the {@link StarredPolygon} accounting for the inner {@link EllipticPolygon}.
    * @return a {@link List} of combined {@link Double} points that make up the {@link Polygon}.
    */
   static List< Double > calculateWithInnerPolygon( EllipticPolygon outerPolygon, EllipticPolygon innerPolygon ){
      List< Double > combinedPoints = new ArrayList<>();
      List< Double > primaryPoints = calculateSidePoints( outerPolygon, 0 );
      List< Double > secondaryPoints = calculateSidePoints( innerPolygon, 360.0 / outerPolygon.getNumberOfSides() / 2 );
      
      for ( int i = 0; i < outerPolygon.getNumberOfSides(); i++ ) {
         combinedPoints.add( primaryPoints.remove( 0 ) );
         combinedPoints.add( primaryPoints.remove( 0 ) );
         combinedPoints.add( secondaryPoints.remove( 0 ) );
         combinedPoints.add( secondaryPoints.remove( 0 ) );
      }
      return combinedPoints;
   }//End Method
   
   /**
    * Method to calculate the points in the {@link FractalPolygon} accounting for the number of fractal patterns.
    * @return a {@link List} of combined {@link Double} points that make up the {@link Polygon}.
    */
   static List< Double > calculateFractals( EllipticPolygon polygon ){
      List< Double > combinedPoints = calculateSidePoints( polygon, 0 );
      
      for ( int i = 0; i < polygon.numberOfFractalsProperty().intValue(); i++ ) {
         List< Double > fractal = applyFractal( polygon.getNumberOfSides(), combinedPoints );
         if ( i < polygon.numberOfFractalsProperty().intValue() - 1 ) {
            fractal.remove( 0 );
            fractal.remove( 0 );
         }
         combinedPoints.addAll( fractal );
      }
      
      return combinedPoints;
   }//End Method
   
   /**
    * Method to apply the fractal pattern to the {@link Polygon} given by the provided {@link List} of {@link Double}s.
    * @param primaryPoints the {@link Double}s representing the points in the {@link Polygon}.
    * @return a {@link List} of {@link Double}s for the {@link Polygon} with a fractal pattern applied.
    */
   static List< Double > applyFractal( int numberOfSides, List< Double > primaryPoints ){
      List< Double > combinedPoints = new ArrayList<>();
      
      //Retain start to calculate fractal edge between end and start.
      Point2D startingPoisition = new Point2D( primaryPoints.remove( 0 ), primaryPoints.remove( 0 ) );
      
      Point2D from = null;
      Point2D to = startingPoisition;
      while ( !primaryPoints.isEmpty() ) {
         from = to;
         to = new Point2D( primaryPoints.remove( 0 ), primaryPoints.remove( 0 ) );
         
         List< Double > fractalPoints = EllipticPolygonCalculations.calculateFractal( numberOfSides, from, to );
         combinedPoints.addAll( fractalPoints );
         
         //Remove last x, y as this will be provided by the next.
         fractalPoints.remove( 0 );
         fractalPoints.remove( 0 );
      }
      //Finally calculate between end and start.
      List< Double > fractalPoints = EllipticPolygonCalculations.calculateFractal( numberOfSides, to, startingPoisition );
      combinedPoints.addAll( fractalPoints );
      return combinedPoints;
   }//End Method
   
   /**
    * Method to take the configuration of the {@link EllipticPolygon} and calculate the 
    * horizontal radius.
    * @return the radius to use in points calculations.
    */
   static double calculateAppliedHorizontalRadius( EllipticPolygon polygon ){
      if ( polygon.inversionProperty().get() ) {
         return polygon.horizontalRadiusProperty().negate().doubleValue();
      } else {
         return polygon.horizontalRadiusProperty().doubleValue();
      }
   }//End Method
   
   /**
    * Method to take the configuration of the {@link EllipticPolygon} and calculate the 
    * vertical radius.
    * @return the radius to use in points calculations.
    */
   static double calculateAppliedVerticalRadius( EllipticPolygon polygon ){
      if ( polygon.inversionProperty().get() ) {
         return polygon.verticalRadiusProperty().negate().doubleValue();
      } else {
         return polygon.verticalRadiusProperty().doubleValue();
      }
   }//End Method

}//End Class
