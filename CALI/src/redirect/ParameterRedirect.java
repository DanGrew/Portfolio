/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package redirect;

import java.lang.reflect.Constructor;
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
      Class< ? >[] expectedParameterTypes = method.getParameterTypes();
      Object[] redirectedParameters = redirectParameters( expectedParameterTypes, parameters );
      return method.invoke( object, redirectedParameters );
   }// End Method
   
   /**
    * Method to invoke the given {@link Constructor}, having replaced the parameters with the 
    * correct types.
    * @param constructor the {@link Constructor} to invoke.
    * @param parameters the parameters to convert to actual types.
    * @return the {@link Object} constructed using the {@link Constructor}.
    */
   public Object construct( Constructor< ? > constructor, Object... parameters ) throws InstantiationException, 
                                                                                        IllegalAccessException, 
                                                                                        IllegalArgumentException, 
                                                                                        InvocationTargetException 
   {
      Class< ? >[] expectedParameterTypes = constructor.getParameterTypes();
      Object[] redirectedParameters = redirectParameters( expectedParameterTypes, parameters );
      return constructor.newInstance( redirectedParameters );
   }// End Method
   
   /**
    * Method to redirect the parameters for the given expected types.
    * @param parameterTypes the expected {@link Class}es of parameter.
    * @param parameters the {@link Object} parameters to redirect.
    * @return the array of redirected parameters.
    */
   private Object[] redirectParameters( Class< ? >[] parameterTypes, Object[] parameters ) {
      Object[] redirectedParameters = new Object[ parameters.length ];
      for ( int i = 0; i < parameterTypes.length; i++ ) {
         redirectedParameters[ i ] = redirectParameter( parameters[ i ], parameterTypes[ i ] );
      }
      return redirectedParameters;
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
      } else if ( Singleton.class.isAssignableFrom( expectedType ) ) {
         @SuppressWarnings("unchecked") //Safe because isAssignableFrom checks. 
         Class< ? extends Singleton > singletonClass = ( Class< ? extends Singleton > )expectedType;
         return redirectSingleton( object, singletonClass );
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
    * @param object the object to redirect.
    * @param singletonClass the {@link Class} of the {@link Singleton} to use to look up.
    * @return the object in the correct format.
    */
   private Object redirectSingleton( Object object, Class< ? extends Singleton > singletonClass ) {
      Object singleton = RequestSystem.retrieve( singletonClass, object.toString() );
      if ( singleton == null ) {
         throw new NullPointerException( "No object defined for " + object.toString() + "." );
      }
      return singleton;
   }// End Method

}// End Class
