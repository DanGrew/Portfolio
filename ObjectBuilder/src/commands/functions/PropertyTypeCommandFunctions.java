/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.functions;

import java.util.function.Function;

import architecture.request.RequestSystem;
import commands.PropertyTypeCommands;
import commands.parameters.PropertyTypeCommandParameters;
import parameter.CommandParameters;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * {@link Class} to hold the {@link Function}s associated with {@link PropertyTypeCommands}.
 */
public class PropertyTypeCommandFunctions {

   public static final Function< CommandParameters, PropertyType > CREATE_PROPERTY_TYPE_FUNCTION = new Function< CommandParameters, PropertyType >() {

      /**
       * {@inheritDoc}
       */
      @Override public PropertyType apply( CommandParameters parameters ) {
         String name = parameters.getExpectedParameter( PropertyTypeCommandParameters.STRING_PARAMETER, String.class );
         Class< ? > clazzType = parameters.getExpectedParameter( PropertyTypeCommandParameters.CLASS_TYPE_PARAMETER, Class.class );
         PropertyType propertyType = new PropertyTypeImpl( name, clazzType );
         RequestSystem.store( propertyType, PropertyType.class );
         return propertyType;
      }// End Method
   };
}// End Class
