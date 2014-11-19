/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.network.Perceptron;
import neuralnetwork.creator.view.module.FileManager;

import org.junit.BeforeClass;
import org.junit.Test;

import representation.xml.wrapper.XmlPerceptronWrapper;
import temporary.TemporaryFiles;
import testdata.TestData;
import architecture.event.EventSystem;

/**
 * The {@link FileManagerTest} is responsible for testing the {@link FileManager}.
 */
public class FileManagerTest {

   /** Temporary output file. **/
   private static final String OUTPUT_FILE = "FileManager.xml";
   /** The test perceptron to load. **/
   private static final String TEST_PERCEPTRON_FILE = "TestPerceptron.xml";
   /** The {@link FileManager} being tested.**/
   private static FileManager< Perceptron, XmlPerceptronWrapper > fileManager;
   
   /** Enum defining the events used for this test. **/
   private enum TestEvents {
      LoadRequest,
      SaveRequest,
      Loaded,
      Saved;
   }// End Enum
   
   /**
    * Method to initialise the {@link FileManager} prior to testing.
    */
   @BeforeClass public static void initialise(){
      fileManager = new FileManager< Perceptron, XmlPerceptronWrapper >( 
               TestEvents.LoadRequest, 
               TestEvents.Loaded,
               TestEvents.SaveRequest,
               TestEvents.Saved,
               Perceptron.class, 
               XmlPerceptronWrapper.class, 
               object -> { return new XmlPerceptronWrapper( object ); }
      );
   }// End Method
   
   /**
    * Method to test that the {@link FileManager} can receive an event to save, save the {@link Object}
    * then fire an event to confirm it has been saved.
    */
   @Test public void SaveTest() {
      Perceptron perceptron = new Perceptron( 10, 34 );
      fileManager.manage( perceptron );
      
      List< Object > objects = new ArrayList< Object >();
      EventSystem.registerForEvent( TestEvents.Saved, ( type, object ) -> {
         objects.add( type );
         objects.add( object );
      } );
      
      EventSystem.raiseEvent( 
               TestEvents.SaveRequest, 
               new File( TemporaryFiles.TEMPORARY_DIRECTORY + OUTPUT_FILE ) 
      );
      assertEquals( TestEvents.Saved, objects.remove( 0 ) );
      assertEquals( perceptron, objects.remove( 0 ) );
   }// End Method
   
   /**
    * Method to test that the {@link FileManager} can receive a load request, load an {@link Object}
    * then notify the success of the loading.
    */
   @Test public void LoadTest() {
      File file = new File( TestData.TEST_DATA_DIRECTORY + TEST_PERCEPTRON_FILE );
      
      List< Object > objects = new ArrayList< Object >();
      EventSystem.registerForEvent( TestEvents.Loaded, ( type, object ) -> {
         objects.add( type );
         objects.add( object );
      } );
      
      EventSystem.raiseEvent( TestEvents.LoadRequest, file );
      assertEquals( TestEvents.Loaded, objects.remove( 0 ) );
      Object loaded = objects.remove( 0 );
      assertTrue( loaded instanceof Perceptron );
   }// End Method

}// End Class
