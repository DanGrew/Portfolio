/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import org.junit.Assert;
import org.junit.Test;

import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * {@link IdentificationMatcher} test.
 */
public class IdentificationMatcherTest {

   /**
    * {@link IdentificationMatcher#test(model.singleton.Singleton)} test.
    */
   @Test public void shouldMatch() {
      IdentificationMatcher< TestSingleton > sut = new IdentificationMatcher<>( "value" );
      TestSingleton test1 = new TestSingletonImpl( "someValue" );
      Assert.assertFalse( sut.test( test1 ) );
      TestSingleton test2 = new TestSingletonImpl( "somevalue" );
      Assert.assertFalse( sut.test( test2 ) );
      TestSingleton test3 = new TestSingletonImpl( "something else" );
      Assert.assertFalse( sut.test( test3 ) );
      TestSingleton test4 = new TestSingletonImpl( "value" );
      Assert.assertTrue( sut.test( test4 ) );
   }//End Method

}//End Class
