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

/**
 * The {@link ResizePoint} provides a small {@link Circle} that can be used to 
 * resize {@link SidedPolygon}s.
 */
public class ResizePoint extends Circle {
   
   /**
    * The {@link HorizontalRadiusUpdater} is responsible for recalculating the {@link SidedPolygon#horizontalRadiusProperty()}
    * when the associated event triggers.
    */
   private class HorizontalRadiusUpdater implements ChangeListener< Number > {
      
      private SidedPolygon polygon;
      
      /**
       * Constructs a new {@link HorizontalRadiusUpdater}.
       * @param polygon the {@link SidedPolygon} to update.
       */
      private HorizontalRadiusUpdater( SidedPolygon polygon ) {
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
    * The {@link VerticalRadiusUpdater} is responsible for recalculating the {@link SidedPolygon#verticalRadiusProperty()}
    * when the associated event triggers.
    */
   private class VerticalRadiusUpdater implements ChangeListener< Number > {
      
      private SidedPolygon polygon;
      
      /**
       * Constructs a new {@link VerticalRadiusUpdater}.
       * @param polygon the {@link SidedPolygon} to update.
       */
      private VerticalRadiusUpdater( SidedPolygon polygon ) {
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
    * Constructs a new {@link ResizePoint} for the given {@link SidedPolygon}, located and the bottom right of the
    * {@link Bounds}.
    * @param node the {@link SidedPolygon} associated.
    */
   public ResizePoint( SidedPolygon node ) {
      super( 4 );
      setCenterX( node.centreXProperty().doubleValue() + node.getTranslateX() + node.horizontalRadiusProperty().doubleValue() );
      setCenterY( node.centreYProperty().doubleValue() + node.getTranslateY() + node.verticalRadiusProperty().doubleValue() );
      setFill( Color.ORANGE );
      setStroke( Color.ORANGE );
      
      ContentDragBehaviour dragBehaviour = new ContentDragBehaviour();
      dragBehaviour.registerForDragOperations( this );
      
      node.translateXProperty().addListener( ( change, old, updated ) -> {
         setCenterX( node.centreXProperty().doubleValue() + node.getTranslateX() + node.horizontalRadiusProperty().doubleValue() );
         translateXProperty().set( 0.0 );
      } );
      node.translateYProperty().addListener( ( change, old, updated ) -> {
         setCenterY( node.centreYProperty().doubleValue() + node.getTranslateY() + node.verticalRadiusProperty().doubleValue() );
         translateYProperty().set( 0.0 );
      } );
      
      translateXProperty().addListener( new HorizontalRadiusUpdater( node ) );
      translateYProperty().addListener( new VerticalRadiusUpdater( node ) );
      
      setOnMouseEntered( event -> {
         this.getParent().getScene().setCursor( Cursor.SE_RESIZE );
      } );
      setOnMouseExited( event -> {
         this.getParent().getScene().setCursor( Cursor.DEFAULT );
      } );
   }//End Constructor
   
}//End Class
