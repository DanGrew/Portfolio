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

import objecttype.BuilderType;
import objecttype.BuilderTypeImpl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import commands.parameters.ObjectBuilderClassParameterTypes;

import property.Property;
import property.PropertyImpl;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import serialization.XmlBuilderObjectWrapper;
import serialization.XmlPropertyTypeWrapper;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

/**
 * Test for the {@link SerializableBuilderObject}.
 */
public class SerializableBuilderObjectTest {
   
   @Rule public TemporaryFolder folder = new TemporaryFolder();

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
      
      BuilderType testType1 = new BuilderTypeImpl( "builder" );
      testType1.addPropertyType( name );
      testType1.addPropertyType( age );
      testType1.addPropertyType( reference );
      RequestSystem.store( testType1, BuilderType.class );
      
      BuilderObject testObject1 = new BuilderObjectImpl( testType1, "TestObject1" );
      testObject1.set( name, "NameA" );
      actualTypes.add( testObject1 );
      BuilderObject testObject2 = new BuilderObjectImpl( testType1, "TestObject2" );
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
//      final String typeName = "Type";
//      PropertyType writableType = new PropertyTypeImpl( typeName, String.class );
//      
//      PropertyType existingType = new PropertyTypeImpl( typeName, Number.class );
//      RequestSystem.store( existingType, PropertyType.class );
//      
//      Assert.assertEquals( existingType, RequestSystem.retrieve( PropertyType.class, typeName ) );
//      Assert.assertNotEquals( writableType, RequestSystem.retrieve( PropertyType.class, typeName ) );
//      
//      XmlPropertyTypeWrapper serializedCollection = new XmlPropertyTypeWrapper();
//      serializedCollection.addUnwrapped( writableType );
//      
//      File testFile = folder.newFile();
//      SerializationSystem.saveToFile( serializedCollection, testFile, XmlPropertyTypeWrapper.class, XmlPropertyTypeImpl.class );
//      
//      XmlPropertyTypeWrapper parsedPropertyType = SerializationSystem.loadSingletonsFromFile( 
//               XmlPropertyTypeWrapper.class, testFile, XmlPropertyTypeWrapper.class, XmlPropertyTypeImpl.class );
//      Assert.assertNotNull( parsedPropertyType );
//      
//      PropertyType resultingVersion = RequestSystem.retrieve( PropertyType.class, typeName );
//      Assert.assertNotNull( resultingVersion );
//      Assert.assertEquals( writableType.getTypeClass(), resultingVersion.getTypeClass() );
      Assert.fail();
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
         
         BuilderType expectedBuilder = expected.getBuilderType();
         BuilderType parsedBuilder = expected.getBuilderType();
         Assert.assertEquals( expectedBuilder, parsedBuilder );
         
         for ( PropertyType type : expectedBuilder.getPropertyTypes() ) {
            Assert.assertEquals( expected.get( type ), parsedObject.get( type ) );
         }
      }
   }// End Method

}// End Class
