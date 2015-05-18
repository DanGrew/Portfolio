/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import parameter.CommandParameter;
import parameter.CommandParameterImpl;

import commands.BuilderTypeCommands;
import commands.functions.BuilderTypeCommandFunctions;

/**
 * {@link Class} to hold {@link CommandParameter}s for {@link BuilderTypeCommands} and
 * {@link BuilderTypeCommandFunctions}.
 */
public class BuilderTypeCommandParameters {
   
   public static final CommandParameter STRING_PARAMETER = new CommandParameterImpl();
   public static final CommandParameter STRING_PARAMETER_2 = new CommandParameterImpl();

}// End Class
