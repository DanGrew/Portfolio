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

import command.CommandKey;

/**
 * Test for the {@link CaliStatementKeyImpl}.
 */
public class CaliNewCommandKeyTest {
   
   private CommandKey newKey;

   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      newKey = new CaliNewCommandKeyImpl();
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
      Assert.assertEquals( CaliNewCommandKeyImpl.key(), newKey.autoComplete( "new" ) );
      Assert.assertEquals( CaliNewCommandKeyImpl.key(), newKey.autoComplete( "n" ) );
      Assert.assertEquals( CaliNewCommandKeyImpl.key(), newKey.autoComplete( "" ) );
      Assert.assertEquals( CaliNewCommandKeyImpl.key(), newKey.autoComplete( "NE" ) );
   }// End Method
   
   /**
    * {@link CommandKey#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertEquals( CaliNewCommandKeyImpl.key(), newKey.autoComplete( "anything" ) );
   }// End Method
   
   /**
    * {@link CommandKey#extractKeyExpression(String)} acceptance test.
    */
   @Test public void shouldExtractKey() {
      Assert.assertEquals( CaliNewCommandKeyImpl.key(), newKey.extractKeyExpression( "new" ) );
      Assert.assertEquals( CaliNewCommandKeyImpl.key().toUpperCase(), newKey.extractKeyExpression( "NEW" ) );
      Assert.assertEquals( "n", newKey.extractKeyExpression( "n" ) );
   }// End Method
   
   /**
    * {@link CommandKey#removeKeyFromInput(String)} acceptance test.
    */
   @Test public void shouldRemoveKey() {
      Assert.assertEquals( "anything", newKey.removeKeyFromInput( "new anything" ) );
      Assert.assertEquals( "anything", newKey.removeKeyFromInput( "ne anything" ) );
   }// End Method
}// End Class
