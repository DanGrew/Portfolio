/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import static org.junit.Assert.assertEquals;
import model.singleton.Neuron;
import model.singleton.Singleton;
import model.singleton.Synapse;
import model.structure.NeuronLayer;
import architecture.request.RequestSystem;

/**
 * The {@link Resolution} class provides methods are validating that {@link Singleton}s are
 * referenced correctly in the {@link RequestSystem}.
 */
public class Resolution {

   /**
    * Method to assert that a {@link Singleton} is resolved correctly in the {@link RequestSystem}.
    * @param object the {@link Object} to assert.
    * @param clazz the {@link Class} of the {@link Singleton}.
    */
   public static < T extends Singleton > void assertResolved( T object, Class< T > clazz ){
      T resolvedObject = RequestSystem.retrieve( clazz, object.getIdentification() );
      assertEquals( resolvedObject, object );
   }// End Method
   
   /**
    * Method to assert that a {@link NeuronLayer} is resolved correctly in the {@link RequestSystem}.
    * @param layer the {@link NeuronLayer} to assert, asserting each {@link Neuron} and {@link Synapse}.
    */
   public static < T extends Singleton > void assertResolved( NeuronLayer layer ){
      layer.iterator().forEachRemaining( neuron -> { 
         Resolution.assertResolved( neuron, Neuron.class );
         neuron.inputSynapseIterator().forEachRemaining( synapse -> {
            Resolution.assertResolved( synapse, Synapse.class );
         } );
         neuron.outputSynapseIterator().forEachRemaining( synapse -> {
            Resolution.assertResolved( synapse, Synapse.class );
         } );
      } );
   }// End Method
}
