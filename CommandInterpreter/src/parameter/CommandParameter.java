/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

import command.Command;

/**
 * The {@link CommandParameter} defines the interface for interacting with a parameter
 * of a {@link Command}.
 */
public interface CommandParameter {
   
   /**
    * Method to determine whether the given {@link String} expression partial matches the 
    * {@link CommandParameter}. 
    * @param expression the {@link String} expression input.
    * @return true if the input is contained within expected parameters values.
    */
   public boolean partialMatches( String expression );
   
   /**
    * Method to determine whether the given {@link String} expression completely matches the
    * {@link CommandParameter}.
    * @param expression the {@link String} expression input.
    * @return true if the input is appropriate for execution of the {@link Command}.
    */
   public boolean completeMatches( String expression );
   
   /**
    * Method to parse a valid {@link Object} for this {@link CommandParameter} from the expression
    * input.
    * @param expression the {@link String} expression input.
    * @return the parsed {@link Object}, or null if expression does not match.
    */
   public Object parseObject( String expression );

}// End Interface

