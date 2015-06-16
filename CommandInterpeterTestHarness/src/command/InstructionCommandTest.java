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

import parameter.CommandParameter;
import parameter.wrapper.CommandParameters;
import utility.TestFunctions;

/**
 * Test for the {@link InstructionCommandImpl}.
 */
public class InstructionCommandTest {
   
   protected final String DESCRIPTION = "Something very informative";
   protected CommandParameter key;
   protected Function< CommandParameters, CommandResult< String > > function;
   protected Command< String > command;

   /**
    * Method to setup the test by creating the relevant objects to test.
    */
   @SuppressWarnings("unchecked") @Before public void setup(){
      key = Mockito.mock( CommandParameter.class );
      Mockito.when( key.extractInput( Mockito.anyString() ) ).thenAnswer( TestFunctions.singleParameterExtractor() );
      Mockito.when( key.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      
      function = Mockito.mock( Function.class );
      command = new ParameterizedCommandImpl< String >( DESCRIPTION, function, key );
   }// End Method
   
   /**
    * Method to test that the {@link Command} has been created properly.
    */
   @Test public void basicConstructionTest() {
      Assert.assertNotNull( command );
   }// End Method
   
   /**
    * Method to test that a {@link Command} successfully identifies partial matches.
    */
   @Test public void assertPartialMatches(){
      Mockito.when( key.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      Assert.assertTrue( command.partialMatches( "Anything" ) );
      
      Mockito.when( key.partialMatches( Mockito.anyString() ) ).thenReturn( false );
      Mockito.when( key.extractInput( Mockito.anyString() ) ).thenAnswer( TestFunctions.firstArgument() );
      Mockito.when( key.extractInput( Mockito.anyString() ) ).thenAnswer( TestFunctions.firstArgument() );
      Assert.assertFalse( command.partialMatches( "Anything" ) );
   }// End Method
   
   /**
    * Method to test that a {@link Command} successfully identifies complete matches.
    */
   @Test public void assertCompleteMatches(){
      Assert.assertTrue( command.completeMatches( "Anything" ) );
      
      Mockito.when( key.completeMatches( Mockito.anyString() ) ).thenReturn( false );
      Assert.assertFalse( command.completeMatches( "Anything" ) );
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
      
      Mockito.when( key.extractInput( Mockito.anyString() ) ).thenReturn( "" );
      command.execute( "Any Expression" );
      Mockito.verify( function, Mockito.times( 2 ) ).apply( Mockito.anyObject() );
   }// End Method

}// End Class
