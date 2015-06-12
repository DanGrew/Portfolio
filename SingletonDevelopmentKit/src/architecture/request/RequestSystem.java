/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.request;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import model.singleton.Singleton;
import architecture.data.DataManagementSystem;
import architecture.event.system.ManagementSystem;
import architecture.utility.IdentificationMatcher;

/**
 * The {@link RequestSystem} provides an interface to the management systems that provide access
 * to {@link Object}s being used in the system.
 */
public class RequestSystem extends ManagementSystem {
   
   /**
    * {@link DataManagementSystem#store(Object, Class...)}.
    * TODO verify class types.
    */
   public static < T > void store( T object, Class< ? >... classes ){
      dataSystem().store( object, classes );
   }// End Method
   
   /**
    * Method to determine whether the {@link RequestSystem} has the {@link Object} matching the {@link Class}
    * and {@link Predicate} stored.
    * @param clazz the {@link Class} of the {@link Object}.
    * @param criteria the {@link Predicate} matching the {@link Object}.
    * @return true if the {@link Object} is present, false otherwise.
    */
   public static < T > boolean contains( Class< T > clazz, Predicate< T > criteria ){
      return dataSystem().retrieveAll( clazz, criteria ).size() > 0;
   }// End Method
   
   /**
    * Method to determine whether the {@link RequestSystem} has the {@link Object} matching the identification.
    * @param clazz the {@link Class} of the {@link Object}.
    * @param identification the {@link String} identification of the {@link Singleton}.
    * @return true if the {@link Object} is present, false otherwise.
    */
   public static < T extends Singleton > boolean contains( Class< T > clazz, final String identification ){
      return dataSystem().retrieveAll( clazz, test -> {
         return test.getIdentification().equals( identification );
      } ).size() > 0;
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
   public static < T extends Singleton > T retrieve( Class< T > clazz, String identification ){
      return dataSystem().retrieve( clazz, new IdentificationMatcher< T >( identification ) );
   }// End Method
   
   /**
    * {@link DataManagementSystem#retrieveAll(Class, Predicate)}.
    */
   public static < T > List< T > retrieveAll( Class< T > clazz, Predicate< T > criteria ){
      return dataSystem().retrieveAll( clazz, criteria );
   }// End Method
   
   /**
    * {@link DataManagementSystem#retrieveAll(Class, Predicate)}.
    */
   public static < T > List< T > retrieveAll( Class< T > clazz ){
      return dataSystem().retrieveAll( clazz, null );
   }// End Method
   
   /**
    * {@link DataManagementSystem#retrieveAll(Class, Predicate, Predicate)}.
    */
   public static < MinimumParentT > List< MinimumParentT > retrieveAll( 
            Class< MinimumParentT > minimumParent, 
            Predicate< Class< ? > > classMatcher, 
            Predicate< MinimumParentT > objectMatcher 
   ) {
      return dataSystem().retrieveAll( 
               minimumParent, 
               classMatcher, 
               objectMatcher 
      );
   }// End Method
   
   /**
    * Method to retrieve all {@link Singleton}s matching the given class matcher and singleton name.
    * @param minimumParent the minimum parent type of the {@link Singleton}s.
    * @param classMatcher the {@link Predicate} to match the {@link Class} of the {@link Singleton}.
    * @param singletonName the exact name to match.
    * @param <MinimumParentT> the minimum type of the {@link Singleton} to return as, and match as.
    * @return a {@link List} of minimum parent types matching.
    */
   public static < MinimumParentT extends Singleton > List< MinimumParentT > retrieveAllSingletons( 
            Class< MinimumParentT > minimumParent, 
            Predicate< Class< ? > > classMatcher, 
            String singletonName 
   ) {
      return dataSystem().retrieveAll( 
               minimumParent, 
               classMatcher, 
               object -> { return object.getIdentification().equals( singletonName ); }
      );
   }// End Method
   
   /**
    * {@link DataManagementSystem#retrieveAll(Class, Predicate, Predicate)} with third parameter null.
    */
   public static < MinimumParentT extends Singleton > List< MinimumParentT > retrieveAllMatchingClass( 
            Class< MinimumParentT > minimumParent, 
            Predicate< Class< ? > > classMatcher
   ) {
      return dataSystem().retrieveAll( 
               minimumParent, 
               classMatcher, 
               null
      );
   }// End Method
   
   /**
    * {@link DataManagementSystem#process(Class, Predicate, Consumer)}.
    */
   public static < T > void process( Class< T > clazz, Predicate< T > criteria, Consumer< T > process ){
      dataSystem().process( clazz, criteria, process );
   }// End Method
   
   /**
    * Method to {@link DataManagementSystem#process(Class, Predicate, Consumer)} matching the {@link String}
    * identification given.
    * @param clazz the {@link Class} of the {@link Singleton}.
    * @param identification the identification of the {@link Singleton}.
    * @param process the {@link Consumer} to process on the matching {@link Object}s.
    */
   public static < T extends Singleton > void process( Class< T > clazz, String identification, Consumer< T > process ){
      dataSystem().process( clazz, new IdentificationMatcher< T >( identification ), process );
   }// End Method
   
}// End Class
