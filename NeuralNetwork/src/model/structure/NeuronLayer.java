/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import architecture.utility.ObjectGenerator;
import model.function.ThresholdFunction;
import model.singleton.Neuron;

/**
 * The {@link NeuronLayer} collects a layer of {@link Neuron}s to process together.
 * Typcially all {@link Neuron}s with fire at the same time in the layer
 */
public class NeuronLayer {

   /** The {@link Class} of the {@link ThresholdFunction} to use. **/
   private Class< ? extends ThresholdFunction > thresholdFunction;
   /** The {@link List} of {@link Neuron}s in the {@link NeuronLayer}. **/
   private List< Neuron > neurons;
   /** The number of {@link Neuron}s in the {@link NeuronLayer}.**/
   private final int capacity;

   /**
    * The {@link NeuronLayerBuilder} provides a method of building a {@link NeuronLayer} by configuring
    * the {@link NeuronLayer} during construction.
    */
   public static class NeuronLayerBuilder {

      /** The number of {@link Neuron}s in the {@link NeuronLayer}.**/
      private int numberOfNeurons;
      /** The {@link Class} of the {@link ThresholdFunction} to use.**/
      private Class< ? extends ThresholdFunction > thresholdFunction;

      /**
       * Constructs a new {@link NeuronLayerBuilder}.
       */
      public NeuronLayerBuilder(){}

      /**
       * Method to configure the number of {@link Neuron}s in the {@link NeuronLayer}.
       * @param numberOfNeurons the number of {@link Neuron}s in the {@link NeuronLayer}.
       * @return the {@link NeuronLayerBuilder}.
       */
      public NeuronLayerBuilder numberOfNeurons( int numberOfNeurons ){
         this.numberOfNeurons = numberOfNeurons;
         return this;
      }// End Method

      /**
       * Method to configure the {@link ThresholdFunction}.
       * @param thresholdFunction the {@link ThresholdFunction} to use in the {@link Neuron}s.
       * @return the {@link NeuronLayerBuilder}.
       */
      public NeuronLayerBuilder thresholdFunction( Class< ? extends ThresholdFunction > thresholdFunction ){
         this.thresholdFunction = thresholdFunction;
         return this;
      }// End Method
   }// End Class

   /**
    * Constructs a new {@link NeuronLayer}.
    * @param builder the {@link NeuronLayerBuilder} providing the configuration.
    */
   public NeuronLayer( NeuronLayerBuilder builder ){
      thresholdFunction = builder.thresholdFunction;
      capacity = builder.numberOfNeurons;
      neurons = new ArrayList< Neuron >( capacity );
      constructNeurons();
   }// End Constructor

   /**
    * Method to construct the {@link Neuron}s in the {@link NeuronLayer}.
    */
   public void constructNeurons() {
      for ( int i = 0; i < capacity; i++ ){
         ThresholdFunction function = ObjectGenerator.construct( thresholdFunction );
         Neuron neuron = new Neuron( function );
         addNeuron( neuron );
      }
   }// End Method

   /**
    * Method to add a {@link Neuron} to the {@link NeuronLayer}.
    * @param neuron the {@link Neuron} to add.
    */
   public void addNeuron( Neuron neuron ){
      neurons.add( neuron );
   }// End Method

   /**
    * Method to add all {@link Neuron}s in the {@link Collection}.
    * @param neurons the {@link Neuron}s to add.
    */
   public void addAllNeurons( Collection< Neuron > neurons ){
      this.neurons.addAll( neurons );
   }// End Method

   /**
    * Method to add all {@link Neuron}s to the {@link NeuronLayer}.
    * @param neurons the array of {@link Neuron}s to add.
    */
   public void addAllNeurons( Neuron... neurons ){
      addAllNeurons( Arrays.asList( neurons ) );
   }// End Method

   /**
    * Method to get the {@link Neuron} at the {@link NetworkPosition} provided.
    * @param position the {@link NetworkPosition} of the {@link Neuron}.
    * @return the {@link Neuron} at the given {@link NetworkPosition}.
    */
   public Neuron getNeuronAtPosition( NetworkPosition position ){
      if ( position.index > neurons.size() ){
         throw new NullPointerException();
      } else {
         return neurons.get( position.index );
      }
   }// End Method

   /**
    * Method to configure the input values for the {@link NeuronLayer}.
    * @param inputValues the input values to set on the {@link Neuron}s.
    */
   public void configureInput( Double... inputValues ){
      if ( inputValues.length > size() ){
         throw new IllegalArgumentException();
      } else {
         for ( int i = 0; i < inputValues.length; i++ ){
            Neuron neuron = neurons.get( i );
            neuron.synapseFired( inputValues[ i ] );
         }
      }
   }// End Method

   /**
    * Method to fire all {@link Neuron}s in the {@link NeuronLayer}. This will iterate
    * over the {@link Neuron}s and call {@link Neuron#fireNeuron()}.
    */
   public void fireLayer(){
      for ( Neuron neuron : neurons ){
         neuron.fireNeuron();
      }
   }// End Method

   /**
    * Method to get an {@link Iterator} of {@link Neuron}s in the {@link NeuronLayer}.
    * @return the {@link Neuron} {@link Iterator}.
    */
   public Iterator< Neuron > iterator(){
      return neurons.iterator();
   }// End Method

   /**
    * Method to get the size of the {@link NeuronLayer}, as the number of {@link Neuron}s in it.
    * @return the number of {@link Neuron}s in the {@link NeuronLayer}.
    */
   public int size(){
      return neurons.size();
   }// End Method

}// End Class
