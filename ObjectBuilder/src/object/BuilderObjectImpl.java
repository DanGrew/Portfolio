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
import objecttype.BuilderType;
import property.Property;
import property.PropertyImpl;
import propertytype.PropertyType;

/**
 * Implementation of the {@link BuilderObject}.
 */
public class BuilderObjectImpl extends SingletonImpl< SerializableBuilderObject >implements BuilderObject {
   
   private BuilderType type;
   private Map< PropertyType, Property > propertyValues;
   
   /**
    * Constructs a new {@link BuilderObjectImpl}.
    * @param type the {@link BuilderType} this object is representing.
    * @param name the identification for the {@link BuilderObject}.
    */
   public BuilderObjectImpl( BuilderType type, String name ) {
      super( name );
      this.type = type;
      propertyValues = new HashMap< PropertyType, Property >();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public BuilderType getBuilderType() {
      return type;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object get( PropertyType propertyType ) {
      if ( type.hasProperty( propertyType ) ) {
         Property property = retrieveProperty( propertyType );
         return property.getValue();
      }
      return null;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void set( PropertyType propertyType, Object value ) {
      if ( type.hasProperty( propertyType ) ) {
         Property property = retrieveProperty( propertyType );
         property.setValue( value );
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
      serializable.setBuilderType( getBuilderType() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableBuilderObject serialized ) {
      type = serialized.getBuilderType();
   }// End Method

}// End Class
