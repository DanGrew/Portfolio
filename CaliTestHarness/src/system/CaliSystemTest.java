/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package system;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;
import common.TestObjects.TestAnnotatedSingleton;
import common.TestObjects.TestAnnotatedSingletonImpl;

/**
 * Test for the {@link CaliSystem}.
 */
public class CaliSystemTest {
   
   private static final String TEST_SINGLETON_NAME = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static final String TEST_ANNOTATED_SINGLETON_NAME = "TestAnnotated";
   private static TestAnnotatedSingleton TEST_ANNOTATED_SINGLETON_OBJECT;
   
   /**
    * Method to set up the test, initialising some test objects.
    */
   @BeforeClass public static void setup(){
      RequestSystem.reset();
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON_NAME );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      TEST_ANNOTATED_SINGLETON_OBJECT = new TestAnnotatedSingletonImpl( TEST_ANNOTATED_SINGLETON_NAME );
      RequestSystem.store( TEST_ANNOTATED_SINGLETON_OBJECT, TestAnnotatedSingleton.class );
   }// End Method

   /**
    * Method to assert that names that do not match are identified as such.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertTrue( CaliSystem.completeMatch( TEST_SINGLETON_NAME ).isEmpty() );
   }// End Method
   
   /**
    * Method to test that names that completely match identify the appropriate objects.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertEquals( 
               Arrays.asList( TEST_ANNOTATED_SINGLETON_OBJECT ), 
               CaliSystem.completeMatch( TEST_ANNOTATED_SINGLETON_NAME ) 
      );
   }// End Method
   
   /**
    * Method to assert that names that do not partially match are identified as such.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertTrue( CaliSystem.partialMatch( TEST_SINGLETON_NAME ).isEmpty() );
      Assert.assertTrue( CaliSystem.partialMatch( "anything" ).isEmpty() );
   }// End Method
   
   /**
    * Method to test that names that partially match identify the appropriate objects.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertEquals( Arrays.asList( TEST_ANNOTATED_SINGLETON_OBJECT ), CaliSystem.partialMatch( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( Arrays.asList( TEST_ANNOTATED_SINGLETON_OBJECT ), CaliSystem.partialMatch( "TestAn" ) );
   }// End Method

}// End Class
