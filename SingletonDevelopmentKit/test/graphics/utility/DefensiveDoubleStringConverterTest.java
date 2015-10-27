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
   
   /**
    * Test that any number can be read without exceptioning.
    */
   @Test public void shouldAcceptNumber() {
      DefensiveDoubleStringConverter converter = new DefensiveDoubleStringConverter();
      Assert.assertEquals( 100.0, converter.fromString( "100" ), TestCommon.precision() );
   }//End Method

}//End Class
