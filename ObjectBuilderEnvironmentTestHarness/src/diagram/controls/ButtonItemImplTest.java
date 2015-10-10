/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

/**
 * {@link ButtonItemImpl} test.
 */
public class ButtonItemImplTest {
   
   /**
    * Method to initialise JavaFx for the test.
    */
   @BeforeClass public static void initialiseJavaFx(){
      new JFXPanel();
   }//End Method

   /**
    * Test that the item produces a {@link Button}.
    */
   @Test public void shouldProduceAButton() {
      ButtonItemImpl item = new ButtonItemImpl( "test", new Rectangle( 20, 20 ), null );
      Assert.assertNotNull( item.getController() );
      Assert.assertTrue( item.getController() instanceof Button );
   }//End Method
   
   /**
    * Test that the {@link Button} when pressed, triggers the action given.
    */
   @Test public void shouldRunAction() {
      final BooleanProperty success = new SimpleBooleanProperty( false );
      ButtonItemImpl item = new ButtonItemImpl( "test", new Rectangle( 20, 20 ), () -> success.set( true ) );
      Button button = ( Button )item.getController();
      button.getOnAction().handle( new ActionEvent() );
      Assert.assertTrue( success.get() );
   }//End Method

}//End Class
