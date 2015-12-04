/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import diagram.shapes.CanvasShape;

/**
 * Defines an event for defining a collection of selected or deselected {@link CanvasShape}s.
 */
public class SelectShapesEvent {
   
   public final List< CanvasShape > selectedShapes;
   
   /**
    * Constructs a new {@link SelectShapesEvent}.
    * @param selected the {@link CanvasShape}s associated.
    */
   public SelectShapesEvent( CanvasShape... selected ) {
      this( Arrays.asList( selected ) );
   }//End Constructor
   
   /**
    * Constructs a new {@link SelectShapesEvent}.
    * @param selected a {@link List} of {@link CanvasShape}s associatdd.
    */
   public SelectShapesEvent( List< CanvasShape > selected ) {
      this.selectedShapes = new ArrayList<>( selected );
   }//End Constructor

}//End Class
