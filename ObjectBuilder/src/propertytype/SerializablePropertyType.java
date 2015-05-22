/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import parameter.classparameter.ClassParameterType;
import model.data.SerializedSingleton;

/**
 * {@link SerializedSingleton} for the {@link PropertyType}.
 */
public interface SerializablePropertyType extends SerializedSingleton< PropertyType >{

   /**
    * Method to set the {@link ClassParameterType} associated with the {@link PropertyType}.
    * @param clazz the {@link ClassParameterType}.
    */
   public void setTypeClass( ClassParameterType clazz );
   
   /**
    * Getter for the {@link ClassParameterType} associated with the {@link PropertyType}.
    * @return the {@link ClassParameterType}.
    */
   public ClassParameterType getTypeClass();
   
}// End Interface
