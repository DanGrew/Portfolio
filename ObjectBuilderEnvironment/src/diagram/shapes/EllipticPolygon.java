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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import math.ShapesAndVectors;

/**
 * The {@link EllipticPolygon} provides a {@link Polygon} that is centred with horizontal
 * and vertical radius' where any number of points can be used to determine the shape which are
 * defined by the ellipse created by the radius'.
 */
public class EllipticPolygon extends Polygon {
   
   private static final double DEFAULT_RADIUS = 100;
   
   private IntegerProperty numberOfSidesProperty;
   private DoubleProperty centreXProperty;
   private DoubleProperty centreYProperty;
   private DoubleProperty horizontalRadiusProperty;
   private DoubleProperty verticalRadiusProperty;
   private BooleanProperty inversionProperty;
   
   private ObjectProperty< PolygonType > polygonTypeProperty;
   private EllipticPolygon innerPolygon;
   
   private IntegerProperty numberOfFractalsProperty;
   
   /**
    * Method to provide the default radius of the {@link Polygon}.
    * @return the default radius.
    */
   public static final double getDefaultRadius(){
      return DEFAULT_RADIUS;
   }//End Method
   
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
    * Constructs a new {@link EllipticPolygon}.
    * @param builder the {@link EllipticPolygonBuilder} configuring the polygon.
    */
   public EllipticPolygon( EllipticPolygonBuilder builder ) {
      polygonTypeProperty = new SimpleObjectProperty< PolygonType >( builder.getPolygonTypeProperty() );
      polygonTypeProperty.addListener( ( change, old, updated ) ->  calculatePoints() );
      numberOfSidesProperty = new SimpleIntegerProperty( builder.getNumberOfSidesProperty() );
      numberOfSidesProperty.addListener( ( change, old, updated ) -> calculatePoints() );
      centreXProperty = new SimpleDoubleProperty( builder.getCentreXProperty() );
      centreYProperty = new SimpleDoubleProperty( builder.getCentreYProperty() );
      horizontalRadiusProperty = new SimpleDoubleProperty( builder.getHorizontalRadiusProperty() );
      horizontalRadiusProperty.addListener( ( change, oldValue, newValue ) -> calculatePoints() );
      verticalRadiusProperty = new SimpleDoubleProperty( builder.getVerticalRadiusProperty() );
      verticalRadiusProperty.addListener( ( change, oldValue, newValue ) -> calculatePoints() );
      inversionProperty = new SimpleBooleanProperty( builder.isInversionProperty() );
      inversionProperty.addListener( ( change, old, updated ) -> calculatePoints() );
      numberOfFractalsProperty = new SimpleIntegerProperty( builder.getNumberOfFractals() );
      numberOfFractalsProperty.addListener( ( change, old, updated ) -> calculatePoints() );
      calculatePoints();
   }//End Constructor
   
   /**
    * Method to get the {@link PolygonType} associated with this type of {@link EllipticPolygon}.
    * @return the {@link PolygonType}.
    */
   public ObjectProperty< PolygonType > polygonTypeProperty(){
      return polygonTypeProperty;
   }//End Method
   
   /**
    * Method to provide the calculation for the points in the {@link Polygon}.
    */
   public void calculatePoints(){
      getPoints().clear();
      switch ( polygonTypeProperty.get() ) {
         case Fractal:
            getPoints().addAll( calculateFractals() );
            break;
         case Regular:
            getPoints().addAll( calculateSidePoints( this, 0 ) );
            break;
         case Starred:
            innerPolygon = new EllipticPolygon(
                  new EllipticPolygonBuilder( PolygonType.Regular, getNumberOfSides() )
                     .centreXProperty( centreXProperty().doubleValue() )
                     .centreYProperty( centreYProperty().doubleValue() )
                     .horizontalRadiusProperty( horizontalRadiusProperty().doubleValue() / 2 )
                     .verticalRadiusProperty( horizontalRadiusProperty().doubleValue() / 2 )
            );
            getPoints().addAll( calculateWithInnerPolygon() );
            break;
         default:
            break;
      }
   }//End Method
   
   /**
    * Method to calculate the points of the {@link EllipticPolygon} given with the angle offset.
    * @param polygon the {@link EllipticPolygon} to calculate for.
    * @param angleOffset the offset to apply to the angle.
    * @return the calculated points.
    */
   protected final List< Double > calculateSidePoints( EllipticPolygon polygon, double angleOffset ){
      List< Double > calculatedPoints = new ArrayList<>();
      for ( int i = 0; i < polygon.numberOfSidesProperty.doubleValue(); i++ ) {
         double angle = ( 360.0 / polygon.numberOfSidesProperty.doubleValue() ) * i;
         Point2D point = ShapesAndVectors.calculatePointOnCircle( 
                  polygon.centreXProperty.doubleValue(), 
                  polygon.centreYProperty.doubleValue(), 
                  polygon.calculateAppliedHorizontalRadius(), 
                  polygon.calculateAppliedVerticalRadius(), 
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
   private final List< Double > calculateWithInnerPolygon(){
      List< Double > combinedPoints = new ArrayList<>();
      List< Double > primaryPoints = calculateSidePoints( this, 0 );
      List< Double > secondaryPoints = calculateSidePoints( innerPolygon, 360.0 / getNumberOfSides() / 2 );
      
      for ( int i = 0; i < getNumberOfSides(); i++ ) {
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
   protected final List< Double > calculateFractals(){
      List< Double > combinedPoints = calculateSidePoints( this, 0 );
      
      for ( int i = 0; i < numberOfFractalsProperty.intValue(); i++ ) {
         List< Double > fractal = applyFractal( combinedPoints );
         if ( i < numberOfFractalsProperty.intValue() - 1 ) {
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
   
   /**
    * Getter for the {@link DoubleProperty} of the centre x position.
    * @return the {@link DoubleProperty}.
    */
   public DoubleProperty centreXProperty() {
      return centreXProperty;
   }//End Method
   
   /**
    * Getter for the {@link DoubleProperty} of the centre y position.
    * @return the {@link DoubleProperty}.
    */
   public DoubleProperty centreYProperty() {
      return centreYProperty;
   }//End Method
   
   /**
    * Getter for the {@link DoubleProperty} of the horizontal radius.
    * @return the {@link DoubleProperty}.
    */
   public DoubleProperty horizontalRadiusProperty() {
      return horizontalRadiusProperty;
   }//End Method
   
   /**
    * Getter for the {@link DoubleProperty} of the vertical radius.
    * @return the {@link DoubleProperty}.
    */
   public DoubleProperty verticalRadiusProperty() {
      return verticalRadiusProperty;
   }//End Method

   /**
    * Getter for the number of sides in the {@link EllipticPolygon}.
    * @return the number of sides in the shape.
    */
   public int getNumberOfSides() {
      return numberOfSidesProperty.intValue();
   }//End Method
   
   /**
    * Getter for the {@link IntegerProperty} for the number of sides in the {@link EllipticPolygon}.
    * @return the {@link IntegerProperty}.
    */
   public IntegerProperty numberOfSidesProperty(){
      return numberOfSidesProperty;
   }//End Method
   
   /**
    * Method to take the configuration of the {@link EllipticPolygon} and calculate the 
    * horizontal radius.
    * @return the radius to use in points calculations.
    */
   private double calculateAppliedHorizontalRadius(){
      if ( inversionProperty.get() ) {
         return horizontalRadiusProperty.negate().doubleValue();
      } else {
         return horizontalRadiusProperty.doubleValue();
      }
   }//End Method
   
   /**
    * Method to take the configuration of the {@link EllipticPolygon} and calculate the 
    * vertical radius.
    * @return the radius to use in points calculations.
    */
   private double calculateAppliedVerticalRadius(){
      if ( inversionProperty.get() ) {
         return verticalRadiusProperty.negate().doubleValue();
      } else {
         return verticalRadiusProperty.doubleValue();
      }
   }//End Method
   
   /**
    * Getter for the inversion property. This determines whether the {@link EllipticPolygon}s
    * radius' are inverted.
    * @return the inversion {@link BooleanProperty}.
    */
   public BooleanProperty inversionProperty() {
      return inversionProperty;
   }//End Method
   
   /**
    * Getter for the number of fractals property on the {@link EllipticPolygon}. Note that this is only used
    * when the {@link EllipticPolygon} is of the {@link PolygonType#Fractal} type.
    * @return the {@link IntegerProperty}.
    */
   public IntegerProperty numberOfFractalsProperty() {
      return numberOfFractalsProperty;
   }//End Method
}//End Class
