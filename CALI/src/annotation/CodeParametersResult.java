/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

/**
 * The {@link CodeParametersResult} provides the result of parsing coding parameters
 * from a {@link String} expression.
 */
public class CodeParametersResult {
   
   public enum Result {
      EMPTY_NO_OPEN, 
      DOES_NOT_OPEN, 
      OPEN_NO_PARAMETERS, 
      PARAMETERS_NO_CLOSE, 
      SUCCESS
   }
   
   private String resultingExpression;
   private Result result;
   private String[] parameters;
   
   /**
    * Getter for the resulting expression, following parsing.
    * @return the expression.
    */
   public String getResultingExpression() {
      return resultingExpression;
   }// End Method
   
   /**
    * Setter for the resulting expression, see {@link #getResultingExpression()}.
    * @param resultingExpression the expression.
    */
   public void setResultingExpression( String resultingExpression ) {
      this.resultingExpression = resultingExpression;
   }// End Method
   
   /**
    * Getter for the type of {@link Result}.
    * @return the {@link Result}.
    */
   public Result getResult() {
      return result;
   }// End Method
   
   /**
    * Setter for the type of {@link Result}, see {@link #getResult()}.
    * @param result the {@link Result} type.
    */
   public void setResult( Result result ) {
      this.result = result;
   }// End Method
   
   /**
    * Getter for the parsed parameters, as {@link String}s.
    * @return the array of parameters.
    */
   public String[] getParameters() {
      return parameters;
   }// End Method
   
   /**
    * Setter for the parameters parsed, see {@link #getParameters()}.
    * @param parameters the parameters parsed.
    */
   public void setParameters( String[] parameters ) {
      this.parameters = parameters;
   }// End Method
   
}// End Class
