/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parameter.CommandParameter;
import parameter.wrapper.CommandParameters;
import parameter.wrapper.LinkedMapParametersImpl;
import utility.TestFunctions;

/**
 * Test for the {@link LinkedMapParametersImpl}.
 */
public class LinkedMapParametersTest {

   private CommandParameter testParameter1;
   private CommandParameter testParameter2;
   private CommandParameters parameters;
   
   /**
    * Method to setup the objects for testing.
    */
   @Before public void setup(){
      testParameter1 = Mockito.mock( CommandParameter.class );
      testParameter2 = Mockito.mock( CommandParameter.class );
      Mockito.when( testParameter1.extractInput( Mockito.anyString() ) ).thenAnswer( TestFunctions.singleParameterExtractor() );
      Mockito.when( testParameter2.extractInput( Mockito.anyString() ) ).thenAnswer( TestFunctions.singleParameterExtractor() );
      parameters = new LinkedMapParametersImpl();
      parameters.applyParameters( testParameter1, testParameter2 );      
   }// End Method
   
   /**
    * Method to test that the get and set methods function correctly and the values are stored.
    */
   @Test public void getAndSetParameterTest() {
      Object value1 = parameters.getParameter( testParameter1 );
      Assert.assertNull( value1 );
      
      final String testParameter1Value = "String";
      parameters.setParameter( testParameter1, testParameter1Value );
      value1 = parameters.getParameter( testParameter1 );
      Assert.assertNotNull( value1 );
      Assert.assertEquals( testParameter1Value, value1 );
      
      Object value2 = parameters.getParameter( testParameter2 );
      Assert.assertNull( value2 );
      
      final Number testParameter2Value = 1234;
      parameters.setParameter( testParameter2, testParameter2Value );
      value2 = parameters.getParameter( testParameter2 );
      Assert.assertNotNull( value2 );
      Assert.assertEquals( testParameter2Value, value2 );
      
      Assert.assertEquals( testParameter1Value, value1 );
   }// End Method
   
   /**
    * Method to test that the {@link LinkedMapParametersImpl} identifies partial matches correctly.
    */
   @Test public void partialMatchesTest(){
      Mockito.when( testParameter1.partialMatches( Mockito.anyString() ) ).thenReturn( false );
      Mockito.when( testParameter2.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      
      Assert.assertFalse( parameters.partialMatches( "Anything" ) );
      Assert.assertTrue( parameters.partialMatches( "" ) );
      Assert.assertFalse( parameters.partialMatches( "Anything Twice" ) );

      Mockito.when( testParameter1.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( testParameter1.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      
      Assert.assertTrue( parameters.partialMatches( "Anything" ) );
      Assert.assertTrue( parameters.partialMatches( "" ) );
      Assert.assertTrue( parameters.partialMatches( "Anything Twice" ) );
      
      Mockito.when( testParameter2.partialMatches( Mockito.anyString() ) ).thenReturn( false );
      
      Assert.assertTrue( parameters.partialMatches( "Anything" ) );
      Assert.assertTrue( parameters.partialMatches( "" ) );
      Assert.assertFalse( parameters.partialMatches( "Anything Twice" ) );
   }// End Method
   
   /**
    * Method to test that the {@link LinkedMapParametersImpl} identifies complete matches correctly.
    */
   @Test public void completeMatchesTest(){
      Mockito.when( testParameter1.completeMatches( Mockito.anyString() ) ).thenReturn( false );
      Mockito.when( testParameter2.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      
      Assert.assertFalse( parameters.completeMatches( "Anything" ) );
      Assert.assertFalse( parameters.completeMatches( "" ) );
      Assert.assertFalse( parameters.completeMatches( "Anything Twice" ) );

      Mockito.when( testParameter1.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      
      //Wrong number of params.
      Assert.assertFalse( parameters.completeMatches( "Anything" ) );
      //No input
      Assert.assertFalse( parameters.completeMatches( "" ) );
      //Complete for requirements of params.
      Assert.assertTrue( parameters.completeMatches( "Anything Twice" ) );
   }// End Method
   
   /**
    * Method to test that the auto complete suggestions function correctly.
    */
   @Test public void autoCompleteTest(){
      
      final String SUGGESTION = "SUGGESTION1";
      final String SUGGESTION2 = "SUGGESTION2";
      Mockito.when( testParameter1.autoComplete( Mockito.anyString() ) ).thenReturn( SUGGESTION );
      Mockito.when( testParameter1.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( testParameter2.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( testParameter2.autoComplete( Mockito.anyString() ) ).thenReturn( SUGGESTION2 );
      
      Assert.assertEquals( SUGGESTION, parameters.autoComplete( "Anything" ) );
      Assert.assertEquals( SUGGESTION + " " + SUGGESTION2, parameters.autoComplete( "Anything Twice" ) );
      Assert.assertNull( SUGGESTION, parameters.autoComplete( "" ) );
   }// End Method
   
   /**
    * Method to test that the {@link LinkedMapParametersImpl} has values populated correctly when parameterized.
    */
   @Test public void parameterizeTest(){
      Mockito.when( testParameter1.parseObject( Mockito.anyString() ) ).then( TestFunctions.singleParameterParser() );
      Mockito.when( testParameter2.parseObject( Mockito.anyString() ) ).then( TestFunctions.singleParameterParser() );
      
      final String FIRST_VALUE = "Anything";
      final String SECOND_VALUE = "Twice";
      parameters.parameterize( FIRST_VALUE + " " + SECOND_VALUE );
      Assert.assertEquals( FIRST_VALUE, parameters.getParameter( testParameter1 ) );
      Assert.assertEquals( SECOND_VALUE, parameters.getParameter( testParameter2 ) );
   }// End Method

   /**
    * {@link LinkedMapParametersImpl#getSuggestions(String)} test.
    */
   @Test public void shouldSuggest(){
      List< String > expectedFirstSuggestions = Arrays.asList( "firstSuggestion" );
      Mockito.when( testParameter1.getSuggestions( Mockito.anyString() ) ).thenReturn( expectedFirstSuggestions );
      List< String > expectedSecondSuggestions = Arrays.asList( "secondSuggestionA", "secondSuggestionB" );
      Mockito.when( testParameter2.getSuggestions( Mockito.anyString() ) ).thenReturn( expectedSecondSuggestions );
      
      //Enter first parameters return.
      Mockito.when( testParameter1.completeMatches( Mockito.anyString() ) ).thenReturn( false );
      Mockito.when( testParameter1.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( testParameter1.extractInput( Mockito.anyString() ) ).then( TestFunctions.singleParameterExtractor() );
      Assert.assertEquals( expectedFirstSuggestions, parameters.getSuggestions( "anything" ) );
      
      //Enter second parameters return.
      Mockito.when( testParameter1.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( testParameter2.partialMatches( Mockito.anyString() ) ).thenReturn( true );
      Mockito.when( testParameter2.extractInput( Mockito.anyString() ) ).then( TestFunctions.singleParameterExtractor() );
      Assert.assertEquals( expectedSecondSuggestions, parameters.getSuggestions( "anything" ) );
      
      //Pass both checks
      Mockito.when( testParameter2.completeMatches( Mockito.anyString() ) ).thenReturn( true );
      Assert.assertEquals( Arrays.asList( LinkedMapParametersImpl.READY ), parameters.getSuggestions( "anything" ) );
   }// End Method
   
}// End Class
