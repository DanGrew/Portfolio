/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.ReferenceClassParameterTypeImpl;
import propertytype.PropertyType;

/**
 * The {@link ObjectBuilderClassParameterTypes} provides the extra {@link ClassParameterType}s to be used in addition
 * to the {@link ClassParameterTypes}.
 */
public class ObjectBuilderClassParameterTypes {

   public static final ClassParameterType PROPERTY_TYPE_PARAMETER_TYPE = new ReferenceClassParameterTypeImpl< PropertyType >( PropertyType.class );
   
   static {
      ClassParameterTypes.addParameterType( PROPERTY_TYPE_PARAMETER_TYPE );
   }
}// End Class
