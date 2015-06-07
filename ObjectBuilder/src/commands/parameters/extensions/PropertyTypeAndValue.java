/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters.extensions;

import propertytype.PropertyType;

/**
 * The {@link PropertyTypeAndValue} holds a pair, a {@link PropertyType} and a value to set
 * for it.
 */
public class PropertyTypeAndValue {
   
   private PropertyType propertyType;
   private Object value;
   
   /**
    * Constructs a new {@link PropertyTypeAndValue}.
    * @param type the {@link PropertyType}.
    * @param value the value.
    */
   public PropertyTypeAndValue( PropertyType type, Object value ) {
      this.propertyType = type;
      this.value = value;
   }// End Constructor

   /**
    * Getter for the {@link PropertyType}.
    * @return the {@link PropertyType}.
    */
   public PropertyType getPropertyType() {
      return propertyType;
   }// End Method

   /**
    * Getter for the value.
    * @return the {@link Object} value.
    */
   public Object getValue() {
      return value;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( propertyType == null ) ? 0 : propertyType.getIdentification().hashCode() );
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
      if ( obj == null ) {
         return false;
      }
      if ( getClass() != obj.getClass() ) {
         return false;
      }
      PropertyTypeAndValue other = ( PropertyTypeAndValue ) obj;
      if ( propertyType == null ) {
         if ( other.propertyType != null ) {
            return false;
         }
      } else if ( !propertyType.equals( other.propertyType ) ) {
         return false;
      }
      if ( value == null ) {
         if ( other.value != null ) {
            return false;
         }
      } else if ( !value.equals( other.value ) ) {
         return false;
      }
      return true;
   }// End Method
   
}// End Class
