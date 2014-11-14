/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package representation.xml.wrapper;

import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import architecture.request.RequestSystem;
import model.data.SerializableNeuron;
import model.singleton.Neuron;
import representation.xml.model.XmlNeuron;

/**
 * The {@link XmlNeuronWrapper} provides an extension to the {@link XmlCollectionWrapper} to
 * wrap {@link Neuron}s in their associated {@link XmlNeuron}s.
 */
@XmlRootElement @XmlSeeAlso( { XmlNeuron.class } )
public class XmlNeuronWrapper extends XmlCollectionWrapper< Neuron, SerializableNeuron >{
   
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
   }

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      objects.forEach( object -> {
         Neuron neuron = RequestSystem.retrieve( Neuron.class, object.getIdentification() );
         if ( neuron == null ){
            throw new NullPointerException( object.getIdentification() + " does not exist." );
         } else {
            neuron.read( object );
         }
      } );
   }// End Method
   
}// End Class
