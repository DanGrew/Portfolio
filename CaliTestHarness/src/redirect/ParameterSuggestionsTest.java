/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package redirect;

import java.util.ArrayList;
import java.util.Arrays;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.ReferenceClassParameterTypeImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

import common.TestObjects.TestAnnotatedSingletonImpl;
import common.TestObjects.TestAnotherAnnotatedSingletonImpl;
import common.TestObjects.TestDoubleParameterAnnotatedSingletonImpl;

/**
 * Test for the {@link ParameterSuggestions}.
 */
public class ParameterSuggestionsTest {
   
   private static final String SINGLETON = "singleton";
   private static Singleton SINGLETON_OBJECT;
   
   private static final String TEST_SINGLETON = "testSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   
   private static final String TEST_SINGLETON_2 = "testSingleton2";
   private static TestSingleton TEST_SINGLETON_OBJECT_2;
   
   private static final String TEST_SINGLETON_3 = "testSingleton3";
   private static TestAnotherAnnotatedSingletonImpl TEST_SINGLETON_OBJECT_3;
   
   /**
    * Method to setup data for the test.
    */
   @BeforeClass public static void setup(){
      SINGLETON_OBJECT = new TestSingletonImpl( SINGLETON );
      RequestSystem.store( SINGLETON_OBJECT, Singleton.class );
      
      TEST_SINGLETON_OBJECT = new TestSingletonImpl( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, Singleton.class );
      
      TEST_SINGLETON_OBJECT_2 = new TestSingletonImpl( TEST_SINGLETON_2 );
      RequestSystem.store( TEST_SINGLETON_OBJECT_2, Singleton.class );
      
      TEST_SINGLETON_OBJECT_3 = new TestAnotherAnnotatedSingletonImpl( TEST_SINGLETON_3 );
      RequestSystem.store( TEST_SINGLETON_OBJECT_3 );
      
      ClassParameterTypes.addParameterTypes( Arrays.asList( 
               new ReferenceClassParameterTypeImpl<>( TestSingleton.class ),
               new ReferenceClassParameterTypeImpl<>( Singleton.class )
      ) );
   }// End Method

   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link String}s.
    */
   @Test public void shouldIdentifyRedirectionsForString(){
      Assert.assertEquals( 
               Arrays.asList( "anything" ), 
               ParameterSuggestions.identifyRedirectionsFor( String.class, "anything" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "23" ), 
               ParameterSuggestions.identifyRedirectionsFor( String.class, "23" ) 
      );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link String}s.
    */
   @Test public void shouldNotIdentifyRedirectionsForString(){
      Assert.assertEquals( new ArrayList<>(), ParameterSuggestions.identifyRedirectionsFor( String.class, null ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link Number}s.
    */
   @Test public void shouldIdentifyRedirectionsForNumber(){
      Assert.assertEquals( 
               Arrays.asList( "234.0" ), 
               ParameterSuggestions.identifyRedirectionsFor( Number.class, 234 ) 
      );
      Assert.assertEquals( 
               Arrays.asList( "0.0034" ), 
               ParameterSuggestions.identifyRedirectionsFor( Number.class, 0.0034 ) 
      );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link Number}s.
    */
   @Test public void shouldNotIdentifyRedirectionsForNumber(){
      Assert.assertEquals( new ArrayList<>(), ParameterSuggestions.identifyRedirectionsFor( Number.class, "anything" ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link TestSingleton}s.
    */
   @Test public void shouldIdentifyRedirectionsForTestSingleton(){
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON, TEST_SINGLETON_2 ), 
               ParameterSuggestions.identifyRedirectionsFor( TestSingleton.class, TEST_SINGLETON ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON, TEST_SINGLETON_2 ), 
               ParameterSuggestions.identifyRedirectionsFor( TestSingleton.class, "tes" ) 
       );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link TestSingleton}s.
    */
   @Test public void shouldNotRedirectIdentifyRedirectionsForTestSingleton(){
      Assert.assertEquals( new ArrayList<>(), ParameterSuggestions.identifyRedirectionsFor( TestSingleton.class, "anything" ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link Singleton}s.
    */
   @Test public void shouldIdentifyRedirectionsForSingleton(){
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON, TEST_SINGLETON_2 ), 
               ParameterSuggestions.identifyRedirectionsFor( Singleton.class, TEST_SINGLETON ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON, TEST_SINGLETON_2 ), 
               ParameterSuggestions.identifyRedirectionsFor( Singleton.class, "tes" ) 
      );
      Assert.assertEquals( 
               Arrays.asList( TEST_SINGLETON_2 ), 
               ParameterSuggestions.identifyRedirectionsFor( Singleton.class, TEST_SINGLETON_2 ) 
      );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#matchesSignature(java.lang.reflect.Executable, Object...)}.
    */
   @Test public void shouldMatchSignature() throws NoSuchMethodException, SecurityException {
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestSingletonImpl.class.getConstructor( String.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestAnnotatedSingletonImpl.class.getConstructor( String.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything", 234.4
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               234.4
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, TestAnotherAnnotatedSingletonImpl.class ),
               "anything", TEST_SINGLETON_OBJECT_2
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestDoubleParameterAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything"
      ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#matchesSignature(java.lang.reflect.Executable, Object...)}.
    */
   @Test public void shouldNotMatchSignature() throws NoSuchMethodException, SecurityException {
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               234.4
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestDoubleParameterAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, TestAnotherAnnotatedSingletonImpl.class ),
               "anything", "test"
      ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link String}s.
    */
   @Test public void shouldIdentifyExactMatchForString(){
      Assert.assertEquals( 
               "anything", 
               ParameterSuggestions.identifyExactMatchFor( String.class, "anything" ) 
      );
      Assert.assertEquals( 
               "23", 
               ParameterSuggestions.identifyExactMatchFor( String.class, "23" ) 
      );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link String}s.
    */
   @Test public void shouldNotIdentifyExactMatchForString(){
      Assert.assertNull( ParameterSuggestions.identifyExactMatchFor( String.class, null ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link Number}s.
    */
   @Test public void shouldIdentifyExactMatchForNumber(){
      Assert.assertEquals( 
               234.0, 
               ParameterSuggestions.identifyExactMatchFor( Number.class, 234 ) 
      );
      Assert.assertEquals( 
               0.0034, 
               ParameterSuggestions.identifyExactMatchFor( Number.class, 0.0034 ) 
      );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link Number}s.
    */
   @Test public void shouldNotIdentifyExactMatchForNumber(){
      Assert.assertNull( ParameterSuggestions.identifyExactMatchFor( Number.class, "anything" ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link TestSingleton}s.
    */
   @Test public void shouldIdentifyExactMatchForTestSingleton(){
      Assert.assertEquals( 
               TEST_SINGLETON_OBJECT, 
               ParameterSuggestions.identifyExactMatchFor( TestSingleton.class, TEST_SINGLETON ) 
      );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link TestSingleton}s.
    */
   @Test public void shouldNotIdentifyExactMatchForTestSingleton(){
      Assert.assertNull( ParameterSuggestions.identifyExactMatchFor( TestSingleton.class, "anything" ) );
      Assert.assertNull( ParameterSuggestions.identifyExactMatchFor( TestSingleton.class, "tes" ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#identifyRedirectionsFor(Class, Object)} for {@link Singleton}s.
    */
   @Test public void shouldIdentifyExactMatchForSingleton(){
      Assert.assertEquals( 
               TEST_SINGLETON_OBJECT, 
               ParameterSuggestions.identifyExactMatchFor( Singleton.class, TEST_SINGLETON ) 
      );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#matchesSignature(java.lang.reflect.Executable, Object...)}.
    */
   @Test public void shouldExactMatchSignature() throws NoSuchMethodException, SecurityException {
      Assert.assertTrue( ParameterSuggestions.matchesExactSignature(
               TestSingletonImpl.class.getConstructor( String.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesExactSignature(
               TestAnnotatedSingletonImpl.class.getConstructor( String.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything", 234.4
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, TestAnotherAnnotatedSingletonImpl.class ),
               "anything", TEST_SINGLETON_OBJECT_2
      ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#matchesSignature(java.lang.reflect.Executable, Object...)}.
    */
   @Test public void shouldNotExactMatchMatchSignature() throws NoSuchMethodException, SecurityException {
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything"
      ) );
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               234.4
      ) );
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestDoubleParameterAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything"
      ) );
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything", "something"
      ) );
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, TestAnotherAnnotatedSingletonImpl.class ),
               "anything", "anything"
      ) );
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestDoubleParameterAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything", TEST_SINGLETON_3
      ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#matchesExactSignature(java.lang.reflect.Executable, Object...)}.
    */
   @Test public void shouldMatchExactSignature() throws NoSuchMethodException, SecurityException {
      Assert.assertTrue( ParameterSuggestions.matchesExactSignature(
               TestSingletonImpl.class.getConstructor( String.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesExactSignature(
               TestAnnotatedSingletonImpl.class.getConstructor( String.class ),
               "anything"
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything", 234.4
      ) );
      Assert.assertTrue( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, TestAnotherAnnotatedSingletonImpl.class ),
               "anything", TEST_SINGLETON_OBJECT_2
      ) );
   }// End Method
   
   /**
    * {@link ParameterSuggestions#matchesExactSignature(java.lang.reflect.Executable, Object...)}.
    */
   @Test public void shouldNotMatchExactSignature() throws NoSuchMethodException, SecurityException {
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything"
      ) );
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               234.4
      ) );
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestDoubleParameterAnnotatedSingletonImpl.class.getConstructor( String.class, Double.class ),
               "anything"
      ) );
      Assert.assertFalse( ParameterSuggestions.matchesExactSignature(
               TestAnotherAnnotatedSingletonImpl.class.getConstructor( String.class, TestAnotherAnnotatedSingletonImpl.class ),
               "anything", "test"
      ) );
   }// End Method

}// End Class
