/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import org.junit.Assert;
import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import utility.TestCommon;

/**
 * Test for {@link Layer}.
 */
public class LayerTest {

   /**
    * {@link Layer#layerNode(ObservableList, Node)} test.
    */
   @Test public void shouldLayerNode() {
      Layer layer = new Layer( -20 );
      
      ObservableList< Node > list = FXCollections.observableArrayList();
      Pane pane = new Pane();
      layer.layerNode( list, pane );
      
      Assert.assertTrue( list.contains( pane ) );
      Assert.assertEquals( -20, pane.getTranslateZ(), TestCommon.precision() );
   }//End Method

}//End Class
