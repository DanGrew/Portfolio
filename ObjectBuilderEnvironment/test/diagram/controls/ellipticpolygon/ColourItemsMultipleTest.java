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
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import javafx.collections.FXCollections;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

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
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.FillColour,
               EllipticPolygonProperties.StrokeColour
      ) );  
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.FillColour,
               EllipticPolygonProperties.StrokeColour
      ) );  
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.FillColour,
               EllipticPolygonProperties.StrokeColour
      ) );  
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
