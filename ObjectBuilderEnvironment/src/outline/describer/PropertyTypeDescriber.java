/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import propertytype.PropertyType;

/**
 * {@link OutlineDescriber} for the {@link PropertyType}.
 */
public class PropertyTypeDescriber extends SingletonDescriberImpl< PropertyType >{

   public static final String TYPE = "PropertyType";
   public static final String CLASS = "Class";
   
   /**
    * Constructs a new {@link PropertyTypeDescriber}.
    * @param propertyType the {@link PropertyType}.
    */
   public PropertyTypeDescriber( PropertyType propertyType ) {
      super( propertyType, TYPE );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnDescription(int columnReference) {
      switch ( columnReference ) {
         case 1:
            return CLASS;
         default:
            return super.getColumnDescription( columnReference );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnEntry(int columnReference) {
      switch ( columnReference ) {
         case 1:
            return getSingleton().getParameterType().getName();
         default:
            return super.getColumnEntry( columnReference );
      }
   }// End Method
   
}// End Class
