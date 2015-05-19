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
import objecttype.BuilderType;
import objecttype.BuilderTypeImpl;
import objecttype.SerializableBuilderTypeTest;
import objecttype.XmlBuilderTypeImpl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

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
      List< PropertyType > actualPropertyTypes = new ArrayList< PropertyType >();
      PropertyType testType1 = new PropertyTypeImpl( "type1", String.class );
      actualPropertyTypes.add( testType1 );
      PropertyType testType2 = new PropertyTypeImpl( "type2", Number.class );
      actualPropertyTypes.add( testType2 );
      
      List< BuilderType > actualBuilderTypes = new ArrayList< BuilderType >();
      BuilderType builder1 = new BuilderTypeImpl( "FirstBuilder" );
      builder1.addPropertyType( testType1 );
      actualBuilderTypes.add( builder1 );
      
      BuilderType builder2 = new BuilderTypeImpl( "SecondBuilder" );
      builder2.addPropertyType( testType2 );
      builder2.addPropertyType( testType1 );
      actualBuilderTypes.add( builder2 );
      
      XmlObjectBuilderSystemWrapper serializedCollection = new XmlObjectBuilderSystemWrapper();
      serializedCollection.addAllPropertyTypes( actualPropertyTypes );
      serializedCollection.addAllBuilderTypes( actualBuilderTypes );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlObjectBuilderSystemWrapper.class, XmlPropertyTypeImpl.class, XmlBuilderTypeImpl.class );
      
      XmlObjectBuilderSystemWrapper parsedSystem = SerializationSystem.loadWrapperFromFile( 
               XmlObjectBuilderSystemWrapper.class, testFile, XmlObjectBuilderSystemWrapper.class, XmlPropertyTypeImpl.class, XmlBuilderTypeImpl.class );
      Assert.assertNotNull( parsedSystem );
      
      List< PropertyType > parsedPropertyTypes = parsedSystem.retrievePropertyTypes();
      SerializablePropertyTypeTest.assertPropertyTypeLists( actualPropertyTypes, parsedPropertyTypes );

      List< BuilderType > parsedBuilderTypes = parsedSystem.retrieveBuilderTypes();
      SerializableBuilderTypeTest.assertBuiderTypes( actualBuilderTypes, parsedBuilderTypes );
   }// End Method

}// End Class