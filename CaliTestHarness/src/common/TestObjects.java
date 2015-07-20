/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package common;

import java.lang.reflect.Method;

import annotation.Cali;
import model.data.SerializedSingleton;
import model.singleton.Singleton;
import model.singleton.SingletonImpl;

/**
 * {@link TestObjects} provides objects used for testing {@link Singleton}s.
 */
public class TestObjects {

   /** Interface for a testable {@link Singleton}.**/
   @Cali
   public static interface TestAnnotatedSingleton extends Singleton, SerializedSingleton< TestAnnotatedSingletonImpl >{}
   
   /** Implementation for a testable {@link Singleton}.**/
   @Cali
   public static class TestAnnotatedSingletonImpl extends SingletonImpl< TestAnnotatedSingleton > implements TestAnnotatedSingleton {

      /**
       * Constructs a new {@link TestAnnotatedSingletonImpl}.
       * @param identification the name.
       */
      @Cali public TestAnnotatedSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor
      
      public TestAnnotatedSingletonImpl( String identification, String anotherValue ) {
         super( identification );
      }// End Constructor
      
      /** Test {@link Method} for {@link Cali}.**/
      @Cali public void testCaliMethod( String anything ){}
      
      /** Test {@link Method} overloaded for {@link Cali}.**/
      @Cali public void overloaded( String anything ){}
      
      /** Test {@link Method} overloaded for {@link Cali}.**/
      @Cali public void overloaded( String anything, String twice ){}
      
      /** Test {@link Method} overloaded for {@link Cali}, deliberately not {@link Cali}.**/
      public void nonCaliMethod( String anything ){}
      
      @Override protected void writeSingleton( TestAnnotatedSingleton serializable ) {}
      @Override protected void readSingleton( TestAnnotatedSingleton serialized ) {}

      @Override public TestAnnotatedSingletonImpl unwrap() {
         return null;
      }

      @Override public void resolve() {}

      /**
       * {@inheritDoc}
       */
      @Override public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ( ( identification == null ) ? 0 : identification.hashCode() );
         return result;
      }// End Method

      /**
       * {@inheritDoc}
       */
      @Override public boolean equals( Object obj ) {
         if ( this == obj ) {
            return true;
         }
         if ( obj == null ){
            return false;
         }
         if ( getClass() != obj.getClass() ){
            return false;
         }
         TestAnnotatedSingletonImpl other = ( TestAnnotatedSingletonImpl ) obj;
         if ( identification == null ) {
            if ( other.identification != null ){
               return false;
            }
         } else if ( !identification.equals( other.identification ) ){
            return false;
         }
         return true;
      }// End Method
      
   }// End Class
   
   /** Another class of testable {@link Singleton}. */
   public static class TestAnotherAnnotatedSingletonImpl extends TestAnnotatedSingletonImpl {

      /**
       * Constructs a new {@link TestAnotherAnnotatedSingletonImpl}.
       * @param identification the name.
       */
      @Cali public TestAnotherAnnotatedSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor
      
      /**
       * Constructs a new {@link TestAnotherAnnotatedSingletonImpl}.
       * @param identification the name.
       * @param anotherValue another value.
       */
      @Cali public TestAnotherAnnotatedSingletonImpl( String identification, Double anotherValue ) {
         super( identification );
      }// End Constructor
   }// End Class
}
