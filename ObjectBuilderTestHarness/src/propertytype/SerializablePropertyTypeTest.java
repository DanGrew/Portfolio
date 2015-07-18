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
import architecture.request.RequestSystem;
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
      assertPropertyTypeLists( actualTypes, parsedTypes );
   }// End Method
   
   /**
    * Method to test that when reading a {@link PropertyType} that already exists, it is overwritten. 
    */
   @Test public void overwriteTest() throws IOException{
      final String typeName = "Type";
      PropertyType writableType = new PropertyTypeImpl( typeName, String.class );
      
      PropertyType existingType = new PropertyTypeImpl( typeName, Number.class );
      RequestSystem.store( existingType, PropertyType.class );
      
      Assert.assertEquals( existingType, RequestSystem.retrieve( PropertyType.class, typeName ) );
      Assert.assertNotEquals( writableType, RequestSystem.retrieve( PropertyType.class, typeName ) );
      
      XmlPropertyTypeWrapper serializedCollection = new XmlPropertyTypeWrapper();
      serializedCollection.addUnwrapped( writableType );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlPropertyTypeWrapper.class, XmlPropertyTypeImpl.class );
      
      XmlPropertyTypeWrapper parsedPropertyType = SerializationSystem.loadSingletonsFromFile( 
               XmlPropertyTypeWrapper.class, testFile, XmlPropertyTypeWrapper.class, XmlPropertyTypeImpl.class );
      Assert.assertNotNull( parsedPropertyType );
      
      PropertyType resultingVersion = RequestSystem.retrieve( PropertyType.class, typeName );
      Assert.assertNotNull( resultingVersion );
      Assert.assertEquals( writableType.getTypeClass(), resultingVersion.getTypeClass() );
   }// End Method
   
   /**
    * Method to assert that two {@link List} of {@link PropertyType}s are identical.
    * @param actualTypes the original {@link PropertyType}s.
    * @param parsedTypes the parsed {@link PropertyType}s.
    */
   public static void assertPropertyTypeLists( List< PropertyType > actualTypes, List< PropertyType > parsedTypes){
      Assert.assertEquals( actualTypes.size(), parsedTypes.size() );
      for ( int i = 0; i < actualTypes.size(); i++ ) {
         PropertyType expected = actualTypes.get( i );
         PropertyType parsed = parsedTypes.get( i );
         Assert.assertEquals( expected.getDisplayName(), parsed.getDisplayName() );
         Assert.assertEquals( expected.getTypeClass(), parsed.getTypeClass() );
      }
   }// End Method

}// End Class
