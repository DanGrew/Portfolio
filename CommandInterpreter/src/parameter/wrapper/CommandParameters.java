/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.wrapper;

import java.util.List;

import parameter.CommandParameter;

/**
 * The {@link CommandParameters} provides a wrapper for the {@link CommandParameter}s needed for a {@link Command}.
 * This is used for executing {@link Function}s and providing controlled access to the {@link CommandParameter}s.
 */
public interface CommandParameters extends Iterable< CommandParameter >{
   
   /**
    * Method to get a {@link List} of suggestions for the remainder of the {@link CommandParameters}.
    * @param input the current input for the {@link CommandParameters}.
    * @return a {@link List} of {@link String} options for the {@link CommandParameters}.
    */
   public List< String > getSuggestions( String expression );
   
   /**
    * Method to apply the given {@link CommandParameter}s to this {@link CommandParameters}. This will assume
    * null values until parameterized.
    * @param parameters the array of {@link CommandParameter}s.
    */
   public void applyParameters( CommandParameter... parameters );
   
   /**
    * Setter for the value associated with the given {@link CommandParameter}.
    * @param parameter the {@link CommandParameter} to set.
    * @param value the value of the {@link CommandParameter}.
    */
   public void setParameter( CommandParameter parameter, Object value );
   
   /**
    * Getter for the value associated with the given {@link CommandParameter}.
    * @param parameter the {@link CommandParameter} in question.
    * @return the associated value.
    */
   public Object getParameter( CommandParameter parameter ) ;
   
   /**
    * Getter for the value associated with the given {@link CommandParameter} when the exact type
    * is known.
    * @param parameter the {@link CommandParameter} in quesiton.
    * @param expected the expected {@link Class} of the value.
    * @return the associated value in the correct type.
    */
   public < TypeT > TypeT getExpectedParameter( CommandParameter parameter, Class< TypeT > expected );
   
   /**
    * Method to determine whether the separated parts of the expression match the {@link CommandParameter}s
    * associated.
    * @param expression the {@link String} expression containing only the remaining {@link CommandParameter}s to parse.
    * @return true if all parts completely match other than the last which only has to partially match.
    */
   public boolean partialMatches( String expression );
   
   /**
    * Method to determine whether the separated parts of the expression match the {@link CommandParameter}s
    * associated exactly.
    * @param expression the {@link String} expression containing only the remaining {@link CommandParameter}s to parse.
    * @return true if all parts completely match.
    */
   public boolean completeMatches( String expression );
   
   /**
    * Method to parameterize the {@link CommandParameter}s with the parts of the expression input.
    * @param expression the {@link String} expression containing only the remaining {@link CommandParameter}s to parse.
    */
   public void parameterize( String expression );
   
   /**
    * Method to suggest a auto completion for the given parameters. This specifically looks at the
    * last parameters and attempts to auto complete that.
    * @param expression the {@link String} expression containing only the remaining {@link CommandParameter}s to parse.
    * @return the suggestion.
    */
   public String autoComplete( String expression );
   
   /**
    * Method to determine whether the {@link CommandParameters} has complete values for {@link CommandParameter}s
    * associated.
    * @return true if all {@link CommandParameter}s are parameterized.
    */
   public boolean isComplete();
   
   /**
    * Method to reset all values associated with {@link CommandParameter}s.
    */
   public void reset();

}// End Class
