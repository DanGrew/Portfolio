package utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

import org.junit.Assert;


/**
 * {@link TestCommon} defines some common elements for unti tests.
 */
public class TestCommon {
   
   private static final double DOUBLE_PRECISION = 0.001;

   /**
    * Method to get the default double precision.
    * @return the double error.
    */
   public static double precision(){
      return DOUBLE_PRECISION;
   }// End Method
   
   /**
    * Method to assert that two {@link Collection}s are the same irrespective of order.
    * @param collectionA the first {@link Collection}.
    * @param collectionB the second {@link Collection}.
    */
   public static void assertCollectionsSameOrderIrrelevant( Collection< ? > collectionA, Collection< ? > collectionB ) {
      Assert.assertEquals( 
               collectionA + " and " + collectionB + " have a different number of elements.", 
               collectionA.size(), 
               collectionB.size() 
      );
      
      List< ? > listA = new ArrayList<>( collectionA );
      List< ? > listB = new ArrayList<>( collectionB );
    
      for ( Object object : listA ) {
         Assert.assertTrue( listB + " does not contain " + object, listB.contains( object ) );
         listB.remove( object );
      }
      
      Assert.assertTrue( listB.isEmpty() );
   }// End Method
   
   /**
    * Method to assert that the contents of two {@link Iterator}s are the same.
    * @param iterateOver the {@link Iterator} to iterate using.
    * @param checkAgainst the {@link Iterator} to check against.
    */
   public static < TypeT > void assertIterators( Iterator< TypeT > iterateOver, Iterator< TypeT > checkAgainst ) {
      assertIterators( iterateOver, checkAgainst, ( a, b ) -> { return a.equals( b ); } );
   }//End Method
   
   /**
    * Method to assert that the contents of two {@link Iterator}s are the same.
    * @param iterateOver the {@link Iterator} to iterate using.
    * @param checkAgainst the {@link Iterator} to check against.
    * @param equalityCheck the {@link BiPredicate} used to compare any two items.
    */
   public static < TypeT > void assertIterators( 
            Iterator< TypeT > iterateOver, 
            Iterator< TypeT > checkAgainst, 
            BiPredicate< TypeT, TypeT > equalityCheck 
   ) {
      iterateOver.forEachRemaining( object -> {
         Assert.assertTrue( checkAgainst.hasNext() );
         TypeT next = checkAgainst.next();
         Assert.assertTrue( equalityCheck.test( object, next ) );
      } );
      Assert.assertFalse( checkAgainst.hasNext() );
   }//End Method
}// End Class
