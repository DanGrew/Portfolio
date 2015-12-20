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

import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
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
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.CentreX,
               EllipticPolygonProperties.CentreY
      ) );
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} changes centre X.
    */
   @Test public void shouldMakePolygonChangeCentreX() {
      Spinner< Double > spinner = systemUnderTest.centreXSpinner();
      final double value = -500.39;
      Assert.assertNotEquals( value, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( value, scenario.diamond.centreXProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes centre Y.
    */
   @Test public void shouldMakePolygonChangeCentreY() {
      Spinner< Double > spinner = systemUnderTest.centreYSpinner();
      final double value = 84723.2342;
      Assert.assertNotEquals( value, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( value, scenario.diamond.centreYProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
}//End Class
