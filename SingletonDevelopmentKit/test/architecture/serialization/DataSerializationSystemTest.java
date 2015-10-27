/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.serialization;

import java.io.File;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import architecture.serialization.utility.XmlSimpleEntry;

/**
 * {@link DataSerializationSystemImpl} test.
 */
public class DataSerializationSystemTest {
   
   @Rule public TemporaryFolder temp = new TemporaryFolder();

   /**
    * Root element to wrap an {@link XmlSimpleEntry}.
    */
   @XmlRootElement( name  = "wrapper" )
   public static class XmlWrapper {
      @XmlElement private XmlSimpleEntry< String, Double > entry;
      
      /** Blank constructor for JaxB.**/
      public XmlWrapper() {}
   }//End Class
   
   /**
    * {@link SerializationSystem#saveToFile(Object, File, Class...)} and {@link SerializationSystem#loadFromFile(Class, File, Class...)}
    * test.
    * @throws IOException if a {@link File} cannot be created.
    */
   @Test public void shouldSerializeAndDeserialize() throws IOException {
      final String stringValue = "Anything";
      final Double doubleValue = 3487.238;
      XmlSimpleEntry< String, Double > entry = new XmlSimpleEntry<>( new SimpleEntry<>( stringValue, doubleValue ) );
      
      XmlWrapper wrapper = new XmlWrapper();
      wrapper.entry = entry;
      
      File temporaryFile = temp.newFile();
      SerializationSystem.saveToFile( wrapper, temporaryFile, XmlWrapper.class, XmlSimpleEntry.class );
      
      XmlWrapper parsedWrapper = SerializationSystem.loadFromFile( XmlWrapper.class, temporaryFile, XmlWrapper.class, XmlSimpleEntry.class );
      Assert.assertNotEquals( wrapper, parsedWrapper );
      Assert.assertNotEquals( wrapper.entry, parsedWrapper.entry );
      Assert.assertEquals( wrapper.entry.getKey(), parsedWrapper.entry.getKey() );
      Assert.assertEquals( wrapper.entry.getValue(), parsedWrapper.entry.getValue() );
   }//End Method

}//End Class
