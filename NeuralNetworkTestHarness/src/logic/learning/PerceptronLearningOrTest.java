/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package logic.learning;

import logic.constructed.PerceptronLogicalOrTest;
import model.network.Perceptron;
import model.structure.LearningParameter;
import model.structure.LearningParameters;

import org.junit.BeforeClass;

/**
 * The {@link PerceptronLearningOrTest} is responsible for training a {@link Perceptron}
 * to learn the logical Or operation as configured in {@link PerceptronLogicalOrTest}.
 */
public class PerceptronLearningOrTest extends PerceptronLogicalOrTest {

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
               .targetParameters( 1.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 0.0, 1.0 )
               .targetParameters( 1.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 1.0, 1.0 )
               .targetParameters( 1.0 )
      );
      perceptron.learn( parameters );
   }// End Method

}