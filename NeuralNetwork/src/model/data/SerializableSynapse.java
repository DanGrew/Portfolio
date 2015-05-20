/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.data;

import model.function.learning.PerceptronLearningRule;
import model.singleton.Neuron;
import model.singleton.Synapse;

/**
 * The {@link SerializableSynapse} interface provides the interface that should be implemented to
 * serialize a {@link Synapse}.
 */
public interface SerializableSynapse extends SerializedSingleton< Synapse > {

   /**
    * Method to set the input {@link Neuron} to the {@link Synapse}.
    * @param input the input {@link Neuron}.
    */
   public void setInputNeuron( Neuron input );
   
   /**
    * Method to get the identification of the {@link Neuron}.
    * @return the {@link String} identification.
    */
   public String getInputNeuron();
   
   /**
    * Method to set the output {@link Neuron} of the {@link Synapse}.
    * @param output the output {@link Neuron}.
    */
   public void setOutputNeuron( Neuron output );
   
   /**
    * Method to get the identification of the output {@link Neuron}.
    * @return the {@link String} identification.
    */
   public String getOutputNeuron();
   
   /**
    * Method to set the {@link Class} of the {@link PerceptronLearningRule} being used.
    * @param learningRule the {@link Class}.
    */
   public void setLearningRule( Class< ? extends PerceptronLearningRule > learningRule );
   
   /**
    * Method to get the {@link Class} of the learning rule used by the {@link Synapse}.
    * @return the {@link Class} of the learning rule.
    */
   public Class< ? extends PerceptronLearningRule > getLearningRule();
   
   /**
    * Method to set the learning rate used by the {@link Synapse}.
    * @param learningRate the learning rate.
    */
   public void setLearningRate( double learningRate );
   
   /**
    * Method to get the learning rate of the {@link Synapse}.
    * @return the learning rate.
    */
   public double getLearningRate();
   
   /**
    * Method to set the output last fired to the {@link Synapse}.
    * @param lastFiredOutput the last fired output from the input {@link Neuron}.
    */
   public void setLastFiredOutput( Double lastFiredOutput );
   
   /**
    * Method to get the last fired output to the {@link Synapse}.
    * @return the last fired output if defined.
    */
   public Double getLastFiredOutput();
   
   /**
    * Method to set the weight of the {@link Synapse}.
    * @param weight {@link Synapse#getWeightProperty()}.
    */
   public void setWeight( Double weight );
   
   /**
    * Method to get the current weight of the {@link Synapse}.
    * @return the weight.
    */
   public Double getWeight();
   
}// End Interface
