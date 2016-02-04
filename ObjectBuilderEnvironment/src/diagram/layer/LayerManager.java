/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * The {@link LayerManager} is responsible for layering the {@link Node}s in a {@link Scene} according
 * to the z translation of the {@link Node}s.
 */
public class LayerManager {
   
   private static final NodeSorter NODE_SORTER = new NodeSorter();
   
   private Map< Layers, Layer > layerMapping;
   private ObservableList< Node > layeredObjects;
   
   /**
    * Private {@link Comparator} used to order the elements in the {@link Scene}.
    */
   private static class NodeSorter implements Comparator< Node > {

      /**
       * {@inheritDoc}
       */
      @Override public int compare( Node o1, Node o2 ) {
         return Double.compare( o1.getTranslateZ(), o2.getTranslateZ() );
      }//End Method
      
   }//End Class
   
   /**
    * Constructs a new {@link LayerManager}.
    * @param layeredObjects the {@link ObservableList} to order and layer/
    */
   public LayerManager( ObservableList< Node > layeredObjects ) {
      this.layeredObjects = layeredObjects;
      layerMapping = new HashMap<>();
      layerMapping.put( Layers.Content, new Layer( -1.0 ) );
      layerMapping.put( Layers.Selection, new Layer( 0.0 ) );
      layerMapping.put( Layers.Control, new Layer( 1.0 ) );
   }//End Constructor
   
   /**
    * Method to add the given {@link Node}s to the given {@link Layers}.
    * @param layer the {@link Layers} to add to.
    * @param nodes the {@link Node}s to add.
    */
   public void addNodes( Layers layer, Node... nodes ) {
      addNodes( layer, Arrays.asList( nodes ) );
   }//End Method

   /**
    * Method to add the given {@link Node}s to the given {@link Layers}.
    * @param layer the {@link Layers} to add to.
    * @param nodes the {@link Node}s to add.
    */
   public void addNodes( Layers layer, Collection< Node > nodes ) {
      nodes.forEach( node -> {
         layerMapping.get( layer ).layerNode( layeredObjects, node );
      } );
      List< Node > sorted = new ArrayList<>( layeredObjects );
      sorted.sort( NODE_SORTER );
      layeredObjects.clear();
      layeredObjects.addAll( sorted );
   }//End Method

   /**
    * Method to remove the given {@link Node} from the {@link LayerManager}.
    * @param node the {@link Node} to remove.
    */
   public void remove( Node node ) {
      layeredObjects.remove( node );
   }//End Method
   
   /**
    * Method to remove all the given {@link Node}s from the {@link LayerManager}.
    * @param nodes the {@link Node}s to remove.
    */
   public void remove( Collection< Node > nodes ) {
      layeredObjects.removeAll( nodes );
   }//End Method
   
}//End Class
