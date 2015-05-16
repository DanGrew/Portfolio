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
import parameter.CommandParameters;

/**
 * The {@link ParameterizedCommandImpl} provides an implementation of the {@link Command} interface
 * that requires {@link CommandParameter}s in order for its {@link Function} to be executed.
 * @param <ReturnT> the type of the result when executed.
 */
public class ParameterizedCommandImpl< ReturnT > extends InstructionCommandImpl< ReturnT > implements Command< ReturnT >{

   private CommandParameters parameters;
   
   /**
    * Constructs a new {@link ParameterizedCommandImpl}.
    * @param key the key to be matched for the {@link Command}.
    * @param description a user friendly description of the {@link Command}.
    * @param function the {@link Function} to be executed.
    * @param parameters the {@link CommandParameter} that must be provided to execute the {@link Function}.
    */
   public ParameterizedCommandImpl( 
            String key, 
            String description, 
            Function< CommandParameters, ReturnT > function, 
            CommandParameter... parameters 
   ) {
      super( key, description, function );
      this.parameters = new CommandParameters();
      this.parameters.applyParameters( parameters );
   }// End Constructor
   
   /**
    * Method to identify the {@link CommandParameter}s provided in the given {@link String}
    * expression input.
    * @param expression the input provided that should match the {@link Command} syntax.
    * @return an array of {@link String} parameters.
    */
   private String[] identifyParameters( String expression ) {
      String[] parts = expression.trim().split( " " );
      String[] parameterValues = new String[ parts.length - 1 ];
      System.arraycopy( parts, 1, parameterValues, 0, parameterValues.length );
      return parameterValues;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean matches( String expression ) {
      if ( super.matches( expression ) ) {
         String[] parameterValues = identifyParameters( expression );
         return parameters.partialMatches( parameterValues );
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
      super.parameterize( expression );
      String[] parameterValues = identifyParameters( expression );
      parameters.parameterize( parameterValues );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public ReturnT execute() {
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

}// End Class
