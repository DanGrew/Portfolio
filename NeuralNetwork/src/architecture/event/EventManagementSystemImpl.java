/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.event;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * The {@link EventManagementSystemImpl} is responsible for providing a basic
 * implementation of the {@link EventManagementSystem}.
 */
public class EventManagementSystemImpl implements EventManagementSystem {

   private Map< Observable, Enum< ? > > utilObservables;
   private Map< Enum< ? >, ObservableList< ? > > listObservables;
   
   private Map< Enum< ? >, Set< EventReceiver > > eventSubscriptions;
   private Map< Enum< ? >, Set< Observer > > utilObservers;
   
   /**
    * Constructs a new {@link EventManagementSystemImpl}.
    */
   public EventManagementSystemImpl() {
      utilObservables = new LinkedHashMap< Observable, Enum< ? > >();
      listObservables = new LinkedHashMap< Enum< ? >, ObservableList< ? > >();
      eventSubscriptions = new LinkedHashMap< Enum< ? >, Set< EventReceiver > >();
      utilObservers = new LinkedHashMap< Enum< ? >, Set< Observer > >();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void register( Enum< ? > observableReference, Observer observer ) {
      Set< Observer > observers = utilObservers.get( observableReference );
      if ( observers == null ){
         observers = new LinkedHashSet< Observer >();
         utilObservers.put( observableReference, observers );
      }
      observers.add( observer );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void observe( Enum< ? > observableReference, Observable observable ) {
      if ( utilObservables.containsKey( observableReference ) ){
         throw new IllegalArgumentException( String.format( 
                  "Util Enum %s already managed.",
                  observableReference.toString()
         ) );
      } else {
         utilObservables.put( observable, observableReference );
         observable.addObserver( ( observableObject, object ) -> notifyUtilObservers( observableObject, object ) );
      }
   }// End Method
   
   /**
    * Method to notify all {@link Observer}s that the given {@link Observable} has changed
    * with the given {@link Object}.
    * @param observable the {@link Observable} that has changed.
    * @param object the {@link Object} provided as parameter to the change.
    */
   private void notifyUtilObservers( Observable observable, Object object ){
      Enum< ? > observableReference = utilObservables.get( observable );
      Set< Observer > observers = utilObservers.get( observableReference );
      if ( observers != null ){
         for ( Observer observer : observers ){
            observer.update( observable, object );
         }
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public < T > void registerForList( Enum< ? > observableReference, ListChangeListener< T > observer ){
      ObservableList< T > observable = ( ObservableList< T > ) listObservables.get( observableReference );
      if ( observable == null ){
         throw new IllegalArgumentException( "Observable is not defined." );
      } else {
         observable.addListener( observer );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void observeList( Enum< ? > observableReference, ObservableList< ? > observable ){
      if ( listObservables.containsKey( observableReference ) ){
         throw new IllegalArgumentException( String.format( 
                  "List Enum %s already managed.",
                  observableReference.toString()
         ) );
      } else {
         listObservables.put( observableReference, observable );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void register( Enum< ? > event, EventReceiver receiver ) {
      Set< EventReceiver > receivers = eventSubscriptions.get( event );
      if ( receivers == null ){
         receivers = new LinkedHashSet< EventReceiver >();
         eventSubscriptions.put( event, receivers );
      }
      receivers.add( receiver );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void raiseEvent( Enum< ? > event, Object object ) {
      Set< EventReceiver > receivers = eventSubscriptions.get( event );
      if ( receivers != null ){
         for ( EventReceiver receiver : receivers ){
            receiver.receive( event, object );
         }
      }
   }// End Method

}// End Class
