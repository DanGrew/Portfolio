/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import command.Command;

/**
 * The {@link CommandParameters} provides a wrapper for the {@link CommandParameter}s needed for a {@link Command}.
 * This is used for executing {@link Function}s and providing controlled access to the {@link CommandParameter}s.
 */
public class CommandParameters implements Iterable< CommandParameter >{
   
   private Map< CommandParameter, Object > parameters;
   
   /**
    * Constructs a new {@link CommandParameters}.
    */
   public CommandParameters(){
      parameters = new LinkedHashMap< CommandParameter, Object >();
   }// End Constructor
   
   /**
    * Method to apply the given {@link CommandParameter}s to this {@link CommandParameters}. This will assume
    * null values until parameterized.
    * @param parameters the array of {@link CommandParameter}s.
    */
   public void applyParameters( CommandParameter... parameters ) {
      for ( CommandParameter parameter : parameters ) {
         setParameter( parameter, null );
      }
   }// End Method
   
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
   
   /**
    * Method to determine whether the separated parts of the expression match the {@link CommandParameter}s
    * associated.
    * @param expressionParts the {@link String} parts of the expression.
    * @return true if all parts completely match other than the last which only has to partially match.
    */
   public boolean partialMatches( String[] expressionParts ) {
      if ( expressionParts.length == 0 ) {
         return true;
      } else if ( expressionParts.length > parameters.size() ) {
         return false;
      } else {
         Iterator< CommandParameter > paramIterator = parameters.keySet().iterator();
         for ( int i = 0; i < expressionParts.length - 1; i++ ) {
            CommandParameter parameter = paramIterator.next();
            if ( !parameter.completeMatches( expressionParts[ i ] ) ) {
               return false;
            }
         }
         CommandParameter parameter = paramIterator.next();
         if ( !parameter.partialMatches( expressionParts[ expressionParts.length - 1 ] ) ) {
            return false;
         }
         return true;
      }
   }// End Method
   
   /**
    * Method to determine whether the separated parts of the expression match the {@link CommandParameter}s
    * associated exactly.
    * @param expressionParts the {@link String} parts of the expression.
    * @return true if all parts completely match.
    */
   public boolean completeMatches( String[] expressionParts ) {
      if ( expressionParts.length == 0 ) {
         return false;
      } else if ( expressionParts.length != parameters.size() ) {
         return false;
      } else {
         Iterator< CommandParameter > paramIterator = parameters.keySet().iterator();
         for ( int i = 0; i < expressionParts.length; i++ ) {
            CommandParameter parameter = paramIterator.next();
            if ( !parameter.completeMatches( expressionParts[ i ] ) ) {
               return false;
            }
         }
         return true;
      }
   }// End Method
   
   /**
    * Method to parameterize the {@link CommandParameter}s with the parts of the expression input.
    * @param expressionParts the {@link String} parts of the expression providing the parameters.
    */
   public void parameterize( String[] expressionParts ) {
      if ( expressionParts.length == 0 ) {
         return;
      } else if ( expressionParts.length > parameters.size() ) {
         return;
      } else {
         Iterator< CommandParameter > paramIterator = parameters.keySet().iterator();
         for ( int i = 0; i < expressionParts.length; i++ ) {
            CommandParameter parameter = paramIterator.next();
            Object value = parameter.parseObject( expressionParts[ i ] );
            setParameter( parameter, value );
         }
      }
   }// End Method
   
   /**
    * Method to suggest a auto completion for the given parameters. This specifically looks at the
    * last parameters and attempts to auto complete that.
    * @param parameterValues the {@link String} parameters input.
    * @return the suggestion.
    */
   public String autoComplete( String[] parameterValues ) {
      if ( parameterValues.length == 0 ) {
         return null;
      } else if ( parameterValues.length > parameters.size() ) {
         return null;
      } else {
         Iterator< CommandParameter > paramIterator = parameters.keySet().iterator();
         for ( int i = 0; i < parameterValues.length - 1; i++ ) {
            paramIterator.next();
         }
         CommandParameter parameter = paramIterator.next();
         return parameter.autoComplete( parameterValues[ parameterValues.length - 1 ] );
      }
   }// End Method
   
   /**
    * Method to determine whether the {@link CommandParameters} has complete values for {@link CommandParameter}s
    * associated.
    * @return true if all {@link CommandParameter}s are parameterized.
    */
   public boolean isComplete(){
      for ( Entry< CommandParameter, Object > entry : parameters.entrySet() ) {
         if ( entry.getValue() == null ) {
            return false;
         }
      }
      return true;
   }// End Method
   
   /**
    * Method to reset all values associated with {@link CommandParameter}s.
    */
   public void reset(){
      for ( Entry< CommandParameter, Object > entry : parameters.entrySet() ) {
         entry.setValue( null );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Iterator< CommandParameter > iterator() {
      return parameters.keySet().iterator();
   }// End Method
   

}// End Class
