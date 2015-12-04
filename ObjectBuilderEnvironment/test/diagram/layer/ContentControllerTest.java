/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import architecture.event.EventSystem;
import diagram.shapes.AddShapeEvent;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.toolbox.ContentEvents;
import graphics.JavaFxInitializer;

/**
 * Test for the {@link ContentController}.
 */
public class ContentControllerTest {

   /**
    * Initialise method to launch JavaFx.
    */
   @BeforeClass public static void initialiseJavaFx(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
   }//End Method
   
   /**
    * Test for adding a shape from event.
    * @throws InterruptedException 
    */
   @Test public void shouldTriggerAddShape() throws InterruptedException {
      Content content = Mockito.mock( Content.class );
      new ContentController( content );
      
      EventSystem.raiseEvent( ContentEvents.AddShape, new AddShapeEvent( null, 100, -50 ) );
      Thread.sleep( 1000 );
      Mockito.verify( content ).addShape( null, 100, -50 );
      Mockito.verifyNoMoreInteractions( content );
   }//End Method
   
   /**
    * Test for selecting a shape from event.
    */
   @Test public void shouldTriggerSelect() throws InterruptedException {
      Content content = Mockito.mock( Content.class );
      new ContentController( content );
      
      EllipticPolygon polygon = Mockito.mock( EllipticPolygon.class );
      EventSystem.raiseEvent( ContentEvents.SelectNode, polygon );
      Thread.sleep( 1000 );
      Mockito.verify( content ).select( polygon );
      Mockito.verifyNoMoreInteractions( content );
   }//End Method

   /**
    * Test for deselecting a shape from event.
    */
   @Test public void shouldTriggerDeselect() throws InterruptedException {
      Content content = Mockito.mock( Content.class );
      new ContentController( content );
      
      EllipticPolygon polygon = Mockito.mock( EllipticPolygon.class );
      EventSystem.raiseEvent( ContentEvents.DeselectNode, polygon );
      Thread.sleep( 1000 );
      Mockito.verify( content ).deselect( polygon );
      Mockito.verifyNoMoreInteractions( content );
   }//End Method

}//End Class
