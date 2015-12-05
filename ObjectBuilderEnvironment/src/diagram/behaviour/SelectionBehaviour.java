/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.behaviour;

import architecture.event.EventSystem;
import diagram.events.SelectShapesEvent;
import diagram.layer.Content;
import diagram.shapes.CanvasShape;
import diagram.toolbox.ContentEvents;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * {@link SelectionBehaviour} is responsible for handling selection of items in the {@link Content}.
 */
public class SelectionBehaviour {

   /**
    * Method to register the given {@link Node} as a selectable item.
    * @param selectable the {@link Node} that can be selected.
    * @param subject the {@link CanvasShape} subject associated.
    */
   public void registerForSelectionBehaviour( Node selectable, CanvasShape subject ) {
      selectable.addEventFilter( MouseEvent.MOUSE_PRESSED, event -> {
         switch ( event.getButton() ) {
            case MIDDLE:
               break;
            case NONE:
               break;
            case PRIMARY:
               EventSystem.raiseEvent( ContentEvents.SelectShapes, new SelectShapesEvent( subject ) );
               break;
            case SECONDARY:
               EventSystem.raiseEvent( ContentEvents.DeselectShapes, new SelectShapesEvent( subject ) );
               break;
            default:
               break;
         }
      } );
   }//End Method
}//End Class
