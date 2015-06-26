/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import system.CaliSystem;

import command.pattern.CommandVerifier;
import common.TestObjects.TestAnnotatedSingletonImpl;
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;

/**
 * Test for the {@link NewCommandImpl}.
 */
public class NewCommandTest implements CommandVerifier {

   private static Command< Object > command;
   
   /**
    * Method to set up the test.
    */
   @BeforeClass public static void setup(){
      command = new NewCommandImpl();
      
      CaliSystem.register( TestAnnotatedSingletonImpl.class );
      CaliSystem.register( TestAnotherAnnotatedSingletonImpl.class );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void basicConstructionTest() {
      Assert.assertNotNull( command );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldPartialMatch() {
      Assert.assertTrue( command.partialMatches( "new TestAnotherAnnotatedSingletonImpl( testName, 567.06 )" ) );
      Assert.assertTrue( command.partialMatches( "new TestAnnotatedSingletonImpl( testName )" ) );
      Assert.assertTrue( command.partialMatches( "new TestAnnotatedSingletonImpl( test" ) );
      Assert.assertTrue( command.partialMatches( "new TestAnnotated" ) );
      Assert.assertTrue( command.partialMatches( "ne" ) );
      Assert.assertTrue( command.partialMatches( "" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotPartialMatch() {
      Assert.assertFalse( command.partialMatches( "anything" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( command.completeMatches( "new TestAnnotatedSingletonImpl( testName )" ) );
      Assert.assertTrue( command.completeMatches( "new TestAnotherAnnotatedSingletonImpl( testName, 495.987 )" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotCompleteMatch() {
      Assert.assertFalse( command.completeMatches( "new TestAnnotatedSingletonImpl( testNa" ) );
      Assert.assertFalse( command.completeMatches( "new TestAnnotatedSingleto" ) );
      Assert.assertFalse( command.completeMatches( "new " ) );
      Assert.assertFalse( command.completeMatches( "n" ) );
      Assert.assertFalse( command.completeMatches( "" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldAutoComplete() {
      Assert.assertEquals( "new TestAnnotatedSingletonImpl( testName", command.autoComplete( "new TestAnnotatedSingletonImpl( testName" ) );
      Assert.assertEquals( "new TestAnnotatedSingletonImpl(", command.autoComplete( "new TestAnnotatedSingletonImpl(" ) );
      Assert.assertEquals( "new TestAnnotatedSingletonImpl(", command.autoComplete( "new TestAnnotated" ) );
      Assert.assertEquals( "new", command.autoComplete( "n" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotAutoComplete() {
      Assert.assertEquals( "anything", command.autoComplete( "anything" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldExecuteAndParameterize() {
      Assert.assertEquals( new TestAnnotatedSingletonImpl( "testName" ), command.execute( "new TestAnnotatedSingletonImpl( testName )" ).getResult() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotExecuteAndParameterize() {
      Assert.assertNull( command.execute( "new TestAnnotatedSingletonImpl( " ) );
   }// End Method
   
   @Test public void shouldRedirectConstructor(){
      Assert.fail();
   }

}// End Class
