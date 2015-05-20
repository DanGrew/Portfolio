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
import model.singleton.Singleton;
import propertytype.PropertyType;

/**
 * The {@link SerializableBuilderType} provides a interface defining how to serialize a 
 * {@link BuilderType} {@link Singleton}.
 */
public interface SerializableBuilderType extends SerializedSingleton< BuilderType > {

   /**
    * Method to add all {@link PropertyType}s given to the {@link SerializableBuilderType}.
    * @param types the {@link PropertyType}s associated with the {@link BuilderType}.
    */
   public void addAllPropertyTypes( Collection< PropertyType > types ) ;
   
   /**
    * Method to resolve the parsed {@link PropertyType} identifications to the {@link PropertyType}s
    * they represent.
    * @return the resolve {@link PropertyType}s in a {@link Collection}.
    */
   public Collection< PropertyType > resolvePropertyTypes();
   
}// End Interface
