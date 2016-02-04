/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package represenation.xml.wrapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.request.RequestSystem;
import representation.xml.wrapper.SingletonCollectionWrapper;
import test.model.TestObjects.SerializableTestSingleton;
import test.model.TestObjects.SerializableTestSingletonImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;
import utility.TestCommon;

/**
 * {@link SingletonCollectionWrapper} test.
 */
public class SingletonCollectionWrapperTest {

   /**
    * Specific {@link SingletonCollectionWrapper} for {@link TestSingleton} for testing.
    */
   private static class TestSingletongCollectionWrapper extends SingletonCollectionWrapper< TestSingleton, SerializableTestSingleton > {

      /**
       * Constructs a new {@link TestSingletongCollectionWrapper}.
       * @param iterator the {@link Iterator} of {@link TestSingleton}s.
       */
      public TestSingletongCollectionWrapper( Iterator< TestSingleton > iterator ) {
         super( iterator );
      }//End Constructor
      
      /**
       * {@inheritDoc}
       */
      @Override public void addUnwrapped( TestSingleton object ) {
         super.addObject( object.write( SerializableTestSingletonImpl.class ) );
      }//End Method

      /**
       * {@inheritDoc}
       */
      @Override public List< TestSingleton > retrieveSingletons() {
         return super.retrieveSingletons( TestSingleton.class );
      }//End Method
      
   }//End Class
   
   /**
    * Initialises the {@link Singleton}s for the test.
    */
   @BeforeClass public static void initialiseSingletons(){
      RequestSystem.reset();
   }//End Method
   
   /**
    * {@link SingletonCollectionWrapper} constructor and {@link SingletonCollectionWrapper#iterator()} test.
    */
   @Test public void shouldConstructAndIterate() {
      List< TestSingleton > singletons = Arrays.asList( 
               new TestSingletonImpl( "anything" ),
               new TestSingletonImpl( "something" ),
               new TestSingletonImpl( "nothing" )
      );
      SingletonCollectionWrapper< TestSingleton, SerializableTestSingleton > wrapper = new TestSingletongCollectionWrapper( 
               singletons.iterator() );
      Iterator< SerializableTestSingleton > iterator = wrapper.iterator();
      Assert.assertTrue( iterator.hasNext() );
      Assert.assertEquals( "anything", iterator.next().getIdentification() );
      Assert.assertTrue( iterator.hasNext() );
      Assert.assertEquals( "something", iterator.next().getIdentification() );
      Assert.assertTrue( iterator.hasNext() );
      Assert.assertEquals( "nothing", iterator.next().getIdentification() );
      Assert.assertFalse( iterator.hasNext() );
   }//End Method
   
   /**
    * {@link SingletonCollectionWrapper#addAll(Iterator)} test.
    */
   @Test public void shouldAddAndIterate() {
      SingletonCollectionWrapper< TestSingleton, SerializableTestSingleton > wrapper = new TestSingletongCollectionWrapper( 
               Collections.emptyIterator() );
      List< SerializableTestSingleton > singletons = Arrays.asList( 
               new SerializableTestSingletonImpl( "anything" ),
               new SerializableTestSingletonImpl( "something" ),
               new SerializableTestSingletonImpl( "nothing" )
      );
      wrapper.addAll( singletons );
      Iterator< SerializableTestSingleton > iterator = wrapper.iterator();
      Assert.assertTrue( iterator.hasNext() );
      Assert.assertEquals( "anything", iterator.next().getIdentification() );
      Assert.assertTrue( iterator.hasNext() );
      Assert.assertEquals( "something", iterator.next().getIdentification() );
      Assert.assertTrue( iterator.hasNext() );
      Assert.assertEquals( "nothing", iterator.next().getIdentification() );
      Assert.assertFalse( iterator.hasNext() );
   }//End Method
   
   /**
    * {@link SingletonCollectionWrapper#constructSingletons()} and {@link SingletonCollectionWrapper#resolveSingletons()} test.
    */
   @Test public void shouldConstructAndRetrieveSingletons() {
      List< TestSingleton > singletons = Arrays.asList( 
               new TestSingletonImpl( "anything" ),
               new TestSingletonImpl( "something" ),
               new TestSingletonImpl( "nothing" )
      );
      SingletonCollectionWrapper< TestSingleton, SerializableTestSingleton > wrapper = new TestSingletongCollectionWrapper( 
               singletons.iterator() );
      
      for ( TestSingleton singleton : singletons ) {
         Assert.assertFalse( RequestSystem.contains( TestSingleton.class, singleton.getIdentification() ) );
      }
      wrapper.constructSingletons();
      for ( TestSingleton singleton : singletons ) {
         Assert.assertTrue( RequestSystem.contains( TestSingleton.class, singleton.getIdentification() ) );
      }
      
      List< TestSingleton > constructed = wrapper.retrieveSingletons();
      Assert.assertNotEquals( singletons, constructed );
      TestCommon.assertIterators( 
               singletons.iterator(), 
               constructed.iterator(), 
               ( TestSingleton a, TestSingleton b ) -> { return a.getIdentification().equals( b.getIdentification() ); } 
      );
   }//End Method
}//End Class
