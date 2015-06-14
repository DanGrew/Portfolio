/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.lang.reflect.Constructor;
import java.util.Arrays;

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
      Class< ? > objectClazz = parseObjectClass( objectNamePart );
      if ( objectClazz != null ) {
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
                  CommandParameterParseUtilities.delimiter() 
         );
         
         //Fill types, default assumed string.
         Class< ? >[] parameterTypes = new Class< ? >[ parameters.length ];
         Arrays.fill( parameterTypes, String.class );
         try {
            objectClazz.getConstructor( parameterTypes );
            return true;
         } catch ( NoSuchMethodException | SecurityException e ) {
            return false;
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
   private Class< ? > parseObjectClass( String value ){
      if ( value == null ) {
         return null;
      }
      Class< ? > clazz = CaliSystem.partialMatchClass( value );
      if ( clazz != null ) {
         return clazz;
      }
      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      return false;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      return null;
   }// End Method

}// End Class
