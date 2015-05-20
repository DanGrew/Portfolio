/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import model.data.SerializedSingleton;
import model.singleton.Singleton;
import model.singleton.SingletonImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.ClassParameterImpl;
import parameter.CommandParameter;
import parameter.SingletonReferenceParameterImpl;
import architecture.request.RequestSystem;

/**
 * Test to prove specific {@link CommandParameter}s function correctly.
 */
public class CommandParametersTest {
   
   private static final String TEST_SINGLETON = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   
   /** Interface for a testable {@link Singleton}.**/
   private static interface TestSingleton extends Singleton< TestSingleton >, SerializedSingleton< TestSingletonImpl >{}
   
   /** Implementation for a testable {@link Singleton}.**/
   private static class TestSingletonImpl extends SingletonImpl< TestSingleton > implements TestSingleton {

      /**
       * Constructs a new {@link TestSingletonImpl}.
       * @param identification the name.
       */
      public TestSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor

      @Override protected void writeSingleton( TestSingleton serializable ) {}
      @Override protected void readSingleton( TestSingleton serialized ) {}

      @Override public TestSingletonImpl unwrap() {
         return null;
      }

      @Override public void resolve() {}
      
   }// End Class
   
   /** Another class of testable {@link Singleton}. */
   private static class AnotherTestSingletonImpl extends TestSingletonImpl {

      /**
       * Constructs a new {@link AnotherTestSingletonImpl}.
       * @param identification the name.
       */
      public AnotherTestSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor
   }// End Class
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
   }// End Method

   /**
    * Method to test that a {@link ClassParameterImpl} uses {@link Class}es correctly.
    */
   @Test public void classParameterTest() {
      CommandParameter parameter = new ClassParameterImpl();
      Assert.assertTrue( parameter.partialMatches( "str" ) );
      
      Assert.assertTrue( parameter.completeMatches( "String" ) );
      Assert.assertTrue( parameter.completeMatches( "Number" ) );
      Assert.assertFalse( parameter.completeMatches( "dsjdn" ) );
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
