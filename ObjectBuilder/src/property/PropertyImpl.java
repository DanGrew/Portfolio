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
      if ( value == null ) {
         this.value = value;
      } else if ( isCorrectType( value ) ) {
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

   /**
    * {@inheritDoc}
    */
   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( type == null ) ? 0 : type.getIdentification().hashCode() );
      result = prime * result + ( ( value == null ) ? 0 : value.hashCode() );
      return result;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean equals( Object obj ) {
      if ( this == obj ) {
         return true;
      }
      if ( obj == null ){
         return false;
      }
      if ( getClass() != obj.getClass() ){
         return false;
      }
      PropertyImpl other = ( PropertyImpl ) obj;
      if ( type == null ) {
         if ( other.type != null ){
            return false;
         }
      } else if ( !type.getIdentification().equals( other.type.getIdentification() ) ){
         return false;
      }
      if ( value == null ) {
         if ( other.value != null ){
            return false;
         }
      } else if ( !value.equals( other.value ) ){
         return false;
      }
      return true;
   }// End Method
   
}// End Class
