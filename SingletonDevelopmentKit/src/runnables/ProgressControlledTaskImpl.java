/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package runnables;

import javafx.concurrent.Task;

/**
 * The {@link ProgressControlledTaskImpl} provides and implementation for the {@link ProgressControlledTask} 
 * allowing the {@link Task} progress to be updated.
 */
public class ProgressControlledTaskImpl extends Task< Void > implements ProgressControlledTask {

   private ProgressControlledTaskBindable runnable;
   
   /**
    * Constructs a new {@link ProgressControlledTaskImpl}.
    * @param runnable the {@link ProgressControlledTaskBindable} to bind this {@link Task} to.
    */
   public ProgressControlledTaskImpl( ProgressControlledTaskBindable runnable ) {
      this.runnable = runnable;
      runnable.bind( this );
   }// End Class
   
   /**
    * {@inheritDoc}
    */
   @Override public void publicizedUpdateProgress( double workDone, double max ) {
      updateProgress( workDone, max );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void publicizedUpdateMessage( String message ) {
      updateMessage( message );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected Void call() throws Exception {
      runnable.run();
      return null;
   }// End Method
   
}// End Class
