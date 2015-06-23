/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import annotation.CodeParametersResult.Result;
import parameter.CommandParameterParseUtilities;

/**
 * The {@link CodeParameterParser} is responsible for parsing coding parameters from
 * a {@link String} expression using the format( ..., ... ).
 */
public class CodeParameterParser {

   /**
    * Method to parse the parameters and construct a result of the parsing.
    * @param expression the expression to parse from.
    * @return the {@link CodeParametersResult} describing the result.
    */
   static CodeParametersResult parseCodeParameters( String expression ) {
      CodeParametersResult result = new CodeParametersResult();
      
      if ( expression.isEmpty() ) {
         result.setResultingExpression( expression );
         result.setResult( Result.EMPTY_NO_OPEN );
         return result;
      }
      
      //Remove open brackets.
      if ( !expression.startsWith( CaliParserUtilities.open() ) ) {
         result.setResultingExpression( expression );
         result.setResult( Result.DOES_NOT_OPEN );
         return result;
      }
      expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.regexOpen() ).trim();
      
      if ( expression.isEmpty() ) {
         result.setResultingExpression( expression );
         result.setResult( Result.OPEN_NO_PARAMETERS );
         return result;
      }
      
      //Get parameters.
      String[] parameters = CommandParameterParseUtilities.parseUpTo( 
               expression, 
               CaliParserUtilities.regexClose(), 
               CaliParserUtilities.parameterDelimiter()
      );
      
      //Cannot match partial constructor, allow any parameters before defining end bracket.
      if ( !expression.contains( CaliParserUtilities.close() ) ) {
         result.setResultingExpression( expression );
         result.setParameters( parameters );
         result.setResult( Result.PARAMETERS_NO_CLOSE );
         return result;
      }
      
      for ( String parameter : parameters ) {
         expression = CommandParameterParseUtilities.reduce( expression, parameter.trim() );
         if ( expression.startsWith( CaliParserUtilities.parameterDelimiter() ) ) {
            expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.parameterDelimiter() );
         }
      }
      expression = expression.replaceFirst( CaliParserUtilities.regexClose(), "" );
      
      result.setResultingExpression( expression );
      result.setResult( Result.SUCCESS );
      result.setParameters( parameters );
      return result;
   }// End Method
   
}// End Class
