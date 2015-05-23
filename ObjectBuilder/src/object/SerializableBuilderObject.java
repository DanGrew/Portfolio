/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import java.io.Serializable;
import java.util.List;

import model.data.SerializedSingleton;
import objecttype.BuilderType;
import property.Property;

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
   
   /**
    * Method to set the value for the given {@link Property}.
    * @param property the {@link Property} to set the value for.
    */
   public void setValue( Property property );
   
   /**
    * Method to get a {@link List} of {@link Property}s deserialized from the data.
    * @return a {@link List} of deserialized {@link Property}s.
    */
   public List< Property > getValues();
   
}// End Interface
