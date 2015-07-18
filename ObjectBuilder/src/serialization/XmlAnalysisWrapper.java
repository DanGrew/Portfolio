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
import search.SearchOnly;
import search.XmlSearchOnlyWrapper;
import architecture.representation.SingletonContainer;
import architecture.representation.StructuralRepresentation;
import architecture.request.RequestSystem;

/**
 * The {@link XmlAnalysisWrapper} wraps the analysis objects in the {@link ObjectBuilder} system and its {@link Singleton}s
 * into a single piece of Xml.
 */
@XmlRootElement
public class XmlAnalysisWrapper implements SingletonContainer, StructuralRepresentation< Object >{

   @XmlElement private XmlSearchOnlyWrapper searchOnlys;
   @XmlElement private XmlGraphWrapper graphs;
   
   /**
    * Constructs a new {@link XmlAnalysisWrapper}.
    */
   public XmlAnalysisWrapper() {
      searchOnlys = new XmlSearchOnlyWrapper();
      graphs = new XmlGraphWrapper();
   }// End Constructor
   
   /**
    * Method to add all {@link SearchOnly}s given.
    * @param searchOnlys the {@link SearchOnly}s to add.
    */
   public void addAllSearchOnlys( List< SearchOnly > searchOnlys ) {
      searchOnlys.forEach( object -> this.searchOnlys.addUnwrapped( object ) );
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
      searchOnlys.constructSingletons();
      graphs.constructSingletons();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public void resolveSingletons() {
      searchOnlys.resolveSingletons();
      graphs.resolveSingletons();
   }// End Method
   
   /**
    * Method to retrieve the {@link SearchOnly}s stored in the {@link RequestSystem} from the parsing
    * of this object.
    * @return a {@link List} of {@link SearchOnly}s resolved with the {@link RequestSystem}.
    */
   public List< SearchOnly > retrieveSearchOnlys(){
      return searchOnlys.retrieveSingletons();
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
