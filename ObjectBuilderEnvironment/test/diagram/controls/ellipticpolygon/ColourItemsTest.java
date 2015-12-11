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

import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.collections.FXCollections;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import utility.TestCommon;

/**
 * {@link ColourItems} test.
 */
public class ColourItemsTest {

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
      
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( scenario.diamond ) );
      Mockito.when( scenario.shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      systemUnderTest = new ColourItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_FILL_COLOUR, scenario.diamond.fillProperty().get() );
      Assert.assertEquals( SelectionScenario.DIAMOND_STROKE_COLOUR, scenario.diamond.strokeProperty().get() );
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
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} changes stroke.
    */
   @Test public void shouldMakePolygonsChangeStroke() {
      ColorPicker picker = systemUnderTest.strokeColourChooser();
      picker.valueProperty().set( Color.AQUAMARINE );
      
      Assert.assertEquals( Color.AQUAMARINE, scenario.diamond.strokeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} change fill.
    */
   @Test public void shouldMakePolygonsChangeFill() {
      ColorPicker picker = systemUnderTest.fillColourChooser();
      picker.valueProperty().set( Color.AQUAMARINE );
      
      Assert.assertEquals( Color.AQUAMARINE, scenario.diamond.fillProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
}//End Class
