/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import serialization.XmlDefinitionWrapper;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

/**
 * Test for the {@link SerializableDefinition}.
 */
public class SerializableDefinitionTest {
   
   @Rule public TemporaryFolder folder = new TemporaryFolder();

   /**
    * Method to test the serialization into and back out of a {@link File} using an
    * {@link XmlDefinitionWrapper}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      List< Definition > actualTypes = new ArrayList< Definition >();
      
      DefinitionStructure.initialise();
      PropertyType testType1 = new PropertyTypeImpl( "type1", String.class );
      RequestSystem.store( testType1, PropertyType.class );
      PropertyType testType2 = new PropertyTypeImpl( "type2", Number.class );
      RequestSystem.store( testType2, PropertyType.class );
      
      Definition builder1 = new DefinitionImpl( "FirstBuilder" );
      builder1.addPropertyType( testType1 );
      actualTypes.add( builder1 );
      
      Definition builder2 = new DefinitionImpl( "SecondBuilder" );
      builder2.addPropertyType( testType2 );
      builder2.addPropertyType( testType1 );
      actualTypes.add( builder2 );
      
      XmlDefinitionWrapper serializedCollection = new XmlDefinitionWrapper();
      serializedCollection.addAllUnwrapped( actualTypes.iterator() );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlDefinitionWrapper.class, XmlDefinitionImpl.class );
      
      XmlDefinitionWrapper parsedDefinition = SerializationSystem.loadSingletonsFromFile( 
               XmlDefinitionWrapper.class, testFile, XmlDefinitionWrapper.class, XmlDefinitionImpl.class );
      Assert.assertNotNull( parsedDefinition );
      List< Definition > parsedTypes = parsedDefinition.retrieveSingletons();
      assertBuiderTypes( actualTypes, parsedTypes );
   }// End Method
   
   /**
    * Method to test the overwriting of {@link Definition}s when loading from {@link File}.
    */
   @Test public void overwriteTest() throws IOException {
      PropertyType testType1 = new PropertyTypeImpl( "type1", String.class );
      RequestSystem.store( testType1, PropertyType.class );
      PropertyType testType2 = new PropertyTypeImpl( "type2", Number.class );
      RequestSystem.store( testType2, PropertyType.class );
      
      final String builderName = "BuilderName";
      Definition writableBuilder = new DefinitionImpl( builderName );
      writableBuilder.addPropertyType( testType1 );
      
      Definition existingBuilder = new DefinitionImpl( builderName );
      existingBuilder.addPropertyType( testType2 );
      existingBuilder.addPropertyType( testType1 );
      RequestSystem.store( existingBuilder, Definition.class );
      
      XmlDefinitionWrapper serializedCollection = new XmlDefinitionWrapper();
      serializedCollection.addUnwrapped( writableBuilder );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlDefinitionWrapper.class, XmlDefinitionImpl.class );
      
      Definition retrieved = RequestSystem.retrieve( Definition.class, builderName );
      Assert.assertEquals( retrieved, existingBuilder );
      Assert.assertTrue( retrieved.hasProperty( testType1 ) );
      Assert.assertTrue( retrieved.hasProperty( testType2 ) );
      
      XmlDefinitionWrapper parsedDefinition = SerializationSystem.loadSingletonsFromFile( 
               XmlDefinitionWrapper.class, testFile, XmlDefinitionWrapper.class, XmlDefinitionImpl.class );
      Assert.assertNotNull( parsedDefinition );
      
      retrieved = RequestSystem.retrieve( Definition.class, builderName );
      Assert.assertEquals( retrieved, existingBuilder );
      Assert.assertTrue( retrieved.hasProperty( testType1 ) );
      Assert.assertFalse( retrieved.hasProperty( testType2 ) );
   }// End Method
   
   /**
    * Method to assert that two {@link List} of {@link Definition}s are identical.
    * @param actualTypes the original {@link Definition}s.
    * @param parsedTypes the parsed {@link Definition}s.
    */
   public static void assertBuiderTypes( List< Definition > actualTypes, List< Definition > parsedTypes ) {
      Assert.assertEquals( actualTypes.size(), parsedTypes.size() );
      for ( int i = 0; i < actualTypes.size(); i++ ) {
         Definition expected = actualTypes.get( i );
         Definition parsed = parsedTypes.get( i );
         Assert.assertEquals( expected.getIdentification(), parsed.getIdentification() );
         
         List< PropertyType > expectedPropertyTypes = expected.getPropertyTypes();
         List< PropertyType > parsedPropertyTypes = parsed.getPropertyTypes();
         
         Assert.assertEquals( expectedPropertyTypes.size(), parsedPropertyTypes.size() );
         for ( int j = 0; j < expectedPropertyTypes.size(); j++ ) {
            PropertyType a = expectedPropertyTypes.get( i );
            PropertyType b = parsedPropertyTypes.get( i );
            Assert.assertEquals( a.getIdentification(), b.getIdentification() );
            Assert.assertEquals( a.getTypeClass(), b.getTypeClass() );
         }
      }
   }// End Method

}// End Class
