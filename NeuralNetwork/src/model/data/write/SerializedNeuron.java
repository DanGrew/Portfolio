/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.data.write;

import model.data.read.SerializableNeuron;
import model.singleton.Neuron;

/**
 * The {@link SerializedNeuron} interface provides the interface that should be implemented
 * to deserialize a {@link SerializableNeuron} into a {@link Neuron}.
 */
public interface SerializedNeuron extends SerializedSingleton {

}// End Interface
