package test.model;

import model.data.SerializedSingleton;
import model.singleton.Singleton;
import model.singleton.SingletonImpl;

/**
 * {@link TestObjects} provides objects used for testing {@link Singleton}s.
 */
public class TestObjects {

   /** Interface for a testable {@link Singleton}.**/
   public static interface TestSingleton extends Singleton, SerializedSingleton< TestSingletonImpl >{}
   
   /** Implementation for a testable {@link Singleton}.**/
   public static class TestSingletonImpl extends SingletonImpl< TestSingleton > implements TestSingleton {

      /**
       * Constructs a new {@link TestSingletonImpl}.
       * @param identification the name.
       */
      public TestSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor

      @Override protected void writeSingleton( TestSingleton serializable ) {}
      @Override protected void readSingleton( TestSingleton serialized ) {}

      @Override public TestSingletonImpl unwrap() {
         return null;
      }

      @Override public void resolve() {}
      
   }// End Class
   
   /** Another class of testable {@link Singleton}. */
   public static class AnotherTestSingletonImpl extends TestSingletonImpl {

      /**
       * Constructs a new {@link AnotherTestSingletonImpl}.
       * @param identification the name.
       */
      public AnotherTestSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor
   }// End Class
}
