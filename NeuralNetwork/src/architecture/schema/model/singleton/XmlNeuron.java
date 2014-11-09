/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.schema.model.singleton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import model.data.read.SerializableNeuron;
import model.data.write.SerializedNeuron;
import model.function.threshold.ThresholdFunction;
import model.singleton.Neuron;
import model.singleton.Synapse;
import architecture.data.wrapper.XmlSingletonWrapper;

/**
 * The {@link XmlNeuron} provides an implementation of the {@link SerializableNeuron} and {@link SerializedNeuron}
 * interfaces that will define how the {@link Neuron} is mapped to XML.
 */
@XmlAccessorType( XmlAccessType.FIELD )
public class XmlNeuron extends XmlSingletonWrapper< Neuron > implements SerializableNeuron, SerializedNeuron {
   
   private static final long serialVersionUID = 1L;

   /** {@link List} of {@link String}s representing the incoming {@link Synapse}s.**/
   @XmlElementWrapper( name = "incoming" ) @XmlElement( name = "synapse" ) 
   private List< String > incomingSynapses;
   /** {@link List} of {@link String}s representing the outgoing {@link Synapse}s.**/
   @XmlElementWrapper( name = "outgoing" ) @XmlElement( name = "synapse" ) 
   private List< String > outgoingSynapses;
   /** The {@link Class} of the {@link ThresholdFunction} to use.**/
   @XmlElement private Class< ? extends ThresholdFunction > thresholdFunction;
   /** {@link Double} representing the current output of the {@link Neuron}.**/
   @XmlElement private Double currentOutput;
   
   /**
    * Constructs a new {@link XmlNeuron}.
    */
   public XmlNeuron(){
      super();
      incomingSynapses = new ArrayList< String >();
      outgoingSynapses = new ArrayList< String >();
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void addIncomingSynapse( Synapse synapse ) {
      incomingSynapses.add( synapse.getIdentification() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addAllIncomingSynapses( Iterator< Synapse > incoming ) {
      incoming.forEachRemaining( synapse -> addIncomingSynapse( synapse ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addOutgoingSynapse( Synapse synapse ) {
      outgoingSynapses.add( synapse.getIdentification() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addAllOutgoingSynapses( Iterator< Synapse > incoming ) {
      incoming.forEachRemaining( synapse -> addOutgoingSynapse( synapse ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setThresholdFunction( Class< ? extends ThresholdFunction > functionType ) {
      thresholdFunction = functionType;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void setCurrentOutput( Double output ) {
      currentOutput = output;
   }// End Method
   
}// End Class
