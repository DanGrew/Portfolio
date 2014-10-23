/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.function;

import model.singleton.Neuron;
import model.singleton.Synapse;

/**
 * The {@link ThresholdFunction} is responsible for defining the base of all threshold
 * functions used to calculate action potential of a {@link Neuron}.
 */
public abstract class ThresholdFunction {

   /** The threshold of the function, that needs to be exceeded for the {@link Neuron}
    * to reach its action potential.**/
   protected Double threshold;
   /** The calculated output based on the input from associated {@link Neuron}s and
    * {@link Synapse}s. **/
   protected Double output;

   /**
    * Constructs a new {@link ThresholdFunction}.
    */
   protected ThresholdFunction() {
      this( null, null );
   }// End Method

   /**
    * Constructs a new {@link ThresholdFunction}.
    * @param threshold the threshold for the function.
    * @param output the precalculated output, or input.
    */
   protected ThresholdFunction( Double threshold, Double output ) {
      this.threshold = threshold;
      this.output = output;
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
      return output >= threshold;
   }// End Method

   /**
    * Method to set the threshold of the {@link ThresholdFunction}
    * @param threshold the threshold to exceed.
    */
   public void setThreshold( double threshold ){
      this.threshold = threshold;
>>>>>>> branch 'master' of https://github.com/DanGrew/Portfolio.git
   }// End Method

   /**
    * Method to set the output of the function. This can only be accessed in the hierarchy
    * following calculations.
    * @param output the output calculated.
    */
   protected void setOutput( Double output ) {
      this.output = output;
   }// End Method

   /**
    * Method to get the output from this function.
    * @return the output calculated from the inputs.
    */
   public Double getOutput() {
      return output;
   }// End Method

   /**
    * Method to calculate the output of this threshold and consequently, associated {@link Neuron}.
    */
   public abstract void calculateOutput();

}// End Class
