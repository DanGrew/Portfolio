/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

import java.util.Arrays;
import java.util.List;

import model.singleton.Singleton;
import architecture.request.RequestSystem;

import command.Command;

/**
 * {@link CommandParameterParseUtilities} is responsible for providing common methods of manipulting
 * input for {@link Command}s and {@link CommandParameter}s.
 */
public class CommandParameterParseUtilities {
   
   private static final String COMMAND_DELIMITER = " ";
   private static final String SPACE_REGEX = "\\s+";
   
   /**
    * Method to get the delimiter for {@link Command}s.
    * @return the {@link String} delimiter.
    */
   public static String delimiter(){
      return COMMAND_DELIMITER;
   }// End Method
   
   /**
    * Method to parse the parameter values for the given expression, using the given delimiter.
    * @param delimiter the delimiter for the expression.
    * @param expectedParameters the number of parameters expected.
    * @param expression the expression to parse.
    * @return an array of {@link String} values, equal to the number of expected parameters. If 
    * not parameter value specified "" is returned in the array.
    */
   public static final String[] parseParameters( String delimiter, int expectedParameters, String expression ) {
      if ( expression.startsWith( delimiter ) ) {
         expression = expression.replaceFirst( SPACE_REGEX, "" );
      }
      expression = expression.replaceAll( SPACE_REGEX, " " );
      String[] parts = expression.split( delimiter );
      if ( parts.length >= expectedParameters ) {
         String[] parameters = new String[ expectedParameters ];
         System.arraycopy( parts, 0, parameters, 0, expectedParameters );
         return parameters;
      } else if ( parts.length < expectedParameters ){
         String[] parameters = new String[ expectedParameters ];
         System.arraycopy( parts, 0, parameters, 0, parts.length );
         Arrays.fill( parameters, parts.length, expectedParameters, "" );
         return parameters;
      } else {
         String[] result = new String[ expectedParameters ];
         Arrays.fill( result, "" );
         return result;
      }
   }// End Method
   
   /**
    * Method to parse the parameter values from the given expression using the default delimiter.
    * @param expectedParameters the number of parameters expected.
    * @param expression the expression to parse.
    * @return an array of {@link String} values, equal to the number of expected parameters. If 
    * not parameter value specified "" is returned in the array.
    * 
    * @see CommandParameterParseUtilities#parseParameters(String, int, String).
    * @see CommandParameterParseUtilities#delimiter().
    */
   public static final String[] parseParameters( int expectedParameters, String expression ) {
      return parseParameters( delimiter(), expectedParameters, expression );
   }// End Method
   
   /**
    * Method to parse the parameter values from the given expression using the default delimiter
    * where only one parameter is expected.
    * @param expression the expression to parse.
    * @return an array of {@link String} values, equal to the number of expected parameters. If 
    * not parameter value specified "" is returned in the array.
    * 
    * @see CommandParameterParseUtilities#parseParameters(int, String).
    * @see CommandParameterParseUtilities#delimiter().
    */
   public static final String parseSingle( String expression ) {
      String[] result = parseParameters( 1, expression );
      if ( result == null ) {
         return null;
      } else {
         return result[ 0 ];
      }
   }// End Method
   
   /**
    * Method to parse all values up to the given value.
    * @param expression the expression to parse from.
    * @param searchFor the value to parse up to.
    * @param delimiter the delimiter for separating the values.
    * @return an array of parts in the expression up to the given.
    */
   public static final String[] parseUpTo( String expression, String searchFor, String delimiter ) {
      String[] parts = expression.split( searchFor );
      if ( parts.length == 0 ) {
         return new String[ 0 ];
      }
      String[] partsToReturn = parts[ 0 ].split( delimiter );
      for ( int i = 0; i < partsToReturn.length; i++ ) {
         partsToReturn[ i ] = partsToReturn[ i ].trim();
      }
      return partsToReturn;
   }// End Method
   
   /**
    * Method to reduce the given expression by removing the given parameters.
    * @param expression the expression containing the parameters.
    * @param parameters the parameters to remove.
    * @return the expression minus the given parameters.
    */
   public static final String reduce( String expression, String... parameters ) {
      for ( String parameter : parameters ) {
         expression = expression.replaceFirst( parameter, "" );
         if ( expression.startsWith( COMMAND_DELIMITER ) ) {
            expression = expression.replaceFirst( SPACE_REGEX, "" );
         }
      }
      return expression;
   }// End Method
   
   /**
    * Method to retrieve the {@link List} of {@link Singleton}s that partially match the given identification and {@link Class}.
    * @param name the partial name.
    * @param clazz the {@link Class} of the {@link Singleton} to match.
    * @return a {@link List} of matches.
    */
   public static < SingletonT extends Singleton > List< SingletonT > partialMatchesSingleton( String name, Class< SingletonT > clazz ) {
      List< SingletonT > matches = RequestSystem.retrieveAll( clazz, object -> {
        return object.getIdentification().startsWith( name ); 
      } );
      return matches;
   }// End Method
   
   /**
    * Method to retrieve the first {@link Singleton} that partially match the given identification and {@link Class}.
    * @param name the partial name.
    * @param clazz the {@link Class} of the {@link Singleton} to match.
    * @return the first match.
    */
   public static < SingletonT extends Singleton > SingletonT firstPartialMatchesSingleton( String name, Class< SingletonT > clazz ) {
      List< SingletonT > result = partialMatchesSingleton( name, clazz );
      if ( result.isEmpty() ) {
         return null;
      } else {
         return result.get( 0 );
      }
   }// End Method

}// End Class
