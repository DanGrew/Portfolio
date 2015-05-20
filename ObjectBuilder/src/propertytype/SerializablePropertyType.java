/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import model.data.SerializedSingleton;

/**
 * {@link SerializedSingleton} for the {@link PropertyType}.
 */
public interface SerializablePropertyType extends SerializedSingleton< PropertyType >{

   /**
    * Method to set the {@link Class} associated with the {@link PropertyType}.
    * @param clazz the {@link Class}.
    */
   public void setTypeClass( Class< ? > clazz );
   
   /**
    * Getter for the {@link Class} associated with the {@link PropertyType}.
    * @return the {@link Class}.s
    */
   public Class< ? > getTypeClass();
   
}// End Interface
