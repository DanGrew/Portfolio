/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import java.util.List;

import object.BuilderObject;
import propertytype.PropertyType;

/**
 * {@link OutlineDescriber} for the {@link BuilderObject}s.
 */
public class BuilderObjectDescriber extends SingletonDescriberImpl< BuilderObject >{

   public static final String NAME = "Name";
   
   /**
    * Constructs a new {@link BuilderObjectDescriber}.
    * @param singleton the {@link BuilderObject}.
    */
   public BuilderObjectDescriber( BuilderObject singleton ) {
      super( singleton, NAME );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnDescription( int columnReference ) {
      List< PropertyType > types = getSingleton().getDefinition().getPropertyTypes();
      if ( columnReference == 0 || !hasSingleton() || columnReference > types.size() ) {
         return super.getColumnDescription( columnReference );
      } else {
         return types.get( columnReference - 1 ).getIdentification();
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnEntry( int columnReference ) {
      List< PropertyType > types = getSingleton().getDefinition().getPropertyTypes();
      if ( columnReference == 0 || !hasSingleton() || columnReference > types.size() ) {
         return super.getColumnEntry( columnReference );
      } else {
         Object value = getSingleton().get( types.get( columnReference - 1 ) );
         if ( value == null ) {
            return null;
         } else {
            return value.toString();
         }
      }
   }// End Method

}// End Class
