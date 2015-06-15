/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.key;

import command.CommandKey;

public class CaliStatementKeyImpl implements CommandKey {

   @Override public String getStringKey() {
      return null;
   }

   @Override public boolean partialMatches( String expression ) {
      return false;
   }

   @Override public boolean completeMatches( String expression ) {
      return false;
   }

   @Override public String autoComplete( String expression ) {
      return null;
   }

   @Override public String extractKeyExpression( String expression ) {
      return null;
   }

   @Override public String removeKeyFromInput( String expression ) {
      return null;
   }

}
