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

import utility.TestCommon;

/**
 * {@link DefensiveDoubleStringConverter} test. 
 */
public class DefensiveDoubleStringConverterTest {

   /**
    * Test that any string can be read without exceptioning.
    */
   @Test public void shouldAcceptNonNumber() {
      DefensiveDoubleStringConverter converter = new DefensiveDoubleStringConverter();
      Assert.assertEquals( 0.0, converter.fromString( "anything" ), TestCommon.precision() );
   }//End Method

}//End Class
