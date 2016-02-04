/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.event;

import architecture.event.EventManagementSystem;
import architecture.event.EventReceiver;

/**
 * The {@link JavaFxEventSystem} provides an {@link EventSystem} that automatically
 * redirects all notifications on the java fx {@link Thread}.
 */
public class JavaFxEventSystem {
   
   private static final EventManagementSystem JFX_EVENTS = new JavaFxEventManagementSystemImpl();

   /**
    * {@link JavaFxEventManagementSystemImpl#register(Enum, EventReceiver)}.
    */
   public static void registerForEvent( Enum< ? > event, EventReceiver receiver ) {
      JFX_EVENTS.register( event, receiver );
   }// End Method

}// End Class
