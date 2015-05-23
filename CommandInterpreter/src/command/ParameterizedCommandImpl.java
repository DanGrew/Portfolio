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
   @Override public boolean partialMatches( String expression ) {
      if ( super.partialMatches( expression ) ) {
         String[] parameterValues = identifyParameters( expression );
         return parameters.partialMatches( parameterValues );
      }
      return false;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      if ( super.partialMatches( expression ) ) {
         String[] parameterValues = identifyParameters( expression );
         return parameters.completeMatches( parameterValues );
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
   @Override public String autoComplete( String expression ) {
      String[] expressionParts = expression.trim().split( " " );
      if ( expressionParts.length > 1 ) {
         String[] parameterValues = identifyParameters( expression );
         String suggestion = parameters.autoComplete( parameterValues );
         if ( suggestion == null ) {
            return expression;
         } else {
            expressionParts[ expressionParts.length - 1 ] = suggestion;
            return String.join( " ", expressionParts );
         } 
      } else {
         return super.autoComplete( expression );
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
