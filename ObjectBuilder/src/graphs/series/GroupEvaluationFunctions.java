/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link GroupEvaluationFunctions} provides some common functions operating 
 * on numbers to calculate a result.
 */
public class GroupEvaluationFunctions {

   /**
    * {@link NumberFunction} to count the number of values encountered.
    */
   private static class CountFunction implements NumberFunction {
      
      private double result = 0;
      
      /**
       * {@inheritDoc}
       */
      @Override public void consider( double value ) {
         result++;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public Double getResult() {
         return result;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         result = 0;
      }//End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         nextCategory();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to count the number of values
    * encountered.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newCountFunction(){
      return new CountFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to count the number of values encountered.
    */
   private static class CumulativeCountFunction extends CountFunction {
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         //Do nothing.
      }//End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         super.nextCategory();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to count the number of values
    * encountered, that accumulates across categories.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newCumulativeCountFunction(){
      return new CumulativeCountFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to identify the maximum value encountered.
    */
   private static class MaximumFunction implements NumberFunction {
      
      private Double result = null;
      
      /**
       * {@inheritDoc}
       */
      @Override public void consider( double value ) {
         if ( result == null ) {
            result = value;
         } else {
            result = Math.max( value, result );
         }
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public Double getResult() {
         return result;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         result = null;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         nextCategory();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to identify the maximum of values
    * encountered.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newMaximumFunction(){
      return new MaximumFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to identify the maximum value encountered.
    */
   private static class CumulativeMaximumFunction extends MaximumFunction {
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         //Do nothing.
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         super.nextCategory();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to identify the maximum of values
    * encountered, across all categories.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newCumulativeMaximumFunction(){
      return new CumulativeMaximumFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to identify the minimum value encountered.
    */
   private static class MinimumFunction implements NumberFunction {
      
      private Double result = null;
      
      /**
       * {@inheritDoc}
       */
      @Override public void consider( double value ) {
         if ( result == null ) {
            result = value;
         } else {
            result = Math.min( value, result );
         }
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public Double getResult() {
         return result;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         result = null;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         nextCategory();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to identify the minimum of values
    * encountered.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newMinimumFunction(){
      return new MinimumFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to identify the minimum value encountered.
    */
   private static class CumulativeMinimumFunction extends MinimumFunction {
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         //Do nothing.
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         super.nextCategory();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to identify the minimum of values
    * encountered, across categories.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newCumulativeMinimumFunction(){
      return new CumulativeMinimumFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to sum the values encountered.
    */
   private static class SumFunction implements NumberFunction {
      
      private double result = 0;
      
      /**
       * {@inheritDoc}
       */
      @Override public void consider( double value ) {
         result += value;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public Double getResult() {
         return result;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         result = 0;
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         nextCategory();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to sum the values
    * encountered.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newSumFunction(){
      return new SumFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to sum the values encountered.
    */
   private static class CumulativeSumFunction extends SumFunction {
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         //Do nothing.
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         super.nextCategory();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to sum the values
    * encountered, across all categories.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newCumulativeSumFunction(){
      return new CumulativeSumFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to calculate the average of values encountered.
    */
   private static class AverageFunction implements NumberFunction {
      
      private NumberFunction count;
      private NumberFunction sum;
      
      private AverageFunction() {
         count = newCountFunction();
         sum = newSumFunction();
      }
      
      /**
       * {@inheritDoc}
       */
      @Override public void consider( double value ) {
         sum.consider( value );
         count.consider( value );
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public Double getResult() {
         if ( sum.getResult() == 0.0 ) {
            return 0.0;
         }
         if ( count.getResult() == 0 ) {
            return 0.0;
         }
         return sum.getResult() / count.getResult();
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         count.nextCategory();
         sum.nextCategory();
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         count.reset();
         sum.reset();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to calculate the average of the values
    * encountered.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newAverageFunction(){
      return new AverageFunction();
   }// End Method
   
   /**
    * {@link NumberFunction} to calculate the average of values encountered.
    */
   private static class CumulativeAverageFunction extends AverageFunction {
      
      private List< Double > previousAverages;
      
      /**
       * Constructs a new {@link CumulativeAverageFunction}.
       */
      private CumulativeAverageFunction() {
         super();
         previousAverages = new ArrayList<>();
      }//End Constructor
      
      /**
       * {@inheritDoc}
       */
      @Override public Double getResult() {
         double average = super.getResult();
         for ( Double value : previousAverages ) {
            average += value;
         }
         return average / ( previousAverages.size() + 1 );
      }//End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void nextCategory() {
         previousAverages.add( super.getResult() );
         super.nextCategory();
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         previousAverages.clear();
         super.reset();
      }//End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to calculate the average of the values
    * encountered, across all categories.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newCumulativeAverageFunction(){
      return new CumulativeAverageFunction();
   }// End Method
   
}// End Class
