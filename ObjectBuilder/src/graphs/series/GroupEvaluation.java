/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import object.BuilderObject;
import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;

/** {@link Enum} describing the {@link NumberFunction}s that can be applied to {@link BuilderObject}s.*/
public enum GroupEvaluation implements NumberFunction {
   
   Count( GroupEvaluationFunctions.newCountFunction(), null ), 
   Maximum( GroupEvaluationFunctions.newMaximumFunction(), ClassParameterTypes.NUMBER_PARAMETER_TYPE ), 
   Minimum( GroupEvaluationFunctions.newMinimumFunction(), ClassParameterTypes.NUMBER_PARAMETER_TYPE ), 
   Sum( GroupEvaluationFunctions.newSumFunction(), ClassParameterTypes.NUMBER_PARAMETER_TYPE ), 
   Average( GroupEvaluationFunctions.newAverageFunction(), ClassParameterTypes.NUMBER_PARAMETER_TYPE ), 
   CumulativeCount( GroupEvaluationFunctions.newCumulativeCountFunction(), null ), 
   CumulativeMaximum( GroupEvaluationFunctions.newCumulativeMaximumFunction(), ClassParameterTypes.NUMBER_PARAMETER_TYPE ), 
   CumulativeMinimum( GroupEvaluationFunctions.newCumulativeMinimumFunction(), ClassParameterTypes.NUMBER_PARAMETER_TYPE ), 
   CumulativeSum( GroupEvaluationFunctions.newCumulativeSumFunction(), ClassParameterTypes.NUMBER_PARAMETER_TYPE ), 
   CumulativeAverage( GroupEvaluationFunctions.newCumulativeAverageFunction(), ClassParameterTypes.NUMBER_PARAMETER_TYPE );
   
   private NumberFunction function;
   private ClassParameterType compatibleType;
   
   /**
    * Constructs a new {@link GroupEvaluation}.
    * @param function the {@link NumberFunction} associated.
    */
   private GroupEvaluation( NumberFunction function, ClassParameterType compatibleType ) {
      this.function = function;
      this.compatibleType = compatibleType;
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void consider( double value ) {
      function.consider( value );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Double getResult() {
      return function.getResult();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void nextCategory() {
      function.nextCategory();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void reset() {
      function.reset();
   }//End Method

   /**
    * Method to get a display friendly name.
    * @return the {@link String} name.
    */
   public String getDisplayName() {
      return name();
   }//End Method
   
   /**
    * Method to determine whether the given {@link ClassParameterType} is compatible with this {@link GroupEvaluation}.
    * @param type the {@link ClassParameterType} in question.
    * @return true if this {@link GroupEvaluation} can be used with the given {@link ClassParameterType}.
    */
   public boolean isCompatible( ClassParameterType type ) {
      if ( this.compatibleType == null ) {
         return true;
      } 
      return compatibleType.equals( type );
   }//End Method
   
}//End Enum
