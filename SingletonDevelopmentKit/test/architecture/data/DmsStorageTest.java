package architecture.data;

import static org.junit.Assert.fail;
import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.Test;

import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

/**
 * Test for the {@link DataManagementSystem}'s storage mechanism.
 */
public class DmsStorageTest {

   @Test public void shouldNotStoreUnassignableClassTypes() {
      fail( "Not yet implemented!" );
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
