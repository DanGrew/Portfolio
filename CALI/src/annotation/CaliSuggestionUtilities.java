/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;

import parameter.CommandParameterParseUtilities;
import parameter.wrapper.CommandParameters;
import redirect.ParameterSuggestions;

/**
 * The {@link CaliSuggestionUtilities} provides common methods for identifying suggestions
 * for {@link CommandParameters}.
 */
public class CaliSuggestionUtilities {

   /**
    * Method to construct the suggestions for all parameters of the associated {@link Executable}s
    * given the number of parameters parsed.
    * @param items the items to construct suggestions for.
    * @param the number of parameters parsed.
    * @return a {@link List} of {@link String} suggestions.
    */
   public static List< String > suggestAllParameters( List< ? extends Executable > items, int numberOfParameters ) {
      List< String > suggestions = new ArrayList<>();
      for ( Executable executable : items ) {
         StringBuffer buffer = new StringBuffer();
         for ( int i = numberOfParameters; i < executable.getParameterCount(); i++ ) {
            Class< ? > parameter = executable.getParameterTypes()[ i ];
            buffer.append( CaliParserUtilities.getDescriptionOfParameter( parameter ) );
            buffer.append( CaliParserUtilities.parameterDelimiter() );
            buffer.append( CommandParameterParseUtilities.delimiter() );
         }
         if ( buffer.length() > 0 ) {
            buffer.setLength( buffer.length() - 2 );
            buffer.append( CommandParameterParseUtilities.delimiter() );
         }
         buffer.append( CaliParserUtilities.close() );
         suggestions.add( buffer.toString() );
      }
      return suggestions;
   }// End Method
   
   /**
    * Method to auto correct for all parameters of the associated {@link Executable}
    * given the parameters parsed.
    * @param item the item to construct the auto correct for.
    * @param parameters the parameters parsed.
    * @return a {@link String} auto correct result.
    */
   public static String autoCorrectAllParameters( Executable item, Object[] parameters ) {
      boolean complete = true;
      StringBuffer buffer = new StringBuffer( CaliParserUtilities.open() + CommandParameterParseUtilities.delimiter() );
      for ( int i = 0; i < parameters.length; i++ ) {
         Class< ? > parameter = item.getParameterTypes()[ i ];
         List< String > suggestions = ParameterSuggestions.identifyRedirectionsFor( parameter, parameters[ i ] );
         if ( suggestions.size() != 1 ) {
            complete = false;
         }
         String commonPrefix = CaliParserUtilities.lowestCommonSubstring( suggestions );
         buffer.append( commonPrefix );
         buffer.append( CaliParserUtilities.parameterDelimiter() );
         buffer.append( CommandParameterParseUtilities.delimiter() );
      }
      if ( buffer.length() > 0 ) {
         buffer.setLength( buffer.length() - 2 );
      }
      if ( complete ) {
         buffer.append( CommandParameterParseUtilities.delimiter() );
         buffer.append( CaliParserUtilities.close() );
      }
      return buffer.toString();
   }// End Method
}// End Class
