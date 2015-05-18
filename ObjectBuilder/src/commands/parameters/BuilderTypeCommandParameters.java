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
import propertytype.PropertyType;
import commands.BuilderTypeCommands;
import commands.functions.BuilderTypeCommandFunctions;

/**
 * {@link Class} to hold {@link CommandParameter}s for {@link BuilderTypeCommands} and
 * {@link BuilderTypeCommandFunctions}.
 */
public class BuilderTypeCommandParameters {
   
   public static final CommandParameter STRING_PARAMETER = new CommandParameterImpl();
   public static final CommandParameter STRING_PARAMETER_2 = new CommandParameterImpl();
   public static final CommandParameter BUILDER_TYPE_REFERENCE_PARAMETER = new SingletonReferenceParameterImpl( BuilderType.class );
   public static final CommandParameter PROPERTY_TYPE_REFERENCE_PARAMETER = new SingletonReferenceParameterImpl( PropertyType.class );

}// End Class
