/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import command.key.CaliNewCommandKeyImpl;
import command.pattern.CommandVerifier;
import common.TestObjects.TestAnnotatedSingletonImpl;

/**
 * Test for the {@link NewCommandImpl}.
 */
public class NewCommandTest implements CommandVerifier {

   private Command< Object > command;
   
   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      command = new NewCommandImpl();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void basicConstructionTest() {
      Assert.assertEquals( CaliNewCommandKeyImpl.key(), command.getKey() );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldPartialMatch() {
      Assert.assertTrue( command.partialMatches( "new TestAnnotatedSingleton( testName, 567.06 )" ) );
      Assert.assertTrue( command.partialMatches( "new TestAnnotatedSingleton( testName )" ) );
      Assert.assertTrue( command.partialMatches( "new TestAnnotatedSingleton( test" ) );
      Assert.assertTrue( command.partialMatches( "new TestAnnotated" ) );
      Assert.assertTrue( command.partialMatches( "ne" ) );
      Assert.assertTrue( command.partialMatches( "" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotPartialMatch() {
      Assert.assertTrue( command.partialMatches( "anything" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( command.completeMatches( "new TestAnnotatedSingleton( testName )" ) );
      Assert.assertTrue( command.completeMatches( "new TestAnnotatedSingleton( testName, 495.987 )" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotCompleteMatch() {
      Assert.assertTrue( command.completeMatches( "new TestAnnotatedSingleton( testNa" ) );
      Assert.assertTrue( command.completeMatches( "new TestAnnotatedSingleto" ) );
      Assert.assertTrue( command.completeMatches( "new " ) );
      Assert.assertTrue( command.completeMatches( "n" ) );
      Assert.assertTrue( command.completeMatches( "" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldAutoComplete() {
      Assert.assertEquals( "new TestAnnotatedSingleton( testName", command.autoComplete( "new TestAnnotatedSingleton( testName }" ) );
      Assert.assertEquals( "new TestAnnotatedSingleton(", command.autoComplete( "new TestAnnotatedSingleton(" ) );
      Assert.assertEquals( "new TestAnnotatedSingleton(", command.autoComplete( "new TestAnnotated" ) );
      Assert.assertEquals( "new", command.autoComplete( "n" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotAutoComplete() {
      Assert.assertNull( command.autoComplete( "anything" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldExecuteAndParameterize() {
      Assert.assertEquals( new TestAnnotatedSingletonImpl( "testName"), command.autoComplete( "new TestAnnotatedSingleton( testName )" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotExecuteAndParameterize() {
      Assert.assertEquals( new TestAnnotatedSingletonImpl( "testName"), command.autoComplete( "new TestAnnotatedSingleton( " ) );
   }// End Method

}// End Class
