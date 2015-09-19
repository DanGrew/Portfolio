/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * The {@link Layer} is responsible for ordering {@link Node}s on the z axis providing
 * layers to the scene.
 */
class Layer {
   
   private Pane parent;
   private double depth;
   
   /**
    * Constructs a new {@link Layer}.
    * @param parent the {@link Pane} parent.
    * @param depth the depth of the {@link Layer} as a z coordinate.
    */
   Layer( Pane parent, double depth ) {
      this.parent = parent;
      this.depth = depth;
   }//End Constructor
   
   /**
    * Method to layer the given {@link Node}.
    * @param node the {@link Node} to layer.
    */
   void layerNode( Node node ){
      parent.getChildren().add( node );
      node.setTranslateZ( depth );
      node.toFront();
   }//End Method
   
   /**
    * Method to layer all {@link Node}s in the given {@link List}.
    * @param nodes the {@link List} of {@link Node}s.
    */
   void layerAllNodes( List< Node > nodes ) {
      nodes.forEach( node -> layerNode( node ) );
   }//End Method

}//End Class
