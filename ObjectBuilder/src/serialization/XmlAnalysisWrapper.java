/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package serialization;

import graphs.graph.Graph;
import graphs.graph.XmlGraphWrapper;
import gui.ObjectBuilder;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.singleton.Singleton;
import search.SearchSpace;
import search.XmlSearchSpaceWrapper;
import architecture.representation.SingletonContainer;
import architecture.representation.StructuralRepresentation;
import architecture.request.RequestSystem;

/**
 * The {@link XmlAnalysisWrapper} wraps the analysis objects in the {@link ObjectBuilder} system and its {@link Singleton}s
 * into a single piece of Xml.
 */
@XmlRootElement
public class XmlAnalysisWrapper implements SingletonContainer, StructuralRepresentation< Object >{

   @XmlElement private XmlSearchSpaceWrapper searchSpaces;
   @XmlElement private XmlGraphWrapper graphs;
   
   /**
    * Constructs a new {@link XmlAnalysisWrapper}.
    */
   public XmlAnalysisWrapper() {
      searchSpaces = new XmlSearchSpaceWrapper();
      graphs = new XmlGraphWrapper();
   }// End Constructor
   
   /**
    * Method to add all {@link SearchSpace}s given.
    * @param searchOnlys the {@link SearchSpace}s to add.
    */
   public void addAllSearchOnlys( List< SearchSpace > searchOnlys ) {
      searchOnlys.forEach( object -> this.searchSpaces.addUnwrapped( object ) );
   }// End Method
   
   /**
    * Method to add all {@link Graph}s given.
    * @param graphs the {@link Graph}s to add.
    */
   public void addAllGraphs( List< Graph > graphs ) {
      graphs.forEach( object -> this.graphs.addUnwrapped( object ) );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public Object makeStructure() {
      return null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void constructSingletons() {
      searchSpaces.constructSingletons();
      graphs.constructSingletons();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      searchSpaces.resolveSingletons();
      graphs.resolveSingletons();
   }// End Method
   
   /**
    * Method to retrieve the {@link SearchSpace}s stored in the {@link RequestSystem} from the parsing
    * of this object.
    * @return a {@link List} of {@link SearchSpace}s resolved with the {@link RequestSystem}.
    */
   public List< SearchSpace > retrieveSearchOnlys(){
      return searchSpaces.retrieveSingletons();
   }// End Method
   
   /**
    * Method to retrieve the {@link Graph}s stored in the {@link RequestSystem} from the parsing
    * of this object.
    * @return a {@link List} of {@link Graph}s resolved with the {@link RequestSystem}.
    */
   public List< Graph > retrieveGraphs(){
      return graphs.retrieveSingletons();
   }// End Method
   
}// End Class
