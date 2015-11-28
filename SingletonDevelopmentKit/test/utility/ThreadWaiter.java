/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import org.junit.Assert;

/**
 * The {@link ThreadWaiter} is responsible for holding one {@link Thread} while another
 * is processing, that will eventually interrupt this {@link Thread}. This should only be used
 * as a test utility.
 */
public class ThreadWaiter {

   private final static long DEFAULT_TIMEOUT = 10000;
   private final static Runnable SIMPLE_RETURN = () -> {};
   private final long timeout;
   private final Thread thisThread;
   private Runnable assertions;
   
   /**
    * Constructor for a default 10 seconds timeout.
    */
   public ThreadWaiter() {
      this( DEFAULT_TIMEOUT );
   }//End Constructor
   
   /**
    * Constructor for a given timeout.
    * @param milliseconds the number of milliseconds to wait.
    */
   public ThreadWaiter( long milliseconds ) {
      timeout = milliseconds;
      thisThread = Thread.currentThread();
      assertions = SIMPLE_RETURN;
   }//End Constructor
   
   /**
    * Method to interrupt the waiting {@link Thread}, to be called once the other
    * {@link Thread} has finished, by the other {@link Thread}.
    */
   public void interrupt(){
      thisThread.interrupt();
   }//End Method
   
   /**
    * Method to wait for the interruption, blocking the {@link Thread} associated with the
    * {@link ThreadWaiter}. Note that if the timeout is not satisfied, failure.
    */
   public void waitForTrigger(){
      try {
         Thread.sleep( timeout );
         Assert.fail( "Event took too long to process." );
      } catch ( InterruptedException e ) {
         assertions.run();
      }
   }//End Method
   
   /**
    * Method to configure the assertions to perform as a {@link Runnable}.
    * @param assertions the {@link Runnable} to run when interrupted. 
    */
   public void assertions( Runnable assertions ) {
      this.assertions = assertions;
   }//End Method
   
}//End Class
