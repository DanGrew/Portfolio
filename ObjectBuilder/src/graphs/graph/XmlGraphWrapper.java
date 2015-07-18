/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import representation.xml.wrapper.XmlCollectionWrapper;

/**
 * The {@link XmlGraphWrapper} provides an Xml wrapper for the {@link Graph}s in the 
 * system.
 */
@XmlRootElement
public class XmlGraphWrapper extends XmlCollectionWrapper< Graph, SerializableGraph >{

   /**
    * {@inheritDoc}
    */
   @Override public void addUnwrapped( Graph object ) {
      SerializableGraph serialized = object.write( XmlGraphImpl.class );
      super.addObject( serialized );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< Graph > retrieveSingletons() {
      return super.retrieveSingletons( Graph.class );
   }// End Method

}// End Class
