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
      NO_PARAMETERS_METHOD_MATCHES, 
      DOES_NOT_OPEN, EMPTY_NO_OPEN, 
      OPEN_NO_PARAMETERS, 
      PARAMETERS_NO_CLOSE, 
      METHOD_MATCHES, 
      METHOD_SIGNATURE_DOES_NOT_MATCH
   }// End Enum
   
   /**
    * Method to parse the {@link Method} for the given {@link Singleton} and expression.
    * @param singleton the {@link Singleton} the {@link Method} is for.
    * @param expression the expression the information is parsed from.
    * @param reference the reference to the {@link Singleton} in the expression.
    */
   public void parse( Singleton singleton, String expression, String reference ){
      if ( singleton != null ) {
         expression = CommandParameterParseUtilities.reduce( expression, reference );
         if ( expression.isEmpty() ) {
            setResult( Result.NO_METHOD_NAME );
            return;
         }
         
         String methodNamePart = CaliParserUtilities.extractObjectType( expression );
         expression = CommandParameterParseUtilities.reduce( expression, methodNamePart );
         if ( expression.isEmpty() ) {
            List< Method > matches = CaliSystem.partialMatchMethodName( singleton.getClass(), methodNamePart );
            if ( matches.isEmpty() ) {
               setResult( Result.NO_PARAMETERS_METHOD_DOES_NOT_MATCH );
            } else {
               setResult( Result.NO_PARAMETERS_METHOD_MATCHES );
            }
            return;
         }
         
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
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
               return;
            case SUCCESS:
               Method method = CaliSystem.matchMethodSignature( singleton.getClass(), methodNamePart, result.getParameterTypes() );
               if ( method != null ) {
                  setResult( Result.METHOD_MATCHES );
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
