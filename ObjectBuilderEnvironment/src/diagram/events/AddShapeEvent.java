/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.events;

import diagram.layer.Content;
import model.singleton.Singleton;

/**
 * The {@link AddShapeEvent} provides the event and information for adding a shape to
 * the {@link Content} layer.
 */
public class AddShapeEvent {
   
   public final Singleton association;
   public final double xPosition;
   public final double yPosition;
   
   /**
    * Constructs a new {@link AddShapeEvent}.
    * @param association the {@link Singleton} associated.
    * @param xPosition the {@link #xPosition}.
    * @param yPosition the {@link #yPosition}.
    */
   public AddShapeEvent( Singleton association, double xPosition, double yPosition ) {
      this.association = association;
      this.xPosition = xPosition;
      this.yPosition = yPosition;
   }//End Constructor

}//End Class
