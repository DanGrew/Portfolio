/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import diagram.layer.ContentDragBehaviour;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

/**
 * The {@link ResizePoint} provides a small {@link Circle} that can be used to 
 * resize {@link EllipticPolygon}s.
 */
public class ResizePoint extends Circle {
   
   /**
    * The {@link HorizontalRadiusUpdater} is responsible for recalculating the {@link EllipticPolygon#horizontalRadiusProperty()}
    * when the associated event triggers.
    */
   private class HorizontalRadiusUpdater implements ChangeListener< Number > {
      
      private EllipticPolygon polygon;
      
      /**
       * Constructs a new {@link HorizontalRadiusUpdater}.
       * @param polygon the {@link EllipticPolygon} to update.
       */
      private HorizontalRadiusUpdater( EllipticPolygon polygon ) {
         this.polygon = polygon;
      }//End Constructor

      /**
       * {@inheritDoc}
       */
      @Override public void changed( ObservableValue< ? extends Number > observable, Number oldValue, Number newValue ) {
         double radius = (
                  ( getCenterX() + getTranslateX() ) 
                     - 
                  ( polygon.centreXProperty().doubleValue() + polygon.translateXProperty().doubleValue() ) 
         );
         polygon.horizontalRadiusProperty().set( radius );
      }//End Method
      
   }//End Class
   
   /**
    * The {@link VerticalRadiusUpdater} is responsible for recalculating the {@link EllipticPolygon#verticalRadiusProperty()}
    * when the associated event triggers.
    */
   private class VerticalRadiusUpdater implements ChangeListener< Number > {
      
      private EllipticPolygon polygon;
      
      /**
       * Constructs a new {@link VerticalRadiusUpdater}.
       * @param polygon the {@link EllipticPolygon} to update.
       */
      private VerticalRadiusUpdater( EllipticPolygon polygon ) {
         this.polygon = polygon;
      }//End Constructor

      /**
       * {@inheritDoc}
       */
      @Override public void changed( ObservableValue< ? extends Number > observable, Number oldValue, Number newValue ) {
         double radius = (
                  ( getCenterY() + getTranslateY() ) 
                     - 
                  ( polygon.centreYProperty().doubleValue() + polygon.translateYProperty().doubleValue() ) 
         );
         polygon.verticalRadiusProperty().set( radius );
      }//End Method
      
   }//End Class
   
   /**
    * Constructs a new {@link ResizePoint} for the given {@link EllipticPolygon}, located and the bottom right of the
    * {@link Bounds}.
    * @param polygon the {@link EllipticPolygon} associated.
    */
   public ResizePoint( EllipticPolygon polygon ) {
      super( 4 );
      resetCentreX( polygon );
      resetCentreY( polygon );
      setFill( Color.ORANGE );
      setStroke( Color.ORANGE );
      
      ContentDragBehaviour dragBehaviour = new ContentDragBehaviour();
      dragBehaviour.registerForDragOperations( this );
      
      polygon.translateXProperty().addListener( ( change, old, updated ) -> {
         resetCentreX( polygon );
      } );
      polygon.translateYProperty().addListener( ( change, old, updated ) -> {
         resetCentreY( polygon );
      } );
      
      translateXProperty().addListener( new HorizontalRadiusUpdater( polygon ) );
      translateYProperty().addListener( new VerticalRadiusUpdater( polygon ) );
      
      setOnMouseEntered( event -> {
         this.getParent().getScene().setCursor( Cursor.SE_RESIZE );
      } );
      setOnMouseExited( event -> {
         this.getParent().getScene().setCursor( Cursor.DEFAULT );
      } );
   }//End Constructor
   
   /**
    * Method to reset the centre based on the {@link EllipticPolygon}.
    * @param polygon the {@link EllipticPolygon} used to calculate the centre x of this {@link Ellipse}.
    */
   private void resetCentreX( EllipticPolygon polygon ){
      setCenterX( polygon.centreXProperty().doubleValue() + polygon.getTranslateX() + polygon.horizontalRadiusProperty().doubleValue() );
      translateXProperty().set( 0.0 );
   }//End Method
   
   /**
    * Method to reset the centre based on the {@link EllipticPolygon}.
    * @param polygon the {@link EllipticPolygon} used to calculate the centre y of this {@link Ellipse}.
    */
   private void resetCentreY( EllipticPolygon polygon ) {
      setCenterY( polygon.centreYProperty().doubleValue() + polygon.getTranslateY() + polygon.verticalRadiusProperty().doubleValue() );
      translateYProperty().set( 0.0 );
   }//End Method
   
}//End Class
