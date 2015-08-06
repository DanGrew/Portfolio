/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package runnables;

/**
 * The {@link ProgressControlledTaskBindable} provides a {@link Runnable} interface that can
 * be bound too for controlling {@link ProgressControlledTask}s.
 */
public interface ProgressControlledTaskBindable extends Runnable {
   
   /**
    * Method to bind the {@link Runnable} to the {@link ProgressControlledTask} so that the
    * {@link Runnable} can publish its progress to the {@link ProgressControlledTask}.
    * @param task the {@link ProgressControlledTask}.
    */
   public void bind( ProgressControlledTask task );

}// End Interface
