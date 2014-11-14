/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture;

import java.io.File;

import model.network.Perceptron;
import model.singleton.Neuron;

import org.junit.BeforeClass;
import org.junit.Test;

import representation.xml.wrapper.XmlPerceptronWrapper;
import temporary.TemporaryFiles;
import utility.Comparison;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

/**
 * The {@link SerializationSystemTest} is responsible for testing the {@link SerializationSystem}
 * interface.
 */
public class SerializationSystemTest {
   
   /** The name of the output file to test writing and reading.**/
   private static final String OUTPUT_FILE = "XML_PERCEPTRON.xml";
   /** The constructed {@link Perceptron} to write, and validate against.**/
   private static Perceptron initialPerceptron;

   /**
    * Method to initialise the tests by constructing the {@link Perceptron} and 
    * writing it to the output file in XML.
    */
   @BeforeClass public static void initialise() {
      initialPerceptron = new Perceptron( 20, 10 );
      XmlPerceptronWrapper wrapper = new XmlPerceptronWrapper( initialPerceptron );
      File file = new File( TemporaryFiles.TEMPORARY_DIRECTORY + OUTPUT_FILE );
      SerializationSystem.saveToFile( wrapper, file );
      SerializationSystem.loadSingletonsFromFile( XmlPerceptronWrapper.class, file );
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

}// End Class
