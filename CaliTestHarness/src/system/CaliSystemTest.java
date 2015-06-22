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
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;

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
      
      CaliSystem.register( TestAnnotatedSingletonImpl.class );
      CaliSystem.register( TestAnotherAnnotatedSingletonImpl.class );
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
      Assert.assertTrue( CaliSystem.partialMatchSingletons( TEST_SINGLETON_NAME ).isEmpty() );
      Assert.assertTrue( CaliSystem.partialMatchSingletons( "anything" ).isEmpty() );
   }// End Method
   
   /**
    * Method to test that names that partially match identify the appropriate objects.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertEquals( Arrays.asList( TEST_ANNOTATED_SINGLETON_OBJECT ), CaliSystem.partialMatchSingletons( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( Arrays.asList( TEST_ANNOTATED_SINGLETON_OBJECT ), CaliSystem.partialMatchSingletons( "TestAn" ) );
   }// End Method
   
   /**
    * {@link CaliSystem#matchConstructor(String, Class...)} acceptance test.
    */
   @Test public void shouldMatchConstructor() throws NoSuchMethodException, SecurityException {
      Assert.assertEquals( 
               TestAnnotatedSingletonImpl.class.getConstructor( String.class ),
               CaliSystem.matchConstructor( "TestAnnotatedSingletonImpl", String.class ) 
      );
      Assert.assertEquals( 
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, String.class ), 
               CaliSystem.matchConstructor( "TestAnotherAnnotatedSingletonImpl", String.class, String.class ) 
      );
   }// End Method
   
   /**
    * {@link CaliSystem#matchConstructor(String, Class...)} reject test.
    */
   @Test public void shouldNotMatchConstructor() {
      Assert.assertNull( 
               CaliSystem.matchConstructor( "TestAnnotatedSingletonImpl", String.class, String.class ) 
      );
      Assert.assertNull( 
               CaliSystem.matchConstructor( "TestAnotherAnnotatedSingletonImpl", String.class, String.class, String.class ) 
      );
   }// End Method
   
   /**
    * {@link CaliSystem#partialMatchMethodName(Class, String)} acceptance test.
    */
   @Test public void shouldPartialMatchMethodName() throws NoSuchMethodException, SecurityException{
      Assert.assertEquals(
               Arrays.asList( TestAnnotatedSingletonImpl.class.getMethod( "testCaliMethod", String.class ) ),
               CaliSystem.partialMatchMethodName( TestAnnotatedSingletonImpl.class, "testCaliMethod" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TestAnnotatedSingletonImpl.class.getMethod( "testCaliMethod", String.class ) ),
               CaliSystem.partialMatchMethodName( TestAnnotatedSingletonImpl.class, "testCaliMe" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( 
                        TestAnnotatedSingletonImpl.class.getMethod( "overloaded", String.class ),
                        TestAnnotatedSingletonImpl.class.getMethod( "overloaded", String.class, String.class )
               ),
               CaliSystem.partialMatchMethodName( TestAnnotatedSingletonImpl.class, "overloaded" ) 
      );
   }// End Method
   
   /**
    * {@link CaliSystem#partialMatchMethodName(Class, String)} reject test.
    */
   @Test public void shouldNotPartialMatchMethodName(){
      Assert.assertTrue( CaliSystem.partialMatchMethodName( TestSingletonImpl.class, "testCaliMethod" ).isEmpty() );
      Assert.assertTrue( CaliSystem.partialMatchMethodName( TestAnnotatedSingletonImpl.class, "nonCaliMethod" ).isEmpty() );
   }// End Method

   /**
    * {@link CaliSystem#matchMethodSignature(Class, String, Class...)} acceptance test.
    */
   @Test public void shouldMatchMethodSignature() throws NoSuchMethodException, SecurityException{
      Assert.assertEquals( 
               TestAnnotatedSingletonImpl.class.getMethod( "testCaliMethod", String.class ),
               CaliSystem.matchMethodSignature( TestAnnotatedSingletonImpl.class, "testCaliMethod", String.class ) 
      );
      Assert.assertEquals( 
               TestAnnotatedSingletonImpl.class.getMethod( "testCaliMethod", String.class ),
               CaliSystem.matchMethodSignature( TestAnnotatedSingletonImpl.class, "testCaliMeth", String.class ) 
      );
   }// End Method
   
   /**
    * {@link CaliSystem#matchMethodSignature(Class, String, Class...)} reject test.
    */
   @Test public void shouldNotMatchMethodSignature(){
      Assert.assertNull( 
               CaliSystem.matchMethodSignature( TestAnnotatedSingletonImpl.class, "anything", String.class ) 
      );
      Assert.assertNull( CaliSystem.matchMethodSignature( TestSingletonImpl.class, "testCaliMethod" ) );
      Assert.assertNull( CaliSystem.matchMethodSignature( TestAnnotatedSingletonImpl.class, "nonCaliMethod" ) );
      Assert.assertNull( CaliSystem.matchMethodSignature( TestAnnotatedSingletonImpl.class, "nonCaliMethod", String.class ) );
   }// End Method

}// End Class
