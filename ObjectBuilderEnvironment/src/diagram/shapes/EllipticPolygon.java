/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;

/**
 * The {@link EllipticPolygon} provides a {@link Polygon} that it centred on an {@link Ellipse}
 * with an adjustable horizontal and vertical radius for behaviour like an {@link Ellipse}.
 */
public interface EllipticPolygon {

   /**
    * Getter for the {@link DoubleProperty} of the centre x position.
    * @return the {@link DoubleProperty}.
    */
   public DoubleProperty centreXProperty();
   
   /**
    * Getter for the {@link DoubleProperty} of the centre y position.
    * @return the {@link DoubleProperty}.
    */
   public DoubleProperty centreYProperty();
   
   /**
    * Getter for the {@link DoubleProperty} of the horizontal radius.
    * @return the {@link DoubleProperty}.
    */
   public DoubleProperty horizontalRadiusProperty();
   
   /**
    * Getter for the {@link DoubleProperty} of the vertical radius.
    * @return the {@link DoubleProperty}.
    */
   public DoubleProperty verticalRadiusProperty();

   /**
    * Getter for the number of sides in the {@link SidedPolygon}.
    * @return the number of sides in the shape.
    */
   public int getNumberOfSides();
}//End Interface
