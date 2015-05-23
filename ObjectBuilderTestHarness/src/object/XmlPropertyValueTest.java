/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import org.junit.Assert;
import org.junit.Test;

import property.Property;
import property.PropertyImpl;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;

import commands.parameters.ObjectBuilderClassParameterTypes;

/**
 * Test for the {@link XmlPropertyValue}.
 */
public class XmlPropertyValueTest {

   /**
    * Method to test the serialization of a {@link String} {@link PropertyType}.
    */
   @Test public void stringPropertyValueTest(){
      final String TYPE_NAME = "StringType";
      final String VALUE_NAME = "Value";
      
      PropertyType type = new PropertyTypeImpl( TYPE_NAME, String.class );
      RequestSystem.store( type, PropertyType.class );
      Property property = new PropertyImpl( type );
      property.setValue( VALUE_NAME );
      XmlPropertyValue propertyValue = new XmlPropertyValue( property );
      
      Property deserialized = propertyValue.deserialize();
      Assert.assertEquals( TYPE_NAME, deserialized.getDisplayName() );
      Assert.assertEquals( VALUE_NAME, deserialized.getValue() );
   }// End Method
   
   /**
    * Method to test the serialization of a {@link Number} {@link PropertyType}.
    */
   @Test public void numberPropertyValueTest(){
      final String TYPE_NAME = "NumberType";
      final Number VALUE_NAME = 123.987;
      
      PropertyType type = new PropertyTypeImpl( TYPE_NAME, Number.class );
      RequestSystem.store( type, PropertyType.class );
      Property property = new PropertyImpl( type );
      property.setValue( VALUE_NAME );
      XmlPropertyValue propertyValue = new XmlPropertyValue( property );
      
      Property deserialized = propertyValue.deserialize();
      Assert.assertEquals( TYPE_NAME, deserialized.getDisplayName() );
      Assert.assertEquals( VALUE_NAME, deserialized.getValue() );
   }// End Method
   
   /**
    * Method to test the serialization of a {@link ObjectBuilderClassParameterTypes#PROPERTY_TYPE_PARAMETER_TYPE} {@link PropertyType}.
    */
   @Test public void referencePropertyValueTest(){
      final String TYPE_NAME = "ReferenceType";
      
      PropertyType type = new PropertyTypeImpl( TYPE_NAME, ObjectBuilderClassParameterTypes.PROPERTY_TYPE_PARAMETER_TYPE );
      RequestSystem.store( type, PropertyType.class );
      Property property = new PropertyImpl( type );
      property.setValue( type );
      XmlPropertyValue propertyValue = new XmlPropertyValue( property );
      
      Property deserialized = propertyValue.deserialize();
      Assert.assertEquals( TYPE_NAME, deserialized.getDisplayName() );
      Assert.assertEquals( type, deserialized.getValue() );
   }// End Method

}// End Class
