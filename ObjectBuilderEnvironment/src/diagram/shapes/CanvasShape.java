/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import javafx.beans.property.DoubleProperty;

/**
 * Th {@link CanvasShape} provides a wrapper for all types of shape in the
 * {@link DiagramCanvas}.
 */
public interface CanvasShape {

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
    * {@link Polygon#translateXProperty()}
    */
   public DoubleProperty translateXProperty();

   /**
    * {@link Polygon#translateYProperty()}
    */
   public DoubleProperty translateYProperty();

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
    * {@link Polygon#rotateProperty()}
    */
   public DoubleProperty rotateProperty();
}//End Interface
