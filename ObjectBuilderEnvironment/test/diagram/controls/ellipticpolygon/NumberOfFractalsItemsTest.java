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

import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import utility.TestCommon;

/**
 * {@link PolygonTypeItems} test.
 */
public class NumberOfFractalsItemsTest {

   private SelectionScenario scenario;
   private NumberOfFractalsItems systemUnderTest;
   
   /**
    * Method to initialise the environment for testing.
    */
   @BeforeClass public static void initialiseScenario(){
      SelectionScenario.initialiseScenario();
   }//End Method
   
   /**
    * Method to initialise the system under test.
    */
   @Before public void initialiseSystemUnderTest(){
      scenario = new SelectionScenario();
      
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( scenario.diamond ) );
      Mockito.when( scenario.shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      systemUnderTest = new NumberOfFractalsItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_FRACTAL, scenario.diamond.numberOfFractalsProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      Assert.assertEquals( SelectionScenario.DIAMOND_TYPE, scenario.diamond.polygonTypeProperty().get() );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_X, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_Y, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_HORIZONTAL_RADIUS, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_VERTICAL_RADIUS, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_ROTATE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_INVERSION, scenario.diamond.inversionProperty().get() );
      Assert.assertEquals( SelectionScenario.DIAMOND_NUMBER_OF_SIDES, scenario.diamond.numberOfSidesProperty().get() );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can have the number of fractals changed.
    */
   @Test public void shouldMakePolygonsChangeSides() {
      for ( int i = 0; i < 4; i++ ) {
         Button button = systemUnderTest.fractalsButton( i );
         button.fire();
         
         Assert.assertEquals( i, scenario.diamond.numberOfFractalsProperty().get() );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method

   /**
    * Prove that numbers outside of the {@link Button} range are ignored.
    */
   @Test public void shouldIgnoreButtonOutsideOfRange() {
      Button button = systemUnderTest.fractalsButton( 5 );
      Assert.assertNull( button );
      button = systemUnderTest.fractalsButton( -1 );
      Assert.assertNull( button );
   }//End Method
   
   /**
    * Prove that the original {@link EllipticPolygon} does not change when a graphic is constructed.
    */
   @Test public void graphicShouldNotChangeAssociatedPolygons() {
      for ( int i = 0; i < 4; i++ ) {
         Button button = systemUnderTest.fractalsButton( i );
         assertGraphicUpdates( button, i );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @param numberOfFractals the expected number of fractals.
    */
   private void assertGraphicUpdates( Button button, int numberOfFractals ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      
      Assert.assertEquals( PolygonType.Fractal, graphicPolygon.polygonTypeProperty().get() );
      Assert.assertEquals( numberOfFractals, graphicPolygon.numberOfFractalsProperty().get() );
   }//End Method
   
}//End Class
