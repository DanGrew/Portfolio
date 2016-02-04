/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * {@link DataManagementSystem} processing test.
 */
public class DmsProcessTest {
   
   private static TestSingleton TEST_SINGLETON_1;
   private static TestSingleton TEST_SINGLETON_2;
   private static TestSingleton TEST_SINGLETON_3;
   private static TestSingleton TEST_SINGLETON_4;
   
   /**
    * Method to initialise the {@link Singleton}s for the test.
    */
   @BeforeClass public static void initialiseSingletons(){
      RequestSystem.reset();
      TEST_SINGLETON_1 = new TestSingletonImpl( "anything" );
      RequestSystem.store( TEST_SINGLETON_1 );
      TEST_SINGLETON_2 = new TestSingletonImpl( "something" );
      RequestSystem.store( TEST_SINGLETON_2 );
      TEST_SINGLETON_3 = new TestSingletonImpl( "else" );
      RequestSystem.store( TEST_SINGLETON_3 );
      TEST_SINGLETON_4 = new TestSingletonImpl( "finally" );
      RequestSystem.store( TEST_SINGLETON_4 );
   }//End Method

   /**
    * {@link RequestSystem#process(Class, String, java.util.function.Consumer)} test.
    */
   @Test public void shouldProcessSingle() {
      List< String > toPopulate = new ArrayList<>();
      RequestSystem.process( 
               TestSingleton.class, 
               TEST_SINGLETON_1.getIdentification(), 
               ( singleton ) -> {
                  toPopulate.add( singleton.getIdentification() );
               }
      );
      Assert.assertEquals( 1, toPopulate.size() );
      Assert.assertEquals( TEST_SINGLETON_1.getIdentification(), toPopulate.get( 0 ) );
   }//End Method
   
   /**
    * {@link RequestSystem#process(Class, java.util.function.Predicate, java.util.function.Consumer)} test.
    */
   @Test public void shouldProcessAll() {
      List< String > toPopulate = new ArrayList<>();
      RequestSystem.process( 
               TestSingleton.class, 
               ( singleton ) -> { return true; }, 
               ( singleton ) -> {
                  toPopulate.add( singleton.getIdentification() );
               }
      );
      Assert.assertEquals( 4, toPopulate.size() );
      Assert.assertEquals( TEST_SINGLETON_1.getIdentification(), toPopulate.get( 0 ) );
      Assert.assertEquals( TEST_SINGLETON_2.getIdentification(), toPopulate.get( 1 ) );
      Assert.assertEquals( TEST_SINGLETON_3.getIdentification(), toPopulate.get( 2 ) );
      Assert.assertEquals( TEST_SINGLETON_4.getIdentification(), toPopulate.get( 3 ) );
   }//End Method

}//End Class
