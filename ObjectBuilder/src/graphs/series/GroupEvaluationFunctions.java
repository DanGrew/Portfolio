/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

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
      @Override public void reset() {
         result = 0;
      }
      
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
      @Override public void reset() {
         result = null;
      }// End Method
      
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
      @Override public void reset() {
         result = null;
      }// End Method
      
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
      @Override public void reset() {
         result = 0;
      }// End Method
      
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
         return sum.getResult() / count.getResult();
      }// End Method
      
      /**
       * {@inheritDoc}
       */
      @Override public void reset() {
         count.reset();
         sum.reset();
      }// End Method
      
   };
   
   /**
    * Method to construct a new {@link NumberFunction} to calculate the average of the values
    * encountered.
    * @return a new {@link NumberFunction}.
    */
   public static NumberFunction newAverageFunction(){
      return new AverageFunction();
   }// End Method
   
}// End Class
