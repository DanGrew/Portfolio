/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.util.Arrays;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.CommandParameter;
import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.ReferenceClassParameterTypeImpl;
import system.CaliSystem;
import test.model.TestObjects.TestSingletonImpl;
import annotation.CaliParserUtilities;
import command.pattern.CommandParameterVerifier;
import common.TestObjects.TestAnnotatedSingletonImpl;
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;
import common.TestObjects.TestDoubleParameterAnnotatedSingletonImpl;

/**
 * Test for the {@link ConstructorParameterImpl}.
 */
public class ConstructorParameterTest implements CommandParameterVerifier {

   private static CommandParameter parameter;
   
   /**
    * Method to set up the test.
    */
   @BeforeClass public static void setup(){
      CaliSystem.register( TestAnnotatedSingletonImpl.class );
      CaliSystem.register( TestAnotherAnnotatedSingletonImpl.class );
      CaliSystem.register( TestDoubleParameterAnnotatedSingletonImpl.class );
      ClassParameterTypes.addParameterTypes( Arrays.asList( 
               new ReferenceClassParameterTypeImpl<>( Singleton.class ) 
      ) );
      parameter = new ConstructorParameterImpl();
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test @Override public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( TestAnnotatedSingletonImpl.class.getSimpleName() ) );
      Assert.assertTrue( parameter.partialMatches( "TestAn" ) );
      Assert.assertTrue( parameter.partialMatches( TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() ) );
      Assert.assertTrue( parameter.partialMatches( TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + "anything" ) );
      Assert.assertTrue( parameter.partialMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + "anything" + CaliParserUtilities.close() ) 
      );
      Assert.assertTrue( parameter.partialMatches( "TestAn(" ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#partialMatches(String)} reject test.
    */
   @Test @Override public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
      Assert.assertFalse( parameter.partialMatches( TestSingletonImpl.class.getSimpleName() ) );
      Assert.assertFalse( parameter.partialMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + 
               " name, anything " + CaliParserUtilities.close() 
      ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + 
               "name" + CaliParserUtilities.close() 
      ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#completeMatches(String)} reject test.
    */
   @Test @Override public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
      Assert.assertFalse( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + "name" 
      ) );
      Assert.assertFalse( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() 
      ) );
      Assert.assertFalse( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName()
      ) );
      Assert.assertFalse( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + 
               " name, anything " + CaliParserUtilities.close() 
      ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#extractInput(String)} acceptance test.
    */
   @Test @Override public void shouldExtract() {
      Assert.assertEquals( "anything", parameter.extractInput( "anything" ) );
      Assert.assertEquals( "", parameter.extractInput( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + "name" 
      ) );
      Assert.assertEquals( "", parameter.extractInput( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() 
      ) );
      Assert.assertEquals( "", parameter.extractInput( 
               TestAnnotatedSingletonImpl.class.getSimpleName()
      ) );
      
      Assert.assertEquals( "anythingElse", parameter.extractInput( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + 
               "name" + CaliParserUtilities.close() + 
               "anythingElse"
      ) );
      
      Assert.assertEquals( "anythingElse", parameter.extractInput( 
               TestAnotherAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + 
               "name, something" + CaliParserUtilities.close() + 
               "anythingElse"
      ) );
      Assert.assertEquals( "", parameter.extractInput( "TestAnotherAnnotatedSingletonImpl( testName, 567.06 )" ) );
      Assert.assertEquals( "", parameter.extractInput( "TestAnotherAnnotatedSingletonImpl( testName, " ) );
      Assert.assertEquals( "", parameter.extractInput( "TestDoubleParameterAnnotatedSingletonImpl( testName )" ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void shouldParseParameters() {
      Assert.fail();
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#parseObject(String)} acceptance test.
    * @throws SecurityException 
    * @throws NoSuchMethodException 
    */
   @Test public void shouldParse() throws NoSuchMethodException, SecurityException {
      final String testParameter = "name";
      ConstructorParameterValue constructorValue = new ConstructorParameterValue();
      constructorValue.setConstructor( TestAnnotatedSingletonImpl.class.getConstructor( String.class ) );
      constructorValue.addParameters( testParameter );
      Assert.assertEquals( 
               constructorValue, 
               parameter.parseObject( 
                        TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + 
                        testParameter + CaliParserUtilities.close() 
               ) 
      );
      
      final String testParameter2 = "name";
      final String testParameter3 = "20.0";
      constructorValue = new ConstructorParameterValue();
      constructorValue.setConstructor( TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ) );
      constructorValue.addParameters( testParameter2 );
      constructorValue.addParameters( testParameter3 );
      Assert.assertEquals( 
               constructorValue, 
               parameter.parseObject( 
                        TestAnotherAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + 
                        testParameter2 + ", " + testParameter3 + CaliParserUtilities.close() 
               ) 
      );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#parseObject(String)} reject test.
    * @throws SecurityException 
    * @throws NoSuchMethodException 
    */
   @Test @Override public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
      
      final String testParameter2 = "name";
      final double testParameter3 = 20.0;
      Assert.assertNull( 
               parameter.parseObject( 
                        TestAnnotatedSingletonImpl.class.getSimpleName() + CaliParserUtilities.open() + 
                        testParameter2 + ", " + testParameter3 + CaliParserUtilities.close() 
               ) 
      );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test @Override public void shouldAutoComplete() {
      Assert.assertEquals( 
               "Test",
               parameter.autoComplete( "Tes" ) 
      );
      Assert.assertEquals( 
               "TestAn",
               parameter.autoComplete( "TestA" ) 
      );
      Assert.assertEquals( 
               "TestAnnotatedSingletonImpl(",
               parameter.autoComplete( "TestAnnotatedSingletonImpl" ) 
      );
      Assert.assertEquals(
               "TestAnnotatedSingletonImpl(",
               parameter.autoComplete( "TestAnn" ) 
      );
      Assert.assertEquals( 
               "TestAnnotatedSingletonImpl(",
               parameter.autoComplete( "TestAnnotatedSingletonImpl(" ) 
      );
      Assert.assertEquals( 
               "TestAnnotatedSingletonImpl( anything )",
               parameter.autoComplete( "TestAnnotatedSingletonImpl( anything" ) 
      );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#autoComplete(String)} reject test.
    */
   @Test @Override public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( " " ) );
   }// End Method
   
   @Test public void acceptsNonStringParameters(){
      Assert.fail();
   }

   /**
    * {@link ConstructorParameterImpl#getSuggestions(String)} test.
    */
   @Test public void shouldSuggest(){
      Assert.assertEquals( 
               Arrays.asList( "TestAnnotatedSingletonImpl(", "TestAnotherAnnotatedSingletonImpl(", "TestDoubleParameterAnnotatedSingletonImpl(" ), 
               parameter.getSuggestions( "" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "TestAnnotatedSingletonImpl(", "TestAnotherAnnotatedSingletonImpl(" ), 
               parameter.getSuggestions( "TestA" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "TestAnnotatedSingletonImpl(" ), 
               parameter.getSuggestions( "TestAnnota" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "<String> )", "<String>, <Double> )", "<String>, <TestAnotherAnnotatedSingletonImpl> )" ), 
               parameter.getSuggestions( "TestAnotherAnnotatedSingletonImpl(" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "<String> )", "<String>, <Double> )", "<String>, <TestAnotherAnnotatedSingletonImpl> )" ), 
               parameter.getSuggestions( "TestAnotherAnnotatedSingletonImpl( anything" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( ")", "<Double> )", "<TestAnotherAnnotatedSingletonImpl> )" ), 
               parameter.getSuggestions( "TestAnotherAnnotatedSingletonImpl( anything," ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "<Double> )", "<TestAnotherAnnotatedSingletonImpl> )" ), 
               parameter.getSuggestions( "TestAnotherAnnotatedSingletonImpl( anything, 23" ) 
      );
      Assert.assertEquals( 
               Arrays.asList(), 
               parameter.getSuggestions( "TestAnotherAnnotatedSingletonImpl( anything, 23 )" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "<String>, <Double> )" ), 
               parameter.getSuggestions( "TestDoubleParameterAnnotatedSingletonImpl( anything" ) 
      );
   }// End Method
}// End Class
