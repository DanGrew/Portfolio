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

import object.BuilderObjectImpl;
import objecttype.Definition;
import propertytype.PropertyType;
import system.CaliSystem;

/**
 * {@link BuilderObjectImpl} test for {@link Cali} usage.
 */
public class CaliBuilderObjectTest {
   
   /**
    * Method to setup up the {@link CaliSystem}.
    */
   @BeforeClass public static void setup(){
      CaliSystem.register( BuilderObjectImpl.class );
   }//End Method

   /**
    * Method to to test that the expected methods are found by the {@link CaliSystem}.
    */
   @Test public void shouldFindConstructorAndMethods() throws NoSuchMethodException, SecurityException {
      Assert.assertEquals( 
               BuilderObjectImpl.class.getConstructor( String.class, Definition.class ), 
               CaliSystem.matchConstructor( "BuilderObject", String.class, Definition.class )
      );
      Assert.assertEquals( 
               BuilderObjectImpl.class.getMethod( "getDefinition" ), 
               CaliSystem.matchMethodSignature( BuilderObjectImpl.class, "getDefinition" )
      );
      Assert.assertEquals( 
               BuilderObjectImpl.class.getMethod( "get", PropertyType.class ), 
               CaliSystem.matchMethodSignature( BuilderObjectImpl.class, "get", PropertyType.class )
      );
      Assert.assertEquals( 
               BuilderObjectImpl.class.getMethod( "set", PropertyType.class, Object.class ), 
               CaliSystem.matchMethodSignature( BuilderObjectImpl.class, "set", PropertyType.class, Object.class )
      );
   }//End Method

}//End Class
