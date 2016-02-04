/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import model.singleton.Singleton;

/**
 * The {@link CaliDataManagement} is responsible for providing access to the {@link RequestSystem}, specifically for
 * {@link Cali} annotated objects.
 */
public interface CaliDataManagement {

   /**
    * Method to retrieve all {@link Singleton}s associated with the given {@link Singleton#getIdentification()}.
    * @param testSingletonName the {@link Singleton#getIdentification()}.
    * @return all {@link Singleton}s that partially match the name.
    */
   public List< Singleton > partialMatch( String testSingletonName );

   /**
    * Method to retrieve all {@link Singleton}s associated with the given {@link Singleton#getIdentification()}.
    * @param testSingletonName the {@link Singleton#getIdentification()}.
    * @return all {@link Singleton}s that completely match the name.
    */
   public List< Singleton > completeMatch( String testSingletonName );

   /**
    * Method to match the given {@link Class}es to the parameter {@link Class} types.
    * @param objectClasses the possible {@link Class}es to match.
    * @param classes the {@link Class}es of the parameters.
    * @return the {@link Constructor} if found.
    */
   public Constructor< ? > matchConstructor( List< Class< ? > > objectClasses, Class< ? >[] classes );
   
   /**
    * Method to match the given {@link Class}es to the parameter {@link Class} types.
    * @param objectClasses the possible {@link Class}es to match.
    * @param expectedParameterNumber the number of parameters in the {@link Constructor} expected.
    * @return the {@link Constructor} if found.
    */
   public Constructor< ? > matchConstructor( List< Class< ? > > objectClasses, int expectedParameterNumber );
   
   /**
    * Method to match the partial {@link Method} name to the given {@link Class} of {@link Object}.
    * @param clazz the {@link Class} to reflect on.
    * @param methodName the partial {@link Method} name to match.
    * @return all {@link Method}s that match the name.
    */
   public List< Method > matchMethodName( Class< ? > clazz, String methodName );

   /**
    * Method to match the single {@link Method} given by the partial {@link Method} name and parameters.
    * @param clazz the {@link Class} to reflect on.
    * @param methodPartialName the partial {@link Method} name.
    * @param parameterTypes the {@link Class}es of parameters expected. Must be exact.
    * @return the matching {@link Method} or null if not unique.
    */
   public Method matchMethodSignature( Class< ? > clazz, String methodPartialName, Class< ? >... parameterTypes );
   
   /**
    * Method to match the single {@link Method} given by the partial {@link Method} name and parameters.
    * @param clazz the {@link Class} to reflect on.
    * @param methodPartialName the partial {@link Method} name.
    * @param parameterCountExpected the number of parameters expected.
    * @return the matching {@link Method} or null if not unique.
    */
   public Method matchMethodSignature( Class< ? > clazz, String methodPartialName, int parameterCountExpected );

   /**
    * Method to find all {@link Constructor}s for the given {@link Class} that have the given number of
    * parameters.
    * @param clazz the {@link Class} of the object.
    * @param numberOfParametersEntered the number of parameters entered, null indicates any number.
    * @return a {@link List} of {@link Constructor}s that have at least the given number of parameters.
    */
   public List< Constructor< ? > > findConstructors( Class< ? > clazz, Integer numberOfParametersEntered );

      /**
    * Method to find all {@link Method}s for the given {@link Class} that have the given number of
    * parameters.
    * @param clazz the {@link Class} of the object.
    * @param partialName the partial name of the {@link Method} to match.
    * @param numberOfParametersEntered the number of parameters entered, null indicates any number.
    * @return a {@link List} of {@link Constructor}s that have at least the given number of parameters.
    */
   public List< Method > findMethods( Class< ? > clazz, String partialName, Integer numberOfParametersEntered );

}// End Interface
