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

import parameter.CommandParameter;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import annotation.CaliAnnotationSyntax;
import architecture.request.RequestSystem;

import command.Command;
import command.parameter.ConstructorParameterImpl;
import command.pattern.CommandParameterVerifier;
import common.TestObjects.TestAnnotatedSingleton;
import common.TestObjects.TestAnnotatedSingletonImpl;
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;

/**
 * Test for the {@link CaliStatementParameterImpl}.
 */
public class CaliStatementCommandKeyTest implements CommandParameterVerifier {

   private static final String TEST_SINGLETON_NAME = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static final String TEST_ANNOTATED_SINGLETON_NAME = "TestAnnotated";
   private static TestAnnotatedSingleton TEST_ANNOTATED_SINGLETON_OBJECT;
   private static final String TEST_ANOTHER_ANNOTATED_SINGLETON_NAME = "TestAnother";
   private static TestAnotherAnnotatedSingletonImpl TEST_ANOTHER_ANNOTATED_SINGLETON_OBJECT;
   private static CommandParameter command;
   
   /**
    * Method to set up the test, initialising some test objects.
    */
   @BeforeClass public static void setup(){
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON_NAME );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
      TEST_ANNOTATED_SINGLETON_OBJECT = new TestAnnotatedSingletonImpl( TEST_ANNOTATED_SINGLETON_NAME );
      RequestSystem.store( TEST_ANNOTATED_SINGLETON_OBJECT, TestAnnotatedSingleton.class );
      TEST_ANOTHER_ANNOTATED_SINGLETON_OBJECT = new TestAnotherAnnotatedSingletonImpl( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME, "anything" );
      RequestSystem.store( TEST_ANOTHER_ANNOTATED_SINGLETON_OBJECT, TestAnotherAnnotatedSingletonImpl.class );
      
      command = new CaliStatementParameterImpl();
   }// End Method   
   
   /**
    * Method to test {@link CaliStatementParameterImpl#partialMatches(String)} acceptance.
    */
   @Test @Override public void shouldPartialMatch() {
      Assert.assertTrue( command.partialMatches( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertTrue( command.partialMatches( "TestA" ) );
      Assert.assertTrue( command.partialMatches( "TestAnnot" ) );
      Assert.assertTrue( command.partialMatches( "TestAnot" ) );
      Assert.assertTrue( command.partialMatches( "" ) );
      
      Assert.assertTrue( command.partialMatches( TEST_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter() ) );
      Assert.assertTrue( command.partialMatches( "TestA." ) );
      Assert.assertTrue( command.partialMatches( "TestAnnot." ) );
      Assert.assertTrue( command.partialMatches( "TestAnot." ) );
      Assert.assertTrue( command.partialMatches( "." ) );
      Assert.assertTrue( command.partialMatches( "" ) );
   }// End Method
   
   /**
    * Method to test {@link CaliStatementParameterImpl#partialMatches(String)} rejecting.
    */
   @Test @Override public void shouldNotPartialMatch() {
      Assert.assertFalse( command.partialMatches( TEST_SINGLETON_NAME ) );
      Assert.assertFalse( command.partialMatches( "AnythingElse" ) );
   }// End Method
   
   /**
    * {@link Command#completeMatches(String)} acceptance test.
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( command.completeMatches( TEST_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter() ) );
      Assert.assertTrue( command.completeMatches( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter() ) );
   }// End Method
   
   /**
    * {@link Command#completeMatches(String)} reject test.
    */
   @Test @Override public void shouldNotCompleteMatch() {
      Assert.assertFalse( command.completeMatches( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertFalse( command.completeMatches( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertFalse( command.completeMatches( "anything" ) );
      Assert.assertFalse( command.completeMatches( TEST_SINGLETON_NAME ) );
   }// End Method

   /**
    * {@link Command#autoComplete(String)} acceptance test.
    */
   @Test @Override public void shouldAutoComplete() {
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter(), command.autoComplete( "TestAnno" ) );
      Assert.assertEquals( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter(), command.autoComplete( "TestAno" ) );
   }// End Method
   
   /**
    * {@link Command#autoComplete(String)} reject test.
    */
   @Test @Override public void shouldNotAutoComplete() {
      Assert.assertNull( command.autoComplete( "TestA" ) );
      Assert.assertNull( command.autoComplete( "" ) );
      Assert.assertNull( command.autoComplete( "anything" ) );
      Assert.assertNull( command.autoComplete( TEST_SINGLETON_NAME ) );
   }// End Method

   /**
    * {@link CommandKey#extractKeyExpression(String)} acceptance test.
    */
   @Test @Override public void shouldParseParameters(){
      Assert.assertEquals( "TestAnno", command.parseParameter( "TestAnno" ) );
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME, command.parseParameter( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter(), command.parseParameter( TEST_ANNOTATED_SINGLETON_NAME + ". anything else" ) );
   }// End Method
   
   /**
    * {@link CommandKey#removeKeyFromInput(String)} acceptance test.
    */
   @Test @Override public void shouldExtract() {
      Assert.assertEquals( "", command.extractInput( "TestAnno." ) );
      Assert.assertEquals( "", command.extractInput( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( "anything else", command.extractInput( TEST_ANNOTATED_SINGLETON_NAME + ". anything else") );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#parseObject(String)} acceptance test.
    * @throws SecurityException 
    * @throws NoSuchMethodException 
    */
   @Test @Override public void shouldParse() throws NoSuchMethodException, SecurityException {
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_OBJECT, command.parseObject( TEST_ANNOTATED_SINGLETON_NAME + "." ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#parseObject(String)} reject test.
    * @throws SecurityException 
    * @throws NoSuchMethodException 
    */
   @Test @Override public void shouldNotParse() {
      Assert.assertNull( command.parseObject( "anything" ) );
   }// End Method

}// End Class
