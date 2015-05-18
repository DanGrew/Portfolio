/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
      for ( Class< ? > clazz : classes ) {
         manager = getDataManager( clazz );
         manager.store( object );   
      }
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

}// End Class
