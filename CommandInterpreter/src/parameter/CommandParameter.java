/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

import java.util.List;

/**
 * The {@link CommandParameter} defines the interface for interacting with a parameter
 * of a {@link Command}.
 */
public interface CommandParameter {
   /** Constant defining the suggestion when the {@link Command} or {@link CommandParameter} is complete.**/
   public static final String READY = "READY";   
   
   /**
    * Method to get a {@link String} description of the type of {@link CommandParameter} being
    * applied.
    * @return a {@link String} description of the {@link CommandParameter}.
    */
   public String getParameterType();
   
   /**
    * Method to get a {@link List} of suggestions for completion of the {@link CommandParameter}.
    * @param input the current input for the {@link CommandParameter}.
    * @return a {@link List} of {@link String} options for the {@link CommandParameter}.
    */
   public List< String > getSuggestions( String expression );
   
   /**
    * Method to determine whether the {@link CommandParameter} requires some information to be
    * set for it.
    * @return true if a value must be set, false otherwise.
    */
   public default boolean requiresValue() {
      return true;
   }// End Method
   
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

   /**
    * Method to extract the parameter values from the given expression.
    * @param expression the input entered for the {@link Command}.
    * @return the expression minus the parameter values for this {@link CommandParameter}.
    */
   public default String extractInput( String expression ) {
      String parameter = parseParameter( expression );
      return CommandParameterParseUtilities.reduce( expression, parameter );
   }// End Method
   
   /**
    * Method to parse the value in the expression that relates to the parameter values of this
    * {@link CommandParameter}. 
    * @param expression the expression containing the parameters.
    * @return the {@link String} representation of the parameter values defined in the expression,
    * as they are in the expression.
    */
   public default String parseParameter( String expression ) {
      String parameter = CommandParameterParseUtilities.parseSingle( expression );
      return parameter;
   }// End Method

}// End Interface

