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

import object.BuilderObject;
import object.SerializableBuilderObject;
import object.XmlBuilderObjectImpl;
import representation.xml.wrapper.XmlCollectionWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlBuilderObjectWrapper} provides a wrapper for all {@link BuilderObject}s in the system.
 */
@XmlRootElement
public class XmlBuilderObjectWrapper extends XmlCollectionWrapper< BuilderObject, SerializableBuilderObject >{

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      objects.forEach( object -> {
         BuilderObject objectImpl = RequestSystem.retrieve( BuilderObject.class, object.getIdentification() );
         if ( objectImpl == null ){
            throw new NullPointerException( object.getIdentification() + " does not exist." );
         } else {
            objectImpl.read( object );
         }
      } );
   }// End Method

   /**
    * {@inheritDoc} 
    */
   @Override public void addUnwrapped( BuilderObject object ) {
      SerializableBuilderObject serialized = object.write( XmlBuilderObjectImpl.class );
      super.addObject( serialized );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< BuilderObject > retrieveSingletons() {
      return super.retrieveSingletons( BuilderObject.class );
   }// End Method

}// End Class
