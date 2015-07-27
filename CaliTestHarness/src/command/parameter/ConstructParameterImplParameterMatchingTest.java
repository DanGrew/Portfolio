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
import architecture.request.RequestSystem;

import command.pattern.CommandParameterVerifier;
import common.TestObjects.TestAnnotatedSingletonImpl;
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;

/**
 * Test for complex tests that involve matching parameters and types for the {@link ConstructorParameterImpl}.
 */
public class ConstructParameterImplParameterMatchingTest implements CommandParameterVerifier {

   private static CommandParameter parameter;
   private static String TEST_FIRST_SINGLETON_NAME;
   private static TestAnotherAnnotatedSingletonImpl TEST_FIRST_SINGLETON;
   private static String TEST_SECOND_SINGLETON_NAME;
   private static TestAnotherAnnotatedSingletonImpl TEST_SECOND_SINGLETON;
   private static String TEST_THIRD_SINGLETON_NAME;
   private static String NON_CALI_SINGLETON_NAME;
   private static TestSingletonImpl NON_CALI_SINGLETON;
   
   /**
    * Method to setup the {@link CaliSystem} and {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      RequestSystem.reset();
      
      TEST_FIRST_SINGLETON_NAME = "TestFirstSingleton";
      TEST_FIRST_SINGLETON = new TestAnotherAnnotatedSingletonImpl( TEST_FIRST_SINGLETON_NAME );
      RequestSystem.store( TEST_FIRST_SINGLETON, Singleton.class );
      TEST_SECOND_SINGLETON_NAME = "TestSecondSingleton";
      TEST_SECOND_SINGLETON = new TestAnotherAnnotatedSingletonImpl( TEST_SECOND_SINGLETON_NAME );
      RequestSystem.store( TEST_SECOND_SINGLETON, Singleton.class );
      TEST_THIRD_SINGLETON_NAME = "TestThirdSingleton";
      TestAnnotatedSingletonImpl TEST_THIRD_SINGLETON = new TestAnnotatedSingletonImpl( TEST_THIRD_SINGLETON_NAME );
      RequestSystem.store( TEST_THIRD_SINGLETON, Singleton.class );
      
      NON_CALI_SINGLETON_NAME = "TestNonCaliSingleton";
      NON_CALI_SINGLETON = new TestSingletonImpl( NON_CALI_SINGLETON_NAME );
      RequestSystem.store( NON_CALI_SINGLETON );
      
      CaliSystem.register( TestAnotherAnnotatedSingletonImpl.class );
      CaliSystem.register( TestAnnotatedSingletonImpl.class );
      ClassParameterTypes.addParameterTypes( Arrays.asList( 
               new ReferenceClassParameterTypeImpl<>( Singleton.class ) 
      ) );
      
      parameter = new ConstructorParameterImpl();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldPartialMatch() {
      Assert.assertTrue( parameter.partialMatches( "TestAnotherAnnotatedSingletonImpl( 23" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnotherAnnotatedSingletonImpl( anything, 23" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnotherAnnotatedSingletonImpl( anything, TestSeco" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnotherAnnotatedSingletonImpl( anything, Te" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotPartialMatch() {
      Assert.assertFalse( parameter.partialMatches( "TestAnotherAnnotatedSingletonImpl( anything, anything" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldCompleteMatch() {
      Assert.assertTrue( parameter.partialMatches( "TestAnotherAnnotatedSingletonImpl( anything, 23 )" ) );
      Assert.assertTrue( parameter.partialMatches( "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton )" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotCompleteMatch() {
      Assert.assertFalse( parameter.completeMatches( "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton" ) );
      Assert.assertFalse( parameter.completeMatches( "TestAnotherAnnotatedSingletonImpl( anything, Te )" ) );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void shouldExtract() {
      //No complications with parameter values.
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void shouldParseParameters() {
    //No complications with parameter values.
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void shouldParse() throws NoSuchMethodException, SecurityException {
    //No complications with parameter values.
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void shouldNotParse() {
    //No complications with parameter values.
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldAutoComplete() {
      Assert.assertEquals( 
               "TestAnnotatedSingletonImpl( 23 )",
               parameter.autoComplete( "TestAnnotatedSingletonImpl( 23" ) 
      );
      Assert.assertEquals( 
               "TestAnotherAnnotatedSingletonImpl( anything, 23.0 )",
               parameter.autoComplete( "TestAnotherAnnotatedSingletonImpl( anything, 23" ) 
      );
      Assert.assertEquals( 
               "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton )",
               parameter.autoComplete( "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton" ) 
      );
      Assert.assertEquals( 
               "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton )",
               parameter.autoComplete( "TestAnotherAnnotatedSingletonImpl( anything, TestSeco" ) 
      );
      Assert.assertEquals( 
               "TestAnotherAnnotatedSingletonImpl( anything, Test",
               parameter.autoComplete( "TestAnotherAnnotatedSingletonImpl( anything, Te" )
      );
      Assert.assertEquals( 
               "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton )",
               parameter.autoComplete( "TestAnotherAnnotatedSingletonImpl( anything, TestS" )
      );
      Assert.assertEquals( 
               "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton )",
               parameter.autoComplete( "TestAnotherAnnotatedSingletonImpl( anything, TestS )" )
      );
      Assert.assertEquals( 
               "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton )",
               parameter.autoComplete( "TestAnotherAnnotatedSingletonImpl( anything, TestSecondSingleton )" )
      );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldNotAutoComplete() {
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Test @Override public void shouldSuggest() {
   }// End Method

}
