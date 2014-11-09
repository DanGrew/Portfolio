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
 * in a static manor without breaking the model view controller pattern.
 */
public interface EventManagementSystem {
   
   public void observe( Enum< ? > observableReference, Observable observable );
   
   public void register( Enum< ? > observableReference, Observer observer );
   
   public void observeList( Enum< ? > observableReference, ObservableList< ? > observable );
   
   public < T > void registerForList( Enum< ? > observableReference, ListChangeListener< T > observer );
   
   public void register( Enum< ? > event, EventReceiver receiver );
   
   public void raiseEvent( Enum< ? > event, Object object );

}// End Interface
