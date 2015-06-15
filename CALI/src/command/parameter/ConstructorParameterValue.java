/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import command.Command;

import annotation.Cali;

/**
 * The {@link ConstructorParameterValue} defines the input for the creation of
 * an {@link Object} through its {@link Constructor} for a {@link Cali} {@link Command}.
 */
public class ConstructorParameterValue {

   private Constructor< ? > constructor;
   private List< Object > parameters;
   
   /**
    * Constructs a new {@link ConstructorParameterValue}.
    */
   public ConstructorParameterValue() {
      parameters = new ArrayList< Object >();
   }// End Constructor
   
   /**
    * Setter for the {@link Constructor}.
    * @param constructor the {@link Constructor} to set.
    */
   public void setConstructor( Constructor< ? > constructor ) {
      this.constructor = constructor;
   }// End Method

   /**
    * Method to add the parameters input.
    * @param testParameters the {@link Object} parameters.
    */
   public void addParameters( Object... testParameters ) {
      this.parameters.addAll( Arrays.asList( testParameters ) );
   }// End Method

   /**
    * Getter for the {@link Constructor}.
    * @return the {@link Constructor} found.
    */
   public Constructor< ? > getConstructor() {
      return constructor;
   }// End Method

   /**
    * Getter for the array of parameters.
    * @return the {@link Object} parameters input.
    */
   public Object[] getParameters() {
      return parameters.toArray();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( constructor == null ) ? 0 : constructor.hashCode() );
      result = prime * result + ( ( parameters == null ) ? 0 : parameters.hashCode() );
      return result;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean equals( Object obj ) {
      if ( this == obj ) {
         return true;
      }
      if ( obj == null ){
         return false;
      }
      if ( getClass() != obj.getClass() ){
         return false;
      }
      ConstructorParameterValue other = ( ConstructorParameterValue ) obj;
      if ( constructor == null ) {
         if ( other.constructor != null ){
            return false;
         }
      } else if ( !constructor.equals( other.constructor ) ){
         return false;
      }
      if ( parameters == null ) {
         if ( other.parameters != null ){
            return false;
         }
      } else if ( !parameters.equals( other.parameters ) ){
         return false;
      }
      return true;
   }// End Method

}// End Class
