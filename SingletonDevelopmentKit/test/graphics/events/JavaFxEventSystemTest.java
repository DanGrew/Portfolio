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
import graphics.event.JavaFxEventSystem;
import javafx.JavaFxInitializer;
import javafx.application.Platform;

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
   @Test public void shouldCallBackOnPlatformThread() throws InterruptedException {
      final List< Object > result = new ArrayList<>();
      JavaFxEventSystem.registerForEvent( TestEvents.SimpleEvent, ( event, object ) -> {
         Assert.assertTrue( Platform.isFxApplicationThread() );
         result.add( "yep, it has come through" );
      } );
      
      EventSystem.raiseEvent( TestEvents.SimpleEvent, null );
      
      //Expect no more than a second of waiting.
      Thread.sleep( 1000 );
      Assert.assertFalse( result.isEmpty() );
      Assert.assertEquals( 1, result.size() );
   }// End Method

}// End Class
