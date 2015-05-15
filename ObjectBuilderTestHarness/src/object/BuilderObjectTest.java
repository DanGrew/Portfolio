/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import objecttype.BuilderType;
import objecttype.BuilderTypeImpl;

import org.junit.Assert;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link BuilderObject}.
 */
public class BuilderObjectTest {

   /**
    * Method to test the basic construction of a {@link BuilderObject} using a {@link BuilderType} proving
    * that it stores and provides the correct information.
    */
   @Test public void basicConstructionTest() {
      BuilderType type = new BuilderTypeImpl();
      
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
      
      BuilderObject object = new BuilderObjectImpl( type );
      Assert.assertNull( object.get( string ) );
      Assert.assertNull( object.get( number ) );
      
      final String testString = "testString";
      object.set( string, testString );
      Assert.assertEquals( testString, object.get( string ) );
      
      final Number testNumber = 100.01;
      object.set( number, testNumber );
      Assert.assertEquals( testNumber, object.get( number ) );
   }// End Method

}// End Class
