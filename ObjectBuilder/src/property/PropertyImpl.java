/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package property;

import java.io.Serializable;

import propertytype.PropertyType;

/**
 * Implementation of the {@link Property} interface providing a {@link PropertyType}
 * with value pairing for a single instance of data.
 */
public class PropertyImpl implements Property {

   private PropertyType type;
   private Object value;
   
   /**
    * Constructs a new {@link PropertyImpl}.
    * @param type the {@link PropertyType} being represented.
    */
   public PropertyImpl( PropertyType type ) {
      this.type = type;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void setValue( Object value ) {
      if ( isCorrectType( value ) ) {
         this.value = value;
      } else {
         Object deserialized = type.deserialize( value.toString() );
         this.value = deserialized;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public PropertyType getType() {
      return type;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object getValue() {
      return value;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isCorrectType( Object value ) {
      return type.isCorrectType( value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getDisplayName() {
      return type.getDisplayName();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Serializable serializeValue() {
      return type.serialize( getValue() );
   }// End Method
   
}// End Class
