/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import java.util.Collection;

import model.data.SerializedSingleton;
import propertytype.PropertyType;

/**
 * The {@link SerializableDefinition} provides a interface defining how to serialize a 
 * {@link Definition} {@link Singleton}.
 */
public interface SerializableDefinition extends SerializedSingleton< Definition > {

   /**
    * Method to add all {@link PropertyType}s given to the {@link SerializableDefinition}.
    * @param types the {@link PropertyType}s associated with the {@link Definition}.
    */
   public void addAllPropertyTypes( Collection< PropertyType > types ) ;
   
   /**
    * Method to resolve the parsed {@link PropertyType} identifications to the {@link PropertyType}s
    * they represent.
    * @return the resolve {@link PropertyType}s in a {@link Collection}.
    */
   public Collection< PropertyType > resolvePropertyTypes();
   
}// End Interface
