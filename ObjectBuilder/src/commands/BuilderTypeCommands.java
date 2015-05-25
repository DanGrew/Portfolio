/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import objecttype.BuilderType;
import command.Command;
import command.CommandKey;
import command.CommandKeyImpl;
import command.InstructionCommandImpl;
import command.ParameterizedCommandImpl;
import commands.functions.BuilderTypeCommandFunctions;
import commands.parameters.BuilderTypeCommandParameters;

/**
 * {@link Class} for holding {@link BuilderType} {@link Command}s.
 */
public class BuilderTypeCommands {

   private static final CommandKey CREATE_COMMAND_KEY = new CommandKeyImpl( "CreateBuilderType" );
   private static final String CREATE_COMMAND_DESCRIPTION = "Function to create a new Builder Type.";
   
   public static final Command< BuilderType > CREATE_BUILDER_TYPE_COMMAND = new ParameterizedCommandImpl< BuilderType >(
            CREATE_COMMAND_KEY, 
            CREATE_COMMAND_DESCRIPTION, 
            BuilderTypeCommandFunctions.CREATE_BUILDER_TYPE_FUNCTION, 
            BuilderTypeCommandParameters.STRING_PARAMETER
   );
   
   private static final CommandKey ADD_PROPERTY_COMMAND_KEY = new CommandKeyImpl( "AddProperty" );
   private static final String ADD_PROPERTY_COMMAND_DESCRIPTION = "Function to add a PropertyType to an existing Builder Type.";
   
   public static final Command< BuilderType > ADD_PROPERTY_COMMAND = new ParameterizedCommandImpl< BuilderType >(
            ADD_PROPERTY_COMMAND_KEY, 
            ADD_PROPERTY_COMMAND_DESCRIPTION, 
            BuilderTypeCommandFunctions.ADD_PROPERTY_FUNCTION, 
            BuilderTypeCommandParameters.BUILDER_TYPE_REFERENCE_PARAMETER,
            BuilderTypeCommandParameters.PROPERTY_TYPE_REFERENCE_PARAMETER
   );
   
   private static final CommandKey VIEW_COMMAND_KEY = new CommandKeyImpl( "ViewBuilderTypes" );
   private static final String VIEW_COMMAND_DESCRIPTION = "Function to view all Builder Types.";
   public static final Command< Void > VIEW_BUILDER_TYPES_COMMAND = new InstructionCommandImpl< Void >(
            VIEW_COMMAND_KEY, 
            VIEW_COMMAND_DESCRIPTION, 
            BuilderTypeCommandFunctions.VIEW_BUILDER_TYPES_FUNCTION 
   );
}// End Class
