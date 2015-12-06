/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.canvas;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import architecture.event.EventSystem;
import diagram.toolbox.ContentEvents;
import graphics.JavaFxInitializer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Pane;
import utility.TestCommon;

/**
 * Test for the {@link CanvasViewport}.
 */
public class CanvasViewportTest {

   /**
    * Initialise method to launch JavaFx.
    */
   @BeforeClass public static void initialiseJavaFx(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
   }//End Method
   
   /**
    * Test for the binding of the pref dimensions of the view and the content.
    */
   @Test public void shouldBindPrefDimensions() {
      Pane pane = new Pane();
      pane.setPrefWidth( 4532 );
      pane.setPrefHeight( 38981 );
      CanvasViewport viewport = new CanvasViewport( pane );
      
      Assert.assertEquals( pane.getPrefWidth(), viewport.getViewPrefWidth(), TestCommon.precision() );
      Assert.assertEquals( pane.getPrefHeight(), viewport.getViewPrefHeight(), TestCommon.precision() );
      
      pane.setPrefWidth( 567 );
      pane.setPrefHeight( -4567 );
      Assert.assertEquals( pane.getPrefWidth(), viewport.getViewPrefWidth(), TestCommon.precision() );
      Assert.assertEquals( pane.getPrefHeight(), viewport.getViewPrefHeight(), TestCommon.precision() );
   }//End Method
   
   /**
    * Test for panning the view.
    */
   @Test public void shouldPan() {
      Pane pane = new Pane();
      CanvasViewport viewport = new CanvasViewport( pane );
      
      final double panX = 239487;
      final double panY = -29485;
      viewport.panViewport( panX, panY );
      
      Assert.assertEquals( panX, viewport.getViewTranslationX(), TestCommon.precision() );
      Assert.assertEquals( panY, viewport.getViewTranslationY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Test for scaling the view.
    */
   @Test public void shouldScale() {
      Pane pane = new Pane();
      CanvasViewport viewport = new CanvasViewport( pane );
      
      final double scaleX = 0.34;
      final double scaleY = 1.456;
      viewport.zoomViewport( scaleX, scaleY );
      
      Assert.assertEquals( scaleX, viewport.getViewScaleX(), TestCommon.precision() );
      Assert.assertEquals( scaleY, viewport.getViewScaleY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Test for triggering the panning via gui events.
    */
   @Test public void shouldTriggerPan(){
      Pane pane = new Pane();
      CanvasViewport viewport = new CanvasViewport( pane );
      
      final double panX = 239487;
      final double panY = -29485;
      ScrollEvent event = new ScrollEvent( null, 0, 0, 0, 0, true, true, true, true, true, true, panX, panY, 0, 0, null, 0, null, 0, 0, null );
      viewport.getOnScroll().handle( event );
      
      Assert.assertEquals( panX, viewport.getViewTranslationX(), TestCommon.precision() );
      Assert.assertEquals( panY, viewport.getViewTranslationY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Test for triggering the scaling via gui events.
    */
   @Test public void shouldTriggerScale(){
      Pane pane = new Pane();
      CanvasViewport viewport = new CanvasViewport( pane );
      
      final double scale = 0.34;
      ZoomEvent event = new ZoomEvent( null, 0, 0, 0, 0, false, false, false, false, false, false, scale, 0, null );
      viewport.getOnZoom().handle( event );
      
      Assert.assertEquals( scale, viewport.getViewScaleX(), TestCommon.precision() );
      Assert.assertEquals( scale, viewport.getViewScaleY(), TestCommon.precision() );
   }//End Method
   
   /**
    * Test for receiving a drag drop event.
    */
   @Test public void shouldReceiveDragDrop(){
      Pane pane = new Pane();
      pane.setPrefWidth( 4532 );
      pane.setPrefHeight( 38981 );
      CanvasViewport viewport = new CanvasViewport( pane );
      Assert.assertTrue( viewport.getOnDragDropped() instanceof CanvasViewportSingletonDropper );
   }//End Method
   
   /**
    * Test for proving {@link ViewportInformation} is provided.
    */
   @Test public void shouldHaveViewportInformation(){
      Pane pane = new Pane();
      CanvasViewport viewport = new CanvasViewport( pane );
      
      boolean found = false;
      for ( Node node : viewport.getChildren() ) {
         if ( node instanceof ViewportInformation ) {
            found = true;
            break;
         }
      }
      
      Assert.assertTrue( found );
   }//End Method
   
   /**
    * Test that the content provided is held in the {@link CanvasViewport}.
    */
   @Test public void shouldContainContentInChild(){
      Pane pane = new Pane();
      CanvasViewport viewport = new CanvasViewport( pane );
      Node node = viewport.getChildren().get( 0 );
      Assert.assertNotNull( node );
      Assert.assertTrue( node instanceof Pane );
      Assert.assertEquals( ( ( Pane )node ).getChildren().get( 0 ), pane );
   }//End Method

}//End Class
