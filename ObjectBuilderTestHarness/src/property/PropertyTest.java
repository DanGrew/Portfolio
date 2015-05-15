/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package property;

import org.junit.Assert;
import org.junit.Test;

import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link Property} interface.
 */
public class PropertyTest {

   /**
    * Method to test the basic usage of a {@link Property}, setting the value and getting it.
    */
   @Test public void basicGetSetTest() {
      String testValue = "TestValue";
      String testName = "TestName";
      Class< ? > testClass = String.class;
      
      PropertyType type = new PropertyTypeImpl( testName, testClass );
      Property property = new PropertyImpl( type );
      property.setValue( testValue );
      Assert.assertEquals( testName, property.getDisplayName() );
      Assert.assertEquals( testValue, property.getValue() );
      Assert.assertEquals( testClass, property.getType().getTypeClass() );
   }// End Method
   
   /**
    * Method to test a {@link String} {@link Property} works correctly.
    */
   @Test public void basicStringTest() {
      final String testName = "testName";
      PropertyType type = new PropertyTypeImpl( testName, String.class );
      Property string = new PropertyImpl( type );
      Assert.assertEquals( testName, type.getDisplayName() );
      Assert.assertTrue( string.isCorrectType( "Test" ) );
   }// End Method
   
   /**
    * Method to test a basic {@link Number} {@link Property} works correctly.
    */
   @Test public void basicNumberTest(){
      final String testName = "testName";
      PropertyType type = new PropertyTypeImpl( testName, Number.class );
      Property number = new PropertyImpl( type );
      Assert.assertEquals( testName, number.getDisplayName() );
      Assert.assertTrue( number.isCorrectType( 100 ) );
      Assert.assertTrue( number.isCorrectType( 100.1 ) );
      Assert.assertTrue( number.isCorrectType( 0.1 ) );
   }// End Method

}// End Class
