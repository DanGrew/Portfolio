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
import java.util.Arrays;
import java.util.List;

import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import system.CaliSystem;
import annotation.Cali;
import annotation.CaliParserUtilities;
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
               String[] parameters = result.getParameters();
               //Fill types, default assumed string.
               Class< ? >[] parameterTypes = new Class< ? >[ parameters.length ];
               Arrays.fill( parameterTypes, String.class );
               Constructor< ? > constructor = CaliSystem.matchConstructor( objectNamePart, parameterTypes );
               if ( constructor != null ) {
                  return true;
               }
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
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String objectNamePart = CaliParserUtilities.extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         if ( expression.isEmpty() ) {
            return false;
         }
         
         //Remove open brackets.
         if ( !expression.startsWith( CaliParserUtilities.open() ) ) {
            return false;
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return false;
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliParserUtilities.close() ) ) {
            return false;
         }
         
         //Get parameters.
         String[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliParserUtilities.regexClose(), 
                  CaliParserUtilities.parameterDelimiter() 
         );
         
         //Fill types, default assumed string.
         Class< ? >[] parameterTypes = new Class< ? >[ parameters.length ];
         Arrays.fill( parameterTypes, String.class );
         Constructor< ? > constructor = CaliSystem.matchConstructor( objectNamePart, parameterTypes );
         if ( constructor != null ) {
            return true;
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
         if ( expression.isEmpty() ) {
            return null;
         }
         
         //Remove open brackets.
         if ( !expression.startsWith( CaliParserUtilities.open() ) ) {
            return null;
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return null;
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliParserUtilities.close() ) ) {
            return null;
         }
         
         //Get parameters.
         Object[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliParserUtilities.regexClose(), 
                  CaliParserUtilities.parameterDelimiter() 
         );
         
         //Fill types, default assumed string.
         Class< ? >[] parameterTypes = new Class< ? >[ parameters.length ];
         Arrays.fill( parameterTypes, String.class );
         Constructor< ? > constructor = CaliSystem.matchConstructor( objectNamePart, parameterTypes );
         if ( constructor != null ) {
            value.setConstructor( constructor );
            value.addParameters( parameters );
            return value;
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
         if ( expression.isEmpty() ) {
            return autoCorrectUpToOpen( expression, onlyChoice );
         }
         
         //Remove open brackets.
         if ( !expression.startsWith( CaliParserUtilities.open() ) ) {
            return autoCorrectUpToOpen( expression, onlyChoice );
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return autoCorrectUpToOpen( expression, onlyChoice );
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliParserUtilities.close() ) ) {
            return autoCorrectUpToOpen( expression, onlyChoice );
         }
         
         //Get parameters.
         String[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliParserUtilities.regexClose(), 
                  CaliParserUtilities.parameterDelimiter()
         );
         
         //Fill types, default assumed string.
         Class< ? >[] parameterTypes = new Class< ? >[ parameters.length ];
         Arrays.fill( parameterTypes, String.class );
         Constructor< ? > constructor = CaliSystem.matchConstructor( objectNamePart, parameterTypes );
         if ( constructor != null ) {
            StringBuffer autoCorrect = new StringBuffer();
            autoCorrect.append( constructor.getDeclaringClass().getSimpleName() );
            autoCorrect.append( CaliParserUtilities.open() );
            autoCorrect.append( CommandParameterParseUtilities.delimiter() );
            for ( String parameter : parameters ) {
               autoCorrect.append( parameter ).append( CaliParserUtilities.parameterDelimiter() );
               autoCorrect.append( CommandParameterParseUtilities.delimiter() );
            }
            autoCorrect.deleteCharAt( autoCorrect.lastIndexOf( CaliParserUtilities.parameterDelimiter() ) );
            autoCorrect.append( CaliParserUtilities.close() );
            
            autoCorrect.append( CommandParameterParseUtilities.delimiter() );
            autoCorrect.append( expression );
            return autoCorrect.toString();
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
         if ( expression.isEmpty() ) {
            return expression;
         }
         
         //Remove open brackets.
         if ( !expression.startsWith( CaliParserUtilities.open() ) ) {
            return expression;
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return expression;
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliParserUtilities.close() ) ) {
            //Match everything until close specified.
            return "";
         }
         
         //Get parameters.
         String[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliParserUtilities.regexClose(), 
                  CaliParserUtilities.parameterDelimiter() 
         );
         
         for ( String parameter : parameters ) {
            expression = CommandParameterParseUtilities.reduce( expression, parameter.trim() );
            if ( expression.startsWith( CaliParserUtilities.parameterDelimiter() ) ) {
               expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.parameterDelimiter() );
            }
         }
         
         if ( expression.startsWith( CaliParserUtilities.close() ) ) {
            expression = CommandParameterParseUtilities.reduce( expression, CaliParserUtilities.regexClose() ).trim();
         }
      }
      return expression;
   }// End Method

}// End Class
