/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.util.StringConverter;

/**
 * Defensive extension of the {@link DoubleSpinnerValueFactory} that does not crash when
 * an unexpected value is found.
 */
public class DefensiveDoubleSpinnerValueFactory extends DoubleSpinnerValueFactory {

   /**
    * Constructs a new {@link DefensiveDoubleSpinnerValueFactory}, setting the {@link DefensiveDoubleStringConverter}
    * as the {@link StringConverter}.
    * @param min @see {@link DefensiveDoubleSpinnerValueFactory#getMin()}.
    * @param max @see {@link DefensiveDoubleSpinnerValueFactory#getMax()}.
    */
   public DefensiveDoubleSpinnerValueFactory( double min, double max ) {
      super( min, max );
      setConverter( new DefensiveDoubleStringConverter() );
   }//End Constructor

}//End Class
