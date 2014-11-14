/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.request;

import java.util.function.Predicate;

import model.singleton.Singleton;
import architecture.data.DataManagementSystem;
import architecture.event.system.System;

/**
 * The {@link RequestSystem} provides an interface to the management systems that provide access
 * to {@link Object}s being used in the system.
 */
public class RequestSystem extends System {
   
   /**
    * {@link DataManagementSystem#store(Object)}.
    */
   public static < T > void store( T object ){
      dataSystem().store( object);
   }// End Method
   
   /**
    * {@link DataManagementSystem#retrieve(Class, Predicate)}.
    */
   public static < T > T retrieve( Class< T > clazz, Predicate< T > criteria ){
      return dataSystem().retrieve( clazz, criteria );
   }// End Method
   
   /**
    * Method to retrieve the {@link Object} from the {@link DataManagementSystem} that matches
    * the identification provided.
    * @param clazz the {@link Class} of the {@link Object} to match.
    * @param identification the identification of the {@link Singleton}.
    * @return the matching {@link Singleton}.
    */
   public static < T extends Singleton< ? > > T retrieve( Class< T > clazz, String identification ){
      return dataSystem().retrieve( clazz, test -> {
         return test.getIdentification().equals( identification );
      } );
   }// End Method
   
}// End Class
