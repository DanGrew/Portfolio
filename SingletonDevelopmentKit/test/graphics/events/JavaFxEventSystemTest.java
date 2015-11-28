/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.events;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.event.EventSystem;
import graphics.JavaFxInitializer;
import graphics.event.JavaFxEventSystem;
import javafx.application.Platform;
import utility.ThreadWaiter;

/**
 * Test for the {@link JavaFxEventSystem}.
 */
public class JavaFxEventSystemTest {

   /** Enum for testing notifications.**/
   private enum TestEvents {
      SimpleEvent;
   }// End Enum
   
   /**
    * Method to setup the graphics for the java fx {@link Thread}.
    */
   @BeforeClass public static void setup(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
   }// End Method
   
   /**
    * {@link JavaFxEventSystem#registerForEvent(Enum, architecture.event.EventReceiver)} test.
    */
   @Test public void shouldCallBackOnPlatformThread() {
      final ThreadWaiter waiter = new ThreadWaiter();
      final List< Object > result = new ArrayList<>();
      waiter.assertions( () -> {
         Assert.assertFalse( result.isEmpty() );
         Assert.assertEquals( 1, result.size() );
      } );
      
      JavaFxEventSystem.registerForEvent( TestEvents.SimpleEvent, ( event, object ) -> {
         Assert.assertTrue( Platform.isFxApplicationThread() );
         result.add( "yep, it has come through" );
         waiter.interrupt();
      } );
      
      EventSystem.raiseEvent( TestEvents.SimpleEvent, null );
      
      waiter.waitForTrigger();
   }// End Method

}// End Class
