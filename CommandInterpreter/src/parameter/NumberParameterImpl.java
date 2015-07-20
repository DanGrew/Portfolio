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
 * The {@link NumberParameterImpl} provides the implementation for the {@link CommandParameter} where
 * the only accepted input is a {@link Number} value.
 */
public class NumberParameterImpl implements CommandParameter {

   public static final String ANY_NUMBER = "<number>";
   private static final List< String > SUGGESTIONS;
   
   static {
      SUGGESTIONS = new ArrayList<>();
      SUGGESTIONS.add( ANY_NUMBER );
   }
   
   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "number";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< String > getSuggestions( String expression ) {
      return SUGGESTIONS;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      if ( parameter.isEmpty() ) {
         return true;
      }
      try {
         Double.valueOf( parameter );
         return true;
      } catch ( NumberFormatException exception ) {
         return false;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      if ( expression.isEmpty() ) {
         return false;
      }
      return partialMatches( expression );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      try {
         Double value = Double.valueOf( parameter );
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
   
   /**
    * {@inheritDoc}
    */
   @Override public String extractInput( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      return CommandParameterParseUtilities.reduce( expression, parameter );
   }// End Method

}// End Class
