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
import parameter.wrapper.CommandParameters;
import parameter.wrapper.LinkedMapParametersImpl;

/**
 * The {@link ParameterizedCommandImpl} provides an implementation of the {@link Command} interface
 * that requires {@link CommandParameter}s in order for its {@link Function} to be executed.
 * @param <ReturnT> the type of the result when executed.
 */
public class ParameterizedCommandImpl< ReturnT > implements Command< ReturnT >{

   private String description;
   private Function< CommandParameters, CommandResult< ReturnT > > function;
   private CommandParameters parameters;
   
   /**
    * Constructs a new {@link ParameterizedCommandImpl}.
    * @param key the {@link CommandParameter} to be matched for the {@link Command}.
    * @param description a user friendly description of the {@link Command}.
    * @param function the {@link Function} to be executed.
    * @param parameters the {@link CommandParameter} that must be provided to execute the {@link Function}.
    */
   public ParameterizedCommandImpl( 
            String description, 
            Function< CommandParameters, CommandResult< ReturnT > > function, 
            CommandParameter... parameters 
   ) {
      this.description = description;
      this.function = function;
      this.parameters = new LinkedMapParametersImpl();
      applyParameters( parameters );
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getDescription() {
      return description;
   }// End Method
   
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
      return parameters.partialMatches( expression );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      return parameters.completeMatches( expression );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( CommandParameter parameter, Object value ) {
      parameters.setParameter( parameter, value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( String expression ) {
      if ( completeMatches( expression ) ) {
         parameters.parameterize( expression );
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      if ( expression == null ) {
         //Does not match key.
         return null;
      }
      String suggestion = parameters.autoComplete( expression );
      if ( suggestion == null ) {
         //Note expression may be empty.
         return expression.trim();
      } else {
         return suggestion; 
      } 
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public CommandResult< ReturnT > execute( String expression ) {
      if ( completeMatches( expression ) ) {
         parameterize( expression );
         return execute();
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public CommandResult< ReturnT > execute() {
      if ( parameters.isComplete() ) {
         return function.apply( parameters );
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resetParameters() {
      parameters.reset();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append( description );
      for ( CommandParameter parameter : parameters ) {
         buffer.append( " " );
         buffer.append( "<" ).append( parameter.getParameterType() ).append( ">" );
      }
      return buffer.toString();
   }// End Method

}// End Class
