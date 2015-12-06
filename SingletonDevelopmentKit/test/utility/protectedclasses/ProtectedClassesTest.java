/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility.protectedclasses;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.input.Dragboard;

/**
 * {@link ProtectedClasses} test.
 */
public class ProtectedClassesTest {

   /**
    * Prove that a {@link Dragboard} can be generated and returned immediately.
    */
   @Test public void shouldGenerateDragboard() {
      Dragboard generated = ProtectedClasses.DragBoard();
      Assert.assertNotNull( generated );
   }//End Method

}//End Class
