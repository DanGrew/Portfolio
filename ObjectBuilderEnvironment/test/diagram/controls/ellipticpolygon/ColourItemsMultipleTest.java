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
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.collections.FXCollections;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import utility.TestCommon;

/**
 * {@link ColourItems} multiple selection test.
 */
public class ColourItemsMultipleTest {
   
   private SelectionScenario scenario;
   private ColourItems systemUnderTest;
   
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
      systemUnderTest = new ColourItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_FILL_COLOUR, scenario.diamond.fillProperty().get() );
      Assert.assertEquals( SelectionScenario.DIAMOND_STROKE_COLOUR, scenario.diamond.strokeProperty().get() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_FILL_COLOUR, scenario.triangle.fillProperty().get() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_STROKE_COLOUR, scenario.triangle.strokeProperty().get() );
      Assert.assertEquals( SelectionScenario.PENTAGON_FILL_COLOUR, scenario.pentagon.fillProperty().get() );
      Assert.assertEquals( SelectionScenario.PENTAGON_STROKE_COLOUR, scenario.pentagon.strokeProperty().get() );
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
    * Prove that an {@link EllipticPolygon} changes stroke colour.
    */
   @Test public void shouldMakePolygonsChangeStroke() {
      ColorPicker picker = systemUnderTest.strokeColourChooser();
      picker.valueProperty().set( Color.AQUAMARINE );
      
      Assert.assertEquals( Color.AQUAMARINE, scenario.diamond.strokeProperty().get() );
      Assert.assertEquals( Color.AQUAMARINE, scenario.triangle.strokeProperty().get() );
      Assert.assertEquals( Color.AQUAMARINE, scenario.pentagon.strokeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes fill colour.
    */
   @Test public void shouldMakePolygonsChangeFill() {
      ColorPicker picker = systemUnderTest.fillColourChooser();
      picker.valueProperty().set( Color.AQUAMARINE );
      
      Assert.assertEquals( Color.AQUAMARINE, scenario.diamond.fillProperty().get() );
      Assert.assertEquals( Color.AQUAMARINE, scenario.triangle.fillProperty().get() );
      Assert.assertEquals( Color.AQUAMARINE, scenario.pentagon.fillProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
}//End Class
