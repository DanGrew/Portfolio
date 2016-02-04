/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for the {@link PropertyTypeDescriber}.
 */
public class PropertyTypeDescriberTest {

   private static final String TEST_VALUE = "anything";
   private static OutlineDescriber describer;
   
   /**
    * MEthod to setup the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      describer = new OutlineDescriberImpl( TEST_VALUE );      
   }// End Method
   
   /**
    * {@link PropertyTypeDescriber#getColumnDescription(int)} test.
    */
   @Test public void shouldDescribeColumns() {
      Assert.assertEquals( TEST_VALUE, describer.getColumnDescription( 0 ) );
      Assert.assertEquals( null, describer.getColumnDescription( 1 ) );
   }// End Method
   
   /**
    * {@link PropertyTypeDescriber#getColumnEntry(int)} test.
    */
   @Test public void shouldFillEntries(){
      Assert.assertEquals( TEST_VALUE, describer.getColumnEntry( 0 ) );
      Assert.assertEquals( null, describer.getColumnEntry( 1 ) );
   }// End Method

}// End Class
