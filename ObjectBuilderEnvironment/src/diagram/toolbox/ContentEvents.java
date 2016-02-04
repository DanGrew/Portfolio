/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.toolbox;

/**
 * {@link Enum} defining the events that can be received by the {@link Content}
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
   /** Select the {@link CanvasShape}s associated with the event. **/
   SelectShapes, 
   /** Deselect the {@link CanvasShape}s associated with the event. **/
   DeselectShapes,
   /** Select the {@link Singleton}s associated with the event. **/
   SelectSingletons, 
   /** Deselect the {@link Singleton}s associated with the event. **/
   DeselectSingletons, 
   /** Add a shape at the given position, {@link AddShapeEvent} given. **/
   AddShape 

}//End Enum
