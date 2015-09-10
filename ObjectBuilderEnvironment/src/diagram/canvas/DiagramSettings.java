/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

/**
 * The {@link DiagramSettings} provides a object that is configurable by the system, where the system
 * can interact with this object in any state.
 */
public class DiagramSettings {
   
   private DiagramShapes shape;
   
   /**
    * Constructs a new {@link DiagramSettings}.
    */
   public DiagramSettings() {
      shape = DiagramShapes.Rectangle;
   }//End Constructor

   /**
    * Getter for the {@link DiagramShapes} to be drawn.
    * @return the shape the {@link DiagramShapes}.
    */
   public DiagramShapes getShape() {
      return shape;
   }//End Method

   /**
    * Setter for the {@link DiagramShapes} to draw.
    * @param shape the shape to set.
    */
   public void setShape( DiagramShapes shape ) {
      this.shape = shape;
   }//End Method
   
}//End Class
