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
 * The {@link ProgressControlledTask} provides an interface for publicizing the
 * update methods of a {@link Task}.
 */
public interface ProgressControlledTask {

   /**
    * {@link Task}s update progress method, that is protected.
    * @param workDone the work done.
    * @param max the amount of work to do.
    */
   public void publicizedUpdateProgress( double workDone, double max );

   /**
    * {@link Task}s update message method, that is protected.
    * @param message the message describing the progress.
    */
   public void publicizedUpdateMessage( String message );
}// End Interface
