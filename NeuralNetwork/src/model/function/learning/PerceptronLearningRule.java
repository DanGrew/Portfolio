/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.function.learning;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import model.data.read.SerializableSynapse;
import model.network.Perceptron;
import model.singleton.Neuron;
import model.singleton.Synapse;
import architecture.utility.ObjectGenerator;

/**
 * The {@link PerceptronLearningRule} is responsible for defining how a {@link Perceptron}
 * learns, adjusting its weights to account for the given input data and output target.
 */
public class PerceptronLearningRule {

   /** The amount to adjust the weights by in each learning phase.**/
   private final double learningRate;
   /** The output fired by the input {@link Neuron} of the associated {@link Synapse}.**/
   private double firedOutput;
   /** The weight to apply to the fired output of the input {@link Neuron}. **/
   private DoubleProperty weight;

   /**
    * Constructs a new {@link PerceptronLearningRule}.
    * @param learningRate the learning rate to adjust the weights by each epoch.
    */
   public PerceptronLearningRule( double learningRate ){
      this.learningRate = learningRate;
      weight = new SimpleDoubleProperty( ObjectGenerator.newRandom() );
   }// End Constructor

   /**
    * Setter for the output fired by the input {@link Neuron}.
    * @param firedOutput the output received from the input {@link Neuron} of the {@link Synapse}.
    */
   public void setFiredOutput( double firedOutput ){
      this.firedOutput = firedOutput;
   }// End Method

   /**
    * Method to get the weight being applied to the output from the input {@link Neuron}.
    * @return the weight.
    */
   public Double getWeight(){
      return weight.get();
   }// End Method

   /**
    * Method to get the {@link Property} of the weight used for receiving events when changed.
    * @return the {@link DoubleProperty} for the weight.
    */
   public DoubleProperty getWeightProperty(){
      return weight;
   }// End Method
   
   /**
    * Method to set the weight to be applied to the output from the input {@link Neuron}.
    * @param weight the weight to set.
    */
   public void setWeight( double weight ){
      this.weight.set( weight );
   }// End Method

   /**
    * Method to apply the weight to the output provided by the {@link #inputNeuron}.
    * @param output the output provided.
    * @return the weighted output to provide to the {@link #outputNeuron}.
    */
   public Double applyWeight( Double output ){
      if ( output == null ){
         return null;
      } else {
         return output * weight.get();
      }
   }// End Method
   
   /**
    * Method to learn from the target data and adjust the weight appropriately.
    * @param target the target the output {@link Neuron} should have achieved.
    * @param output the output achieved by the output {@link Neuron}
    */
   public void learn( double target, double output ){
      double learningAdjustment = learningRate * ( target - output ) * firedOutput;
      setWeight( weight.get() + learningAdjustment );
   }// End Method
   
   /**
    * Method to write the data of this learning rule to the {@link SerializableSynapse}.
    * @param serializable the {@link SerializableSynapse} to write to.
    */
   public void write( SerializableSynapse serializable ){
      serializable.setLastFiredOutput( firedOutput );
      serializable.setLearningRate( learningRate );
      serializable.setWeight( weight.get() );
   }// End Method

}// End Class
