/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package serialization;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import objecttype.Definition;
import objecttype.SerializableDefinition;
import objecttype.XmlDefinitionImpl;
import representation.xml.wrapper.XmlCollectionWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlDefinitionWrapper} provides a wrapper for all {@link Definition}s in the system.
 */
@XmlRootElement
public class XmlDefinitionWrapper extends XmlCollectionWrapper< Definition, SerializableDefinition >{

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      objects.forEach( object -> {
         Definition definition = RequestSystem.retrieve( Definition.class, object.getIdentification() );
         if ( definition == null ){
            throw new NullPointerException( object.getIdentification() + " does not exist." );
         } else {
            definition.read( object );
         }
      } );
   }// End Method

   /**
    * {@inheritDoc} 
    */
   @Override public void addUnwrapped( Definition object ) {
      SerializableDefinition serialized = object.write( XmlDefinitionImpl.class );
      super.addObject( serialized );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< Definition > retrieveSingletons() {
      return super.retrieveSingletons( Definition.class );
   }// End Method

}// End Class
