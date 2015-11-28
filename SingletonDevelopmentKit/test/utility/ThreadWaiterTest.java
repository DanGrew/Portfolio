/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * {@link ThreadWaiter} test.
 */
public class ThreadWaiterTest {
   
   /** Backup for hanging tests.**/
   @Rule public Timeout timeout = new Timeout( 5000, TimeUnit.MILLISECONDS );

   /**
    * Basic test that the interruption should work.
    */
   @Test public void shouldWaitForInterrupt()  {
      final ThreadWaiter waiter = new ThreadWaiter();
      runInterrupter( waiter, 1000 );
      waiter.waitForTrigger();
   }//End Method
   
   /**
    * Method to test that an {@link AssertionError} is thrown if not satisfied.
    */
   @Test( expected = AssertionError.class ) public void shouldFailIfNotInterrupted() {
      final ThreadWaiter waiter = new ThreadWaiter( 1000 );
      waiter.waitForTrigger();
   }//End Method
   
   /**
    * Method to prove that the assertions configured are run.
    */
   @Test public void shouldRunAssertions() {
      final ThreadWaiter waiter = new ThreadWaiter( 1000 );
      final BooleanProperty passed = new SimpleBooleanProperty( false );
      waiter.assertions( () -> {
         passed.set( true );
      } );
      runInterrupter( waiter, 500 );
      waiter.waitForTrigger();
      Assert.assertTrue( passed.get() );
   }//End Method
   
   /**
    * Method to test that an {@link ThreadWaiter#interrupt()} does not cause an issue if 
    * not waiting.
    */
   @Test public void shouldInterruptWithoutWaiting() {
      final ThreadWaiter waiter = new ThreadWaiter( 1000 );
      waiter.interrupt();
   }//End Method
   
   /**
    * Private method to interrupt from another {@link Thread}, to be started before waiting.
    * @param waiter the {@link ThreadWaiter} to interrupt.
    * @param millisecondsToInterruption amount of time to wait before interrupting.
    */
   private void runInterrupter( final ThreadWaiter waiter, long millisecondsToInterruption ) {
      new Thread( () -> {
         try {
            Thread.sleep( millisecondsToInterruption );
            waiter.interrupt();
         } catch ( Exception e ) {
            Assert.fail( "Runnable failed to sleep." );
         }
      } ).start();
   }//End Method
   
}//End Class
