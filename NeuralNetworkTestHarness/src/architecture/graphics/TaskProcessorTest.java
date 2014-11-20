/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.graphics;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.JFXPanel;

import org.junit.BeforeClass;
import org.junit.Test;

import architecture.TaskProcessor;
import architecture.event.EventSystem;

/**
 * The {@link TaskProcessorTest} is responsible for testing the {@link TaskProcessor}.
 */
public class TaskProcessorTest {
   
   /** Enum defining the events that trigger {@link Task}s.**/
   private enum Triggers {
      Loop,
      Parameter;
   }// End Enum
   
   private static TaskProcessor processor = new TaskProcessor();
   private static Thread testThread;

   /**
    * Method to initialise the JavaFx {@link Thread} and components.
    */
   @BeforeClass public static void initialise(){
      new JFXPanel();
      testThread = Thread.currentThread();
   }// End Method
   
   /**
    * Method to wait the {@link Thread}s other that the JavaFx {@link Thread} so
    * that the {@link Platform#runLater(Runnable)} can complete.
    */
   private static void waitForThreads(){
      try {
         Thread.sleep( 2000 );
      } catch ( InterruptedException e ) {
         e.printStackTrace();
      }
   }// End Method
   
   /**
    * Method to check that the {@link Thread}s that are processing are correct. The processing 
    * should not be completed on the test {@link Thread}.
    */
   private void assertThreads(){
      assertTrue( Thread.currentThread() != testThread );
   }// End Method
   
   /**
    * Method to test that looping over some function populating a {@link List} works correctly
    * on the correct {@link Thread}.
    */
   @Test public void LoopTest() {
      int count = 10;
      List< Integer > expected = new ArrayList< Integer >();
      
      processor.addTask( Triggers.Loop, object -> {
         assertThreads();
         for ( int i = 0; i < count; i++ ){
            expected.add( i );
         }
         return "Done";
      } );
      
      EventSystem.raiseEvent( Triggers.Loop, null );
      
      waitForThreads();
      
      Platform.runLater( () -> {
         EventSystem.raiseEvent( Triggers.Loop, null );
      } );
      
      waitForThreads();
      
      for ( int i = 0; i < count; i++ ){
         assertTrue( expected.remove( 0 ).intValue() == i );
      }
      for ( int i = 0; i < count; i++ ){
         assertTrue( expected.remove( 0 ).intValue() == i );
      }
      assertTrue( expected.isEmpty() );
   }// End Method
   
   /**
    * Method to test that a parameter of an event is used correctly when performing a {@link Task}.
    */
   @Test public void ParameterTest(){
      String success = "Success";
      List< String > expected = new ArrayList< String >();
      
      processor.addTask( Triggers.Parameter, object -> {
         assertThreads();
         expected.add( ( String )object );
         return null;
      } );
      
      EventSystem.raiseEvent( Triggers.Parameter, success );
      
      waitForThreads();
      
      assertTrue( expected.remove( 0 ).equals( success ) );
      assertTrue( expected.isEmpty() );
   }// End Method

}// End Class
