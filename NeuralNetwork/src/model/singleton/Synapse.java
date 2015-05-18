/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.singleton;

import architecture.request.RequestSystem;
import architecture.utility.ObjectGenerator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import model.data.SerializableSynapse;
import model.function.learning.PerceptronLearningRule;
import model.function.threshold.ThresholdFunction;

 /**
  * The {@link Synapse} is responsible for connecting two {@link Neuron}s and weighting
  * the output between them.
  */
public class Synapse extends SingletonImpl< SerializableSynapse >{

   /** The input {@link Neuron} providing the output value to this {@link Synapse}.**/
   private Neuron inputNeuron;
   /** The output {@link Neuron} the {@link Synapse} provides the weighted output to.**/
   private Neuron outputNeuron;
   /** The {@link PerceptronLearningRule} defining how the {@link Synapse} should learn 
    * from test data, allowing the {@link PerceptronLearningRule#getWeight()} to be adjusted
    * to better achieve the target data. */
   private PerceptronLearningRule learningRule;

   /**
    * Constructs a new {@link Synapse} with the given {@link String} identification.
    * @param identification the identification of the {@link Synapse}.
    */
   public Synapse( String identification ){
      super( identification );
   }// End Constructor
   
   /**
    * Constructs a new {@link Synapse}.
    * @param inputNeuron the {@link Neuron} providing the output to the {@link Synapse}.
    * @param outputNeuron the {@link Neuron} receiving the weighted output form the {@link Synapse}.
    */
   public Synapse( Neuron inputNeuron, Neuron outputNeuron ) {
      super( generateIdentification( inputNeuron.getIdentificationProperty().get(), outputNeuron.getIdentificationProperty().get() ) );
      this.inputNeuron = inputNeuron;
      this.outputNeuron = outputNeuron;
      learningRule = new PerceptronLearningRule( 0.1 );
      connectSynapse();
   }// End Constructor

   /**
    * Constructs a new {@link Synapse}.
    * @param inputNeuron the {@link Neuron} providing the output to the {@link Synapse}.
    * @param weight the weight associated with the {@link Synapse}.
    * @param outputNeuron the {@link Neuron} receiving the weighted output from the {@link Synapse}.
    */
   public Synapse( Neuron inputNeuron, double weight, Neuron outputNeuron ) {
      this( inputNeuron, outputNeuron );
      learningRule.setWeight( weight );
   }// End Constructor
   
   /**
    * Method to generate the identification of the {@link Synapse} given the identification of
    * the input {@link Neuron} and output {@link Neuron}.
    * @param input the identification of the input {@link Neuron}.
    * @param output the identification of the output {@link Neuron}.
    * @return the identification of the {@link Synapse}.
    */
   public static String generateIdentification( String input, String output ){
      return input + "_" + output;
   }// End Method

   /**
    * Method to connect the {@link Synapse} to the {@link #inputNeuron} and the {@link #outputNeuron}.
    */
   private void connectSynapse() {
      inputNeuron.addOutgoingSynapse( this );
      outputNeuron.addIncomingSynapse( this );
   }// End Method
   
   /**
    * Method to get the identification of the {@link Synapse}.
    * @return the {@link String} representing the id of the {@link Synapse}.
    */
   public String getIdentification(){
      return identification;
   }// End Method

   /**
    * Method to get the {@link Neuron} that provides the output to this {@link Synapse}.
    * @return the {@link Neuron}.
    */
   public Neuron getInput(){
      return inputNeuron;
   }// End Method

   /**
    * Method to get the {@link Neuron} that the weighted output is provided to.
    * @return the {@link Neuron}.
    */
   public Neuron getOutput(){
      return outputNeuron;
   }// End Method
   
   /**
    * Method to get the {@link Property} for the weight of the {@link Synapse}.
    * @return {@link #learningRule#getWeightProperty()}.
    */
   public DoubleProperty getWeightProperty(){
      return learningRule.getWeightProperty();
   }// End Method

   /**
    * Method to set the weight associated with this {@link Synapse}.
    * @param weight the weight to modify the output of the {@link #inputNeuron}.
    */
   public void setWeight( double weight ){
      learningRule.setWeight( weight );
   }// End Method

   /**
    * Method to fire the output through the {@link Synapse} to the connected {@link Neuron}.
    * @param output the calculated output from the {@link ThresholdFunction}.
    */
   public void fire( Double output ){
      learningRule.setFiredOutput( output );
      Double weightedOutput = learningRule.applyWeight( output );
      if ( weightedOutput != null ){
         outputNeuron.synapseFired( weightedOutput );
      } else {
         throw new NullPointerException();
      }
   }// End Method

   /**
    * Method to make the {@link Synapse} learn from the performance of the associated
    * output {@link Neuron}.
    * @param target the target of the output {@link Neuron}.
    * @param output the output achieved by the output {@link Neuron}.
    */
   public void learn( double target, double output ){
      learningRule.learn( target, output );
   }// End Method
   
   /**
    * Method to produce a weight sumary of the {@link Synapse}.
    * @return a {@link String} containing the weight.
    */
   public String toWeightString(){
      return learningRule.getWeight().toString();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializableSynapse serializable ) {
      serializable.setInputNeuron( inputNeuron );
      serializable.setOutputNeuron( outputNeuron );
      serializable.setLearningRule( learningRule.getClass() );
      learningRule.write( serializable );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void readSingleton( SerializableSynapse serialized ){
      inputNeuron = RequestSystem.retrieve( Neuron.class, serialized.getInputNeuron() );
      outputNeuron = RequestSystem.retrieve( Neuron.class, serialized.getOutputNeuron() );
      learningRule = ObjectGenerator.construct( serialized.getLearningRule() );
      learningRule.setFiredOutput( serialized.getLastFiredOutput() );
      learningRule.setWeight( serialized.getWeight() );
      learningRule.setLearningRate( serialized.getLearningRate() );
   }// End Method

}// End Class
