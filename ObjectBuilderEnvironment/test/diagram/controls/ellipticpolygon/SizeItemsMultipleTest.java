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
import javafx.scene.control.Spinner;
import utility.TestCommon;

/**
 * {@link SizeItems} multiple selection test.
 */
public class SizeItemsMultipleTest {
   
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
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( 
               FXCollections.observableSet( scenario.diamond, scenario.triangle, scenario.pentagon ) );
      Mockito.when( scenario.shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      scenario.controller = new ShapeManagerSelectionControllerImpl( scenario.shapes );
      systemUnderTest = new SizeItems( scenario.controller );
      
      Assert.assertEquals( SelectionScenario.DIAMOND_HORIZONTAL_RADIUS, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_VERTICAL_RADIUS, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.DIAMOND_STROKE_WIDTH, scenario.diamond.strokeWidthProperty().get(), TestCommon.precision() );
      
      Assert.assertEquals( SelectionScenario.TRIANGLE_HORIZONTAL_RADIUS, scenario.triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_VERTICAL_RADIUS, scenario.triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_STROKE_WIDTH, scenario.triangle.strokeWidthProperty().get(), TestCommon.precision() );
      
      Assert.assertEquals( SelectionScenario.PENTAGON_HORIZONTAL_RADIUS, scenario.pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_VERTICAL_RADIUS, scenario.pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( SelectionScenario.PENTAGON_STROKE_WIDTH, scenario.pentagon.strokeWidthProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.HorizontalRadius,
               EllipticPolygonProperties.VerticalRadius,
               EllipticPolygonProperties.StrokeWidth
      ) );  
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.HorizontalRadius,
               EllipticPolygonProperties.VerticalRadius,
               EllipticPolygonProperties.StrokeWidth
      ) );  
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.HorizontalRadius,
               EllipticPolygonProperties.VerticalRadius,
               EllipticPolygonProperties.StrokeWidth
      ) );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} changes horizontal radius.
    */
   @Test public void shouldMakePolygonsChangeHorizontalRadius() {
      final double radius = 3495.0982;
      assertHorizontalRadiusProperty( radius, radius );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes horizontal radius to the minimum only.
    */
   @Test public void shouldMakePolygonsChangeHorizontalRadiusMin() {
      final double minValue = -300;
      assertHorizontalRadiusProperty( 1, minValue );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes vertical radius.
    */
   @Test public void shouldMakePolygonsChangeVerticalRadius() {
      final double radius = 3495.0982;
      assertVerticalRadiusProperty( radius, radius );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes vertical radius to the minimum only.
    */
   @Test public void shouldMakePolygonsChangeVerticalRadiusMin() {
      final double minValue = -300;
      assertVerticalRadiusProperty( 1, minValue );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes stroke width.
    */
   @Test public void shouldMakePolygonsChangeStrokeWidth() {
      final double width = 10;
      assertVerticalRadiusProperty( width, width );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} changes stroke width to the minimum only.
    */
   @Test public void shouldMakePolygonsChangeStrokeWidthMin() {
      final double width = -300;
      assertStrokeWidthProperty( 1, width );
   }//End Method
   
   /**
    * Method to assert that the horizontal radius is changed correctly.
    * @param expected the expected horizontal radius.
    * @param value the value to change to.
    */
   private void assertHorizontalRadiusProperty( double expected, double value ) {
      Spinner< Double > spinner = systemUnderTest.horizontalRadiusSpinner();
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( expected, scenario.diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Method to assert that the vertical radius is changed correctly.
    * @param expected the expected vertical radius.
    * @param value the value to change to.
    */
   private void assertVerticalRadiusProperty( double expected, double value ) {
      Spinner< Double > spinner = systemUnderTest.verticalRadiusSpinner();
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( expected, scenario.diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Method to assert that the stroke width is changed correctly.
    * @param expected the expected stroke width.
    * @param value the value to change to.
    */
   private void assertStrokeWidthProperty( double expected, double value ) {
      Spinner< Double > spinner = systemUnderTest.strokeWidthSpinner();
      spinner.getValueFactory().valueProperty().set( value );
      
      Assert.assertEquals( expected, scenario.diamond.strokeWidthProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.triangle.strokeWidthProperty().get(), TestCommon.precision() );
      Assert.assertEquals( expected, scenario.pentagon.strokeWidthProperty().get(), TestCommon.precision() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
}//End Class
