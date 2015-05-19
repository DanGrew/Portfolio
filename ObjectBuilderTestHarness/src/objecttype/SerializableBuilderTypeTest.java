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
import serialization.XmlBuilderTypeWrapper;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

/**
 * Test for the {@link SerializableBuilderType}.
 */
public class SerializableBuilderTypeTest {
   
   @Rule public TemporaryFolder folder = new TemporaryFolder();

   /**
    * Method to test the serialization into and back out of a {@link File} using an
    * {@link XmlBuilderTypeWrapper}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      List< BuilderType > actualTypes = new ArrayList< BuilderType >();
      
      PropertyType testType1 = new PropertyTypeImpl( "type1", String.class );
      RequestSystem.store( testType1, PropertyType.class );
      PropertyType testType2 = new PropertyTypeImpl( "type2", Number.class );
      RequestSystem.store( testType2, PropertyType.class );
      
      BuilderType builder1 = new BuilderTypeImpl( "FirstBuilder" );
      builder1.addPropertyType( testType1 );
      actualTypes.add( builder1 );
      
      BuilderType builder2 = new BuilderTypeImpl( "SecondBuilder" );
      builder2.addPropertyType( testType2 );
      builder2.addPropertyType( testType1 );
      actualTypes.add( builder2 );
      
      XmlBuilderTypeWrapper serializedCollection = new XmlBuilderTypeWrapper();
      serializedCollection.addAllUnwrapped( actualTypes.iterator() );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlBuilderTypeWrapper.class, XmlBuilderTypeImpl.class );
      
      XmlBuilderTypeWrapper parsedBuilderType = SerializationSystem.loadSingletonsFromFile( 
               XmlBuilderTypeWrapper.class, testFile, XmlBuilderTypeWrapper.class, XmlBuilderTypeImpl.class );
      Assert.assertNotNull( parsedBuilderType );
      List< BuilderType > parsedTypes = parsedBuilderType.retrieveSingletons();
      
      Assert.assertEquals( actualTypes.size(), parsedTypes.size() );
      for ( int i = 0; i < actualTypes.size(); i++ ) {
         BuilderType expected = actualTypes.get( 0 );
         BuilderType parsed = parsedTypes.get( 0 );
         Assert.assertEquals( expected.getIdentification(), parsed.getIdentification() );
         
         List< PropertyType > expectedPropertyTypes = expected.getPropertyTypes();
         List< PropertyType > parsedPropertyTypes = parsed.getPropertyTypes();
         
         Assert.assertEquals( expectedPropertyTypes.size(), parsedPropertyTypes.size() );
         for ( int j = 0; i < expectedPropertyTypes.size(); i++ ) {
            PropertyType a = expectedPropertyTypes.get( i );
            PropertyType b = parsedPropertyTypes.get( i );
            Assert.assertEquals( a.getIdentification(), b.getIdentification() );
            Assert.assertEquals( a.getTypeClass(), b.getTypeClass() );
         }
      }
   }// End Method

}// End Class
