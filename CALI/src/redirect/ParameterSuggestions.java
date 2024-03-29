/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package redirect;

import java.lang.reflect.Executable;
import java.util.List;

import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;

/**
 * {@link ParameterSuggestions} is responsible for identifying suggestions for input for parameters
 * of {@link Constructor}s and {@link Method}s.
 */
public class ParameterSuggestions {

   /**
    * Method to identify all redirected parameters for the given input value.
    * @param type the {@link Class} of the parameter expected.
    * @param value the {@link Object} value.
    * @return a {@link List} of {@link Object}s that match the input.
    */
   public static List< String > identifyRedirectionsFor( Class< ? > type, Object value ) {
      ClassParameterType parameterType = ClassParameterTypes.flexibleValueOf( type );
      if ( parameterType != null ) {
         return parameterType.suggest( value );
      } else {
         return null;
      }
   }// End Method
   
   /**
    * Method to identify the exact match of the value if it exists for the given input value.
    * @param type the {@link Class} of the parameter expected.
    * @param value the {@link Object} value.
    * @return the exact match found, or null.
    */
   public static Object identifyExactMatchFor( Class< ? > type, Object value ) {
      if ( value == null ) {
         return null;
      }
      ClassParameterType parameterType = ClassParameterTypes.flexibleValueOf( type );
      if ( parameterType != null ) {
         return parameterType.deserialize( value.toString() );
      } else {
         return null;
      }
   }// End Method

   /**
    * Method to determine whether the given parameters are acceptable for the given {@link Executable}.
    * @param executable the {@link Executable} that accepts parameters.
    * @param parameters the parameters.
    * @return true if they match, false otherwise.
    */
   public static boolean matchesSignature( Executable executable, Object... parameters ) {
      Class< ? >[] parameterClasses = executable.getParameterTypes();
      if ( parameterClasses.length < parameters.length ) {
         return false;
      }
      
      for ( int i = 0; i < parameters.length; i++ ) {
         List< String > suggestions = identifyRedirectionsFor( parameterClasses[ i ], parameters[ i ] );
         if ( suggestions == null ) {
            return false;
         }
         if ( suggestions.isEmpty() ) {
            return false;
         }
      }
      return true;
   }// End Method

   /**
    * Method to determine whether the given parameters are acceptable for the given {@link Executable}, where
    * the parameters match exactly.
    * @param executable the {@link Executable} that accepts parameters.
    * @param parameters the parameters.
    * @return true if they match, false otherwise.
    */
   public static boolean matchesExactSignature( Executable executable, Object... parameters ) {
      Class< ? >[] parameterClasses = executable.getParameterTypes();
      if ( parameterClasses.length != parameters.length ) {
         return false;
      }
      
      for ( int i = 0; i < parameters.length; i++ ) {
         Object match = identifyExactMatchFor( parameterClasses[ i ], parameters[ i ] );
         if ( match == null ) {
            return false;
         }
      }
      return true;
   }// End Method

}// End Class
