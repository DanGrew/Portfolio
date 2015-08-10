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
import objecttype.Definition;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;

/**
 * Test for the {@link SearchSpace}.
 */
public class SearchSpaceTest {
   
   private static final String TEST_PROPERTY_VALUE = "anySpecific Value";
   private static final Double TEST_NUMBER_VALUE = 54.3498;
   private static PropertyType ANY_NUMBER_TYPE;
   private static PropertyType ANY_STRING_PROPERTY;
   private static Definition ANY_DEFINITION_ONE;
   private static Definition ANY_DEFINITION_TWO;
   private static BuilderObject ANY_OBJECT_ONE;
   private static BuilderObject ANY_OBJECT_TWO;
   private static BuilderObject ANY_OBJECT_THREE;
   private static BuilderObject ANY_OBJECT_FOUR;
   
   /**
    * Method to setup the {@link Singleton}s for the test.
    */
   @BeforeClass public static void setup(){
      ANY_STRING_PROPERTY = new PropertyTypeImpl( "something", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( ANY_STRING_PROPERTY );
      ANY_NUMBER_TYPE = new PropertyTypeImpl( "number", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( ANY_NUMBER_TYPE, PropertyType.class );
      
      ANY_DEFINITION_ONE = Mockito.mock( Definition.class );
      Mockito.when( ANY_DEFINITION_ONE.hasProperty( ANY_STRING_PROPERTY ) ).thenReturn( false );
      RequestSystem.store( ANY_DEFINITION_ONE );
      ANY_DEFINITION_TWO = Mockito.mock( Definition.class );
      Mockito.when( ANY_DEFINITION_TWO.hasProperty( ANY_STRING_PROPERTY ) ).thenReturn( true );
      RequestSystem.store( ANY_DEFINITION_TWO );
      
      ANY_OBJECT_ONE = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_ONE.getDefinition() ).thenReturn( ANY_DEFINITION_ONE );
      //Deliberately configure incorrect return to prove this is checked.
      Mockito.when( ANY_OBJECT_ONE.get( ANY_STRING_PROPERTY ) ).thenReturn( TEST_PROPERTY_VALUE );
      RequestSystem.store( ANY_OBJECT_ONE );
      ANY_OBJECT_TWO = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_TWO.getDefinition() ).thenReturn( ANY_DEFINITION_TWO );
      Mockito.when( ANY_OBJECT_TWO.get( ANY_STRING_PROPERTY ) ).thenReturn( TEST_PROPERTY_VALUE );
      RequestSystem.store( ANY_OBJECT_TWO );
      ANY_OBJECT_THREE = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_THREE.getDefinition() ).thenReturn( ANY_DEFINITION_TWO );
      RequestSystem.store( ANY_OBJECT_THREE );
      ANY_OBJECT_FOUR = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_FOUR.getDefinition() ).thenReturn( ANY_DEFINITION_ONE );
      Mockito.when( ANY_OBJECT_FOUR.get( ANY_NUMBER_TYPE ) ).thenReturn( TEST_NUMBER_VALUE );
      RequestSystem.store( ANY_OBJECT_FOUR );
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
      search.include( SearchPolicy.ExactString, ANY_STRING_PROPERTY, TEST_PROPERTY_VALUE );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_TWO ), 
               matches 
      );
   }// End Method
   
   /**
    * Results should not include {@link PropertyType} and value when value is not correct type.
    */
   @Test public void shouldNotIncludePropertyValue() {
      SearchSpace search = new SearchSpace( "search" );
      search.include( SearchPolicy.ExactString, ANY_NUMBER_TYPE, TEST_PROPERTY_VALUE );
      
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
      search.include( SearchPolicy.ExactString, ANY_STRING_PROPERTY, TEST_PROPERTY_VALUE );
      
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
      search.include( SearchPolicy.ExactString, ANY_STRING_PROPERTY, TEST_PROPERTY_VALUE );
      search.include( SearchPolicy.ExactNumber, ANY_NUMBER_TYPE, TEST_NUMBER_VALUE );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_TWO, ANY_OBJECT_FOUR ), 
               matches 
      );
   }// End Method

}// End Class
