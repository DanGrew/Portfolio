/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link TestCommon} test.
 */
public class TestCommonTest {

   /**
    * {@link TestCommon#assertCollectionsSameOrderIrrelevant(java.util.Collection, java.util.Collection)} acceptance test.
    */
   @Test public void shouldAssertCollectionsSameOrderIndependent() {
      List< String > a = Arrays.asList( "anything", "something", "nothing" );
      List< String > b = Arrays.asList( "anything", "something", "nothing" );
      TestCommon.assertCollectionsSameOrderIrrelevant( a, b );
      
      a = Arrays.asList( "anything", "anything", "nothing" );
      b = Arrays.asList( "anything", "nothing", "anything" );
      TestCommon.assertCollectionsSameOrderIrrelevant( a, b );
   }//End Method
   
   /**
    * {@link TestCommon#assertCollectionsSameOrderIrrelevant(java.util.Collection, java.util.Collection)} reject test.
    */
   @Test( expected = AssertionError.class ) 
   public void shouldFailAssertCollectionsSameOrderIndependent() {
      List< String > a = Arrays.asList( "anything", "something", "somethingElse" );
      List< String > b = Arrays.asList( "anything", "something", "nothing" );
      TestCommon.assertCollectionsSameOrderIrrelevant( a, b );
   }//End Method
   
   /**
    * {@link TestCommon#assertIterators(java.util.Iterator, java.util.Iterator)} acceptance test.
    */
   @Test public void shouldAssertIteratorsEquality(){
      List< String > a = Arrays.asList( "anything", "something", "nothing" );
      List< String > b = Arrays.asList( "anything", "something", "nothing" );
      TestCommon.assertIterators( a.iterator(), b.iterator() );
      
      a = Arrays.asList( "anything", "anything", "nothing" );
      b = Arrays.asList( "anything", "anything", "nothing" );
      TestCommon.assertIterators( a.iterator(), b.iterator() );
   }//End Method
   
   /**
    * {@link TestCommon#assertIterators(java.util.Iterator, java.util.Iterator)} reject test.
    */
   @Test( expected = AssertionError.class ) 
   public void shouldFailAssertIteratorsEquality(){
      List< String > a = Arrays.asList( "anything", "something", "anything" );
      List< String > b = Arrays.asList( "anything", "something", "nothing" );
      TestCommon.assertIterators( a.iterator(), b.iterator() );
   }//End Method
   
   /**
    * {@link TestCommon#assertIterators(java.util.Iterator, java.util.Iterator)} reject for size mismatch.
    */
   @Test( expected = AssertionError.class ) 
   public void shouldFailAssertIteratorsSize(){
      List< String > a = Arrays.asList( "anything", "something", "anything", "anything" );
      List< String > b = Arrays.asList( "anything", "something", "anything" );
      TestCommon.assertIterators( a.iterator(), b.iterator() );
   }//End Method
   
   /**
    * {@link TestCommon#assertIterators(java.util.Iterator, java.util.Iterator, java.util.function.BiPredicate)} test.
    */
   @Test public void shouldAssertIteratorsCustomEquality(){
      List< String > a = Arrays.asList( "a", "b", "c" );
      List< String > b = Arrays.asList( "x", "y", "z" );
      TestCommon.assertIterators( a.iterator(), b.iterator(), ( s, t ) -> { return s.compareTo( t ) < 0; } );
   }//End Method
   
   /**
    * Prove that a random number can be generated within a range.
    */
   @Test public void shouldGenerateRandomWithinRange(){
      final double min = 35000;
      final double max = 35678;
      for ( int i = 0; i < 1000000; i++ ) {
         double random = TestCommon.random( min, max );
         Assert.assertTrue( random >= min );
         Assert.assertTrue( random <= max );
      }
   }//End Method

}//End Class
