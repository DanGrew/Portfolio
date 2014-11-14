/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.structure;

import model.singleton.Neuron;
import model.structure.LearningParameter.NeuronValue;
import architecture.utility.ReadOnlyArray;

/**
 * The {@link NeuronValueArray} provides an implementation of a {@link ReadOnlyArray}
 * of {@link NeuronValue}s, providing a convenience constructor.
 */
public class NeuronValueArray extends ReadOnlyArray< NeuronValue >{

   /**
    * Constructs a new {@link NeuronValueArray} with default {@link NetworkPosition}s
    * for the values.
    * @param values the {@link Number} values of the {@link Neuron}s.
    */
   public NeuronValueArray( Number... values ) {
      super( convertNumbers( values ) );
   }// End Constructor
   
   /**
    * Constructs a new {@link NeuronValueArray} with the given {@link NeuronValue}s.
    * @param values the {@link NeuronValue}s.
    */
   public NeuronValueArray( NeuronValue... values ){
      super( values );
   }// End Constructor
   
   /**
    * Method to convert an array of {@link Number}s into an array of {@link NeuronValue}s, assuming
    * default {@link NetworkPosition}s.
    * @param values the {@link Number}s to convert.
    * @return an array of {@link NeuronValue}s, created with default {@link NetworkPosition}s on
    * the input layer and each with the array index.
    */
   private static NeuronValue[] convertNumbers( Number[] values ){
      NeuronValue[] array = new NeuronValue[ values.length];
      for ( int i = 0; i < values.length; i++ ){
         array[ i ] = new NeuronValue( 
            new NetworkPosition( 0, i ), 
            values[ i ] 
         );
      }
      return array;
   }// End Method
   
}// End Class