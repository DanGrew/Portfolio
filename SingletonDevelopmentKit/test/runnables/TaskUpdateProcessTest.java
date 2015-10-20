/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package runnables;

import org.junit.Assert;
import org.junit.Test;

import runnables.TaskUpdateProcess.TaskProgress;
import runnables.TaskUpdateProcess.TaskState;

/**
 * Test for the {@link TaskUpdateProcess}.
 */
public class TaskUpdateProcessTest {
   
   /**
    * Test {@link ProgressControlledTask} to verify publishing.
    */
   private class TestControllableTask implements ProgressControlledTask {

      private double workDone;
      private double max;
      private String message;
      
      /**
       * {@inheritDoc}
       */
      @Override public void publicizedUpdateProgress( double workDone, double max ) {
         this.workDone = workDone;
         this.max = max;
      }// End Method

      /**
       * {@inheritDoc}
       */
      @Override public void publicizedUpdateMessage( String message ) {
         this.message = message;
      }// End Method
      
   }// End Class
   
   private ProgressMessageGenerator ONE_OFF_PROGRESS_MESSAGE = new ProgressMessageGenerator() {
      
      /**
       * {@inheritDoc}
       */
      @Override public String generateMessage( int currentProgress, int progressToMake ) {
         return generateOneOffMessage();
      }
      
   };
   
   private ProgressMessageGenerator LOOP_2_PROGRESS_MESSAGE = new ProgressMessageGenerator() {
      
      /**
       * {@inheritDoc}
       */
      @Override public String generateMessage( int currentProgress, int progressToMake ) {
         return generateLoop2Message( currentProgress, progressToMake );
      }
   };
   
   private ProgressMessageGenerator LOOP_1_PROGRESS_MESSAGE = new ProgressMessageGenerator() {
      
      /**
       * {@inheritDoc}
       */
      @Override public String generateMessage( int currentProgress, int progressToMake ) {
         return generateLoop1Message( currentProgress, progressToMake );
      }
   };
   
   private ProgressMessageGenerator INITIAL_PROGRESS_MESSAGE = new ProgressMessageGenerator() {
      
      /**
       * {@inheritDoc}
       */
      @Override public String generateMessage( int currentProgress, int progressToMake ) {
         return generateInitialMessage();
      }
   };
   
   /**
    * Test to run through all states asserting the messages and progress at each update.
    */
   @Test public void shouldMoveThroughProcessUpdatingWorkAndMessage() {
      TaskProgress oneOff = new TaskProgress( 1, null, ONE_OFF_PROGRESS_MESSAGE );
      TaskProgress loop2 = new TaskProgress( 13, oneOff, LOOP_2_PROGRESS_MESSAGE );
      TaskProgress loop1 = new TaskProgress( 20, loop2, LOOP_1_PROGRESS_MESSAGE );
      TaskProgress startingState = new TaskProgress( 1, loop1, INITIAL_PROGRESS_MESSAGE );
      
      TestControllableTask task = new TestControllableTask();
      
      TaskState state = new TaskState();
      state.setTaskLength( 35 );
      state.bind( task );
      state.setInitialState( startingState );
      state.reset();
      
      assertState( task, state, startingState );
      assertState( task, state, loop1 );
      assertState( task, state, loop2 );
      assertState( task, state, oneOff );
   }// End Method
   
   /**
    * Method to assert the given {@link TaskProgress}.
    * @param task the {@link TestControllableTask} being published to.
    * @param state the {@link TaskState} controlling the progress.
    * @param progress the {@link TaskProgress} currently in.
    */
   private void assertState( TestControllableTask task, TaskState state, TaskProgress progress ) {
      int totalProgressSoFar = state.getTotalProgress();
      for ( int i = 0; i < progress.getWeight(); i++ ) {
         Assert.assertEquals( i, state.getProgressInState() );
         Assert.assertEquals( progress.generateMessage( i ), task.message );
         Assert.assertEquals( totalProgressSoFar + i, ( int )task.workDone );
         Assert.assertEquals( state.getTotalProgressToMake(), ( int )task.max );
         state.progressMade();
      }
      Assert.assertEquals( progress.getNext(), state.getCurrentState() );
   }// End Method
   
   /**
    * Method to generate a message for the one off sub task.
    * @return the message.
    */
   private String generateOneOffMessage(){
      return "This is simply a one off message";
   }// End Method

   /**
    * Method to generate a message for the loop 2 sub task.
    * @param progress the current progress.
    * @param progressToMake the total progress to make.
    * @return the message.
    */
   private String generateLoop2Message( int progress, int progressToMake ){
      return "Loop 2 has made " + progress + " out of " + progressToMake;
   }// End Method
   
   /**
    * Method to generate a message for the loop 1 sub task.
    * @param progress the current progress.
    * @param progressToMake the total progress to make.
    * @return the message.
    */
   private String generateLoop1Message( int progress, int progressToMake ){
      return "" + ( double )progress / ( double )progressToMake;
   }// End Method
   
   /**
    * Method to generate a message for the initial state.
    * @return the message.
    */
   private String generateInitialMessage(){
      return "Ready to start";
   }// End Method
}// End Class
