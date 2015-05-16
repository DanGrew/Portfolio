/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import java.util.function.Function;

import parameter.CommandParameter;
import parameter.CommandParameters;

/**
 * The {@link InstructionCommandImpl} provides an implementaton of the {@link Command} to 
 * support a simple instruction where no {@link CommandParameter}s are required.
 * @param <ReturnT> the type of the return value of the {@link Command}.
 */
public class InstructionCommandImpl< ReturnT > implements Command< ReturnT > {

   private String key;
   private String description;
   private Function< CommandParameters, ReturnT > function;
   
   /**
    * Constructs a new {@link InstructionCommandImpl}.
    * @param key the {@link String} key that must be matched.
    * @param description a user friendly description of the {@link Command}.
    * @param function the {@link Function} to execute.
    */
   public InstructionCommandImpl( String key, String description, Function< CommandParameters, ReturnT > function ) {
      this.key = key;
      this.description = description;
      this.function = function;
   }// End Constructor
   
   /**
    * Getter for the key to match to this {@link Command}.
    * @return the {@link String} key.
    */
   private String getKey(){
      return key;
   }// End Method
   
   /**
    * Getter for the {@link Function} to execute when this {@link Command} is matched.
    * @return the {@link Function} to execute.
    */
   protected Function< CommandParameters, ReturnT > getFunction(){
      return function;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String trimmedExpression = expression.trim();
      String[] splitExpression = trimmedExpression.split( " " );
      if ( splitExpression[ 0 ].length() == 0 ) {
         return true;
      }
      return getKey().toLowerCase().startsWith( splitExpression[ 0 ].toLowerCase() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String trimmedExpression = expression.trim();
      String[] splitExpression = trimmedExpression.split( " " );
      if ( splitExpression[ 0 ].length() == 0 ) {
         return false;
      }
      return getKey().toLowerCase().equals( splitExpression[ 0 ].toLowerCase() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( CommandParameter parameter, Object value ) {}
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( String expression ) {}
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      if ( partialMatches( expression ) ) {
         return suggestKey( expression );
      } else {
         return null;
      }
   }// End Method
   
   /**
    * Method to suggest the auto complete for the key if appropriate.
    * @param expression the {@link String} expression to auto complete.
    * @return the suggested auto completion.
    */
   protected String suggestKey( String expression ) {
      String[] expressionParts = expression.trim().split( " " );
      if ( expressionParts.length == 1 ){
         return getKey();
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public ReturnT execute( String expression ) {
      parameterize( expression );
      return execute();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public ReturnT execute() {
      return function.apply( null );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resetParameters() {}
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      if ( description == null ) {
         return getKey();
      } else {
         return getKey() + ": " + description;
      }
   }// End Method

}// End Class
