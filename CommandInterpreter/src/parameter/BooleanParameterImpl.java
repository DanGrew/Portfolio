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
 * The {@link BooleanParameterImpl} provides the implementation for the {@link CommandParameter} where
 * the only accepted input is a {@link Boolean} value.
 */
public class BooleanParameterImpl implements CommandParameter {

   private static final List< String > SUGGESTIONS;
   private static final String TRUE = Boolean.TRUE.toString();
   private static final String FALSE = Boolean.FALSE.toString();
   
   static {
      SUGGESTIONS = new ArrayList<>();
      SUGGESTIONS.add( TRUE );
      SUGGESTIONS.add( FALSE );
   }
   
   /**
    * {@inheritDoc}
    */
   @Override public List< String > getSuggestions( String expression ) {
      List< String > suggestions = new ArrayList<>( SUGGESTIONS );
      suggestions.removeIf( object -> {
         return !object.toUpperCase().startsWith( expression.toUpperCase() );
      } );
      return suggestions;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "boolean";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      return TRUE.toLowerCase().startsWith( parameter.toLowerCase() ) ||
             FALSE.toLowerCase().startsWith( parameter.toLowerCase() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      return TRUE.toLowerCase().equals( parameter.toLowerCase() ) ||
             FALSE.toLowerCase().equals( parameter.toLowerCase() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      if ( parameter.length() == 0 ) {
         return null;
      } else if ( parameter.toLowerCase().equals( TRUE.toLowerCase() ) ) {
         return true;
      } else if ( parameter.toLowerCase().equals( FALSE.toLowerCase() ) ) {
         return false;
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      if ( parameter.length() == 0 ) {
         return null;
      } else if ( TRUE.toLowerCase().startsWith( parameter.toLowerCase() ) ) {
         return TRUE;
      } else if ( FALSE.toLowerCase().startsWith( parameter.toLowerCase() ) ) {
         return FALSE;
      } else {
         return null;
      }
   }// End Method
   
}// End Class
