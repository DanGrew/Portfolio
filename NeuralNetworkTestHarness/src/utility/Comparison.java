/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.Iterator;

import model.data.SerializableLearningParameter;
import model.data.SerializableNeuron;
import model.data.SerializableSynapse;
import model.singleton.LearningParameter;
import model.singleton.LearningParameter.NeuronValue;
import model.singleton.Neuron;
import model.singleton.Synapse;
import model.sort.NetworkPositionComparator;
import model.structure.NeuronLayer;
import representation.xml.model.XmlLearningParameter;
import representation.xml.model.XmlNeuron;
import representation.xml.model.XmlSynapse;

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
      assertEqual( inputA, inputB, ( a, b ) -> { return a.compareTo( b ); } );
      assertEquals( inputA.hasNext(), inputB.hasNext() );
      
      Iterator< String > outputA = serializableA.outgoingSynapseIterator();
      Iterator< String > outputB = serializableB.outgoingSynapseIterator();
      assertEqual( outputA, outputB, ( a, b ) -> { return a.compareTo( b ); } );
      assertEquals( outputA.hasNext(), outputB.hasNext() );
      
      assertEquals( serializableA.getThresholdFunction(), serializableB.getThresholdFunction() );
      doubleAssert( serializableA.getCurrentOutput(), serializableB.getCurrentOutput() );
   }// End Method
   
   /**
    * Method to assert that the given {@link Synapse}s are equal. This is done by converting them
    * to a {@link SerializableSynapse} and comparing the data.
    * @param synapseA the first {@link Synapse}.
    * @param synapseB the second {@link Synapse}.
    */
   public static void assertEqual( Synapse synapseA, Synapse synapseB ){
      SerializableSynapse serializableA = synapseA.write( XmlSynapse.class );
      SerializableSynapse serializableB = synapseB.write( XmlSynapse.class );
      
      assertEquals( serializableA.getIdentification(), serializableB.getIdentification() );
      assertEquals( serializableA.getInputNeuron(), serializableB.getInputNeuron() );
      assertEquals( serializableA.getOutputNeuron(), serializableB.getOutputNeuron() );
      assertEquals( serializableA.getLearningRule(), serializableB.getLearningRule() );
      doubleAssert( serializableA.getLearningRate(), serializableB.getLearningRate() );
      doubleAssert( serializableA.getLastFiredOutput(), serializableB.getLastFiredOutput() );
      doubleAssert( serializableA.getWeight(), serializableB.getWeight() );
   }// End Method
   
   /**
    * Method to assert that two {@link NeuronLayer}s are that same. This will check the bias, {@link ThresholdFunction}
    * and {@link Neuron}s in the layer.
    * @param layerA the first {@link NeuronLayer}.
    * @param layerB the second {@link NeuronLayer}.
    */
   public static void assertEqual( NeuronLayer layerA, NeuronLayer layerB ){
      assertEquals( layerA.getCapacity(), layerB.getCapacity() );
      assertEquals( layerA.getThresholdFunction(), layerB.getThresholdFunction() );
      layerA.iterator().forEachRemaining( neuronA -> {
         assertEqual( neuronA, layerB.getNeuronAtPosition( neuronA.getPosition() ) );
      } );
   }// End Method
   
   /**
    * Method to assert that two {@link LearningParameter}s are equal. This checks their identification
    * and {@link NeuronValue}s representing the targets.
    * @param parameterA the first {@link LearningParameter}.
    * @param parameterB the second {@link LearningParameter}.
    */
   public static void assertEqual( LearningParameter parameterA, LearningParameter parameterB ){
      SerializableLearningParameter serializedA = parameterA.write( XmlLearningParameter.class );
      SerializableLearningParameter serializedB = parameterB.write( XmlLearningParameter.class );
      
      assertEquals( serializedA.getIdentification(), serializedB.getIdentification() );
      Comparator< NeuronValue > comparator = ( a, b ) -> {
         int positionsCompare = NetworkPositionComparator.POSITION_COMPARATOR.compare( a.position, b.position );
         if ( positionsCompare == 0 ){
            int valueCompare = Integer.compare( a.value.intValue(), b.value.intValue() );
            return valueCompare;
         } else {
            return positionsCompare;
         }
      };
      Iterator< NeuronValue > inputsA = serializedA.inputParametersIterator();
      Iterator< NeuronValue > inputsB = serializedB.inputParametersIterator();
      assertEqual( inputsA, inputsB, comparator );
      Iterator< NeuronValue > targetsA = serializedA.targetParametersIterator();
      Iterator< NeuronValue > targetsB = serializedB.targetParametersIterator();
      assertEqual( targetsA, targetsB, comparator );
   }// End Method
   
   /**
    * Method to assert that the contents of two {@link Iterator}s are equal.
    * @param iteratorA the first {@link Iterator}.
    * @param iteratorB the second {@link Iterator}.
    * @param comparison the method to compare the {@link Object}s in the {@link Iterator}s.
    */
   public static < T > void assertEqual( Iterator< T > iteratorA, Iterator< T > iteratorB, Comparator< T > comparison ){
      while ( iteratorA.hasNext() && iteratorB.hasNext() ){
         T nextA = iteratorA.next();
         T nextB = iteratorB.next();
         assertEquals( comparison.compare( nextA, nextB ), 0 );
      }
   }// End Method
}// End Class
