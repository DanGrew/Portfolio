/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import javafx.util.StringConverter;

/**
 * {@link StringConverter} for {@link Enum}s.
 */
public class EnumStringConverter< EnumT extends Enum< EnumT > > extends StringConverter< EnumT > {
   
   private Class< EnumT > lookupType;
   
   /**
    * Constructs a new {@link EnumStringConverter}.
    * @param lookupType the {@link Class} of the {@link Enum}.
    */
   public EnumStringConverter( Class< EnumT > lookupType ) {
      this.lookupType = lookupType;
   }//End Constructor
   
   /** 
    * {@inheritDoc} 
    */
   @Override public String toString( EnumT value ) {
      return ( value != null ) ? value.name() : "";
   }//End Method

   /** 
    * {@inheritDoc} 
    */
   @Override public EnumT fromString( String value ) {
      return Enum.valueOf( lookupType, value );
   }//End Method
}//End Class
