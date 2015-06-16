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
import parameter.FixedValueParameterImpl;
import parameter.wrapper.CommandParameters;

/**
 * The {@link InstructionCommandImpl} provides an implementation of the {@link Command} to 
 * support a simple instruction where no {@link CommandParameter}s are required.
 * @param <ReturnT> the type of the return value of the {@link Command}.
 */
public class InstructionCommandImpl< ReturnT > implements Command< ReturnT > {

   private CommandParameter key;
   private String description;
   private Function< CommandParameters, CommandResult< ReturnT > > function;
   
   /**
    * Constructs a new {@link InstructionCommandImpl}.
    * @param key the {@link String} key that must be matched.
    * @param description a user friendly description of the {@link Command}.
    * @param function the {@link Function} to execute.
    */
   public InstructionCommandImpl( 
            String key, 
            String description, 
            Function< CommandParameters, CommandResult< ReturnT > > function 
   ) {
      this( new FixedValueParameterImpl( key ), description, function );
   }// End Constructor
   
   /**
    * Constructs a new {@link InstructionCommandImpl}.
    * @param key the {@link CommandParameter} key that must be matched.
    * @param description a user friendly description of the {@link Command}.
    * @param function the {@link Function} to execute.
    */
   public InstructionCommandImpl( 
            CommandParameter key, 
            String description, 
            Function< CommandParameters, CommandResult< ReturnT > > function 
   ) {
      this.key = key;
      this.description = description;
      this.function = function;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String getDescription(){
      return description;
   }// End Method
   
   /**
    * Getter for the {@link Function} to execute when this {@link Command} is matched.
    * @return the {@link Function} to execute.
    */
   protected Function< CommandParameters, CommandResult< ReturnT > > getFunction(){
      return function;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean partialMatches( String expression ) {
      return key.partialMatches( expression );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean completeMatches( String expression ) {
      return key.completeMatches( expression );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( CommandParameter parameter, Object value ) {}
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( String expression ) {}
   
   /**
    * {@inheritDoc}
    */
   @Override public String autoComplete( String expression ) {
      if ( partialMatches( expression ) ) {
         return key.autoComplete( expression );
      } else {
         return null;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public CommandResult< ReturnT > execute( String expression ) {
      parameterize( expression );
      return execute();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public CommandResult< ReturnT > execute() {
      return function.apply( null );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void resetParameters() {}
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString() {
      if ( description == null ) {
         return "Command";
      } else {
         return description;
      }
   }// End Method

}// End Class
