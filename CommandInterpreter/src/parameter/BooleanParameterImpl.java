/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

/**
 * The {@link BooleanParameterImpl} provides the implementation for the {@link CommandParameter} where
 * the only accepted input is a {@link Boolean} value.
 */
public class BooleanParameterImpl implements CommandParameter {

   private static final String TRUE = Boolean.TRUE.toString();
   private static final String FALSE = Boolean.FALSE.toString();
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      return TRUE.toLowerCase().startsWith( expression.toLowerCase() ) ||
             FALSE.toLowerCase().startsWith( expression.toLowerCase() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      return TRUE.toLowerCase().equals( expression.toLowerCase() ) ||
             FALSE.toLowerCase().equals( expression.toLowerCase() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      if ( expression.toLowerCase().equals( TRUE.toLowerCase() ) ) {
         return true;
      } else if ( expression.toLowerCase().equals( FALSE.toLowerCase() ) ) {
         return false;
      } else {
         return null;
      }
   }// End Method

}// End Class
