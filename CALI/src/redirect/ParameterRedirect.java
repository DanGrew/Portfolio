/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package redirect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import architecture.request.RequestSystem;
import model.singleton.Singleton;

/**
 * The {@link ParameterRedirect} is responsible for redirecting {@link Method} calls from
 * and input that provides {@link String}s to the actual types of parameter.
 */
public class ParameterRedirect {

   /**
    * Method to invoke the given {@link Method}, having replaced the parameters with the 
    * correct types.
    * @param object the object to invoke the {@link Method} on.
    * @param method the {@link Method} to invoke.
    * @param parameters the parameters to convert to actual types.
    * @return the result of the invocation.
    */
   public Object invoke( Object object, Method method, Object... parameters ) throws IllegalAccessException, 
                                                                                     IllegalArgumentException, 
                                                                                     InvocationTargetException 
   {
      Object[] redirectedParameters = new Object[ parameters.length ];
      Class< ? >[] expectedParameterTypes = method.getParameterTypes();
      for ( int i = 0; i < expectedParameterTypes.length; i++ ) {
         redirectedParameters[ i ] = redirectParameter( parameters[ i ], expectedParameterTypes[ i ] );
      }
      
      return method.invoke( object, redirectedParameters );
   }// End Method
   
   /**
    * Method to redirect a single parameter based on the expected type.
    * @param object the object parameter.
    * @param expectedType the {@link Class} of the type expected.
    * @return the parameter as the current type.
    */
   private Object redirectParameter( Object object, Class< ? > expectedType ) {
      if ( expectedType.equals( String.class ) ) {
         return redirectString( object );
      } else if ( expectedType.equals( Double.class ) ) {
         return redirectDouble( object );
      } else if ( expectedType.equals( Singleton.class ) ) {
         return redirectSingleton( object );
      } else {
         return object;
      }
   }// End Method

   /**
    * Method to redirect to a {@link String} parameter.
    * @param string the object to redirect.
    * @return the object in the correct format.
    */
   private String redirectString( Object string ) {
      return string.toString();
   }// End Method
   
   /**
    * Method to redirect to a {@link Double} parameter.
    * @param string the object to redirect.
    * @return the object in the correct format.
    */
   private Double redirectDouble( Object string ) {
      try {
         Double value = Double.valueOf( string.toString() );
         return value;
      } catch ( NumberFormatException exception ) {
         return null;
      }
   }// End Method
   
   /**
    * Method to redirect to a {@link Singleton} parameter.
    * @param string the object to redirect.
    * @return the object in the correct format.
    */
   private Object redirectSingleton( Object object ) {
      return RequestSystem.retrieve( Singleton.class, object.toString() );
   }// End Method

}// End Class
