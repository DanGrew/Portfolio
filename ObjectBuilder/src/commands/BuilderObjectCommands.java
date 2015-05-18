/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands;

import object.BuilderObject;

import command.Command;
import command.ParameterizedCommandImpl;
import commands.functions.BuilderObjectCommandFunctions;
import commands.parameters.BuilderObjectCommandParameters;

/**
 * {@link Class} for holding {@link BuilderObject} {@link Command}s.
 */
public class BuilderObjectCommands {

   private static final String CREATE_COMMAND_KEY = "CreateObject";
   private static final String CREATE_COMMAND_DESCRIPTION = "Function to create a new Object using a BuilderType.";
   
   public static final Command< BuilderObject > CREATE_BUILDER_OBJECT_COMMAND = new ParameterizedCommandImpl< BuilderObject >(
            CREATE_COMMAND_KEY, 
            CREATE_COMMAND_DESCRIPTION, 
            BuilderObjectCommandFunctions.CREATE_BUILDER_OBJECT_FUNCTION, 
            BuilderObjectCommandParameters.STRING_PARAMETER,
            BuilderObjectCommandParameters.BUILDER_TYPE_REFERENCE_PARAMETER
   );

}// End Class
