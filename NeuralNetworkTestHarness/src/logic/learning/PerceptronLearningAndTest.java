/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package logic.learning;

import logic.constructed.PerceptronLogicalAndTest;
import model.network.Perceptron;
import model.structure.LearningParameter;
import model.structure.LearningParameters;

import org.junit.BeforeClass;

/**
 * The {@link PerceptronLearningAndTest} is responsible for training a {@link Perceptron}
 * to learn the logical And operation as configured in {@link PerceptronLogicalAndTest}.
 */
public class PerceptronLearningAndTest extends PerceptronLogicalAndTest {

   /**
    * Method to construct the {@link Perceptron} for the model.
    * Random weights are used and the {@link Perceptron} is trained with {@link LearningParameters}.
    */
   @BeforeClass public static void constructNeuralNetwork(){
      perceptron = new Perceptron( 2, 1 );
      LearningParameters parameters = new LearningParameters();
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 0.0, 0.0 )
               .targetParameters( 0.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 1.0, 0.0 )
               .targetParameters( 0.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 0.0, 1.0 )
               .targetParameters( 0.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 1.0, 1.0 )
               .targetParameters( 1.0 )
      );
      perceptron.learn( parameters );
   }// End Method

}
