/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.key;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;
import command.CommandKey;
import common.TestObjects.TestAnnotatedSingleton;
import common.TestObjects.TestAnnotatedSingletonImpl;

/**
 * Test for the {@link CaliCommandKeyImpl}.
 */
public class CaliKeyTest {

   private static final String TEST_SINGLETON_NAME = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static final String TEST_ANNOTATED_SINGLETON_NAME = "TestAnnotated";
   private static TestAnnotatedSingleton TEST_ANNOTATED_SINGLETON_OBJECT;
   private static CommandKey caliKey;
   
   /**
    * Method to set up the test, initialising some test objects.
    */
   @BeforeClass public static void setup(){
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON_NAME );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      TEST_ANNOTATED_SINGLETON_OBJECT = new TestAnnotatedSingletonImpl( TEST_ANNOTATED_SINGLETON_NAME );
      RequestSystem.store( TEST_ANNOTATED_SINGLETON_OBJECT, TestAnnotatedSingleton.class );
      
      caliKey = new CaliCommandKeyImpl();
   }// End Method   
   
   /**
    * Method to test {@link CaliCommandKeyImpl#partialMatches(String)} acceptance.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( caliKey.partialMatches( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertTrue( caliKey.partialMatches( "TestA" ) );
      Assert.assertTrue( caliKey.partialMatches( "" ) );
   }// End Method
   
   /**
    * Method to test {@link CaliCommandKeyImpl#partialMatches(String)} rejecting.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( caliKey.partialMatches( TEST_SINGLETON_NAME ) );
      Assert.assertFalse( caliKey.partialMatches( "AnythingElse" ) );
      Assert.assertFalse( caliKey.partialMatches( null ) );
   }// End Method

}// End Class
