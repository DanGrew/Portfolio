/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import system.CaliSystem;
import command.pattern.CommandParameterVerifier;
import common.TestObjects.TestAnnotatedSingletonImpl;

/**
 * Test for the {@link ConstructorParameterImpl}.
 */
public class SingletonMethodCallParameterTest extends SingletonReferenceParameterTest implements CommandParameterVerifier {

   /**
    * {@inheritDoc}
    */
   @Before @Override public void initialise(){
      parameter = new SingletonMethodCallParameterImpl();
      
      CaliSystem.register( TestAnnotatedSingletonImpl.class );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldPartialMatch() {
      super.shouldPartialMatch();
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.testCaliMethod( stringValue )" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.testCaliMethod( anything" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.testCaliM( anything" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.testCaliMethod(" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.testCaliMethod" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.test" ) );
      Assert.assertTrue( parameter.partialMatches( "" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotPartialMatch() {
      super.shouldNotPartialMatch();
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
      Assert.assertFalse( parameter.partialMatches( "TestAnnotated.nonCali" ) );
      Assert.assertFalse( parameter.partialMatches( "TestAnnotated.testCaliMethod( name, anything )" ) );
      Assert.assertFalse( parameter.partialMatches( "TestAnnotated.nonCaliMethod( name )" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void shouldPartialMatchFurtherInput() {
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.testCaliMethod( stringValue ).anything" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.testCaliMethod( stringValue ) anything" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.partialMatches( "TestAnnotated.testCaliMethod( stringValue )" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotCompleteMatch() {
      super.shouldNotCompleteMatch();
      Assert.assertFalse( parameter.partialMatches( "TestAnnotated.testCaliMethod( name, anything )" ) );
      Assert.assertFalse( parameter.partialMatches( "TestAnnotated.nonCaliMethod( name )" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldExtract() {
      Assert.assertEquals( "", parameter.extractInput( "TestAnno." ) );
      Assert.assertEquals( "", parameter.extractInput( TEST_ANNOTATED_SINGLETON_NAME ) );
      Assert.assertEquals( "anything", parameter.extractInput( "anything" ) );
      Assert.assertEquals( 
               "", 
               parameter.extractInput( "TestAnnotated.testCaliMethod( name )" ) 
      );
      Assert.assertEquals( 
               "anything", 
               parameter.extractInput( "TestAnnotated.testCaliMethod( name ) anything" ) 
      );
      Assert.assertEquals( 
               "", 
               parameter.extractInput( "TestAnnotated.testCaliMethod( name" ) 
      );
      Assert.assertEquals( 
               "", 
               parameter.extractInput( "TestAnnotated.testCaliMethod(" ) 
      );
      Assert.assertEquals( 
               "anything.anything( anything )", 
               parameter.extractInput( "anything.anything( anything )" ) 
      );
      Assert.assertEquals( 
               "anything.anything( anything ) blah blah blah", 
               parameter.extractInput( "anything.anything( anything ) blah blah blah" ) 
      );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void shouldParseParameters() {
      super.shouldParseParameters();
      Assert.assertEquals( "anything", parameter.parseParameter( "anything" ) );
      Assert.assertEquals( 
               "TestAnnotated.testCaliMethod( name )", 
               parameter.parseParameter( "TestAnnotated.testCaliMethod( name )" ) 
      );
      Assert.assertEquals( 
               "anything.anything( anything )", 
               parameter.parseParameter( "anything.anything( anything )" ) 
      );
      Assert.assertEquals( 
               "anything.anything( anything ) blah blah blah", 
               parameter.parseParameter( "anything.anything( anything ) blah blah blah" ) 
      );
      Assert.assertEquals( "TestAnnotated.testCaliMethod( name )", parameter.parseParameter( "TestAnnotated.testCaliMethod( name )" ) );
      Assert.assertEquals( "TestAnnotated.testCaliMethod( name )", parameter.parseParameter( "TestAnnotated.testCaliMethod( name ) anything" ) );
   }
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldParse() throws NoSuchMethodException, SecurityException {
      final String testParameter = "name";
      SingletonMethodCallValue callValue = new SingletonMethodCallValue();
      callValue.setSingleton( TEST_ANNOTATED_SINGLETON_OBJECT );
      callValue.setMethod( TestAnnotatedSingletonImpl.class.getMethod( "testCaliMethod", String.class ) );
      callValue.addParameter( testParameter );
      Assert.assertEquals( callValue, parameter.parseObject( "TestAnnotated.testCaliMethod( name )" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotParse() {
      super.shouldNotParse();
      
      Assert.assertNull( parameter.parseObject( "TestAnnotated.testCaliMethod( anything, twice )" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldAutoComplete() {
      super.shouldAutoComplete();
      
      Assert.assertEquals( 
               "TestAnnotated.testCaliMethod(",
               parameter.autoComplete( "TestAnnotated.testC" ) 
      );
      Assert.assertEquals(
               "TestAnnotated.testCaliMethod(",
               parameter.autoComplete( "TestAnn.testC" ) 
      );
      Assert.assertEquals( 
               "TestAnnotated.testCaliMethod(",
               parameter.autoComplete( "TestAnnotated.testCaliMethod(" ) 
      );
      Assert.assertEquals( 
               "TestAnnotated.testCaliMethod( anything",
               parameter.autoComplete( "TestAnnotated.testCaliMethod( anything" ) 
      );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotAutoComplete() {
      super.shouldNotAutoComplete();
      
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( " " ) );
   }// End Method
   
   /**
    * {@link SingletonMethodCallParameterImpl#getSuggestions(String)} test.
    */
   @Test public void shouldSuggest(){
      Assert.fail();
      Assert.assertEquals( 
               Arrays.asList( TEST_ANNOTATED_SINGLETON_NAME ),
               parameter.getSuggestions( "Test" ) 
      );
   }// End Method
   
}// End Class
