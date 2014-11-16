/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.model;

import javax.xml.bind.annotation.XmlElement;

import architecture.request.RequestSystem;
import model.data.SerializableSynapse;
import model.function.learning.PerceptronLearningRule;
import model.singleton.Neuron;
import model.singleton.Synapse;
import representation.xml.wrapper.XmlSingletonWrapper;

/**
 * The {@link XmlSynapse} provides an implementation of the {@link SerializableSynapse} and the {@link SerializedSynapse}
 * interfaces to represent a {@link Synapse} in XML.
 */
public class XmlSynapse extends XmlSingletonWrapper< Synapse > implements SerializableSynapse {
   
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
   @Override public String getInputNeuron(){
      return inputNeuron;
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
   @Override public String getOutputNeuron(){
      return outputNeuron;
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
   @Override public Class< ? extends PerceptronLearningRule > getLearningRule(){
      return learningRule;
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
   @Override public double getLearningRate(){
      return learningRate;
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
   @Override public Double getLastFiredOutput(){
      return lastFiredOutput;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setWeight( Double weight ) {
      this.currentWeight = weight;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Double getWeight(){
      return currentWeight;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Synapse unwrap() {
      Synapse synapse = RequestSystem.retrieve( Synapse.class, identification );
      if ( synapse == null ){
         synapse = new Synapse( identification );
         RequestSystem.store( synapse );
      }
      return synapse;
   }// End Method
   
}// End Class
