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

import diagram.selection.ShapeManagerSelectionControllerImpl;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import javafx.collections.FXCollections;
import javafx.scene.control.Slider;
import utility.TestCommon;

/**
 * {@link RotationItems} multiple selection test.
 */
public class RotationItemsMultipleTest {
   
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
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( 
               FXCollections.observableSet( scenario.diamond, scenario.triangle, scenario.pentagon ) );
      Mockito.when( scenario.shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      scenario.controller = new ShapeManagerSelectionControllerImpl( scenario.shapes );
      systemUnderTest = new RotationItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_ROTATE, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_ROTATE, scenario.triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_ROTATE, scenario.pentagon.rotateProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Rotation
      ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Rotation
      ) ); 
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Rotation
      ) ); 
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} rotates.
    */
   @Test public void shouldMakePolygonsRotate() {
      final double rotateValue = 93.291837;
      assertRotateProperty( rotateValue, rotateValue );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} rotates to the maximum only.
    */
   @Test public void shouldMakePolygonsRotateMax() {
      final double maxValue = 293.291837;
      assertRotateProperty( systemUnderTest.rotateSlider().getMax(), maxValue );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} rotates to the minimum only.
    */
   @Test public void shouldMakePolygonsRotateMin() {
      final double minValue = -300;
      assertRotateProperty( systemUnderTest.rotateSlider().getMin(), minValue );
   }//End Method
   
   /**
    * Method to assert that the rotation is changed correctly.
    * @param expected the expected rotation.
    * @param value the value to rotate to.
    */
   private void assertRotateProperty( double expected, double value ) {
      Slider slider = systemUnderTest.rotateSlider();
      slider.valueProperty().set( value );
      
      Assert.assertEquals( expected, scenario.diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.pentagon.rotateProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
}//End Class
