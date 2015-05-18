/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

import model.singleton.SingletonImpl;

/**
 * Implementation of the {@link PropertyType}.
 */
public class PropertyTypeImpl extends SingletonImpl< SerializablePropertyType > implements PropertyType {

   private Class< ? > typeClass;

   /**
    * Constructs a new {@link PropertyTypeImpl}.
    * @param displayName the {@link String} display name of the {@link PropertyType}.
    * @param clazz the {@link Class} associated with the type.
    */
   public PropertyTypeImpl( String displayName, Class< ? > clazz ) {
      super( displayName );
      this.typeClass = clazz;
   }// End Class
   
   /**
    * {@inheritDoc}
    */
   @Override public Class< ? > getTypeClass() {
      return typeClass;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isCorrectType( Object value ) {
      return typeClass.isAssignableFrom( value.getClass() );
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
   @Override public String toString() {
      return getDisplayName();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void writeSingleton( SerializablePropertyType serializable ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void readSingleton( SerializablePropertyType serialized ) {
      throw new UnsupportedOperationException( "Not implemented yet." );
   }// End Method

}// End Class
