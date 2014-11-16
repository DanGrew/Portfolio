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

import architecture.event.system.ManagementSystem;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * The {@link EventSystem} provides the public static interface to the {@link EventManagementSystem}
 * implementation.
 */
public class EventSystem extends ManagementSystem {
   
   /**
    * {@link EventManagementSystem#observe(Enum, Observable)}.
    */
   public static void observe( Enum< ? > observableReference, Observable observable ){
      eventSystem().observe( observableReference, observable );
   }// End Method
   
   /**
    * {@link EventManagementSystem#register(Enum, Observer)}.
    */
   public static void register( Enum< ? > observableReference, Observer observer ){
      eventSystem().register( observableReference, observer );
   }// End Method
   
   /**
    * {@link EventManagementSystem#observeList(Enum, ObservableList)}.
    */
   public static void observeList( Enum< ? > observableReference, ObservableList< ? > observable ){
      eventSystem().observeList( observableReference, observable );
   }// End Method
   
   /**
    * {@link EventManagementSystem#registerForList(Enum, ListChangeListener)}.
    */
   public static < T > void registerForList( Enum< ? > observableReference, Class< T > changeClass, ListChangeListener< T > observer ){
      eventSystem().registerForList( observableReference, changeClass, observer );
   }// End Method
   
   /**
    * {@link EventManagementSystem#register(Enum, EventReceiver)}.
    */
   public static void registerForEvent( Enum< ? > event, EventReceiver receiver ){
      eventSystem().register( event, receiver );
   }// End Method
   
   /**
    * {@link EventManagementSystem#register(Enum, Observer)}.
    */
   public static void raiseEvent( Enum< ? > event, Object object ){
      eventSystem().raiseEvent( event, object );
   }// End Method
   
}// End Class
