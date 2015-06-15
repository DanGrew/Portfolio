/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.key;

import java.util.List;

import model.singleton.Singleton;
import parameter.CommandParameterParseUtilities;
import system.CaliSystem;
import annotation.CaliAnnotationSyntax;

import command.CommandKey;

/**
 * {@link CaliStatementKeyImpl} is the variable {@link CommandKey} that matches
 * {@link Singleton}s by {@link Singleton#getIdentification()}.
 */
public class CaliStatementKeyImpl implements CommandKey {

   /**
    * {@inheritDoc}
    */
   @Override public String getStringKey() {
      return "<singleton>.";
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String key = CommandParameterParseUtilities.parseSingle( expression );
      if ( key.endsWith( CaliAnnotationSyntax.statementDelimiter() ) ) {
         key = key.substring( 0, key.length() - 1 );
      }
      List< Singleton > matches = CaliSystem.partialMatchSingletons( key );
      return !matches.isEmpty();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String key = CommandParameterParseUtilities.parseSingle( expression );
      if ( key.endsWith( CaliAnnotationSyntax.statementDelimiter() ) ) {
         key = key.substring( 0, key.length() - 1 );
      } else {
         return false;
      }
      List< Singleton > matches = CaliSystem.partialMatchSingletons( key );
      return !matches.isEmpty();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String key = CommandParameterParseUtilities.parseSingle( expression );
      if ( key.endsWith( CaliAnnotationSyntax.statementDelimiter() ) ) {
         key = key.substring( 0, key.length() - 1 );
      }
      List< Singleton > matches = CaliSystem.partialMatchSingletons( key );
      if ( matches.isEmpty() || matches.size() > 1 ) {
         return null;
      } else {
         return matches.get( 0 ).getIdentification() + CaliAnnotationSyntax.statementDelimiter();
      }
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String extractKeyExpression( String expression ) {
      String key = CommandParameterParseUtilities.parseSingle( expression );
      return key;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public String removeKeyFromInput( String expression ) {
      String key = CommandParameterParseUtilities.parseSingle( expression );
      return CommandParameterParseUtilities.reduce( expression, key );
   }// End Method

}// End Class
