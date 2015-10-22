package architecture.data;

import org.junit.Assert;
import org.junit.Test;

import architecture.request.RequestSystem;
import model.singleton.Singleton;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * Test for the {@link DataManagementSystem}'s storage mechanism.
 */
public class DmsStorageTest {

   /**
    * Test to prove that the {@link RequestSystem} verifies that the given {@link Class}es match
    * the {@link Object} given.
    */
   @Test public void shouldNotStoreUnassignableClassTypes() {
      TestSingletonImpl impl = new TestSingletonImpl( "anything" );
      
      RequestSystem.store( impl, String.class );
      
      Assert.assertTrue( RequestSystem.retrieveAll( TestSingleton.class ).contains( impl ) );
      Assert.assertTrue( RequestSystem.retrieveAll( TestSingletonImpl.class ).contains( impl ) );
      Assert.assertFalse( RequestSystem.retrieveAll( String.class ).contains( impl ) );
   }// End Method
   
   /**
    * Test to prove the {@link DataManagementSystem} stores {@link Singleton}s with associated 
    * interfaces.
    */
   @Test public void shouldStoreAllObjectTypes(){
      TestSingletonImpl impl = new TestSingletonImpl( "anything" );
      
      RequestSystem.store( impl );
      
      Assert.assertFalse( RequestSystem.retrieveAll( Singleton.class ).contains( impl ) );
      Assert.assertTrue( RequestSystem.retrieveAll( TestSingleton.class ).contains( impl ) );
      Assert.assertTrue( RequestSystem.retrieveAll( TestSingletonImpl.class ).contains( impl ) );
   }// End Method

}// End Class
