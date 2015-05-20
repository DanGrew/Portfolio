/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import java.util.function.Function;

import parameter.CommandParameters;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;
import command.CommandResult;
import command.CommandResultImpl;
import commands.PropertyTypeCommands;
import commands.parameters.PropertyTypeCommandParameters;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link PropertyTypeCommands}.
 */
public class PropertyTypeCommandFunctions {

   public static final Function< CommandParameters, CommandResult< PropertyType > > CREATE_PROPERTY_TYPE_FUNCTION = 
            new Function< CommandParameters, CommandResult< PropertyType > >() {

      /**
       * {@inheritDoc}
       */
      @Override public CommandResult< PropertyType > apply( CommandParameters parameters ) {
         String name = parameters.getExpectedParameter( PropertyTypeCommandParameters.STRING_PARAMETER, String.class );
         Class< ? > clazzType = parameters.getExpectedParameter( PropertyTypeCommandParameters.CLASS_TYPE_PARAMETER, Class.class );
         PropertyType propertyType = new PropertyTypeImpl( name, clazzType );
         RequestSystem.store( propertyType, PropertyType.class );
         return new CommandResultImpl< PropertyType >( "Created " + name, propertyType );
      }// End Method
   };
}// End Class
