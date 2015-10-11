/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;

/**
 * {@link SdkBindings} provides some custom extensions to {@link Bindings}.
 */
public class SdkBindings {
   
   /**
    * Method to loosely bind a {@link Property} of {@link Number} with an {@link ObjectProperty} of {@link Double}.
    * @param property the {@link Property} to bind.
    * @param objectProperty the {@link ObjectProperty} to bind that provides the initial value.
    */
   public static void bind( Property< Number > property, ObjectProperty< Double > objectProperty ) {
      property.setValue( objectProperty.get() );
      applyBinding( property, objectProperty );
   }//End Method
   
   /**
    * Method to loosely bind a {@link Property} of {@link Number} with an {@link ObjectProperty} of {@link Double}.
    * @param objectProperty the {@link ObjectProperty} to bind.
    * @param property the {@link Property} to bind that provides the initial value.
    */
   public static void bind( ObjectProperty< Double > objectProperty, Property< Number > property ) {
      objectProperty.set( property.getValue().doubleValue() );
      applyBinding( property, objectProperty );
   }//End Method
   
   /**
    * Method to manually apply a bidirectional {@link Bindings} to the {@link Property} and {@link ObjectProperty}.
    * @param property the {@link Property} to bind.
    * @param objectProperty the {@link ObjectProperty} to bind.
    */
   private static void applyBinding( Property< Number > property, ObjectProperty< Double > objectProperty ) {
      property.addListener( ( change, old, updated ) -> {
         if ( Double.compare( objectProperty.getValue(), updated.doubleValue() ) == 0 ) {
            return;
         }
         objectProperty.set( updated.doubleValue() );
      } );
      
      objectProperty.addListener( ( change, old, updated ) -> {
         if ( Double.compare( property.getValue().doubleValue(), updated ) == 0 ) {
            return;
         }
         property.setValue( updated );
      } );
   }//End Method

}//End Class
