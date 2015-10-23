/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * {@link UnmodifiableIterator} test.
 */
public class UnmodifiableIteratorTest {

   /**
    * {@link UnmodifiableIterator#next()} and {@link UnmodifiableIterator#hasNext()} test.
    */
   @Test public void shouldForwardCalls() {
      Iterator< ? > iterator = Mockito.mock( Iterator.class );
      UnmodifiableIterator< ? > sut = new UnmodifiableIterator<>( iterator );
      Mockito.verifyZeroInteractions( iterator );
      
      Mockito.verify( iterator, Mockito.times( 0 ) ).next();
      Mockito.verify( iterator, Mockito.times( 0 ) ).hasNext();
      
      sut.next();
      Mockito.verify( iterator, Mockito.times( 1 ) ).next();
      sut.hasNext();
      Mockito.verify( iterator, Mockito.times( 1 ) ).hasNext();
      
      sut.next();
      sut.next();
      sut.next();
      Mockito.verify( iterator, Mockito.times( 4 ) ).next();
      sut.hasNext();
      sut.hasNext();
      sut.hasNext();
      Mockito.verify( iterator, Mockito.times( 4 ) ).hasNext();
   }//End Method
   
   /**
    * {@link UnmodifiableIterator#remove()} exception throwing test.
    */
   @Test( expected = UnsupportedOperationException.class )
   public void shouldNotRemove() {
      Iterator< ? > iterator = Mockito.mock( Iterator.class );
      UnmodifiableIterator< ? > sut = new UnmodifiableIterator<>( iterator );
      Mockito.verifyZeroInteractions( iterator );
      
      Mockito.verify( iterator, Mockito.times( 0 ) ).remove();
      
      sut.remove();
      Assert.fail( "Should have thrown exception passing the test." );
   }//End Method

}//End Class
