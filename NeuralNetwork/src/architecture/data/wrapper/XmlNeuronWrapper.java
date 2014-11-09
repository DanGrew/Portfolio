/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data.wrapper;

import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import model.data.read.SerializableNeuron;
import model.data.write.SerializedNeuron;
import model.singleton.Neuron;
import architecture.schema.model.singleton.XmlNeuron;

/**
 * The {@link XmlNeuronWrapper} provides an extension to the {@link XmlCollectionWrapper} to
 * wrap {@link Neuron}s in their associated {@link XmlNeuron}s.
 */
@XmlRootElement @XmlSeeAlso( { XmlNeuron.class } )
public class XmlNeuronWrapper extends XmlCollectionWrapper< Neuron, SerializableNeuron, SerializedNeuron >{
   
   /**
    * Constructs a new {@link XmlNeuronWrapper}.
    */
   public XmlNeuronWrapper(){
      super();
   }// End Constructor
   
   /**
    * Constructs a new {@link XmlNeuronWrapper}.
    * @param iterator the {@link Iterator} of {@link Neuron}s to wrap and add.
    */
   public XmlNeuronWrapper( Iterator< Neuron > iterator ){
      super( iterator );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void addUnwrapped( Neuron object ) {
      addObject( object.write( XmlNeuron.class ) );
   }// End Method

}// End Class
