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

public class PerceptronLogicalOrTest {

   /** The {@link Perceptron} constructed and configured. **/
   protected static Perceptron perceptron;

   /**
    * Method to construct the {@link Perceptron} for the model.
    */
   @BeforeClass public static void constructNeuralNetwork(){
      perceptron = new Perceptron( 2, 1 );
      perceptron.configureWeight(
               new NetworkPosition( 0, 0 ),
               new NetworkPosition( 1, 0 ),
               1.0
      );
      perceptron.configureWeight(
               new NetworkPosition( 0, 1 ),
               new NetworkPosition( 1, 0 ),
               1.0
      );
      perceptron.configureBias(
               new NetworkPosition( 1, 0 ),
               -0.5
      );
   }// End Method

   /**
    * Method to test that zero and zero results in zero.
    */
   @Test public void ZeroZeroTest() {
      perceptron.configureInput(
               new Double( 0 ),
               new Double( 0 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutputArray();
      assertTrue( output.get( 0 ) == 0 );
   }// End Method

   /**
    * Method to test that zero and one results in one.
    */
   @Test public void ZeroOneTest() {
      perceptron.configureInput(
               new Double( 0 ),
               new Double( 1 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutputArray();
      assertTrue( output.get( 0 ) == 1 );
   }// End Method

   /**
    * Method to test that one and zero results in one.
    */
   @Test public void OneZeroTest() {
      perceptron.configureInput(
               new Double( 1 ),
               new Double( 0 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutputArray();
      assertTrue( output.get( 0 ) == 1 );
   }// End Method

   /**
    * Method to test that one and one results in one.
    */
   @Test public void OneOneTest() {
      perceptron.configureInput(
               new Double( 1 ),
               new Double( 1 )
      );
      perceptron.fireInput();
      ReadOnlyArray< Double > output = perceptron.getOutputArray();
      assertTrue( output.get( 0 ) == 1 );
   }// End Methode

}// End Class
