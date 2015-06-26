/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import objecttype.Definition;
import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;

import command.Command;
import command.ParameterizedCommandImpl;
import commands.functions.DefinitionCommandFunctions;
import commands.parameters.DefinitionCommandParameters;

/**
 * {@link Class} for holding {@link Definition} {@link Command}s.
 */
public class DefinitionCommands {

   private static final CommandParameter CREATE_KEY_PARAMETER = new FixedValueParameterImpl( "CreateDefinition" );
   private static final String CREATE_COMMAND_DESCRIPTION = "Function to create a new Builder Type.";
   
   public static final Command< Definition > CREATE_DEFINITION_COMMAND = new ParameterizedCommandImpl< Definition >(
            CREATE_COMMAND_DESCRIPTION, 
            DefinitionCommandFunctions.CREATE_DEFINITION_FUNCTION, 
            CREATE_KEY_PARAMETER, 
            DefinitionCommandParameters.STRING_PARAMETER
   );
   
   private static final CommandParameter ADD_PROPERTY_KEY_PARAMETER = new FixedValueParameterImpl( "AddProperty" );
   private static final String ADD_PROPERTY_COMMAND_DESCRIPTION = "Function to add a PropertyType to an existing Builder Type.";
   
   public static final Command< Definition > ADD_PROPERTY_COMMAND = new ParameterizedCommandImpl< Definition >(
            ADD_PROPERTY_COMMAND_DESCRIPTION, 
            DefinitionCommandFunctions.ADD_PROPERTY_FUNCTION, 
            ADD_PROPERTY_KEY_PARAMETER, 
            DefinitionCommandParameters.DEFINITION_REFERENCE_PARAMETER,
            DefinitionCommandParameters.PROPERTY_TYPE_REFERENCE_PARAMETER
   );
   
   private static final CommandParameter VIEW_KEY_PARAMETER = new FixedValueParameterImpl( "ViewDefinitions" );
   private static final String VIEW_COMMAND_DESCRIPTION = "Function to view all Builder Types.";
   public static final Command< Void > VIEW_BUILDER_TYPES_COMMAND = new ParameterizedCommandImpl< Void >(
            VIEW_COMMAND_DESCRIPTION, 
            DefinitionCommandFunctions.VIEW_BUILDER_TYPES_FUNCTION,
            VIEW_KEY_PARAMETER
   );
}// End Class
