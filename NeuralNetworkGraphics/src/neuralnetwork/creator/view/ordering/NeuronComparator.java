/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view.ordering;

import java.util.Comparator;

import model.singleton.Neuron;
import model.structure.NetworkPosition;

/**
 * The {@link NeuronComparator} is responsible for providing comparators to order {@link Neuron}s.
 */
public class NeuronComparator {

   private static final int SMALLER = -1;
   private static final int EQUAL = 0;
   private static final int GREATER = 1;
   
   /** {@link Comparator} for {@link Neuron}s that orders based on position, where layer is ordered
    * ascending, then index. **/
   public static final Comparator< Neuron > NETWORK_POSITION_COMPARATOR = new NetworkPositionComparator();
   
   /**
    * The {@link NeuronComparator} is responsible for defining a {@link Comparator} to order {@link Neuron}s
    * by position, ascending by layer then index.
    */
   private static class NetworkPositionComparator implements Comparator< Neuron > {

      /**
       * {@inheritDoc}
       */
      @Override public int compare( Neuron neuronA, Neuron neuronB ) {
         NetworkPosition positionA = neuronA.getPosition();
         NetworkPosition positionB = neuronB.getPosition();
         
         if ( positionA.layer < positionB.layer ){
            return SMALLER;
         } else if ( positionA.layer > positionB.layer ){
            return GREATER;
         } else if ( positionA.index < positionB.index ){
            return SMALLER;
         } else if ( positionA.index > positionB.index ){
            return GREATER;
         } else {
            return EQUAL;
         }
      }// End Method
      
   }// End Class
   
}// End Constructor
