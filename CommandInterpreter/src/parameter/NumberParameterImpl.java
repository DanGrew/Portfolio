/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

/**
 * The {@link NumberParameterImpl} provides the implementation for the {@link CommandParameter} where
 * the only accepted input is a {@link Number} value.
 */
public class NumberParameterImpl implements CommandParameter {

   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "number";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      try {
         Double.valueOf( expression );
         return true;
      } catch ( NumberFormatException exception ) {
         return false;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      return partialMatches( expression );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      try {
         Double value = Double.valueOf( expression );
         return value;
      } catch ( NumberFormatException exception ) {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      return null;
   }// End Method

}// End Class
