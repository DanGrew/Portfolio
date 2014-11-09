/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.data.read;

import model.function.learning.PerceptronLearningRule;
import model.singleton.Neuron;
import model.singleton.Synapse;

/**
 * The {@link SerializableSynapse} interface provides the interface that should be implemented to
 * serialize a {@link Synapse}.
 */
public interface SerializableSynapse extends SerializableSingleton {

   /**
    * Method to set the input {@link Neuron} to the {@link Synapse}.
    * @param input the input {@link Neuron}.
    */
   public void setInputNeuron( Neuron input );
   
   /**
    * Method to set the output {@link Neuron} of the {@link Synapse}.
    * @param output the output {@link Neuron}.
    */
   public void setOutputNeuron( Neuron output );
   
   /**
    * Method to set the {@link Class} of the {@link PerceptronLearningRule} being used.
    * @param learningRule the {@link Class}.
    */
   public void setLearningRule( Class< ? extends PerceptronLearningRule > learningRule );
   
   /**
    * Method to set the learning rate used by the {@link Synapse}.
    * @param learningRate the learning rate.
    */
   public void setLearningRate( double learningRate );
   
   /**
    * Method to set the output last fired to the {@link Synapse}.
    * @param lastFiredOutput the last fired output from the input {@link Neuron}.
    */
   public void setLastFiredOutput( Double lastFiredOutput );
   
   /**
    * Method to set the weight of the {@link Synapse}.
    * @param weight {@link Synapse#getWeightProperty()}.
    */
   public void setWeight( Double weight );
   
}// End Interface
