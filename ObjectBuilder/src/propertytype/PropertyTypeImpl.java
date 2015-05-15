/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package propertytype;

/**
 * Implementation of the {@link PropertyType}.
 */
public class PropertyTypeImpl implements PropertyType {

   private String displayName;
   private Class< ? > typeClass;

   /**
    * Constructs a new {@link PropertyTypeImpl}.
    * @param displayName the {@link String} display name of the {@link PropertyType}.
    * @param clazz the {@link Class} associated with the type.
    */
   public PropertyTypeImpl( String displayName, Class< ? > clazz ) {
      this.typeClass = clazz;
      this.displayName = displayName;
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
      return displayName;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( displayName == null ) ? 0 : displayName.hashCode() );
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

}// End Class
