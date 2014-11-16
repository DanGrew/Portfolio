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

import model.function.threshold.ThresholdFunction;
import model.singleton.Neuron;
import model.singleton.Synapse;
import model.structure.LearningParameter.NeuronValue;
import architecture.utility.ObjectGenerator;
import architecture.utility.ReadOnlyArray;

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
   private int capacity;

   /**
    * The {@link NeuronLayerBuilder} provides a method of building a {@link NeuronLayer} by configuring
    * the {@link NeuronLayer} during construction.
    */
   public static class NeuronLayerBuilder {

      /** {@link Collection} of {@link Neuron}s to use in the layer. **/
      private Collection< Neuron > neurons;
      /** The number of {@link Neuron}s in the {@link NeuronLayer}.**/
      private int numberOfNeurons;
      /** The {@link Class} of the {@link ThresholdFunction} to use.**/
      private Class< ? extends ThresholdFunction > thresholdFunction;
      /** The layer position in the network. **/
      private int layer = 0;

      /**
       * Constructs a new {@link NeuronLayerBuilder}.
       */
      public NeuronLayerBuilder(){}
      
      /**
       * Method to configure the {@link Neuron}s to use in the layer.
       * @param neurons the {@link Collection} of {@link Neuron}s.
       * @return the {@link NeuronLayerBuilder} configured.
       */
      public NeuronLayerBuilder neurons( Collection< Neuron > neurons ){
         this.neurons = neurons;
         return this;
      }// End Method

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
      
      /**
       * Method to configure the position of the layer in the network, used to position
       * {@link Neuron}s when constructed.
       * @param layer the position of the layer in the network.
       * @return the {@link NeuronLayerBuilder}.
       */
      public NeuronLayerBuilder layer( int layer ){
         this.layer = layer;
         return this;
      }// End Method
   }// End Class

   /**
    * Constructs a new {@link NeuronLayer}.
    * @param builder the {@link NeuronLayerBuilder} providing the configuration.
    */
   public NeuronLayer( NeuronLayerBuilder builder ){
      thresholdFunction = builder.thresholdFunction;
      if ( builder.neurons == null ){
         capacity = builder.numberOfNeurons;
         neurons = new ArrayList< Neuron >( capacity );
         constructNeurons( builder.layer );
      } else {
         neurons = new ArrayList< Neuron >( builder.neurons.size() );
         includeNeurons( builder.neurons );
      }
   }// End Constructor

   /**
    * Method to construct the {@link Neuron}s in the {@link NeuronLayer}.
    * @param layer the position of the {@link NeuronLayer} in the network.
    */
   public void constructNeurons( int layer ) {
      for ( int i = 0; i < capacity; i++ ){
         ThresholdFunction function = ObjectGenerator.construct( thresholdFunction );
         Neuron neuron = new Neuron( new NetworkPosition( layer, i ), function );
         addNeuron( neuron );
      }
   }// End Method
   
   /**
    * Method to include the {@link Collection} of {@link Neuron}s in the {@link NeuronLayer}.
    * @param neurons the {@link Collection} of {@link Neuron}s.
    */
   private void includeNeurons( Collection< Neuron > neurons ){
      capacity = neurons.size();
      neurons.forEach( neuron -> addNeuron( neuron ) );
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
    * Method to get the capacity of the {@link NeuronLayer}.
    * @return the int capacity.
    */
   public int getCapacity(){
      return capacity;
   }// End Method
   
   /**
    * Method to get the {@link ThresholdFunction} {@link Class} used by the {@link NeuronLayer}.
    * @return the {@link ThresholdFunction}.
    */
   public Class< ? extends ThresholdFunction > getThresholdFunction(){
      return thresholdFunction;
   }// End Method

   /**
    * Method to configure the input values for the {@link NeuronLayer}.
    * @param inputValues the {@link NeuronValueArray} of input values to set on the {@link Neuron}s.
    */
   public void configureInput( NeuronValueArray inputValues ){
      if ( inputValues.length() > size() ){
         throw new IllegalArgumentException();
      } else {
         for ( NeuronValue value : inputValues ){
            Neuron neuron = getNeuronAtPosition( value.position );
            neuron.synapseFired( value.value.doubleValue() );
         }
      }
   }// End Method
   
   /**
    * Method to construct a {@link NeuronValueArray} of output from the {@link Neuron}s.
    * @return a {@link NeuronValueArray} of output.
    */
   public NeuronValueArray constructOutput(){
      NeuronValue[] values = new NeuronValue[ neurons.size() ];
      for ( int i = 0; i < neurons.size(); i++ ){
         values[ i ] = neurons.get( i ).constructValue();
      }
      return new NeuronValueArray( values );
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
    * Method to make the {@link Neuron}s in this {@link NeuronLayer} {@link Neuron#learn(double)}.
    * @param targets the targets the {@link Neuron}s should have achieved.
    */
   public void learn( ReadOnlyArray< NeuronValue > targets ){
      if ( targets.length() == neurons.size() ){
         for ( NeuronValue value : targets ){
            Neuron neuron = getNeuronAtPosition( value.position );
            neuron.learn( value.value.doubleValue() );
         }
      } else {
         throw new IllegalArgumentException();
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

   /**
    * Method to produce a summary of the weights of {@link Synapse}s to {@link Neuron}s in this
    * {@link NeuronLayer}.
    * @return a {@link String} summarising the weights.
    */
   public String toWeightString(){
      StringBuffer buffer = new StringBuffer();
      for ( Neuron neuron : neurons ){
         buffer.append( neuron.toWeightString() ).append( "\n" );
      }
      return buffer.toString();
   }// End Method

}// End Class
