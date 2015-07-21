/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CaliParseUtilitiesTest {

   @Test public void shouldExtractObjectType(){
      fail();
   }
   
   @Test public void shouldNotExtractObjectType(){
      fail();
   }
   
   /**
    * {@link CaliParserUtilities#lowestCommonSubstring(java.util.List)}.
    */
   @Test public void shouldFindCommonPrefix(){
      Assert.assertEquals(
               "Test",
               CaliParserUtilities.lowestCommonSubstring( Arrays.asList( 
                    "TestAnything",
                    "TestElse",
                    "Test"
               ) ) 
      );
      Assert.assertEquals(
               "Test",
               CaliParserUtilities.lowestCommonSubstring( Arrays.asList( 
                    "TestAnything",
                    "TestSomeAnything",
                    "TestSomeMoreAnything"
               ) ) 
      );
      Assert.assertEquals(
               "Test",
               CaliParserUtilities.lowestCommonSubstring( Arrays.asList( 
                    "TestMoreAnything",
                    "TestSomeAnything"
               ) ) 
      );
      Assert.assertEquals(
               "",
               CaliParserUtilities.lowestCommonSubstring( Arrays.asList() ) 
      );
      Assert.assertEquals(
               "anything",
               CaliParserUtilities.lowestCommonSubstring( Arrays.asList( "anything" ) ) 
      );
   }// End Method
}// End Class
