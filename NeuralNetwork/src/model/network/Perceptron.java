/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.network;

import java.util.Iterator;

import architecture.utility.ReadOnlyArray;
import model.function.threshold.BasicInputFunction;
import model.function.threshold.McCullochPittsFunction;
import model.singleton.LearningParameter;
import model.singleton.LearningParameter.NeuronValue;
import model.singleton.Neuron;
import model.structure.LearningParameters;
import model.structure.NetworkPosition;
import model.structure.NeuronLayer;
import model.structure.NeuronLayer.NeuronLayerBuilder;
import model.structure.NeuronValueArray;

/**
 * The {@link Perceptron} provides a basic Neural Network with two layers, input and output.
 * The input layer simply inserts values into the network while the output layer uses the
 * {@link McCullochPittsFunction} to calculate the output of the {@link Neuron} based on the
 * inputs from the input layer.
 */
public class Perceptron {

   /** The {@link Neuron} representing the bias, connected to all output layers.**/
   private Neuron bias;
   /** The {@link NeuronLayer} representing the input layer of the {@link Perceptron}.**/
   private NeuronLayer inputLayer;
   /** The {@link NeuronLayer} respresenting the output layer of the {@link Perceptron}.**/
   private NeuronLayer outputLayer;

   /**
    * Constructs a new {@link Perceptron}, initialising the layers and bias.
    * @param inputNeurons the number of {@link Neuron}s in the input layer.
    * @param outputNeurons the number of {@link Neuron}s in the output layer.
    */
   public Perceptron( int inputNeurons, int outputNeurons ){
      bias = new Neuron( 
               new NetworkPosition( 0, inputNeurons ), 
               new BasicInputFunction( 1 ) 
      );
      inputLayer = new NeuronLayer( new NeuronLayerBuilder()
                  .numberOfNeurons( inputNeurons )
                  .thresholdFunction( BasicInputFunction.class )
                  .layer( 0 )
      );
      outputLayer = new NeuronLayer( new NeuronLayerBuilder()
                  .numberOfNeurons( outputNeurons )
                  .thresholdFunction( McCullochPittsFunction.class )
                  .layer( 1 )
      );
      connectLayers();
   }// End Constructor
   
   /**
    * Constructs a new {@link Perceptron}.
    * @param bias the {@link Neuron} providing the bias.
    * @param inputLayer the {@link NeuronLayer} providing the input.
    * @param outputLayer the {@link NeuronLayer} providing the output.
    */
   public Perceptron( Neuron bias, NeuronLayer inputLayer, NeuronLayer outputLayer ){
      this.bias = bias;
      this.inputLayer = inputLayer;
      this.outputLayer = outputLayer;
   }// End Constructor

   /**
    * Method to connect the {@link Neuron}s in the {@link Perceptron}. This will connect
    * all {@link Neuron}s in the input layer to the output layer.
    */
   private void connectLayers(){
      for ( Iterator< Neuron > inputIterator = inputLayer.iterator(); inputIterator.hasNext(); ){
         Neuron input = inputIterator.next();
         for ( Iterator< Neuron > outputIterator = outputLayer.iterator(); outputIterator.hasNext(); ){
            Neuron output = outputIterator.next();
            input.addOutgoingSynapse( output );
            bias.addOutgoingSynapse( output );
         }
      }
   }// End Method

   /**
    * Method to configure the input values for the {@link Perceptron}. This can be done multiple times
    * as the {@link Neuron}s are reset after each {@link NeuronLayer} has fired.
    * @param inputValues the {@link NeuronValueArray} of values to input. Must be same number of values as the
    * number of {@link Neuron}s in the input layer.
    */
   public void configureInput( NeuronValueArray inputValues ){
      inputLayer.configureInput( inputValues );
   }// End Method

   /**
    * Method to configure the {@link Double} input values to the {@link Perceptron}.
    * @param inputValues the {@link NeuronValue}s defining the input values.
    */
   public void configureInput( NeuronValue... inputValues ){
      inputLayer.configureInput( new NeuronValueArray( inputValues ) );
   }// End Method
   
   /**
    * Method to configure the {@link Double} input values to the {@link Perceptron}.
    * @param inputValues the {@link Double}s defining the input values. A default
    * {@link NetworkPosition} is assumed.
    */
   public void configureInput( Double... inputValues ){
      inputLayer.configureInput( new NeuronValueArray( inputValues ) );
   }// End Method

   /**
    * Method to configure the weight of the {@link Synapse} between the {@link Neuron}s at the given
    * {@link NetworkPosition}s.
    * @param input the {@link NetworkPosition} of the {@link Neuron} in the input layer.
    * @param output the {@link NetworkPosition} of the {@link Neuron} in the output layer.
    * @param weight the weight to use in the {@link Synapse} between them.
    */
   public void configureWeight( NetworkPosition input, NetworkPosition output, double weight ){
      Neuron inputNeuron = inputLayer.getNeuronAtPosition( input );
      Neuron outputNeuron = outputLayer.getNeuronAtPosition( output );
      inputNeuron.configureOutgoingWeight( outputNeuron, weight );
   }// End Method

   /**
    * Method to configure the threshold of the {@link Neuron} at the given {@link NetworkPosition}.
    * @param position the {@link NetworkPosition} of the {@link Neuron} to configure.
    * @param weight the threshold to set for the associated {@link ThresholdFunction}.
    */
   public void configureBias( NetworkPosition position, double weight ){
      Neuron neuron = null;
      switch( position.layer ){
         case 0:
            throw new IllegalArgumentException();
         case 1:
            neuron = outputLayer.getNeuronAtPosition( position );
            bias.configureOutgoingWeight( neuron, weight );
            break;
         default:
            throw new IllegalArgumentException();
      }
   }// End Method

   /**
    * Method to fire the input values through the network to the output layer.
    */
   public void fireInput(){
      bias.fireNeuron();
      inputLayer.fireLayer();
      outputLayer.fireLayer();
   }// End Method

   /**
    * Method to make the {@link Perceptron} learn given the {@link LearningParameters} defining the input values
    * and target values expected. This will {@link #learn(double)} each target having set the input for the {@link Perceptron},
    * then move onto the next {@link LearningParameter}. When all {@link LearningParameter}s are {@link LearningParameter#isSatisfied(Perceptron)}
    * the {@link Perceptron} has learnt the test data.
    * @param learningParameters the {@link LearningParameters} defining the test cases the {@link Perceptron} should
    * understand and reproduce.
    */
   public void learn( LearningParameters learningParameters ){
      boolean isComplete = false;
      int epoch = 0;
      while ( !isComplete ){
         for ( Iterator< LearningParameter > iterator = learningParameters.iterator(); iterator.hasNext(); ){
            epoch++;
            LearningParameter parameter = iterator.next();
            parameter.configureInput( this );
            applyOnlineLearning( parameter );
            System.out.println( "EPOCH " + epoch + ": \n" + outputLayer.toWeightString() );
         }
         isComplete = true;
         for ( Iterator< LearningParameter > iterator = learningParameters.iterator(); iterator.hasNext(); ){
            LearningParameter parameter = iterator.next();
            if ( !parameter.isSatisfied( this ) ){
               isComplete = false;
               break;
            }
         }
      }
   }// End Method
   
   /**
    * Method to learn the given {@link LearningParameter}. This will call
    * {@link LearningParameter#configureInput(Perceptron)} and then {@link #applyOnlineLearning(LearningParameter)}.
    * @param parameter the {@link LearningParameter} to learn.
    */
   public void learn( LearningParameter parameter ){
      parameter.configureInput( this );
      applyOnlineLearning( parameter );
   }// End Method

   /**
    * Method to apply online learning, allow the perceptron to learn the target output having
    * configured the input for the {@link Perceptron}. This will {@link #fireInput()} and
    * make the output {@link NeuronLayer#learn(Double[])}, adjusting the weights immediately.
    * @param parameter the {@link LearningParameter} to learn.
    */
   public void applyOnlineLearning( LearningParameter parameter ){
      fireInput();
      outputLayer.learn( parameter.getTargetParameters() );
   }// End Method

   public void applyBatchLearning( LearningParameters parameters ){

   }

   /**
    * Method to validate whether the {@link Perceptron} has learnt the given target values.
    * @param targets the {@link Double} targets to learn.
    * @return whether the targets have been learnt.
    */
   public boolean isLearnt( ReadOnlyArray< NeuronValue > targets ){
      NeuronValueArray outputs = getOutput();
      if ( targets.length() == outputs.length() ){
         for ( int i = 0; i < outputs.length(); i++ ){
            double output = outputs.get( i ).value.doubleValue();
            double target = targets.get( i ).value.get();
            if ( output != target ){
               return false;
            }
         }
         return true;
      } else {
         throw new IllegalArgumentException();
      }
   }// End Method

   /**
    * Method to get the array of output values from the output layer.
    * @return the {@link Double} {@link ReadOnlyArray} of output.
    */
   public ReadOnlyArray< Double > getOutputArray(){
      Double[] output = new Double[ outputLayer.size() ];
      int outputCount = 0;
      for ( Iterator< Neuron > iterator = outputLayer.iterator(); iterator.hasNext(); ){
         Neuron outputNeuron = iterator.next();
         output[ outputCount ] = outputNeuron.getOutput();
         outputCount++;
      }
      return new ReadOnlyArray< Double >( output );
   }// End Method
   
   /**
    * Method to get the {@link NeuronValueArray} of output from the output 
    * {@link NeuronLayer}.
    * @return the {@link NeuronLayer#constructOutput()};
    */
   public NeuronValueArray getOutput(){
      return outputLayer.constructOutput();
   }// End Method
   
   /**
    * Method to get the {@link Neuron} providing the bias to the network.
    * @return the bias {@link Neuron}.
    */
   public Neuron getBias(){
      return bias;
   }// End Method
   
   /** 
    * Method to get the input {@link NeuronLayer} for the network.
    * @return the {@link NeuronLayer} for the input.
    */
   public NeuronLayer getInputLayer(){
      return inputLayer;
   }// End Method
   
   /**
    * Method to get the output {@link NeuronLayer} for the network.
    * @return the {@link NeuronLayer} for the output.
    */
   public NeuronLayer getOutputLayer(){
      return outputLayer;
   }// End Method

}// End Class
