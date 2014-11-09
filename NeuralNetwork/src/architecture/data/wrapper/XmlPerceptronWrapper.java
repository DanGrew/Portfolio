/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data.wrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.network.Perceptron;
import model.singleton.Neuron;
import model.singleton.Synapse;

/**
 * The {@link XmlPerceptronWrapper} provides a wrapping class for the {@link Perceptron}
 * so that it can be exported as an XML structure.
 */
@XmlRootElement
public class XmlPerceptronWrapper {
   
   /** The {@link XmlNeuronWrapper} containing the {@link Neuron}s in the {@link Perceptron}. **/
   @XmlElement private XmlNeuronWrapper neuronWrapper;
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
      neuronWrapper = new XmlNeuronWrapper();
      neuronWrapper.addAllUnwrapped( perceptron.getInputLayer().iterator() );
      neuronWrapper.addUnwrapped( perceptron.getBias() );
      neuronWrapper.addAllUnwrapped( perceptron.getOutputLayer().iterator() );
      
      synapseWrapper = new XmlSynapseWrapper();
      perceptron.getOutputLayer().iterator().forEachRemaining( 
               neuron -> synapseWrapper.addAllUnwrapped( neuron.inputSynapseIterator() ) 
      );
   }// End Constructor

}// End Class
