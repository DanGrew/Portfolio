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
import annotation.CaliAnnotationSyntax;

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
      String objectNamePart = extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         if ( expression.isEmpty() ) {
            return true;
         }
         
         //Remove open brackets.
         if ( !expression.startsWith( CaliAnnotationSyntax.open() ) ) {
            return false;
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliAnnotationSyntax.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return true;
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliAnnotationSyntax.close() ) ) {
            return true;
         }
         
         //Get parameters.
         String[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliAnnotationSyntax.regexClose(), 
                  CaliAnnotationSyntax.parameterDelimiter()
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
    * Method to extract the type of object from the expression.
    * @param expression the expression to parse from.
    * @return the {@link String} part of the expression for the  object type.
    */
   private String extractObjectType( String expression ) {
      String[] objectClassNameParts = CommandParameterParseUtilities.parseUpTo( 
               expression, 
               CaliAnnotationSyntax.regexOpen(), 
               CommandParameterParseUtilities.delimiter() 
      );
      if ( objectClassNameParts.length > 1 ) {
         return null;
      }
      
      String objectClassName = objectClassNameParts[ 0 ].trim();
      if ( objectClassName.endsWith( CaliAnnotationSyntax.open() ) ) {
         //Object cannot have ( in the name, safe to assume replacement.
         objectClassName = objectClassName.replaceAll( CaliAnnotationSyntax.regexOpen(), CommandParameterParseUtilities.delimiter() );
         objectClassName = objectClassName.trim();
      }
      String[] split = objectClassName.split( CommandParameterParseUtilities.delimiter() );
      //Object cannot have spaces in the name.
      if ( split.length == 1 ) {
         String objectName = split[ 0 ];
         return objectName;
      }
      return null;
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
      String objectNamePart = extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         if ( expression.isEmpty() ) {
            return false;
         }
         
         //Remove open brackets.
         if ( !expression.startsWith( CaliAnnotationSyntax.open() ) ) {
            return false;
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliAnnotationSyntax.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return false;
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliAnnotationSyntax.close() ) ) {
            return false;
         }
         
         //Get parameters.
         String[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliAnnotationSyntax.regexClose(), 
                  CaliAnnotationSyntax.parameterDelimiter() 
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
      
      String objectNamePart = extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         if ( expression.isEmpty() ) {
            return null;
         }
         
         //Remove open brackets.
         if ( !expression.startsWith( CaliAnnotationSyntax.open() ) ) {
            return null;
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliAnnotationSyntax.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return null;
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliAnnotationSyntax.close() ) ) {
            return null;
         }
         
         //Get parameters.
         Object[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliAnnotationSyntax.regexClose(), 
                  CaliAnnotationSyntax.parameterDelimiter() 
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
      
      String objectNamePart = extractObjectType( expression );
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
         if ( !expression.startsWith( CaliAnnotationSyntax.open() ) ) {
            return autoCorrectUpToOpen( expression, onlyChoice );
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliAnnotationSyntax.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return autoCorrectUpToOpen( expression, onlyChoice );
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliAnnotationSyntax.close() ) ) {
            return autoCorrectUpToOpen( expression, onlyChoice );
         }
         
         //Get parameters.
         String[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliAnnotationSyntax.regexClose(), 
                  CaliAnnotationSyntax.parameterDelimiter()
         );
         
         //Fill types, default assumed string.
         Class< ? >[] parameterTypes = new Class< ? >[ parameters.length ];
         Arrays.fill( parameterTypes, String.class );
         Constructor< ? > constructor = CaliSystem.matchConstructor( objectNamePart, parameterTypes );
         if ( constructor != null ) {
            StringBuffer autoCorrect = new StringBuffer();
            autoCorrect.append( constructor.getDeclaringClass().getSimpleName() );
            autoCorrect.append( CaliAnnotationSyntax.open() );
            autoCorrect.append( CommandParameterParseUtilities.delimiter() );
            for ( String parameter : parameters ) {
               autoCorrect.append( parameter ).append( CaliAnnotationSyntax.parameterDelimiter() );
               autoCorrect.append( CommandParameterParseUtilities.delimiter() );
            }
            autoCorrect.deleteCharAt( autoCorrect.lastIndexOf( CaliAnnotationSyntax.parameterDelimiter() ) );
            autoCorrect.append( CaliAnnotationSyntax.close() );
            
            autoCorrect.append( CommandParameterParseUtilities.delimiter() );
            autoCorrect.append( expression );
            return autoCorrect.toString();
         }
      }
      return null;
   }// End Method
   
   /**
    * Method to auto correct the expression up to the {@link CaliAnnotationSyntax#open()} tag.
    * @param expression the expression to auto correct.
    * @param onlyChoice the {@link Class} single choice available.
    * @return the auto corrected expression.
    */
   private String autoCorrectUpToOpen( String expression, Class< ? > onlyChoice ){
      if ( onlyChoice != null ) {
         String autoCorrect = onlyChoice.getSimpleName() + CaliAnnotationSyntax.open() + CommandParameterParseUtilities.delimiter() + expression;
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
      String objectNamePart = extractObjectType( expression );
      List< Class< ? > > objectClasses = parseObjectClass( objectNamePart );
      if ( !objectClasses.isEmpty() ) {
         expression = CommandParameterParseUtilities.reduce( expression, objectNamePart );
         if ( expression.isEmpty() ) {
            return expression;
         }
         
         //Remove open brackets.
         if ( !expression.startsWith( CaliAnnotationSyntax.open() ) ) {
            return expression;
         }
         expression = CommandParameterParseUtilities.reduce( expression, CaliAnnotationSyntax.regexOpen() ).trim();
         
         if ( expression.isEmpty() ) {
            return expression;
         }
         
         //Cannot match partial constructor, allow any parameters before defining end bracket.
         if ( !expression.contains( CaliAnnotationSyntax.close() ) ) {
            //Match everything until close specified.
            return "";
         }
         
         //Get parameters.
         String[] parameters = CommandParameterParseUtilities.parseUpTo( 
                  expression, 
                  CaliAnnotationSyntax.regexClose(), 
                  CaliAnnotationSyntax.parameterDelimiter() 
         );
         
         for ( String parameter : parameters ) {
            expression = CommandParameterParseUtilities.reduce( expression, parameter.trim() );
            if ( expression.startsWith( CaliAnnotationSyntax.parameterDelimiter() ) ) {
               expression = CommandParameterParseUtilities.reduce( expression, CaliAnnotationSyntax.parameterDelimiter() );
            }
         }
         
         if ( expression.startsWith( CaliAnnotationSyntax.close() ) ) {
            expression = CommandParameterParseUtilities.reduce( expression, CaliAnnotationSyntax.regexClose() ).trim();
         }
      }
      return expression;
   }// End Method

}// End Class
