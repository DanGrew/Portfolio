/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

import model.singleton.Singleton;
import test.model.TestObjects.TestSingletonImpl;


/**
 * Test for the {@link GraphSort}.
 */
public class ComparatorsTest {

   /**
    * {@link Comparators#compare(String, String)} test.
    */
   @Test public void shouldStringCompare(){
      Assert.assertEquals( 0, Comparators.compare( "me", "me" ) );
      Assert.assertEquals( 0, Comparators.compare( "something really long", "something really long" ) );
      Assert.assertEquals( 0, Comparators.compare( ( String )null, ( String )null ) );
      
      Assert.assertTrue( Comparators.compare( "me", "you" ) < 0 );
      Assert.assertTrue( Comparators.compare( null, "you" ) < 0 );
      
      Assert.assertTrue( Comparators.compare( "you", "me" ) > 0 );
      Assert.assertTrue( Comparators.compare( "you", null ) > 0 );
   }// End Method
   
   /**
    * {@link Comparators#compare(Double, Double)} test.
    */
   @Test public void shouldNumberCompare(){
      Assert.assertEquals( 0, Comparators.compare( "2345", "2345" ) );
      Assert.assertEquals( 0, Comparators.compare( "0.84953847", "0.84953847" ) );
      Assert.assertEquals( 0, Comparators.compare( ( Double )null, ( Double )null ) );
      
      Assert.assertTrue( Comparators.compare( "5", "8" ) < 0 );
      Assert.assertTrue( Comparators.compare( null, "8" ) < 0 );
      
      Assert.assertTrue( Comparators.compare( "56", "-34" ) > 0 );
      Assert.assertTrue( Comparators.compare( "56", null ) > 0 );
   }// End Method
   
   /**
    * Prove that {@link Comparators#stringExtractionComparater(java.util.function.Function)} works.
    */
   @Test public void shouldConstructComparatorForSingletonStringExtraction(){
      Comparator< Singleton > comparator = Comparators.stringExtractionComparater( singleton -> { return singleton.getIdentification(); } );
      
      Singleton first = new TestSingletonImpl( "first" );
      Singleton second = new TestSingletonImpl( "second" );
      Singleton last = new TestSingletonImpl( "xlast" );
      
      Assert.assertTrue( comparator.compare( first, second ) < 0 );
      Assert.assertTrue( comparator.compare( second, last ) < 0 );
      Assert.assertTrue( comparator.compare( last, second ) > 0 );
      Assert.assertTrue( comparator.compare( second, second ) == 0 );
   }//End Method
   
}// End Class
