/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package commands.parameters;

import java.util.ArrayList;
import java.util.List;

import object.BuilderObject;
import objecttype.Definition;
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
   public static final ClassParameterType BUILDER_TYPE_PARAMETER_TYPE = new ReferenceClassParameterTypeImpl< Definition >( Definition.class );
   public static final ClassParameterType BUILDER_OBJECT_PARAMETER_TYPE = new ReferenceClassParameterTypeImpl< BuilderObject >( BuilderObject.class );
   private static final List< ClassParameterType > PARAMETER_TYPES = new ArrayList<>();
   
   static {
      initialiseTypes();
   }
   
   /**
    * Method to initialise the {@link ClassParameterType}s supported in an extension to
    * the default {@link ClassParameterTypes}.
    */
   public static void initialiseTypes() {
      if ( PARAMETER_TYPES.isEmpty() ) {
         ClassParameterTypes.initialiseTypes();
         PARAMETER_TYPES.add( PROPERTY_TYPE_PARAMETER_TYPE );
         PARAMETER_TYPES.add( BUILDER_TYPE_PARAMETER_TYPE );
         PARAMETER_TYPES.add( BUILDER_OBJECT_PARAMETER_TYPE );
         ClassParameterTypes.addParameterTypes( PARAMETER_TYPES );
      }
   }// End Method
}// End Class
