/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.structure;

import model.singleton.Neuron;

/**
 * The {@link NetworkPosition} respresents the position of a {@link Neuron}
 * in matrix notation, i.e. Nxy, xth row, yth column.
 */
public class NetworkPosition {

   /** The layer that the position is on, increasing from input to output.**/
   public final int layer;
   /** The index of the {@link Neuron} in the {@link NeuronLayer}.**/
   public final int index;

   /**
    * Constructs a new {@link NetworkPosition}.
    * @param layer the {@link #layer}.
    * @param index the {@link #index}.
    */
   public NetworkPosition( int layer, int index ){
      this.layer = layer;
      this.index = index;
   }// End Method

}// End Class
