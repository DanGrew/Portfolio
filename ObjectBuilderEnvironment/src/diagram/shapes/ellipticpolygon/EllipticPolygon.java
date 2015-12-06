/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes.ellipticpolygon;

import diagram.shapes.CanvasShape;
import diagram.shapes.PolygonType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Polygon;

/**
 * The {@link EllipticPolygon} provides a {@link Polygon} that is centred with horizontal
 * and vertical radius' where any number of points can be used to determine the shape which are
 * defined by the ellipse created by the radius'.
 */
public class EllipticPolygon extends Polygon implements CanvasShape {
   
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
      rotateProperty().set( builder.getRotateProperty() );
      calculatePoints();
   }//End Constructor
   
   /**
    * Method to provide the calculation for the points in the {@link Polygon}.
    */
   public void calculatePoints(){
      getPoints().clear();
      switch ( polygonTypeProperty.get() ) {
         case Fractal:
            getPoints().addAll( EllipticPolygonCalculations.calculateFractals( this ) );
            break;
         case Regular:
            getPoints().addAll( EllipticPolygonCalculations.calculateSidePoints( this, 0 ) );
            break;
         case Starred:
            innerPolygon = new EllipticPolygon(
                  new EllipticPolygonBuilder( PolygonType.Regular, getNumberOfSides() )
                     .centreXProperty( centreXProperty().doubleValue() )
                     .centreYProperty( centreYProperty().doubleValue() )
                     .horizontalRadiusProperty( horizontalRadiusProperty().doubleValue() / 2 )
                     .verticalRadiusProperty( horizontalRadiusProperty().doubleValue() / 2 )
            );
            getPoints().addAll( EllipticPolygonCalculations.calculateWithInnerPolygon( this, innerPolygon ) );
            break;
         default:
            break;
      }
   }//End Method
   
   /**
    * Method to get the {@link PolygonType} associated with this type of {@link EllipticPolygon}.
    * @return the {@link PolygonType}.
    */
   public ObjectProperty< PolygonType > polygonTypeProperty(){
      return polygonTypeProperty;
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
