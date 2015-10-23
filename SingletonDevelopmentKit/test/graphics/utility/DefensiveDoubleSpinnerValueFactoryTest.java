/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphics.utility;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link DefensiveDoubleSpinnerValueFactory} test.
 */
public class DefensiveDoubleSpinnerValueFactoryTest {

   /**
    * Proves the constructor ensures the {@link DefensiveDoubleStringConverter} is used.
    */
   @Test public void shouldUseDefensiveConverter() {
      DefensiveDoubleSpinnerValueFactory sut = new DefensiveDoubleSpinnerValueFactory( 0, 0 );
      Assert.assertTrue( sut.getConverter() instanceof DefensiveDoubleStringConverter );
   }//End Method

}//End Class
