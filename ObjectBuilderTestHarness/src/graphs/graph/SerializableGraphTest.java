/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package graphs.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import architecture.request.RequestSystem;
import architecture.serialization.SerializationSystem;
import graphs.graph.sorting.GraphSort;
import graphs.series.GroupEvaluation;
import model.singleton.Singleton;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;

/**
 * Test for the {@link SerializableGraph}.
 */
public class SerializableGraphTest {
   
   @Rule public TemporaryFolder folder = new TemporaryFolder();
   private static PropertyType ANY_PROPERTY_TYPE_1;
   private static PropertyType ANY_PROPERTY_TYPE_2;
   private static PropertyType ANY_PROPERTY_TYPE_3;
   
   /**
    * Method to setup some dependent {@link Singleton}s.
    */
   @BeforeClass public static void setup(){
      RequestSystem.reset();
      ANY_PROPERTY_TYPE_1 = new PropertyTypeImpl( "type1", ClassParameterTypes.STRING_PARAMETER_TYPE );
      RequestSystem.store( ANY_PROPERTY_TYPE_1, PropertyType.class );
      ANY_PROPERTY_TYPE_2 = new PropertyTypeImpl( "type2", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( ANY_PROPERTY_TYPE_2, PropertyType.class );
      ANY_PROPERTY_TYPE_3 = new PropertyTypeImpl( "type3", ClassParameterTypes.NUMBER_PARAMETER_TYPE );
      RequestSystem.store( ANY_PROPERTY_TYPE_3, PropertyType.class );
   }// End Method

   /**
    * Method to test the serialization of {@link Graph}s using {@link XmlGraphWrapper}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      List< Graph > actualGraphs = new ArrayList< Graph >();
      
      Graph testGraph1 = new Graph( "graph1" );
      actualGraphs.add( testGraph1 );
      
      Graph testGraph2 = new Graph( "graph2" );
      testGraph2.setHorizontalProperty( ANY_PROPERTY_TYPE_1 );
      testGraph2.setHorizontalAxisLabel( "SomeHorizontalAxisLabel" );
      testGraph2.setHorizontalSort( GraphSort.StringReverseAlphabetical );
      testGraph2.addVerticalProperty( ANY_PROPERTY_TYPE_2 );
      testGraph2.addVerticalProperty( ANY_PROPERTY_TYPE_3 );
      testGraph2.addGroupEvaluation( ANY_PROPERTY_TYPE_2, GroupEvaluation.Count );
      testGraph2.addGroupEvaluation( ANY_PROPERTY_TYPE_3, GroupEvaluation.Maximum );
      testGraph2.setDefaultValueForUndefinedNumber( 4.0 );
      testGraph2.setDefaultValueForUndefinedString( "Default" );
      testGraph2.setDimension( 456, 987.654 );
      testGraph2.setVerticalAxisLabel( "aLabelForVertical" );;
      actualGraphs.add( testGraph2 );
      
      Graph testGraph3 = new Graph( "graph3" );
      actualGraphs.add( testGraph3 );
      
      XmlGraphWrapper serializedCollection = new XmlGraphWrapper();
      serializedCollection.addAllUnwrapped( actualGraphs.iterator() );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlGraphWrapper.class, XmlGraphImpl.class );
      
      XmlGraphWrapper parsedGraph = SerializationSystem.loadSingletonsFromFile( 
               XmlGraphWrapper.class, testFile, XmlGraphWrapper.class, XmlGraphImpl.class );
      Assert.assertNotNull( parsedGraph );
      List< Graph > parsedGraphs = parsedGraph.retrieveSingletons();
      assertGraphLists( actualGraphs, parsedGraphs );
   }// End Method
   
   /**
    * Method to test that when reading a {@link Graph} that already exists, it is overwritten. 
    */
   @Test public void overwriteTest() throws IOException{
      final String name = "graph";
      Graph writableGraph = new Graph( name );
      final String writtenLabel = "newLabel";
      writableGraph.setHorizontalAxisLabel( writtenLabel );
      
      Graph existingType = new Graph( name );
      final String originalLabel = "originalLabel";
      existingType.setHorizontalAxisLabel( originalLabel );
      RequestSystem.store( existingType, Graph.class );
      
      Assert.assertEquals( existingType, RequestSystem.retrieve( Graph.class, name ) );
      Assert.assertNotEquals( writableGraph, RequestSystem.retrieve( Graph.class, name ) );
      
      XmlGraphWrapper serializedCollection = new XmlGraphWrapper();
      serializedCollection.addUnwrapped( writableGraph );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( serializedCollection, testFile, XmlGraphWrapper.class, XmlGraphImpl.class );
      
      XmlGraphWrapper parsedPropertyType = SerializationSystem.loadSingletonsFromFile( 
               XmlGraphWrapper.class, testFile, XmlGraphWrapper.class, XmlGraphImpl.class );
      Assert.assertNotNull( parsedPropertyType );
      
      Graph resultingVersion = RequestSystem.retrieve( Graph.class, name );
      Assert.assertNotNull( resultingVersion );
      Assert.assertEquals( writableGraph.getHorizontalAxisLabel(), resultingVersion.getHorizontalAxisLabel() );
   }// End Method
   
   /**
    * Method to assert that two {@link List} of {@link Graph}s are identical.
    * @param actualGraphs the original {@link Graph}s.
    * @param parsedGraphs the parsed {@link Graph}s.
    */
   public static void assertGraphLists( List< Graph > actualGraphs, List< Graph > parsedGraphs){
      Assert.assertEquals( actualGraphs.size(), parsedGraphs.size() );
      for ( int i = 0; i < actualGraphs.size(); i++ ) {
         Graph expected = actualGraphs.get( i );
         Graph parsed = parsedGraphs.get( i );
         Assert.assertEquals( expected.getDataSeries(), parsed.getDataSeries() );
         Assert.assertEquals( expected.getVerticalProperties(), parsed.getVerticalProperties() );
         Assert.assertEquals( expected.getGroupEvaluations(), parsed.getGroupEvaluations() );
         Assert.assertEquals( expected.getVerticalAxisLabel(), parsed.getVerticalAxisLabel() );
         Assert.assertEquals( expected.getHorizontalProperty(), parsed.getHorizontalProperty() );
         Assert.assertEquals( expected.getHorizontalSort(), parsed.getHorizontalSort() );
         Assert.assertEquals( expected.getHorizontalAxisLabel(), parsed.getHorizontalAxisLabel() );
         Assert.assertEquals( expected.getDefaultValueForUndefinedNumber(), parsed.getDefaultValueForUndefinedNumber() );
         Assert.assertEquals( expected.getDefaultValueForUndefinedString(), parsed.getDefaultValueForUndefinedString() );
         Assert.assertEquals( expected.getDimension(), parsed.getDimension() );
         
      }
   }// End Method

}// End Class
