/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * The {@link DataManagementSystem} provides the interface defining access to the {@link Object}s
 * currently active in the system.
 */
public interface DataManagementSystem {
   
   /**
    * Method to store the given {@link Object} for access by other elements in the system.
    * @param object the {@link Object} to store.
    * @param classes array of {@link Class}es that the object should be stored as. This could
    * include supertypes or interfaces.
    */
   public < T > void store( T object, Class< ? >... classes );
   
   /**
    * Method to retrieve the {@link Object} of the given {@link Class} matching
    * the given criteria.
    * @param clazz the {@link Class} the {@link Object} is.
    * @param criteria the method of matching the {@link Object}.
    * @return the matching {@link Object}.
    */
   public < T > T retrieve( Class< T > clazz, Predicate< T > criteria );
   
   /**
    * Method to retrieve the all of the {@link Object}s of the given {@link Class} matching
    * the given criteria.
    * @param clazz the {@link Class} the {@link Object} is.
    * @param criteria the method of matching the {@link Object}.
    * @return the matching {@link List} of {@link Object}s.
    */
   public < T > List< T > retrieveAll( Class< T > clazz, Predicate< T > criteria );
   
   /**
    * Method to {@link #retrieveAll(Class, Predicate)} {@link Object}s matching the {@link Predicate}
    * and process them using the {@link Consumer}.
    * @param clazz the {@link Class} of the {@link Object} to retrieve.
    * @param criteria the {@link Predicate} to match the {@link Object}s.
    * @param process the {@link Consumer} to process.
    */
   public < T > void process( Class< T > clazz, Predicate< T > criteria, Consumer< T > process );

}// End Interface

