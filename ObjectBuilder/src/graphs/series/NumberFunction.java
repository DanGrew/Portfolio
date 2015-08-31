/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

/**
 * Interface defining a function manipulating numbers.
 */
public interface NumberFunction {
   
   /**
    * Method to for the function to consider this number in its evaluation.
    * @param value the value to consider.
    */
   public void consider( double value );
   
   /**
    * Method to calculate the result of the function based on the considered values.
    * @return the result.
    */
   public Double getResult();
   
   /**
    * Method to move on to the next category of data.
    */
   public void nextCategory();

   /**
    * Method to reset the function in its entirety.
    */
   public void reset();

}//End Interface
