/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.layer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import architecture.event.EventSystem;
import diagram.events.AddShapeEvent;
import diagram.events.SelectShapesEvent;
import diagram.events.SelectSingletonsEvent;
import diagram.selection.ShapesManager;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.toolbox.ContentEvents;
import graphics.JavaFxInitializer;
import model.singleton.Singleton;

/**
 * Test for the {@link ContentController}.
 */
public class ContentControllerTest {

   private Content content;
   private ShapesManager shapesManager;
   
   /**
    * Initialise method to launch JavaFx.
    */
   @BeforeClass public static void initialiseJavaFx(){
      JavaFxInitializer.startPlatform();
   }//End Method
   
   /**
    * Method to initialise the situation for testing.
    */
   @Before public void initaliseSituation(){
      content = Mockito.mock( Content.class );
      new ContentController( content );
      shapesManager = Mockito.mock( ShapesManager.class );
      Mockito.when( content.shapesManager() ).thenReturn( shapesManager );
   }//End Method
   
   /**
    * Test for adding a shape from event.
    * @throws InterruptedException 
    */
   @Test public void shouldTriggerAddShape() throws InterruptedException {
      EventSystem.raiseEvent( ContentEvents.AddShape, new AddShapeEvent( null, 100, -50 ) );
      Thread.sleep( 1000 );
      Mockito.verify( content ).addShape( null, 100, -50 );
      Mockito.verifyNoMoreInteractions( shapesManager );
   }//End Method
   
   /**
    * Test for selecting a shape from event.
    */
   @Test public void shouldTriggerSelect() throws InterruptedException {
      EllipticPolygon polygon = Mockito.mock( EllipticPolygon.class );
      EventSystem.raiseEvent( ContentEvents.SelectShapes, new SelectShapesEvent( polygon ) );
      Thread.sleep( 1000 );
      Mockito.verify( shapesManager ).select( polygon );
      Mockito.verifyNoMoreInteractions( shapesManager );
   }//End Method

   /**
    * Test for deselecting a shape from event.
    */
   @Test public void shouldTriggerDeselect() throws InterruptedException {
      EllipticPolygon polygon = Mockito.mock( EllipticPolygon.class );
      EventSystem.raiseEvent( ContentEvents.DeselectShapes, new SelectShapesEvent( polygon ) );
      Thread.sleep( 1000 );
      Mockito.verify( shapesManager ).deselect( polygon );
      Mockito.verifyNoMoreInteractions( shapesManager );
   }//End Method
   
   /**
    * Prove that the selection of a {@link Singleton} is triggered.
    */
   @Test public void shouldTriggerSingletonSelect() throws InterruptedException {
      Singleton singleton = Mockito.mock( Singleton.class );
      EventSystem.raiseEvent( ContentEvents.SelectSingletons, new SelectSingletonsEvent( singleton ) );
      Thread.sleep( 1000 );
      Mockito.verify( shapesManager ).select( singleton );
      Mockito.verifyNoMoreInteractions( shapesManager );
   }//End Method

   /**
    * Prove that the deselection of a {@link Singleton} is triggered.
    */
   @Test public void shouldTriggerSingletonDeselect() throws InterruptedException {
      Singleton singleton = Mockito.mock( Singleton.class );
      EventSystem.raiseEvent( ContentEvents.DeselectSingletons, new SelectSingletonsEvent( singleton ) );
      Thread.sleep( 1000 );
      Mockito.verify( shapesManager ).deselect( singleton );
      Mockito.verifyNoMoreInteractions( shapesManager );
   }//End Method

}//End Class
