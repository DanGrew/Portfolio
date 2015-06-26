/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import java.util.List;

import model.data.SingletonSerialization;
import propertytype.PropertyType;

/**
 * The {@link Definition} provides the interface for specification of an object, holding the
 * property types the object should define.
 */
public interface Definition extends SingletonSerialization< SerializableDefinition > {

   /**
    * Method to add a {@link PropertyType} to the {@link Definition}.
    * @param property the {@link PropertyType} to add.
    */
   public void addPropertyType( PropertyType property );
   
   /**
    * Method to determine whether the {@link Definition} has the given {@link PropertyType}.
    * @param property the {@link PropertyType} in question.
    * @return true if the {@link PropertyType} is held by the object.
    */
   public boolean hasProperty( PropertyType property );
   
   /**
    * Getter for a defensive copy of the {@link PropertyType}s associated.
    * @return a {@link List} of {@link PropertyType}s.
    */
   public List< PropertyType > getPropertyTypes();
}// End Interface
