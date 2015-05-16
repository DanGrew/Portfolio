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
 * The {@link InstructionCommandImpl} provides an implementaton of the {@link Command} to 
 * support a simple instruction where no {@link CommandParameter}s are required.
 * @param <ReturnT> the type of the return value of the {@link Command}.
 */
public class InstructionCommandImpl< ReturnT > implements Command< ReturnT > {

   private String key;
   private Function< CommandParameters, ReturnT > function;
   
   /**
    * Constructs a new {@link InstructionCommandImpl}.
    * @param key the {@link String} key that must be matched.
    * @param function the {@link Function} to execute.
    */
   public InstructionCommandImpl( String key, Function< CommandParameters, ReturnT > function ) {
      this.key = key;
      this.function = function;
   }// End Constructor
   
   /**
    * Getter for the key to match to this {@link Command}.
    * @return the {@link String} key.
    */
   private String getKey(){
      return key;
   }// End Method
   
   /**
    * Getter for the {@link Function} to execute when this {@link Command} is matched.
    * @return the {@link Function} to execute.
    */
   protected Function< CommandParameters, ReturnT > getFunction(){
      return function;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean matches( String expression ) {
      String trimmedExpression = expression.trim();
      return trimmedExpression.startsWith( getKey() );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void parameterize( CommandParameter parameter, Object value ) {}
   
   /**
    * {@inheritDoc}
    */
   @Override public ReturnT execute() {
      return function.apply( null );
   }// End Method

}// End Class
