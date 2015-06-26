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

import parameter.CommandParameter;
import parameter.SingletonReferenceParameterImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

/**
 * Test for the {@link SingletonReferenceParameterImpl}.
 */
public class SingletonReferenceParameterTest {
   
   private static final String TEST_SINGLETON = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static CommandParameter parameter;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      RequestSystem.reset();
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      parameter = new SingletonReferenceParameterImpl( TestSingleton.class );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( "Te" ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
      Assert.assertTrue( parameter.partialMatches( TEST_SINGLETON + " anything" ) );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
      //Only accepts reference as separate parameters.
      Assert.assertFalse( parameter.partialMatches( TEST_SINGLETON + ".anything" ) );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( TEST_SINGLETON ) );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "" ) );
      Assert.assertFalse( parameter.completeMatches( " " ) );
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      final String testRemainder = "anything";
      Assert.assertEquals( testRemainder, parameter.extractInput( TEST_SINGLETON + " " + testRemainder ) );
      Assert.assertEquals( "", parameter.extractInput( "anything " ) );
      Assert.assertEquals( "", parameter.extractInput( "anything" ) );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#parseObject(String)} acceptance test.
    */
   @Test public void shouldParse() {
      Assert.assertEquals( TEST_SINGLETON_OBJECT, parameter.parseObject( TEST_SINGLETON ) );
      Assert.assertEquals( TEST_SINGLETON_OBJECT, parameter.parseObject( TEST_SINGLETON + "   " ) );
      Assert.assertEquals( TEST_SINGLETON_OBJECT, parameter.parseObject( "   " + TEST_SINGLETON ) );
      Assert.assertEquals( TEST_SINGLETON_OBJECT, parameter.parseObject( TEST_SINGLETON + " something else" ) );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      Assert.assertEquals( TEST_SINGLETON, parameter.autoComplete( "Te" ) );
   }// End Method
   
   /**
    * {@link SingletonReferenceParameterImpl#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "tes" ) );
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( " " ) );
   }// End Method
}// End Class
