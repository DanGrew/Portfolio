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

import annotation.Cali;
import architecture.event.EventSystem;
import model.singleton.SingletonImpl;
import propertytype.PropertyType;

/**
 * Implementation of the {@link Definition}.
 */
@Cali public class DefinitionImpl extends SingletonImpl< SerializableDefinition > implements Definition {

   private Collection< PropertyType > properties;
   
   /**
    * Constructs a new {@link DefinitionImpl}.
    * @param identification the {@link String} name of the {@link Definition}.
    */
   @Cali public DefinitionImpl( String identification ) {
      super( identification );
      properties = new LinkedHashSet< PropertyType >();
      properties.add( getNamePropertyType() );
      this.identification = identification;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public PropertyType getNamePropertyType() {
      return DefinitionStructure.getNamePropertyType();
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void addPropertyType( PropertyType property ) {
      if ( !properties.contains( property ) ) {
         properties.add( property );
         EventSystem.raiseEvent( Definition.Events.PropertyAdded, this );
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
   @Override protected void writeSingleton( SerializableDefinition serializable ) {
      serializable.addAllPropertyTypes( properties );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializableDefinition serialized ) {
      properties = serialized.resolvePropertyTypes();
   }// End Method

}// End Class
