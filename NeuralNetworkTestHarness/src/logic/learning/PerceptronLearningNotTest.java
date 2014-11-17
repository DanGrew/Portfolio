/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package logic.learning;

import logic.constructed.PerceptronLogicalNotTest;
import model.network.Perceptron;
import model.singleton.LearningParameter;
import model.structure.LearningParameters;

import org.junit.BeforeClass;

/**
 * The {@link PerceptronLearningNotTest} is responsible for training a {@link Perceptron}
 * to learn the logical Not operation as configured in {@link PerceptronLogicalNotTest}.
 */
public class PerceptronLearningNotTest extends PerceptronLogicalNotTest {

   /**
    * Method to construct the {@link Perceptron} for the model.
    * Random weights are used and the {@link Perceptron} is trained with {@link LearningParameters}.
    */
   @BeforeClass public static void constructNeuralNetwork(){
      perceptron = new Perceptron( 1, 1 );
      LearningParameters parameters = new LearningParameters();
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 0.0 )
               .targetParameters( 1.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 1.0 )
               .targetParameters( 0.0 )
      );
      perceptron.learn( parameters );
   }// End Method

}
