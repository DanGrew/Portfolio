/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package serialization;

import gui.ObjectBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.singleton.Singleton;
import object.BuilderObject;
import object.BuilderObjectImpl;
import object.SerializableBuilderObjectTest;
import object.XmlBuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import objecttype.DefinitionStructure;
import objecttype.SerializableDefinitionTest;
import objecttype.XmlDefinitionImpl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import propertytype.SerializablePropertyTypeTest;
import propertytype.XmlPropertyTypeImpl;
import architecture.serialization.SerializationSystem;

/**
 * {@link ObjectBuilderSystemSerializationTest} is responsible for test that an {@link XmlObjectBuilderSystemWrapper} can
 * serialize a number of {@link Singleton}s in the {@link ObjectBuilder} system.
 */
public class ObjectBuilderSystemSerializationTest {

   @Rule public TemporaryFolder folder = new TemporaryFolder();

   /**
    * Method to test the serialization into and back out of a {@link File} using an
    * {@link XmlObjectBuilderSystemWrapper}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      DefinitionStructure.initialise();
      List< PropertyType > actualPropertyTypes = new ArrayList< PropertyType >();
      PropertyType testType1 = new PropertyTypeImpl( "type1", String.class );
      actualPropertyTypes.add( testType1 );
      PropertyType testType2 = new PropertyTypeImpl( "type2", Number.class );
      actualPropertyTypes.add( testType2 );
      PropertyType testType3 = new PropertyTypeImpl( "type3", ClassParameterTypes.DATE_PARAMETER_TYPE );
      actualPropertyTypes.add( testType3 );
      
      List< Definition > actualDefinitions = new ArrayList< Definition >();
      Definition builder1 = new DefinitionImpl( "FirstBuilder" );
      builder1.addPropertyType( testType1 );
      actualDefinitions.add( builder1 );
      
      Definition builder2 = new DefinitionImpl( "SecondBuilder" );
      builder2.addPropertyType( testType3 );
      builder2.addPropertyType( testType2 );
      builder2.addPropertyType( testType1 );
      actualDefinitions.add( builder2 );
      
      List< BuilderObject > actualObjects = new ArrayList<>();
      BuilderObject object1 = new BuilderObjectImpl( "object1", builder1 );
      object1.set( testType1, "TestString" );
      actualObjects.add( object1 );
      BuilderObject object2 = new BuilderObjectImpl( "object2", builder2 );
      object2.set( testType2, 67988.1298 );
      object2.set( testType3, "25/03/15" );
      actualObjects.add( object2 );
      
      XmlObjectBuilderSystemWrapper serializedCollection = new XmlObjectBuilderSystemWrapper();
      serializedCollection.addAllPropertyTypes( actualPropertyTypes );
      serializedCollection.addAllDefinitions( actualDefinitions );
      serializedCollection.addAllBuilderObjects( actualObjects );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( 
               serializedCollection, 
               testFile, 
               XmlObjectBuilderSystemWrapper.class, 
               XmlPropertyTypeImpl.class, 
               XmlDefinitionImpl.class, 
               XmlBuilderObjectImpl.class
      );
      
      XmlObjectBuilderSystemWrapper parsedSystem = SerializationSystem.loadWrapperFromFile( 
               XmlObjectBuilderSystemWrapper.class, 
               testFile, 
               XmlObjectBuilderSystemWrapper.class, 
               XmlPropertyTypeImpl.class, 
               XmlDefinitionImpl.class,
               XmlBuilderObjectImpl.class
      );
      Assert.assertNotNull( parsedSystem );
      
      List< PropertyType > parsedPropertyTypes = parsedSystem.retrievePropertyTypes();
      SerializablePropertyTypeTest.assertPropertyTypeLists( actualPropertyTypes, parsedPropertyTypes );

      List< Definition > parsedDefinitions = parsedSystem.retrieveDefinitions();
      SerializableDefinitionTest.assertBuiderTypes( actualDefinitions, parsedDefinitions );
      
      List< BuilderObject > parsedBuilderObjects = parsedSystem.retrieveBuilderObjects();
      SerializableBuilderObjectTest.assertBuilderObjectLists( actualObjects, parsedBuilderObjects );
   }// End Method

}// End Class