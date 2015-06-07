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

/**
 * The {@link InstructionCommandImpl} provides an implementaton of the {@link Command} to 
 * support a simple instruction where no {@link CommandParameter}s are required.
 * @param <ReturnT> the type of the return value of the {@link Command}.
 */
public class InstructionCommandImpl< ReturnT > implements Command< ReturnT > {

   private CommandKey key;
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
      this( new CommandKeyImpl( key ), description, function );
   }// End Constructor
   
   /**
    * Constructs a new {@link InstructionCommandImpl}.
    * @param key the {@link CommandKey} key that must be matched.
    * @param description a user friendly description of the {@link Command}.
    * @param function the {@link Function} to execute.
    */
   public InstructionCommandImpl( 
            CommandKey key, 
            String description, 
            Function< CommandParameters, CommandResult< ReturnT > > function 
   ) {
      this.key = key;
      this.description = description;
      this.function = function;
   }// End Constructor
   
   /**
    * Protected method to gain access to the {@link CommandKey}.
    * @return the {@link CommandKey}.
    */
   protected CommandKey getCommandKey(){
      return key;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   public String getKey(){
      return key.getStringKey();
   }// End Method
   
   /**
    * Getter for the description associated with the {@link Command}.
    * @return a {@link String} description of the {@link Command}.
    */
   protected String getDescription(){
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
         return getKey();
      } else {
         return getKey() + ": " + description;
      }
   }// End Method

}// End Class
