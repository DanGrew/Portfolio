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
import model.singleton.Synapse;
import model.structure.NetworkPosition;

/**
 * The {@link SynapseComparator} is responsible for providing a {@link Comparator} of {@link Synapse}s.
 */
public class SynapseComparator {

   private static final int SMALLER = -1;
   private static final int EQUAL = 0;
   private static final int GREATER = 1;
   
   /** {@link Comparator} that orders {@link Synapse}s by input {@link Neuron} then output {@link Neuron},
    * ascending layer then index. **/
   public static final Comparator< Synapse > NETWORK_POSITION_COMPARATOR = new NetworkPositionComparator();
   
   /**
    * The {@link NetworkPositionComparator} is responsible for comparing {@link Synapse}s and ordering 
    * then ascending layer then index, input {@link Neuron} then output {@link Neuron}.
    */
   private static class NetworkPositionComparator implements Comparator< Synapse > {

      /**
       * {@inheritDoc}
       */
      @Override public int compare( Synapse synapseA, Synapse synapseB ) {
         NetworkPosition inputA = synapseA.getInput().getPosition();
         NetworkPosition outputA = synapseA.getOutput().getPosition();
         NetworkPosition inputB = synapseB.getInput().getPosition();
         NetworkPosition outputB = synapseB.getOutput().getPosition();
         
         if ( inputA.layer < inputB.layer ){
            return SMALLER;
         } else if ( inputA.layer > inputB.layer ){
            return GREATER;
         } else if ( inputA.index < inputB.index ){
            return SMALLER;
         } else if ( inputA.index > inputB.index ){
            return GREATER;
         } else if ( outputA.layer < outputB.layer ){
            return SMALLER;
         } else if ( outputA.layer > outputB.layer ){
            return GREATER;
         } else if ( outputA.index < outputB.index ){
            return SMALLER;
         } else if ( outputA.index > outputB.index ){
            return GREATER;
         } else {
            return EQUAL;
         }
      }// End Method
      
   }// End Class
   
}// End Class
