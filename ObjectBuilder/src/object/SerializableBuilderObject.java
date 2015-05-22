/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import java.io.Serializable;

import objecttype.BuilderType;
import model.data.SerializedSingleton;

/**
 * The {@link SerializableBuilderObject} provides the interface any {@link Serializable} form
 * of {@link BuilderObject} should implement.
 */
public interface SerializableBuilderObject extends SerializedSingleton< BuilderObject > {

   /**
    * Setter for the associated {@link BuilderType}.
    * @param builderType the {@link BuilderType}.
    */
   public void setBuilderType( BuilderType builderType );
   
   /**
    * Getter for the {@link BuilderType}.
    * @return the {@link BuilderType}.
    */
   public BuilderType getBuilderType();
   
}// End Interface
