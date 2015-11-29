/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package launch;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import diagram.canvas.DiagramCanvas;
import graphics.JavaFxInitializer;
import gui.CommandPrompt;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import outline.SystemOutline;

/**
 * {@link Perspectives} test.
 */
public class PerspectivesTest {

   /**
    * Method to setup JavaFx for the test.
    */
   @BeforeClass public static void setupJfx(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
   }//End Method
   
   /**
    * Prove that the container is initially put to spreadsheets.
    */
   @Test public void shouldInitiallyBeSpreadsheet() {
      BorderPane container = new BorderPane();
      Assert.assertNull( container.getCenter() );
      new Perspectives( container );
      Assert.assertTrue( container.getCenter() instanceof SystemOutline );
      Assert.assertNull( container.getLeft() );
      Assert.assertNull( container.getRight() );
      Assert.assertNull( container.getTop() );
      Assert.assertTrue( container.getBottom() instanceof CommandPrompt );
   }//End Method

   /**
    * Prove that the contents are swapped when the {@link RadioButton}s are used.
    */
   @Test public void shouldSwapContents() {
      BorderPane container = new BorderPane();
      Assert.assertNull( container.getCenter() );
      Perspectives perspectives = new Perspectives( container );
      Assert.assertTrue( container.getCenter() instanceof SystemOutline );
      
      perspectives.diagrams().setSelected( true );
      Assert.assertTrue( container.getCenter() instanceof DiagramCanvas );
      Assert.assertTrue( container.getLeft() instanceof SystemOutline );
      Assert.assertNull( container.getRight() );
      Assert.assertNull( container.getTop() );
      Assert.assertNull( container.getBottom() );
      
      perspectives.spreadsheet().setSelected( true );
      Assert.assertTrue( container.getCenter() instanceof SystemOutline );
      Assert.assertNull( container.getLeft() );
      Assert.assertNull( container.getRight() );
      Assert.assertNull( container.getTop() );
      Assert.assertTrue( container.getBottom() instanceof CommandPrompt );
   }//End Method
   
   /**
    * Prove that the {@link RadioButton}s cannot be on at the same time.
    */
   @Test public void buttonsShouldNotBeOnAtSameTime() {
      BorderPane container = new BorderPane();
      Perspectives perspectives = new Perspectives( container );
      Assert.assertFalse( perspectives.diagrams().isSelected() );
      Assert.assertTrue( perspectives.spreadsheet().isSelected() );
      
      perspectives.diagrams().setSelected( true );
      Assert.assertTrue( perspectives.diagrams().isSelected() );
      Assert.assertFalse( perspectives.spreadsheet().isSelected() );
      
      perspectives.spreadsheet().setSelected( true );
      Assert.assertFalse( perspectives.diagrams().isSelected() );
      Assert.assertTrue( perspectives.spreadsheet().isSelected() );
   }//End Method
   
}//End Class
