/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package logic.constructed;

import static org.junit.Assert.assertTrue;
import model.network.Perceptron;
import model.structure.NetworkPosition;

import org.junit.BeforeClass;
import org.junit.Test;

import architecture.utility.ReadOnlyArray;

public class PerceptronLogicalNotTest {

   /** The {@link Perceptron} constructed and configured. **/
   protected static Perceptron perceptron;

   /**
    * Method to construct the {@link Perceptron} for the model.
    */
   @BeforeClass public static void constructNeuralNetwork(){
      perceptron = new Perceptron( 1, 1 );
      perceptron.configureWeight(
               new NetworkPosition( 0, 0 ),
               new NetworkPosition( 1, 0 ),
               -1.0
      );
      perceptron.configureBias(
               new NetworkPosition( 1, 0 ),
               0.5
      );
   }// End Method

   /**
    * Method to test that zero is inverted to one.
    */
   @Test public void ZeroTest() {
      perceptron.configureInput(
               new Double( 0 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutputArray();
      assertTrue( output.get( 0 ) == 1 );
   }// End Method

   /**
    * Method to test that one is inverted.
    */
   @Test public void OneTest() {
      perceptron.configureInput(
               new Double( 1 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutputArray();
      assertTrue( output.get( 0 ) == 0 );
   }// End Method

}// End Class
