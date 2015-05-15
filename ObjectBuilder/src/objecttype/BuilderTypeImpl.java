/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import java.util.Collection;
import java.util.LinkedHashSet;

import property.Property;
import propertytype.PropertyType;

/**
 * Implementation of the {@link BuilderType}.
 */
public class BuilderTypeImpl implements BuilderType {

   private Collection< PropertyType > properties;
   
   /**
    * Constructs a new {@link BuilderTypeImpl}.
    */
   public BuilderTypeImpl() {
      properties = new LinkedHashSet< PropertyType >();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void addPropertyType( PropertyType property ) {
      properties.add( property );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean hasProperty( PropertyType property ) {
      return properties.contains( property );
   }// End Method

}// End Class
