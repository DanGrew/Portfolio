/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import search.SearchSpace.SearchCriteria;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

/**
 * Test for the {@link SerializableSearchOnly}.
 */
public class SerializableSearchSpaceTest {
   
   @Rule public TemporaryFolder folder = new TemporaryFolder();
   private static PropertyType ANY_PROPERTY_TYPE_1;
   private static PropertyType ANY_PROPERTY_TYPE_2;
   private static PropertyType ANY_PROPERTY_TYPE_3;
   
   /**
    * Method to setup some dependent {@link Singleton}s.
    */
   @BeforeClass public static void setup(){
      ANY_PROPERTY_TYPE_1 = new PropertyTypeImpl( "type1", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( ANY_PROPERTY_TYPE_1, PropertyType.class );
      ANY_PROPERTY_TYPE_2 = new PropertyTypeImpl( "type2", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( ANY_PROPERTY_TYPE_2, PropertyType.class );
      ANY_PROPERTY_TYPE_3 = new PropertyTypeImpl( "type3", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( ANY_PROPERTY_TYPE_3, PropertyType.class );
   }// End Method

   /**
    * Method to test the serialization of {@link SearchSpace}s using {@link XmlSearchSpaceWrapper}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      List< SearchSpace > actualSearches = new ArrayList< SearchSpace >();
      
      SearchSpace search1 = new SearchSpace( "search1" );
      actualSearches.add( search1 );
      
      SearchSpace search2 = new SearchSpace( "search2" );
      search2.include( SearchPolicy.ExactString, ANY_PROPERTY_TYPE_1, "anything" );
      search2.include( SearchPolicy.ExactNumber, ANY_PROPERTY_TYPE_2, 25.0 );
      actualSearches.add( search2 );
      
      SearchSpace search3 = new SearchSpace( "search3" );
      actualSearches.add( search3 );
      
      XmlSearchSpaceWrapper serializedCollection = new XmlSearchSpaceWrapper();
      serializedCollection.addAllUnwrapped( actualSearches.iterator() );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlSearchSpaceWrapper.class, XmlSearchSpaceImpl.class );
      
      XmlSearchSpaceWrapper parsedSearchWrapper = SerializationSystem.loadSingletonsFromFile( 
               XmlSearchSpaceWrapper.class, testFile, XmlSearchSpaceWrapper.class, XmlSearchSpaceImpl.class );
      Assert.assertNotNull( parsedSearchWrapper );
      List< SearchSpace > parsedSearches = parsedSearchWrapper.retrieveSingletons();
      assertSearchOnlyLists( actualSearches, parsedSearches );
   }// End Method
   
   /**
    * Method to test that when reading a {@link SearchSpace} that already exists, it is overwritten. 
    */
   @Test public void overwriteTest() throws IOException{
      final String name = "graph";
      SearchSpace writableSearch = new SearchSpace( name );
      final String writtenValue = "newValue";
      writableSearch.include( SearchPolicy.ExactString, ANY_PROPERTY_TYPE_1, writtenValue );
      
      SearchSpace existingSearch = new SearchSpace( name );
      final String originalValue = "originalValue";
      existingSearch.include( SearchPolicy.ContainsString, ANY_PROPERTY_TYPE_1, originalValue );
      RequestSystem.store( existingSearch, Search.class );
      
      Assert.assertEquals( existingSearch, RequestSystem.retrieve( SearchSpace.class, name ) );
      Assert.assertNotEquals( writableSearch, RequestSystem.retrieve( SearchSpace.class, name ) );
      
      XmlSearchSpaceWrapper serializedCollection = new XmlSearchSpaceWrapper();
      serializedCollection.addUnwrapped( writableSearch );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlSearchSpaceWrapper.class, XmlSearchSpaceImpl.class );
      
      XmlSearchSpaceWrapper parsedPropertyType = SerializationSystem.loadSingletonsFromFile( 
               XmlSearchSpaceWrapper.class, testFile, XmlSearchSpaceWrapper.class, XmlSearchSpaceImpl.class );
      Assert.assertNotNull( parsedPropertyType );
      
      SearchSpace resultingVersion = RequestSystem.retrieve( SearchSpace.class, name );
      Assert.assertNotNull( resultingVersion );
      assertSearchOnlyLists( Arrays.asList( writableSearch ), Arrays.asList( resultingVersion ) );
   }// End Method
   
   /**
    * Method to assert that two {@link List} of {@link SearchSpace}s are identical.
    * @param actualSearches the original {@link SearchSpace}s.
    * @param parsedSearches the parsed {@link SearchSpace}s.
    */
   public static void assertSearchOnlyLists( List< SearchSpace > actualSearches, List< SearchSpace > parsedSearches ){
      Assert.assertEquals( actualSearches.size(), parsedSearches.size() );
      for ( int i = 0; i < actualSearches.size(); i++ ) {
         SearchSpace expected = actualSearches.get( i );
         SearchSpace parsed = parsedSearches.get( i );

         Collection< SearchCriteria > expectedIncludedTypes = expected.getInclusions();
         Collection< SearchCriteria > parsedIncludedTypes = parsed.getInclusions();
         Assert.assertEquals( expectedIncludedTypes, parsedIncludedTypes );
      }
   }// End Method

}// End Class
