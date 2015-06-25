/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package caliobjects;

import command.Command;
import model.singleton.SingletonImpl;
import annotation.Cali;

/**
 * The {@link Calculator} provides a basic calculator that can be controlled by {@link Command}s.
 */
@Cali
public class Calculator extends SingletonImpl< SerializableCalculator > {

   private static final String NOT_EVALUATED = "NotEvaluated";
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
    * @return the current result, that is a double, as a {@link String}.
    */
   @Cali public String getResult() {
      return "" + result;
   }// End Method
   
   /**
    * Method to add two values together, input as strings.
    * @param stringA the {@link String} representing the first value.
    * @param stringB the {@link String} representing the second value.
    * @return the result of the addition.
    */
   @Cali public String add( String stringA, String stringB ) {
      try {
         Double valueA = Double.valueOf( stringA );
         Double valueB = Double.valueOf( stringB );
         return "" + ( valueA + valueB );
      } catch ( NumberFormatException exception ) {
         return NOT_EVALUATED;
      }
   }// End Method

   /**
    * Method to add the given value to the current result.
    * @param string the {@link String} representing the value to add.
    * @return the current {@link Calculator#getResult()}.
    */
   @Cali public String add( String string ) {
      try {
         Double value = Double.valueOf( string );
         setResult( getResultValue() + value );
      } catch ( NumberFormatException exception ) {}
      return getResult();
   }// End Method

   /**
    * Method to subtract the second parameter from the first.
    * @param stringA the value to subtract from.
    * @param stringB the value to subtract.
    * @return the result of the subtraction.
    */
   @Cali public String subtract( String stringA, String stringB ) {
      try {
         Double valueA = Double.valueOf( stringA );
         Double valueB = Double.valueOf( stringB );
         return "" + ( valueA - valueB );
      } catch ( NumberFormatException exception ) {
         return NOT_EVALUATED;
      }
   }// End Method

   /**
    * Method to subtract the parameter from the current {@link Calculator#getResult()}.
    * @param string the value to subtract.
    */
   @Cali public String subtract( String string ) {
      try {
         Double value = Double.valueOf( string );
         setResult( getResultValue() - value );
      } catch ( NumberFormatException exception ) {}
      return getResult();
   }// End Method

   /**
    * Method to clear the {@link Calculator}, to zero.
    * @return the current {@link Calculator#getResult()}.
    */
   @Cali public String clear() {
      setResult( 0.0 );
      return getResult();
   }// End Method

   /**
    * Method to clear the {@link Calculator} to the given value.
    * @param string the value to clear the calculator to.
    * @return the {@link Calculator#getResult()}.
    */
   @Cali public String clear( String string ) {
      try {
         Double value = Double.valueOf( string );
         setResult( value );
      } catch ( NumberFormatException exception ) {}
      return getResult();
   }// End Method

   /**
    * Method to multiple the given parameters.
    * @param stringA the first parameter to multiply.
    * @param stringB the second parameter to multiply.
    * @return the result of the multiplication.
    */
   @Cali public String multiply( String stringA, String stringB ) {
      try {
         Double valueA = Double.valueOf( stringA );
         Double valueB = Double.valueOf( stringB );
         return "" + ( valueA * valueB );
      } catch ( NumberFormatException exception ) {
         return NOT_EVALUATED;
      }
   }// End Method

   /**
    * Method to multiple the {@link Calculator#getResult()} by the given value.
    * @param string the value to multiply by.
    * @return the {@link Calculator#getResult()}.
    */
   @Cali public String multiply( String string ) {
      try {
         Double value = Double.valueOf( string );
         setResult( getResultValue() * value );
      } catch ( NumberFormatException exception ) {}
      return getResult();
   }// End Method

}// End Class
