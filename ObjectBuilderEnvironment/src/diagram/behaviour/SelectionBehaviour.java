/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.behaviour;

import architecture.event.EventSystem;
import diagram.layer.Content;
import diagram.toolbox.ContentEvents;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * {@link SelectionBehaviour} is responsible for handling selection of items in the {@link Content}.
 */
public class SelectionBehaviour {

   /**
    * Method to register the given {@link Node} as a selectable item.
    * @param node the {@link Node} that can be selected.
    */
   public void registerForSelectionBehaviour( Node node ) {
      node.addEventFilter( MouseEvent.MOUSE_PRESSED, event -> {
        EventSystem.raiseEvent( ContentEvents.SelectNode, node );
      } );
   }//End Method
}//End Class
