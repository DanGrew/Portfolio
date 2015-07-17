/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import objecttype.Definition;
import objecttype.DefinitionImpl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link BuilderObject}.
 */
public class BuilderObjectTest {

   /**
    * Method to test the basic construction of a {@link BuilderObject} using a {@link Definition} proving
    * that it stores and provides the correct information.
    */
   @Test public void basicConstructionTest() {
      Definition type = new DefinitionImpl( "Test" );
      
      final String stringDisplay = "StringValue";
      final Class< ? > stringClass = String.class;
      PropertyType string = new PropertyTypeImpl( stringDisplay, stringClass );
      type.addPropertyType( string );
      Assert.assertTrue( type.hasProperty( string ) );
      
      final String numberDisplay = "NumberValue";
      final Class< ? > numberClass = Number.class;
      PropertyType number = new PropertyTypeImpl( numberDisplay, numberClass );
      type.addPropertyType( number );
      Assert.assertTrue( type.hasProperty( number ) );  
      
      BuilderObject object = new BuilderObjectImpl( "Test", type );
      Assert.assertNull( object.get( string ) );
      Assert.assertNull( object.get( number ) );
      
      final String testString = "testString";
      object.set( string, testString );
      Assert.assertEquals( testString, object.get( string ) );
      
      final Number testNumber = 100.01;
      object.set( number, testNumber );
      Assert.assertEquals( testNumber, object.get( number ) );
   }// End Method
   
   /**
    * Method to perform a basic construction test using the {@link Mockito} framework.
    */
   @Test public void mockitoBasicConstructionTest(){
      PropertyType testPropertyTypeA = Mockito.mock( PropertyType.class, "PropertyA" );
      Mockito.when( testPropertyTypeA.isCorrectType( Mockito.anyObject() ) ).thenReturn( true );
      
      Definition testTypeA = Mockito.mock( Definition.class, "TypeA" );
      Mockito.when( testTypeA.hasProperty( testPropertyTypeA ) ).thenReturn( true );
      
      BuilderObject testObject = new BuilderObjectImpl( "TestName", testTypeA );
      final Object testValue = "Anything";
      testObject.set( testPropertyTypeA, testValue );
      Object value = testObject.get( testPropertyTypeA );
      Assert.assertEquals( testValue, value );
   }// End Method

}// End Class
