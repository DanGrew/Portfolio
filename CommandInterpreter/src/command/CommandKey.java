/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

/**
 * The {@link CommandKey} provides an interface to a object representing a key to a {@link Command} 
 * indicating a particular {@link Command} is to be executed.
 */
public interface CommandKey {

   /**
    * Getter for the {@link String} representation of the {@link CommandKey}.
    * @return the {@link String} key.
    */
   public String getStringKey();

   /**
    * Method to determine whether the given {@link String} expression partially matches the input, allowing
    * parameters to be specified after the key.
    * @param expression the input in question.
    * @return true if the key is partially matched.
    */
   public boolean partialMatches( String expression );

   /**
    * Method to determine whether the given {@link String} expression completely matches the input, allowing
    * parameters to be specified after the key.
    * @param expression the input in question.
    * @return true if the key is partially matched.
    */
   public boolean completeMatches( String expression );
   
   /**
    * Method to request a suggestion for the given expression to auto complete it.
    * @param expression the expression to auto complete.
    * @return the suggestion for auto completing the key.
    */
   public String autoComplete( String expression );

   /**
    * Method to extract the key from the expression, even if not complete.
    * @param expression the expression to extract from.
    * @return the key in the current form in the expression.
    */
   public String extractKeyExpression( String expression );

   /**
    * Method to remove the key from the expression and return the remaining elements.
    * @param expression the expression in question.
    * @return the expression without the key.
    */
   public String removeKeyFromInput( String expression );

}// End Interface