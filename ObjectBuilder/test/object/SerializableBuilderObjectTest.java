/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.singleton.Singleton;
import objecttype.Definition;
import objecttype.DefinitionImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import serialization.XmlBuilderObjectWrapper;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;
import commands.parameters.ObjectBuilderClassParameterTypes;

/**
 * Test for the {@link SerializableBuilderObject}.
 */
public class SerializableBuilderObjectTest {
   
   @Rule public TemporaryFolder folder = new TemporaryFolder();

   /**
    * Method to setup the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      RequestSystem.reset();
   }// End Method
   
   /**
    * Method to test the serialization of {@link BuilderObject}s using {@link XmlBuilderObjectImpl}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      List< BuilderObject > actualTypes = new ArrayList< BuilderObject >();
      
      PropertyType name = new PropertyTypeImpl( "Name", String.class );
      RequestSystem.store( name, PropertyType.class );
      PropertyType age = new PropertyTypeImpl( "Age", Number.class );
      RequestSystem.store( age, PropertyType.class );
      PropertyType reference = new PropertyTypeImpl( "Age", ObjectBuilderClassParameterTypes.PROPERTY_TYPE_PARAMETER_TYPE );
      RequestSystem.store( reference, PropertyType.class );
      
      Definition testType1 = new DefinitionImpl( "builder" );
      testType1.addPropertyType( name );
      testType1.addPropertyType( age );
      testType1.addPropertyType( reference );
      RequestSystem.store( testType1, Definition.class );
      
      BuilderObject testObject1 = new BuilderObjectImpl( "TestObject1", testType1 );
      testObject1.set( name, "NameA" );
      actualTypes.add( testObject1 );
      BuilderObject testObject2 = new BuilderObjectImpl( "TestObject2", testType1 );
      testObject2.set( name, "NameB" );
      testObject2.set( age, 10 );
      testObject2.set( reference, testType1 );
      actualTypes.add( testObject2 );
      
      XmlBuilderObjectWrapper serializedCollection = new XmlBuilderObjectWrapper();
      serializedCollection.addAllUnwrapped( actualTypes.iterator() );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlBuilderObjectWrapper.class, XmlBuilderObjectImpl.class );
      
      XmlBuilderObjectWrapper parsedPropertyType = SerializationSystem.loadSingletonsFromFile( 
               XmlBuilderObjectWrapper.class, testFile, XmlBuilderObjectWrapper.class, XmlBuilderObjectImpl.class );
      Assert.assertNotNull( parsedPropertyType );
      List< BuilderObject > parsedTypes = parsedPropertyType.retrieveSingletons();
      assertBuilderObjectLists( actualTypes, parsedTypes );
   }// End Method
   
   /**
    * Method to test that when reading a {@link PropertyType} that already exists, it is overwritten. 
    */
   @Test public void overwriteTest() throws IOException{
      PropertyType name = new PropertyTypeImpl( "Name", String.class );
      RequestSystem.store( name, PropertyType.class );
      
      Definition testType1 = new DefinitionImpl( "builder" );
      testType1.addPropertyType( name );
      RequestSystem.store( testType1, Definition.class );
      
      BuilderObject toWrite = new BuilderObjectImpl( "TestObject1", testType1 );
      toWrite.set( name, "NameC" );
      
      BuilderObject toOverwrite = new BuilderObjectImpl( "TestObject1", testType1 );
      toWrite.set( name, "NameB" );
      RequestSystem.store( toOverwrite, BuilderObject.class );
      
      XmlBuilderObjectWrapper serializedCollection = new XmlBuilderObjectWrapper();
      serializedCollection.addUnwrapped( toWrite );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlBuilderObjectWrapper.class, XmlBuilderObjectImpl.class );
      
      XmlBuilderObjectWrapper parsedPropertyType = SerializationSystem.loadSingletonsFromFile( 
               XmlBuilderObjectWrapper.class, testFile, XmlBuilderObjectWrapper.class, XmlBuilderObjectImpl.class );
      Assert.assertNotNull( parsedPropertyType );
      
      BuilderObject systemVersion = RequestSystem.retrieve( BuilderObject.class, toOverwrite.getIdentification() );
      Assert.assertEquals( toOverwrite, systemVersion );
      Assert.assertEquals( toWrite.get( name ), systemVersion.get( name ) );
   }// End Method
   
   /**
    * Method to assert that two {@link List} of {@link BuilderObject}s are identical.
    * @param actual the original {@link BuilderObject}s.
    * @param parsed the parsed {@link BuilderObject}s.
    */
   public static void assertBuilderObjectLists( List< BuilderObject > actual, List< BuilderObject > parsed){
      Assert.assertEquals( actual.size(), parsed.size() );
      for ( int i = 0; i < actual.size(); i++ ) {
         BuilderObject expected = actual.get( 0 );
         BuilderObject parsedObject = parsed.get( 0 );
         Assert.assertEquals( expected.getIdentification(), parsedObject.getIdentification() );
         
         Definition expectedBuilder = expected.getDefinition();
         Definition parsedBuilder = expected.getDefinition();
         Assert.assertEquals( expectedBuilder, parsedBuilder );
         
         for ( PropertyType type : expectedBuilder.getPropertyTypes() ) {
            Assert.assertEquals( expected.get( type ), parsedObject.get( type ) );
         }
      }
   }// End Method

}// End Class
