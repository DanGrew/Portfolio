/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.singleton;

import java.util.LinkedHashMap;
import java.util.Map;

import model.function.threshold.McCullochPittsFunction;
import model.function.threshold.ThresholdFunction;

/**
 * The {@link Neuron} represents a single {@link Neuron} in the Neural Network.
 */
public class Neuron {

   /** The {@link ThresholdFunction} to use when calculating whether the {@link Neuron}
    * should fire along the associated {@link Synapse}s.**/
   private ThresholdFunction thresholdFunction;
   /** {@link Map} of output {@link Neuron} that this {@link Neuron} is connected to.**/
   private Map< Neuron, Synapse > outgoingSynpases;
   /** {@link Map} of input {@link Neuron} that are connected to this {@link Neuron}.**/
   private Map< Neuron, Synapse > incomingSynapses;

   /**
    * Constructs a new {@link Neuron}.
    * @param thresholdFunction the {@link ThresholdFunction} the {@link Neuron} will use.
    */
   public Neuron( ThresholdFunction thresholdFunction ) {
      outgoingSynpases = new LinkedHashMap< Neuron, Synapse >();
      incomingSynapses = new LinkedHashMap< Neuron, Synapse >();
      this.thresholdFunction = thresholdFunction;
   }// End Constructor

   /**
    * Constructs a new {@link Neuron}, using the default {@link ThresholdFunction} as
    * the {@link McCullochPittsFunction}.
    */
   public Neuron() {
      this( new McCullochPittsFunction() );
   }// End Constructor

   /**
    * Method to add a {@link Synapse} incoming to this {@link Neuron}.
    * @param synapse the {@link Synapse} connecting the two {@link Neuron}s.
    */
   public void addIncomingSynapse( Synapse synapse ) {
      incomingSynapses.put( synapse.getInput(), synapse );
   }// End Method

   /**
    * Method to add a {@link Neuron} incoming to this {@link Neuron}.
    * @param input the {@link Neuron} incoming to this {@link Neuron}, to create a {@link Synapse}
    * from to this.
    */
   public void addIncomingSynapse( Neuron input ){
      new Synapse( input, this );
   }// End Method

   /**
    * Method to add a {@link Synapse} outgoing from this {@link Neuron}.
    * @param synapse the {@link Synapse} connecting the two {@link Neuron}s.
    */
   public void addOutgoingSynapse( Synapse synapse ) {
      outgoingSynpases.put( synapse.getOutput(), synapse );
   }// End Method

   /**
    * Method to add a {@link Neuron} outgoing to this {@link Neuron}.
    * @param input the {@link Neuron} outgoing to this {@link Neuron}, to create a {@link Synapse}
    * from to this.
    */
   public void addOutgoingSynapse( Neuron output ){
      new Synapse( this, output );
   }// End Method

   /**
    * Method to configure the weight on the outgoing {@link Synapse} to the given {@link Neuron}.
    * @param output the {@link Neuron} the {@link Synapse} is to.
    * @param weight the weight the {@link Synapse} should use.
    */
   public void configureOutgoingWeight( Neuron output, double weight ){
      Synapse synapse = outgoingSynpases.get( output );
      synapse.setWeight( weight );
   }// End Method

   /**
    * Method to process the firing of an associated {@link Synapse}.
    * @param input the double weighted input.
    */
   public void synapseFired( double input ) {
      thresholdFunction.synapseFired( input );
   }// End Method

   /**
    * Method to get the output calculated by the associated {@link ThresholdFunction}.
    * @return the calculated output.
    */
   public Double getOutput() {
      return thresholdFunction.getOutput();
   }// End Method

   /**
    * Method to fire the {@link Neuron}. This will calculate the output and then fire the
    * associated {@link Synapse}s with the calculated output.
    */
   public void fireNeuron() {
      thresholdFunction.calculateOutput();
      if ( thresholdFunction.excedesThreshold() ){
         for ( Synapse synpase : outgoingSynpases.values() ){
            synpase.fire( thresholdFunction.getOutput() );
         }
      }
   }// End Method

   /**
    * Method to make the {@link Neuron}s incoming {@link Synapse}s learn from the
    * given target value. The current output of the {@link Neuron} is used for comparison
    * of performance.
    * @param target the target the {@link Neuron} should have achieved.
    */
   public void learn( double target ){
      for ( Synapse synapse : incomingSynapses.values() ){
         synapse.learn( target, thresholdFunction.getOutput() );
      }
   }// End Method

   /**
    * Method to produce a {@link String} summarising the weights from inputs this {@link Neuron} has
    * incoming.
    * @return a {@link String} defining the current weights being applied to the inputs of this
    * {@link Neuron}.
    */
   public String toWeightString(){
      StringBuffer buffer = new StringBuffer();
      for ( Synapse synapse : incomingSynapses.values() ){
         buffer.append( synapse.toWeightString() ).append( " " );
      }
      return buffer.toString();
   }// End Method

}// End Class
