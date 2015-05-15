/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package property;

import propertytype.PropertyType;

/**
 * The {@link Property} defines the interface for a piece of data stored for a 
 * specific {@link PropertyType}.
 */
public interface Property {
   
   /**
    * Getter for the display name associated with the {@link PropertyType}.
    * @return the {@link String} display name.
    */
   public String getDisplayName();
   
   /**
    * Setter for the value associated with the {@link Property}.
    * @param value the value matching the {@link PropertyType}.
    */
   public void setValue( Object value );
   
   /**
    * Getter for the value associated with the {@link Property}.
    * @return the {@link Object} value.
    */
   public Object getValue();
   
   /**
    * Getter for the {@link PropertyType} associated with the {@link Property}.
    * @return the {@link PropertyType} associated.
    */
   public PropertyType getType();
   
   /**
    * Method to determine whether the given value is appropriate for this {@link Property} and its
    * {@link PropertyType}.
    * @param value the {@link Object} vaue.
    * @return true if appropriate.
    */
   public boolean isCorrectType( Object value );
   
}// End Interface
