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
import diagram.selection.ShapeManagerSelectionControllerImpl;
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
public class NumberOfSidesItemsTest {
   
   private static final int TESTED_VALUE = 4;
   private static final PolygonType DEFAULT_TYPE = PolygonType.Regular;
   private static final int DEFAULT_ROTATE = 189;
   private static final int DEFAULT_FRACTAL = 1;
   private static final boolean DEFAULT_INVERSION = false;
   private static final int DEFAULT_VERTICAL_RADIUS = 564;
   private static final int DEFAULT_HORIZONTAL_RADIUS = 876;
   private static final int DEFAULT_CENTRE_Y = 203;
   private static final int DEFAULT_CENTRE_X = 101;
   private EllipticPolygon polygon;
   private ShapesManager shapes;
   private SelectionController controller;
   private NumberOfSidesItems systemUnderTest;
   
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
               new EllipticPolygonBuilder( DEFAULT_TYPE, TESTED_VALUE )
                  .centreXProperty( DEFAULT_CENTRE_X )
                  .centreYProperty( DEFAULT_CENTRE_Y )
                  .horizontalRadiusProperty( DEFAULT_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( DEFAULT_VERTICAL_RADIUS )
                  .inversionProperty( DEFAULT_INVERSION )
                  .numberOfFractals( DEFAULT_FRACTAL )
                  .rotateProperty( DEFAULT_ROTATE )
                  
      );
      Assert.assertEquals( TESTED_VALUE, polygon.numberOfSidesProperty().get() );
      
      shapes = Mockito.mock( ShapesManager.class );
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( polygon ) );
      Mockito.when( shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      controller = new ShapeManagerSelectionControllerImpl( shapes );
      systemUnderTest = new NumberOfSidesItems( controller );
      Assert.assertEquals( TESTED_VALUE, polygon.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      Assert.assertEquals( DEFAULT_TYPE, polygon.polygonTypeProperty().get() );
      Assert.assertEquals( DEFAULT_CENTRE_X, polygon.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_CENTRE_Y, polygon.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_HORIZONTAL_RADIUS, polygon.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_VERTICAL_RADIUS, polygon.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_ROTATE, polygon.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_INVERSION, polygon.inversionProperty().get() );
      Assert.assertEquals( DEFAULT_FRACTAL, polygon.numberOfFractalsProperty().get() );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can have the number of sides changed and nothing else.
    */
   @Test public void shouldMakePolygonsChangeSides() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
         button.fire();
         
         Assert.assertEquals( i, polygon.numberOfSidesProperty().get() );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method

   /**
    * Prove that numbers outside of the {@link Button} range are ignored.
    */
   @Test public void shouldIgnoreButtonOutsideOfRange() {
      Button button = systemUnderTest.sidesButton( 11 );
      Assert.assertNull( button );
      button = systemUnderTest.sidesButton( 2 );
      Assert.assertNull( button );
   }//End Method
   
   /**
    * Prove that the original {@link EllipticPolygon} does not change when a graphic is constructed.
    */
   @Test public void graphicShouldNotChangeAssociatedPolygons() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
         assertGraphicUpdates( button, i );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @param expectedType the expected {@link PolygonType} of the {@link Button}'s graphic.
    */
   private void assertGraphicUpdates( Button button, int numberOfSides ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      
      Assert.assertEquals( numberOfSides, graphicPolygon.numberOfSidesProperty().get() );
   }//End Method
   
}//End Class
