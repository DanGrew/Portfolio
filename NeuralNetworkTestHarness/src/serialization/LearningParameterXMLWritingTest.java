/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package serialization;

import java.io.File;

import model.data.SerializableLearningParameter;
import model.singleton.LearningParameter;
import model.singleton.LearningParameter.NeuronValue;
import model.structure.NetworkPosition;

import org.junit.Test;

import representation.xml.model.XmlLearningParameter;
import temporary.TemporaryFiles;
import utility.Comparison;
import architecture.serialization.SerializationSystem;
import architecture.utility.ObjectGenerator;

/**
 * The {@link LearningParameterXMLWritingTest} is responsible for testing the read and write
 * of a {@link LearningParameter} to and from {@link XmlLearningParameter}.
 */
public class LearningParameterXMLWritingTest {
   
   /** The output file to temporarily save the data. **/
   private static final String OUTPUT_FILE = "LearningParameters.xml";

   /**
    * Method to write a {@link LearningParameter} to a file, then read it back in and
    * assert that they are equal.
    */
   @Test public void ReadAndWriteTest() {
      LearningParameter parameter = new LearningParameter( "Parameter" );
      parameter.inputParameters( 
               ObjectGenerator.newRandom(), 
               ObjectGenerator.newRandom(), 
               ObjectGenerator.newRandom() 
      );
      parameter.targetParameters( 
               new NeuronValue( new NetworkPosition( 1, 0 ), ObjectGenerator.newRandom() ), 
               new NeuronValue( new NetworkPosition( 1, 1 ), ObjectGenerator.newRandom() ),
               new NeuronValue( new NetworkPosition( 1, 2 ), ObjectGenerator.newRandom() )
      );
      
      File file = new File( TemporaryFiles.TEMPORARY_DIRECTORY + OUTPUT_FILE ); 
      
      SerializableLearningParameter serialized = parameter.write( XmlLearningParameter.class );
      SerializationSystem.saveToFile( serialized, file );
      LearningParameter constructed = SerializationSystem.loadWrappedSingleton( XmlLearningParameter.class, file );
      Comparison.assertEqual( parameter, constructed );
   }// End Method

}// End Class
