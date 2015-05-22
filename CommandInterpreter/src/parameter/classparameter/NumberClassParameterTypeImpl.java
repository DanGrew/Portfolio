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
 * {@link Number} extension for the {@link ClassParameterType}.
 */
public class NumberClassParameterTypeImpl extends ClassParameterTypeImpl< Number > {

   /**
    * Constructs a new {@link NumberClassParameterTypeImpl}.
    */
   public NumberClassParameterTypeImpl() {
      super( Number.class );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public Serializable serialize( Object object ) {
      return ( Serializable )object;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object deserialize( Serializable object ) {
      return Double.valueOf( object.toString() );
   }// End Method

}// End Class
