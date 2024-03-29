/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import representation.xml.wrapper.SingletonCollectionWrapper;

/**
 * The {@link XmlSearchSpaceWrapper} provides an Xml wrapper for the {@link SearchSpace}s in the 
 * system.
 */
@XmlRootElement
public class XmlSearchSpaceWrapper extends SingletonCollectionWrapper< SearchSpace, SerializableSearchSpace >{

   /**
    * {@inheritDoc}
    */
   @Override public void addUnwrapped( SearchSpace object ) {
      SerializableSearchSpace serialized = object.write( XmlSearchSpaceImpl.class );
      super.addObject( serialized );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< SearchSpace > retrieveSingletons() {
      return super.retrieveSingletons( SearchSpace.class );
   }// End Method

}// End Class
