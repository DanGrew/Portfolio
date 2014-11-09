/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.data.read;

import java.io.Serializable;
import java.util.Iterator;

import model.function.threshold.ThresholdFunction;
import model.singleton.Neuron;
import model.singleton.Synapse;

/**
 * The {@link SerializableNeuron} provides the interface required to serialize a {@link Neuron} and
 * store all of its data to recreate it.
 */
public interface SerializableNeuron extends Serializable, SerializableSingleton {

   /**
    * Method to serialize an incoming {@link Synapse}.
    * @param synapse the {@link Synapse} incoming to the {@link Neuron}.
    */
   public void addIncomingSynapse( Synapse synapse );
   
   /**
    * Method to serialize all incoming {@link Synapse}s.
    * @param incoming the {@link Iterator} of {@link Synapse}s incoming to the {@link Neuron}.
    */
   public void addAllIncomingSynapses( Iterator< Synapse > incoming );
   
   /**
    * Method to serialize an outgoing {@link Synapse}.
    * @param synapse the {@link Synapse} outgoing from the {@link Neuron}.
    */
   public void addOutgoingSynapse( Synapse synapse );
   
   /**
    * Method to serialize all outgoing {@link Synapse}s.
    * @param incoming the {@link Iterator} of {@link Synapse}s outgoing to the {@link Neuron}.
    */
   public void addAllOutgoingSynapses( Iterator< Synapse > incoming );
   
   /**
    * Method to set the {@link Class} of the {@link ThresholdFunction} used by the {@link Neuron}.
    * @param functionType the {@link Class} of the {@link ThresholdFunction}.
    */
   public void setThresholdFunction( Class< ? extends ThresholdFunction > functionType );
   
   /**
    * Method to set the current output of the {@link Neuron}.
    * @param output the output produced on the last input fire.
    */
   public void setCurrentOutput( Double output );
}// End Interface

