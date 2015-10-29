/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package represenation.xml.wrapper;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;
import architecture.serialization.utility.XmlSimpleEntry;
import representation.xml.wrapper.XmlSingletonWrapper;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * {@link XmlSingletonWrapper} test.
 */
public class XmlSingletonWrapperTest {

   private static TestSingleton TEST_SINGLETON = new TestSingletonImpl( "anything" );
   @Rule public TemporaryFolder temp = new TemporaryFolder();

   /**
    * Root element to wrap an {@link XmlTestSingleton}.
    */
   @XmlRootElement( name  = "wrapper" )
   public static class XmlWrapper {
      @XmlElement private XmlSingletonWrapper< TestSingleton > entry;
      
      /** Blank constructor for JaxB.**/
      public XmlWrapper() {}
   }//End Class
   
   /** 
    * Custom extension to provide the abstract behaviour.
    */
   public static class XmlTestSingleton extends XmlSingletonWrapper< TestSingleton > {

      /**
       * {@inheritDoc}
       */
      @Override public TestSingleton unwrap() {
         throw new UnsupportedOperationException( "Should not be called." );
      }//End Method
      
   }//End Class
   
   /**
    * Test that the item can be serialized and deserialized.
    * @throws IOException if a {@link File} cannot be created.
    */
   @Test public void shouldSerializeAndDeserialize() throws IOException {
      XmlTestSingleton entry = new XmlTestSingleton();
      entry.setIdentification( TEST_SINGLETON.getIdentification() );
      Assert.assertEquals( TEST_SINGLETON.getIdentification(), entry.getIdentification() );
      
      XmlWrapper wrapper = new XmlWrapper();
      wrapper.entry = entry;
      
      File temporaryFile = temp.newFile();
      SerializationSystem.saveToFile( wrapper, temporaryFile, XmlWrapper.class, XmlTestSingleton.class );
      
      XmlWrapper parsedWrapper = SerializationSystem.loadFromFile( XmlWrapper.class, temporaryFile, XmlWrapper.class, XmlTestSingleton.class );
      Assert.assertNotEquals( wrapper, parsedWrapper );
      Assert.assertNotEquals( wrapper.entry, parsedWrapper.entry );
      Assert.assertEquals( wrapper.entry.getIdentification(), parsedWrapper.entry.getIdentification() );
   }//End Method
   
}//End Class
