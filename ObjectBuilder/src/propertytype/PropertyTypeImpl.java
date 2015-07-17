/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import java.io.Serializable;

import model.singleton.SingletonImpl;
import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import annotation.Cali;

/**
 * Implementation of the {@link PropertyType}.
 */
@Cali public class PropertyTypeImpl extends SingletonImpl< SerializablePropertyType > implements PropertyType {

   private ClassParameterType typeClass;

   /**
    * Constructs a new {@link PropertyTypeImpl}.
    * @param displayName the {@link String} display name of the {@link PropertyType}.
    * @param clazz the {@link Class} associated with the type.
    */
   public PropertyTypeImpl( String displayName, Class< ? > clazz ) {
      super( displayName );
      this.typeClass = ClassParameterTypes.valueOf( clazz.getSimpleName() );
      if ( typeClass == null ) {
         throw new IllegalArgumentException( "Unsupported class provided." );
      }
   }// End Class
   
   /**
    * Constructs a new {@link PropertyTypeImpl}.
    * @param displayName the {@link String} display name of the {@link PropertyType}.
    * @param clazz the {@link ClassParameterType} associated with the type.
    */
   public PropertyTypeImpl( String displayName, ClassParameterType clazz ) {
      super( displayName );
      this.typeClass = clazz;
   }// End Class
   
   /**
    * Constructs a new {@link PropertyTypeImpl}.
    * @param displayName the {@link String} display name of the {@link PropertyType}.
    * @param clazz the {@link ClassParameterType} associated with the type.
    * @throws CaliConstructionException 
    */
   @Cali public PropertyTypeImpl( String displayName, String parameterType ) {
      super( displayName );
      this.typeClass = ClassParameterTypes.valueOf( parameterType );
      if ( typeClass == null ) {
         this.typeClass = ClassParameterTypes.STRING_PARAMETER_TYPE;
      }
   }// End Class
   
   /**
    * {@inheritDoc}
    */
   @Override public Class< ? > getTypeClass() {
      return typeClass.getTypeClass();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public ClassParameterType getParameterType() {
      return typeClass;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isCorrectType( Object value ) {
      if ( value != null ) {
         return typeClass.getTypeClass().isAssignableFrom( value.getClass() );
      } else {
         return false;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getDisplayName() {
      return getIdentification();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( getIdentification() == null ) ? 0 : getIdentification().hashCode() );
      result = prime * result + ( ( typeClass == null ) ? 0 : typeClass.hashCode() );
      return result;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean equals( Object object ) {
      if ( object instanceof PropertyType ) {
         PropertyType type = ( PropertyType )object;
         if ( !type.getDisplayName().equals( getDisplayName() ) ) {
            return false;
         }
         if ( !type.getTypeClass().equals( getTypeClass() ) ) {
            return false;
         }
         return true;
      }
      return false;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Serializable serialize( Object value ) {
      return typeClass.serialize( value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object deserialize( Serializable value ) {
      return typeClass.deserialize( value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      return getDisplayName();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializablePropertyType serializable ) {
      serializable.setTypeClass( typeClass );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializablePropertyType serialized ) {
      typeClass = serialized.getTypeClass();
   }// End Method

}// End Class
