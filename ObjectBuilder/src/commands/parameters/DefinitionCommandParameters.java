/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import objecttype.Definition;
import parameter.CommandParameter;
import parameter.CommandParameterImpl;
import parameter.SingletonNameAsReferenceParameterImpl;
import propertytype.PropertyType;

/**
 * {@link Class} to hold {@link CommandParameter}s for {@link DefinitionCommands} and
 * {@link DefinitionCommandFunctions}.
 */
public class DefinitionCommandParameters {
   
   public static final CommandParameter STRING_PARAMETER = new CommandParameterImpl();
   public static final CommandParameter STRING_PARAMETER_2 = new CommandParameterImpl();
   public static final CommandParameter DEFINITION_REFERENCE_PARAMETER = new SingletonNameAsReferenceParameterImpl( Definition.class );
   public static final CommandParameter PROPERTY_TYPE_REFERENCE_PARAMETER = new SingletonNameAsReferenceParameterImpl( PropertyType.class );

}// End Class
