/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import java.io.Serializable;

import model.data.SingletonSerialization;
import parameter.classparameter.ClassParameterType;

/**
 * {@link PropertyType} defines the basic structure of a {@link Property} defining the
 * {@link Class} associated and a unique identifier for its display name,
 */
public interface PropertyType extends SingletonSerialization< SerializablePropertyType > {
   
   /**
    * Getter for the {@link Class} associated with the data type.
    * @return the {@link Class}.
    */
   public Class< ? > getTypeClass();
   
   /**
    * Getter for the {@link ClassParameterType} associated.
    * @return the {@link ClassParameterType}.
    */
   public ClassParameterType getParameterType();
   
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
   
   /**
    * Method to serialize the given {@link Object} according to the associated {@link ClassParameterType}.
    * @param value the value to serialize.
    * @return the serialized version of the {@link Object}.
    */
   public Serializable serialize( Object value );
   
   /**
    * Method to deserialize the given serialized {@link Object}.
    * @param value the serialized {@link Object}.
    * @return the deserialized version of the object.
    */
   public Object deserialize( Serializable value );
   
}// End Interface
