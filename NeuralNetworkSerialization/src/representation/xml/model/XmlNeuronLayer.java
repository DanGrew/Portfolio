/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import model.function.threshold.ThresholdFunction;
import model.singleton.Neuron;
import model.structure.NeuronLayer;
import model.structure.NeuronLayer.NeuronLayerBuilder;
import representation.xml.wrapper.XmlNeuronWrapper;
import architecture.representation.SingletonContainer;
import architecture.representation.StructuralRepresentation;
import architecture.request.RequestSystem;

/**
 * The {@link XmlNeuronLayer} is an XML {@link StructuralRepresentation} of the {@link NeuronLayer}.
 */
public class XmlNeuronLayer implements SingletonContainer, StructuralRepresentation< NeuronLayer > {
   
   /** The {@link XmlNeuronWrapper} containing the {@link Neuron}s in the {@link NeuronLayer}.**/
   @XmlElement private XmlNeuronWrapper neurons;
   /** The {@link Class} of the {@link ThresholdFunction} the layer uses. **/
   @XmlElement private Class< ? extends ThresholdFunction > thresholdFunction;
   
   /**
    * Constructs a new {@link XmlNeuronLayer}.
    */
   public XmlNeuronLayer(){}
   
   /**
    * Constructs a new {@link XmlNeuronLayer}.
    * @param layer the {@link NeuronLayer} to represent.
    */
   public XmlNeuronLayer( NeuronLayer layer ){
      neurons = new XmlNeuronWrapper( layer.iterator() );
      thresholdFunction = layer.getThresholdFunction();
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void constructSingletons() {
      neurons.constructSingletons();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      neurons.resolveSingletons();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public NeuronLayer makeStructure() {
      List< Neuron > layerNeurons = new ArrayList< Neuron >();
      neurons.iterator().forEachRemaining( object -> {
         Neuron neuron = RequestSystem.retrieve( Neuron.class, object.getIdentification() );
         if ( neuron == null ){
            throw new NullPointerException( "Neuron does not exist: " + object.getIdentification() + "." );
         } else {
            layerNeurons.add( neuron );
         }
      } );
      return new NeuronLayer( new NeuronLayerBuilder().neurons( layerNeurons ).thresholdFunction( thresholdFunction ) );
   }// End Method

}// End Class
