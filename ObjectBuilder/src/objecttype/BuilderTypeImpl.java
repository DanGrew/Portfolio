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

import model.singleton.SingletonImpl;
import property.Property;
import propertytype.PropertyType;

/**
 * Implementation of the {@link BuilderType}.
 */
public class BuilderTypeImpl extends SingletonImpl< SerializableBuilderType > implements BuilderType {

   private Collection< PropertyType > properties;
   
   /**
    * Constructs a new {@link BuilderTypeImpl}.
    * @param identification the {@link String} name of the {@link BuilderType}.
    */
   public BuilderTypeImpl( String identification ) {
      super( identification );
      properties = new LinkedHashSet< PropertyType >();
      this.identification = identification;
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

   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializableBuilderType serializable ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableBuilderType serialized ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }// End Method

}// End Class
