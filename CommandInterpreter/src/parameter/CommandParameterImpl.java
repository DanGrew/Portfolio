/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

/**
 * The {@link CommandParameterImpl} provides the implementation of the {@link CommandParameter}
 * interface.
 */
public class CommandParameterImpl implements CommandParameter{

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      return true;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      return true;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      return expression;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      return null;
   }// End Method

}// End Class
