/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import object.BuilderObject;
import parameter.CommandParameter;
import parameter.FixedValueParameterImpl;

import command.Command;
import command.InstructionCommandImpl;
import command.ParameterizedCommandImpl;
import commands.functions.BuilderObjectCommandFunctions;
import commands.parameters.BuilderObjectCommandParameters;

/**
 * {@link Class} for holding {@link BuilderObject} {@link Command}s.
 */
public class BuilderObjectCommands {

   private static final CommandParameter CREATE_KEY_PARAMETER = new FixedValueParameterImpl( "CreateObject" );
   private static final String CREATE_COMMAND_DESCRIPTION = "Function to create a new Object using a BuilderType.";
   
   public static final Command< BuilderObject > CREATE_BUILDER_OBJECT_COMMAND = new ParameterizedCommandImpl< BuilderObject >(
            CREATE_COMMAND_DESCRIPTION, 
            BuilderObjectCommandFunctions.CREATE_BUILDER_OBJECT_FUNCTION, 
            CREATE_KEY_PARAMETER, 
            BuilderObjectCommandParameters.STRING_PARAMETER,
            BuilderObjectCommandParameters.BUILDER_TYPE_REFERENCE_PARAMETER
   );
   
   private static final CommandParameter SET_PROPERTY_KEY_PARAMETER = new FixedValueParameterImpl( "SetProperty" );
   private static final String SET_PROPERTY_COMMAND_DESCRIPTION = "Function to set the value for a property.";
   
   public static final Command< BuilderObject > SET_PROPERTY_COMMAND = new ParameterizedCommandImpl< BuilderObject >(
            SET_PROPERTY_COMMAND_DESCRIPTION, 
            BuilderObjectCommandFunctions.SET_PROPERTY_FUNCTION, 
            SET_PROPERTY_KEY_PARAMETER, 
            BuilderObjectCommandParameters.BUILDER_OBJECT_REFERENCE_PARAMETER,
            BuilderObjectCommandParameters.BUILDER_OBJECT_VALUE_PARAMETER
   );
   
   private static final String VIEW_COMMAND_KEY = "ViewObjects";
   private static final String VIEW_COMMAND_DESCRIPTION = "Function to view all objects in the system.";
   public static final Command< Void > VIEW_OBJECTS_COMMAND = new InstructionCommandImpl< Void >(
            VIEW_COMMAND_KEY, 
            VIEW_COMMAND_DESCRIPTION, 
            BuilderObjectCommandFunctions.VIEW_OBJECTS_FUNCTION 
   );

}// End Class
