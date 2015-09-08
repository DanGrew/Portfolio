/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import diagram.toolbox.ContentEvents;
import graphics.event.JavaFxEventSystem;
import javafx.scene.Node;

/**
 * The {@link InformationLayerController} is responsible for controlling the {@link InformationLayer}.
 */
public class InformationLayerController {
   
   private InformationLayer informationLayer;
   
   /**
    * Constructs a new {@link InformationLayerController}.
    * @param layer the {@link InformationLayer} associated.
    */
   InformationLayerController( InformationLayer layer ) {
      this.informationLayer = layer;
      
      JavaFxEventSystem.registerForEvent( ContentEvents.SelectNode, ( event, source ) -> handleSelection( ( Node )source ) );
   }//End Constructor

   /**
    * Method to handle the selection of the given {@link Node}.
    * @param node the {@link Node} to select.
    */
   private void handleSelection( Node node ) {
      informationLayer.selectNode( node );
   }//End Method
   
}//End Class
