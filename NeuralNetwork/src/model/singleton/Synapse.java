/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.singleton;

import model.function.ThresholdFunction;

 /**
  * The {@link Synapse} is responsible for connecting two {@link Neuron}s and weighting
  * the output between them.
  */
public class Synapse {

   /** Constant defining the default weight of the {@link Synapse} if none is configured. **/
   private static final double DEFAULT_WEIGHT = 1.0;
   /** The input {@link Neuron} providing the output value to this {@link Synapse}.**/
   private Neuron inputNeuron;
   /** The output {@link Neuron} the {@link Synapse} provides the weighted output to.**/
   private Neuron outputNeuron;
   /** The weight to apply to the output from the input {@link Neuron}.**/
   private double weight;

   /**
    * Constructs a new {@link Synapse}.
    * @param inputNeuron the {@link Neuron} providing the output to the {@link Synapse}.
    * @param outputNeuron the {@link Neuron} receiving the weighted output form the {@link Synapse}.
    */
   public Synapse( Neuron inputNeuron, Neuron outputNeuron ) {
      this.inputNeuron = inputNeuron;
      this.outputNeuron = outputNeuron;
      this.weight = DEFAULT_WEIGHT;
      connectSynapse();
   }// End Constructor

   /**
    * Constructs a new {@link Synapse}.
    * @param inputNeuron the {@link Neuron} providing the output to the {@link Synapse}.
    * @param weight the weight associated with the {@link Synapse}.
    * @param outputNeuron the {@link Neuron} receiving the weighted output from the {@link Synapse}.
    */
   public Synapse( Neuron inputNeuron, double weight, Neuron outputNeuron ) {
      this( inputNeuron, outputNeuron );
      this.weight = weight;
   }// End Constructor

   /**
    * Method to connect the {@link Synapse} to the {@link #inputNeuron} and the {@link #outputNeuron}.
    */
   private void connectSynapse() {
      inputNeuron.addOutgoingSynapse( this );
      outputNeuron.addIncomingSynapse( this );
   }// End Method

   /**
    * Method to get the {@link Neuron} that provides the output to this {@link Synapse}.
    * @return the {@link Neuron}.
    */
   public Neuron getInput(){
      return inputNeuron;
   }// End Method

   /**
    * Method to get the {@link Neuron} that the weighted output is provided to.
    * @return the {@link Neuron}.
    */
   public Neuron getOutput(){
      return outputNeuron;
   }// End Method

   /**
    * Method to set the weight associated with this {@link Synapse}.
    * @param weight the weight to modify the output of the {@link #inputNeuron}.
    */
   public void setWeight( double weight ){
      this.weight = weight;
   }// End Method

   /**
    * Method to apply the weight to the output provided by the {@link #inputNeuron}.
    * @param output the output provided.
    * @return the weighted output to provide to the {@link #outputNeuron}.
    */
   private Double applyWeight( Double output ){
      if ( output == null ){
         return null;
      } else {
         return output * weight;
      }
   }// End Method

   /**
    * Method to fire the output through the {@link Synapse} to the connected {@link Neuron}.
    * @param output the calculated output from the {@link ThresholdFunction}.
    */
   public void fire( Double output ){
      Double weightedOutput = applyWeight( output );
      if ( weightedOutput != null ){
         outputNeuron.synapseFired( weightedOutput );
      } else {
         throw new NullPointerException();
      }
   }// End Method

}// End Class
