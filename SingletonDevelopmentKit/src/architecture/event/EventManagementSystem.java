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
 * The {@link EventManagementSystem} provides the interface for a system responsible for
 * defining the observer pattern in an event system that provides access to different structures
 * in a static manor without breaking the model view controller pattern, using the mediator pattern.
 */
public interface EventManagementSystem {
   
   /**
    * Method to add the given {@link Observable} to the {@link EventManagementSystem} so that it
    * can be observed by {@link Observer}s in the system.
    * @param observableReference {@link Enum} representing the item to observe.
    * @param observable the {@link Observable} that can be Observed.
    */
   public void observe( Enum< ? > observableReference, Observable observable );
   
   /**
    * Method to register the given {@link Observer} with the {@link Observable} associated with the
    * given {@link Enum}.
    * @param observableReference the {@link Enum} representing the {@link Observable}.
    * @param observer the {@link Observer} of the {@link Observable}.
    */
   public void register( Enum< ? > observableReference, Observer observer );
   
   /**
    * Method to add the given {@link ObservableList} to the {@link EventManagementSystem} so that it
    * can be observed by {@link ListChangeListener}s in the system.
    * @param observableReference {@link Enum} representing the item to observe.
    * @param observable the {@link ObservableList} that can be Observed.
    */
   public void observeList( Enum< ? > observableReference, ObservableList< ? > observable );
   
   /**
    * Method to register the given {@link ListChangeListener} with the {@link ObservableList} associated with the
    * given {@link Enum}.
    * @param observableReference the {@link Enum} representing the {@link ObservableList}.
    * @param changeClass the class associated with the {@link ObservableList} providing the generic to the 
    * {@link ListChangeListener}.
    * @param observer the {@link ListChangeListener} of the {@link ObservableList}.
    */
   public < T > void registerForList( 
            Enum< ? > observableReference, Class< T > changeClass, ListChangeListener< T > observer 
   );
   
   /**
    * Method to register the given {@link EventReceiver} with the {@link EventManagementSystem} for the given {@link Enum}
    * event.
    * @param event the {@link Enum} representing the event.
    * @param receiver the {@link EventReceiver} to receive the event.
    */
   public void register( Enum< ? > event, EventReceiver receiver );
   
   /**
    * Method to raise an event for the given {@link Enum} event type with the associated {@link Object}.
    * @param event the {@link Enum} representing the event.
    * @param object the {@link Object} provided as source to the event.
    */
   public void raiseEvent( Enum< ? > event, Object object );

}// End Interface
