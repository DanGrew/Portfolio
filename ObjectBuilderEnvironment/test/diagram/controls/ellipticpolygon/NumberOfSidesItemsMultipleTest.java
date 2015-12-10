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

import diagram.selection.ShapeManagerSelectionControllerImpl;
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
public class NumberOfSidesItemsMultipleTest {
   
   private SelectionScenario scenario;
   private NumberOfSidesItems systemUnderTest;
   
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
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( 
               FXCollections.observableSet( scenario.diamond, scenario.triangle, scenario.pentagon ) );
      Mockito.when( scenario.shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      scenario.controller = new ShapeManagerSelectionControllerImpl( scenario.shapes );
      systemUnderTest = new NumberOfSidesItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_TYPE, scenario.diamond.polygonTypeProperty().get() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_TYPE, scenario.triangle.polygonTypeProperty().get() );
      Assert.assertEquals( SelectionScenario.PENTAGON_TYPE, scenario.pentagon.polygonTypeProperty().get() );
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
      Assert.assertEquals( SelectionScenario.DIAMOND_FRACTAL, scenario.diamond.numberOfFractalsProperty().get() );
      
      Assert.assertEquals( SelectionScenario.TRIANGLE_TYPE, scenario.triangle.polygonTypeProperty().get() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_CENTRE_X, scenario.triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_CENTRE_Y, scenario.triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_HORIZONTAL_RADIUS, scenario.triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_VERTICAL_RADIUS, scenario.triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_ROTATE, scenario.triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_INVERSION, scenario.triangle.inversionProperty().get() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_FRACTAL, scenario.triangle.numberOfFractalsProperty().get() ); 
      
      Assert.assertEquals( SelectionScenario.PENTAGON_TYPE, scenario.pentagon.polygonTypeProperty().get() );
      Assert.assertEquals( SelectionScenario.PENTAGON_CENTRE_X, scenario.pentagon.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_CENTRE_Y, scenario.pentagon.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_HORIZONTAL_RADIUS, scenario.pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_VERTICAL_RADIUS, scenario.pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_ROTATE, scenario.pentagon.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_INVERSION, scenario.pentagon.inversionProperty().get() );
      Assert.assertEquals( SelectionScenario.PENTAGON_FRACTAL, scenario.pentagon.numberOfFractalsProperty().get() ); 
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Regular}.
    */
   @Test public void shouldMakePolygonChangeSides() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
         button.fire();
         
         Assert.assertEquals( i, scenario.diamond.numberOfSidesProperty().get() );
         Assert.assertEquals( i, scenario.triangle.numberOfSidesProperty().get() );
         Assert.assertEquals( i, scenario.pentagon.numberOfSidesProperty().get() );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Regular} updates.
    */
   @Test public void graphicShouldMimicPolygonWithDifferentSides() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
         EllipticPolygon graphicPolygon = extractGraphicPolygon( button );
         assertGraphicDisplaysProperty( i, graphicPolygon );
      }
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @return the {@link EllipticPolygon} in the graphic of the {@link Button}.
    */
   private EllipticPolygon extractGraphicPolygon( Button button ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      return graphicPolygon;
   }//End Method
   
   /**
    * Method to prove that the associated graphic is not disturbed by the shapes it represents.
    */
   private void assertGraphicDisplaysProperty( int expectedSides, EllipticPolygon graphicPolygon ){
      Assert.assertEquals( expectedSides, graphicPolygon.numberOfSidesProperty().get() );
   }//End Method

}//End Class
