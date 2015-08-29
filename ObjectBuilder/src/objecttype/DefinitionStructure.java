/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import architecture.request.RequestSystem;
import object.BuilderObject;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * {@link DefinitionStructure} provides structural information for the {@link Definition}.
 */
public class DefinitionStructure {
   
   private static final PropertyType NAME = new PropertyTypeImpl( "Name", String.class );
   
   /**
    * Method to initialise the {@link DefinitionStructure} in the system.
    */
   public static void initialise(){
      RequestSystem.store( getNamePropertyType(), PropertyType.class );
   }//End Method
   
   /**
    * Method to get the name {@link PropertyType} common to all {@link BuilderObject}s and {@link Definition}s.
    * @return the {@link PropertyType} singleton.
    */
   public static PropertyType getNamePropertyType(){
      return NAME;
   }//End Method

}//End Class
