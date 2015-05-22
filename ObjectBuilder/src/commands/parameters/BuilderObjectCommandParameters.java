/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import object.BuilderObject;
import objecttype.BuilderType;
import parameter.CommandParameter;
import parameter.CommandParameterImpl;
import parameter.SingletonReferenceParameterImpl;
import propertytype.PropertyType;
import commands.BuilderObjectCommands;
import commands.functions.BuilderTypeCommandFunctions;

/**
 * {@link Class} to hold {@link CommandParameter}s for {@link BuilderObjectCommands} and
 * {@link BuilderTypeCommandFunctions}.
 */
public class BuilderObjectCommandParameters {
   
   public static final CommandParameter STRING_PARAMETER = new CommandParameterImpl();
   public static final CommandParameter BUILDER_TYPE_REFERENCE_PARAMETER = new SingletonReferenceParameterImpl( BuilderType.class );
   public static final CommandParameter PROPERTY_TYPE_REFERENCE_PARAMETER = new SingletonReferenceParameterImpl( PropertyType.class );
   public static final CommandParameter BUILDER_OBJECT_REFERENCE_PARAMETER = new SingletonReferenceParameterImpl( BuilderObject.class );

}// End Class
