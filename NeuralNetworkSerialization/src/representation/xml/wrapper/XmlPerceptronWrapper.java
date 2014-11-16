/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.wrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.network.Perceptron;
import model.singleton.Neuron;
import model.singleton.Synapse;
import model.structure.NeuronLayer;
import representation.xml.model.XmlNeuron;
import representation.xml.model.XmlNeuronLayer;
import architecture.representation.SingletonContainer;
import architecture.representation.StructuralRepresentation;
import architecture.request.RequestSystem;

/**
 * The {@link XmlPerceptronWrapper} provides a wrapping class for the {@link Perceptron}
 * so that it can be exported as an XML structure.
 */
@XmlRootElement
public class XmlPerceptronWrapper implements SingletonContainer, StructuralRepresentation< Perceptron > {
   
   /** The {@link XmlNeuron} representing the bias {@link Neuron}. **/
   @XmlElement private XmlNeuron bias;
   /** The {@link XmlNeuronLayer} representing the input {@link NeuronLayer}. **/
   @XmlElement private XmlNeuronLayer inputLayer;
   /** The {@link XmlNeuronLayer} representing the output {@link NeuronLayer}. **/
   @XmlElement private XmlNeuronLayer outputLayer;
   /** The {@link XmlSynapseWrapper} containing the {@link Synapse}s in the {@link Perceptron}. **/
   @XmlElement private XmlSynapseWrapper synapseWrapper;
   
   /**
    * Constructs a new {@link XmlPerceptronWrapper}.
    */
   public XmlPerceptronWrapper(){}
   
   /**
    * Constructs a new {@link XmlPerceptronWrapper} with the given {@link Perceptron}.
    * @param perceptron the {@link Perceptron} to wrap and represent its {@link Neuron}s and
    * {@link Synapse}s in XML.
    */
   public XmlPerceptronWrapper( Perceptron perceptron ){
      bias = ( XmlNeuron )perceptron.getBias().write( XmlNeuron.class );
      inputLayer = new XmlNeuronLayer( perceptron.getInputLayer() );
      outputLayer = new XmlNeuronLayer( perceptron.getOutputLayer() );
      
      synapseWrapper = new XmlSynapseWrapper();
      perceptron.getOutputLayer().iterator().forEachRemaining( 
               neuron -> synapseWrapper.addAllUnwrapped( neuron.inputSynapseIterator() ) 
      );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void constructSingletons() {
      bias.unwrap();
      inputLayer.constructSingletons();
      outputLayer.constructSingletons();
      synapseWrapper.constructSingletons();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      synapseWrapper.resolveSingletons();
      RequestSystem.process( Neuron.class, bias.getIdentification(), object -> object.read( bias ) );
      inputLayer.resolveSingletons();
      outputLayer.resolveSingletons();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Perceptron makeStructure() {
      NeuronLayer inputNeuronLayer = inputLayer.makeStructure();
      NeuronLayer outputNeuronLayer = outputLayer.makeStructure();
      Perceptron perceptron = new Perceptron( 
               RequestSystem.retrieve( Neuron.class, bias.getIdentification() ), 
               inputNeuronLayer, 
               outputNeuronLayer 
      );
      return perceptron;
   }// End Method

}// End Class
