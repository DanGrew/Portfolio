/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters.extensions;

import java.util.Arrays;
import java.util.List;

import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import propertytype.PropertyType;
import architecture.request.RequestSystem;

/**
 * The {@link PropertyTypeAndValueParameterImpl} is responsible for providing a {@link CommandParameter}
 * that accepts a {@link PropertyType} and then parses a value for that {@link PropertyType}.
 */
public class PropertyTypeAndValueParameterImpl implements CommandParameter {

   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "REF[ PropertyType ] + value";
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public List< String > getSuggestions( String expression ) {
      return Arrays.asList( getParameterType() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String propertyType = parameters[ 0 ];
      String value = parameters[ 1 ];
      
      PropertyType actualType = CommandParameterParseUtilities.firstPartialMatchesSingleton( propertyType, PropertyType.class );
      if ( actualType != null ) {
         if ( value.isEmpty() ) {
            return true;
         } else {
            Object parsed = actualType.deserialize( value );
            if ( parsed == null ) {
               return false;
            } else {
               return true;
            }
         }
      } else {
         return false;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String propertyType = parameters[ 0 ];
      String value = parameters[ 1 ];
      
      PropertyType actualType = RequestSystem.retrieve( PropertyType.class, propertyType );
      if ( actualType != null ) {
         if ( value.isEmpty() ) {
            return false;
         } else {
            Object parsed = actualType.deserialize( value );
            if ( parsed == null ) {
               return false;
            } else {
               return true;
            }
         }
      } else {
         return false;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String propertyType = parameters[ 0 ];
      String value = parameters[ 1 ];
      
      PropertyType actualType = RequestSystem.retrieve( PropertyType.class, propertyType );
      if ( actualType != null ) {
         if ( value.isEmpty() ) {
            return null;
         } else {
            Object parsed = actualType.deserialize( value );
            if ( parsed == null ) {
               return null;
            } else {
               return new PropertyTypeAndValue( actualType, parsed );
            }
         }
      } else {
         return null;
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      String propertyType = parameters[ 0 ];
      String value = parameters[ 1 ];
      
      PropertyType actualType = CommandParameterParseUtilities.firstPartialMatchesSingleton( propertyType, PropertyType.class );
      if ( actualType != null ) {
         if ( value.isEmpty() ) {
            return actualType.getIdentification();
         } else {
            return actualType.getIdentification() + CommandParameterParseUtilities.delimiter() + value;
         }
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String extractInput( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      return CommandParameterParseUtilities.reduce( expression, parameters );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String parseParameter( String expression ) {
      String[] parameters = CommandParameterParseUtilities.parseParameters( 2, expression );
      return String.join( CommandParameterParseUtilities.delimiter(), parameters );
   }// End Method

}// End Class
