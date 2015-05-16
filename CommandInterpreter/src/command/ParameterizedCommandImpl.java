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
   }// End Constructor
   
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
   @Override public ReturnT execute() {
      return getFunction().apply( parameters );
   }// End Method

}// End Class
