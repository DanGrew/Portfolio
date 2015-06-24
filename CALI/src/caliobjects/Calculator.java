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
    * Method to add two values together, input as strings.
    * @param stringA the {@link String} representing the first value.
    * @param stringB the {@link String} representing the second value.
    */
   @Cali public void add( String stringA, String stringB ) {
      try {
         Double valueA = Double.valueOf( stringA );
         Double valueB = Double.valueOf( stringB );
         setResult( valueA + valueB );
      } catch ( NumberFormatException exception ) {
         return;
      }
   }// End Method

   /**
    * Method to add the given value to the current result.
    * @param string the {@link String} representing the value to add.
    */
   @Cali public void add( String string ) {
      try {
         Double value = Double.valueOf( string );
         setResult( getResultValue() + value );
      } catch ( NumberFormatException exception ) {
         return;
      }
   }// End Method
    
   /**
    * Getter for the current result in the {@link Calculator}.
    * @return the current result, that is a double, as a {@link String}.
    */
   @Cali public String getResult() {
      return "" + result;
   }// End Method

}// End Class
