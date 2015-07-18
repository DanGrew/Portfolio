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

import representation.xml.wrapper.XmlCollectionWrapper;

/**
 * The {@link XmlSearchOnlyWrapper} provides an Xml wrapper for the {@link SearchOnly}s in the 
 * system.
 */
@XmlRootElement
public class XmlSearchOnlyWrapper extends XmlCollectionWrapper< SearchOnly, SerializableSearchOnly >{

   /**
    * {@inheritDoc}
    */
   @Override public void addUnwrapped( SearchOnly object ) {
      SerializableSearchOnly serialized = object.write( XmlSearchOnlyImpl.class );
      super.addObject( serialized );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< SearchOnly > retrieveSingletons() {
      return super.retrieveSingletons( SearchOnly.class );
   }// End Method

}// End Class
