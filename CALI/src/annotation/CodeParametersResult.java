/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import java.lang.reflect.Method;

import model.result.ComplexReturnResult;
import annotation.CodeParametersResult.Result;

/**
 * The {@link CodeParametersResult} provides the result of parsing coding parameters
 * from a {@link String} expression.
 */
public class CodeParametersResult extends ComplexReturnResult< Result >{
   
   public enum Result {
      EMPTY_NO_OPEN, 
      DOES_NOT_OPEN, 
      OPEN_NO_PARAMETERS, 
      PARAMETERS_NO_CLOSE, 
      SUCCESS
   }
   
   private String resultingExpression;
   private String[] parameters;
   private int numberOfParameters;
   
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
    * Getter for the parsed parameters, as {@link String}s.
    * @return the array of parameters.
    */
   public Object[] getParameters() {
      return parameters;
   }// End Method
   
   /**
    * Getter for the number parameters input.
    * @return the int number of parameters in the {@link Method}.
    */
   public int getNumberOfParameters() {
      return numberOfParameters;
   }// End Method
   
   /**
    * Setter for the parameters parsed, see {@link #getParameters()}.
    * @param parameters the parameters parsed.
    */
   public void setParameters( String[] parameters ) {
      this.parameters = parameters;
      numberOfParameters = parameters.length;
   }// End Method

}// End Class
