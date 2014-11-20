/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture;

import java.util.function.Function;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * The {@link EventService} defines an extension to {@link Service} that uses a parameter to
 * perform some processing, on a separate thread (not the JavaFx thread).
 */
public class EventService< T > extends Service< T >{
   
   /** The {@link Function} defining what the {@link Task} should do. **/
   private Function< Object, T > task;
   /** The {@link Object} parameter. **/
   private Object parameter;
   
   /**
    * Constructs a new {@link EventService}.
    * @param task the {@link Function} defining the {@link Task}.
    */
   public EventService( Function< Object, T > task ) {
      this.task = task;
   }// End Constructor
   
   /**
    * Method to set the parameter used for the {@link Task}.
    * @param parameter the {@link Object} parameter.
    */
   public void setParameter( Object parameter ){
      this.parameter = parameter;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected Task< T > createTask() {
      return new Task< T >(){
         @Override protected T call() throws Exception {
             return task.apply( parameter );
         }
      };
   }// End Method

}// End Class
