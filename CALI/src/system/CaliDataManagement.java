/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.lang.reflect.Constructor;
import java.util.List;

import annotation.Cali;
import architecture.request.RequestSystem;
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

}// End Interface
