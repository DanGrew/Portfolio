/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package redirect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import redirect.ParameterRedirectTest.TestClass.TestEnum;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import architecture.request.RequestSystem;

/**
 * Test for the {@link ParameterRedirect}.
 */
public class ParameterRedirectTest {

   private static final String SINGLETON = "singleton";
   private static Singleton SINGLETON_OBJECT;
   
   private static final String TEST_SINGLETON = "testSingleton";
   private static TestSingleton TEST_SINGLETON_OBJECT;
   
   private static final ParameterRedirect redirect = new ParameterRedirect();
   
   /** Test class defining some methods to invoke.**/
   public static class TestClass {
      public enum TestEnum { Value }
      
      public TestClass( Double value, String string, Singleton singleton ){}
      public void doubleValue( Double value ){}
      public void integerValue( Integer value ){}
      public void string( String value ){}
      public void singleton( Singleton value ){}
      public void specificSingleton( TestSingleton singleton ){}
      public void multiple( Double valueA, String valueB, Singleton valueC ){}
      public void enumMethod( TestEnum value ){}
   }// End Class
   
   /**
    * Method to setup data for the test.
    */
   @BeforeClass public static void setup(){
      SINGLETON_OBJECT = Mockito.mock( Singleton.class );
      Mockito.when( SINGLETON_OBJECT.getIdentification() ).thenReturn( SINGLETON );
      RequestSystem.store( SINGLETON_OBJECT, Singleton.class );
      
      TEST_SINGLETON_OBJECT = Mockito.mock( TestSingletonImpl.class );
      Mockito.when( TEST_SINGLETON_OBJECT.getIdentification() ).thenReturn( TEST_SINGLETON );
      RequestSystem.store( TEST_SINGLETON_OBJECT, TestSingleton.class );
   }// End Method
   
   /**
    * Test to prove the redirecting of {@link Double} parameters.
    */
   @Test public void shouldRedirectDoubles() throws NoSuchMethodException, 
                                                    SecurityException, 
                                                    IllegalAccessException, 
                                                    IllegalArgumentException, 
                                                    InvocationTargetException 
   {
      final Double testValue = 22.45;
      final String testRepresentation = testValue.toString();
      TestClass testClass = Mockito.mock( TestClass.class );
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "doubleValue", Double.class ),
               testRepresentation
      );
      
      Mockito.verify( testClass ).doubleValue( testValue );
      Mockito.verifyNoMoreInteractions( testClass );
   }// End Method
   
   /**
    * Test to prove the redirecting of {@link Integer} parameters.
    */
   @Test public void shouldRedirectIntegers() throws NoSuchMethodException, 
                                                    SecurityException, 
                                                    IllegalAccessException, 
                                                    IllegalArgumentException, 
                                                    InvocationTargetException 
   {
      final Integer testValue = 22;
      final String testRepresentation = testValue.toString();
      TestClass testClass = Mockito.mock( TestClass.class );
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "integerValue", Integer.class ),
               testRepresentation
      );
      
      Mockito.verify( testClass ).integerValue( testValue );
      Mockito.verifyNoMoreInteractions( testClass );
   }// End Method
   
   /**
    * Test to prove the redirecting of {@link String} parameters.
    */
   @Test public void shouldRedirectStrings() throws IllegalAccessException, 
                                                    IllegalArgumentException, 
                                                    InvocationTargetException, 
                                                    NoSuchMethodException, 
                                                    SecurityException  
   {
      final String testValue = "anything";
      TestClass testClass = Mockito.mock( TestClass.class );
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "string", String.class ),
               testValue
      );
      
      Mockito.verify( testClass ).string( testValue );
      Mockito.verifyNoMoreInteractions( testClass );
   }// End Method
   
   /**
    * Test to prove the redirecting of {@link Singleton} parameters.
    */
   @Test public void shouldRedirectSingletons() throws IllegalAccessException, 
                                                       IllegalArgumentException, 
                                                       InvocationTargetException, 
                                                       NoSuchMethodException, 
                                                       SecurityException 
   {
      final Singleton testValue = SINGLETON_OBJECT;
      final String testRepresentation = testValue.getIdentification();
      TestClass testClass = Mockito.mock( TestClass.class );
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "singleton", Singleton.class ),
               testRepresentation
      );
      
      Mockito.verify( testClass ).singleton( testValue );
      Mockito.verifyNoMoreInteractions( testClass );
   }// End Method
   
   /**
    * Test to prove the redirecting of {@link Singleton} when the class is more specific that
    * {@link Singleton}.
    */
   @Test public void shouldRedirectSpecificSingletons() throws IllegalAccessException, 
                                                       IllegalArgumentException, 
                                                       InvocationTargetException, 
                                                       NoSuchMethodException, 
                                                       SecurityException 
   {
      final TestSingleton testValue = TEST_SINGLETON_OBJECT;
      final String testRepresentation = testValue.getIdentification();
      TestClass testClass = Mockito.mock( TestClass.class );
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "specificSingleton", TestSingleton.class ),
               testRepresentation
      );
      
      Mockito.verify( testClass ).specificSingleton( testValue );
      Mockito.verifyNoMoreInteractions( testClass );
   }// End Method
   
   /**
    * Test to prove the redirecting of multiple parameters.
    */
   @Test public void shouldRedirectMultiple() throws IllegalAccessException, 
                                                     IllegalArgumentException, 
                                                     InvocationTargetException, 
                                                     NoSuchMethodException, 
                                                     SecurityException 
   {
      final Double testNumber = 22.45;
      final String testNumberRepresentation = testNumber.toString();
      final String testString = "anything";
      final Singleton testSingleton = SINGLETON_OBJECT;
      final String testSingletonRepresentation = testSingleton.getIdentification();
      
      TestClass testClass = Mockito.mock( TestClass.class );
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "multiple", Double.class, String.class, Singleton.class ),
               testNumberRepresentation, testString, testSingletonRepresentation
      );
      
      Mockito.verify( testClass ).multiple( testNumber, testString, testSingleton );
      Mockito.verifyNoMoreInteractions( testClass );
   }// End Method
   
   /**
    * Test to prove the redirecting of {@link Constructor} parameters.
    */
   @Test public void shouldRedirectConstructor() throws NoSuchMethodException, 
                                                        SecurityException, 
                                                        InstantiationException, 
                                                        IllegalAccessException, 
                                                        IllegalArgumentException, 
                                                        InvocationTargetException 
   {
      final Double testNumber = 22.45;
      final String testNumberRepresentation = testNumber.toString();
      final String testString = "anything";
      final Singleton testSingleton = SINGLETON_OBJECT;
      final String testSingletonRepresentation = testSingleton.getIdentification();
      
      Object constructed = redirect.construct( 
               TestClass.class.getConstructor( Double.class, String.class, Singleton.class ),
               testNumberRepresentation, testString, testSingletonRepresentation
      );
      
      Assert.assertNotNull( constructed );
      Assert.assertEquals( TestClass.class, constructed.getClass() );
   }// End Method
   
   /**
    * Test to prove the redirecting of {@link Enum} parameters.
    */
   @Test public void shouldRedirectEnums() throws IllegalAccessException, 
                                                  IllegalArgumentException, 
                                                  InvocationTargetException, 
                                                  NoSuchMethodException, 
                                                  SecurityException  
   {
      TestClass testClass = Mockito.mock( TestClass.class );
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "enumMethod", TestEnum.class ),
               "Value"
      );
      
      Mockito.verify( testClass ).enumMethod( TestEnum.Value );
      Mockito.verifyNoMoreInteractions( testClass );
      
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "enumMethod", TestEnum.class ),
               "something"
      );
      Mockito.verifyNoMoreInteractions( testClass );
   }// End Method
   
}// End Class
