/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import diagram.events.AddShapeEvent;
import diagram.events.SelectShapesEvent;
import diagram.shapes.CanvasShape;
import diagram.toolbox.ContentEvents;
import graphics.event.JavaFxEventSystem;

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
      
      JavaFxEventSystem.registerForEvent( ContentEvents.SelectShapes, ( event, source ) -> handleSelection( ( SelectShapesEvent )source ) );
      JavaFxEventSystem.registerForEvent( ContentEvents.DeselectShapes, ( event, source ) -> handleDeselection( ( SelectShapesEvent )source ) );
      JavaFxEventSystem.registerForEvent( ContentEvents.AddShape, ( event, source ) -> handleAddShape( ( AddShapeEvent )source ) );
      
   }//End Constructor
   
   /**
    * Method to handle adding a shape to the {@link Content}.
    * @param event the {@link AddShapeEvent}.
    */
   private void handleAddShape( AddShapeEvent event ){
      contentLayer.addShape( event.association, event.xPosition, event.yPosition );
   }//End Method
   
   /**
    * Method to handle the selection of the given {@link CanvasShape}.
    * @param event the {@link SelectShapesEvent}.
    */
   private void handleSelection( SelectShapesEvent event ) {
      for ( CanvasShape shape : event.selectedShapes ) {
         contentLayer.select( shape );
      }
   }//End Method
   
   /**
    * Method to handle the deselection of the given {@link CanvasShape}.
    * @param event the {@link SelectShapesEvent}.
    */
   private void handleDeselection( SelectShapesEvent event ) {
      for ( CanvasShape shape : event.selectedShapes ) {
         contentLayer.deselect( shape );
      }
   }//End Method
   
}//End Class
