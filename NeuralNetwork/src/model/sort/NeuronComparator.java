/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.sort;

import java.util.Comparator;

import model.singleton.Neuron;
import model.structure.NetworkPosition;

/**
 * The {@link NeuronComparator} is responsible for providing comparators to order {@link Neuron}s.
 */
public class NeuronComparator {

   /** {@link Comparator} for {@link Neuron}s that orders based on position, where layer is ordered
    * ascending, then index. **/
   public static final Comparator< Neuron > NETWORK_POSITION_COMPARATOR = new NeuronPositionComparator();
   
   /**
    * The {@link NeuronComparator} is responsible for defining a {@link Comparator} to order {@link Neuron}s
    * by position, ascending by layer then index.
    */
   private static class NeuronPositionComparator implements Comparator< Neuron > {

      /**
       * {@inheritDoc}
       */
      @Override public int compare( Neuron neuronA, Neuron neuronB ) {
         NetworkPosition positionA = neuronA.getPosition();
         NetworkPosition positionB = neuronB.getPosition();
         return NetworkPositionComparator.POSITION_COMPARATOR.compare( positionA, positionB );
      }// End Method
      
   }// End Class
   
}// End Class
