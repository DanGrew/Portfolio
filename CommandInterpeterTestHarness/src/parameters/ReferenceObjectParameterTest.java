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
import parameter.ReferenceObjectParameterImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

/**
 * Test for the {@link ReferenceObjectParameterImpl}.
 */
public class ReferenceObjectParameterTest {
   
   private static final String TEST_SINGLETON = "TestInstance";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static CommandParameter parameter;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      parameter = new ReferenceObjectParameterImpl( TestSingleton.class );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      //type acceptance
      Assert.assertTrue( parameter.partialMatches( "Te" ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
      Assert.assertTrue( parameter.partialMatches( TestSingleton.class.getSimpleName() ) );
      
      //identification acceptance
      Assert.assertTrue( parameter.partialMatches( "TestSingleton TestIn" ) );
      Assert.assertTrue( parameter.partialMatches( TestSingleton.class.getSimpleName() + " " + TEST_SINGLETON ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
      Assert.assertFalse( parameter.partialMatches( TestSingleton.class.getSimpleName() + " anything" ) );
      Assert.assertFalse( parameter.partialMatches( "Test Test" ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( TestSingleton.class.getSimpleName() + " " + TEST_SINGLETON ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "" ) );
      Assert.assertFalse( parameter.completeMatches( " " ) );
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
      Assert.assertFalse( parameter.completeMatches( TestSingleton.class.getSimpleName() + " " ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      Assert.assertEquals( "", parameter.extractInput( TestSingleton.class.getSimpleName() + " " + TEST_SINGLETON ) );
      final String testRemainder = "anything else";
      Assert.assertEquals( 
               testRemainder, 
               parameter.extractInput( TestSingleton.class.getSimpleName() + " " + TEST_SINGLETON + " " + testRemainder ) 
      );
      Assert.assertEquals( "", parameter.extractInput( "anything " ) );
      Assert.assertEquals( "", parameter.extractInput( "anything" ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#parseObject(String)} acceptance test.
    */
   @Test public void shouldParse() {
      Assert.assertEquals( TEST_SINGLETON_OBJECT, parameter.parseObject( TestSingleton.class.getSimpleName() + " " + TEST_SINGLETON ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
      Assert.assertNull( parameter.parseObject( TestSingleton.class.getSimpleName() ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      Assert.assertEquals( TestSingleton.class.getSimpleName(), parameter.autoComplete( "Te" ) );
      Assert.assertEquals( 
               TestSingleton.class.getSimpleName() + " " + TEST_SINGLETON, 
               parameter.autoComplete( TestSingleton.class.getSimpleName() + " TestI" ) 
      );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "tes" ) );
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( " " ) );
   }// End Method
}// End Class
