/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The {@link DataManager} is responsible for managing {@link Object}s of the associated 
 * type.
 */
public class DataManager< T > {

   /** {@link Set} of {@link Object}s being managed.**/
   private Set< T > data;
   
   /**
    * Constructs a new {@link DataManager}.
    */
   public DataManager(){
      data = new LinkedHashSet< T >();
   }// End Constructor
   
   /**
    * Method to store the given {@link Object}.
    * @param object the {@link Object} to store.
    */
   public void store( T object ){
      data.add( object );
   }// End Method
   
   /**
    * Method to retrieve the {@link Object} that matches the given {@link Predicate}.
    * @param matcher the {@link Predicate} defining the match criteria.
    * @return the matching {@link Object}.
    */
   public T retrieve( Predicate< T > matcher ){
      Optional< T > firstFound = data.stream().filter( matcher ).findFirst();
      if ( firstFound.isPresent() ){
         return firstFound.get();
      } else {
         return null;
      }
   }// End Method
   
   /**
    * Method to retrieve all {@link Object}s that match the {@link Predicate} criteria.
    * @param matcher the {@link Predicate} defining the criteria for matching.
    * @return a {@link List} of matching {@link Object}s.
    */
   public List< T > retrieveAll( Predicate< T > matcher ){
      if ( matcher == null ) {
         return new ArrayList<>( data );
      } else {
         return data.stream().filter( matcher ).collect( Collectors.toList() );
      }
   }// End Method
   
   /**
    * Method to remove the given {@link Object}.
    * @param object the {@link Object} to remove.
    */
   public void remove( T object ){
      data.remove( object );
   }// End Method
   
   /**
    * Method to remove all {@link Object}s that match the {@link Predicate}.
    * @param matcher the {@link Predicate} defining the criteria for matching.
    */
   public void removeAll( Predicate< T > matcher ){
      data.removeIf( matcher );
   }// End Method
   
}// End Class
