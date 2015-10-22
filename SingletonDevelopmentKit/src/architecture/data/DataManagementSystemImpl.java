/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Predicate;

import architecture.event.EventSystem;

/**
 * The {@link DataManagementSystemImpl} provides the implementation to the {@link DataManagementSystem}
 * interface.
 */
public class DataManagementSystemImpl implements DataManagementSystem {

   /** {@link Map} of {@link Object} {@link Class} to the {@link DataManager} managing it. **/
   private Map< Class< ? >, DataManager< ? > > dataManagers;
   
   /**
    * Constructs a new {@link DataManagementSystemImpl}.
    */
   public DataManagementSystemImpl() {
      dataManagers = new HashMap< Class< ? >, DataManager< ? > >();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public < T > void store( T object, Class< ? >... classes ) {
      DataManager< T > manager = getDataManager( object.getClass() );
      manager.store( object );
      Class< ? >[] interfaces = object.getClass().getInterfaces();
      for ( Class< ? > interfaceClass : interfaces ) {
         manager = getDataManager( interfaceClass );
         manager.store( object );   
      }
      for ( Class< ? > clazz : classes ) {
         if ( clazz.isAssignableFrom( object.getClass() ) ) {
            manager = getDataManager( clazz );
            manager.store( object );   
         }
      }
      EventSystem.raiseEvent( 
               DataManagementSystem.Events.ObjectStored, 
               new SingletonStoredSource( object, classes ) 
      );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public < T > T retrieve( Class< T > clazz, Predicate< T > criteria ) {
      DataManager< T > manager = getDataManager( clazz );
      return manager.retrieve( criteria );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public < T > List< T > retrieveAll( Class< T > clazz, Predicate< T > criteria ){
      DataManager< T > manager = getDataManager( clazz );
      return manager.retrieveAll( criteria );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public < MinimumParentT > List< MinimumParentT > retrieveAll( 
            Class< MinimumParentT > minimumParent,
            Predicate< Class< ? >> classMatcher, 
            Predicate< MinimumParentT > objectMatcher 
   ) {
      Set< MinimumParentT > matches = new LinkedHashSet<>();
      Collection< DataManager< MinimumParentT > > relevantManagers = getAllDataManagersAssignedFrom( minimumParent, classMatcher );
      for ( DataManager< MinimumParentT > manager : relevantManagers ) {
         matches.addAll( manager.retrieveAll( objectMatcher ) );
      }
      return new ArrayList<>( matches );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public < T > void process( Class< T > clazz, Predicate< T > criteria, Consumer< T > process ){
      DataManager< T > manager = getDataManager( clazz );
      List< T > object = manager.retrieveAll( criteria );
      object.forEach( process );
   }// End Method
   
   /**
    * Method to get the {@link DataManager} associated with the given {@link Class}.
    * @param clazz the {@link Class} of the {@link DataManager}.
    * @return the matching {@link DataManager}. One is created if it doesn't exist.
    */
   private < T > DataManager< T > getDataManager( Class< ? > clazz ){
      @SuppressWarnings("unchecked") 
      DataManager< T > manager = ( DataManager< T > ) dataManagers.get( clazz );
      if ( manager == null ){
         manager = new DataManager< T >();
         dataManagers.put( clazz, manager );
      }
      return manager;
   }// End Method
   
   /**
    * Method to get all {@link DataManager}s that match the given parent {@link Class} and the {@link Predicate} for
    * the matching.
    * @param parent the {@link Class} of the parent.
    * @param classMatcher the {@link Predicate} to match the {@link Class} associated with the {@link DataManager}.
    * @return a {@link Collection} of {@link DataManager}s appropriate for the parent type and {@link Predicate}.
    */
   private < MinimumParentT > Collection< DataManager< MinimumParentT > > getAllDataManagersAssignedFrom( 
            Class< MinimumParentT > parent,
            Predicate< Class< ? > > classMatcher 
   ) {
      Set< DataManager< MinimumParentT  > > matchingManagers = new LinkedHashSet<>();
      for ( Entry< Class< ? >, DataManager< ? > > entry : dataManagers.entrySet() ) {
         if ( parent.isAssignableFrom( entry.getKey() ) ) {
            
            @SuppressWarnings("unchecked") //Verified by assign method directly above. 
            DataManager< MinimumParentT > manager = ( DataManager< MinimumParentT > )entry.getValue();
            
            if ( classMatcher == null ) {
               matchingManagers.add( manager );
            } else if ( classMatcher.test( entry.getKey() ) ){
               matchingManagers.add( manager );
            }
         }
      }
      return matchingManagers;
   }// End Method

}// End Class
