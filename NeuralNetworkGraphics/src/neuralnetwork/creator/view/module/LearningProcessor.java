/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package neuralnetwork.creator.view.module;

import java.util.Iterator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.network.Perceptron;
import model.singleton.LearningParameter;
import model.structure.LearningParameters;
import neuralnetwork.creator.view.NetworkViewerController;
import architecture.event.EventSystem;

/**
 * The {@link LearningProcessor} is responsible for causing a {@link Perceptron} to learn
 * {@link LearningParameter}s as a result of user input.
 */
public class LearningProcessor {

   /** Enum defining the events used by this module. **/
   public enum Events {
      /** The {@link LearningProcessor} is learning a new parameter. **/
      LearningParameter, 
      /** Instruction to the {@link LearningProcessor} to learn the associated {@link LearningParameter}.**/
      RequestLearnParameter,
      /** Instruction to the {@link LearningProcessor} to leanr the associated {@link LearningParameters}s 
       * using the online learning technique.**/
      RequestOnlineLearning,
      /** The {@link LearningProcessor} is learning {@link LearningParameters} using online learning. **/
      OnlineLearning,
      /** The {@link LearningProcessor} has learnt a {@link LearningParameter}. **/
      ParameterLearnt;
   }// End Enum

   /** The {@link Perceptron} learning. **/
   private Perceptron perceptron;

   /**
    * Constructs a new {@link LearningProcessor}.
    */
   public LearningProcessor() {
      EventSystem.registerForEvent( 
               NetworkViewerController.Events.PerceptronLoaded, 
               ( type, object ) -> setPerceptron( ( Perceptron )object ) 
      );
   }// End Constructor
   
   /**
    * Method to set the {@link Perceptron}. The set {@link Perceptron} will have the learning
    * performed on it.
    * @param perceptron the {@link Perceptron} to be taught.
    */
   public void setPerceptron( Perceptron perceptron ){
      this.perceptron = perceptron;
   }// End Method

   /**
    * Method to start the learning process with {@link LearningParameters}.
    * @param parameters the {@link LearningParameters} to learn.
    */
   public void startLearning( LearningParameters parameters ){
      EventSystem.raiseEvent( Events.OnlineLearning, parameters );
      timedLearn( parameters );
   }// End Method
   
   /**
    * Method to learn the {@link LearningParameters} with a delay between each update.
    * @param parameters the {@link LearningParameters} to learn.
    */
   private void timedLearn( LearningParameters parameters ) {
      Iterator< LearningParameter > iterator = parameters.iterator();
      if ( iterator.hasNext() ){
         Timeline timeline = new Timeline( new KeyFrame( 
                  Duration.millis( 500 ), 
                  event -> learn( iterator.next() ) 
         ) );
         timeline.setCycleCount( parameters.size() );
         timeline.play();
      }
   }// End Method
   
   /**
    * Method to process the request to teach the {@link Perceptron}.
    * @param parameter the {@link LearningParameter} to learn.
    */
   public void startLearning( LearningParameter parameter ) {
      EventSystem.raiseEvent( Events.LearningParameter, parameter );
      timedLearn( parameter );
   }// End Method

   /**
    * Method to perform a learning action, timing the process so output can be visualised.
    * @param parameter the {@link LearningParameter} to learn.
    */
   private void timedLearn( LearningParameter parameter ) {
      Timeline timeline = new Timeline( new KeyFrame( 
               Duration.millis( 1000 ), 
               event -> learn( parameter ) 
      ) );
      timeline.play();
   }// End Method

   /**
    * Method to teach the {@link Perceptron} a single {@link LearningParameter}.
    * @param the {@link LearningParameter} to teach.
    */
   private void learn( LearningParameter parameter ) {
      perceptron.learn( parameter );
      EventSystem.raiseEvent( Events.ParameterLearnt, parameter );
   }// End Method
}// End Class
