/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import parameter.CommandParameter;

/**
 * The {@link Command} defines the interface for specifying a type of instruction the
 * user can provide to the system to cause an action to be performed.
 * @param <ReturnT> the type of the return.
 */
public interface Command< ReturnT > {
   
   /**
    * Method to determine whether the given expression matches the key for the {@link Command}.
    * @param expression the {@link String} expression to match.
    * @return true if the {@link Command} is appropriate for the expression.
    */
   public boolean matches( String expression );
   
   /**
    * Method to parameterize the {@link Command} by parsing the input and setting the values.
    * @param expression the {@link String} expression provided as input.
    */
   public void parameterize( String expression );
   
   /**
    * Method to parameterize the {@link Command} by setting the associated parameter.
    * @param parameter the {@link CommandParameter} to set.
    * @param value the value for the {@link CommandParameter}.
    */
   public void parameterize( CommandParameter parameter, Object value );
   
   /**
    * Method to execute the given {@link String} input expression.
    * @param expression the input to parse, parameterize and execute.
    * @return the result, null if the expression is not sufficient.
    */
   public ReturnT execute( String expression );
   
   /**
    * Method to execute the {@link Command} and return the result.
    * @return the result of the {@link Command}.
    */
   public ReturnT execute();
   
   /**
    * Method to reset the {@link CommandParameter}s to default and not configured.
    */
   public void resetParameters();

}// End Interface
