/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.processing;

import architecture.TaskProcessor;
import model.singleton.LearningParameter;
import model.structure.LearningParameters;
import neuralnetwork.creator.view.module.LearningProcessor;

/**
 * The {@link LearnerTasks} defines the specific {@link Task}s to be processed by
 * a {@link TaskProcessor} for a network displayed using JavaFx.
 */
public class LearnerTasks {

   /** The {@link TaskProcessor} respinsible for processing the {@link Task}s.**/
   private TaskProcessor processor;
   /** The {@link LearningProcessor} responsible for teaching a {@link Perceptron}. */
   private LearningProcessor learner;
   
   /**
    * Constructs a new {@link LearnerTasks} and initialises the {@link Task}s.
    */
   public LearnerTasks(){
      processor = new TaskProcessor();
      learner = new LearningProcessor();
      initialiseTasks();
   }// End Constructor
   
   /**
    * Method to initialise the {@link Task}s to be processed.
    */
   private void initialiseTasks(){
      processor.addTask( 
            LearningProcessor.Events.RequestLearnParameter, 
            ( object ) -> { 
               learner.startLearning( ( LearningParameter ) object );
               return null;
            }
      );
      processor.addTask( 
            LearningProcessor.Events.RequestOnlineLearning, 
            ( object ) -> { 
               learner.startLearning( ( LearningParameters ) object );
               return null;
            }
      );
   }// End Method

}// End Class
