/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import diagram.shapes.PolygonType;

/**
 * The {@link DiagramSettings} provides a object that is configurable by the system, where the system
 * can interact with this object in any state.
 */
public class DiagramSettings {
   
   private PolygonType polygonType;
   private int numberOfSides;
   
   /**
    * Constructs a new {@link DiagramSettings}.
    */
   public DiagramSettings() {
      numberOfSides = 4;
      polygonType = PolygonType.Regular;
   }//End Constructor

   /**
    * Getter for the {@link PolygonType} to draw.
    * @return the {@link PolygonType}.
    */
   public PolygonType getPolygonType() {
      return polygonType;
   }//End Method
   
   /**
    * Setter for the {@link PolygonType}.
    * @param polygonType the {@link PolygonType} to set.
    */
   public void setPolygonType( PolygonType polygonType ) {
      this.polygonType = polygonType;
   }//End Method
   
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
