package utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
      Assert.assertEquals( collectionA.size(), collectionB.size() );
      
      List< ? > listA = new ArrayList<>( collectionA );
      List< ? > listB = new ArrayList<>( collectionB );
    
      for ( Object object : listA ) {
         Assert.assertTrue( listB.contains( object ) );
         listB.remove( object );
      }
      
      Assert.assertTrue( listB.isEmpty() );
   }// End Method
}// End Class
