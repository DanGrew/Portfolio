/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.util.Arrays;
import java.util.List;

import model.singleton.Singleton;
import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import system.CaliSystem;
import annotation.CaliParserUtilities;

/**
 * {@link SingletonReferenceParameterImpl} is the variable {@link CommandParameter} that matches
 * {@link Singleton}s by {@link Singleton#getIdentification()}.
 */
public class SingletonReferenceParameterImpl implements CommandParameter {

   /**
    * {@inheritDoc}
    */
   @Override public String getParameterType() {
      return "<statment>";
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
      String key = CommandParameterParseUtilities.parseSingle( expression );
      key = CaliParserUtilities.parseForStatement( key );
      List< Singleton > matches = CaliSystem.partialMatchSingletons( key );
      return !matches.isEmpty();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String key = CommandParameterParseUtilities.parseSingle( expression );
      key = CaliParserUtilities.parseForStatement( key );
      if ( key == null ) {
         return false;
      }
      List< Singleton > matches = CaliSystem.completeMatch( key );
      return !matches.isEmpty();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String key = CommandParameterParseUtilities.parseSingle( expression );
      if ( key.endsWith( CaliParserUtilities.statementDelimiter() ) ) {
         key = key.substring( 0, key.length() - 1 );
      }
      List< Singleton > matches = CaliSystem.partialMatchSingletons( key );
      if ( matches.isEmpty() || matches.size() > 1 ) {
         return null;
      } else {
         return matches.get( 0 ).getIdentification() + CaliParserUtilities.statementDelimiter();
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object parseObject( String expression ) {
      if ( completeMatches( expression ) ) {
         String key = CommandParameterParseUtilities.parseSingle( expression );
         if ( key.endsWith( CaliParserUtilities.statementDelimiter() ) ) {
            key = key.substring( 0, key.length() - 1 );
         }
         List< Singleton > matches = CaliSystem.partialMatchSingletons( key );
         if ( matches.isEmpty() || matches.size() > 1 ) {
            return null;
         } else {
            return matches.get( 0 );
         }
      } else {
         return null;
      }
   }// End Method


}// End Class
