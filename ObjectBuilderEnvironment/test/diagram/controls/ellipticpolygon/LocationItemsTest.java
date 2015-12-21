/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.controls.ellipticpolygon.composite.ButtonPad;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.collections.FXCollections;
import javafx.scene.control.Spinner;
import utility.TestCommon;

/**
 * {@link LocationItems} test.
 */
public class LocationItemsTest {

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
      
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( scenario.diamond ) );
      Mockito.when( scenario.shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      systemUnderTest = new LocationItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_X, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_CENTRE_Y, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( new ArrayList<>() );
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} changes centre X.
    */
   @Test public void shouldMakePolygonChangeCentreX() {
      Spinner< Double > spinner = systemUnderTest.centreXSpinner();
      final double value = -500.39;
      Assert.assertNotEquals( value, scenario.diamond.translateXProperty().get(), TestCommon.precision() );
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( value, scenario.diamond.translateXProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre Y.
    */
   @Test public void shouldMakePolygonChangeCentreY() {
      Spinner< Double > spinner = systemUnderTest.centreYSpinner();
      final double value = 84723.2342;
      Assert.assertNotEquals( value, scenario.diamond.translateYProperty().get(), TestCommon.precision() );
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( value, scenario.diamond.translateYProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
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
    * Method to assert that the movement has been witnessed.
    * @param xStep the expected step in the x axis.
    * @param yStep the expected step in the y axis.
    * @param buttonIndex the index of the {@link Button} in question.
    */
   private void assertMovement( double xStep, double yStep, int buttonIndex ){
      ButtonPad pad = systemUnderTest.movePad();
      double currentTranslateX = scenario.diamond.getTranslateX();
      double currentTranslateY = scenario.diamond.getTranslateY();
      pad.button( buttonIndex ).fire();
      currentTranslateX += xStep;
      currentTranslateY += yStep;
      Assert.assertEquals( currentTranslateX, scenario.diamond.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( currentTranslateY, scenario.diamond.getTranslateY(), TestCommon.precision() );
   }//End Method
   
}//End Class
