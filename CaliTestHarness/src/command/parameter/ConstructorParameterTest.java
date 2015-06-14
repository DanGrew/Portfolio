/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.BooleanParameterImpl;
import parameter.CommandParameter;
import system.CaliSystem;
import test.model.TestObjects.TestSingleton;
import annotation.CaliAnnotationSyntax;

import common.TestObjects.TestAnnotatedSingletonImpl;

/**
 * Test for the {@link ConstructorParameterImpl}.
 */
public class ConstructorParameterTest {

   private static CommandParameter parameter;
   
   /**
    * Method to set up the test.
    */
   @BeforeClass public static void setup(){
      CaliSystem.register( TestAnnotatedSingletonImpl.class );
      parameter = new ConstructorParameterImpl();
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#partialMatches(String)} acceptance test.
    */
   @Test public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( TestAnnotatedSingletonImpl.class.getSimpleName() ) );
      Assert.assertTrue( parameter.partialMatches( "TestAn" ) );
      Assert.assertTrue( parameter.partialMatches( TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() ) );
      Assert.assertTrue( parameter.partialMatches( TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + "anything" ) );
      Assert.assertTrue( parameter.partialMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + "anything" + CaliAnnotationSyntax.close() ) 
      );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#partialMatches(String)} reject test.
    */
   @Test public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "anything" ) );
      Assert.assertFalse( parameter.partialMatches( TestSingleton.class.getSimpleName() ) );
      Assert.assertTrue( parameter.partialMatches( "TestAn(" ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#completeMatches(String)} acceptance test.
    */
   @Test public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + 
               "name" + CaliAnnotationSyntax.close() 
      ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#completeMatches(String)} reject test.
    */
   @Test public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "anything" ) );
      Assert.assertFalse( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + "name" 
      ) );
      Assert.assertFalse( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() 
      ) );
      Assert.assertFalse( parameter.completeMatches( 
               TestAnnotatedSingletonImpl.class.getSimpleName()
      ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#extractInput(String)} acceptance test.
    */
   @Test public void shouldExtract() {
      Assert.assertEquals( "", parameter.extractInput( "anything" ) );
      Assert.assertEquals( "", parameter.extractInput( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + "name" 
      ) );
      Assert.assertEquals( "", parameter.extractInput( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() 
      ) );
      Assert.assertEquals( "", parameter.extractInput( 
               TestAnnotatedSingletonImpl.class.getSimpleName()
      ) );
      
      Assert.assertEquals( "anythingElse", parameter.extractInput( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + 
               "name" + CaliAnnotationSyntax.close() + 
               "anythingElse"
      ) );
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
      constructorValue.addParameter( testParameter );
      Assert.assertEquals( 
               constructorValue, 
               parameter.parseObject( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + 
               testParameter + CaliAnnotationSyntax.close() 
      ) );
      
      final String testParameter2 = "name";
      final double testParameter3 = 20.0;
      constructorValue = new ConstructorParameterValue();
      constructorValue.setConstructor( TestAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ) );
      constructorValue.addParameter( testParameter );
      Assert.assertEquals( 
               constructorValue, 
               parameter.parseObject( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + 
               testParameter2 + ", " + testParameter3 + CaliAnnotationSyntax.close() 
      ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#parseObject(String)} reject test.
    */
   @Test public void shouldNotParse() {
      Assert.assertNull( parameter.parseObject( "anything" ) );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#autoComplete(String)} acceptance test.
    */
   @Test public void shouldAutoComplete() {
      Assert.assertEquals( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open(),
               parameter.autoComplete( TestAnnotatedSingletonImpl.class.getSimpleName() ) 
       );
      Assert.assertEquals(
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open(),
               parameter.autoComplete( "TestAn" ) 
      );
      Assert.assertEquals( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open(),
               parameter.autoComplete( TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() ) 
      );
      Assert.assertEquals( 
               TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + "anything",
               parameter.autoComplete( TestAnnotatedSingletonImpl.class.getSimpleName() + CaliAnnotationSyntax.open() + "anything" ) 
      );
   }// End Method
   
   /**
    * {@link ConstructorParameterImpl#autoComplete(String)} reject test.
    */
   @Test public void shouldNotAutoComplete() {
      Assert.assertNull( parameter.autoComplete( "anything" ) );
      Assert.assertNull( parameter.autoComplete( "" ) );
      Assert.assertNull( parameter.autoComplete( " " ) );
   }// End Method
   
   @Test public void acceptsNonStringParameters(){
      Assert.fail();
   }

}// End Class
