/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import annotation.CaliParserUtilities;
import architecture.request.RequestSystem;
import command.pattern.CommandParameterVerifier;
import common.TestObjects.TestAnnotatedSingleton;
import common.TestObjects.TestAnnotatedSingletonImpl;
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;
import parameter.CommandParameter;
import system.CaliSystem;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * Test for the {@link SingletonReferenceParameterImpl}.
 */
public class SingletonReferenceParameterTest implements CommandParameterVerifier {

   protected static final String TEST_SINGLETON_NAME = "TestSingleton";
   protected static TestSingleton TEST_SINGLETON_OBJECT;
   protected static final String TEST_ANNOTATED_SINGLETON_NAME = "TestAnnotated";
   protected static TestAnnotatedSingleton TEST_ANNOTATED_SINGLETON_OBJECT;
   protected static final String TEST_ANOTHER_ANNOTATED_SINGLETON_NAME = "TestAnother";
   protected static TestAnotherAnnotatedSingletonImpl TEST_ANOTHER_ANNOTATED_SINGLETON_OBJECT;
   protected CommandParameter parameter;
   
   /**
    * Method to set up the test, initialising some test objects.
    */
   @BeforeClass public static void setup(){
      RequestSystem.reset();
      
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON_NAME );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      TEST_ANNOTATED_SINGLETON_OBJECT = new TestAnnotatedSingletonImpl( TEST_ANNOTATED_SINGLETON_NAME );
      RequestSystem.store( TEST_ANNOTATED_SINGLETON_OBJECT, TestAnnotatedSingleton.class );
      TEST_ANOTHER_ANNOTATED_SINGLETON_OBJECT = new TestAnotherAnnotatedSingletonImpl( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME, 23.0 );
      RequestSystem.store( TEST_ANOTHER_ANNOTATED_SINGLETON_OBJECT, TestAnotherAnnotatedSingletonImpl.class );
      
      CaliSystem.register( TestAnnotatedSingletonImpl.class );
      CaliSystem.register( TestAnotherAnnotatedSingletonImpl.class );
   }// End Method   
   
   @Before public void initialise(){
      parameter = new SingletonReferenceParameterImpl();
   }
   
   /**
    * Method to test {@link SingletonReferenceParameterImpl#partialMatches(String)} acceptance.
    */
   @Test @Override public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertTrue( parameter.partialMatches( "TestA" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnnot" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnot" ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
      
      Assert.assertTrue( parameter.partialMatches( TEST_ANNOTATED_SINGLETON_NAME + CaliParserUtilities.statementDelimiter() ) );
      Assert.assertTrue( parameter.partialMatches( "TestA." ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnnot." ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnot." ) );
      Assert.assertTrue( parameter.partialMatches( "." ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
   }// End Method
   
   /**
    * Method to test {@link SingletonReferenceParameterImpl#partialMatches(String)} when further input is provided.
    */
   @Test public void shouldPartialMatchFurtherInput(){
      Assert.assertTrue( parameter.partialMatches( TEST_ANNOTATED_SINGLETON_NAME + " anything" ) );
      Assert.assertTrue( parameter.partialMatches( TEST_ANNOTATED_SINGLETON_NAME + ".anything" ) );
   }// End Method
   
   /**
    * Method to test {@link SingletonReferenceParameterImpl#partialMatches(String)} rejecting.
    */
   @Test @Override public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( TEST_SINGLETON_NAME ) );
      Assert.assertFalse( parameter.partialMatches( "AnythingElse" ) );
   }// End Method
   
   /**
    * {@link Command#completeMatches(String)} acceptance test.
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( TEST_ANNOTATED_SINGLETON_NAME + CaliParserUtilities.statementDelimiter() ) );
      Assert.assertTrue( parameter.completeMatches( TEST_ANNOTATED_SINGLETON_NAME + ".anything" ) );
      Assert.assertTrue( parameter.completeMatches( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME + CaliParserUtilities.statementDelimiter() ) );
      //. is not needed for completion.
      Assert.assertTrue( parameter.completeMatches( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertTrue( parameter.completeMatches( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME ) );
   }// End Method
   
   /**
    * {@link Command#completeMatches(String)} reject test.
    */
   @Test @Override public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
      Assert.assertFalse( parameter.completeMatches( TEST_SINGLETON_NAME ) );
   }// End Method

   /**
    * {@link Command#autoComplete(String)} acceptance test.
    */
   @Test @Override public void shouldAutoComplete() {
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME + CaliParserUtilities.statementDelimiter(), parameter.autoComplete( "TestAnno" ) );
      Assert.assertEquals( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME + CaliParserUtilities.statementDelimiter(), parameter.autoComplete( "TestAno" ) );
   }// End Method
   
   /**
    * {@link Command#autoComplete(String)} reject test.
    */
   @Test @Override public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "TestA" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( TEST_SINGLETON_NAME ) );
   }// End Method

   /**
    * {@link CommandKey#extractKeyExpression(String)} acceptance test.
    */
   @Test @Override public void shouldParseParameters(){
      Assert.assertEquals( "TestAnno", parameter.parseParameter( "TestAnno" ) );
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME, parameter.parseParameter( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME + CaliParserUtilities.statementDelimiter(), parameter.parseParameter( TEST_ANNOTATED_SINGLETON_NAME + ". anything else" ) );
   }// End Method
   
   /**
    * {@link CommandKey#removeKeyFromInput(String)} acceptance test.
    */
   @Test @Override public void shouldExtract() {
      Assert.assertEquals( "", parameter.extractInput( "TestAnno." ) );
      Assert.assertEquals( "", parameter.extractInput( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( "anything else", parameter.extractInput( TEST_ANNOTATED_SINGLETON_NAME + ". anything else") );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#parseObject(String)} acceptance test.
    * @throws SecurityException 
    * @throws NoSuchMethodException 
    */
   @Test @Override public void shouldParse() throws NoSuchMethodException, SecurityException {
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_OBJECT, parameter.parseObject( TEST_ANNOTATED_SINGLETON_NAME + "." ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#parseObject(String)} reject test.
    * @throws SecurityException 
    * @throws NoSuchMethodException 
    */
   @Test @Override public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
   }// End Method
   
   @Test public void shouldSuggest(){
      final List< String > expectedResult = parameter.getSuggestions( null );
      Assert.assertEquals( expectedResult, parameter.getSuggestions( "" ) );
      Assert.assertEquals( expectedResult, parameter.getSuggestions( "anything" ) );
      Assert.assertEquals( expectedResult, parameter.getSuggestions( TEST_ANNOTATED_SINGLETON_NAME ) );
   }

}// End Class
