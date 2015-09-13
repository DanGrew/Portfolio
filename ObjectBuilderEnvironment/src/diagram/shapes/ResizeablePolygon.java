/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 * The {@link ResizeablePolygon} provides a {@link Polygon} that is centred with horizontal
 * and vertical radius' where any number of points can be used to determine the shape which are
 * defined by the ellipse created by the radius'.
 */
public class ResizeablePolygon extends Polygon{
   
   private static final double DEFAULT_RADIUS = 100;
   private DoubleProperty numberOfSidesProperty;
   private DoubleProperty centreXProperty;
   private DoubleProperty centreYProperty;
   private DoubleProperty horizontalRadiusProperty;
   private DoubleProperty verticalRadiusProperty;
   
   /**
    * Method to provide the default radius of the {@link Polygon}.
    * @return the default radius.
    */
   public static final double getDefaultRadius(){
      return DEFAULT_RADIUS;
   }//End Method
   
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
    * Constructs a new {@link ResizeablePolygon}.
    * @param numberOfSides the number of sides in the {@link Polygon}.
    * @param centrePositionX the centre x of the {@link Polygon}.
    * @param centrePositionY the centre y of the {@link Polygon}.
    */
   public ResizeablePolygon( int numberOfSides, double centrePositionX, double centrePositionY ) {
      this( numberOfSides, centrePositionX, centrePositionY, DEFAULT_RADIUS, DEFAULT_RADIUS );
   }//End Constructor
   
   /**
    * Constructs a new {@link ResizeablePolygon}.
    * @param numberOfSides the number of sides in the {@link Polygon}.
    * @param centrePositionX the centre x of the {@link Polygon}.
    * @param centrePositionY the centre y of the {@link Polygon}.
    * @param horizontalRadius the horizontal radius.
    * @param verticalRadius the vertical radius.
    */
   public ResizeablePolygon( 
            int numberOfSides, 
            double centrePositionX, 
            double centrePositionY, 
            double horizontalRadius, 
            double verticalRadius 
   ) {
      numberOfSidesProperty = new SimpleDoubleProperty( numberOfSides );
      centreXProperty = new SimpleDoubleProperty( centrePositionX );
      centreYProperty = new SimpleDoubleProperty( centrePositionY );
      horizontalRadiusProperty = new SimpleDoubleProperty( horizontalRadius );
      horizontalRadiusProperty.addListener( ( change, oldValue, newValue ) -> calculatePoints() );
      verticalRadiusProperty = new SimpleDoubleProperty( verticalRadius );
      verticalRadiusProperty.addListener( ( change, oldValue, newValue ) -> calculatePoints() );
      calculatePoints();
   }//End Constructor
   
   /**
    * Method to provide the calculation for the points in the {@link Polygon}.
    */
   private void calculatePoints(){
      getPoints().clear();
      for ( int i = 0; i < numberOfSidesProperty.doubleValue(); i++ ) {
         double angle = ( 360.0 / numberOfSidesProperty.doubleValue() ) * i;
         Point2D point = calculatePointOnCircle( 
                  centreXProperty.doubleValue(), 
                  centreYProperty.doubleValue(), 
                  horizontalRadiusProperty.doubleValue(), 
                  verticalRadiusProperty.doubleValue(), 
                  angle 
         );
         getPoints().addAll( point.getX(), point.getY() );
      }
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
}//End Class
