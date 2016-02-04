/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package runnables;

/**
 *The {@link TaskUpdateProcess} collects classes related to the {@link TaskProgress} concept.
 */
public class TaskUpdateProcess {

   /** 
    * The {@link TaskProgress} provides an element of the process where a subset of work
    * needs to be done.
    */
   public static class TaskProgress {
      private int weight;
      private ProgressMessageGenerator messageGenerator;
      private TaskProgress next;
      
      /**
       * Constructs a new {@link TaskProgress}.
       * @param weight the amount of work to do.
       * @param next the next {@link TaskProgress} in the process.
       * @param messageGenerator the {@link ProgressMessageGenerator} used to generate progress messages.
       */
      public TaskProgress( int weight, TaskProgress next, ProgressMessageGenerator messageGenerator ) {
         this.weight = weight;
         this.next = next;
         this.messageGenerator = messageGenerator;
      }// End Constructor
      
      /**
       * Getter for the amount of work to be done.
       * @return the weight.
       */
      public int getWeight() {
         return weight;
      }// End Method
      
      /**
       * Method to generate a progress message for the given amount of progress.
       * @return the progress message.
       */
      public String generateMessage( int progress ) {
         return messageGenerator.generateMessage( progress, getWeight() );
      }// End Method
      
      /**
       * Getter for the next {@link TaskProgress} in the process.
       * @return the next {@link TaskProgress}, null if the final.
       */
      public TaskProgress getNext() {
         return next;
      }// End Method
   }// End Class
   
   /**
    * The {@link TaskState} respresents the state of a {@link Task} or process based on the 
    * {@link TaskProgress} that the process is made up of.
    */
   public static class TaskState {
      
      private ProgressControlledTask task;
      private TaskProgress initialState;
      private TaskProgress currentState;
      private int progressInState;
      private int progressMade;
      private int progressToMake;
      
      /**
       * Setter for the amount of work expected for the entire task.
       * @param taskLength the length of the task.
       */
      public void setTaskLength( int taskLength ) {
         this.progressToMake = taskLength;
      }// End Method
      
      /**
       * Method to bind the given {@link ProgressControlledTask} to this state.
       * Updates to this state will update the {@link ProgressControlledTask}.
       * @param task the {@link ProgressControlledTask} to bind to.
       */
      public void bind( ProgressControlledTask task ) {
         this.task = task;
      }// End Method
      
      /**
       * Method to determine whethe rthe state is bound to a {@link ProgressControlledTask}.
       * @return true if bound.
       */
      public boolean isBound(){
         return task != null;
      }// End Method
      
      /**
       * Method to set the initial state of the process.
       * @param progress the initial {@link TaskProgress}.
       */
      public void setInitialState( TaskProgress progress ) {
         this.initialState = progress;
      }// End Method
      
      /**
       * Method to reset the progress to the initial state.
       */
      public void reset(){
         currentState = initialState;
         progressInState = 0;
         progressMade = 0;
         if ( isBound() ) {
            task.publicizedUpdateProgress( 0, progressToMake );
            task.publicizedUpdateMessage( currentState.generateMessage( 0 ) );
         }
      }// End Method
      
      /**
       * Method to indicate that progress has been made resulting in movement between {@link TaskProgress}es
       * and updates to the bound {@link ProgressControlledTask}.
       */
      public void progressMade(){
         progressMade++;
         progressInState++;
         
         if ( progressInState == currentState.getWeight() ) {
            currentState = currentState.next;
            progressInState = 0;
         }
         
         if ( isBound() ) {
            if ( currentState == null ) {
               task.publicizedUpdateProgress( progressToMake, progressToMake );
               task.publicizedUpdateMessage( "Task Complete" );
            } else {
               task.publicizedUpdateProgress( progressMade, progressToMake );
               task.publicizedUpdateMessage( currentState.generateMessage( progressInState ) );
            }
         }
      }// End Method

      /**
       * Getter for the total progress made for the process.
       * @return the total progress.
       */
      public int getTotalProgress() {
         return progressMade;
      }// End Method

      /**
       * Getter for the progress made in the current {@link TaskProgress} state.
       * @return the progress in state.
       */
      public int getProgressInState() {
         return progressInState;
      }// End Method

      /**
       * Getter for the progress to make for the entire process.
       * @return the total progress to make.
       */
      public int getTotalProgressToMake() {
         return progressToMake;
      }// End Method
      
      /**
       * Getter for the current {@link TaskProgress} state.
       * @return the current {@link TaskProgress}.
       */
      public TaskProgress getCurrentState() {
         return currentState;
      }// End Method

   }// End Class
   
}// End Class
