/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import java.util.HashMap;
import java.util.Map;

import model.singleton.SingletonImpl;
import objecttype.Definition;
import property.Property;
import property.PropertyImpl;
import propertytype.PropertyType;
import annotation.Cali;
import architecture.event.EventSystem;

/**
 * Implementation of the {@link BuilderObject}.
 */
@Cali public class BuilderObjectImpl extends SingletonImpl< SerializableBuilderObject >implements BuilderObject {
   
   private Definition type;
   private Map< PropertyType, Property > propertyValues;
   
   /**
    * Constructs a new {@link BuilderObjectImpl}.
    * @param type the {@link Definition} this object is representing.
    * @param name the identification for the {@link BuilderObject}.
    */
   @Cali public BuilderObjectImpl( String name, Definition type ) {
      super( name );
      this.type = type;
      propertyValues = new HashMap< PropertyType, Property >();
      
      PropertyType nameProperty = type.getNamePropertyType();
      if ( nameProperty != null ) {
         Property property = retrieveProperty( nameProperty );
         property.setValue( getIdentification() );
         propertyValues.put( type.getNamePropertyType(), property );
      }
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public Definition getDefinition() {
      return type;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public Object get( PropertyType propertyType ) {
      if ( type.hasProperty( propertyType ) ) {
         Property property = retrieveProperty( propertyType );
         return property.getValue();
      }
      return null;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void set( PropertyType propertyType, Object value ) {
      if ( propertyType.equals( type.getNamePropertyType() ) ) {
         //Cannot change name at this point.
         return;
      }
      if ( type.hasProperty( propertyType ) ) {
         Property property = retrieveProperty( propertyType );
         property.setValue( value );
         EventSystem.raiseEvent( BuilderObject.Events.PropertySet, this );
      }
   }// End Method
   
   /**
    * Method to retrieve the given {@link PropertyType} from the internal storage of this object.
    * @param propertyType the {@link PropertyType} required.
    * @return the {@link Property}, created if not defined.
    */
   private Property retrieveProperty( PropertyType propertyType ){
      Property property = propertyValues.get( propertyType );
      if ( property == null ) {
         property = new PropertyImpl( propertyType );
         propertyValues.put( propertyType, property );
      }
      return property;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializableBuilderObject serializable ) {
      serializable.setDefinition( getDefinition() );
      for ( Property property : propertyValues.values() ) {
         serializable.setValue( property );
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableBuilderObject serialized ) {
      type = serialized.getDefinition();
      for ( Property property : serialized.getValues() ) {
         set( property.getType(), property.getValue() );
      }
   }// End Method

}// End Class
