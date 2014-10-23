/*
 * ----------------------------------------
 *            Neural Networks
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
 package model.function;

 /**
  * The {@link BasicInputFunction} is respresents the absense of a {@link ThresholdFunction}
  * when inserting data into the Neural Network.
  */
public class BasicInputFunction extends ThresholdFunction {

   /**
    * Constructs a new {@link BasicInputFunction}.
    */
   public BasicInputFunction(){
      super( null );
   }// End Constructor

   /**
    * Constructs a new {@link BasicInputFunction}.
    * @param input the input value into the Neural Network.
    */
   public BasicInputFunction( double input ){
      super( input );
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void synapseFired( double output ) {
      this.output = output;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean excedesThreshold(){
      return true;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void calculateOutput() {
      /* There is nothing to calculate as the input function provides
       * data into the Neural Network. */
   }// End Method

}// End Class
