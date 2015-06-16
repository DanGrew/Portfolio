/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import objecttype.BuilderType;
import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;

import command.Command;
import command.ParameterizedCommandImpl;
import commands.functions.BuilderTypeCommandFunctions;
import commands.parameters.BuilderTypeCommandParameters;

/**
 * {@link Class} for holding {@link BuilderType} {@link Command}s.
 */
public class BuilderTypeCommands {

   private static final CommandParameter CREATE_KEY_PARAMETER = new FixedValueParameterImpl( "CreateBuilderType" );
   private static final String CREATE_COMMAND_DESCRIPTION = "Function to create a new Builder Type.";
   
   public static final Command< BuilderType > CREATE_BUILDER_TYPE_COMMAND = new ParameterizedCommandImpl< BuilderType >(
            CREATE_COMMAND_DESCRIPTION, 
            BuilderTypeCommandFunctions.CREATE_BUILDER_TYPE_FUNCTION, 
            CREATE_KEY_PARAMETER, 
            BuilderTypeCommandParameters.STRING_PARAMETER
   );
   
   private static final CommandParameter ADD_PROPERTY_KEY_PARAMETER = new FixedValueParameterImpl( "AddProperty" );
   private static final String ADD_PROPERTY_COMMAND_DESCRIPTION = "Function to add a PropertyType to an existing Builder Type.";
   
   public static final Command< BuilderType > ADD_PROPERTY_COMMAND = new ParameterizedCommandImpl< BuilderType >(
            ADD_PROPERTY_COMMAND_DESCRIPTION, 
            BuilderTypeCommandFunctions.ADD_PROPERTY_FUNCTION, 
            ADD_PROPERTY_KEY_PARAMETER, 
            BuilderTypeCommandParameters.BUILDER_TYPE_REFERENCE_PARAMETER,
            BuilderTypeCommandParameters.PROPERTY_TYPE_REFERENCE_PARAMETER
   );
   
   private static final CommandParameter VIEW_KEY_PARAMETER = new FixedValueParameterImpl( "ViewBuilderTypes" );
   private static final String VIEW_COMMAND_DESCRIPTION = "Function to view all Builder Types.";
   public static final Command< Void > VIEW_BUILDER_TYPES_COMMAND = new ParameterizedCommandImpl< Void >(
            VIEW_COMMAND_DESCRIPTION, 
            BuilderTypeCommandFunctions.VIEW_BUILDER_TYPES_FUNCTION,
            VIEW_KEY_PARAMETER
   );
}// End Class
