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

import diagram.canvas.ViewportInformation.LabelUpdater;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Test for the {@link ViewportInformation}.
 */
public class ViewportInformationTest {

   /**
    * Initialise method to launch JavaFx.
    */
   @BeforeClass public static void initialiseJavaFx(){
      new JFXPanel();
   }//End Method
   
   /**
    * Test for {@link LabelUpdater}.
    */
   @Test public void shouldUpdateLabel() {
      final String initial = "anything";
      Label label = new Label( initial );
      String format = "test( %s, %s )";
      DoubleProperty xValue = new SimpleDoubleProperty();
      DoubleProperty yValue = new SimpleDoubleProperty();
      LabelUpdater updater = new LabelUpdater( label, format, xValue, yValue );
      
      Assert.assertEquals( initial, label.getText() );
      
      xValue.set( 100 );
      updater.changed( null, null, null );
      Assert.assertEquals( 
         String.format( 
               format, 
               LabelUpdater.COORDINATE_FORMAT.format( xValue.get() ), 
               LabelUpdater.COORDINATE_FORMAT.format( yValue.get() ) 
         ), 
         label.getText() 
      );
      
      xValue.set( -938472.2039487 );
      updater.changed( null, null, null );
      Assert.assertEquals( 
         String.format( 
               format, 
               LabelUpdater.COORDINATE_FORMAT.format( xValue.get() ), 
               LabelUpdater.COORDINATE_FORMAT.format( yValue.get() ) 
         ), 
         label.getText() 
      );
   }//End Method
   
   /**
    * Test for {@link ViewportInformation#getCanvasLocation()}.
    */
   @Test public void shouldUpdateCanvasLocation(){
      Pane scalablePane = new Pane();
      Pane viewport = new Pane();
      ViewportInformation information = new ViewportInformation( scalablePane, viewport );
      
      Assert.assertEquals( 
               String.format( ViewportInformation.CANVAS_LOCATION_FORMAT, 0, 0 ), 
               information.getCanvasLocation() 
      );
      
      scalablePane.setTranslateX( 100 );
      Assert.assertEquals( 
               String.format( ViewportInformation.CANVAS_LOCATION_FORMAT, 100, 0 ), 
               information.getCanvasLocation() 
      );
      scalablePane.setTranslateY( -2938.398 );
      Assert.assertEquals( 
               String.format( ViewportInformation.CANVAS_LOCATION_FORMAT, 100, -2938.4 ), 
               information.getCanvasLocation() 
      );
   }//End Method
   
   /**
    * Test for {@link ViewportInformation#getCanvasScale()}.
    */
   @Test public void shouldUpdateCanvasScale(){
      Pane scalablePane = new Pane();
      Pane viewport = new Pane();
      ViewportInformation information = new ViewportInformation( scalablePane, viewport );
      
      Assert.assertEquals( 
               String.format( ViewportInformation.CANVAS_SCALE_FORMAT, 1, 1 ), 
               information.getCanvasScale() 
      );
      
      scalablePane.setScaleX( 0.345 );
      Assert.assertEquals( 
               String.format( ViewportInformation.CANVAS_SCALE_FORMAT, 0.34, 1 ), 
               information.getCanvasScale() 
      );
      scalablePane.setScaleY( 1.348 );
      Assert.assertEquals( 
               String.format( ViewportInformation.CANVAS_SCALE_FORMAT, 0.34, 1.35 ), 
               information.getCanvasScale() 
      );
   }//End Method
   
   /**
    * Test for {@link ViewportInformation#getMouseLocation()}.
    */
   @Test public void shouldUpdateMouseLocation(){
      Pane scalablePane = new Pane();
      Pane viewport = new Pane();
      ViewportInformation information = new ViewportInformation( scalablePane, viewport );
      
      MouseEvent event = new MouseEvent( 
               MouseEvent.MOUSE_MOVED, 34.2334, -2398.348, 0, 0, null, 0, 
               false, false, false, false, false, false, false, false, false, false, null 
      );
      viewport.fireEvent( event );
      Assert.assertEquals( 
               String.format( ViewportInformation.MOUSE_LOCATION_FORMAT, 34.23, -2398.35 ), 
               information.getMouseLocation() 
      );
   }//End Method
   
   /**
    * Test that the {@link ViewportInformation} has at least 3 {@link Label}s to prove it has content tested
    * above.
    */
   @Test public void shouldHaveThreeLabelsForData(){
      Pane scalablePane = new Pane();
      Pane viewport = new Pane();
      ViewportInformation information = new ViewportInformation( scalablePane, viewport );
      Assert.assertTrue( information.getChildren().get( 0 ) instanceof Label );
      Assert.assertTrue( information.getChildren().get( 1 ) instanceof Label );
      Assert.assertTrue( information.getChildren().get( 2 ) instanceof Label );
   }//End Method

}//End Class
