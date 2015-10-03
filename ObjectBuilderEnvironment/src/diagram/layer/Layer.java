/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * The {@link Layer} is responsible for ordering {@link Node}s on the z axis providing
 * layers to the scene.
 */
class Layer {
   
   private double depth;
   
   /**
    * Constructs a new {@link Layer}.
    * @param depth the depth of the {@link Layer} as a z coordinate.
    */
   Layer( double depth ) {
      this.depth = depth;
   }//End Constructor
   
   /**
    * Method to layer the given {@link Node}.
    * @param layeredNodes the {@link ObservableList} to add the {@link Node} to.
    * @param node the {@link Node} to layer.
    */
   void layerNode( ObservableList< Node > layeredNodes, Node node ){
      layeredNodes.add( node );
      node.setTranslateZ( depth );
   }//End Method
   
}//End Class
