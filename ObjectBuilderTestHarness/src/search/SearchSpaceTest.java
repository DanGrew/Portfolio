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
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;

/**
 * Test for the {@link SearchSpace}.
 */
public class SearchSpaceTest {
   
   private static PropertyType AGE;
   private static PropertyType COMPANY;
   private static PropertyType HAIR_COLOUR;
   
   private static Definition PERSON;
   
   private static BuilderObject DAN;
   private static BuilderObject LIZ;
   private static BuilderObject MOM;
   private static BuilderObject DAD;
   
   /**
    * Method to setup the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      AGE = new PropertyTypeImpl( "Age", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( AGE, PropertyType.class );
      COMPANY = new PropertyTypeImpl( "Company", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( COMPANY, PropertyType.class );
      HAIR_COLOUR = new PropertyTypeImpl( "HairColour", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( HAIR_COLOUR, PropertyType.class );
      
      PERSON = new DefinitionImpl( "Person" );
      PERSON.addPropertyType( AGE );
      PERSON.addPropertyType( COMPANY );
      PERSON.addPropertyType( HAIR_COLOUR );
      RequestSystem.store( PERSON, Definition.class );
      
      DAN = new BuilderObjectImpl( "Dan", PERSON );
      DAN.set( AGE, 26 );
      DAN.set( COMPANY, "Graffica" );
      DAN.set( HAIR_COLOUR, "Brown" );
      RequestSystem.store( DAN, BuilderObject.class );
      
      LIZ = new BuilderObjectImpl( "Liz", PERSON );
      LIZ.set( AGE, 26 );
      LIZ.set( COMPANY, "Mvp" );
      LIZ.set( HAIR_COLOUR, "Brown" );
      RequestSystem.store( LIZ, BuilderObject.class );
      
      MOM = new BuilderObjectImpl( "Mom", PERSON );
      MOM.set( AGE, 53 );
      MOM.set( COMPANY, "CHSCHS" );
      MOM.set( HAIR_COLOUR, "Brown" );
      RequestSystem.store( MOM, BuilderObject.class );
      
      DAD = new BuilderObjectImpl( "Dad", PERSON );
      DAD.set( AGE, 53 );
      DAD.set( COMPANY, "CHSCHS" );
      DAD.set( HAIR_COLOUR, "Brown" );
      RequestSystem.store( DAD, BuilderObject.class );
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
    * Results should only include matching {@link PropertyType} and value.
    */
   @Test public void shouldIncludeMultiple() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "CHSCHS" );
      search.include( SearchPolicy.ExactNumber, AGE, 26 );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( DAN, LIZ, MOM, DAD ), 
               matches 
      );
   }// End Method
   
   /**
    * Results should only include matching {@link PropertyType} and value.
    */
   @Test public void shouldExcludeSingle() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "CHSCHS" );
      search.include( SearchPolicy.ExactNumber, AGE, 26 );
      
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
    * Results should only include matching {@link PropertyType} and value.
    */
   @Test public void shouldExcludeMultiple() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, COMPANY, "CHSCHS" );
      search.include( SearchPolicy.ExactNumber, AGE, 26 );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( DAN, LIZ, MOM, DAD ), 
               matches 
      );
      
      search.exclude( SearchPolicy.ExactString, COMPANY, "Graffica" );
      search.exclude( SearchPolicy.ExactNumber, AGE, 53 );
      
      search.identifyMatches();
      matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( LIZ ), 
               matches 
      );
   }// End Method

}// End Class
