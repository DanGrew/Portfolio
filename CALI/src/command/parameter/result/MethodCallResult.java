/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter.result;

import java.lang.reflect.Method;
import java.util.List;

import model.result.ComplexReturnResult;
import model.singleton.Singleton;
import parameter.CommandParameterParseUtilities;
import system.CaliSystem;
import annotation.CaliParserUtilities;
import annotation.CodeParametersResult;

import command.parameter.result.MethodCallResult.Result;

/**
 * The {@link MethodCallResult} is the result when a {@link Method} is parsed from input.
 */
public class MethodCallResult extends ComplexReturnResult< Result >{
   
   /** {@link Enum} for the various types of parsing result. */
   public enum Result {
      SINGELTON_NULL, 
      NO_METHOD_NAME, 
      NO_PARAMETERS_METHOD_DOES_NOT_MATCH, 
      DOES_NOT_OPEN, EMPTY_NO_OPEN, 
      OPEN_NO_PARAMETERS, 
      PARAMETERS_NO_CLOSE, 
      METHOD_MATCHES, 
      METHOD_SIGNATURE_DOES_NOT_MATCH, 
      NO_PARAMETERS_EXACT_MATCH, 
      NO_PARAMETERS_MULTIPLE_MATCHES, 
      CANNOT_FIND_METHOD_NAME
   }// End Enum
   
   private Method method;
   private Object[] parameters;
   private String expression;
   
   /**
    * Getter for the resulting expression when each part is parsed from it.
    * @return the expression following parse.
    */
   public String getResultingExpression() {
      return expression;
   }// End Method
   
   /**
    * Setter for the resulting expression.
    * @param expression the expression following parse.
    */
   private void setResultingExpression( String expression ) {
      this.expression = expression;
   }// End Method
   
   /**
    * Getter for the {@link Method} parsed.
    * @return the {@link Method}.
    */
   public Method getMethod() {
      return method;
   }// End Method
   
   /**
    * Setter for the {@link Method} parsed.
    * @param method the {@link Method} parsed.
    */
   private void setMethod( Method method ) {
      this.method = method;
   }// End Method
   
   /**
    * Setter for the parsed parameters.
    * @param objects the {@link Object}s parsed.
    */
   private void setParameters( Object... objects ) {
      this.parameters = objects;
   }// End Method
   
   /**
    * Getter for the parsed parameters.
    * @return the array of parameters.
    */
   public Object[] getParameters() {
      return parameters;
   }// End Method
   
   /**
    * Method to construct the input provided for the parameters found.
    * @return a {@link String} representation of the parameters typed.
    */
   public String constructParametersInput(){
      if ( parameters == null ) {
         return null;
      }
      StringBuffer buffer = new StringBuffer();
      for ( Object object : parameters ) {
         buffer.append( object.toString() )
               .append( CaliParserUtilities.parameterDelimiter() )
               .append( CommandParameterParseUtilities.delimiter() );
      }
      buffer.setLength( buffer.length() - 2 );
      return buffer.toString();
   }// End Method
   
   /**
    * Method to parse the {@link Method} for the given {@link Singleton} and expression.
    * @param singleton the {@link Singleton} the {@link Method} is for.
    * @param expression the expression the information is parsed from.
    * @param reference the reference to the {@link Singleton} in the expression.
    */
   public void parse( Singleton singleton, String expression, String reference ){
      setResultingExpression( expression );
      if ( singleton != null ) {
         expression = CommandParameterParseUtilities.reduce( expression, reference );
         setResultingExpression( expression );
         if ( expression.isEmpty() ) {
            setResult( Result.NO_METHOD_NAME );
            return;
         }
         
         String methodNamePart = CaliParserUtilities.extractObjectType( expression );
         if ( methodNamePart == null ) {
            setResult( Result.CANNOT_FIND_METHOD_NAME );
            return;
         }
         expression = CommandParameterParseUtilities.reduce( expression, methodNamePart );
         setResultingExpression( expression );
         List< Method > matches = CaliSystem.partialMatchMethodName( singleton.getClass(), methodNamePart );
         if ( expression.isEmpty() ) {
            if ( matches.isEmpty() ) {
               setResult( Result.NO_PARAMETERS_METHOD_DOES_NOT_MATCH );
            } else if ( matches.size() == 1 ){
               setResult( Result.NO_PARAMETERS_EXACT_MATCH );
               setMethod( matches.get( 0 ) );
            } else {
               setResult( Result.NO_PARAMETERS_MULTIPLE_MATCHES );
            }
            return;
         } else {
            if ( matches.size() == 1 ) {
               setMethod( matches.get( 0 ) );
            }
         }
         
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
         setResultingExpression( result.getResultingExpression() );
         switch ( result.getResult() ) {
            case DOES_NOT_OPEN:
               setResult( Result.DOES_NOT_OPEN );
               return;
            case EMPTY_NO_OPEN:
               setResult( Result.EMPTY_NO_OPEN );
               return;
            case OPEN_NO_PARAMETERS:
               setResult( Result.OPEN_NO_PARAMETERS );
               return;
            case PARAMETERS_NO_CLOSE:
               setResult( Result.PARAMETERS_NO_CLOSE );
               setParameters( result.getParameters() );
               //Assume remainder are all parameters.
               setResultingExpression( "" );
               return;
            case SUCCESS:
               Method method = CaliSystem.matchMethodSignature( singleton.getClass(), methodNamePart, result.getParameterTypes() );
               if ( method != null ) {
                  setResult( Result.METHOD_MATCHES );
                  setMethod( method );
                  setParameters( result.getParameters() );
               } else {
                  setResult( Result.METHOD_SIGNATURE_DOES_NOT_MATCH );
               }
               return;
            default:
               break;
         }
      } else {
         setResult( Result.SINGELTON_NULL );
         return;
      }
   }// End Method
}// End Class
