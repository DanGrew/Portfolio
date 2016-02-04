/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import parameter.CommandParameter;
import parameter.ReferenceObjectParameterImpl;
import test.model.TestObjects.TestAnotherSingletonImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * Test for the {@link ReferenceObjectParameterImpl}.
 */
public class ReferenceObjectParameterTest {
   
   private static final String TEST_SINGLETON = "TestInstance";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static final String TEST_SINGLETON_2 = "TestAnother";
   private static TestSingleton TEST_SINGLETON_OBJECT_2;
   private static CommandParameter parameter;
   
   /**
    * Method to initialise the test {@link Singleton}s to use.
    */
   @BeforeClass public static void storageInitialisation(){
      RequestSystem.reset();
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      TEST_SINGLETON_OBJECT_2 = new TestSingletonImpl( TEST_SINGLETON_2 );
      RequestSystem.store( TEST_SINGLETON_OBJECT_2, TestSingleton.class );
      parameter = new ReferenceObjectParameterImpl( TestSingletonImpl.class, TestAnotherSingletonImpl.class );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      //type acceptance
      Assert.assertTrue( parameter.partialMatches( "Te" ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
      Assert.assertTrue( parameter.partialMatches( TestSingletonImpl.class.getSimpleName() ) );
      
      //identification acceptance
      Assert.assertTrue( parameter.partialMatches( "TestSingletonImpl TestIn" ) );
      Assert.assertTrue( parameter.partialMatches( TestSingletonImpl.class.getSimpleName() + " " + TEST_SINGLETON ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
      Assert.assertFalse( parameter.partialMatches( TestSingletonImpl.class.getSimpleName() + " anything" ) );
      Assert.assertFalse( parameter.partialMatches( "Test Test" ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( TestSingletonImpl.class.getSimpleName() + " " + TEST_SINGLETON ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "" ) );
      Assert.assertFalse( parameter.completeMatches( " " ) );
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
      Assert.assertFalse( parameter.completeMatches( TestSingletonImpl.class.getSimpleName() + " " ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      Assert.assertEquals( "", parameter.extractInput( TestSingletonImpl.class.getSimpleName() + " " + TEST_SINGLETON ) );
      final String testRemainder = "anything else";
      Assert.assertEquals( 
               testRemainder, 
               parameter.extractInput( TestSingletonImpl.class.getSimpleName() + " " + TEST_SINGLETON + " " + testRemainder ) 
      );
      Assert.assertEquals( "", parameter.extractInput( "anything " ) );
      Assert.assertEquals( "", parameter.extractInput( "anything" ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#parseObject(String)} acceptance test.
    */
   @Test public void shouldParse() {
      Assert.assertEquals( TEST_SINGLETON_OBJECT, parameter.parseObject( TestSingletonImpl.class.getSimpleName() + " " + TEST_SINGLETON ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
      Assert.assertNull( parameter.parseObject( TestSingletonImpl.class.getSimpleName() ) );
   }// End Method
   
   /**
    * {@link ReferenceObjectParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      Assert.assertEquals( TestSingletonImpl.class.getSimpleName(), parameter.autoComplete( "Te" ) );
      Assert.assertEquals( 
               TestSingletonImpl.class.getSimpleName() + " " + TEST_SINGLETON, 
               parameter.autoComplete( TestSingletonImpl.class.getSimpleName() + " TestI" ) 
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
   
   /**
    * {@link ReferenceObjectParameterImpl#getSuggestions(String)} test.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( 
               Arrays.asList( TestSingletonImpl.class.getSimpleName(), TestAnotherSingletonImpl.class.getSimpleName() ),
               parameter.getSuggestions( "" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TestSingletonImpl.class.getSimpleName() ),
               parameter.getSuggestions( "TestSin" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TestSingletonImpl.class.getSimpleName(), TestAnotherSingletonImpl.class.getSimpleName() ),
               parameter.getSuggestions( "Test" ) 
      );
      Assert.assertEquals( 
               Arrays.asList(),
               parameter.getSuggestions( "anythingElse" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON, TEST_SINGLETON_2 ),
               parameter.getSuggestions( "TestSingletonImpl Test" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON, TEST_SINGLETON_2 ),
               parameter.getSuggestions( "TestSingletonImpl" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON ),
               parameter.getSuggestions( "TestSingletonImpl TestIn" ) 
      );
   }// End Method
}// End Class
