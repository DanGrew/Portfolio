/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package caliobjects;

import annotation.Cali;
import model.singleton.SingletonImpl;

/**
 * The {@link Calculator} provides a basic calculator that can be controlled by {@link Command}s.
 */
@Cali
public class Calculator extends SingletonImpl< SerializableCalculator > {

   private double result;
   
   /**
    * Constructor allowing {@link Command}s to create.
    * @param identification the name of the {@link Calculator}.
    */
   @Cali public Calculator( String identification ) {
      super( identification );
   }// End Constructor
   
   /**
    * Private setter for the result.
    * @param value the result.
    */
   private void setResult( double value ) {
      this.result = value;
   }// End Method
   
   /**
    * Private getter for the result.
    * @return the current result;
    */
   private double getResultValue(){
      return result;
   }// End Method
   
   @Override protected void writeSingleton( SerializableCalculator serializable ) {}
   @Override protected void readSingleton( SerializableCalculator serialized ) {}

   /**
    * Getter for the current result in the {@link Calculator}.
    * @return the current result as a double.
    */
   @Cali public Double getResult() {
      return result;
   }// End Method
   
   /**
    * Method to add two values together, input as strings.
    * @param valueA the first value.
    * @param valueB the second value.
    * @return the result of the addition.
    */
   @Cali public Double add( Double valueA, Double valueB ) {
      return ( valueA + valueB );
   }// End Method

   /**
    * Method to add the given value to the current result.
    * @param value the value to add.
    * @return the current {@link Calculator#getResult()}.
    */
   @Cali public Double add( Double value ) {
      setResult( getResultValue() + value );
      return getResult();
   }// End Method

   /**
    * Method to subtract the second parameter from the first.
    * @param valueA the value to subtract from.
    * @param valueB the value to subtract.
    * @return the result of the subtraction.
    */
   @Cali public Double subtract( Double valueA, Double valueB ) {
      return ( valueA - valueB );
   }// End Method

   /**
    * Method to subtract the parameter from the current {@link Calculator#getResult()}.
    * @param value the value to subtract.
    */
   @Cali public Double subtract( Double value ) {
      setResult( getResultValue() - value );
      return getResult();
   }// End Method

   /**
    * Method to clear the {@link Calculator}, to zero.
    * @return the current {@link Calculator#getResult()}.
    */
   @Cali public Double clear() {
      setResult( 0.0 );
      return getResult();
   }// End Method

   /**
    * Method to clear the {@link Calculator} to the given value.
    * @param value the value to clear the calculator to.
    * @return the {@link Calculator#getResult()}.
    */
   @Cali public Double clear( Double value ) {
      setResult( value );
      return getResult();
   }// End Method

   /**
    * Method to multiple the given parameters.
    * @param valueA the first parameter to multiply.
    * @param valueB the second parameter to multiply.
    * @return the result of the multiplication.
    */
   @Cali public Double multiply( Double valueA, Double valueB ) {
      return ( valueA * valueB );
   }// End Method

   /**
    * Method to multiple the {@link Calculator#getResult()} by the given value.
    * @param value the value to multiply by.
    * @return the {@link Calculator#getResult()}.
    */
   @Cali public Double multiply( Double value ) {
      setResult( getResultValue() * value );
      return getResult();
   }// End Method

}// End Class
