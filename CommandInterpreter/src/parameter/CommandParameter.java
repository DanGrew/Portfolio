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
    * Method to get a {@link String} description of the type of {@link CommandParameter} being
    * applied.
    * @return a {@link String} description of the {@link CommandParameter}.
    */
   public String getParameterType();
   
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
   
   /**
    * Method to suggest an auto completion for the given {@link String} expression for this
    * {@link CommandParameter}.
    * @param expression the {@link String} expression to suggest for.
    * @return the suggested auto completion.
    */
   public String autoComplete( String expression );

}// End Interface

