/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.sort;

import java.util.Comparator;

import model.singleton.Synapse;
import model.structure.NetworkPosition;

/**
 * The {@link SynapseComparator} is responsible for providing a {@link Comparator} of {@link Synapse}s.
 */
public class SynapseComparator {

   /** {@link Comparator} that orders {@link Synapse}s by input {@link Neuron} then output {@link Neuron},
    * ascending layer then index. **/
   public static final Comparator< Synapse > POSITION_COMPARATOR = new PositionComparator();
   
   /**
    * The {@link PositionComparator} is responsible for comparing {@link Synapse}s and ordering 
    * then ascending layer then index, input {@link Neuron} then output {@link Neuron}.
    */
   private static class PositionComparator implements Comparator< Synapse > {

      /**
       * {@inheritDoc}
       */
      @Override public int compare( Synapse synapseA, Synapse synapseB ) {
         NetworkPosition inputA = synapseA.getInput().getPosition();
         NetworkPosition outputA = synapseA.getOutput().getPosition();
         NetworkPosition inputB = synapseB.getInput().getPosition();
         NetworkPosition outputB = synapseB.getOutput().getPosition();
         
         int inputCompare = NetworkPositionComparator.POSITION_COMPARATOR.compare( inputA, inputB );
         if ( inputCompare == 0 ){
            return NetworkPositionComparator.POSITION_COMPARATOR.compare( outputA, outputB );
         } else {
            return inputCompare;
         }
      }// End Method
      
   }// End Class
   
}// End Class
