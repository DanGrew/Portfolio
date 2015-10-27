/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import model.singleton.Singleton;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * {@link SingletonStringConverter} test.
 */
public class SingletonStringConverterTest {
   
   private static TestSingleton TEST_OBJECT_1;
   private static TestSingleton TEST_OBJECT_2;

   /**
    * Initialise method for the {@link Singleton}s needed for the test.
    */
   @BeforeClass public static void initialiseSingletons(){
      RequestSystem.reset();
      TEST_OBJECT_1 = new TestSingletonImpl( "anything" );
      RequestSystem.store( TEST_OBJECT_1 );
      TEST_OBJECT_2 = new TestSingletonImpl( "something" );
      RequestSystem.store( TEST_OBJECT_2 );
   }//End Method
   
   /**
    * {@link SingletonStringConverter#toString(Singleton)} test.
    */
   @Test public void shouldConvertToString() {
      SingletonStringConverter< TestSingleton > converter = new SingletonStringConverter<>( TestSingleton.class );
      Assert.assertEquals( TEST_OBJECT_1.getIdentification(), converter.toString( TEST_OBJECT_1 ) );
      Assert.assertEquals( TEST_OBJECT_2.getIdentification(), converter.toString( TEST_OBJECT_2 ) );
      Assert.assertEquals( "", converter.toString( null ) );
   }//End Method
   
   /**
    * {@link SingletonStringConverter#fromString(String)} test.
    */
   @Test public void shouldConvertFromString() {
      SingletonStringConverter< TestSingleton > converter = new SingletonStringConverter<>( TestSingleton.class );
      Assert.assertEquals( TEST_OBJECT_1, converter.fromString( TEST_OBJECT_1.getIdentification() ) );
      Assert.assertEquals( TEST_OBJECT_2, converter.fromString( TEST_OBJECT_2.getIdentification() ) );
   }//End Method

}//End Class
