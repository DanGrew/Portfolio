/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import model.data.SerializableNeuron;
import model.function.threshold.ThresholdFunction;
import model.singleton.Neuron;
import model.singleton.Synapse;
import model.structure.NetworkPosition;
import representation.xml.wrapper.XmlSingletonWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlNeuron} provides an implementation of the {@link SerializableNeuron} and {@link SerializedNeuron}
 * interfaces that will define how the {@link Neuron} is mapped to XML.
 */
@XmlAccessorType( XmlAccessType.FIELD )
public class XmlNeuron extends XmlSingletonWrapper< Neuron > implements SerializableNeuron {
   
   private static final long serialVersionUID = 1L;

   @XmlElement private NetworkPosition position;
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

   @Override public void setPosition( NetworkPosition position ){
      this.position = position;
   }
   
   @Override public NetworkPosition getPosition(){
      return position;
   }
   
   /**
    * {@inheritDoc}
    */
   @Override public void addIncomingSynapse( Synapse synapse ) {
      incomingSynapses.add( synapse.getIdentification() );
      incomingSynapses.sort( ( a, b ) -> { return a.compareTo( b ); } );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addAllIncomingSynapses( Iterator< Synapse > incoming ) {
      incoming.forEachRemaining( synapse -> addIncomingSynapse( synapse ) );
   }// End Method
   
   @Override public Iterator< String > incomingSynapseIterator(){
      return incomingSynapses.iterator();
   }

   /**
    * {@inheritDoc}
    */
   @Override public void addOutgoingSynapse( Synapse synapse ) {
      outgoingSynapses.add( synapse.getIdentification() );
      outgoingSynapses.sort( ( a, b ) -> { return a.compareTo( b ); } );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addAllOutgoingSynapses( Iterator< Synapse > incoming ) {
      incoming.forEachRemaining( synapse -> addOutgoingSynapse( synapse ) );
   }// End Method
   
   @Override public Iterator< String > outgoingSynapseIterator(){
      return outgoingSynapses.iterator();
   }

   /**
    * {@inheritDoc}
    */
   @Override public void setThresholdFunction( Class< ? extends ThresholdFunction > functionType ) {
      thresholdFunction = functionType;
   }// End Method
   
   @Override public Class< ? extends ThresholdFunction > getThresholdFunction(){
      return thresholdFunction;
   }

   /**
    * {@inheritDoc}
    */
   @Override public void setCurrentOutput( Double output ) {
      currentOutput = output;
   }// End Method
   
   @Override public Double getCurrentOutput(){
      return currentOutput;
   }

   @Override public Neuron unwrap() {
      Neuron neuron = RequestSystem.retrieve( Neuron.class, identification );
      if ( neuron == null ){
         neuron = new Neuron( position );
         RequestSystem.store( neuron );
      }
      return neuron;
   }

}// End Class
