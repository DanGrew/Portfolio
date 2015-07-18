/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package search;

import graphs.graph.Graph;
import graphs.graph.SerializableGraph;
import graphs.graph.XmlGraphWrapper;

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
import property.Property;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;

/**
 * Test for the {@link SerializableSearchOnly}.
 */
public class SerializableSearchOnlyTest {
   
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
    * Method to test the serialization of {@link SearchOnly}s using {@link XmlSearchOnlyWrapper}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      List< SearchOnly > actualSearches = new ArrayList< SearchOnly >();
      
      SearchOnly search1 = new SearchOnly( "search1" );
      actualSearches.add( search1 );
      
      SearchOnly search2 = new SearchOnly( "search2" );
      search2.includePropertyValue( ANY_PROPERTY_TYPE_1, "anything" );
      search2.includePropertyValue( ANY_PROPERTY_TYPE_2, "somethingElse" );
      actualSearches.add( search2 );
      
      SearchOnly search3 = new SearchOnly( "search3" );
      actualSearches.add( search3 );
      
      XmlSearchOnlyWrapper serializedCollection = new XmlSearchOnlyWrapper();
      serializedCollection.addAllUnwrapped( actualSearches.iterator() );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlSearchOnlyWrapper.class, XmlSearchOnlyImpl.class );
      
      XmlSearchOnlyWrapper parsedSearchWrapper = SerializationSystem.loadSingletonsFromFile( 
               XmlSearchOnlyWrapper.class, testFile, XmlSearchOnlyWrapper.class, XmlSearchOnlyImpl.class );
      Assert.assertNotNull( parsedSearchWrapper );
      List< SearchOnly > parsedSearches = parsedSearchWrapper.retrieveSingletons();
      assertSearchOnlyLists( actualSearches, parsedSearches );
   }// End Method
   
   /**
    * Method to test that when reading a {@link SearchOnly} that already exists, it is overwritten. 
    */
   @Test public void overwriteTest() throws IOException{
      final String name = "graph";
      SearchOnly writableSearch = new SearchOnly( name );
      final String writtenValue = "newValue";
      writableSearch.includePropertyValue( ANY_PROPERTY_TYPE_1, writtenValue );
      
      SearchOnly existingSearch = new SearchOnly( name );
      final String originalValue = "originalValue";
      existingSearch.includePropertyValue( ANY_PROPERTY_TYPE_1, originalValue );
      RequestSystem.store( existingSearch, Search.class );
      
      Assert.assertEquals( existingSearch, RequestSystem.retrieve( SearchOnly.class, name ) );
      Assert.assertNotEquals( writableSearch, RequestSystem.retrieve( SearchOnly.class, name ) );
      
      XmlSearchOnlyWrapper serializedCollection = new XmlSearchOnlyWrapper();
      serializedCollection.addUnwrapped( writableSearch );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlSearchOnlyWrapper.class, XmlSearchOnlyImpl.class );
      
      XmlSearchOnlyWrapper parsedPropertyType = SerializationSystem.loadSingletonsFromFile( 
               XmlSearchOnlyWrapper.class, testFile, XmlSearchOnlyWrapper.class, XmlSearchOnlyImpl.class );
      Assert.assertNotNull( parsedPropertyType );
      
      SearchOnly resultingVersion = RequestSystem.retrieve( SearchOnly.class, name );
      Assert.assertNotNull( resultingVersion );
      assertSearchOnlyLists( Arrays.asList( writableSearch ), Arrays.asList( resultingVersion ) );
   }// End Method
   
   /**
    * Method to assert that two {@link List} of {@link SearchOnly}s are identical.
    * @param actualSearches the original {@link SearchOnly}s.
    * @param parsedSearches the parsed {@link SearchOnly}s.
    */
   public static void assertSearchOnlyLists( List< SearchOnly > actualSearches, List< SearchOnly > parsedSearches ){
      Assert.assertEquals( actualSearches.size(), parsedSearches.size() );
      for ( int i = 0; i < actualSearches.size(); i++ ) {
         SearchOnly expected = actualSearches.get( i );
         SearchOnly parsed = parsedSearches.get( i );
         
         Collection< Property > expectedIncludedTypes = expected.getIncludedPropertyTypes();
         Collection< Property > parsedIncludedTypes = parsed.getIncludedPropertyTypes();
         Assert.assertEquals( expectedIncludedTypes, parsedIncludedTypes );
      }
   }// End Method

}// End Class
