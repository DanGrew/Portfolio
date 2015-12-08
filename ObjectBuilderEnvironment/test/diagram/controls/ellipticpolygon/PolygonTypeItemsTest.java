/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.selection.SelectionController;
import diagram.selection.ShapesManagerSelectionControllerImpl;
import diagram.selection.ShapesManager;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import graphics.JavaFxInitializer;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import utility.TestCommon;

/**
 * {@link PolygonTypeItems} test.
 */
public class PolygonTypeItemsTest {
   
   private static final int DEFAULT_ROTATE = 189;
   private static final int DEFAULT_NUMBER_OF_SIDES = 4;
   private static final int DEFAULT_FRACTAL = 1;
   private static final boolean DEFAULT_INVERSION = false;
   private static final int DEFAULT_VERTICAL_RADIUS = 564;
   private static final int DEFAULT_HORIZONTAL_RADIUS = 876;
   private static final int DEFAULT_CENTRE_Y = 203;
   private static final int DEFAULT_CENTRE_X = 101;
   private EllipticPolygon polygon;
   private ShapesManager shapes;
   private SelectionController controller;
   private PolygonTypeItems systemUnderTest;
   
   /**
    * Method to initialise the environment for testing.
    */
   @BeforeClass public static void initialiseScenario(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
   }//End Method
   
   /**
    * Method to initialise the system under test.
    */
   @Before public void initialiseSystemUnderTest(){
      polygon = new EllipticPolygon( 
               new EllipticPolygonBuilder( PolygonType.Starred, DEFAULT_NUMBER_OF_SIDES )
                  .centreXProperty( DEFAULT_CENTRE_X )
                  .centreYProperty( DEFAULT_CENTRE_Y )
                  .horizontalRadiusProperty( DEFAULT_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( DEFAULT_VERTICAL_RADIUS )
                  .inversionProperty( DEFAULT_INVERSION )
                  .numberOfFractals( DEFAULT_FRACTAL )
                  .rotateProperty( DEFAULT_ROTATE )
                  
      );
      Assert.assertEquals( PolygonType.Starred, polygon.polygonTypeProperty().get() );
      
      shapes = Mockito.mock( ShapesManager.class );
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( polygon ) );
      Mockito.when( shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      controller = new ShapesManagerSelectionControllerImpl( shapes );
      systemUnderTest = new PolygonTypeItems( controller );
      Assert.assertEquals( PolygonType.Starred, polygon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      Assert.assertEquals( DEFAULT_NUMBER_OF_SIDES, polygon.numberOfSidesProperty().get() );
      Assert.assertEquals( DEFAULT_CENTRE_X, polygon.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_CENTRE_Y, polygon.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_HORIZONTAL_RADIUS, polygon.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_VERTICAL_RADIUS, polygon.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_ROTATE, polygon.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_INVERSION, polygon.inversionProperty().get() );
      Assert.assertEquals( DEFAULT_FRACTAL, polygon.numberOfFractalsProperty().get() );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Regular}.
    */
   @Test public void shouldMakePolygonRegular() {
      Button button = systemUnderTest.regularButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Regular, polygon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Starred}.
    */
   @Test public void shouldMakePolygonStarred() {
      polygon.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, polygon.polygonTypeProperty().get() );
      
      Button button = systemUnderTest.starredButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Starred, polygon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Fractal}.
    */
   @Test public void shouldMakePolygonFractal() {
      Button button = systemUnderTest.fractalButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Fractal, polygon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Regular} updates.
    */
   @Test public void regularGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.regularButton();
      assertGraphicUpdates( button, PolygonType.Regular );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Starred} updates.
    */
   @Test public void starredGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.starredButton();
      assertGraphicUpdates( button, PolygonType.Starred );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Fractal} updates.
    */
   @Test public void fractalGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.fractalButton();
      assertGraphicUpdates( button, PolygonType.Fractal );
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @param expectedType the expected {@link PolygonType} of the {@link Button}'s graphic.
    */
   private void assertGraphicUpdates( Button button, PolygonType expectedType ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      
      Assert.assertEquals( expectedType, graphicPolygon.polygonTypeProperty().get() );
   }//End Method
   
}//End Class
