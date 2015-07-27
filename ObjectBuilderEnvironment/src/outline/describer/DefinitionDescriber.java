/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import java.util.List;

import model.singleton.Singleton;
import objecttype.Definition;
import propertytype.PropertyType;

/**
 * {@link OutlineDescriber} for {@link Definition}s.
 */
public class DefinitionDescriber extends SingletonDescriberImpl< Definition > {

   public static final String DEFINITION = "Definition";
   
   /**
    * Constructs a new {@link DefinitionDescriber}.
    * @param singleton the {@link Singleton}.
    */
   public DefinitionDescriber( Definition singleton ) {
      super( singleton, DEFINITION );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnEntry( int columnReference ) {
      if ( columnReference == 0 || !hasSingleton() ) {
         return super.getColumnEntry( columnReference );
      }
      List< PropertyType > types = getSingleton().getPropertyTypes();
      if ( columnReference > types.size() ) {
         return super.getColumnEntry( columnReference );
      } else {
         return types.get( columnReference - 1 ).getIdentification();
      }
   }// End Method

}// End Class
