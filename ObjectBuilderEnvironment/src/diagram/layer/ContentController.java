/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import diagram.shapes.AddShapeEvent;
import diagram.shapes.EllipticPolygon;
import diagram.toolbox.ContentEvents;
import graphics.event.JavaFxEventSystem;
import javafx.scene.input.ZoomEvent;

/**
 * The {@link ContentController} is responsible for controlling the {@link Content} and
 * providing a decoupling via events. The events are received here and pushed into the {@link Content}
 * via the appropriate methods on its interface.
 */
public class ContentController {
   
   private Content contentLayer;
   
   /**
    * Constructs a new {@link ContentController}.
    * @param layer the {@link Content} being controlled.
    */
   ContentController( Content layer ) {
      this.contentLayer = layer;
      
      JavaFxEventSystem.registerForEvent( ContentEvents.SelectNode, ( event, source ) -> handleSelection( ( EllipticPolygon )source ) );
      JavaFxEventSystem.registerForEvent( ContentEvents.AddShape, ( event, source ) -> handleAddShape( ( AddShapeEvent )source ) );
   }//End Constructor
   
   /**
    * Method to handle adding a shape to the {@link Content}.
    * @param event the {@link AddShapeEvent}.
    */
   private void handleAddShape( AddShapeEvent event ){
      contentLayer.addShape( event.xPosition, event.yPosition );
   }//End Method
   
   /**
    * Method to handle the selection of the given {@link EllipticPolygon}.
    * @param node the {@link EllipticPolygon} to select.
    */
   private void handleSelection( EllipticPolygon node ) {
      contentLayer.selectNode( node );
   }//End Method
   
}//End Class
