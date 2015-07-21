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
 * Extension of {@link ClassParameterType} for {@link String}s.
 */
public class StringClassParameterTypeImpl extends ClassParameterTypeImpl {

   /**
    * Constructs a new {@link StringClassParameterTypeImpl}.
    */
   public StringClassParameterTypeImpl() {
      super( String.class );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public Serializable serialize( Object object ) {
      if ( object == null ) {
         return null;
      } else {
         return object.toString();
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object deserialize( Serializable object ) {
      return object;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< Object > suggest( Object object ) {
      if ( object == null ) {
         return new ArrayList< Object >();
      }
      return Arrays.asList( object.toString() );
   }// End Method

}// End Class
