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
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
   CanvasViewport( Node content ) {
      contentHolder = new Pane( content );
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
         EventSystem.raiseEvent( ContentEvents.AddShape, new AddShapeEvent( event.getX(), event.getY() ) );
      } );
      setOnDragOver(event -> {
         event.acceptTransferModes( TransferMode.MOVE );
         event.consume();
      });
   }//End Constructor
   
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
