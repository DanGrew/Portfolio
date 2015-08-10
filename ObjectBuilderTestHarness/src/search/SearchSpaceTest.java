/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import static testmodels.PersonModel.AGE;
import static testmodels.PersonModel.COMPANY;
import static testmodels.PersonModel.DAD;
import static testmodels.PersonModel.DAN;
import static testmodels.PersonModel.LIZ;
import static testmodels.PersonModel.MOM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import object.BuilderObject;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import propertytype.PropertyType;
import testmodels.PersonModel;

/**
 * Test for the {@link SearchSpace}.
 */
public class SearchSpaceTest {
   
   /**
    * Method to setup up the test data.
    */
   @BeforeClass public static void setup(){
      PersonModel.setup();
   }// End Method
   
   /**
    * {@link SearchSpace} should construct.
    */
   @Test public void shouldConstruct() {
      Search search = new SearchSpace( "search" );
      Assert.assertTrue( search.getMatches().isEmpty() );
      search.identifyMatches();
      Assert.assertTrue( search.getMatches().isEmpty() );
   }// End Method
   
   /**
    * Results should only include matching {@link PropertyType} and value.
    */
   @Test public void shouldIncludePropertyValue() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "CHSCHS" );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( MOM, DAD ), 
               matches 
      );
   }// End Method
   
   /**
    * Results should not include {@link PropertyType} and value when value is not correct type.
    */
   @Test public void shouldNotIncludePropertyValue() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "anything" );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList(), 
               matches 
      );
   }// End Method
   
   /**
    * Inclusions should be cleared for {@link PropertyType} and values.
    */
   @Test public void shouldClearIncludedPropertyValues() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "CHSCHS" );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertFalse( matches.isEmpty() );
      
      search.clearIncluded();
      matches = search.getMatches();
      Assert.assertTrue( matches.isEmpty() );
   }// End Method
   
   /**
    * {@link SearchSpace#include(SearchPolicy, PropertyType, String)} multiple.
    */
   @Test public void shouldIncludeMultiple() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "CHSCHS" );
      search.include( SearchPolicy.ExactNumber, AGE, "26" );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( DAN, LIZ, MOM, DAD ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchSpace#exclude(SearchPolicy, PropertyType, String)} single.
    */
   @Test public void shouldExcludeSingle() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "CHSCHS" );
      search.include( SearchPolicy.ExactNumber, AGE, "26" );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( DAN, LIZ, MOM, DAD ), 
               matches 
      );
      
      search.exclude( SearchPolicy.ExactString, COMPANY, "Graffica" );
      
      search.identifyMatches();
      matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( LIZ, MOM, DAD ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchSpace#exclude(SearchPolicy, PropertyType, String)} multiple.
    */
   @Test public void shouldExcludeMultiple() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "CHSCHS" );
      search.include( SearchPolicy.ExactNumber, AGE, "26" );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( DAN, LIZ, MOM, DAD ), 
               matches 
      );
      
      search.exclude( SearchPolicy.ExactString, COMPANY, "Graffica" );
      search.exclude( SearchPolicy.ExactNumber, AGE, "53" );
      
      search.identifyMatches();
      matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( LIZ ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchSpace#include(SearchPolicy, PropertyType, String)} invalid.
    */
   @Test public void shouldNotIncludeIncorrectType() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, AGE, "25.0" );
      search.include( SearchPolicy.ExactNumber, COMPANY, "anything" );
      
      Assert.assertEquals( new ArrayList<>(), search.getInclusions() );
   }// End Method

   /**
    * {@link SearchSpace#exclude(SearchPolicy, PropertyType, String)} invalid.
    */
   @Test public void shouldNotExcludeIncorrectType() {
      SearchSpace search = new SearchSpace( "search" );
      search.exclude( SearchPolicy.ExactString, AGE, "25.0" );
      search.exclude( SearchPolicy.ExactNumber, COMPANY, "anything" );
      
      Assert.assertEquals( new ArrayList<>(), search.getExclusions() );
   }// End Method
}// End Class
