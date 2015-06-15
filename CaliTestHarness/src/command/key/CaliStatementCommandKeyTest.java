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
import annotation.CaliAnnotationSyntax;
import architecture.request.RequestSystem;
import command.Command;
import command.CommandKey;
import command.pattern.KeyVerifier;
import common.TestObjects.TestAnnotatedSingleton;
import common.TestObjects.TestAnnotatedSingletonImpl;
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;

/**
 * Test for the {@link CaliStatementKeyImpl}.
 */
public class CaliStatementCommandKeyTest implements KeyVerifier {

   private static final String TEST_SINGLETON_NAME = "TestSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   private static final String TEST_ANNOTATED_SINGLETON_NAME = "TestAnnotated";
   private static TestAnnotatedSingleton TEST_ANNOTATED_SINGLETON_OBJECT;
   private static final String TEST_ANOTHER_ANNOTATED_SINGLETON_NAME = "TestAnother";
   private static TestAnotherAnnotatedSingletonImpl TEST_ANOTHER_ANNOTATED_SINGLETON_OBJECT;
   private static CommandKey caliKey;
   
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
      
      caliKey = new CaliStatementKeyImpl();
   }// End Method   
   
   /**
    * Method to test {@link CaliStatementKeyImpl#partialMatches(String)} acceptance.
    */
   @Test @Override public void shouldPartialMatch() {
      Assert.assertTrue( caliKey.partialMatches( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertTrue( caliKey.partialMatches( "TestA" ) );
      Assert.assertTrue( caliKey.partialMatches( "TestAnnot" ) );
      Assert.assertTrue( caliKey.partialMatches( "TestAnot" ) );
      Assert.assertTrue( caliKey.partialMatches( "" ) );
      
      Assert.assertTrue( caliKey.partialMatches( TEST_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter() ) );
      Assert.assertTrue( caliKey.partialMatches( "TestA." ) );
      Assert.assertTrue( caliKey.partialMatches( "TestAnnot." ) );
      Assert.assertTrue( caliKey.partialMatches( "TestAnot." ) );
      Assert.assertTrue( caliKey.partialMatches( "." ) );
      Assert.assertTrue( caliKey.partialMatches( "" ) );
   }// End Method
   
   /**
    * Method to test {@link CaliStatementKeyImpl#partialMatches(String)} rejecting.
    */
   @Test @Override public void shouldNotPartialMatch() {
      Assert.assertFalse( caliKey.partialMatches( TEST_SINGLETON_NAME ) );
      Assert.assertFalse( caliKey.partialMatches( "AnythingElse" ) );
   }// End Method
   
   /**
    * {@link Command#completeMatches(String)} acceptance test.
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( caliKey.completeMatches( TEST_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter() ) );
      Assert.assertTrue( caliKey.completeMatches( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter() ) );
   }// End Method
   
   /**
    * {@link Command#completeMatches(String)} reject test.
    */
   @Test @Override public void shouldNotCompleteMatch() {
      Assert.assertFalse( caliKey.completeMatches( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertFalse( caliKey.completeMatches( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertFalse( caliKey.completeMatches( "anything" ) );
      Assert.assertFalse( caliKey.completeMatches( TEST_SINGLETON_NAME ) );
   }// End Method

   /**
    * {@link Command#autoComplete(String)} acceptance test.
    */
   @Test @Override public void shouldAutoComplete() {
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter(), caliKey.autoComplete( "TestAnno" ) );
      Assert.assertEquals( TEST_ANOTHER_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter(), caliKey.autoComplete( "TestAno" ) );
   }// End Method
   
   /**
    * {@link Command#autoComplete(String)} reject test.
    */
   @Test @Override public void shouldNotAutoComplete() {
      Assert.assertNull( caliKey.autoComplete( "TestA" ) );
      Assert.assertNull( caliKey.autoComplete( "" ) );
      Assert.assertNull( caliKey.autoComplete( "anything" ) );
      Assert.assertNull( caliKey.autoComplete( TEST_SINGLETON_NAME ) );
   }// End Method

   /**
    * {@link CommandKey#extractKeyExpression(String)} acceptance test.
    */
   @Test @Override public void shouldExtractKey(){
      Assert.assertEquals( "TestAnno", caliKey.extractKeyExpression( "TestAnno" ) );
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME, caliKey.extractKeyExpression( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( TEST_ANNOTATED_SINGLETON_NAME + CaliAnnotationSyntax.statementDelimiter(), caliKey.extractKeyExpression( TEST_ANNOTATED_SINGLETON_NAME + ". anything else" ) );
   }// End Method
   
   /**
    * {@link CommandKey#removeKeyFromInput(String)} acceptance test.
    */
   @Test @Override public void shouldRemoveKey() {
      Assert.assertEquals( "", caliKey.removeKeyFromInput( "TestAnno." ) );
      Assert.assertEquals( "", caliKey.removeKeyFromInput( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( "anything else", caliKey.removeKeyFromInput( TEST_ANNOTATED_SINGLETON_NAME + ". anything else") );
   }// End Method

}// End Class
