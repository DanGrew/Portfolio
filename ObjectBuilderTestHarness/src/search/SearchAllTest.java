/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import model.singleton.Singleton;
import object.BuilderObject;
import objecttype.Definition;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import propertytype.PropertyType;
import architecture.request.RequestSystem;

/**
 * Test for the {@link SearchAll}.
 */
public class SearchAllTest {
   
   private static final String TEST_PROPERTY_VALUE = "anySpecificValue";
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
      ANY_PROPERTY = Mockito.mock( PropertyType.class );
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
   }// End Method

   /**
    * Test to prove the {@link SearchAll} constructs correctly.
    */
   @Test public void shouldConstruct(){
      final String searchName = "anySearchName";
      Search search = new SearchAll( searchName );
      Assert.assertEquals( searchName, search.getIdentification() );
   }// End Method
   
   /**
    * Test for initial matching.
    */
   @Test public void shouldMatchNothingInitially(){
      Search search = new SearchAll( "everything" );
      
      Collection< BuilderObject > matches = new ArrayList<>( search.getMostResultMatches() );
      Assert.assertEquals( 
               new ArrayList<>(), 
               matches 
      );
   }// End Method
   
   /**
    * Test for initial match result.
    */
   @Test public void shouldMatchEverything() {
      Search search = new SearchAll( "everything" );
      search.identifyMatches();
      
      Collection< BuilderObject > matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_TWO, ANY_OBJECT_THREE, ANY_OBJECT_FOUR ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchAll#filterInstance(BuilderObject)} test.
    */
   @Test public void shouldMatchFilterParticularObjects() {
      SearchAll search = new SearchAll( "objects" );
      search.filterInstance( ANY_OBJECT_TWO );
      search.filterInstance( ANY_OBJECT_ONE );
      search.identifyMatches();
      
      Collection< BuilderObject > matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_THREE, ANY_OBJECT_FOUR ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchAll#clearFilteredObjects()} test.
    */
   @Test public void shouldClearFilteredObjects(){
      SearchAll search = new SearchAll( "clearObjects" );
      search.filterInstance( ANY_OBJECT_TWO );
      search.filterInstance( ANY_OBJECT_ONE );
      search.identifyMatches();
      
      Collection< BuilderObject > matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_THREE, ANY_OBJECT_FOUR ), 
               matches 
      );
      
      search.clearFilteredObjects();
      search.identifyMatches();
      matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_TWO, ANY_OBJECT_THREE, ANY_OBJECT_FOUR ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchAll#filterDefinition(Definition)} test.
    */
   @Test public void shouldMatchFilterParticularDefinitions() {
      SearchAll search = new SearchAll( "definitions" );
      search.filterDefinition( ANY_DEFINITION_TWO );
      search.identifyMatches();
      
      Collection< BuilderObject > matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_FOUR ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchAll#clearFilteredDefinitions()} test.
    */
   @Test public void shouldClearFilteredDefinitions() {
      SearchAll search = new SearchAll( "clearDefinitions" );
      search.filterDefinition( ANY_DEFINITION_TWO );
      search.identifyMatches();
      
      Collection< BuilderObject > matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_FOUR ), 
               matches 
      );
      
      search.clearFilteredDefinitions();
      search.identifyMatches();
      matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_TWO, ANY_OBJECT_THREE, ANY_OBJECT_FOUR ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchAll#filterProperty(PropertyType, Object)} test.
    */
   @Test public void shouldMatchEverythingWithPropertyValue() {
      SearchAll search = new SearchAll( "clearDefinitions" );
      
      search.filterProperty( ANY_PROPERTY, TEST_PROPERTY_VALUE );
      search.identifyMatches();
      
      Collection< BuilderObject > matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_THREE, ANY_OBJECT_FOUR ), 
               matches 
      );
   }// End Method
   
   /**
    * {@link SearchAll#clearFilteredProperties()} test.
    */
   @Test public void shouldClearPropertyValue() {
      SearchAll search = new SearchAll( "clearDefinitions" );
      
      search.filterProperty( ANY_PROPERTY, TEST_PROPERTY_VALUE );
      search.identifyMatches();
      
      Collection< BuilderObject > matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_THREE, ANY_OBJECT_FOUR ), 
               matches 
      );
      
      search.clearFilteredProperties();
      search.identifyMatches();
      matches = search.getMostResultMatches();
      Assert.assertEquals( 
               Arrays.asList( ANY_OBJECT_ONE, ANY_OBJECT_TWO, ANY_OBJECT_THREE, ANY_OBJECT_FOUR ), 
               matches 
      );
   }// End Method
   
}// End Class
