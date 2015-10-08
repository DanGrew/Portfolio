/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import javafx.util.converter.DoubleStringConverter;

/**
 * Defensive version of the {@link DoubleStringConverter} that does not crash when something
 * unexpected is parsed.
 */
public class DefensiveDoubleStringConverter extends DoubleStringConverter {

   /**
    * {@inheritDoc}
    */
   @Override public Double fromString( String string ) {
      try {
         Double parsed = super.fromString( string );
         return parsed;
      } catch ( NumberFormatException exception ) {
         return 0d;
      }
   }//End Method

}//End Class
