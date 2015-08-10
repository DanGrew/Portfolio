/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import object.BuilderObject;
import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;

/**
 * The {@link SearchPolicy} provides the different types of policies for matching {@link BuilderObject}s
 * in the system.
 */
public enum SearchPolicy {

   ExactString( 
            ClassParameterTypes.STRING_PARAMETER_TYPE, 
            ( object, type, value ) -> {
                  Object objectValue = object.get( type );
                  if ( objectValue == null ) {
                     return false;
                  }
                  objectValue = objectValue.toString();
                  return value.equals( objectValue );
            } 
    ),
    ContainsString( 
             ClassParameterTypes.STRING_PARAMETER_TYPE, 
             ( object, type, value ) -> {
                Object objectValue = object.get( type );
                if ( objectValue == null ) {
                   return false;
                }
                return objectValue.toString().contains( value.toString() );
             } 
     ),
     ExactNumber( 
              ClassParameterTypes.NUMBER_PARAMETER_TYPE, 
              ( object, type, value ) -> {
                 Object objectValue = object.get( type );
                 if ( objectValue == null ) {
                    return false;
                 }
                 return objectValue.equals( value );
           } 
     );
   
   private ClassParameterType parameterType;
   private SearchPolicyFunction function;
   
   /**
    * Constructs a new {@link SearchPolicy}.
    * @param type the {@link ClassParameterType} appropriate for the policy.
    * @param function the {@link SearchPolicyFunction} used to determine a match.
    */
   private SearchPolicy( ClassParameterType type, SearchPolicyFunction function ) {
      this.parameterType = type;
      this.function = function;
   }// End Constructor
   
   /**
    * Method to determine whether the given data matches the policy.
    * @param object the {@link BuilderObject} in question.
    * @param type the {@link PropertyType} the criteria is for.
    * @param value the value to match.
    * @return true if matches, false otherwise.
    */
   public boolean matchesPolicy( BuilderObject object, PropertyType type, Object value ) {
      if ( !policyApplicableFor( type ) ) {
         return false;
      }
      return function.matchesPolicy( object, type, value );
   }// End Method
   
   /**
    * Method to determine whether the given {@link PropertyType} is appropriate for the
    * type of {@link SearchPolicy}.
    * @param type the {@link PropertyType} in question.
    * @return true if applicable.
    */
   public boolean policyApplicableFor( PropertyType type ) {
      return type.getParameterType().equals( parameterType );
   }// End Method
   
}// End Enum
