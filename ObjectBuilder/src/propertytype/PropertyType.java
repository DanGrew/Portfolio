/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import model.singleton.Singleton;
import property.Property;

/**
 * {@link PropertyType} defines the basic structure of a {@link Property} defining the
 * {@link Class} associated and a unique identifier for its display name,
 */
public interface PropertyType extends Singleton< SerializablePropertyType >{
   
   /**
    * Getter for the {@link Class} associated with the data type.
    * @return the {@link Class}.
    */
   public Class< ? > getTypeClass();
   
   /**
    * Getter for the display name to be used for the type.
    * @return the {@link String} display name.
    */
   public String getDisplayName();
   
   /**
    * Method to determine whether the given {@link Object} value is appropriate for this
    * {@link PropertyType} and its {@link Class}.
    * @param value the value to check.
    * @return true if appropriate.
    */
   public boolean isCorrectType( Object value );
   
}// End Interface
