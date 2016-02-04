/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import architecture.event.EventSystem;
import javafx.concurrent.Service;

/**
 * The {@link TaskProcessor} is responsible for managing {@link Task}s that should be processed
 * outside the JavaFx {@link Thread}.
 */
public class TaskProcessor {
   
   /** {@link Map} of {@link Enum}s defining the events that trigger the {@link Task}s, to
    * the {@link EventService}s that define the {@link Task}s.**/
   private Map< Enum< ? >, EventService< ? > > tasks;
   
   /**
    * Constructs a new {@link TaskProcessor}.
    */
   public TaskProcessor(){
      tasks = new HashMap< Enum< ? >, EventService< ? > >();
   }// End Constructor
   
   /**
    * Method to add a new task to the {@link TaskProcessor}.
    * @param taskTriggerEvent the {@link Enum} defining the event to respond to.
    * @param task the {@link Function} defining the {@link Task} to process when the
    * event is resceived.
    */
   public < T > void addTask( Enum< ? > taskTriggerEvent, Function< Object, T > task ){
      tasks.put( taskTriggerEvent, new EventService< T >( task ) );
      EventSystem.registerForEvent( taskTriggerEvent, ( type, object ) -> start( taskTriggerEvent, object ) );
   }// End Method
   
   /**
    * Method to start the {@link Task} for the given {@link Enum} of the event received and
    * the {@link Object} parameter associated with the event.
    */
   public void start( Enum< ? > task, Object parameter ){
      EventService< ? > service = tasks.get( task );
      if ( service != null ){
         service.setParameter( parameter );
         service.restart();
      } else {
         System.out.println( "Task not defined for " + task.toString() + "." );
      }
   }// End Method
   
   /**
    * Method to cancel the {@link Task} associated with the given {@link Enum} event.
    * @param task the {@link Enum} defining the event received, mapping to the {@link Task} to
    * be processed.
    * @return whether the {@link Task} has been cancelled.
    */
   public boolean cancel( Enum< ? > task ){
      Service< ? > service = tasks.get( task );
      if ( service != null ){
         return service.cancel();
      } else {
         System.out.println( "Task not defined for " + task.toString() + "." );
         return false;
      }
   }// End Method

}// End Class
