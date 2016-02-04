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

import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import javafx.collections.FXCollections;
import javafx.scene.control.Slider;
import utility.TestCommon;

/**
 * {@link RotationItems} test.
 */
public class RotationItemsTest {

   private SelectionScenario scenario;
   private RotationItems systemUnderTest;
   
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
      
      systemUnderTest = new RotationItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_ROTATE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Rotation
      ) );   
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} rotates.
    */
   @Test public void shouldMakePolygonsRotate() {
      Slider slider = systemUnderTest.rotateSlider();
      final double rotateValue = 22.34987;
      Assert.assertNotEquals( rotateValue, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      slider.valueProperty().set( rotateValue );
      
      Assert.assertEquals( rotateValue, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
}//End Class
