/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.toolbox;

import diagram.layer.ContentLayer;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import object.BuilderObject;

/**
 * {@link Enum} defining the events that can be received by the {@link ContentLayer}
 * for controlling the content.
 */
public enum ContentEvents {
   
   /** Add an object to the {@link ContentLayer}, the {@link BuilderObject} is given. **/
   AddObject, 
   /** Zoom in by a fixed amount. **/
   ZoomIn,
   /** Zoom out by a fixed amount. **/
   ZoomOut,
   /** Zoom in/out using gestures, {@link ZoomEvent} given. **/
   ZoomEvent,
   /** Pan up, moving all items up, by a fixed amount.**/
   PanUp,
   /** Pan down, moving all items up, by a fixed amount.**/
   PanDown,
   /** Pan right, moving all items up, by a fixed amount.**/
   PanRight,
   /** Pan left, moving all items up, by a fixed amount.**/
   PanLeft,
   /** Pan around the content, moving all items, using gestures, {@link ScrollEvent} given.**/
   PanEvent, 
   /** Select the {@link Node} associated with the event. **/
   SelectNode 

}//End Enum
