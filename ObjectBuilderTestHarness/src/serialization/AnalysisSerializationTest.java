/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package serialization;

import graphs.graph.Graph;
import graphs.graph.SerializableGraphTest;
import graphs.graph.XmlGraphImpl;
import gui.ObjectBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.singleton.Singleton;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import search.SearchSpace;
import search.SerializableSearchSpaceTest;
import search.XmlSearchSpaceImpl;
import architecture.serialization.SerializationSystem;

/**
 * {@link AnalysisSerializationTest} is responsible for test that an {@link XmlAnalysisWrapper} can
 * serialize a number of {@link Singleton}s in the {@link ObjectBuilder} system.
 */
public class AnalysisSerializationTest {

   @Rule public TemporaryFolder folder = new TemporaryFolder();

   /**
    * Method to test the serialization into and back out of a {@link File} using an
    * {@link XmlAnalysisWrapper}.
    */
   @Test public void collectionSerializationTest() throws IOException {
      List< SearchSpace > actualSearchOnlys = new ArrayList< SearchSpace >();
      actualSearchOnlys.add( new SearchSpace( "searchA" ) );
      actualSearchOnlys.add( new SearchSpace( "searchB" ) );
      actualSearchOnlys.add( new SearchSpace( "searchC" ) );
      
      List< Graph > actualGraphs = new ArrayList< Graph >();
      actualGraphs.add( new Graph( "graphA" ) );
      actualGraphs.add( new Graph( "graphB" ) );
      actualGraphs.add( new Graph( "graphC" ) );

      XmlAnalysisWrapper serializedCollection = new XmlAnalysisWrapper();
      serializedCollection.addAllSearchOnlys( actualSearchOnlys );
      serializedCollection.addAllGraphs( actualGraphs );
      
      File testFile = folder.newFile();
      SerializationSystem.saveToFile( 
               serializedCollection, 
               testFile, 
               XmlAnalysisWrapper.class, 
               XmlSearchSpaceImpl.class, 
               XmlGraphImpl.class 
      );
      
      XmlAnalysisWrapper parsedSystem = SerializationSystem.loadWrapperFromFile( 
               XmlAnalysisWrapper.class, 
               testFile, 
               XmlAnalysisWrapper.class, 
               XmlSearchSpaceImpl.class, 
               XmlGraphImpl.class 
      );
      Assert.assertNotNull( parsedSystem );
      
      List< SearchSpace > parsedSearchOnlys = parsedSystem.retrieveSearchOnlys();
      SerializableSearchSpaceTest.assertSearchOnlyLists( actualSearchOnlys, parsedSearchOnlys );

      List< Graph > parsedGraphs = parsedSystem.retrieveGraphs();
      SerializableGraphTest.assertGraphLists( actualGraphs, parsedGraphs );
   }// End Method

}// End Class