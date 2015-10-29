/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.data;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link DataManager} test.
 */
public class DataManagerTest {

   /**
    * {@link DataManager#removeAll(java.util.function.Predicate)} with return everything test.
    */
   @Test public void shouldStoreObjectsAndRetrieveAllDefaultMatch() {
      DataManager< String > manager = new DataManager<>();
      manager.store( "anything" );
      manager.store( "somethingElse" );
      manager.store( "nothing" );
      List< String > matches = manager.retrieveAll( object -> { return true; } );
      Assert.assertFalse( matches.isEmpty() );
      Assert.assertEquals( 3, matches.size() );
      Assert.assertEquals( "anything", matches.get( 0 ) );
      Assert.assertEquals( "somethingElse", matches.get( 1 ) );
      Assert.assertEquals( "nothing", matches.get( 2 ) );
   }//End Method
   
   /**
    * {@link DataManager#removeAll(java.util.function.Predicate)} with a custom matcher test.
    */
   @Test public void shouldStoreObjectsAndRetrieveAllCustomMatch() {
      DataManager< String > manager = new DataManager<>();
      manager.store( "anything" );
      manager.store( "somethingElse" );
      manager.store( "nothing" );
      List< String > matches = manager.retrieveAll( object -> { return object.contains( "s" ); } );
      Assert.assertFalse( matches.isEmpty() );
      Assert.assertEquals( 1, matches.size() );
      Assert.assertEquals( "somethingElse", matches.get( 0 ) );
   }//End Method
   
   /**
    * {@link DataManager#retrieve(java.util.function.Predicate)} test.
    */
   @Test public void shouldStoreAndRetrieveFirst(){
      DataManager< String > manager = new DataManager<>();
      manager.store( "anything" );
      manager.store( "somethingElse" );
      manager.store( "nothing" );
      String match = manager.retrieve( object -> { return object.contains( "ing" ); } );
      Assert.assertEquals( "anything", match );
   }//End Method
   
   /**
    * {@link DataManager#remove(Object)} test.
    */
   @Test public void shouldStoreObjectsAndRemoveSingle() {
      DataManager< String > manager = new DataManager<>();
      manager.store( "anything" );
      manager.store( "somethingElse" );
      manager.store( "nothing" );
      manager.remove( "somethingElse" );
      List< String > matches = manager.retrieveAll( object -> { return true; } );
      Assert.assertFalse( matches.isEmpty() );
      Assert.assertEquals( 2, matches.size() );
      Assert.assertEquals( "anything", matches.get( 0 ) );
      Assert.assertEquals( "nothing", matches.get( 1 ) );
   }//End Method
   
   /**
    * {@link DataManager#removeAll(java.util.function.Predicate)} test.
    */
   @Test public void shouldStoreObjectsAndRemoveAllMatches() {
      DataManager< String > manager = new DataManager<>();
      manager.store( "anything" );
      manager.store( "somethingElse" );
      manager.store( "nothing" );
      manager.removeAll( object -> { return object.endsWith( "ing" ); } );
      List< String > matches = manager.retrieveAll( object -> { return true; }  );
      Assert.assertFalse( matches.isEmpty() );
      Assert.assertEquals( 1, matches.size() );
      Assert.assertEquals( "somethingElse", matches.get( 0 ) );
   }//End Method

}//End Class
