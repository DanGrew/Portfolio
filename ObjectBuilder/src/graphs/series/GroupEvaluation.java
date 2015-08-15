/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.series;

import object.BuilderObject;

/** {@link Enum} describing the {@link NumberFunction}s that can be applied to {@link BuilderObject}s.*/
public enum GroupEvaluation implements NumberFunction {
   
   Count( GroupEvaluationFunctions.newCountFunction() ), 
   Maximum( GroupEvaluationFunctions.newMaximumFunction() ), 
   Minimum( GroupEvaluationFunctions.newMinimumFunction() ), 
   Sum( GroupEvaluationFunctions.newSumFunction() ), 
   Average( GroupEvaluationFunctions.newAverageFunction() );
   
   private NumberFunction function;
   
   /**
    * Constructs a new {@link GroupEvaluation}.
    * @param function the {@link NumberFunction} associated.
    */
   private GroupEvaluation( NumberFunction function ) {
      this.function = function;
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
   @Override public void reset() {
      function.reset();
   }// End Method

   /**
    * Method to get a display friendly name.
    * @return the {@link String} name.
    */
   public String getDisplayName() {
      return name();
   }//End Method
   
}//End Enum
