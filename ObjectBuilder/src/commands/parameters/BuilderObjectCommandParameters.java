/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import objecttype.BuilderType;
import parameter.CommandParameter;
import parameter.CommandParameterImpl;
import parameter.SingletonReferenceParameterImpl;

import commands.BuilderObjectCommands;
import commands.functions.BuilderTypeCommandFunctions;

/**
 * {@link Class} to hold {@link CommandParameter}s for {@link BuilderObjectCommands} and
 * {@link BuilderTypeCommandFunctions}.
 */
public class BuilderObjectCommandParameters {
   
   public static final CommandParameter STRING_PARAMETER = new CommandParameterImpl();
   public static final CommandParameter BUILDER_TYPE_REFERENCE_PARAMETER = new SingletonReferenceParameterImpl( BuilderType.class );

}// End Class
