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
    * Method to construct a {@link Node} that can be used to control the underlying property.
    * @return a {@link Node} ready to be inserted into a {@link Scene}, where each call constructs
    * a new.
    */
   public Node constructNodeController();

}//End Interface
