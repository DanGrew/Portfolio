/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package parameter.wrapper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;

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
   @Override public List< String > getSuggestions( String expression ) {
      if ( expression.trim().isEmpty() ) {
         return parameters.keySet().iterator().next().getSuggestions( expression );
      }
      for ( CommandParameter parameter : parameters.keySet() ) {
         if ( parameter.completeMatches( expression ) ) {
            expression = parameter.extractInput( expression );
            continue;
         } else if ( parameter.partialMatches( expression ) ) {
            return parameter.getSuggestions( expression );
         } else {
            break;
         }
      }
      return Arrays.asList( CommandParameter.READY );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      if ( expression.trim().isEmpty() ) {
         return true;
      }
      for ( CommandParameter parameter : parameters.keySet() ) {
         if ( parameter.completeMatches( expression ) ) {
            expression = parameter.extractInput( expression );
            continue;
         } else if ( parameter.partialMatches( expression ) ) {
            expression = parameter.extractInput( expression );
         } else {
            break;
         }
      }
      if ( expression.trim().isEmpty() ) {
         return true;
      } else {
         return false;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      for ( CommandParameter parameter : parameters.keySet() ) {
         if ( expression.trim().isEmpty() ) {
            return false;
         } else if ( parameter.completeMatches( expression ) ) {
            expression = parameter.extractInput( expression );
            continue;
         } else {
            break;
         }
      }
      if ( expression.trim().isEmpty() ) {
         return true;
      } else {
         return false;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( String expression ) {
      for ( CommandParameter parameter : parameters.keySet() ) {
         Object value = parameter.parseObject( expression );
         setParameter( parameter, value );
         expression = parameter.extractInput( expression );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      if ( expression.trim().isEmpty() ) {
         return null;
      }
      StringBuffer buffer = new StringBuffer();
      for ( CommandParameter parameter : parameters.keySet() ) {
         if ( expression.isEmpty() ) {
            break;
         } else if ( parameter.partialMatches( expression ) ) {
            String suggestion = parameter.autoComplete( expression );
            if ( suggestion == null ) {
               if ( parameter.completeMatches( expression ) ) {
                  buffer.append( parameter.parseParameter( expression ) );
               } else {
                  //Safe escape, if can't suggestion one, don't suggest any.
                  return null;
               }
            } else {
               buffer.append( suggestion );
            }
            buffer.append( CommandParameterParseUtilities.delimiter() );
            expression = parameter.extractInput( expression );
         } else {
            break;
         }
      }
      buffer.append( expression );
      String suggestion = buffer.toString().trim();
      if ( suggestion.isEmpty() ) {
         return null;
      } else {
         return suggestion;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isComplete(){
      for ( Entry< CommandParameter, Object > entry : parameters.entrySet() ) {
         if ( entry.getKey().requiresValue() && entry.getValue() == null ) {
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
