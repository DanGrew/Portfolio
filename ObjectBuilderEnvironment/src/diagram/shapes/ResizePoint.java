/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import diagram.layer.ContentDragBehaviour;
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
      
      private Ellipse polygon;
      
      /**
       * Constructs a new {@link HorizontalRadiusUpdater}.
       * @param polygon the {@link Ellipse} to update.
       */
      private HorizontalRadiusUpdater( Ellipse polygon ) {
         this.polygon = polygon;
      }//End Constructor

      /**
       * {@inheritDoc}
       */
      @Override public void changed( ObservableValue< ? extends Number > observable, Number oldValue, Number newValue ) {
         double radius = (
                  ( getCenterX() + getTranslateX() ) 
                     - 
                  ( polygon.centerXProperty().doubleValue() + polygon.translateXProperty().doubleValue() ) 
         );
         polygon.radiusXProperty().set( Math.abs( radius ) );
      }//End Method
      
   }//End Class
   
   /**
    * The {@link VerticalRadiusUpdater} is responsible for recalculating the {@link EllipticPolygon#verticalRadiusProperty()}
    * when the associated event triggers.
    */
   private class VerticalRadiusUpdater implements ChangeListener< Number > {
      
      private Ellipse polygon;
      
      /**
       * Constructs a new {@link VerticalRadiusUpdater}.
       * @param polygon the {@link Ellipse} to update.
       */
      private VerticalRadiusUpdater( Ellipse polygon ) {
         this.polygon = polygon;
      }//End Constructor

      /**
       * {@inheritDoc}
       */
      @Override public void changed( ObservableValue< ? extends Number > observable, Number oldValue, Number newValue ) {
         double radius = (
                  ( getCenterY() + getTranslateY() ) 
                     - 
                  ( polygon.centerYProperty().doubleValue() + polygon.translateYProperty().doubleValue() ) 
         );
         polygon.radiusYProperty().set( Math.abs( radius ) );
      }//End Method
      
   }//End Class
   
   /**
    * Constructs a new {@link ResizePoint} for the given {@link Ellipse}, located and the bottom right of the
    * {@link Bounds}.
    * @param polygon the {@link Ellipse} associated.
    */
   public ResizePoint( Ellipse polygon ) {
      super( 4 );
      resetCentreX( polygon );
      resetCentreY( polygon );
      setFill( Color.LAWNGREEN );
      setStroke( Color.LAWNGREEN );
      
      ContentDragBehaviour dragBehaviour = new ContentDragBehaviour();
      dragBehaviour.registerForDragOperations( this );
      
      polygon.translateXProperty().addListener( ( change, old, updated ) -> {
         resetCentreX( polygon );
      } );
      polygon.translateYProperty().addListener( ( change, old, updated ) -> {
         resetCentreY( polygon );
      } );
      
      centerXProperty().addListener( new HorizontalRadiusUpdater( polygon ) );
      centerYProperty().addListener( new VerticalRadiusUpdater( polygon ) );
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
    * Method to reset the centre based on the {@link Ellipse}.
    * @param polygon the {@link Ellipse} used to calculate the centre x of this {@link Ellipse}.
    */
   private void resetCentreX( Ellipse polygon ){
      setCenterX( polygon.centerXProperty().doubleValue() + polygon.getTranslateX() + polygon.radiusXProperty().doubleValue() );
      translateXProperty().set( 0.0 );
   }//End Method
   
   /**
    * Method to reset the centre based on the {@link Ellipse}.
    * @param polygon the {@link Ellipse} used to calculate the centre y of this {@link Ellipse}.
    */
   private void resetCentreY( Ellipse polygon ) {
      setCenterY( polygon.centerYProperty().doubleValue() + polygon.getTranslateY() + polygon.radiusYProperty().doubleValue() );
      translateYProperty().set( 0.0 );
   }//End Method
   
}//End Class
