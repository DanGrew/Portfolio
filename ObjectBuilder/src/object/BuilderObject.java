/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package object;

import model.data.SingletonSerialization;
import objecttype.Definition;
import propertytype.PropertyType;

/**
 * THe {@link BuilderObject} defines the interface to an object created to support a 
 * range of {@link PropertyType}s defined by a {@link Definition}. This object will 
 * hold the {@link Property}s representing those types.
 */
public interface BuilderObject extends SingletonSerialization< SerializableBuilderObject > {

   /** Public events raised by the {@link BuilderObject}.**/
   public enum Events {
      PropertySet
   }// End Enum
   
   /**
    * Getter for the {@link PropertyType} given.
    * @param property the {@link PropertyType} to retrieve the value for.
    * @return the {@link Object} value associated.
    */
   public Object get( PropertyType property );
   
   /**
    * Setter for the {@link PropertyType} and value given.
    * @param type the {@link PropertyType} being set.
    * @param value the {@link Object} value to set.
    */
   public void set( PropertyType type, Object value );
   
   /**
    * Getter for the associated {@link Definition} for this {@link BuilderObject}.
    * @return the {@link Definition}.
    */
   public Definition getDefinition();
   
}// End Interface
