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
   
   private int numberOfSides;
   
   /**
    * Constructs a new {@link DiagramSettings}.
    */
   public DiagramSettings() {
      numberOfSides = 4;
   }//End Constructor

   /**
    * Getter for the number of sides in the shape being drawn.
    * @return the number of sides.
    */
   public int getNumberOfSides() {
      return numberOfSides;
   }//End Method

   /**
    * Setter for the number of sides in the shape to be drawn.
    * @param numberOfSides the number of sides..
    */
   public void setNumberOfSides( int numberOfSides ) {
      this.numberOfSides = numberOfSides;
   }//End Method
   
}//End Class
