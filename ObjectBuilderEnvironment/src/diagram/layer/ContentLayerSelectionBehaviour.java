/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import architecture.event.EventSystem;
import diagram.toolbox.ContentEvents;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * {@link ContentLayerSelectionBehaviour} is responsible for handling selection of items in the {@link ContentLayer}.
 */
public class ContentLayerSelectionBehaviour {

   /**
    * Method to register the given {@link Node} as a selectable item.
    * @param node the {@link Node} that can be selected.
    */
   void registerForSelectionBehaviour( Node node ) {
      node.addEventFilter( MouseEvent.MOUSE_PRESSED, event -> {
        EventSystem.raiseEvent( ContentEvents.SelectNode, node );
      } );
   }//End Method
}//End Class
