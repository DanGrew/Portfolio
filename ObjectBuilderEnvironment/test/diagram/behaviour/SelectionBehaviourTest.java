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
import org.mockito.Mockito;

import architecture.event.EventSystem;
import diagram.shapes.CanvasShape;
import diagram.toolbox.ContentEvents;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Test for {@link SelectionBehaviour}.
 */
public class SelectionBehaviourTest {

   /**
    * Basic test to prove event is raised.
    */
   @Test public void shouldRaiseSelectionEvent() {
      SelectionBehaviour behaviour = new SelectionBehaviour();
      
      CanvasShape subject = Mockito.mock( CanvasShape.class );
      Pane pane = new Pane();
      behaviour.registerForSelectionBehaviour( pane, subject );
      
      BooleanProperty received = new SimpleBooleanProperty( false );
      EventSystem.registerForEvent( ContentEvents.SelectNode, ( event, source ) -> {
         received.set( true );
         Assert.assertEquals( subject, source );
      } );
      
      MouseEvent event = new MouseEvent( 
               MouseEvent.MOUSE_PRESSED, 
               0, 0, 0, 0, MouseButton.PRIMARY, 0, false, false, false, false, false, false, false, false, false, false, null
      );
      pane.fireEvent( event );
      
      Assert.assertTrue( received.get() );
   }//End Method
   
   /**
    * Basic test to prove event is raised.
    */
   @Test public void shouldRaiseDeselectionEvent() {
      SelectionBehaviour behaviour = new SelectionBehaviour();
      
      CanvasShape subject = Mockito.mock( CanvasShape.class );
      Pane pane = new Pane();
      behaviour.registerForSelectionBehaviour( pane, subject );
      
      BooleanProperty received = new SimpleBooleanProperty( false );
      EventSystem.registerForEvent( ContentEvents.DeselectNode, ( event, source ) -> {
         received.set( true );
         Assert.assertEquals( subject, source );
      } );
      
      MouseEvent event = new MouseEvent( 
               MouseEvent.MOUSE_PRESSED, 
               0, 0, 0, 0, MouseButton.SECONDARY, 0, false, false, false, false, false, false, false, false, false, false, null
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
