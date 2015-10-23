/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package architecture.utility;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link Defense} test.
 */
public class DefenseTest {

   /**
    * {@link Defense#defendString(Object, String)} test.
    */
   @Test public void shouldDefendString() {
      Assert.assertNull( Defense.defendString( null, null ) );
      Assert.assertEquals( "anything", Defense.defendString( "anything", null ) );
      Assert.assertEquals( "anything", Defense.defendString( null, "anything" ) );
      Object object = new HashMap<>();
      Assert.assertEquals( object.toString(), Defense.defendString( object, null ) );
   }//End Method
   
   /**
    * {@link Defense#defendNumber(Object, Number)} test.
    */
   @Test public void shouldDefendNumber() {
      Assert.assertNull( Defense.defendNumber( null, null ) );
      Number testNumber = 100d;
      Assert.assertEquals( testNumber, Defense.defendNumber( testNumber, null ) );
      Assert.assertEquals( testNumber, Defense.defendNumber( null, testNumber ) );
      Object object = new HashMap<>();
      Assert.assertEquals( testNumber, Defense.defendNumber( object, testNumber ) );
   }//End Method

}//End Class
