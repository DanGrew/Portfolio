/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.classparameter;

import java.io.Serializable;

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
      return object.toString();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object deserialize( Serializable object ) {
      return object;
   }// End Method

}// End Class
