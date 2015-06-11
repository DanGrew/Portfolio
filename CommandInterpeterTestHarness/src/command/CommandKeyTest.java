/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the {@link CommandKey} and {@link CommandKeyImpl}.
 */
public class CommandKeyTest {

   /**
    * Method to test that a {@link CommandKeyImpl} correctly identifies partial matches. 
    */
   @Test public void partialMatchesTest() {
      final String stringKey = "SomeCommand";
      CommandKey key = new CommandKeyImpl( stringKey );
      
      Assert.assertTrue( key.partialMatches( stringKey ) );
      Assert.assertTrue( key.partialMatches( "Som" ) );
      Assert.assertTrue( key.partialMatches( "" ) );
      Assert.assertFalse( key.partialMatches( null ) );
   }// End Method
   
   /**
    * Method to test that a {@link CommandKeyImpl} successfully identifies complete matches.
    */
   @Test public void completeMatchesTest() {
      final String stringKey = "SomeCommand";
      CommandKey key = new CommandKeyImpl( stringKey );
      
      Assert.assertFalse( key.completeMatches( "" ) );
      Assert.assertFalse( key.completeMatches( null ) );
      
      Assert.assertTrue( key.completeMatches( stringKey ) );
      Assert.assertTrue( key.completeMatches( stringKey + " other parameters" ) );
      Assert.assertFalse( key.completeMatches( "Som" ) );
      Assert.assertFalse( key.completeMatches( "" ) );
   }// End Method
   
   /**
    * Method to tes tthat the {@link CommandKeyImpl} correctly extracts the key expressions from the input.
    */
   @Test public void extractKeyExpressionTest(){
      final String stringKey = "SomeCommand";
      CommandKey key = new CommandKeyImpl( stringKey );
      
      Assert.assertEquals( "Some", key.extractKeyExpression( "Some" ) );
      Assert.assertEquals( stringKey, key.extractKeyExpression( stringKey ) );
      Assert.assertEquals( "", key.extractKeyExpression( "" ) );
      Assert.assertEquals( "", key.extractKeyExpression( " " ) );
      Assert.assertNull( key.extractKeyExpression( "AnythingElse" ) );
   }// End Method
   
   /**
    * Method to test that the {@link CommandKeyImpl} correctly removes the key from the input.
    */
   @Test public void removeKeyFromInputTest(){
      final String stringKey = "SomeCommand";
      CommandKey key = new CommandKeyImpl( stringKey );
      
      Assert.assertEquals( "", key.removeKeyFromInput( "Some" ) );
      Assert.assertEquals( "extraBit", key.removeKeyFromInput( "SomeCommand extraBit" ) );
      Assert.assertEquals( "", key.removeKeyFromInput( "SomeCommand " ) );
   }// End Method

}// End Class
