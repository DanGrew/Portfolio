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
 * The {@link McCullochPittsFunction} defines the McCullock Pitts {@link Neuron}
 * equation.
 */
public class McCullochPittsFunction extends ThresholdFunction {

   /** Constant defining the output value when the threshold is exceeded. **/
   private static final double FUNCTION_OUTPUT_ACTION_POTENTIAL = 1;
   /** Constant defining the output value when the threshold has not been exceeded. **/
   private static final double FUNCTION_OUTPUT_RESTING_POTENTIAL = 0;
   /** The total input received from {@link Synapse}s associated with the {@link Neuron}
    * using this {@link ThresholdFunction}.**/
   private double inputTotal = 0;

   /**
    * Constructs a new {@link McCullochPittsFunction}.
    */
   public McCullochPittsFunction() {
      super();
   }// End Constructor

   /**
    * Constructs a new {@link McCullochPittsFunction}.
    * @param threshold the threshold value to be exceeded.
    */
   public McCullochPittsFunction( double threshold ){
      super( threshold, null );
   }// End Constructor

   /**
    * {@inheritDoc}
    * Cumulatively stores the output received.
    */
   @Override public void synapseFired( double output ) {
      inputTotal += output;
   }// End Method

   /**
    * {@inheritDoc}
    * Calculates the output of the {@link Neuron} given the {@link #inputTotal}
    * received from input {@link Neuron}s, the threshold and the sign function.
    * out = sign( sum( input ) - threshold ) where sign = x > 0 ? 1 : 0.
    */
   @Override public void calculateOutput() {
      double thresholdResult = inputTotal - threshold;
      if ( thresholdResult >= 0 ){
         setOutput( FUNCTION_OUTPUT_ACTION_POTENTIAL );
      } else {
         setOutput( FUNCTION_OUTPUT_RESTING_POTENTIAL );
      }
      reset();
   }// End Method

   /**
    * Method to reset the function after having evaluated its input.
    */
   private void reset(){
      inputTotal = 0;
   }// End Method

}// End Class
