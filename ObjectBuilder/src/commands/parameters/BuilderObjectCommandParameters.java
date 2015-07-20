/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import object.BuilderObject;
import objecttype.Definition;
import parameter.CommandParameter;
import parameter.CommandParameterImpl;
import parameter.SingletonNameAsReferenceParameterImpl;
import propertytype.PropertyType;
import commands.BuilderObjectCommands;
import commands.functions.DefinitionCommandFunctions;
import commands.parameters.extensions.PropertyTypeAndValueParameterImpl;

/**
 * {@link Class} to hold {@link CommandParameter}s for {@link BuilderObjectCommands} and
 * {@link DefinitionCommandFunctions}.
 */
public class BuilderObjectCommandParameters {
   
   public static final CommandParameter STRING_PARAMETER = new CommandParameterImpl();
   public static final CommandParameter DEFINITION_REFERENCE_PARAMETER = new SingletonNameAsReferenceParameterImpl( Definition.class );
   public static final CommandParameter PROPERTY_TYPE_REFERENCE_PARAMETER = new SingletonNameAsReferenceParameterImpl( PropertyType.class );
   public static final CommandParameter BUILDER_OBJECT_REFERENCE_PARAMETER = new SingletonNameAsReferenceParameterImpl( BuilderObject.class );
   public static final CommandParameter BUILDER_OBJECT_VALUE_PARAMETER = new PropertyTypeAndValueParameterImpl();

}// End Class
