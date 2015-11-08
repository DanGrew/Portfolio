/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parameter.CommandParameter;

/**
 * Test for the {@link SingletonReferenceParameterImpl}.
 */
public class NewCommandParameterTest {
   
   private CommandParameter newKey;

   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      newKey = new NewCommandParameterImpl();
   }// End Method
   
   /**
    * {@link CommandKey#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( newKey.partialMatches( "new" ) );
      Assert.assertTrue( newKey.partialMatches( "n" ) );
      Assert.assertTrue( newKey.partialMatches( " " ) );
      Assert.assertTrue( newKey.partialMatches( "NEW" ) );
   }// End Method
   
   /**
    * {@link CommandKey#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( newKey.partialMatches( "anything" ) );
   }// End Method
   
   /**
    * {@link CommandKey#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( newKey.completeMatches( "new" ) );
      Assert.assertTrue( newKey.completeMatches( "NEW" ) );
   }// End Method
   
   /**
    * {@link CommandKey#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( newKey.completeMatches( "ne" ) );
      Assert.assertFalse( newKey.completeMatches( "anything" ) );
   }// End Method

   /**
    * {@link CommandKey#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      Assert.assertEquals( NewCommandParameterImpl.key(), newKey.autoComplete( "new" ) );
      Assert.assertEquals( NewCommandParameterImpl.key(), newKey.autoComplete( "n" ) );
      Assert.assertEquals( NewCommandParameterImpl.key(), newKey.autoComplete( "" ) );
      Assert.assertEquals( NewCommandParameterImpl.key(), newKey.autoComplete( "NE" ) );
   }// End Method
   
   /**
    * {@link CommandKey#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( newKey.autoComplete( "anything" ) );
   }// End Method
   
   /**
    * {@link CommandKey#extractKeyExpression(String)} acceptance test.
    */
   @Test public void shouldExtractKey() {
      Assert.assertEquals( NewCommandParameterImpl.key(), newKey.parseParameter( "new" ) );
      Assert.assertEquals( NewCommandParameterImpl.key().toUpperCase(), newKey.parseParameter( "NEW" ) );
      Assert.assertEquals( "n", newKey.parseParameter( "n" ) );
   }// End Method
   
   /**
    * {@link CommandKey#removeKeyFromInput(String)} acceptance test.
    */
   @Test public void shouldRemoveKey() {
      Assert.assertEquals( "anything", newKey.extractInput( "new anything" ) );
      Assert.assertEquals( "anything", newKey.extractInput( "ne anything" ) );
   }// End Method
}// End Class
