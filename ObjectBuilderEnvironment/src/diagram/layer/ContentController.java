/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import diagram.shapes.SidedPolygon;
import diagram.toolbox.ContentEvents;
import graphics.event.JavaFxEventSystem;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;

/**
 * The {@link ContentController} is responsible for controlling the {@link Content} and
 * providing a decoupling via events. The events are received here and pushed into the {@link Content}
 * via the appropriate methods on its interface.
 */
public class ContentController {
   
   private static final double PAN_SPEED = 50;
   private Content contentLayer;
   
   /**
    * Constructs a new {@link ContentController}.
    * @param layer the {@link Content} being controlled.
    */
   ContentController( Content layer ) {
      this.contentLayer = layer;
      JavaFxEventSystem.registerForEvent( ContentEvents.ZoomIn, ( event, source ) -> {
         handleZoomIn();
      } );
      JavaFxEventSystem.registerForEvent( ContentEvents.ZoomOut, ( event, source ) -> {
         handleZoomOut();
      } );
      JavaFxEventSystem.registerForEvent( ContentEvents.ZoomEvent, ( event, source ) -> {
         handleZoom( ( ( ZoomEvent )source ).getZoomFactor() );
      } );
      
      JavaFxEventSystem.registerForEvent( ContentEvents.PanEvent, ( event, source ) -> {
         handlePan( ( ScrollEvent )source );
      } );
      JavaFxEventSystem.registerForEvent( ContentEvents.PanUp, ( event, source ) -> {
         handlePan( 0, -PAN_SPEED );
      } );
      JavaFxEventSystem.registerForEvent( ContentEvents.PanDown, ( event, source ) -> {
         handlePan( 0, PAN_SPEED );
      } );
      JavaFxEventSystem.registerForEvent( ContentEvents.PanRight, ( event, source ) -> {
         handlePan( PAN_SPEED, 0 );
      } );
      JavaFxEventSystem.registerForEvent( ContentEvents.PanLeft, ( event, source ) -> {
         handlePan( -PAN_SPEED, 0 );
      } );
      JavaFxEventSystem.registerForEvent( ContentEvents.SelectNode, ( event, source ) -> handleSelection( ( SidedPolygon )source ) );
   }//End Constructor
   
   /**
    * Method to handle the selection of the given {@link SidedPolygon}.
    * @param node the {@link SidedPolygon} to select.
    */
   private void handleSelection( SidedPolygon node ) {
      contentLayer.selectNode( node );
   }//End Method
   
   /**
    * Method to handle a fixed zoom in.
    */
   private void handleZoomIn(){
      handleZoom( 1.25 );
   }//End Method
   
   /**
    * Method to handle a fixed zoom out.
    */
   private void handleZoomOut(){
      handleZoom( 0.8 );
   }//End Method
   
   /**
    * Method to handle a zoom with the given factor to zoom by.
    * @param factor the zoom factor.
    */
   private void handleZoom( double factor ) {
      contentLayer.zoom( factor );
   }//End Method
   
   /**
    * Method to handle the panning from a {@link ScrollEvent}.
    * @param event the {@link ScrollEvent} for the pan.
    */
   private void handlePan( ScrollEvent event ) {
      handlePan( event.getDeltaX(), event.getDeltaY() );
   }//End Method
   
   /**
    * Method to handle the pan of a fixed amount.
    * @param horizontal the horizontal pan amount.
    * @param vertical the vertical pan amount.
    */
   private void handlePan( double horizontal, double vertical ) {
      contentLayer.pan( horizontal, vertical );
   }//End Method

}//End Class