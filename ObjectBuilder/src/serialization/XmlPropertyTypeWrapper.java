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

import propertytype.PropertyType;
import propertytype.SerializablePropertyType;
import propertytype.XmlPropertyTypeImpl;
import representation.xml.wrapper.XmlCollectionWrapper;

/**
 * The {@link XmlPropertyTypeWrapper} provides an Xml wrapper for the {@link PropertyType}s in the 
 * system.
 */
@XmlRootElement
public class XmlPropertyTypeWrapper extends XmlCollectionWrapper< PropertyType, SerializablePropertyType >{

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {}

   /**
    * {@inheritDoc}
    */
   @Override public void addUnwrapped( PropertyType object ) {
      SerializablePropertyType serialized = object.write( XmlPropertyTypeImpl.class );
      super.addObject( serialized );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public List< PropertyType > retrieveSingletons() {
      return super.retrieveSingletons( PropertyType.class );
   }// End Method

}// End Class
