/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import model.singleton.Singleton;
import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import annotation.CaliParserUtilities;

import command.parameter.result.MethodCallResult;
import command.parameter.result.SingletonReferenceParserResult;

/**
 * The {@link SingletonMethodCallParameterImpl} is responsible for parsing a method
 * call on a {@link Singleton} that is referenced by a nested {@link SingletonReferenceParameterImpl}.
 */
public class SingletonMethodCallParameterImpl implements CommandParameter {
   
   private CommandParameter referenceParameter; 

   /**
    * Constructs a new {@link SingletonMethodCallParameterImpl}.
    */
   public SingletonMethodCallParameterImpl() {
      referenceParameter = new SingletonReferenceParameterImpl();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "<singleton>.<method>( <parameters> )";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String reference = parseSingletonReference( expression );
      if ( reference == null ) {
         //No input to match.
         return true;
      }
      
      Singleton singleton = null;
      SingletonReferenceParserResult singletonResult = new SingletonReferenceParserResult();
      singletonResult.parse( reference, referenceParameter );
      switch ( singletonResult.getResult() ) {
         case COMPLETE_MATCHES_NO_SUGGESTION:
            return true;
         case PARTIAL_MATCHES_NO_SUGGESTION:
            return true;
         case SINGLETON_DOES_NOT_MATCH:
            return false;   
         case SINGLETON_PARSED:
            singleton = singletonResult.getSingleton();
         default:
            break;
      }

      MethodCallResult methodResult = new MethodCallResult();
      methodResult.parse( singleton, expression, reference );
      switch ( methodResult.getResult() ) {
         case DOES_NOT_OPEN:
            return true;
         case EMPTY_NO_OPEN:
            return true;
         case METHOD_MATCHES:
            return true;
         case METHOD_SIGNATURE_DOES_NOT_MATCH:
            return false;
         case NO_METHOD_NAME:
            return true;
         case NO_PARAMETERS_METHOD_DOES_NOT_MATCH:
            return false;
         case NO_PARAMETERS_METHOD_MATCHES:
            return true;
         case OPEN_NO_PARAMETERS:
            return true;
         case PARAMETERS_NO_CLOSE:
            return true;
         case SINGELTON_NULL:
            return false;
         default:
            return false;
      }
   }// End Method
   
   /**
    * Method to parse the reference name to the {@link Singleton} from the expression.
    * @param expression the expression to parse from.
    * @return the reference {@link String}, null if nothing in expression.
    */
   private String parseSingletonReference( String expression ){
      if ( expression.contains( CaliParserUtilities.statementDelimiter() ) ) {
         String[] parsedElements = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliParserUtilities.regexStatementDelimter(), 
                  CommandParameterParseUtilities.delimiter() 
         );
         if ( parsedElements.length == 0 ) {
            return null;
         }
         return parsedElements[ 0 ] + CaliParserUtilities.statementDelimiter();
      } else {
         return expression;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String reference = parseSingletonReference( expression );
      if ( reference == null ) {
         //No input to match.
         return true;
      }
      
      Singleton singleton = null;
      SingletonReferenceParserResult singletonResult = new SingletonReferenceParserResult();
      singletonResult.parse( reference, referenceParameter );
      switch ( singletonResult.getResult() ) {
         case COMPLETE_MATCHES_NO_SUGGESTION:
            return false;
         case PARTIAL_MATCHES_NO_SUGGESTION:
            return false;
         case SINGLETON_PARSED:
            singleton = singletonResult.getSingleton();
         default:
            break;
      }

      MethodCallResult methodResult = new MethodCallResult();
      methodResult.parse( singleton, expression, reference );
      switch ( methodResult.getResult() ) {
         case DOES_NOT_OPEN:
            return false;
         case EMPTY_NO_OPEN:
            return false;
         case METHOD_MATCHES:
            return true;
         case METHOD_SIGNATURE_DOES_NOT_MATCH:
            return false;
         case NO_METHOD_NAME:
            return false;
         case NO_PARAMETERS_METHOD_DOES_NOT_MATCH:
            return false;
         case NO_PARAMETERS_METHOD_MATCHES:
            return false;
         case OPEN_NO_PARAMETERS:
            return false;
         case PARAMETERS_NO_CLOSE:
            return false;
         case SINGELTON_NULL:
            return false;
         default:
            return false;
      }
   }// End Method

   @Override public Object parseObject( String expression ) {
      return null;
   }

   @Override public String autoComplete( String expression ) {
      return null;
   }

}// End Class
