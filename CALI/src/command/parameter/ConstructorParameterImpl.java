/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import system.CaliSystem;
import annotation.Cali;
import annotation.CaliParserUtilities;
import annotation.CaliSuggestionUtilities;
import annotation.CodeParametersResult;

/**
 * The {@link ConstructorParameterImpl} provides a {@link CommandParameter} capable of parsing 
 * {@link Constructor}s of {@link Cali} objects.
 */
public class ConstructorParameterImpl implements CommandParameter {
   
   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "Constructor( parameters )";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< String > getSuggestions( String expression ) {
      List< String > suggestions = new ArrayList<>();
      
      String objectNamePart = CaliParserUtilities.extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
         boolean completeParameter = expression.trim().endsWith( CaliParserUtilities.parameterDelimiter() );
         
         switch ( result.getResult() ) {
            case DOES_NOT_OPEN:
            case EMPTY_NO_OPEN:
               suggestions.addAll( suggestAllClasses( objectClasses ) );
               break;
            case OPEN_NO_PARAMETERS:
               suggestions.addAll( suggestOpenNoParameters( objectNamePart, objectClasses, null, completeParameter ) );
               break;
            case PARAMETERS_NO_CLOSE:
               suggestions.addAll( suggestOpenNoParameters( 
                        objectNamePart, 
                        objectClasses, 
                        result.getNumberOfParameters(),
                        completeParameter
               ) );
               break;
            case SUCCESS:
               Object[] parameters = result.getParameters();
               retrieveConstructor( objectNamePart, parameters );
               break;
            default:
               break;
         }
      }
      return suggestions;
   }// End Method
   
   /**
    * Method to construct suggestions for all of the identified {@link Class}es.
    * @param classes the {@link Class}es that can be used with this {@link CommandParameter}.
    * @return a {@link List} of {@link String} suggestions.
    */
   private List< String > suggestAllClasses( List< Class< ? > > classes ) {
      List< String > suggestions = new ArrayList<>();
      classes.forEach( clazz -> suggestions.add( clazz.getSimpleName() + CaliParserUtilities.open() ) );
      return suggestions;
   }// End Method
   
   /**
    * Method to construct suggestions for the given name of the object and the {@link Class}es that 
    * could represent it. If the name matches any exactly, the parameters are described, otherwise
    * the list of objects are suggested.
    * @param objectNamePart the name input, can be partial or empty.
    * @param classes the {@link Class}es that could match the name.
    * @param numberOfParametersEntered the number of parameters entered, can be null indicating none.
    * @param completeParameter whether the expression has ended the last parameter.
    * @return a {@link List} of suggestions for the given criteria.
    */
   private List< String > suggestOpenNoParameters( String objectNamePart, List< Class< ? > > classes, Integer numberOfParametersEntered, boolean completeParameter ) {
      Class< ? > exactMatch = null;
      for ( Class< ? > clazz : classes ) {
         if ( objectNamePart.equals( clazz.getSimpleName() ) ) {
            exactMatch = clazz;
            break;
         }
      }
      
      if ( exactMatch == null ) {
         return suggestAllClasses( classes );
      } else {
         return suggestAllConstructorsParameters( exactMatch, numberOfParametersEntered, completeParameter );
      }
   }// End Method
   
   /**
    * Method to suggest the {@link Constructor} parameters given the criteria.
    * @param clazz the {@link Class} of the object identified.
    * @param numberOfParametersEntered the number of parameters entered for the {@link Constructor}.
    * @param completeParameter whether the last parameter entered has been completed.
    * @return a {@link List} of {@link String} suggestions of the parameter combinations.
    */
   private List< String > suggestAllConstructorsParameters( Class< ? > clazz, Integer numberOfParametersEntered, boolean completeParameter ){
      List< Constructor< ? > > constructors = CaliSystem.findConstructors( clazz, numberOfParametersEntered );
      
      if ( numberOfParametersEntered == null ) {
         numberOfParametersEntered = 0;
      } else if ( !completeParameter ) {
         numberOfParametersEntered--;
      }
      return CaliSuggestionUtilities.suggestAllParameters( constructors, numberOfParametersEntered );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String objectNamePart = CaliParserUtilities.extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
         
         switch ( result.getResult() ) {
            case DOES_NOT_OPEN:
               return false;
            case EMPTY_NO_OPEN:
               return true;
            case OPEN_NO_PARAMETERS:
               return true;
            case PARAMETERS_NO_CLOSE:
               return true;
            case SUCCESS:
               Object[] parameters = result.getParameters();
               return retrieveConstructor( objectNamePart, parameters ) != null;
            default:
               return false;
         }
         
      }
      return false;
   }// End Method
   
   /**
    * Method to parse the {@link Class} of the {@link Object} given by the extract value.
    * @param value the value extracted from the expression.
    * @return the matching {@link Cali} {@link Class}.
    */
   private List< Class< ? > > parseObjectClass( String value ){
      if ( value == null ) {
         return new ArrayList< Class<?> >();
      }
      List< Class< ? > > classes = CaliSystem.partialMatchClass( value );
      if ( classes != null ) {
         return classes;
      }
      return new ArrayList< Class<?> >();
   }// End Method

   /**
    * Method to retrieve the {@link Constructor} for the given object type and parameters.
    * @param objectNamePart the name of the {@link Object}.
    * @param parameters the {@link Object} parameters.
    */
   private Constructor< ? > retrieveConstructor( String objectNamePart, Object[] parameters ){
      Constructor< ? > constructor = CaliSystem.matchConstructor( objectNamePart, parameters.length );
      return constructor;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String objectNamePart = CaliParserUtilities.extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
         
         switch ( result.getResult() ) {
            case DOES_NOT_OPEN:
               return false;
            case EMPTY_NO_OPEN:
               return false;
            case OPEN_NO_PARAMETERS:
               return false;
            case PARAMETERS_NO_CLOSE:
               return false;
            case SUCCESS:
               Object[] parameters = result.getParameters();
               return retrieveConstructor( objectNamePart, parameters ) != null;
            default:
               return false;
         }
      }
      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      ConstructorParameterValue value = new ConstructorParameterValue();
      
      String objectNamePart = CaliParserUtilities.extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
         
         switch ( result.getResult() ) {
            case DOES_NOT_OPEN:
               return null;
            case EMPTY_NO_OPEN:
               return null;
            case OPEN_NO_PARAMETERS:
               return null;
            case PARAMETERS_NO_CLOSE:
               return null;
            case SUCCESS:
               Object[] parameters = result.getParameters();
               Constructor< ? > constructor = retrieveConstructor( objectNamePart, parameters );
               if ( constructor != null ) {
                  value.setConstructor( constructor );
                  value.addParameters( parameters );
                  return value;
               } else {
                  return null;
               }
            default:
               return null;
         }
      }
      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      if ( expression.trim().isEmpty() ) {
         return null;
      }
      
      String objectNamePart = CaliParserUtilities.extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         
         Class< ? > onlyChoice = null;
         if ( objectClasses.size() == 1 ) {
            onlyChoice = objectClasses.get( 0 );
         }
         
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
         
         switch ( result.getResult() ) {
            case DOES_NOT_OPEN:
               return autoCorrectUpToOpen( expression, onlyChoice );
            case EMPTY_NO_OPEN:
               return autoCorrectUpToOpen( expression, onlyChoice );
            case OPEN_NO_PARAMETERS:
               expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.regexOpen() );
               return autoCorrectUpToOpen( expression, onlyChoice );
            case PARAMETERS_NO_CLOSE:
               expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.regexOpen() );
               return autoCorrectUpToOpen( expression, onlyChoice );
            case SUCCESS:
               Object[] parameters = result.getParameters();
               Constructor< ? > constructor = retrieveConstructor( objectNamePart, parameters );
               if ( constructor != null ) {
                  StringBuffer autoCorrect = new StringBuffer();
                  autoCorrect.append( constructor.getDeclaringClass().getSimpleName() );
                  autoCorrect.append( CaliParserUtilities.open() );
                  autoCorrect.append( CommandParameterParseUtilities.delimiter() );
                  for ( Object parameter : parameters ) {
                     autoCorrect.append( parameter ).append( CaliParserUtilities.parameterDelimiter() );
                     autoCorrect.append( CommandParameterParseUtilities.delimiter() );
                  }
                  autoCorrect.deleteCharAt( autoCorrect.lastIndexOf( CaliParserUtilities.parameterDelimiter() ) );
                  autoCorrect.append( CaliParserUtilities.close() );
                  
                  autoCorrect.append( CommandParameterParseUtilities.delimiter() );
                  autoCorrect.append( expression );
                  return autoCorrect.toString();
               }

            default:
               return null;
         }
      }
      return null;
   }// End Method
   
   /**
    * Method to auto correct the expression up to the {@link CaliParserUtilities#open()} tag.
    * @param expression the expression to auto correct.
    * @param onlyChoice the {@link Class} single choice available.
    * @return the auto corrected expression.
    */
   private String autoCorrectUpToOpen( String expression, Class< ? > onlyChoice ){
      if ( onlyChoice != null ) {
         String autoCorrect = onlyChoice.getSimpleName() + CaliParserUtilities.open() + CommandParameterParseUtilities.delimiter() + expression;
         return autoCorrect.trim();
      } else if ( expression.isEmpty() ){
         return null;
      } else {
         return expression;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String extractInput( String expression ) {
      String objectNamePart = CaliParserUtilities.extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
         
         switch ( result.getResult() ) {
            case DOES_NOT_OPEN:
               return result.getResultingExpression();
            case EMPTY_NO_OPEN:
               return result.getResultingExpression();
            case OPEN_NO_PARAMETERS:
               return result.getResultingExpression();
            case PARAMETERS_NO_CLOSE:
               return "";
            case SUCCESS:
               return result.getResultingExpression();
            default:
               return null;
         }
      }
      return expression;
   }// End Method

}// End Class
