/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.sort;

import java.util.Comparator;

import model.structure.NetworkPosition;

/**
 * The {@link NetworkPositionComparator} is responsible for providing comparators to order {@link NetworkPosition}s.
 */
public class NetworkPositionComparator {

   private static final int SMALLER = -1;
   private static final int EQUAL = 0;
   private static final int GREATER = 1;
   
   /** {@link Comparator} for {@link NetworkPosition}s that orders based on position, where layer is ordered
    * ascending, then index. **/
   public static final Comparator< NetworkPosition > POSITION_COMPARATOR = new PositionComparator();
   
   /**
    * The {@link NetworkPositionComparator} is responsible for defining a {@link Comparator} to order {@link NetworkPosition}s
    * by position, ascending by layer then index.
    */
   private static class PositionComparator implements Comparator< NetworkPosition > {

      /**
       * {@inheritDoc}
       */
      @Override public int compare( NetworkPosition positionA, NetworkPosition positionB ) {
         if ( positionA.layer < positionB.layer ){
            return SMALLER;
         } else if ( positionA.layer > positionB.layer ){
            return GREATER;
         } else if ( positionA.index < positionB.index ){
            return SMALLER;
         } else if ( positionA.index > positionB.index ){
            return GREATER;
         } else {
            return EQUAL;
         }
      }// End Method
      
   }// End Class
   
}// End Class
