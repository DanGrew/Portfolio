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

/**
 * The {@link PerceptronLogicalAndTest} is responsible for testing the construction
 * of a {@link Perceptron} by modelling the logical AND function.
 */
public class PerceptronLogicalAndTest {

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
               -1.5
      );

   }// End Method

   /**
    * Method to test that providing two zeros results in zero.
    */
   @Test public void ZeroZeroTest() {
      perceptron.configureInput(
               new Double( 0 ),
               new Double( 0 )
      );
      perceptron.fireInput();
      assertTrue( perceptron.getOutput()[ 0 ] == 0 );
   }// End Method

   /**
    * Method to test that providing zero and one results in zero.
    */
   @Test public void ZeroOneTest() {
      perceptron.configureInput(
               new Double( 0 ),
               new Double( 1 )
      );
      perceptron.fireInput();
      assertTrue( perceptron.getOutput()[ 0 ] == 0 );
   }// End Method

   /**
    * Method to test that providing one and zero results in zero.
    */
   @Test public void OneZeroTest() {
      perceptron.configureInput(
               new Double( 1 ),
               new Double( 0 )
      );
      perceptron.fireInput();
      assertTrue( perceptron.getOutput()[ 0 ] == 0 );
   }// End Method

   /**
    * Method to test that providing one and one results in one.
    */
   @Test public void OneOneTest() {
      perceptron.configureInput(
               new Double( 1 ),
               new Double( 1 )
      );
      perceptron.fireInput();
      assertTrue( perceptron.getOutput()[ 0 ] == 1 );
   }// End Method

}// End Class
