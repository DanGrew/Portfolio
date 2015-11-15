/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.behaviour;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import architecture.event.EventSystem;
import diagram.toolbox.ContentEvents;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Test for {@link SelectionBehaviour}.
 */
public class SelectionBehaviourTest {

   /**
    * Basic test to prove event is raised.
    */
   @Test public void shouldRaiseEvent() {
      SelectionBehaviour behaviour = new SelectionBehaviour();
      
      Pane pane = new Pane();
      behaviour.registerForSelectionBehaviour( pane );
      
      BooleanProperty received = new SimpleBooleanProperty( false );
      EventSystem.registerForEvent( ContentEvents.SelectNode, ( event, source ) -> {
         received.set( true );
         Assert.assertEquals( pane, source );
      } );
      
      MouseEvent event = new MouseEvent( 
               MouseEvent.MOUSE_PRESSED, 
               0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null
      );
      pane.fireEvent( event );
      
      Assert.assertTrue( received.get() );
   }//End Method
   
   /**
    * Method to shutdown the {@link EventSystem} after the test so that it does not interfer
    * with other tests.
    */
   @AfterClass public static void shutdown(){
      EventSystem.reset();
   }//End Method

}//End Class
