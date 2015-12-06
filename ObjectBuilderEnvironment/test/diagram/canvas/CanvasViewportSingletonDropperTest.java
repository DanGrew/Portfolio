/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import architecture.event.EventSystem;
import architecture.request.RequestSystem;
import diagram.events.AddShapeEvent;
import diagram.toolbox.ContentEvents;
import javafx.application.Platform;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import model.singleton.Singleton;
import test.model.TestObjects.TestSingletonImpl;
import utility.ThreadWaiter;
import utility.protectedclasses.ProtectedClasses;

/**
 * {@link CanvasViewportSingletonDropper} test.
 */
public class CanvasViewportSingletonDropperTest {
   
   private Singleton singleton;
   private Singleton singleton2;
   private List< AddShapeEvent > results;
   private CanvasViewportSingletonDropper systemUnderTest;
   private ThreadWaiter waiter;
   
   /**
    * Method to initialise the testing scenario between each test case.
    */
   @Before public void initialiseScenario(){
      RequestSystem.reset();
      singleton = new TestSingletonImpl( "anything" );
      RequestSystem.store( singleton );
      singleton2 = new TestSingletonImpl( "anything2" );
      RequestSystem.store( singleton2 );
      
      results = new ArrayList<>();
      Pane contentHolder = new Pane();
      systemUnderTest = new CanvasViewportSingletonDropper( contentHolder );
      waiter = new ThreadWaiter( 2000 );
      
      EventSystem.registerForEvent( ContentEvents.AddShape, ( event, source ) -> {
         Assert.assertTrue( source instanceof AddShapeEvent );
         results.add( ( AddShapeEvent )source );
         waiter.interrupt();
      } );
   }//End Method

   /**
    * Prove that a single {@link Singleton} can be dropped.
    */
   @Test public void shouldDropIndividualSingleton() {
      waiter.assertions( () -> {
         Assert.assertEquals( 1, results.size() );
         AddShapeEvent firstEvent = results.get( 0 );
         Assert.assertEquals( singleton, firstEvent.association );
      } );
      
      Dragboard dragboard = ProtectedClasses.DragBoard();
      Platform.runLater( () -> {
         ClipboardContent content = DragAndDrop.constructContent( Arrays.asList( singleton ) );
         dragboard.setContent( content );
         
         DragEvent event = new DragEvent( 
                  null, dragboard, 0, 0, 0, 0, null, null, null, null 
         );
      
         systemUnderTest.handle( event );
      } );
      waiter.waitForTrigger();
   }//End Method
   
   /**
    * Prove that multiple {@link Singleton}s can be dropped.
    */
   @Test public void shouldDropMultipleSingletons() {
      Dragboard dragboard = ProtectedClasses.DragBoard();
      waiter.assertions( () -> {
         Assert.assertEquals( 2, results.size() );
         Assert.assertEquals( singleton, results.get( 0 ).association );
         Assert.assertEquals( singleton2, results.get( 1 ).association );
      } );
      Platform.runLater( () -> {
         ClipboardContent content = DragAndDrop.constructContent( Arrays.asList( singleton, singleton2 ) );
         dragboard.setContent( content );
         
         DragEvent event = new DragEvent( 
                  null, dragboard, 0, 0, 0, 0, null, null, null, null 
         );
      
         systemUnderTest.handle( event );
      } );
      waiter.waitForTrigger();
   }//End Method
   
   /**
    * Prove that no events are raised when nothing is dropped.
    */
   @Test public void shouldRaiseNoEventsForNothingBeingDropped() {
      AddShapeEvent interruptionEvent = Mockito.mock( AddShapeEvent.class );
      waiter.assertions( () -> {
         Assert.assertEquals( 1, results.size() );
         Assert.assertEquals( interruptionEvent, results.get( 0 ) );
      } );
      
      Dragboard dragboard = ProtectedClasses.DragBoard();
      
      DragEvent event = new DragEvent( 
               null, dragboard, 0, 0, 0, 0, null, null, null, null 
      );
      
      Platform.runLater( () -> {
         systemUnderTest.handle( event );
         //event could have been raised, if not raise another to interrupt.
         EventSystem.raiseEvent( ContentEvents.AddShape, interruptionEvent );
      } );
      waiter.waitForTrigger();
   }//End Method

}//End Class
