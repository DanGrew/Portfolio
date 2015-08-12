/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph.sorting;

import graphs.graph.Graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

/**
 * The {@link GraphSort} provides methods of sorting the {@link Graph}'s horizontal axis
 * according to the data type it is associated with.
 */
public enum GraphSort {
   
   StringAlphabetical( ( itemA, itemB ) -> {
      Integer inputNullCheck = checkValues( itemA, itemB, true );
      if ( inputNullCheck != null ) {
         return inputNullCheck;
      }
      
      String itemAString = itemA.getXValue();
      String itemBString = itemB.getXValue();
      inputNullCheck = checkValues( itemAString, itemBString, true );
      if ( inputNullCheck != null ) {
         return inputNullCheck;
      }
      
      return itemAString.compareTo( itemBString ); 
   } ),
   
   StringReverseAlphabetical( ( itemA, itemB ) -> {
      Integer inputNullCheck = checkValues( itemA, itemB, false );
      if ( inputNullCheck != null ) {
         return inputNullCheck;
      }
      
      String itemAString = itemA.getXValue();
      String itemBString = itemB.getXValue();
      inputNullCheck = checkValues( itemAString, itemBString, false );
      if ( inputNullCheck != null ) {
         return inputNullCheck;
      }
      
      return itemBString.compareTo( itemAString ); 
   } );
   
   private Comparator< Data< String, Number > > comparatorFunction;
   
   /**
    * Constructs a new {@link GraphSort}.
    * @param comparatorFunction the {@link Comparator} for ordering the {@link Data}.
    */
   private GraphSort( Comparator< Data< String, Number > > comparatorFunction ) {
      this.comparatorFunction = comparatorFunction;
   }// End Constructor
   
   /**
    * Method to sort the given {@link Data} from the {@link Series}.
    * @param data the data to order.
    */
   public void sort( List< Data< String, Number > > data ) {
      Collections.sort( data, comparatorFunction );
   }// End Method

   /**
    * Method to check the given objects based on a particular order.
    * @param objectA the first.
    * @param objectB the second.
    * @param inOrder whether a to b or b to a.
    * @return the comparison is one or both are null preserving the order, or null if
    * they both are not null.
    */
   private static Integer checkValues( Object objectA, Object objectB, boolean inOrder ) {
      if ( !inOrder ) {
         Object tmp = objectA;
         objectA = objectB;
         objectB = tmp;
      }
      if ( objectA == null && objectB == null ) {
         return 0;
      } else if ( objectA == null ) {
         return -1;
      } else if ( objectB == null ) {
         return 1;
      } else {
         return null;
      }
   }// End Method
}// End Class
