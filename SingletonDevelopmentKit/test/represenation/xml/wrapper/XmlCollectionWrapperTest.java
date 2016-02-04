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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import architecture.serialization.SerializationSystem;
import representation.xml.wrapper.XmlCollectionWrapper;
import utility.TestCommon;

/**
 * {@link XmlCollectionWrapper} test.
 */
public class XmlCollectionWrapperTest {

   @Rule public TemporaryFolder temp = new TemporaryFolder();
   
   /**
    * Test to serialize an {@link XmlCollectionWrapper} and read it back in. 
    * @throws IOException if a {@link File} cannot be created.
    */
   @Test public void shouldSerializeAndDeserialize() throws IOException {
      List< String > data = Arrays.asList( "anything", "in", "a", "list" );
      XmlCollectionWrapper< String > wrapper = new XmlCollectionWrapper<>( data );
      
      File temporaryFile = temp.newFile();
      SerializationSystem.saveToFile( wrapper, temporaryFile, XmlCollectionWrapper.class );
      
      @SuppressWarnings("unchecked") //Fail fast in unit test, assumption fine. 
      XmlCollectionWrapper< String > parsedWrapper = ( XmlCollectionWrapper< String > )SerializationSystem.loadFromFile( 
               XmlCollectionWrapper.class, temporaryFile, XmlCollectionWrapper.class );
      Assert.assertNotEquals( wrapper, parsedWrapper );
      
      TestCommon.assertIterators( data.iterator(), parsedWrapper.iterator() );
   }//End Method
   
   /**
    * Test to check that the {@link XmlCollectionWrapper#iterator()} iterates correctly.
    */
   @Test public void shouldIterate(){
      List< String > data = Arrays.asList( "anything", "in", "a", "list" );
      XmlCollectionWrapper< String > wrapper = new XmlCollectionWrapper<>( data );
      TestCommon.assertIterators( data.iterator(), wrapper.iterator() );
   }//End Method

   /**
    * Test to use the add methods of the {@link XmlCollectionWrapper} and assert the additions are
    * present in the {@link Iterator}.
    */
   @Test public void shouldProgressiveAdd(){
      List< String > expected = new ArrayList<>();
      
      XmlCollectionWrapper< String > wrapper = new XmlCollectionWrapper<>();
      TestCommon.assertIterators( expected.iterator(), wrapper.iterator() );
      
      final String singleAdd = "anything";
      expected.add( singleAdd );
      wrapper.addObject( singleAdd );
      TestCommon.assertIterators( expected.iterator(), wrapper.iterator() );
      
      List< String > extrasToAddAll = Arrays.asList( "something", "else", "to", "add" );
      expected.addAll( extrasToAddAll );
      wrapper.addAll( extrasToAddAll );
      TestCommon.assertIterators( expected.iterator(), wrapper.iterator() );
      
      expected.addAll( extrasToAddAll );
      wrapper.addAll( extrasToAddAll.iterator() );
      TestCommon.assertIterators( expected.iterator(), wrapper.iterator() );
   }//End Method
}//End Class
