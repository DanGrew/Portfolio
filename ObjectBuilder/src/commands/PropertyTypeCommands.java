/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;
import propertytype.PropertyType;

import command.Command;
import command.InstructionCommandImpl;
import command.ParameterizedCommandImpl;
import commands.functions.PropertyTypeCommandFunctions;
import commands.parameters.PropertyTypeCommandParameters;

/**
 * {@link Class} for holding {@link PropertyType} {@link Command}s.
 */
public class PropertyTypeCommands {

   private static final CommandParameter CREATE_KEY_PARAMETER = new FixedValueParameterImpl( "CreatePropertyType" );
   private static final String CREATE_COMMAND_DESCRIPTION = "Function to create a new Property Type.";
   
   public static final Command< PropertyType > CREATE_PROPERTY_TYPE_COMMAND = new ParameterizedCommandImpl< PropertyType >(
            CREATE_COMMAND_DESCRIPTION, 
            PropertyTypeCommandFunctions.CREATE_PROPERTY_TYPE_FUNCTION, 
            CREATE_KEY_PARAMETER, 
            PropertyTypeCommandParameters.STRING_PARAMETER,
            PropertyTypeCommandParameters.CLASS_TYPE_PARAMETER
   );
   
   private static final String VIEW_COMMAND_KEY = "ViewPropertyTypes";
   private static final String VIEW_COMMAND_DESCRIPTION = "Function to view all Property Types.";
   public static final Command< Void > VIEW_PROPERTY_TYPES_COMMAND = new InstructionCommandImpl< Void >(
            VIEW_COMMAND_KEY, 
            VIEW_COMMAND_DESCRIPTION, 
            PropertyTypeCommandFunctions.VIEW_PROPERTY_TYPES_FUNCTION 
   );
}// End Class
