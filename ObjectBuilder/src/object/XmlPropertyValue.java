/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import java.io.Serializable;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;

import architecture.request.RequestSystem;
import property.Property;
import property.PropertyImpl;
import propertytype.PropertyType;

/**
 * The {@link XmlPropertyValue} is responsible serializing and deserializing a {@link Property} and its
 * value for storage in an Xml data structure.
 */
public class XmlPropertyValue {
   @XmlElement private String type;
   @XmlElement private Object value;
   
   /**
    * Default constructor for {@link JAXB}.
    */
   public XmlPropertyValue(){}
   
   /**
    * Constructs a new {@link XmlPropertyValue}.
    * @param property the {@link Property} defining the data to serialize.
    */
   public XmlPropertyValue( Property property ) {
      this.type = property.getType().getIdentification();
      this.value = property.serializeValue();
   }// End Constructor
   
   /**
    * Method to deserialize the data into a {@link Property}.
    * @return the deserialized {@link Property}.
    */
   public Property deserialize(){
      PropertyType propertyType = RequestSystem.retrieve( PropertyType.class, type );
      Object deserializedValue = propertyType.deserialize( ( Serializable )value );
      Property property = new PropertyImpl( propertyType );
      property.setValue( deserializedValue );
      return property;
   }// End Method
}// End Class
