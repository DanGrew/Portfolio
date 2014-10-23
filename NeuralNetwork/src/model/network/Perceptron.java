/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.network;

import java.util.Iterator;

import model.function.BasicInputFunction;
import model.function.McCullochPittsFunction;
import model.function.ThresholdFunction;
import model.singleton.Neuron;
import model.singleton.Synapse;
import model.structure.NetworkPosition;
import model.structure.NeuronLayer;
import model.structure.NeuronLayer.NeuronLayerBuilder;

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
      bias = new Neuron( new BasicInputFunction( 1 ) );
      inputLayer = new NeuronLayer( new NeuronLayerBuilder()
                  .numberOfNeurons( inputNeurons )
                  .thresholdFunction( BasicInputFunction.class )
      );
      outputLayer = new NeuronLayer( new NeuronLayerBuilder()
                  .numberOfNeurons( outputNeurons )
                  .thresholdFunction( McCullochPittsFunction.class )
      );
      connectLayers();
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
    * @param inputValues the {@link Double} values to input. Must be same number of values as the
    * number of {@link Neuron}s in the input layer.
    */
   public void configureInput( Double... inputValues ){
      inputLayer.configureInput( inputValues );
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
    * Method to get the array of output values from the output layer.
    * @return the {@link Double} array of output.
    */
   public Double[] getOutput(){
      Double[] output = new Double[ outputLayer.size() ];
      int outputCount = 0;
      for ( Iterator< Neuron > iterator = outputLayer.iterator(); iterator.hasNext(); ){
         Neuron outputNeuron = iterator.next();
         output[ outputCount ] = outputNeuron.getOutput();
         outputCount++;
      }
      return output;
   }// End Method

}// End Class
