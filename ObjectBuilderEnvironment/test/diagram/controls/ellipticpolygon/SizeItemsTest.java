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
import javafx.scene.control.Spinner;
import utility.TestCommon;

/**
 * {@link RotationItems} test.
 */
public class SizeItemsTest {

   private SelectionScenario scenario;
   private SizeItems systemUnderTest;
   
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
      
      systemUnderTest = new SizeItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_HORIZONTAL_RADIUS, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_VERTICAL_RADIUS, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_STROKE_WIDTH, scenario.diamond.strokeWidthProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      Assert.assertEquals( SelectionScenario.DIAMOND_TYPE, scenario.diamond.polygonTypeProperty().get() );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_X, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_Y, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_FILL_COLOUR, scenario.diamond.fillProperty().get() );
      Assert.assertEquals( SelectionScenario.DIAMOND_STROKE_COLOUR, scenario.diamond.strokeProperty().get() );
      Assert.assertEquals( SelectionScenario.DIAMOND_INVERSION, scenario.diamond.inversionProperty().get() );
      Assert.assertEquals( SelectionScenario.DIAMOND_FRACTAL, scenario.diamond.numberOfFractalsProperty().get() );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} changes horizontal radius.
    */
   @Test public void shouldMakePolygonHorizontalRadiusChange() {
      Spinner< Double > spinner = systemUnderTest.horizontalRadiusSpinner();
      final double radiusValue = 22.34987;
      Assert.assertNotEquals( radiusValue, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      spinner.getValueFactory().valueProperty().set( radiusValue );
      
      Assert.assertEquals( radiusValue, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes vertical radius.
    */
   @Test public void shouldMakePolygonVerticalRadiusChange() {
      Spinner< Double > spinner = systemUnderTest.verticalRadiusSpinner();
      final double radiusValue = 987.0999;
      Assert.assertNotEquals( radiusValue, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      spinner.getValueFactory().valueProperty().set( radiusValue );
      
      Assert.assertEquals( radiusValue, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes stroke width.
    */
   @Test public void shouldMakePolygonStrokeWidthChange() {
      Spinner< Double > spinner = systemUnderTest.strokeWidthSpinner();
      final double widthValue = 6.5;
      Assert.assertNotEquals( widthValue, scenario.diamond.strokeWidthProperty().get(), TestCommon.precision() );
      spinner.getValueFactory().valueProperty().set( widthValue );
      
      Assert.assertEquals( widthValue, scenario.diamond.strokeWidthProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
}//End Class
