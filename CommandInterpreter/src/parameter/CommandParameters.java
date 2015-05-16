/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import command.Command;

/**
 * The {@link CommandParameters} provides a wrapper for the {@link CommandParameter}s needed for a {@link Command}.
 * This is used for executing {@link Function}s and providing controlled access to the {@link CommandParameter}s.
 */
public class CommandParameters {
   
   private Map< CommandParameter, Object > parameters;
   
   /**
    * Constructs a new {@link CommandParameters}.
    */
   public CommandParameters(){
      parameters = new LinkedHashMap< CommandParameter, Object >();
   }// End Constructor
   
   /**
    * Setter for the value associated with the given {@link CommandParameter}.
    * @param parameter the {@link CommandParameter} to set.
    * @param value the value of the {@link CommandParameter}.
    */
   public void setParameter( CommandParameter parameter, Object value ){
      parameters.put( parameter, value );
   }// End Method
   
   /**
    * Getter for the value associated with the given {@link CommandParameter}.
    * @param parameter the {@link CommandParameter} in question.
    * @return the associated value.
    */
   public Object getParameter( CommandParameter parameter ) {
      return parameters.get( parameter );
   }// End Method
   
   /**
    * Getter for the value associated with the given {@link CommandParameter} when the exact type
    * is known.
    * @param parameter the {@link CommandParameter} in quesiton.
    * @param expected the expected {@link Class} of the value.
    * @return the associated value in the correct type.
    */
   @SuppressWarnings("unchecked") 
   public < TypeT > TypeT getExpectedParameter( CommandParameter parameter, Class< TypeT > expected ) {
      return ( TypeT )getParameter( parameter );
   }// End Method

}// End Class
