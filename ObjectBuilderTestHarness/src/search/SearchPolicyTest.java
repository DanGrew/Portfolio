/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

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
public class SearchPolicyTest {

   
   private static final String TEST_PROPERTY_VALUE = "anySpecific Value";
   private static final Double TEST_NUMBER_VALUE = 54.3498;
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
      ANY_NUMBER_TYPE = new PropertyTypeImpl( "number", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( ANY_NUMBER_TYPE, PropertyType.class );
      
      ANY_DEFINITION_ONE = Mockito.mock( Definition.class );
      Mockito.when( ANY_DEFINITION_ONE.hasProperty( ANY_PROPERTY ) ).thenReturn( false );
      RequestSystem.store( ANY_DEFINITION_ONE );
      ANY_DEFINITION_TWO = Mockito.mock( Definition.class );
      Mockito.when( ANY_DEFINITION_TWO.hasProperty( ANY_PROPERTY ) ).thenReturn( true );
      Mockito.when( ANY_DEFINITION_TWO.hasProperty( ANY_NUMBER_TYPE ) ).thenReturn( true );
      RequestSystem.store( ANY_DEFINITION_TWO );
      
      ANY_OBJECT_ONE = Mockito.mock( BuilderObject.class );
      Mockito.when( ANY_OBJECT_ONE.getDefinition() ).thenReturn( ANY_DEFINITION_ONE );
      //Deliberately configure incorrect return to prove this is checked.
      Mockito.when( ANY_OBJECT_ONE.get( ANY_PROPERTY ) ).thenReturn( TEST_PROPERTY_VALUE );
      Mockito.when( ANY_OBJECT_ONE.get( ANY_NUMBER_TYPE ) ).thenReturn( TEST_NUMBER_VALUE );
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
    * {@link SearchPolicy#ExactString} test.
    */
   @Test public void shouldExactStringMatch() {
      Assert.assertTrue( SearchPolicy.ExactString.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, TEST_PROPERTY_VALUE ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactString} test.
    */
   @Test public void shouldNotExactStringMatch() {
      Assert.assertFalse( SearchPolicy.ExactString.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, "anythingElse" ) );
      Assert.assertFalse( SearchPolicy.ExactString.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, 25.0 ) );
      Assert.assertFalse( SearchPolicy.ExactString.matchesPolicy( ANY_OBJECT_ONE, ANY_NUMBER_TYPE, 25.0 ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ContainsString} test.
    */
   @Test public void shouldContainString() {
      Assert.assertTrue( SearchPolicy.ContainsString.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, "any" ) );
      Assert.assertTrue( SearchPolicy.ContainsString.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, "fic" ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ContainsString} test.
    */
   @Test public void shouldNotContainString() {
      Assert.assertFalse( SearchPolicy.ContainsString.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, "nothing" ) );
      Assert.assertFalse( SearchPolicy.ContainsString.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, "25" ) );
      Assert.assertFalse( SearchPolicy.ContainsString.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, 25.0 ) );
      Assert.assertFalse( SearchPolicy.ContainsString.matchesPolicy( ANY_OBJECT_ONE, ANY_NUMBER_TYPE, 25.0 ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactNumber} test.
    */
   @Test public void shouldExactNumberMatch() {
      Assert.assertTrue( SearchPolicy.ExactNumber.matchesPolicy( ANY_OBJECT_ONE, ANY_NUMBER_TYPE, TEST_NUMBER_VALUE ) );
   }// End Method
   
   /**
    * {@link SearchPolicy#ExactNumber} test.
    */
   @Test public void shouldNotExactNumberMatch() {
      Assert.assertFalse( SearchPolicy.ExactNumber.matchesPolicy( ANY_OBJECT_ONE, ANY_NUMBER_TYPE, "me" ) );
      Assert.assertFalse( SearchPolicy.ExactNumber.matchesPolicy( ANY_OBJECT_ONE, ANY_NUMBER_TYPE, "25" ) );
      Assert.assertFalse( SearchPolicy.ExactNumber.matchesPolicy( ANY_OBJECT_ONE, ANY_PROPERTY, TEST_PROPERTY_VALUE ) );
   }// End Method
   
}// End Class
