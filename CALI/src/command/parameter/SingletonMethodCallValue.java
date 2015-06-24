/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package command.parameter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.singleton.Singleton;

/**
 * The {@link SingletonMethodCallValue} is responsible for holding the parsed information
 * for executing a {@link Method} on a {@link Singleton}.
 */
public class SingletonMethodCallValue {

   private Singleton singleton;
   private Method method;
   private List< Object > parameters;
   
   /**
    * Constructs a new {@link SingletonMethodCallValue}.
    */
   public SingletonMethodCallValue() {
      parameters = new ArrayList< Object >();
   }// End Constructor
   
   /**
    * Setter for the {@link Singleton}.
    * @param singleton the {@link Singleton}.
    */
   public void setSingleton( Singleton singleton ) {
      this.singleton = singleton;
   }// End Method
   
   /**
    * Getter for the {@link Singleton} identified for the call.
    * @return the {@link Singleton} to call the {@link Method} on.
    */
   public Singleton getSingleton() {
      return singleton;
   }// End Method

   /**
    * Getter for the {@link Method} parsed and matched.
    * @return the {@link Method}.
    */
   public Method getMethod() {
      return method;
   }// End Method
   
   /**
    * Setter for the {@link Method}.
    * @param method the {@link Method} to execute.
    */
   public void setMethod( Method method ) {
      this.method = method;
   }// End Method

   /**
    * Method to add a parameter for the execution of the {@link Method}.
    * @param parameter the {@link Object} parameter.
    */
   public void addParameter( Object parameter ) {
      this.parameters.add( parameter );
   }// End Method
   
   /**
    * Method to add all {@link Object} parameters parsed.
    * @param parameters the {@link Object} parameters.
    */
   public void addParameters( Object... parameters ) {
      this.parameters.addAll( Arrays.asList( parameters ) );
   }// End Method
   
   /**
    * Getter for the parameters to be given to the {@link Method}.
    * @return the array of {@link Object} parameters.
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
      result = prime * result + ( ( method == null ) ? 0 : method.hashCode() );
      result = prime * result + ( ( parameters == null ) ? 0 : parameters.hashCode() );
      result = prime * result + ( ( singleton == null ) ? 0 : singleton.hashCode() );
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
      SingletonMethodCallValue other = ( SingletonMethodCallValue ) obj;
      if ( method == null ) {
         if ( other.method != null ){
            return false;
         }
      } else if ( !method.equals( other.method ) ){
         return false;
      }
      if ( parameters == null ) {
         if ( other.parameters != null ){
            return false;
         }
      } else if ( !parameters.equals( other.parameters ) ){
         return false;
      }
      if ( singleton == null ) {
         if ( other.singleton != null ){
            return false;
         }
      } else if ( !singleton.equals( other.singleton ) ){
         return false;
      }
      return true;
   }// End Method

}// End Class
