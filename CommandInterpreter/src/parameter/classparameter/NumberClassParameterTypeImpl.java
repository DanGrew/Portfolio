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
         return objectToNumber( object );
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
   @Override public List< String > suggest( Object object ) {
      Double value = serialize( object );
      if ( value == null ) {
         return new ArrayList< String >();
      }
      return Arrays.asList( value.toString() );
   }// End Method
   
   /**
    * Method to convert the given {@link Object} into a {@link Double}, if possible.
    * @param object the {@link Object} to convert.
    * @return the {@link Double} if converted successfully, null otherwise.
    */
   public static Double objectToNumber( Object object ) {
      if ( object == null ) {
         return null;
      }
      try { 
         return Double.valueOf( object.toString() );
      } catch ( NumberFormatException exception ) {
         return null;
      }
   }// End Method
   
}// End Class
