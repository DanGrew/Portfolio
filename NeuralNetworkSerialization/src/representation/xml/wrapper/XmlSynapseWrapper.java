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

import model.data.SerializableSynapse;
import model.singleton.Synapse;
import representation.xml.model.XmlSynapse;
import architecture.request.RequestSystem;

/**
 * The {@link XmlSynapseWrapper} defines an extension to the {@link XmlCollectionWrapper} to wrap
 * {@link Synapse}s in {@link XmlSynapse}s for XML storage.
 */
@XmlRootElement @XmlSeeAlso( { XmlSynapse.class } )
public class XmlSynapseWrapper extends XmlCollectionWrapper< Synapse, SerializableSynapse >{
   
   /**
    * Constructs a new {@link XmlSynapseWrapper}.
    */
   public XmlSynapseWrapper(){
      super();
   }// End Constructor
   
   /**
    * Constructs a new {@link XmlSynapseWrapper} with an {@link Iterator} of {@link Synapse}s to 
    * populate the collection.
    * @param iterator the {@link Iterator} of {@link Synapse}s to wrap and add.
    */
   public XmlSynapseWrapper( Iterator< Synapse > iterator ){
      super( iterator );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void addUnwrapped( Synapse object ) {
      addObject( object.write( XmlSynapse.class ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      objects.forEach( object -> {
         Synapse synapse = RequestSystem.retrieve( Synapse.class, object.getIdentification() );
         synapse.read( object );
      } );
   }// End Method
   
}// End Class
