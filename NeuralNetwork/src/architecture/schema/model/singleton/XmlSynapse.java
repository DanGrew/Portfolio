/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.schema.model.singleton;

import javax.xml.bind.annotation.XmlElement;

import model.data.read.SerializableSynapse;
import model.data.write.SerializedSynapse;
import model.function.learning.PerceptronLearningRule;
import model.singleton.Neuron;
import model.singleton.Synapse;
import architecture.data.wrapper.XmlSingletonWrapper;

/**
 * The {@link XmlSynapse} provides an implementation of the {@link SerializableSynapse} and the {@link SerializedSynapse}
 * interfaces to represent a {@link Synapse} in XML.
 */
public class XmlSynapse extends XmlSingletonWrapper< Synapse > implements SerializableSynapse, SerializedSynapse {
   
   /** The {@link String} representing the input {@link Neuron}. **/
   @XmlElement private String inputNeuron;
   /** The {@link String} representing the output {@link Neuron}. **/
   @XmlElement private String outputNeuron;
   /** The {@link Class} of the {@link PerceptronLearningRule} being used. **/
   @XmlElement private Class< ? extends PerceptronLearningRule > learningRule;
   /** The value of the learning rate. **/
   @XmlElement private double learningRate;
   /** The {@link Double} last fired to the {@link Synapse}.**/
   @XmlElement private Double lastFiredOutput;
   /** The {@link Double} representing the current weight.**/
   @XmlElement private Double currentWeight;
   
   /**
    * Constructs a new {@link XmlSynapse}.
    */
   public XmlSynapse(){
      super();
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void setInputNeuron( Neuron input ) {
      inputNeuron = input.getIdentificationProperty().get();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setOutputNeuron( Neuron output ) {
      outputNeuron = output.getIdentificationProperty().get();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setLearningRule( Class< ? extends PerceptronLearningRule > learningRule ) {
      this.learningRule = learningRule;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setLearningRate( double learningRate ) {
      this.learningRate = learningRate;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setLastFiredOutput( Double lastFiredOutput ) {
      this.lastFiredOutput = lastFiredOutput;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setWeight( Double weight ) {
      this.currentWeight = weight;
   }// End Method
   
}// End Class
