/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import propertytype.PropertyType;

import command.Command;
import command.ParameterizedCommandImpl;
import commands.functions.PropertyTypeCommandFunctions;
import commands.parameters.PropertyTypeCommandParameters;

/**
 * {@link Class} for holding {@link PropertyType} {@link Command}s.
 */
public class PropertyTypeCommands {

   private static final String CREATE_COMMAND_KEY = "CreatePropertyType";
   private static final String CREATE_COMMAND_DESCRIPTION = "Function to create a new Property Type.";
   
   public static final Command< PropertyType > CREATE_PROPERTY_TYPE_COMMAND = new ParameterizedCommandImpl< PropertyType >(
            CREATE_COMMAND_KEY, 
            CREATE_COMMAND_DESCRIPTION, 
            PropertyTypeCommandFunctions.CREATE_PROPERTY_TYPE_FUNCTION, 
            PropertyTypeCommandParameters.STRING_PARAMETER,
            PropertyTypeCommandParameters.CLASS_TYPE_PARAMETER
   );
}// End Class
