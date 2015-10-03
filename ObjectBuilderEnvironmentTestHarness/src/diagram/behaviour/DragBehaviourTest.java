/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.behaviour;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import utility.TestCommon;

/**
 * Test for the {@link DragBehaviour}.
 */
public class DragBehaviourTest {

   /**
    * Test for simulating a drag operation and checking the translation calculated.
    */
   @Test public void shouldBasicTranslate() {
      DragBehaviour behaviour = new DragBehaviour();
      
      Pane pane = new Pane();
      Rectangle rectangle = new Rectangle( 20, 50 );
      pane.getChildren().add( rectangle );
      
      behaviour.registerForDragOperations( rectangle );
      
      MouseEvent pressed = new MouseEvent(
               rectangle, null, null, 0, 0, 0, 0, null, 0, 
               false, false, false, false, false, false, false, false, false, false, null
      );
      rectangle.getOnMousePressed().handle( pressed );
      
      MouseEvent dragged = new MouseEvent(
               rectangle, null, null, 100, 300, 0, 0, null, 0, 
               false, false, false, false, false, false, false, false, false, false, null
      );
      rectangle.getOnMouseDragged().handle( dragged );
      
      Assert.assertEquals( 100, rectangle.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 300, rectangle.getTranslateY(), TestCommon.precision() );
   }//End Method

}//End Class
