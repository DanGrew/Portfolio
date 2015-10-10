/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 * Interface describing an item that can be placed in a {@link GridItemSelection} {@link GridPane}.
 */
public interface GridItem {

   /**
    * Method to get the {@link Node} that can be used to control the underlying property.
    * @return a {@link Node} ready to be inserted into a {@link Scene}.
    */
   public Node getWrapper();
   
   /**
    * Method to get the {@link Node} that is the underlying controller of the associated 
    * property.
    * @return the {@link Node} controlling the property.
    */
   public Node getController();
   
}//End Interface
