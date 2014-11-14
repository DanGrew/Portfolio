/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import model.data.SerializableNeuron;
import model.singleton.Neuron;
import representation.xml.model.XmlNeuron;

/**
 * {@link Comparison} provides assertion helper methods for comparing objects and primitives.
 */
public class Comparison {
   
   /** The error to use for double comparisons. **/
   private static final double ERROR = 0.00000001;
   
   /**
    * Method to perform a double assert using the {@link #ERROR}.
    * @param first the first value.
    * @param second the second value.
    */
   public static void doubleAssert( double first, double second ){
      assertEquals( first, second, ERROR );
   }// End Method

   /**
    * Method to assert that two {@link Neuron}s are equal. This is established by serializing
    * them then commaring the data.
    * @param neuronA the first {@link Neuron}.
    * @param neuronB the second {@link Neuron}.
    */
   public static void assertEqual( Neuron neuronA, Neuron neuronB ){
      SerializableNeuron serializableA = neuronA.write( XmlNeuron.class );
      SerializableNeuron serializableB = neuronB.write( XmlNeuron.class );
      
      assertEquals( serializableA.getIdentification(), serializableB.getIdentification() );
      assertEquals( 
               serializableA.getPosition().getRepresentationProperty().get(), 
               serializableB.getPosition().getRepresentationProperty().get() 
      );
      
      Iterator< String > inputA = serializableA.incomingSynapseIterator();
      Iterator< String > inputB = serializableB.incomingSynapseIterator();
      while ( inputA.hasNext() && inputB.hasNext() ){
         assertEquals( inputA.next(), inputB.next() );
      }
      assertEquals( inputA.hasNext(), inputB.hasNext() );
      
      Iterator< String > outputA = serializableA.outgoingSynapseIterator();
      Iterator< String > outputB = serializableB.outgoingSynapseIterator();
      while ( outputA.hasNext() && outputB.hasNext() ){
         assertEquals( outputA.next(), outputB.next() );
      }
      assertEquals( outputA.hasNext(), outputB.hasNext() );
      
      assertEquals( serializableA.getThresholdFunction(), serializableB.getThresholdFunction() );
      doubleAssert( serializableA.getCurrentOutput(), serializableB.getCurrentOutput() );
   }// End Method
}// End Class
