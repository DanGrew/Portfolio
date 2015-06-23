/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import gui.console.ConsoleMessageImpl;

import java.util.function.Function;

import parameter.CommandParameter;
import parameter.wrapper.CommandParameters;
import command.parameter.SingletonMethodCallParameterImpl;

/**
 * The {@link MethodCallCommandImpl} is responsible for allowing the user to call methods on
 * objects using the {@link Command} architecture.
 */
public class MethodCallCommandImpl extends ParameterizedCommandImpl< Object >{

   private static final String DESCRIPTION = "Command to call a method on an existing Object.";
   private static final CommandParameter METHOD_PARAMETER = new SingletonMethodCallParameterImpl();
   private static final Function< CommandParameters, CommandResult< Object > > FUNCTION = new Function< CommandParameters, CommandResult<Object> >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResultImpl< Object > apply( CommandParameters parameters ) {
         System.out.println( "Executing" );
         return new CommandResultImpl< Object >( new ConsoleMessageImpl( "Not implemented yet." ) );
      }// End Method
   }; // End Class
   
   /**
    * Constructs a new {@link NewCommandImpl}.
    */
   public MethodCallCommandImpl() {
      super( 
               DESCRIPTION, 
               FUNCTION, 
               METHOD_PARAMETER 
      );
   }// End Constructor
}// End Class
