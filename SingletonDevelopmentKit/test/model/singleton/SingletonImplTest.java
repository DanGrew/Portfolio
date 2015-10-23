/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.singleton;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import model.data.SerializedSingleton;
import model.data.SingletonSerialization;

/**
 * {@link SingletonImpl} test.
 */
public class SingletonImplTest {
   
   /** Interface for the serializable form of the {@link SpecificSingleton}. **/
   private interface SerializableSpecificSingleton extends SerializedSingleton< SpecificSingleton >{}
   /** Interface for the singleton used to run this test.**/
   private interface SpecificSingleton extends SingletonSerialization< SerializableSpecificSingleton > {}
   
   /**
    * Implementation of the {@link SerializableSpecificSingleton}.
    */
   public static class SerializableSpecificSingletonImpl implements SerializableSpecificSingleton {

      /**
       * {@inheritDoc}
       */
      @Override public String getIdentification() { return null; }

      /**
       * {@inheritDoc}
       */
      @Override public void setIdentification( String identification ) {}

      /**
       * {@inheritDoc}
       */
      @Override public SpecificSingleton unwrap() { return null; }

      /**
       * {@inheritDoc}
       */
      @Override public void resolve() {}
   }//End Class
   
   /**
    * Implementation of the {@link SpecificSingleton}.
    */
   public static class SpecificSingletonImpl extends SingletonImpl< SerializableSpecificSingleton > implements SpecificSingleton{

      /**
       * Constructs a new {@link SpecificSingletonImpl}.
       * @param identification the name of the singleton.
       */
      public SpecificSingletonImpl( String identification ) {
         super( identification );
      }//End Constructor

      /**
       * {@inheritDoc}
       */
      @Override protected void writeSingleton( SerializableSpecificSingleton serializable ) {}

      /**
       * {@inheritDoc}
       */
      @Override protected void readSingleton( SerializableSpecificSingleton serialized ) {}
      
   }//End Class

   /**
    * Test for the identification property.
    */
   @Test public void shouldAccessIdentification() {
      final String TEST_NAME = "anything";
      SpecificSingleton systemUnderTest = new SpecificSingletonImpl( TEST_NAME );
      
      Assert.assertEquals( TEST_NAME, systemUnderTest.getIdentification() );
      
      final String ALTERNATE_TEST_NAME = "something else";
      systemUnderTest.setIdentification( ALTERNATE_TEST_NAME );
      Assert.assertEquals( ALTERNATE_TEST_NAME, systemUnderTest.getIdentification() );
   }//End Method
   
   /**
    * Test to prove the constructor must be given a valid identification.
    */
   @Test( expected = IllegalArgumentException.class ) 
   public void shouldNotAcceptInvalidIdentificationInConstructor(){
      new SpecificSingletonImpl( null );
      Assert.fail( "Did not throw IllegalArgumentException" );
   }//End Method

   /**
    * Test to provide the set will not apply an invalid identification.
    */
   @Test public void shouldNotAcceptInvalidIdentificationSet() {
      final String TEST_NAME = "anything";
      SpecificSingleton systemUnderTest = new SpecificSingletonImpl( TEST_NAME );
      
      Assert.assertEquals( TEST_NAME, systemUnderTest.getIdentification() );
      
      systemUnderTest.setIdentification( null );
      Assert.assertEquals( TEST_NAME, systemUnderTest.getIdentification() );
   }//End Method
   
   /**
    * Test to prove the call through for reading.
    */
   @Test public void shouldCallExtensibleRead(){
      SpecificSingletonImpl systemUnderTest = Mockito.spy( new SpecificSingletonImpl( "anything" ) );
      systemUnderTest.read( new SerializableSpecificSingletonImpl() );
      Mockito.verify( systemUnderTest, Mockito.times( 1 ) ).readSingleton( Mockito.any() );
   }//End Method
   
   /**
    * Test to prove the call through for writing.
    */
   @Test public void shouldCallExtensibleWrite(){
      SpecificSingletonImpl systemUnderTest = Mockito.spy( new SpecificSingletonImpl( "anything" ) );
      systemUnderTest.write( SerializableSpecificSingletonImpl.class );
      Mockito.verify( systemUnderTest, Mockito.times( 1 ) ).writeSingleton( Mockito.any() );
   }//End Method
}//End Class
