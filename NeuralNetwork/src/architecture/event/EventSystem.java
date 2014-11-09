/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.event;

import java.util.Observable;
import java.util.Observer;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * The {@link EventSystem} provides the public static interface to the {@link EventManagementSystem}
 * implementation.
 */
public class EventSystem {
   
   /** The instance of the {@link EventManagementSystem}. **/
   private static EventManagementSystem eventManagementSystem = new EventManagementSystemImpl();

   /**
    * {@link EventManagementSystem#observe(Enum, Observable)}.
    */
   public static void observe( Enum< ? > observableReference, Observable observable ){
      eventManagementSystem.observe( observableReference, observable );
   }// End Method
   
   /**
    * {@link EventManagementSystem#register(Enum, Observer)}.
    */
   public static void register( Enum< ? > observableReference, Observer observer ){
      eventManagementSystem.register( observableReference, observer );
   }// End Method
   
   /**
    * {@link EventManagementSystem#observeList(Enum, ObservableList)}.
    */
   public static void observeList( Enum< ? > observableReference, ObservableList< ? > observable ){
      eventManagementSystem.observeList( observableReference, observable );
   }// End Method
   
   /**
    * {@link EventManagementSystem#registerForList(Enum, ListChangeListener)}.
    */
   public static void registerForList( Enum< ? > observableReference, ListChangeListener< ? > observer ){
      eventManagementSystem.registerForList( observableReference, observer );
   }// End Method
   
   /**
    * {@link EventManagementSystem#register(Enum, EventReceiver)}.
    */
   public static void registerForEvent( Enum< ? > event, EventReceiver receiver ){
      eventManagementSystem.register( event, receiver );
   }// End Method
   
   /**
    * {@link EventManagementSystem#register(Enum, Observer)}.
    */
   public static void raiseEvent( Enum< ? > event, Object object ){
      eventManagementSystem.raiseEvent( event, object );
   }// End Method
   
}// End Class
