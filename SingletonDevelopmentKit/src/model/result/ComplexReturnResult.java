/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package model.result;


/**
 * The {@link ComplexReturnResult} provides the result of parsing coding parameters
 * from a {@link String} expression.
 */
public class ComplexReturnResult< ResultT extends Enum< ? > > {
   
   private ResultT result;
   
   /**
    * Getter for the type of ResultT.
    * @return the ResultT.
    */
   public ResultT getResult() {
      return result;
   }// End Method
   
   /**
    * Setter for the type of ResultT, see {@link #getResult()}.
    * @param result the ResultT type.
    */
   public void setResult( ResultT result ) {
      this.result = result;
   }// End Method
   
}// End Class
