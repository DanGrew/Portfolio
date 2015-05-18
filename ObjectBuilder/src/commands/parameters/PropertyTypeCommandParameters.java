/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import commands.PropertyTypeCommands;
import commands.functions.PropertyTypeCommandFunctions;
import parameter.ClassParameterImpl;
import parameter.CommandParameter;
import parameter.CommandParameterImpl;

/**
 * {@link Class} to hold {@link CommandParameter}s for {@link PropertyTypeCommands} and
 * {@link PropertyTypeCommandFunctions}.
 */
public class PropertyTypeCommandParameters {
   
   public static final CommandParameter STRING_PARAMETER = new CommandParameterImpl();
   public static final CommandParameter CLASS_TYPE_PARAMETER = new ClassParameterImpl();

}// End Class
