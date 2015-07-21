/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import model.singleton.Singleton;
import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import system.CaliSystem;
import annotation.CaliParserUtilities;
import annotation.CaliSuggestionUtilities;

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
   @Override public List< String > getSuggestions( String expression ) {
      List< String > suggestions = new ArrayList< String >();
      
      String reference = parseSingletonReference( expression );
      if ( reference == null ) {
         //No input to match.
         return suggestions;
      }
      
      Singleton singleton = null;
      SingletonReferenceParserResult singletonResult = new SingletonReferenceParserResult();
      singletonResult.parse( reference, referenceParameter );
      switch ( singletonResult.getResult() ) {
         case COMPLETE_MATCHES_NO_SUGGESTION:
         case PARTIAL_MATCHES_NO_SUGGESTION:
            return suggestAllSingletons( reference );
         case COMPLETE_MATCHES_MULTIPLE_SUGGESTION:
         case PARTIAL_MATCHES_MULTIPLE_SUGGESTION:
            return suggestAllSingletons( singletonResult.getMatches() );
         case SINGLETON_DOES_NOT_MATCH:
            return suggestions;
         case SINGLETON_PARSED:
            singleton = singletonResult.getSingleton();
            if ( !singleton.getIdentification().equals( reference.replace( CaliParserUtilities.statementDelimiter(), "" ) ) ) {
               suggestions.add( singleton.getIdentification() + CaliParserUtilities.statementDelimiter() );
               return suggestions;
            }
         default:
            break;
      }

      MethodCallResult methodResult = new MethodCallResult();
      methodResult.parse( singleton, expression, reference );
      switch ( methodResult.getResult() ) {
         case DOES_NOT_OPEN:
         case EMPTY_NO_OPEN:
            suggestions.add( singleton.getIdentification() + CaliParserUtilities.statementDelimiter() );
            return suggestions;
         case NO_METHOD_NAME:
            if ( reference.endsWith( CaliParserUtilities.statementDelimiter() ) ) {
               return suggestAllMethods( singleton.getClass(), null, null );
            } else {
               suggestions.add( singleton.getIdentification() + CaliParserUtilities.statementDelimiter() );
               return suggestions;   
            }
         case METHOD_MATCHES:
            return Arrays.asList( CommandParameter.READY );
         case METHOD_SIGNATURE_DOES_NOT_MATCH:
         case NO_PARAMETERS_METHOD_DOES_NOT_MATCH:
            return suggestions;
         case NO_PARAMETERS_EXACT_MATCH:
         case NO_PARAMETERS_MULTIPLE_MATCHES:
            return suggestAllMethods( singleton.getClass(), methodResult.getMethodNamePart(), null );
         case OPEN_NO_PARAMETERS:
            return suggestAllMethodParameters( methodResult.getMethodMatches(), null, true );
         case PARAMETERS_NO_CLOSE:
            if ( expression.endsWith( CaliParserUtilities.parameterDelimiter() ) ) {
               return suggestAllMethodParameters( methodResult.getMethodMatches(), methodResult.getParameters().length, true );
            } else {
               return suggestAllMethodParameters( methodResult.getMethodMatches(), methodResult.getParameters().length, false );   
            }
         case SINGELTON_NULL:
         case CANNOT_FIND_METHOD_NAME:
         default:
            return suggestions;
      }
   }// End Method
   
   /**
    * Method to construct suggestions for all {@link Class}es that match the given partial name.
    * @param partialName the partial name of the {@link Singleton}.
    * @return a {@link List} of {@link String} suggestions.
    */
   private List< String > suggestAllSingletons( String partialName ) {
      List< Singleton > singletons = CaliSystem.partialMatchSingletons( partialName );
      return suggestAllSingletons( singletons );
   }// End Method
   
   /**
    * Method to suggest all {@link Singleton} names for the given {@link List} of {@link Singleton}s.
    * @param singletons the {@link List} of {@link Singleton}s to suggest.
    * @return a {@link List} of {@link String} suggestions.
    */
   private List< String > suggestAllSingletons( List< Singleton > singletons ) {
      List< String > suggestions = new ArrayList<>();
      singletons.forEach( singleton -> suggestions.add( singleton.getIdentification() + CaliParserUtilities.statementDelimiter() ) );
      return suggestions;
   }// End Method
   
   /**
    * Method to suggest all {@link Method}s for the given {@link Class} and partial name of {@link Method}.
    * @param clazz the {@link Class} of the {@link Singleton}.
    * @param partialName the partial name of the {@link Method}.
    * @param numberOfParameters the number of parameters parsed.
    * @return a {@link List} of {@link String} suggestions.
    */
   private List< String > suggestAllMethods( Class< ? > clazz, String partialName, Integer numberOfParameters ) {
      Set< String > suggestions = new LinkedHashSet<>();
      List< Method > methods = CaliSystem.findMethods( clazz, partialName, numberOfParameters );
      methods.forEach( method -> suggestions.add( method.getName() + CaliParserUtilities.open() ) );
      return new ArrayList<>( suggestions );
   }// End Method
   
   /**
    * Method to suggest the {@link Method} parameters given the criteria.
    * @param methods the {@link List} of {@link Method}s matching.
    * @param numberOfParametersEntered the number of parameters entered for the {@link Method}.
    * @param completeParameter whether the last parameter entered has been completed.
    * @return a {@link List} of {@link String} suggestions of the parameter combinations.
    */
   private List< String > suggestAllMethodParameters( List< Method > methods, Integer numberOfParametersEntered, boolean completeParameter ){
      if ( numberOfParametersEntered == null ) {
         numberOfParametersEntered = 0;
      } else if ( !completeParameter ) {
         numberOfParametersEntered--;
      }
      
      final int comparative = numberOfParametersEntered;
      methods.removeIf( object -> { return object.getParameterCount() <= comparative; } );
      
      if ( methods.isEmpty() ) {
         return Arrays.asList( CaliParserUtilities.close() );
      }
      return CaliSuggestionUtilities.suggestAllParameters( methods, numberOfParametersEntered );
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
         case COMPLETE_MATCHES_MULTIPLE_SUGGESTION:
         case PARTIAL_MATCHES_NO_SUGGESTION:
         case PARTIAL_MATCHES_MULTIPLE_SUGGESTION:
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
         case NO_PARAMETERS_EXACT_MATCH:
            return true;
         case NO_PARAMETERS_MULTIPLE_MATCHES:
            return true;
         case OPEN_NO_PARAMETERS:
            return true;
         case PARAMETERS_NO_CLOSE:
            return true;
         case SINGELTON_NULL:
            return false;
         case CANNOT_FIND_METHOD_NAME:
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
         case COMPLETE_MATCHES_MULTIPLE_SUGGESTION:
         case PARTIAL_MATCHES_NO_SUGGESTION:
         case PARTIAL_MATCHES_MULTIPLE_SUGGESTION:
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
         case NO_PARAMETERS_EXACT_MATCH:
            return false;
         case NO_PARAMETERS_MULTIPLE_MATCHES:
            return false;
         case OPEN_NO_PARAMETERS:
            return false;
         case PARAMETERS_NO_CLOSE:
            return false;
         case SINGELTON_NULL:
            return false;
         case CANNOT_FIND_METHOD_NAME:
            return false;
         default:
            return false;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
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
         case COMPLETE_MATCHES_MULTIPLE_SUGGESTION:
         case PARTIAL_MATCHES_NO_SUGGESTION:
         case PARTIAL_MATCHES_MULTIPLE_SUGGESTION:
         case SINGLETON_DOES_NOT_MATCH:
            return null;   
         case SINGLETON_PARSED:
            singleton = singletonResult.getSingleton();
         default:
            break;
      }

      MethodCallResult methodResult = new MethodCallResult();
      methodResult.parse( singleton, expression, reference );
      switch ( methodResult.getResult() ) {
         case DOES_NOT_OPEN:
            return null;
         case EMPTY_NO_OPEN:
            return null;
         case METHOD_MATCHES:
            SingletonMethodCallValue value = new SingletonMethodCallValue();
            value.setSingleton( singleton );
            value.setMethod( methodResult.getUniqueMethod() );
            value.addParameters( methodResult.getParameters() );
            return value;
         case METHOD_SIGNATURE_DOES_NOT_MATCH:
            return null;
         case NO_METHOD_NAME:
            return null;
         case NO_PARAMETERS_METHOD_DOES_NOT_MATCH:
            return null;
         case NO_PARAMETERS_EXACT_MATCH:
            return null;
         case NO_PARAMETERS_MULTIPLE_MATCHES:
            return null;
         case OPEN_NO_PARAMETERS:
            return null;
         case PARAMETERS_NO_CLOSE:
            return null;
         case SINGELTON_NULL:
            return null;
         case CANNOT_FIND_METHOD_NAME:
            return null;
         default:
            return null;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String reference = parseSingletonReference( expression );
      if ( reference == null ) {
         //No input to match.
         return null;
      }
      
      Singleton singleton = null;
      SingletonReferenceParserResult singletonResult = new SingletonReferenceParserResult();
      singletonResult.parse( reference, referenceParameter );
      switch ( singletonResult.getResult() ) {
         case COMPLETE_MATCHES_NO_SUGGESTION:
            return null;
         case COMPLETE_MATCHES_MULTIPLE_SUGGESTION:
            return null;
         case PARTIAL_MATCHES_NO_SUGGESTION:
            return null;
         case PARTIAL_MATCHES_MULTIPLE_SUGGESTION:
            return null;
         case SINGLETON_DOES_NOT_MATCH:
            return null;   
         case SINGLETON_PARSED:
            singleton = singletonResult.getSingleton();
         default:
            break;
      }

      MethodCallResult methodResult = new MethodCallResult();
      methodResult.parse( singleton, expression, reference );
      switch ( methodResult.getResult() ) {
         case DOES_NOT_OPEN:
         case EMPTY_NO_OPEN:
            return singleton.getIdentification() + CaliParserUtilities.statementDelimiter();
         case METHOD_MATCHES:
         case METHOD_SIGNATURE_DOES_NOT_MATCH:
            return null;
         case NO_METHOD_NAME:
            return singleton.getIdentification() + CaliParserUtilities.statementDelimiter();
         case NO_PARAMETERS_METHOD_DOES_NOT_MATCH:
            return null;
         case NO_PARAMETERS_EXACT_MATCH:
            return singleton.getIdentification() + CaliParserUtilities.statementDelimiter() +
                     methodResult.getUniqueMethod().getName() + CaliParserUtilities.open();
         case NO_PARAMETERS_MULTIPLE_MATCHES:
            return null;
         case OPEN_NO_PARAMETERS:
            return singleton.getIdentification() + CaliParserUtilities.statementDelimiter() +
                     methodResult.getUniqueMethod().getName() + CaliParserUtilities.open();
         case PARAMETERS_NO_CLOSE:
            return singleton.getIdentification() + CaliParserUtilities.statementDelimiter() +
                     methodResult.getUniqueMethod().getName() + CaliParserUtilities.open() + 
                     CommandParameterParseUtilities.delimiter() + methodResult.constructParametersInput();
         case SINGELTON_NULL:
            return null;
         case CANNOT_FIND_METHOD_NAME:
            return null;
         default:
            return null;
      }
   }// End Method 
   
   /**
    * {@inheritDoc}
    */
   @Override public String extractInput( String expression ) {
      String reference = parseSingletonReference( expression );
      if ( reference == null ) {
         //No input to match.
         return null;
      }
      
      
      Singleton singleton = null;
      SingletonReferenceParserResult singletonResult = new SingletonReferenceParserResult();
      singletonResult.parse( reference, referenceParameter );
      switch ( singletonResult.getResult() ) {
         case COMPLETE_MATCHES_NO_SUGGESTION:
         case PARTIAL_MATCHES_NO_SUGGESTION:
            return expression;
         case COMPLETE_MATCHES_MULTIPLE_SUGGESTION:
         case PARTIAL_MATCHES_MULTIPLE_SUGGESTION:
            return expression.replace( reference, "" );
         case SINGLETON_DOES_NOT_MATCH:
            return expression;   
         case SINGLETON_PARSED:
            singleton = singletonResult.getSingleton();
         default:
            break;
      }

      expression = CommandParameterParseUtilities.reduce( expression, reference );
      MethodCallResult methodResult = new MethodCallResult();
      methodResult.parse( singleton, expression, reference );
      return methodResult.getResultingExpression().trim();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String parseParameter( String expression ) {
      String originalExpression = expression;
      String reference = parseSingletonReference( expression );
      if ( reference == null ) {
         //No input to match.
         return null;
      }
      
      Singleton singleton = null;
      SingletonReferenceParserResult singletonResult = new SingletonReferenceParserResult();
      singletonResult.parse( reference, referenceParameter );
      switch ( singletonResult.getResult() ) {
         case COMPLETE_MATCHES_NO_SUGGESTION:
            return reference;
         case COMPLETE_MATCHES_MULTIPLE_SUGGESTION:
            return reference;
         case PARTIAL_MATCHES_NO_SUGGESTION:
            return reference;
         case PARTIAL_MATCHES_MULTIPLE_SUGGESTION:
            return reference;
         case SINGLETON_DOES_NOT_MATCH:
            return expression;   
         case SINGLETON_PARSED:
            singleton = singletonResult.getSingleton();
         default:
            break;
      }
      
      MethodCallResult methodResult = new MethodCallResult();
      methodResult.parse( singleton, expression, reference );
      String remainingParts = methodResult.getResultingExpression();
      return originalExpression.replace( remainingParts, "" ).trim();
   }// End Method

}// End Class
