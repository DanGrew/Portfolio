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
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.CentreX,
               EllipticPolygonProperties.CentreY
      ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.CentreX,
               EllipticPolygonProperties.CentreY
      ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.CentreX,
               EllipticPolygonProperties.CentreY
      ) );
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
    * Method to assert that the centre X is changed correctly.
    * @param expected the expected centre X.
    * @param value the value to change to.
    */
   private void assertCentreXProperty( double expected, double value ) {
      Spinner< Double > spinner = systemUnderTest.centreXSpinner();
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( expected, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.pentagon.centreXProperty().get(), TestCommon.precision() );
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
      
      Assert.assertEquals( expected, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.pentagon.centreYProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
}//End Class
