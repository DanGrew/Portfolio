/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.controls.ellipticpolygon.composite.ButtonPad;
import diagram.selection.ShapeManagerSelectionControllerImpl;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import utility.TestCommon;

/**
 * {@link LocationItems} multiple selection test.
 */
public class LocationItemsMultipleTest {
   
   private SelectionScenario scenario;
   private LocationItems systemUnderTest;
   
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
      systemUnderTest = new LocationItems( scenario.controller );
      
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_X, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_Y, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      
      Assert.assertEquals( SelectionScenario.TRIANGLE_CENTRE_X, scenario.triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_CENTRE_Y, scenario.triangle.centreYProperty().get(), TestCommon.precision() );
      
      Assert.assertEquals( SelectionScenario.PENTAGON_CENTRE_X, scenario.pentagon.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_CENTRE_Y, scenario.pentagon.centreYProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList() );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList() );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList() );
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} changes centre X.
    */
   @Test public void shouldMakePolygonsChangeHorizontalRadius() {
      final double value = 3495.0982;
      assertCentreXProperty( value, value );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre Y.
    */
   @Test public void shouldMakePolygonsChangeVerticalRadius() {
      final double value = 3495.0982;
      assertCentreYProperty( value, value );
   }//End Method
   
   /**
    * Prove polygons are moved with {@link ButtonPad}.
    */
   @Test public void shouldMovePolygonUsingTranslate(){
      assertMovement( -LocationItems.STEP, -LocationItems.STEP, 0 );
      assertMovement( 0, -LocationItems.STEP, 1 );
      assertMovement( LocationItems.STEP, -LocationItems.STEP, 2 );
      assertMovement( -LocationItems.STEP, 0, 3 );
      assertMovement( 0, 0, 4 );
      assertMovement( LocationItems.STEP, 0, 5 );
      assertMovement( -LocationItems.STEP, LocationItems.STEP, 6 );
      assertMovement( 0, LocationItems.STEP, 7 );
      assertMovement( LocationItems.STEP, LocationItems.STEP, 8 );
   }//End Method
   
   /**
    * Method to assert that the centre X is changed correctly.
    * @param expected the expected centre X.
    * @param value the value to change to.
    */
   private void assertCentreXProperty( double expected, double value ) {
      Spinner< Double > spinner = systemUnderTest.centreXSpinner();
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( expected, scenario.diamond.translateXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.triangle.translateXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.pentagon.translateXProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Method to assert that the centre Y is changed correctly.
    * @param expected the expected centre Y.
    * @param value the value to change to.
    */
   private void assertCentreYProperty( double expected, double value ) {
      Spinner< Double > spinner = systemUnderTest.centreYSpinner();
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( expected, scenario.diamond.translateYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.triangle.translateYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.pentagon.translateYProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Method to assert that the movement has been witnessed.
    * @param xStep the expected step in the x axis.
    * @param yStep the expected step in the y axis.
    * @param buttonIndex the index of the {@link Button} in question.
    */
   private void assertMovement( double xStep, double yStep, int buttonIndex ){
      ButtonPad pad = systemUnderTest.movePad();
      
      double currentDiamondTranslateX = scenario.diamond.getTranslateX();
      double currentDiamondTranslateY = scenario.diamond.getTranslateY();
      double currentTriangleTranslateX = scenario.triangle.getTranslateX();
      double currentTriangleTranslateY = scenario.triangle.getTranslateY();
      double currentPentagonTranslateX = scenario.pentagon.getTranslateX();
      double currentPentagonTranslateY = scenario.pentagon.getTranslateY();
      
      pad.button( buttonIndex ).fire();
      currentDiamondTranslateX += xStep;
      currentDiamondTranslateY += yStep;
      currentTriangleTranslateX += xStep;
      currentTriangleTranslateY += yStep;
      currentPentagonTranslateX += xStep;
      currentPentagonTranslateY += yStep;
      
      Assert.assertEquals( currentDiamondTranslateX, scenario.diamond.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( currentDiamondTranslateY, scenario.diamond.getTranslateY(), TestCommon.precision() );
      Assert.assertEquals( currentTriangleTranslateX, scenario.triangle.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( currentTriangleTranslateY, scenario.triangle.getTranslateY(), TestCommon.precision() );
      Assert.assertEquals( currentPentagonTranslateX, scenario.pentagon.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( currentPentagonTranslateY, scenario.pentagon.getTranslateY(), TestCommon.precision() );
   }//End Method
   
}//End Class
