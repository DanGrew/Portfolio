/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import javax.xml.bind.annotation.XmlElement;

import representation.xml.wrapper.XmlSingletonWrapper;
import architecture.request.RequestSystem;

/**
 * The {@link XmlPropertyTypeImpl} provides an Xml form of a serialized {@link PropertyType}.
 */
public class XmlPropertyTypeImpl extends XmlSingletonWrapper< PropertyType > implements SerializablePropertyType {

   @XmlElement private Class< ? > typeClass;
   
   /**
    * {@inheritDoc}
    */
   @Override public void setTypeClass( Class< ? > clazz ) {
      this.typeClass = clazz;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Class< ? > getTypeClass() {
      return typeClass;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public PropertyType unwrap() {
      PropertyType type = RequestSystem.retrieve( PropertyType.class, getIdentification() ); 
      if ( type == null ) {
         type = new PropertyTypeImpl( getIdentification(), typeClass );
         RequestSystem.store( type, PropertyType.class );
      }
      return type;
   }// End Method

}// End Class
