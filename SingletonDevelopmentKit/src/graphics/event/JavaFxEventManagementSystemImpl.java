/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import architecture.event.EventManagementSystem;
import architecture.event.EventReceiver;
import architecture.event.EventSystem;

/**
 * The {@link JavaFxEventManagementSystemImpl} provides an implementation of the {@link EventManagementSystem}
 * that redirects all events onto the {@link Platform}'s java fx {@link Thread}.
 */
public class JavaFxEventManagementSystemImpl implements EventManagementSystem {
   
   private Map< EventRedirect, EventReceiver > redirects;
   
   /**
    * {@link EventRedirect} provides an inner class that redirects event notifications
    * onto the java fx {@link Thread}.
    */
   private class EventRedirect implements EventReceiver {

      /**
       * {@inheritDoc}
       */
      @Override public void receive( Enum< ? > event, Object object ) {
         EventReceiver receiver = redirects.get( this );
         if ( receiver != null ) {
            Platform.runLater( () -> {
               receiver.receive( event, object );
            } );
         }
      }// End Method
      
   }// End Class
   
   /**
    * Constructs a new {@link JavaFxEventManagementSystemImpl}.
    */
   public JavaFxEventManagementSystemImpl() {
      redirects = new HashMap<>();
   }// End Constructor

   @Override public void observe( Enum< ? > observableReference, Observable observable ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }

   @Override public void register( Enum< ? > observableReference, Observer observer ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }

   @Override public void observeList( Enum< ? > observableReference, ObservableList< ? > observable ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }

   @Override public < T > void registerForList( Enum< ? > observableReference, Class< T > changeClass, ListChangeListener< T > observer ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }

   /**
    * {@inheritDoc}
    */
   @Override public void register( Enum< ? > event, EventReceiver receiver ) {
      EventRedirect redirect = new EventRedirect();
      redirects.put( redirect, receiver );
      EventSystem.registerForEvent( event, redirect );
   }// End Method

   @Override public void raiseEvent( Enum< ? > event, Object object ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }

}// End Class
