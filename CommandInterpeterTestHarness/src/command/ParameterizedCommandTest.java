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
import org.junit.Test;
import org.mockito.Mockito;

import parameter.CommandParameter;
import utility.TestFunctions;

/**
 * Test for the {@link ParameterizedCommandImpl}.
 */
public class ParameterizedCommandTest extends InstructionCommandTest{

   private CommandParameter parameter;
   
   /**
    * {@inheritDoc}
    */
   @SuppressWarnings("unchecked") @Override public void setup() {
      key = Mockito.mock( CommandKey.class );
      Mockito.when( key.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( key.removeKeyFromInput( Mockito.anyString() ) ).thenAnswer( TestFunctions.singleParameterExtractor() );
      function = Mockito.mock( Function.class );
      //Mock a simple parameter and have it match anything.
      parameter = Mockito.mock( CommandParameter.class );
      Mockito.when( parameter.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      command = new ParameterizedCommandImpl< String >( key, DESCRIPTION, function, parameter );
   }// End Method
   
   /**
    * Method to test that a {@link Command} successfully identifies complete matches.
    */
   @Test public void assertCompleteMatches(){
      Mockito.when( parameter.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( parameter.extractInput( Mockito.anyString() ) ).thenAnswer( TestFunctions.singleParameterExtractor() );
      Assert.assertTrue( command.completeMatches( "Anything withParam" ) );
      
      Mockito.when( key.completeMatches( Mockito.anyString() ) ).thenReturn( false );
      Mockito.when( parameter.completeMatches( Mockito.anyString() ) ).thenReturn( false );
      Assert.assertFalse( command.completeMatches( "Anything withParam" ) );
   }// End Method
   
   /**
    * Method to test that the {@link Function} associated is executed when the execute method is called.
    */
   @Test public void assertExecuteAndParameterizeTest(){
      Mockito.verifyZeroInteractions( function );
      
      Mockito.when( parameter.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( parameter.parseObject( Mockito.anyString() ) ).thenReturn( "notNullValue" );
      Mockito.when( parameter.extractInput( Mockito.anyString() ) ).thenReturn( "" );
      command.execute( "Something parameterized" );
      Mockito.verify( function, Mockito.times( 1 ) ).apply( Mockito.anyObject() );
      
      command.execute( "Any Expression" );
      Mockito.verify( function, Mockito.times( 2 ) ).apply( Mockito.anyObject() );
   }// End Method

   /**
    * Method to test that the {@link Command} auto completes.
    */
   @Test public void shouldAutoComplete(){
      Mockito.when( key.getStringKey() ).thenReturn( "Anything" );
      Mockito.when( parameter.autoComplete( Mockito.anyString() ) ).thenReturn( null );
      Assert.assertEquals( "Anything ", command.autoComplete( "Anything f" ) );
   }// End Method
   
}// End Class
