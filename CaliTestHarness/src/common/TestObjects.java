/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package common;

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
      public TestAnnotatedSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor
      
      @Override protected void writeSingleton( TestAnnotatedSingleton serializable ) {}
      @Override protected void readSingleton( TestAnnotatedSingleton serialized ) {}

      @Override public TestAnnotatedSingletonImpl unwrap() {
         return null;
      }

      @Override public void resolve() {}
      
   }// End Class
   
   /** Another class of testable {@link Singleton}. */
   public static class TestAnotherAnnotatedSingletonImpl extends TestAnnotatedSingletonImpl {

      /**
       * Constructs a new {@link TestAnotherAnnotatedSingletonImpl}.
       * @param identification the name.
       * @param anotherValue another value.
       */
      public TestAnotherAnnotatedSingletonImpl( String identification, String anotherValue ) {
         super( identification );
      }// End Constructor
   }// End Class
}
