/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import architecture.request.RequestSystem;
import javafx.util.StringConverter;
import model.singleton.Singleton;

/**
 * {@link StringConverter} for {@link Singleton} using {@link Singleton#getIdentification()}.
 */
public class SingletonStringConverter< SingletonT extends Singleton > extends StringConverter< SingletonT > {
   
   private Class< SingletonT > lookupType;
   
   /**
    * Constructs a new {@link SingletonStringConverter}.
    * @param lookupType the {@link Class} of the {@link Singleton}.
    */
   public SingletonStringConverter( Class< SingletonT > lookupType ) {
      this.lookupType = lookupType;
   }//End Constructor
   
   /** 
    * {@inheritDoc} 
    */
   @Override public String toString( SingletonT value ) {
      return ( value != null ) ? value.getIdentification() : "";
   }//End Method

   /** 
    * {@inheritDoc} 
    */
   @Override public SingletonT fromString( String value ) {
      return RequestSystem.retrieve( lookupType, value );
   }//End Method
}//End Class
