package test.model;

import architecture.request.RequestSystem;
import model.data.SerializedSingleton;
import model.data.SingletonSerialization;
import model.singleton.SingletonImpl;

/**
 * {@link TestObjects} provides objects used for testing {@link Singleton}s.
 */
public class TestObjects {
   
   /** Serializable interface for the {@link TestSingleton}.**/
   public static interface SerializableTestSingleton extends SerializedSingleton< TestSingleton > {}
   
   /** Basic implementation of the {@link SerializableTestSingleton}. **/
   public static class SerializableTestSingletonImpl implements SerializableTestSingleton {

      private String identification;
      
      /** Basic constructor for JaxB. **/
      public SerializableTestSingletonImpl() {}
      
      /**
       * Constructs a new {@link SerializableTestSingletonImpl}.
       * @param identification the identification of the {@link TestSingleton}.
       */
      public SerializableTestSingletonImpl( String identification ) {
         setIdentification( identification );
      }//End Constructor
      
      /**
       * {@inheritDoc}
       */
      @Override public String getIdentification() {
         return identification;
      }//End Method

      /**
       * {@inheritDoc}
       */
      @Override public void setIdentification( String identification ) {
         this.identification = identification;
      }//End Method

      /**
       * {@inheritDoc}
       */
      @Override public TestSingleton unwrap() {
         TestSingleton type = RequestSystem.retrieve( TestSingleton.class, getIdentification() ); 
         if ( type == null ) {
            type = new TestSingletonImpl( getIdentification() );
            RequestSystem.store( type, TestSingleton.class );
         }
         return type;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void resolve() {
         TestSingleton type = RequestSystem.retrieve( TestSingleton.class, getIdentification() ); 
         if ( type == null ) {
            throw new IllegalStateException( "TestSingleton must exist at this point." );
         } else {
            type.read( this );
         }
      }// End Method
      
   }//End Class

   /** Interface for a testable {@link Singleton}.**/
   public static interface TestSingleton extends SingletonSerialization< SerializableTestSingleton >{}
   
   /** Implementation for a testable {@link Singleton}.**/
   public static class TestSingletonImpl extends SingletonImpl< SerializableTestSingleton > implements TestSingleton {

      /**
       * Constructs a new {@link TestSingletonImpl}.
       * @param identification the name.
       */
      public TestSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor

      @Override protected void writeSingleton( SerializableTestSingleton serializable ) {}
      @Override protected void readSingleton( SerializableTestSingleton serialized ) {}

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
   
   /** Alternate version class of testable {@link Singleton} with clashing name. */
   public static class TestAnotherSingletonImpl extends TestSingletonImpl {

      /**
       * Constructs a new {@link TestAnotherSingletonImpl}.
       * @param identification the name.
       */
      public TestAnotherSingletonImpl( String identification ) {
         super( identification );
      }// End Constructor
   }// End Class
}
