/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.function.threshold;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import model.singleton.Neuron;
import model.singleton.Synapse;

/**
 * The {@link ThresholdFunction} is responsible for defining the base of all threshold
 * functions used to calculate action potential of a {@link Neuron}.
 */
public abstract class ThresholdFunction {

   /** Constant defining the threshold for firing the {@link Neuron}. **/
   private static final int THRESHOLD = 0;
   /** The calculated output based on the input from associated {@link Neuron}s and
    * {@link Synapse}s. **/
   protected DoubleProperty output;

   /**
    * Constructs a new {@link ThresholdFunction}.
    */
   protected ThresholdFunction() {
      this( 0.0 );
   }// End Method

   /**
    * Constructs a new {@link ThresholdFunction}.
    * @param output the precalculated output, or input.
    */
   protected ThresholdFunction( Double output ) {
      this.output = new SimpleDoubleProperty( output );
   }// End Constructor

   /**
    * Method to process the firing of an associated {@link Synapse} that provides
    * the weighted output of the input {@link Neuron}.
    * @param output the weighted output.
    */
   public abstract void synapseFired( double output );

   /**
    * Method to determine whether the input has exceeded the threshold.
    * @return true if exceeded, false otherwise.
    */
   public boolean excedesThreshold() {
      return outputExceedsThreshold( output.get() );
   }// End Method

   /**
    * Method to determine if the given output exceeds the threshold.
    * @param output the output to compare against the threshold.
    * @return true if exceeded, false otherwise.
    */
   protected boolean outputExceedsThreshold( Double output ){
      if ( output == null ){
         return false;
      } else {
         return output >= THRESHOLD;
      }
   }// End Method

   /**
    * Method to set the output of the function. This can only be accessed in the hierarchy
    * following calculations.
    * @param output the output calculated.
    */
   public void setOutput( Double output ) {
      if ( output != null ){
         this.output.set( output );
      }
   }// End Method

   /**
    * Method to get the output from this function.
    * @return the output calculated from the inputs.
    */
   public Double getOutput() {
      return output.get();
   }// End Method
   
   /** 
    * Method to get the {@link Property} of the output value of this {@link ThresholdFunction}, used
    * for receiving events when the value changes.
    * @return the {@link DoubleProperty} for the output value.
    */
   public DoubleProperty getOutputProperty(){
      return output;
   }// End Method

   /**
    * Method to calculate the output of this threshold and consequently, associated {@link Neuron}.
    */
   public abstract void calculateOutput();

}// End Class
