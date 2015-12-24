/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Function;

import javafx.scene.chart.XYChart.Data;

/**
 * {@link Comparators} provides common {@link Comparator} functions and methods for sorting objects.
 */
public class Comparators {

   /** Basic implementation of a {@link String} {@link Comparator}.**/
   public static final Comparator< String > STRING_ALPHABETICAL = new Comparator< String >() {
      @Override public int compare( String o1, String o2 ) {
         Integer nullResult = compareForNullValues( o1, o2, true );
         if ( nullResult == null ) {
            return o1.compareTo( o2 );
         } else {
            return nullResult;
         }
      }
   };
   
   /**
    * Method to compare two {@link String}s. Note accepts nulls.
    * @param o1 the first.
    * @param o2 the second.
    * @return {@link String#compareTo(String)} with null checking.
    */
   public static int compare( String o1, String o2 ) {
      return STRING_ALPHABETICAL.compare( o1, o2 );
   }// End Method
   
   /** Basic implementation of a {@link Double} {@link Comparator}.**/
   private static final Comparator< Double > NUMBER_ASCENDING = new Comparator< Double >() {
      @Override public int compare( Double o1, Double o2 ) {
         Integer nullResult = compareForNullValues( o1, o2, true );
         if ( nullResult == null ) {
            return o1.compareTo( o2 );
         } else {
            return nullResult;
         }
      }
   };
   
   /**
    * Method to compare two {@link Double}s. Note accepts nulls.
    * @param o1 the first.
    * @param o2 the second.
    * @return {@link Double#compareTo(Double)} with null checking.
    */
   public static int compare( Double o1, Double o2 ) {
      return NUMBER_ASCENDING.compare( o1, o2 );
   }// End Method
   
   /** Basic implementation of a {@link LocalDate} {@link Comparator}.**/
   private static final Comparator< LocalDate > DATE_ASCENDING = new Comparator< LocalDate >() {
      @Override public int compare( LocalDate o1, LocalDate o2 ) {
         Integer nullResult = compareForNullValues( o1, o2, true );
         if ( nullResult == null ) {
            return o1.compareTo( o2 );
         } else {
            return nullResult;
         }
      }
   };
   
   /**
    * Method to compare two {@link LocalDate}s. Note accepts nulls.
    * @param o1 the first.
    * @param o2 the second.
    * @return {@link Double#compareTo(LocalDate)} with null checking.
    */
   public static int compare( LocalDate o1, LocalDate o2 ) {
      return DATE_ASCENDING.compare( o1, o2 );
   }// End Method
   
   /**
    * Method to construct a {@link Comparator} for comparing {@link String}s extracted by the given {@link Function}.
    * @param valueGetter the {@link Function} for getting the {@link String}.
    * @return the {@link Comparator} constructed.
    */
   public static < TypeT > Comparator< TypeT > stringExtractionComparater( Function< TypeT, String > valueGetter ) {
      return new Comparator< TypeT >() {
         @Override public int compare( TypeT o1, TypeT o2 ) {
            String o1s = valueGetter.apply( o1 );
            String o2s = valueGetter.apply( o2 );
            return Comparators.compare( o1s, o2s );
         }
      };
   }//End Method
   
   /**
    * Method to construct a reverse {@link Comparator} for the given. This will simply negate the given {@link Comparator}.
    * @param comparator the {@link Comparator} to reverse.
    * @return a new {@link Comparator} performing the reverse ordering of the given.
    */
   public static < TypeT > Comparator< TypeT > reverseComparator( Comparator< TypeT > comparator ) {
      return new Comparator< TypeT >() {
         @Override public int compare( TypeT o1, TypeT o2 ) {
            return comparator.compare( o2, o1 );
         }
      };
   }// End Method
   
   /**
    * {@link Comparator} for comparing {@link Data} for which the x value should be compared.
    * @param valueComparator the {@link Comparator} for the {@link String} x value to sort by.
    * @return a {@link Comparator} for the {@link Data} wrapping the given {@link Comparator}.
    */
   public static Comparator< Data< String, ? > > seriesComparator( Comparator< String > valueComparator ) {
      return new Comparator< Data< String, ? > >() {
         @Override public int compare( Data< String, ? > itemA, Data< String, ? > itemB ) {
            Integer inputNullCheck = Comparators.compareForNullValues( itemA, itemB, true );
            if ( inputNullCheck != null ) {
               return inputNullCheck;
            }
            
            return valueComparator.compare( itemA.getXValue(), itemB.getXValue() );
         }
      };
   }//End Method
   
   /**
    * Method to check the given objects based on a particular order.
    * @param objectA the first.
    * @param objectB the second.
    * @param inOrder whether a to b or b to a.
    * @return the comparison is one or both are null preserving the order, or null if
    * they both are not null.
    */
   public static Integer compareForNullValues( Object objectA, Object objectB, boolean inOrder ) {
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
