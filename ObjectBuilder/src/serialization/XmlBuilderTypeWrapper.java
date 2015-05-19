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

import objecttype.BuilderType;
import objecttype.SerializableBuilderType;
import objecttype.XmlBuilderTypeImpl;
import representation.xml.wrapper.XmlCollectionWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlBuilderTypeWrapper} provides a wrapper for all {@link BuilderType}s in the system.
 */
@XmlRootElement
public class XmlBuilderTypeWrapper extends XmlCollectionWrapper< BuilderType, SerializableBuilderType >{

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      objects.forEach( object -> {
         BuilderType builderType = RequestSystem.retrieve( BuilderType.class, object.getIdentification() );
         if ( builderType == null ){
            throw new NullPointerException( object.getIdentification() + " does not exist." );
         } else {
            builderType.read( object );
         }
      } );
   }// End Method

   /**
    * {@inheritDoc} 
    */
   @Override public void addUnwrapped( BuilderType object ) {
      SerializableBuilderType serialized = object.write( XmlBuilderTypeImpl.class );
      super.addObject( serialized );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< BuilderType > retrieveSingletons() {
      return super.retrieveSingletons( BuilderType.class );
   }// End Method

}// End Class
