/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CaliParseUtilitiesTest {

   /**
    * {@link CaliParserUtilities#extractObjectType(String)} acceptance test.
    */
   @Test public void shouldExtractObjectType(){
      Assert.assertEquals( "anything", CaliParserUtilities.extractObjectType( "anything" ) );
      Assert.assertEquals( "anything", CaliParserUtilities.extractObjectType( "anything(" ) );
      Assert.assertEquals( "anything", CaliParserUtilities.extractObjectType( "anything(something" ) );
      Assert.assertEquals( "anything", CaliParserUtilities.extractObjectType( "anything(something)" ) );
      Assert.assertEquals( "anything", CaliParserUtilities.extractObjectType( "anything( something" ) );
      Assert.assertEquals( "anything", CaliParserUtilities.extractObjectType( "anything( something )" ) );
      Assert.assertEquals( "anything", CaliParserUtilities.extractObjectType( "anything(( something )" ) );
   }//End Method
      
   /**
    * {@link CaliParserUtilities#extractObjectType(String)} reject test.
    */
   @Test public void shouldNotExtractObjectType(){
      Assert.assertEquals( "", CaliParserUtilities.extractObjectType( "" ) );
      Assert.assertEquals( "", CaliParserUtilities.extractObjectType( "  " ) );
      Assert.assertEquals( "", CaliParserUtilities.extractObjectType( null ) );
      Assert.assertEquals( "", CaliParserUtilities.extractObjectType( "anything here ( something )" ) );
   }//End Method
   
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
