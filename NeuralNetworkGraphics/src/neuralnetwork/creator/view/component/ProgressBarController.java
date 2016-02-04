/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view.component;

import architecture.event.EventSystem;
import javafx.scene.control.ProgressBar;
import model.singleton.LearningParameter;
import model.structure.LearningParameters;
import neuralnetwork.creator.view.module.LearningProcessor;

/**
 * The {@link ProgressBarController} is responsible for controlling the {@link ProgressBar}
 * on the {@link NetworkOverviewController}.
 */
public class ProgressBarController {

   /** The {@link ProgressBar} to update. **/
   private ProgressBar progressBar;
   /** The number of parameters to learn.**/
   private double parametersToLearn = 0;
   /** The number of parameters currently learnt. **/
   private double learntParameters = 0;
   
   /**
    * Constructs a new {@link ProgressBarController}.
    * @param progressBar the {@link ProgressBar} to control.
    */
   public ProgressBarController( ProgressBar progressBar ){
      this.progressBar = progressBar;
      EventSystem.registerForEvent( 
               LearningProcessor.Events.LearningParameter, 
               ( event, parameter ) -> learningStarted( ( LearningParameter ) parameter )
      );
      EventSystem.registerForEvent( 
               LearningProcessor.Events.OnlineLearning, 
               ( event, parameter ) -> learningStarted( ( LearningParameters ) parameter )
      );
      EventSystem.registerForEvent( 
               LearningProcessor.Events.ParameterLearnt, 
               ( event, parameter ) -> learntParameter()
      );
   }// End Constructor
   
   /**
    * Method to process the starting of the learning process.
    * @param parameter the single {@link LearningParameter} being learnt.
    */
   private void learningStarted( LearningParameter parameter ){
      parametersToLearn = 1;
      learntParameters = 0;
      progressBar.setProgress( 0 );
   }// End Method
   
   /**
    * Method to process the starting of the learning process with {@link LearningParameters}s.
    * @param parameters the {@link LearningParameters} to learn.
    */
   private void learningStarted( LearningParameters parameters ){
      parametersToLearn = parameters.size();
      learntParameters = 0;
      progressBar.setProgress( 0 );
   }// End Method
   
   /**
    * Method to process the end of the learning process for a single parameter.
    */
   private void learntParameter(){
      learntParameters++;
      progressBar.setProgress( learntParameters / parametersToLearn );
   }// End Method
}// End Class
