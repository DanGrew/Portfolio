/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.function.Predicate;

/**
 * The {@link DataManagementSystem} provides the interface defining access to the {@link Object}s
 * currently active in the system.
 */
public interface DataManagementSystem {
   
   /**
    * Method to store the given {@link Object} for access by other elements in the system.
    * @param object the {@link Object} to store.
    */
   public < T > void store( T object );
   
   /**
    * Method to retrieve the {@link Object} of the given {@link Class} matching
    * the given criteria.
    * @param clazz the {@link Class} the {@link Object} is.
    * @param criteria the method of matching the {@link Object}.
    * @return the matching {@link Object}.
    */
   public < T > T retrieve( Class< T > clazz, Predicate< T > criteria );

}// End Interface

