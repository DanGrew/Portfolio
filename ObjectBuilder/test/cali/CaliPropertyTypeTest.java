/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package cali;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytype.PropertyTypeImpl;
import system.CaliSystem;

/**
 * {@link PropertyTypeImpl} test for {@link Cali} usage.
 */
public class CaliPropertyTypeTest {

   /**
    * Method to setup up the {@link CaliSystem}.
    */
   @BeforeClass public static void setup(){
      CaliSystem.register( PropertyTypeImpl.class );
   }//End Method

   /**
    * Method to to test that the expected methods are found by the {@link CaliSystem}.
    */
   @Test public void shouldFindConstructorAndMethods() throws NoSuchMethodException, SecurityException {
      Assert.assertEquals( 
               PropertyTypeImpl.class.getConstructor( String.class, String.class ), 
               CaliSystem.matchConstructor( "PropertyTypeImpl", String.class, String.class )
      );
   }//End Method

}//End Class
