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
 * Test for the {@link SearchOnly}.
 */
public class SearchOnlyTest {

   
   private static final String TEST_PROPERTY_VALUE = "anySpecific Value";
   private static PropertyType ANY_NUMBER_TYPE;
   private static PropertyType ANY_PROPERTY;
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
      ANY_PROPERTY = new PropertyTypeImpl( "something", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( ANY_PROPERTY );
      
      ANY_DEFINITION_ONE = Mockito.mock( Definition.class );
      Mockito.when( ANY_DEFINITION_ONE.hasProperty( ANY_PROPERTY ) ).thenReturn( false );
      RequestSystem.store( ANY_DEFINITION_ONE );
      ANY_DEFINITION_TWO = Mockito.mock( Definition.class );
      Mockito.when( ANY_DEFINITION_TWO.hasProperty( ANY_PROPERTY ) ).thenReturn( true );
      RequestSystem.store( ANY_DEFINITION_TWO );
      
      ANY_OBJECT_ONE = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_ONE.getDefinition() ).thenReturn( ANY_DEFINITION_ONE );
      //Deliberately configure incorrect return to prove this is checked.
      Mockito.when( ANY_OBJECT_ONE.get( ANY_PROPERTY ) ).thenReturn( TEST_PROPERTY_VALUE );
      RequestSystem.store( ANY_OBJECT_ONE );
      ANY_OBJECT_TWO = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_TWO.getDefinition() ).thenReturn( ANY_DEFINITION_TWO );
      Mockito.when( ANY_OBJECT_TWO.get( ANY_PROPERTY ) ).thenReturn( TEST_PROPERTY_VALUE );
      RequestSystem.store( ANY_OBJECT_TWO );
      ANY_OBJECT_THREE = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_THREE.getDefinition() ).thenReturn( ANY_DEFINITION_TWO );
      RequestSystem.store( ANY_OBJECT_THREE );
      ANY_OBJECT_FOUR = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_FOUR.getDefinition() ).thenReturn( ANY_DEFINITION_ONE );
      RequestSystem.store( ANY_OBJECT_FOUR );
      
      ANY_NUMBER_TYPE = new PropertyTypeImpl( "number", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( ANY_NUMBER_TYPE, PropertyType.class );
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
      search.includePropertyValue( ANY_PROPERTY, TEST_PROPERTY_VALUE );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_TWO ), 
               matches 
      );
   }// End Method
   
   /**
    * Results should not include {@link PropertyType} and value when value is not correct type.
    */
   @Test public void shouldNotIncludeOnlyPropertyValue() {
      SearchOnly search = new SearchOnly( "search" );
      search.includePropertyValue( ANY_NUMBER_TYPE, TEST_PROPERTY_VALUE );
      
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
      SearchOnly search = new SearchOnly( "search" );
      search.includePropertyValue( ANY_PROPERTY, TEST_PROPERTY_VALUE );
      
      search.identifyMatches();
      Collection< BuilderObject > matches = search.getMatches();
      Assert.assertFalse( matches.isEmpty() );
      
      search.clearIncludedPropertyValues();
      matches = search.getMatches();
      Assert.assertTrue( matches.isEmpty() );
   }// End Method

}// End Class
