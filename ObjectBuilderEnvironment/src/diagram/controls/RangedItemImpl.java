/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import java.util.function.Consumer;

import javafx.scene.Node;

/**
 * Implementation of {@link GridItem} to provide a base for properties that are ranged.
 */
public abstract class RangedItemImpl implements GridItem {
   
   private double min;
   private double max;
   private Consumer< Double > action;
   
   /**
    * Constructs a new {@link RangedItemImpl}.
    * @param action the {@link Consumer} to run the associated control changes value.
    */
   public RangedItemImpl( Consumer< Double > action ) {
      this.action = action;
      min = 0;
      max = 100;
   }//End Constructor
   
   /**
    * Method to set the range for the control.
    * @param min the minimum.
    * @param max the maximum.
    * @return this.
    */
   public RangedItemImpl setRange( double min, double max ) {
      this.min = min;
      this.max = max;
      return this;
   }//End Method
   
   /**
    * Getter for the minimum value.
    * @return the minimum value.
    */
   protected double getMin() {
      return min;
   }//End Method
   
   /**
    * Getter for the maximum value.
    * @return the maximum value.
    */
   protected double getMax() {
      return max;
   }//End Method
   
   /**
    * Method to perform the action associated with the {@link RangedItemImpl}.
    */
   protected void processAction( double propertyValue ){
      action.accept( propertyValue );
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public abstract Node constructNodeController();

}//End Class
