/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link CommandParameterImpl} provides the implementation of the {@link CommandParameter}
 * interface.
 */
public class CommandParameterImpl implements CommandParameter{
   
   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "string";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< String > getSuggestions( String expression ) {
      return new ArrayList< String >();
   }// End Method
   
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
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      return parameter;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      return null;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String extractInput( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      return CommandParameterParseUtilities.reduce( expression, parameter );
   }// End Method

}// End Class
