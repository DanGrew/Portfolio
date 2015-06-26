/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package objecttype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import model.singleton.SingletonImpl;
import propertytype.PropertyType;
import annotation.Cali;

/**
 * Implementation of the {@link BuilderType}.
 */
@Cali public class BuilderTypeImpl extends SingletonImpl< SerializableBuilderType > implements BuilderType {

   private Collection< PropertyType > properties;
   
   /**
    * Constructs a new {@link BuilderTypeImpl}.
    * @param identification the {@link String} name of the {@link BuilderType}.
    */
   @Cali public BuilderTypeImpl( String identification ) {
      super( identification );
      properties = new LinkedHashSet< PropertyType >();
      this.identification = identification;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void addPropertyType( PropertyType property ) {
      if ( !properties.contains( property ) ) {
         properties.add( property );
      }
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
   @Override public List< PropertyType > getPropertyTypes() {
      return new ArrayList< PropertyType >( properties );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializableBuilderType serializable ) {
      serializable.addAllPropertyTypes( properties );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableBuilderType serialized ) {
      properties = serialized.resolvePropertyTypes();
   }// End Method

}// End Class
