/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.network.Perceptron;

/**
 * The {@link LearningParameters} are responsible for defining test data and target
 * outputs for the network to achieve.
 */
public class LearningParameters {

   /** {@link List} of {@link LearningParameter} that should be learnt by the network. **/
   private List< LearningParameter > parameters;

   /**
    * The {@link LearningParameter} is responsible for defining a set of input parameters
    * to a network and the corresponding target outputs.
    */
   public static class LearningParameter {

      /** Array of input values to the network. **/
      private Double[] inputParameters;
      /** Array of target output values the network should achieve. **/
      private Double[] targetParameters;

      /**
       * Configures the {@link LearningParameter} with the input values.
       * @param input the input values to learn.
       * @return the {@link LearningParameter}.
       */
      public LearningParameter inputParameters( Double... input ){
         inputParameters = input;
         return this;
      }// End Method

      /**
       * Configures the {@link LearningParameter} with the target output values.
       * @param targets the target values the network should achieve for the input.
       * @return the {@link LearningParameter}.
       */
      public LearningParameter targetParameters( Double... targets ){
         targetParameters = targets;
         return this;
      }// End Method

      /**
       * Method to configure the input associated with this {@link LearningParameter} in the
       * given {@link Perceptron}.
       * @param perceptron the {@link Perceptron} to configure using {@link Perceptron#configureInput(Double...)}.
       */
      public void configureInput( Perceptron perceptron ){
         perceptron.configureInput( inputParameters );
      }// End Method

      /**
       * Getter for the target to achieve.
       * @return the target output.
       */
      public Double getTarget(){
         return targetParameters[ 0 ];
      }// End Method

      /**
       * Method to determine whether the {@link Perceptron} has satisfied this {@link LearningParameter}.
       * @param perceptron the {@link Perceptron} to validate.
       * @return true if the {@link Perceptron} achieves the targets given the input, false otherwise.
       */
      public boolean isSatisfied( Perceptron perceptron ){
         perceptron.configureInput( inputParameters );
         perceptron.fireInput();
         return getTarget().equals( perceptron.getOutput()[ 0 ] );
      }// End Method
   }// End Class

   /**
    * Constructs a new set of {@link LearningParameters}.
    */
   public LearningParameters(){
      parameters = new ArrayList< LearningParameter >();
   }// End Constructor

   /**
    * Method to add a {@link LearningParameter}.
    * @param parameter the {@link LearningParameter} to add.
    */
   public void addLearningParameter( LearningParameter parameter ){
      parameters.add( parameter );
   }// End Method

   /**
    * Method to get an {@link Iterator} for the {@link LearningParameter}s in this.
    * @return {@link Iterator} of {@link LearningParameter}s.
    */
   public Iterator< LearningParameter > iterator(){
      return parameters.iterator();
   }// End Method

}// End Class
