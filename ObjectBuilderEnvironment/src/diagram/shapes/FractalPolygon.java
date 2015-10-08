/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import math.ShapesAndVectors;

/**
 * The {@link FractalPolygon} is an extension of {@link EllipticPolygon} that applies a fractal pattern
 * to the sides of the {@link EllipticPolygon}.
 */
public class FractalPolygon extends EllipticPolygon{
   
   private IntegerProperty numberOfFractals = new SimpleIntegerProperty( 1 );
   
   /**
    * Method to calculate the fractal pattern for the edge given by the two {@link Point2D}s.
    * @param sides the number of sides in the shape to be reproduced in the fractal.
    * @param from the {@link Point2D} from.
    * @param to the {@link Point2D} to.
    * @return a {@link List} of {@link Double}s representing the points in the {@link Polygon}.
    */
   public static List< Double > calculateFractal( int sides, Point2D from, Point2D to ) {
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
      EllipticPolygon polygon = new EllipticPolygon( sides, 0, 0, radius, radius );
      
      double rotationForAlignment = 360.0 / ( sides * 2 );
      double angleOfLine = ShapesAndVectors.getAngleOfLineBetweenTwoPoints( from, to );
      rotationForAlignment = angleOfLine + 90 + rotationForAlignment;
      
      List< Double > fractalPolygonPoints = polygon.calculateSidePoints( polygon, rotationForAlignment );
      
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
    * Constructs a new {@link FractalPolygon}.
    * @param numberOfSides the number of sides in the {@link Polygon}.
    * @param centrePositionX the centre x of the {@link Polygon}.
    * @param centrePositionY the centre y of the {@link Polygon}.
    * @param horizontalRadius the horizontal radius.
    * @param verticalRadius the vertical radius.
    */
   public FractalPolygon( 
            int numberOfSides, 
            double centrePositionX, 
            double centrePositionY, 
            double horizontalRadius, 
            double verticalRadius 
   ) {
      super( numberOfSides, centrePositionX, centrePositionY, horizontalRadius, verticalRadius, true );
   }//End Constructor
   
   /**
    * Constructs a new {@link FractalPolygon}.
    * @param numberOfSides the number of sides in the {@link Polygon}.
    * @param centrePositionX the centre x of the {@link Polygon}.
    * @param centrePositionY the centre y of the {@link Polygon}.
    * @param horizontalRadius the horizontal radius.
    * @param verticalRadius the vertical radius.
    * @param numberOfFractals the depth of the fractal pattern.
    */
   public FractalPolygon( 
            int numberOfSides, 
            double centrePositionX, 
            double centrePositionY, 
            double horizontalRadius, 
            double verticalRadius,
            int numberOfFractals
   ) {
      super( numberOfSides, centrePositionX, centrePositionY, horizontalRadius, verticalRadius, false );
      this.numberOfFractals.set( numberOfFractals );
      calculatePoints();
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public PolygonType getPolygonType(){
      return PolygonType.Fractal;
   }//End Method
   
   /**
    * Method to provide the calculation for the points in the {@link Polygon}.
    */
   @Override public void calculatePoints(){
      getPoints().clear();
      getPoints().addAll( calculateFractals() );
   }//End Method
   
   /**
    * Method to calculate the points in the {@link FractalPolygon} accounting for the number of fractal patterns.
    * @return a {@link List} of combined {@link Double} points that make up the {@link Polygon}.
    */
   protected final List< Double > calculateFractals(){
      List< Double > combinedPoints = calculateSidePoints( this, 0 );
      
      for ( int i = 0; i < numberOfFractals.intValue(); i++ ) {
         List< Double > fractal = applyFractal( combinedPoints );
         if ( i < numberOfFractals.intValue() - 1 ) {
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
   protected final List< Double > applyFractal( List< Double > primaryPoints ){
      List< Double > combinedPoints = new ArrayList<>();
      
      //Retain start to calculate fractal edge between end and start.
      Point2D startingPoisition = new Point2D( primaryPoints.remove( 0 ), primaryPoints.remove( 0 ) );
      
      Point2D from = null;
      Point2D to = startingPoisition;
      while ( !primaryPoints.isEmpty() ) {
         from = to;
         to = new Point2D( primaryPoints.remove( 0 ), primaryPoints.remove( 0 ) );
         
         List< Double > fractalPoints = calculateFractal( getNumberOfSides(), from, to );
         combinedPoints.addAll( fractalPoints );
         
         //Remove last x, y as this will be provided by the next.
         fractalPoints.remove( 0 );
         fractalPoints.remove( 0 );
      }
      //Finally calculate between end and start.
      List< Double > fractalPoints = calculateFractal( getNumberOfSides(), to, startingPoisition );
      combinedPoints.addAll( fractalPoints );
      return combinedPoints;
   }//End Method
   
}//End Class
