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

import annotation.Cali;
import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;
import parameter.wrapper.CommandParameters;
import command.key.CaliNewCommandKeyImpl;
import command.parameter.ConstructorParameterImpl;
import command.parameter.ConstructorParameterValue;

/**
 * The {@link NewCommandImpl} provides a {@link Command} that allows the user to create
 * new objects using the {@link Cali} annotation.
 */
public class NewCommandImpl extends ParameterizedCommandImpl< Object >{

   private static final CommandParameter NEW_KEY = new FixedValueParameterImpl( CaliNewCommandKeyImpl.key() );
   private static final String DESCRIPTION = "Command to create a new Object.";
   private static final CommandParameter CONSTRUCTOR_PARAMETER = new ConstructorParameterImpl();
   private static final Function< CommandParameters, CommandResult< Object > > FUNCTION = new Function< CommandParameters, CommandResult<Object> >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResultImpl< Object > apply( CommandParameters parameters ) {
         ConstructorParameterValue value = parameters.getExpectedParameter( CONSTRUCTOR_PARAMETER, ConstructorParameterValue.class );
         Constructor< ? > constructor = value.getConstructor();
         try {
            Object object = constructor.newInstance( value.getParameters() );
            return new CommandResultImpl< Object >( 
                     "Successfully created " + constructor.getDeclaringClass().getSimpleName() + ".",
                     object 
            );
         } catch ( InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception ) {
            exception.printStackTrace();
            return new CommandResultImpl< Object >( "Failed to create object, Constructor does not match.", null );
         }
      }// End Method
   }; // End Class
   
   /**
    * Constructs a new {@link NewCommandImpl}.
    */
   public NewCommandImpl() {
      super( 
               DESCRIPTION, 
               FUNCTION, 
               NEW_KEY, 
               CONSTRUCTOR_PARAMETER 
      );
   }// End Constructor

}// End Class
