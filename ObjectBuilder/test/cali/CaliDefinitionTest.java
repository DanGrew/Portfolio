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

import objecttype.DefinitionImpl;
import propertytype.PropertyType;
import system.CaliSystem;

/**
 * {@link DefinitionImpl} test for {@link Cali} usage.
 */
public class CaliDefinitionTest {

   /**
    * Method to setup up the {@link CaliSystem}.
    */
   @BeforeClass public static void setup(){
      CaliSystem.register( DefinitionImpl.class );
   }//End Method

   /**
    * Method to to test that the expected methods are found by the {@link CaliSystem}.
    */
   @Test public void shouldFindConstructorAndMethods() throws NoSuchMethodException, SecurityException {
      Assert.assertEquals( 
               DefinitionImpl.class.getConstructor( String.class ), 
               CaliSystem.matchConstructor( "DefinitionImpl", String.class )
      );
      Assert.assertEquals( 
               DefinitionImpl.class.getMethod( "addPropertyType", PropertyType.class ), 
               CaliSystem.matchMethodSignature( DefinitionImpl.class, "addPropertyType", PropertyType.class )
      );
   }//End Method

}//End Class
