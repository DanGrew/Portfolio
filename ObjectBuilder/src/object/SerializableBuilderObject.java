/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import java.util.List;

import model.data.SerializedSingleton;
import objecttype.Definition;
import property.Property;

/**
 * The {@link SerializableBuilderObject} provides the interface any {@link Serializable} form
 * of {@link BuilderObject} should implement.
 */
public interface SerializableBuilderObject extends SerializedSingleton< BuilderObject > {

   /**
    * Setter for the associated {@link Definition}.
    * @param definition the {@link Definition}.
    */
   public void setDefinition( Definition definition );
   
   /**
    * Getter for the {@link Definition}.
    * @return the {@link Definition}.
    */
   public Definition getDefinition();
   
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
