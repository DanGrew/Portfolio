/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import diagram.layer.Content;

/**
 * The {@link AddShapeEvent} provides the event and information for adding a shape to
 * the {@link Content} layer.
 */
public class AddShapeEvent {
   
   /** X position to add the shape at.**/
   public final double xPosition;
   /** Y position to add the shape at.**/
   public final double yPosition;
   
   /**
    * Constructs a new {@link AddShapeEvent}.
    * @param xPosition the {@link #xPosition}.
    * @param yPosition the {@link #yPosition}.
    */
   public AddShapeEvent( double xPosition, double yPosition ) {
      this.xPosition = xPosition;
      this.yPosition = yPosition;
   }//End Constructor

}//End Class
