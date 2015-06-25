/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package redirect;

import java.lang.reflect.InvocationTargetException;

import model.singleton.Singleton;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import architecture.request.RequestSystem;

/**
 * Test for the {@link ParameterRedirect}.
 */
public class ParameterRedirectTest {

   private static final String SINGLETON = "singleton";
   private static Singleton SINGLETON_OBJECT;
   
   /** Test class defining some methods to invoke.**/
   public static class TestClass {
      public void number( Double value ){}
      public void string( String value ){}
      public void singleton( Singleton value ){}
      public void multiple( Double valueA, String valueB, Singleton valueC ){}
   }// End Class
   
   /**
    * Method to setup data for the test.
    */
   @BeforeClass public static void setup(){
      SINGLETON_OBJECT = Mockito.mock( Singleton.class );
      Mockito.when( SINGLETON_OBJECT.getIdentification() ).thenReturn( SINGLETON );
      RequestSystem.store( SINGLETON_OBJECT, Singleton.class );
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
      ParameterRedirect redirect = new ParameterRedirect();
        
      final Double testValue = 22.45;
      final String testRepresentation = testValue.toString();
      TestClass testClass = Mockito.mock( TestClass.class );
      redirect.invoke( 
               testClass, 
               TestClass.class.getMethod( "number", Double.class ),
               testRepresentation
      );
      
      Mockito.verify( testClass ).number( testValue );
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
      ParameterRedirect redirect = new ParameterRedirect();
      
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
   @Test public void shouldRedirectSingeltons() throws IllegalAccessException, 
                                                       IllegalArgumentException, 
                                                       InvocationTargetException, 
                                                       NoSuchMethodException, 
                                                       SecurityException 
   {
      ParameterRedirect redirect = new ParameterRedirect();
      
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
    * Test to prove the redirecting of multiple parameters.
    */
   @Test public void shouldRedirectMultiple() throws IllegalAccessException, 
                                                     IllegalArgumentException, 
                                                     InvocationTargetException, 
                                                     NoSuchMethodException, 
                                                     SecurityException 
   {
      ParameterRedirect redirect = new ParameterRedirect();
      
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

}// End Class
