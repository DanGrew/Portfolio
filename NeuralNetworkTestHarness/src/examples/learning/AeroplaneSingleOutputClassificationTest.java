/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package examples.learning;

import static org.junit.Assert.assertTrue;
import model.network.Perceptron;
import model.singleton.Neuron;
import model.structure.LearningParameters;
import model.structure.LearningParameters.LearningParameter;

import org.junit.BeforeClass;
import org.junit.Test;

import architecture.utility.ReadOnlyArray;

/**
 * The {@link AeroplaneMultipleOutputClassificationTest} is responsible for teaching a {@link Perceptron}
 * with two output {@link Neuron}s to classify two types of aeroplane.
 */
public class AeroplaneSingleOutputClassificationTest {

   /** The {@link Perceptron} learning.**/
   private static Perceptron perceptron;

   /**
    * Method to construct the {@link Perceptron}, {@link LearningParameters} and then to teach the
    * {@link Perceptron}.
    */
   @BeforeClass public static void initialisePerceptron(){
      perceptron = new Perceptron( 2, 1 );
      LearningParameters parameters = new LearningParameters();
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 1.0, 0.1 )
               .targetParameters( 1.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 2.0, 0.2 )
               .targetParameters( 1.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 0.1, 0.3 )
               .targetParameters( 0.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 2.0, 0.3 )
               .targetParameters( 1.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 0.2, 0.4 )
               .targetParameters( 0.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 3.0, 0.4 )
               .targetParameters( 1.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 0.1, 0.5 )
               .targetParameters( 0.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 1.5, 0.5 )
               .targetParameters( 1.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 0.5, 0.6 )
               .targetParameters( 0.0 )
      );
      parameters.addLearningParameter(
               new LearningParameter()
               .inputParameters( 1.6, 0.7 )
               .targetParameters( 0.0 )
      );
      perceptron.learn( parameters );
   }

   /**
    * Method to test that the first {@link LearningParameter} has been learned.
    */
   @Test public void FirstParameterTest() {
      perceptron.configureInput(
               new Double( 1.0 ),
               new Double( 0.1 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutput();
      assertTrue( output.get( 0 ) == 1.0 );
   }// End Method

   /**
    * Method to test that another {@link LearningParameter} has been learnt, with an alternate
    * result that {@link #FirstParameterTest()}.
    */
   @Test public void ExampleParameterTest() {
      perceptron.configureInput(
               new Double( 0.1 ),
               new Double( 0.5 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutput();
      assertTrue( output.get( 0 ) == 0.0 );
   }// End Method

   /**
    * Method to test an example that the {@link Perceptron} has not seen before.
    */
   @Test public void UnseenExampleTest() {
      perceptron.configureInput(
               new Double( 2.1 ),
               new Double( 0.4 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutput();
      assertTrue( output.get( 0 ) == 1.0 );
   }// End Method

   /**
    * Method to test an example that the {@link Perceptron} has not seen before, with a
    * different result to {@link #UnseenExampleTest()}.
    */
   @Test public void UnseenExampleTest2() {
      perceptron.configureInput(
               new Double( 0.25 ),
               new Double( 0.9 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutput();
      assertTrue( output.get( 0 ) == 0.0 );
   }// End Method

}// End Class
