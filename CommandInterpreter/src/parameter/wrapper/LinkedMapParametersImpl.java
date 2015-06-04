/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.wrapper;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import parameter.CommandParameter;
import command.Command;

/**
 * The {@link LinkedMapParametersImpl} provides a wrapper for the {@link CommandParameter}s needed for a {@link Command}.
 * This is used for executing {@link Function}s and providing controlled access to the {@link CommandParameter}s.
 */
public class LinkedMapParametersImpl implements CommandParameters{
   
   private Map< CommandParameter, Object > parameters;
   
   /**
    * Constructs a new {@link LinkedMapParametersImpl}.
    */
   public LinkedMapParametersImpl(){
      parameters = new LinkedHashMap< CommandParameter, Object >();
   }// End Constructor
   
   /**
    * Method to identify the {@link CommandParameter}s provided in the given {@link String}
    * expression input.
    * @param expression the input provided that should match the {@link Command} syntax.
    * @return an array of {@link String} parameters.
    */
   private String[] identifyParameters( String expression ) {
      String[] parts = expression.trim().split( " " );
      String[] parameterValues = new String[ parts.length ];
      System.arraycopy( parts, 0, parameterValues, 0, parameterValues.length );
      return parameterValues;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void applyParameters( CommandParameter... parameters ) {
      for ( CommandParameter parameter : parameters ) {
         setParameter( parameter, null );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void setParameter( CommandParameter parameter, Object value ){
      parameters.put( parameter, value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object getParameter( CommandParameter parameter ) {
      return parameters.get( parameter );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public < TypeT > TypeT getExpectedParameter( CommandParameter parameter, Class< TypeT > expected ) {
      return expected.cast( getParameter( parameter ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      String[] expressionParts = identifyParameters( expression );
      if ( expressionParts.length == 0 ) {
         return true;
      } else if ( expressionParts.length == 1 && expressionParts[ 0 ].isEmpty() ) {
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
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      String[] expressionParts = identifyParameters( expression );
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
    * {@inheritDoc}
    */
   @Override public void parameterize( String expression ) {
      String[] expressionParts = identifyParameters( expression );
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
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      String[] expressionParts = identifyParameters( expression );
      if ( expressionParts.length == 0 ) {
         return null;
      } else if ( expressionParts.length > parameters.size() ) {
         return null;
      } else {
         Iterator< CommandParameter > paramIterator = parameters.keySet().iterator();
         for ( int i = 0; i < expressionParts.length - 1; i++ ) {
            paramIterator.next();
         }
         CommandParameter parameter = paramIterator.next();
         return parameter.autoComplete( expressionParts[ expressionParts.length - 1 ] );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isComplete(){
      for ( Entry< CommandParameter, Object > entry : parameters.entrySet() ) {
         if ( entry.getValue() == null ) {
            return false;
         }
      }
      return true;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void reset(){
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
