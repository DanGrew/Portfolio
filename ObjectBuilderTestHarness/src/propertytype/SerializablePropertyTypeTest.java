/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import serialization.XmlPropertyTypeWrapper;
import architecture.serialization.SerializationSystem;

/**
 * Test for the {@link SerializablePropertyType}.
 */
public class SerializablePropertyTypeTest {
   
   @Rule public TemporaryFolder folder = new TemporaryFolder();

   /**
    * Method to test the serialization of {@link PropertyType}s using {@link XmlPropertyTypeWrapper}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      List< PropertyType > actualTypes = new ArrayList< PropertyType >();
      
      PropertyType testType1 = new PropertyTypeImpl( "type1", String.class );
      actualTypes.add( testType1 );
      
      PropertyType testType2 = new PropertyTypeImpl( "type2", Number.class );
      actualTypes.add( testType2 );
      
      PropertyType testType3 = new PropertyTypeImpl( "type3", String.class );
      actualTypes.add( testType3 );
      
      XmlPropertyTypeWrapper serializedCollection = new XmlPropertyTypeWrapper();
      serializedCollection.addAllUnwrapped( actualTypes.iterator() );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlPropertyTypeWrapper.class, XmlPropertyTypeImpl.class );
      
      XmlPropertyTypeWrapper parsedPropertyType = SerializationSystem.loadSingletonsFromFile( 
               XmlPropertyTypeWrapper.class, testFile, XmlPropertyTypeWrapper.class, XmlPropertyTypeImpl.class );
      Assert.assertNotNull( parsedPropertyType );
      List< PropertyType > parsedTypes = parsedPropertyType.retrieveSingletons();
      
      Assert.assertEquals( actualTypes.size(), parsedTypes.size() );
      for ( int i = 0; i < actualTypes.size(); i++ ) {
         PropertyType expected = actualTypes.get( 0 );
         PropertyType parsed = parsedTypes.get( 0 );
         Assert.assertEquals( expected.getDisplayName(), parsed.getDisplayName() );
         Assert.assertEquals( expected.getTypeClass(), parsed.getTypeClass() );
      }
   }// End Method

}// End Class
