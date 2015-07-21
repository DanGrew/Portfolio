/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.classparameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link Number} extension for the {@link ClassParameterType}.
 */
public class NumberClassParameterTypeImpl extends ClassParameterTypeImpl {

   /**
    * Constructs a new {@link NumberClassParameterTypeImpl}.
    */
   public NumberClassParameterTypeImpl() {
      super( Number.class );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public Double serialize( Object object ) {
      if ( object != null ) {
         try { 
            return Double.valueOf( object.toString() );
         } catch ( NumberFormatException exception ) {
            return null;
         }
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Double deserialize( Serializable object ) {
      return serialize( object );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< Object > suggest( Object object ) {
      Double value = serialize( object );
      if ( value == null ) {
         return new ArrayList< Object >();
      }
      return Arrays.asList( value );
   }// End Method

}// End Class
