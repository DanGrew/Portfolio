/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.structure;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.network.Perceptron;
import model.singleton.Neuron;
import architecture.utility.ReadOnlyArray;

/**
 * The {@link LearningParameter} is responsible for defining a set of input parameters
 * to a network and the corresponding target outputs.
 */
public class LearningParameter {

   /** Array of input values to the network. **/
   private NeuronValueArray inputParameters;
   /** Array of target output values the network should achieve. **/
   private NeuronValueArray targetParameters;
   /** The {@link StringProperty} for the description of the parameter. **/ 
   private StringProperty descriptionProperty;
   
   /**
    * The {@link NeuronValue} is responsible for defining a {@link NetworkPosition} of a {@link Neuron}
    * and its current output value.
    */
   public static class NeuronValue {
      /** The {@link NetworkPosition} of the {@link Neuron}. **/
      public final NetworkPosition position;
      /** The output value of the {@link Neuron}. **/
      public final ReadOnlyDoubleProperty value;
      
      /**
       * Constructs a new {@link NeuronValue}.
       * @param position the {@link NetworkPosition} of the {@link Neuron}.
       * @param value the value of the {@link Neuron}s output.
       */
      public NeuronValue( NetworkPosition position, Number value ){
         this.position = position;
         this.value = new SimpleDoubleProperty( value.doubleValue() );
      }// End Constructor
      
   }// End Class
   
   /**
    * Constructs a new {@link LearningParameter} with a default description.
    */
   public LearningParameter(){
      this( "Parameter" );
   }// End Constructor
   
   /**
    * Constructs a new {@link LearningParameter} with a {@link String} description.
    * @param description the description of the parameter.
    */
   public LearningParameter( String description ){
      descriptionProperty = new SimpleStringProperty( description );
   }// End Constructor

   /**
    * Configures the {@link LearningParameter} with the input values.
    * @param input the input values to learn.
    * @return the {@link LearningParameter}.
    */
   public LearningParameter inputParameters( Number... input ){
      inputParameters = new NeuronValueArray( input );
      return this;
   }// End Method

   /**
    * Method to get the {@link ReadOnlyArray} of {@link Double} input values to learn.
    * @return the {@link ReadOnlyArray}.
    */
   public ReadOnlyArray< NeuronValue > getInputParameters(){
      return inputParameters;
   }// End Method

   /**
    * Configures the {@link LearningParameter} with the target output values.
    * @param targets the target values the network should achieve for the input.
    * @return the {@link LearningParameter}.
    */
   public LearningParameter targetParameters( NeuronValue... targets ){
      targetParameters = new NeuronValueArray( targets );
      return this;
   }// End Method
   
   public LearningParameter targetParameters( Number... targets ){
      targetParameters = new NeuronValueArray( targets );
      return this;
   }// End Method

   /**
    * Method to get the {@link ReadOnlyArray} of target {@link Double} values.
    * @return the {@link ReadOnlyArray}.
    */
   public ReadOnlyArray< NeuronValue > getTargetParameters(){
      return targetParameters;
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
    * Method to determine whether the {@link Perceptron} has satisfied this {@link LearningParameter}.
    * @param perceptron the {@link Perceptron} to validate.
    * @return true if the {@link Perceptron} achieves the targets given the input, false otherwise.
    */
   public boolean isSatisfied( Perceptron perceptron ){
      perceptron.configureInput( inputParameters );
      perceptron.fireInput();
      return perceptron.isLearnt( targetParameters );
   }// End Method
   
   /**
    * Method to get the {@link String} description {@link Property}.
    * @return the {@link ReadOnlyStringProperty} description.
    */
   public ReadOnlyStringProperty getDescriptionProperty(){
      return descriptionProperty;
   }// End Method
}// End Class
