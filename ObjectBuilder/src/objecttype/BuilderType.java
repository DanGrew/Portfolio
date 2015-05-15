/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import propertytype.PropertyType;

/**
 * The {@link BuilderType} provides the interface for specification of an object, holding the
 * property types the object should define.
 */
public interface BuilderType {

   /**
    * Method to add a {@link PropertyType} to the {@link BuilderType}.
    * @param property the {@link PropertyType} to add.
    */
   public void addPropertyType( PropertyType property );
   
   /**
    * Method to determine whether the {@link BuilderType} has the given {@link PropertyType}.
    * @param property the {@link PropertyType} in question.
    * @return true if the {@link PropertyType} is held by the object.
    */
   public boolean hasProperty( PropertyType property );
}// End Interface
