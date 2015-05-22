/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import javax.xml.bind.annotation.XmlElement;

import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
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
   @Override public void setTypeClass( ClassParameterType clazz ) {
      this.typeClass = clazz.getTypeClass();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public ClassParameterType getTypeClass() {
      return ClassParameterTypes.valueOf( typeClass );
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
   
   /**
    * {@inheritDoc}
    */
   @Override public void resolve() {
      PropertyType type = RequestSystem.retrieve( PropertyType.class, getIdentification() ); 
      if ( type == null ) {
         throw new IllegalStateException( "PropertyType must exist at this point." );
      } else {
         type.read( this );
      }
   }// End Method

}// End Class
