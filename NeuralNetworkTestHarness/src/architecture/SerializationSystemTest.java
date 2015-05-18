/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture;

import static org.junit.Assert.assertEquals;

import java.io.File;

import model.network.Perceptron;
import model.singleton.Neuron;
import model.singleton.Singleton;
import model.singleton.Synapse;

import org.junit.BeforeClass;
import org.junit.Test;

import representation.xml.wrapper.XmlPerceptronWrapper;
import temporary.TemporaryFiles;
import utility.Comparison;
import utility.Resolution;
import architecture.event.system.ManagementSystem;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

/**
 * The {@link SerializationSystemTest} is responsible for testing the {@link SerializationSystem}
 * interface.
 */
public class SerializationSystemTest {
   
   /** The number of {@link Neuron}s in the input layer. **/
   private static final int INPUT_NEURON_COUNT = 20;
   /** The number of {@link Neuron}s in the output layer. **/
   private static final int OUTPUT_NEURON_COUNT = 10;
   /** The number of {@link Neuron}s used as bias. **/
   private static final int BIAS_NEURON_COUNT = 1;
   /** The name of the output file to test writing and reading.**/
   private static final String OUTPUT_FILE = "XML_PERCEPTRON.xml";
   /** The constructed {@link Perceptron} to write, and validate against.**/
   private static Perceptron initialPerceptron;
   /** The {@link Perceptron} constructed from the {@link File}. **/
   private static Perceptron constructedPerceptron;

   /**
    * Method to initialise the tests by constructing the {@link Perceptron} and 
    * writing it to the output file in XML.
    */
   @BeforeClass public static void initialise() {
      ManagementSystem.reset();
      
      initialPerceptron = new Perceptron( INPUT_NEURON_COUNT, OUTPUT_NEURON_COUNT );
      XmlPerceptronWrapper wrapper = new XmlPerceptronWrapper( initialPerceptron );
      File file = new File( TemporaryFiles.TEMPORARY_DIRECTORY + OUTPUT_FILE );
      SerializationSystem.saveToFile( wrapper, file );
      constructedPerceptron = SerializationSystem.loadStructure( XmlPerceptronWrapper.class, file );
   }// End Method
   
   /**
    * Method to test that the correct number of {@link Singleton}s have been created.
    */
   @Test public void CreationCountTest(){
      assertEquals( 
               ( int )RequestSystem.retrieveAll( Neuron.class, test -> { return true; } ).size(),
               INPUT_NEURON_COUNT + OUTPUT_NEURON_COUNT + BIAS_NEURON_COUNT
      );
      assertEquals( 
               ( int )RequestSystem.retrieveAll( Synapse.class, test -> { return true; } ).size(),
               OUTPUT_NEURON_COUNT * ( BIAS_NEURON_COUNT + INPUT_NEURON_COUNT )
      );
   }// End Method
   
   /**
    * Method to test that the {@link Neuron}s in the {@link Perceptron} are written, read back
    * in and correctly constructed.
    */
   @Test public void NeuronExistenceTest(){
      initialPerceptron.getInputLayer().iterator().forEachRemaining( neuron -> {
         Neuron storedNeuron = RequestSystem.retrieve( Neuron.class, neuron.getIdentification() );
         Comparison.assertEqual( neuron, storedNeuron );
      } );
      
      initialPerceptron.getOutputLayer().iterator().forEachRemaining( neuron -> {
         Neuron storedNeuron = RequestSystem.retrieve( Neuron.class, neuron.getIdentification() );
         Comparison.assertEqual( neuron, storedNeuron );
      } );
   }// End Method
   
   /**
    * Method to test that the {@link Synapse}s in the {@link Perceptron} are written, read back
    * in and correctly constructed.
    */
   @Test public void SynapseExistenceTest(){
      initialPerceptron.getOutputLayer().iterator().forEachRemaining( neuron -> {
         neuron.inputSynapseIterator().forEachRemaining( synapse -> {
            Synapse storedSynapse = RequestSystem.retrieve( Synapse.class, synapse.getIdentification() );
            Comparison.assertEqual( synapse, storedSynapse );
         });
      });
   }// End Method
   
   /**
    * Method to test that the {@link #initialPerceptron} matches the {@link #constructedPerceptron}.
    */
   @Test public void ConstructionTest(){
      Comparison.assertEqual( initialPerceptron.getBias(), constructedPerceptron.getBias() );
      Comparison.assertEqual( initialPerceptron.getInputLayer(), constructedPerceptron.getInputLayer() );
      Comparison.assertEqual( initialPerceptron.getOutputLayer(), constructedPerceptron.getOutputLayer() );
   }// End Method
   
   /**
    * Method to test that the {@link #constructedPerceptron} references all {@link Singleton}s in 
    * the {@link RequestSystem}.
    */
   @Test public void ResolutionTest(){
      Resolution.assertResolved( constructedPerceptron.getInputLayer() );
      Resolution.assertResolved( constructedPerceptron.getOutputLayer() );
      Resolution.assertResolved( constructedPerceptron.getBias(), Neuron.class );
   }// End Method
   
}// End Class
