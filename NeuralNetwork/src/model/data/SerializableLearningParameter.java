/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.data;

import java.util.Iterator;

import model.singleton.LearningParameter;
import model.singleton.LearningParameter.NeuronValue;

/**
 * The {@link SerializableLearningParameter} interface defines the methods to implement in
 * order to support the serialization of a {@link LearningParameter}.
 */
public interface SerializableLearningParameter extends SerializedSingleton< LearningParameter >{

   /**
    * Method to add a {@link NeuronValue} as an input parameter.
    * @param value the {@link NeuronValue} to add.
    */
   public void addInputParameter( NeuronValue value );
   
   /**
    * Method to add all {@link NeuronValue}s as input values.
    * @param iterator the {@link Iterator} of {@link NeuronValue}s to add.
    */
   public void addAllInputParameters( Iterator< NeuronValue > iterator );
   
   /**
    * Method to get an {@link Iterator} of {@link NeuronValue}s for the input parameters.
    * @return an {@link Iterator} of {@link NeuronValue}s defining the inputs.
    */
   public Iterator< NeuronValue > inputParametersIterator();
   
   /**
    * Method to add a {@link NeuronValue} as a target parameter.
    * @param value the {@link NeuronValue} to add.
    */
   public void addTargetParameter( NeuronValue value );
   
   /**
    * Method to add all {@link NeuronValue}s as target values.
    * @param iterator the {@link Iterator} of {@link NeuronValue}s to add.
    */
   public void addAllTargetParameters( Iterator< NeuronValue > iterator );
   
   /**
    * Method to get an {@link Iterator} of {@link NeuronValue}s for the target parameters.
    * @return an {@link Iterator} of {@link NeuronValue}s defining the targets.
    */
   public Iterator< NeuronValue > targetParametersIterator();
}// End Interface

