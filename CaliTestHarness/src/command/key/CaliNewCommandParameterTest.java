/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.key;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parameter.CommandParameter;

/**
 * Test for the {@link CaliStatementParameterImpl}.
 */
public class CaliNewCommandParameterTest {
   
   private CommandParameter newKey;

   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      newKey = new CaliNewCommandParameterImpl();
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
      Assert.assertEquals( CaliNewCommandParameterImpl.key(), newKey.autoComplete( "new" ) );
      Assert.assertEquals( CaliNewCommandParameterImpl.key(), newKey.autoComplete( "n" ) );
      Assert.assertEquals( CaliNewCommandParameterImpl.key(), newKey.autoComplete( "" ) );
      Assert.assertEquals( CaliNewCommandParameterImpl.key(), newKey.autoComplete( "NE" ) );
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
      Assert.assertEquals( CaliNewCommandParameterImpl.key(), newKey.parseParameter( "new" ) );
      Assert.assertEquals( CaliNewCommandParameterImpl.key().toUpperCase(), newKey.parseParameter( "NEW" ) );
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
