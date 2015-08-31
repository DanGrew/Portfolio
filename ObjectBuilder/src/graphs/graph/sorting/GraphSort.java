/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph.sorting;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import architecture.utility.Comparators;
import graphs.graph.Graph;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.DateClassParameterTypeImpl;
import parameter.classparameter.NumberClassParameterTypeImpl;

/**
 * The {@link GraphSort} provides methods of sorting the {@link Graph}'s horizontal axis
 * according to the data type it is associated with.
 */
public enum GraphSort {
   
   StringAlphabetical( ClassParameterTypes.STRING_PARAMETER_TYPE, ( itemA, itemB ) -> {
      Integer inputNullCheck = Comparators.compareForNullValues( itemA, itemB, true );
      if ( inputNullCheck != null ) {
         return inputNullCheck;
      }
      
      String itemAString = itemA.getXValue();
      String itemBString = itemB.getXValue();
      return Comparators.compare( itemAString, itemBString );
   } ),
   
   StringReverseAlphabetical( 
            ClassParameterTypes.STRING_PARAMETER_TYPE,
            Comparators.reverseComparator( StringAlphabetical.comparatorFunction )
   ),
   
   NumberAscending( ClassParameterTypes.NUMBER_PARAMETER_TYPE, ( itemA, itemB ) -> {
      Integer inputNullCheck = Comparators.compareForNullValues( itemA, itemB, true );
      if ( inputNullCheck != null ) {
         return inputNullCheck;
      }
      
      Double itemAString = NumberClassParameterTypeImpl.objectToNumber( itemA.getXValue() );
      Double itemBString = NumberClassParameterTypeImpl.objectToNumber( itemB.getXValue() );
      return Comparators.compare( itemAString, itemBString );
   } ),
   
   NumberDescending( 
            ClassParameterTypes.NUMBER_PARAMETER_TYPE,
            Comparators.reverseComparator( NumberAscending.comparatorFunction )
   ),
   
   DateAscending( ClassParameterTypes.DATE_PARAMETER_TYPE, ( itemA, itemB ) -> {
      Integer inputNullCheck = Comparators.compareForNullValues( itemA, itemB, true );
      if ( inputNullCheck != null ) {
         return inputNullCheck;
      }
      
      LocalDate itemAString = DateClassParameterTypeImpl.objectToDate( itemA.getXValue() );
      LocalDate itemBString = DateClassParameterTypeImpl.objectToDate( itemB.getXValue() );
      return Comparators.compare( itemAString, itemBString );
   } ),
   
   DateDescending( 
            ClassParameterTypes.DATE_PARAMETER_TYPE,
            Comparators.reverseComparator( DateAscending.comparatorFunction )
   );
   
   private Comparator< Data< String, Number > > comparatorFunction;
   private ClassParameterType parameterType;
   
   /**
    * Constructs a new {@link GraphSort}.
    * @param type the {@link ClassParameterType} the sort compatible with.
    * @param comparatorFunction the {@link Comparator} for ordering the {@link Data}.
    */
   private GraphSort( ClassParameterType type, Comparator< Data< String, Number > > comparatorFunction ) {
      this.parameterType = type;
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
    * Method to determine whether the given {@link ClassParameterType} can be sorted using this {@link GraphSort}.
    * @param type the {@link ClassParameterType} in question.
    * @return true if it can be sorted, false otherwise.
    */
   public boolean appropriateForSort( ClassParameterType type ) {
      return parameterType.equals( type );
   }// End Method

}// End Class
