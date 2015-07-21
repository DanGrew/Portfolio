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
import redirect.ParameterSuggestions;
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
   
   private static final ParameterSuggestions SUGGESTIONS = new ParameterSuggestions();
   
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
               retrieveConstructors( objectNamePart, parameters );
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
            case SUCCESS:
               Object[] parameters = result.getParameters();
               return !retrieveConstructors( objectNamePart, parameters ).isEmpty();
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
    * Method to retrieve the {@link Constructors} for the given object type and parameters.
    * @param objectNamePart the name of the {@link Object}.
    * @param parameters the {@link Object} parameters.
    * @return a {@link List} of {@link Constructor}s that match the given name and parameters, verifying the types
    * of parameter.
    */
   private List< Constructor< ? > > retrieveConstructors( String objectNamePart, Object[] parameters ){
      List< Constructor< ? > > constructorMatches = new ArrayList<>();
      List< Class< ? > > classes = CaliSystem.partialMatchClass( objectNamePart );
      for ( Class< ? > clazz : classes ) {
         List< Constructor< ? > > constructors = CaliSystem.findConstructors( clazz, parameters.length );
         for ( Constructor< ? > constructor : constructors ) {
            if ( SUGGESTIONS.matchesSignature( constructor, parameters ) ) {
               constructorMatches.add( constructor );
            }
         }
      }
      return constructorMatches;
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
            case EMPTY_NO_OPEN:
            case OPEN_NO_PARAMETERS:
            case PARAMETERS_NO_CLOSE:
               return false;
            case SUCCESS:
               Object[] parameters = result.getParameters();
               List< Constructor< ? > > constructors = retrieveConstructors( objectNamePart, parameters );
               for ( Constructor< ? > constructor : constructors ) {
                  if ( ParameterSuggestions.matchesExactSignature( constructor, parameters ) ) {
                     return true;
                  }
               }
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
               List< Constructor< ? > > constructors = retrieveConstructors( objectNamePart, parameters );
               if ( constructors.isEmpty() ) {
                  return null;
               } else if ( constructors.size() == 1 ) {
                  value.setConstructor( constructors.get( 0 ) );
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
        
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         CodeParametersResult result = CaliParserUtilities.extractParameters( expression );
         
         switch ( result.getResult() ) {
            case DOES_NOT_OPEN:
            case EMPTY_NO_OPEN:
            case OPEN_NO_PARAMETERS:
               return autoCorrectConstructorName( objectClasses );
            case PARAMETERS_NO_CLOSE:
            case SUCCESS:
               return autoCorrectParameters( objectClasses, result.getParameters() );
            default:
               return null;
         }
      }
      return null;
   }// End Method
   
   /**
    * Method to create an auto correct for the {@link Constructor} name given the matching classes.
    * @param matchingClasses the {@link Class}es that match the input.
    * @return the {@link Constructor} name for one match, or common prefix for multiple matches.
    */
   private String autoCorrectConstructorName( List< Class< ? > > matchingClasses ) {
      if ( matchingClasses.size() == 1 ) {
         Class< ? > match = matchingClasses.get( 0 );
         return match.getSimpleName() + CaliParserUtilities.open();
      }
      List< String > names = new ArrayList<>();
      matchingClasses.forEach( clazz -> names.add( clazz.getSimpleName() ) );
      String commonPrefix = CaliParserUtilities.lowestCommonSubstring( names );
      return commonPrefix;
   }// End Method
   
   /**
    * Method to auto correct the parameters for the given matching {@link Class}es and parameters provided.
    * @param matchingClasses the {@link Class}es that match this input.
    * @param parameters the parameters input.
    * @return no auto correct if no matching classes, auto correct construct name if only a single match,
    * or auto correct for the constructor that matches the exact parameters entered. 
    */
   private String autoCorrectParameters( List< Class< ? > > matchingClasses, Object[] parameters ) {
      if ( matchingClasses.size() == 0 ) {
         return null;
      } else if ( matchingClasses.size() > 1 ) {
         return autoCorrectConstructorName( matchingClasses );
      } else {
         Class< ? > match = matchingClasses.get( 0 );
         List< Constructor< ? > > constructors = CaliSystem.findConstructors( match, parameters.length );
         Constructor< ? > matchingConstructor = null;
         if ( constructors.size() == 0 ) {
            return null;
         } else if ( constructors.size() > 1 ) {
            for ( Constructor< ? > constructor : constructors ) {
               if ( ParameterSuggestions.matchesSignature( constructor, parameters ) ) {
                  matchingConstructor = constructor;
                  break;
               }
            }
         } else {
            matchingConstructor = constructors.get( 0 );
         }
         String parameterSuggestion = CaliSuggestionUtilities.autoCorrectAllParameters( matchingConstructor, parameters );
         return matchingConstructor.getDeclaringClass().getSimpleName() + parameterSuggestion;
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
