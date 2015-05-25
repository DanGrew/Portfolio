/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

/**
 * Implementation for the {@link CommandKey} providing a basic {@link String} respresentation.
 */
public class CommandKeyImpl implements CommandKey {

   private String key;
   
   /**
    * Constructs a new {@link CommandKeyImpl}.
    * @param key the key to use in the expression, and for the {@link Command}.
    */
   public CommandKeyImpl( String key ) {
      this.key = key;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getStringKey(){
      return key;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatchesKeyExtract( String expression ) {
      if ( expression == null || expression.length() == 0 ) {
         return true;
      } else {
         return getStringKey().toLowerCase().startsWith( expression.toLowerCase() );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatchesKeyExtract( String expression ) {
      if ( expression == null || expression.length() == 0 ) {
         return false;
      } else {
         return getStringKey().toLowerCase().equals( expression.toLowerCase() );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String keyExtract = extractKeyExpression( expression );
      if ( keyExtract == null ) {
         return false;
      } else {
         return partialMatchesKeyExtract( keyExtract );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String keyExtract = extractKeyExpression( expression );
      if ( keyExtract == null ) {
         return false;
      } else {
         return completeMatchesKeyExtract( keyExtract );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String keyExtract = extractKeyExpression( expression );
      if ( partialMatchesKeyExtract( keyExtract ) ) {
         return getStringKey();
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String extractKeyExpression( String expression ) {
      String trimmedExpression = expression.trim();
      String[] splitExpression = trimmedExpression.split( " " );
      if ( splitExpression[ 0 ].length() == 0 ) {
         return "";
      } else {
         String keyPart = splitExpression[ 0 ];
         if ( partialMatchesKeyExtract( keyPart ) ) {
            return keyPart;
         } else {
            return null;
         }
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String removeKeyFromInput( String expression ) {
      String keyExtract = extractKeyExpression( expression );
      if ( completeMatchesKeyExtract( keyExtract ) ) {
         return expression.replaceFirst( keyExtract, "" ).trim();
      } else {
         return null;
      }
   }// End Method
}// End Class
