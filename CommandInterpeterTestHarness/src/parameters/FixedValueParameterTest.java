/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;
import command.pattern.CommandParameterVerifier;

/**
 * Test for the {@link FixedValueParameterImpl}.
 */
public class FixedValueParameterTest implements CommandParameterVerifier{

   private static final String VALUE = "anything";
   private CommandParameter parameter;
   
   /**
    * Method to set up the test.
    */
   @Before public void setup(){
      parameter = new FixedValueParameterImpl( VALUE );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( VALUE ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
      Assert.assertTrue( parameter.partialMatches( "any" ) );
      Assert.assertTrue( parameter.partialMatches( "Any" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anythingElse" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( VALUE ) );
      Assert.assertTrue( parameter.partialMatches( "Anything" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "anythingElse" ) );
      Assert.assertFalse( parameter.completeMatches( "any" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldExtract() {
      Assert.assertEquals( "else at all", parameter.extractInput( "anything else at all" ) );
      Assert.assertEquals( "", parameter.extractInput( "anything" ) );
      Assert.assertEquals( "else at all", parameter.extractInput( "any else at all" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldParseParameters() {
      Assert.assertEquals( VALUE, parameter.parseParameter( "anything else at all" ) );
      Assert.assertEquals( VALUE, parameter.parseParameter( "anything" ) );
      Assert.assertEquals( "any", parameter.parseParameter( "any else at all" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldParse() throws NoSuchMethodException, SecurityException {
      Assert.assertNull( parameter.parseObject( "anything else at all" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything else at all" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldAutoComplete() {
      Assert.assertEquals( VALUE, parameter.autoComplete( "an" ) );
      Assert.assertEquals( VALUE, parameter.autoComplete( "anythin" ) );
      Assert.assertEquals( VALUE, parameter.autoComplete( "anything" ) );
      Assert.assertEquals( VALUE, parameter.autoComplete( "anythin else at all" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "no match" ) );
   }// End Method

}// End Class
