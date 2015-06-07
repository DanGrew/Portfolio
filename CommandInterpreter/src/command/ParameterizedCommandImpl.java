/*
 * ----------------------------------------
 *           Command Interpreter
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import java.util.function.Function;

import parameter.CommandParameter;
import parameter.CommandParameterParseUtilities;
import parameter.wrapper.CommandParameters;
import parameter.wrapper.LinkedMapParametersImpl;

/**
 * The {@link ParameterizedCommandImpl} provides an implementation of the {@link Command} interface
 * that requires {@link CommandParameter}s in order for its {@link Function} to be executed.
 * @param <ReturnT> the type of the result when executed.
 */
public class ParameterizedCommandImpl< ReturnT > extends InstructionCommandImpl< ReturnT > implements Command< ReturnT >{

   private CommandParameters parameters;
   
   /**
    * Constructs a new {@link ParameterizedCommandImpl}.
    * @param key the {@link CommandKey} to be matched for the {@link Command}.
    * @param description a user friendly description of the {@link Command}.
    * @param function the {@link Function} to be executed.
    * @param parameters the {@link CommandParameter} that must be provided to execute the {@link Function}.
    */
   public ParameterizedCommandImpl( 
            CommandKey key, 
            String description, 
            Function< CommandParameters, CommandResult< ReturnT > > function, 
            CommandParameter... parameters 
   ) {
      super( key, description, function );
      this.parameters = new LinkedMapParametersImpl();
      applyParameters( parameters );
   }// End Constructor
   
   /**
    * Method to apply {@link CommandParameter}s to the {@link Command}.
    * @param parameters the {@link CommandParameter}s to apply.
    */
   protected void applyParameters( CommandParameter... parameters ) {
      this.parameters.applyParameters( parameters );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      if ( super.partialMatches( expression ) ) {
         expression = getCommandKey().removeKeyFromInput( expression );
         return parameters.partialMatches( expression );
      }
      return false;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      if ( super.completeMatches( expression ) ) {
         expression = getCommandKey().removeKeyFromInput( expression );
         return parameters.completeMatches( expression );
      }
      return false;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( CommandParameter parameter, Object value ) {
      super.parameterize( parameter, value );
      parameters.setParameter( parameter, value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( String expression ) {
      if ( completeMatches( expression ) ) {
         super.parameterize( expression );
         expression = getCommandKey().removeKeyFromInput( expression );
         parameters.parameterize( expression );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      expression = getCommandKey().removeKeyFromInput( expression );
      if ( expression == null ) {
         //Does not match key.
         return null;
      }
      String suggestion = parameters.autoComplete( expression );
      if ( suggestion == null ) {
         return getKey();
      } else {
         return getKey() + CommandParameterParseUtilities.delimiter() + suggestion; 
      } 
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public CommandResult< ReturnT > execute() {
      if ( parameters.isComplete() ) {
         return getFunction().apply( parameters );
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resetParameters() {
      super.resetParameters();
      parameters.reset();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append( getKey() );
      for ( CommandParameter parameter : parameters ) {
         buffer.append( " " );
         buffer.append( "<" ).append( parameter.getParameterType() ).append( ">" );
      }
      if ( getDescription() != null ) {
         buffer.append( ": " ).append( getDescription() );
      }
      return buffer.toString();
   }// End Method

}// End Class
