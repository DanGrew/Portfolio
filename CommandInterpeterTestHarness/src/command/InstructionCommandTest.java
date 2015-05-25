/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import java.util.function.Function;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parameter.wrapper.CommandParameters;

/**
 * Test for the {@link InstructionCommandImpl}.
 */
public class InstructionCommandTest {
   
   protected final String DESCRIPTION = "Something very informative";
   protected CommandKey key;
   protected Function< CommandParameters, CommandResult< String > > function;
   protected Command< String > command;

   /**
    * Method to setup the test by creating the relevant objects to test.
    */
   @SuppressWarnings("unchecked") @Before public void setup(){
      key = Mockito.mock( CommandKey.class );
      function = Mockito.mock( Function.class );
      command = new InstructionCommandImpl< String >( key, DESCRIPTION, function );
   }// End Method
   
   /**
    * Method to test that the {@link Command} has been created properly.
    */
   @Test public void basicConstructionTest() {
      Assert.assertEquals( key.getStringKey(), command.getKey() );
   }// End Method
   
   /**
    * Method to test that a {@link Command} successfully identifies partial matches.
    */
   @Test public void assertPartialMatches(){
      Mockito.when( key.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      Assert.assertTrue( command.partialMatches( "Anything" ) );
      Assert.assertTrue( command.partialMatches( "Something Else" ) );
      
      Mockito.when( key.partialMatches( Mockito.anyString() ) ).thenReturn( false );
      Assert.assertFalse( command.partialMatches( "Anything" ) );
      Assert.assertFalse( command.partialMatches( "Something Else" ) );
   }// End Method
   
   /**
    * Method to test that a {@link Command} successfully identifies complete matches.
    */
   @Test public void assertCompleteMatches(){
      Mockito.when( key.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      Assert.assertTrue( command.completeMatches( "Anything" ) );
      Assert.assertTrue( command.completeMatches( "Something Else" ) );
      
      Mockito.when( key.completeMatches( Mockito.anyString() ) ).thenReturn( false );
      Assert.assertFalse( command.completeMatches( "Anything" ) );
      Assert.assertFalse( command.completeMatches( "Something Else" ) );
   }// End Method

   /**
    * Method to test that a {@link Command} successfully identifies auto complete suggestions.
    */
   @Test public void assertAutoComplete(){
      final String SUGGESTION = "SUGGESTION";
      Mockito.when( key.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( key.autoComplete( Mockito.anyString() ) ).thenReturn( SUGGESTION );
      Assert.assertEquals( SUGGESTION, command.autoComplete( "Anything" ) );
      Assert.assertEquals( SUGGESTION, command.autoComplete( "SomethingElse" ) );
   }// End Method
   
   /**
    * Method to test that the {@link Function} associated is executed when the execute method is called.
    */
   @Test public void assertExecuteAndParameterizeTest(){
      Mockito.verifyZeroInteractions( function );
      
      command.execute();
      Mockito.verify( function, Mockito.times( 1 ) ).apply( Mockito.anyObject() );
      
      command.execute( "Any Expression" );
      Mockito.verify( function, Mockito.times( 2 ) ).apply( Mockito.anyObject() );
   }// End Method

}// End Class
