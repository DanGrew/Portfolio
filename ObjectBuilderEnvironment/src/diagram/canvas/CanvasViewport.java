/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import architecture.event.EventSystem;
import diagram.layer.Content;
import diagram.shapes.AddShapeEvent;
import diagram.toolbox.ContentEvents;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import math.ShapesAndVectors;

/**
 * The {@link CanvasViewport} provides the viewport to the {@link Content}, essentially
 * providing the camera.
 */
public class CanvasViewport extends Pane {
   
   private Pane contentHolder;

   /**
    * Constructs a new {@link CanvasViewport}.
    * @param content the {@link Node} content of the {@link Pane}.
    */
   CanvasViewport( Pane content ) {
      contentHolder = new Pane( content );
      contentHolder.prefWidthProperty().bind( content.prefWidthProperty() );
      contentHolder.prefHeightProperty().bind( content.prefHeightProperty() );
      getChildren().add( contentHolder );
      setBackground( new Background( new BackgroundFill( Color.WHITE, null, null ) ) );
      
      setOnScroll( event -> {
         ScrollEvent scroll = ( ScrollEvent )event;
         panViewport( scroll.getDeltaX(), scroll.getDeltaY() );
      } );
      setOnZoom( event -> {
         zoomViewport( ( ( ZoomEvent )event ).getZoomFactor() );  
      } );
      
      setOnDragDropped( event -> {
         Point2D scaledPoint = ShapesAndVectors.scaleClick( event.getX(), event.getY(), contentHolder );
         EventSystem.raiseEvent( ContentEvents.AddShape, new AddShapeEvent( 
                  scaledPoint.getX(), scaledPoint.getY()
         ) );
      } );
      setOnDragOver(event -> {
         event.acceptTransferModes( TransferMode.MOVE );
         event.consume();
      });
   }//End Constructor
   
//   void addShapeAtViewportCoordinates( double x, double y ) {
//      Bounds localBounds = contentHolder.getBoundsInLocal();
//      Bounds parentBounds = contentHolder.getBoundsInParent();
//      double proportion = localBounds.getWidth() / parentBounds.getWidth();
//      
//      EventSystem.raiseEvent( ContentEvents.AddShape, new AddShapeEvent( 
//               ( x - contentHolder.getLayoutX() - contentHolder.getTranslateX() - 500 ) * proportion + 500, 
//               ( y - contentHolder.getLayoutY() - contentHolder.getTranslateY() - 500 ) * proportion + 500
//      ) );
//   }
   
   /**
    * Method to pan the viewport by the given values, as changes to current position.
    * @param changeInX the change to apply to the x.
    * @param changeInY the change to apply to the y.
    */
   void panViewport( double changeInX, double changeInY ){
      contentHolder.setTranslateX( contentHolder.getTranslateX() + changeInX );
      contentHolder.setTranslateY( contentHolder.getTranslateY() + changeInY );
   }//End Method
   
   /**
    * Method to zoom the viewport by the given factor.
    * @param factor the factor to zoom by.
    */
   void zoomViewport( double factor ) {
      contentHolder.setScaleX( contentHolder.getScaleX() * factor );
      contentHolder.setScaleY( contentHolder.getScaleY() * factor );
   }//End Method
}//End Class
