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
}// End Class
