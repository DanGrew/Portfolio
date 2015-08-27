/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.time.LocalDate;

import object.BuilderObject;
import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import parameter.classparameter.DateClassParameterTypeImpl;
import parameter.classparameter.NumberClassParameterTypeImpl;
import propertytype.PropertyType;

/**
 * The {@link SearchPolicy} provides the different types of policies for matching {@link BuilderObject}s
 * in the system.
 */
public enum SearchPolicy {

   ExactString( ClassParameterTypes.STRING_PARAMETER_TYPE, ( object, type, value ) -> {
      Object objectValue = object.get( type );
      if ( objectValue == null ) {
         return false;
      }
      objectValue = objectValue.toString();
      return value.equals( objectValue );
   } ), 
   ContainsString( ClassParameterTypes.STRING_PARAMETER_TYPE, ( object, type, value ) -> {
      Object objectValue = object.get( type );
      if ( objectValue == null ) {
         return false;
      }
      if ( value.isEmpty() ) {
         return true;
      }
      return objectValue.toString().contains( value.toString() );
   } ), 
   ExactNumber( ClassParameterTypes.NUMBER_PARAMETER_TYPE, ( object, type, value ) -> {
      Object objectValue = object.get( type );
      if ( objectValue == null ) {
         return false;
      }
      Double doubleValue = NumberClassParameterTypeImpl.objectToNumber( value );
      if ( doubleValue == null ) {
         return false;
      }
      return objectValue.equals( doubleValue );
   } ),
   GreaterThanNumber( ClassParameterTypes.NUMBER_PARAMETER_TYPE, ( object, type, value ) -> {
      Object objectValue = object.get( type );
      if ( objectValue == null ) {
         return false;
      }
      Double doubleValue = NumberClassParameterTypeImpl.objectToNumber( value );
      if ( doubleValue == null ) {
         return false;
      }
      Double actualDouble = NumberClassParameterTypeImpl.objectToNumber( objectValue );
      return actualDouble >= doubleValue;
   } ),
   LessThanNumber( ClassParameterTypes.NUMBER_PARAMETER_TYPE, ( object, type, value ) -> {
      Object objectValue = object.get( type );
      if ( objectValue == null ) {
         return false;
      }
      Double doubleValue = NumberClassParameterTypeImpl.objectToNumber( value );
      if ( doubleValue == null ) {
         return false;
      }
      Double actualDouble = NumberClassParameterTypeImpl.objectToNumber( objectValue );
      return actualDouble <= doubleValue;
   } ),
   GreaterThanDate( ClassParameterTypes.DATE_PARAMETER_TYPE, ( object, type, value ) -> {
      Object objectValue = object.get( type );
      if ( objectValue == null ) {
         return false;
      }
      LocalDate compareValue = DateClassParameterTypeImpl.objectToDate( value );
      if ( compareValue == null ) {
         return false;
      }
      LocalDate actualDate = DateClassParameterTypeImpl.objectToDate( objectValue );
      return actualDate.isAfter( compareValue );
   } ),
   LessThanDate( ClassParameterTypes.DATE_PARAMETER_TYPE, ( object, type, value ) -> {
      Object objectValue = object.get( type );
      if ( objectValue == null ) {
         return false;
      }
      LocalDate compareValue = DateClassParameterTypeImpl.objectToDate( value );
      if ( compareValue == null ) {
         return false;
      }
      LocalDate actualDate = DateClassParameterTypeImpl.objectToDate( objectValue );
      return actualDate.isBefore( compareValue );
   } );
   

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
   public boolean matchesPolicy( BuilderObject object, PropertyType type, String value ) {
      if ( !isCompatible( type ) ) {
         return false;
      }
      if ( value == null ) {
         value = "";
      }
      return function.matchesPolicy( object, type, value );
   }// End Method
   
   /**
    * Method to determine whether the given {@link PropertyType} is appropriate for the
    * type of {@link SearchPolicy}.
    * @param type the {@link PropertyType} in question.
    * @return true if applicable.
    */
   public boolean isCompatible( PropertyType type ) {
      return type.getParameterType().equals( parameterType );
   }// End Method
   
   /**
    * Getter for the {@link ClassParameterType} associated with the {@link SearchPolicy}.
    * @return the {@link ClassParameterType}.
    */
   public ClassParameterType getParameterType() {
      return parameterType;
   }// End Method
   
}// End Enum
