/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.util.Arrays;
import java.util.Collection;

import model.singleton.Singleton;
import object.BuilderObject;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytype.PropertyType;

/**
 * Test for the {@link SearchOnly}.
 */
public class SearchOnlyTest {

   /**
    * Method to setup up the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      SearchAllTest.setup();
   }// End Method
   
   /**
    * {@link SearchOnly} should construct.
    */
   @Test public void shouldConstruct() {
      Search search = new SearchOnly( "search" );
      Assert.assertTrue( search.getMatches().isEmpty() );
      search.identifyMatches();
      Assert.assertTrue( search.getMatches().isEmpty() );
   }// End Method
   
   /**
    * Results should only include matching {@link PropertyType} and value.
    */
   @Test public void shouldIncludeOnlyPropertyValue() {
      SearchOnly search = new SearchOnly( "search" );
      search.includePropertyValue( SearchAllTest.ANY_PROPERTY, SearchAllTest.TEST_PROPERTY_VALUE );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( SearchAllTest.ANY_OBJECT_TWO ), 
               matches 
      );
   }// End Method
   
   /**
    * Inclusions should be cleared for {@link PropertyType} and values.
    */
   @Test public void shouldClearIncludedPropertyValues() {
      SearchOnly search = new SearchOnly( "search" );
      search.includePropertyValue( SearchAllTest.ANY_PROPERTY, SearchAllTest.TEST_PROPERTY_VALUE );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertFalse( matches.isEmpty() );
      
      search.clearIncludedPropertyValues();
      matches = search.getMatches();
      Assert.assertTrue( matches.isEmpty() );
   }// End Method

}// End Class
