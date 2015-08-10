/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import model.singleton.Singleton;
import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;
import parameter.wrapper.CommandParameters;
import redirect.ParameterRedirect;
import redirect.ParameterRedirectResult;
import redirect.ParameterRedirectResult.Result;
import annotation.Cali;
import architecture.request.RequestSystem;
import command.parameter.ConstructorParameterImpl;
import command.parameter.ConstructorParameterValue;
import command.parameter.NewCommandParameterImpl;

/**
 * The {@link NewCommandImpl} provides a {@link Command} that allows the user to create
 * new objects using the {@link Cali} annotation.
 */
public class NewCommandImpl extends ParameterizedCommandImpl< Object >{

   private static final String DESCRIPTION = "Command to create a new Object.";
   private static final CommandParameter KEY_PARAMETER = new FixedValueParameterImpl( NewCommandParameterImpl.key() );
   private static final CommandParameter CONSTRUCTOR_PARAMETER = new ConstructorParameterImpl();
   private static final ParameterRedirect CONSTRUCTOR_REDIRECT = new ParameterRedirect();
   private static final Function< CommandParameters, CommandResult< Object > > FUNCTION = new Function< CommandParameters, CommandResult<Object> >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResultImpl< Object > apply( CommandParameters parameters ) {
         ConstructorParameterValue value = parameters.getExpectedParameter( CONSTRUCTOR_PARAMETER, ConstructorParameterValue.class );
         Constructor< ? > constructor = value.getConstructor();
         ParameterRedirectResult result = CONSTRUCTOR_REDIRECT.construct( constructor, value.getParameters() );
         if ( result.getResult().equals( Result.Invoked ) ) {
            RequestSystem.store( result.getReturnValue(), Singleton.class );
         }
         return new CommandResultImpl< Object >( result );
      }// End Method
   }; // End Class
   
   /**
    * Constructs a new {@link NewCommandImpl}.
    */
   public NewCommandImpl() {
      super( 
               DESCRIPTION, 
               FUNCTION, 
               KEY_PARAMETER, 
               CONSTRUCTOR_PARAMETER 
      );
   }// End Constructor

}// End Class
