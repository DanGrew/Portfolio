/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.ArrayList;
import java.util.List;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.Test;

import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.event.EventSystem;
import architecture.request.RequestSystem;

/**
 * Test for the {@link DataManagementSystem}'s notifications.
 */
public class DmsNotificationTest {

   /**
    * Method to test that an event is received.
    */
   @Test public void shouldReceiveEvent() {
      List< Object > results = new ArrayList<>();
      TestSingleton testSingleton = new TestSingletonImpl( "anything" );
      Class< ? > testClassA = TestSingletonImpl.class;
      Class< ? > testClassB = TestSingleton.class;
      Class< ? > testClassC = Singleton.class;
      
      EventSystem.registerForEvent( DataManagementSystem.Events.ObjectStored, ( object, source ) -> {
         SingletonStoredSource sourceValue = ( SingletonStoredSource )source;
         results.add( sourceValue.storedObject );
         results.addAll( sourceValue.classes );
      } );
      
      RequestSystem.store( testSingleton, testClassA, testClassB, testClassC );
      
      Assert.assertFalse( results.isEmpty() );
      Assert.assertEquals( testSingleton, results.get( 0 ) );
      Assert.assertEquals( testClassA, results.get( 1 ) );
      Assert.assertEquals( testClassB, results.get( 2 ) );
      Assert.assertEquals( testClassC, results.get( 3 ) );
   }// End Method

}// End Class
