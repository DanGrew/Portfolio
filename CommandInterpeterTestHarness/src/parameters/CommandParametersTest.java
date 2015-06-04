/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.BooleanParameterImpl;
import parameter.ClassParameterImpl;
import parameter.CommandParameter;
import parameter.SingletonReferenceParameterImpl;
import test.model.TestObjects.AnotherTestSingletonImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

/**
 * Test to prove specific {@link CommandParameter}s function correctly.
 */
public class CommandParametersTest {
   
   private static final String TEST_SINGLETON = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
   }// End Method

   /**
    * Method to test that a {@link BooleanParameterImpl} uses {@link Class}es correctly.
    */
   @Test public void booleanParameterTest() {
      CommandParameter parameter = new ClassParameterImpl();
      Assert.assertTrue( parameter.partialMatches( "Tr" ) );
      
      Assert.assertTrue( parameter.completeMatches( "True" ) );
      Assert.assertTrue( parameter.completeMatches( "true" ) );
      Assert.assertFalse( parameter.completeMatches( "alse" ) );
      Assert.assertFalse( parameter.completeMatches( "Fjalse" ) );
   }// End Method
   
   /**
    * Method to test that a {@link ClassParameterImpl} uses {@link Class}es correctly.
    */
   @Test public void classParameterTest() {
      CommandParameter parameter = new ClassParameterImpl();
      Assert.assertTrue( parameter.partialMatches( "Str" ) );
      
      Assert.assertTrue( parameter.completeMatches( "String" ) );
      Assert.assertTrue( parameter.completeMatches( "Number" ) );
      Assert.assertFalse( parameter.completeMatches( "dsjdn" ) );
   }// End Method
   
   /**
    * Method to test that a {@link NumberParameterImpl} uses {@link Class}es correctly.
    */
   @Test public void numberParameterTest() {
      CommandParameter parameter = new ClassParameterImpl();
      Assert.assertTrue( parameter.partialMatches( "12345" ) );
      
      Assert.assertTrue( parameter.completeMatches( "123" ) );
      Assert.assertTrue( parameter.completeMatches( "54.56" ) );
      Assert.assertFalse( parameter.completeMatches( "false" ) );
      Assert.assertFalse( parameter.completeMatches( "something" ) );
   }// End Method

   /**
    * Method to test that a {@link ClassParameterImpl} uses {@link Class}es correctly.
    */
   @Test public void singletonReferenceParameterMatchingTest() {
      CommandParameter parameter = new SingletonReferenceParameterImpl( TestSingleton.class );
      Assert.assertTrue( parameter.partialMatches( "TestSin" ) );
      Assert.assertFalse( parameter.partialMatches( "testSin" ) );
      
      Assert.assertTrue( parameter.completeMatches( TEST_SINGLETON ) );
      Assert.assertFalse( parameter.completeMatches( " " ) );
      Assert.assertFalse( parameter.completeMatches( "TestS" ) );
      Assert.assertFalse( parameter.completeMatches( "testSingleton" ) );
   }// End Method
   
   /**
    * Method to test that a {@link ClassParameterImpl} uses {@link Class}es correctly.
    */
   @Test public void singletonReferenceParameterNoMatchingTest() {
      CommandParameter parameter = new SingletonReferenceParameterImpl( AnotherTestSingletonImpl.class );
      Assert.assertFalse( parameter.partialMatches( "TestSin" ) );
      Assert.assertFalse( parameter.partialMatches( "testSin" ) );
      
      Assert.assertFalse( parameter.completeMatches( TEST_SINGLETON ) );
      Assert.assertFalse( parameter.completeMatches( " " ) );
      Assert.assertFalse( parameter.completeMatches( "TestS" ) );
      Assert.assertFalse( parameter.completeMatches( "testSingleton" ) );
   }// End Method
   
}// End Class
