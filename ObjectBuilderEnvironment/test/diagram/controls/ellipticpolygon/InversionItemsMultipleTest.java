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
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * {@link InversionItems} test.
 */
public class InversionItemsMultipleTest {
   
   private SelectionScenario scenario;
   private InversionItems systemUnderTest;
   
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
      systemUnderTest = new InversionItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_INVERSION, scenario.diamond.inversionProperty().get() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_INVERSION, scenario.triangle.inversionProperty().get() );
      Assert.assertEquals( SelectionScenario.PENTAGON_INVERSION, scenario.pentagon.inversionProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Inversion,
               EllipticPolygonProperties.Type
      ) );  
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Inversion,
               EllipticPolygonProperties.Type
      ) );  
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Inversion,
               EllipticPolygonProperties.Type
      ) );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can be inverted.
    */
   @Test public void shouldMakePolygonChangeInversionTrue() {
      Button button = systemUnderTest.inversionButton( true );
      button.fire();
      
      Assert.assertTrue( scenario.diamond.inversionProperty().get() );
      Assert.assertTrue( scenario.triangle.inversionProperty().get() );
      Assert.assertTrue( scenario.pentagon.inversionProperty().get() );
      Assert.assertEquals( PolygonType.Starred, scenario.diamond.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Starred, scenario.triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Starred, scenario.pentagon.polygonTypeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be not inverted.
    */
   @Test public void shouldMakePolygonChangeInversionFalse() {
      Button button = systemUnderTest.inversionButton( false );
      button.fire();
      
      Assert.assertFalse( scenario.diamond.inversionProperty().get() );
      Assert.assertFalse( scenario.triangle.inversionProperty().get() );
      Assert.assertFalse( scenario.pentagon.inversionProperty().get() );
      Assert.assertEquals( PolygonType.Regular, scenario.diamond.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Regular, scenario.triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Regular, scenario.pentagon.polygonTypeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that the graphic represents inverted correctly.
    */
   @Test public void graphicShouldMimicPolygonWithInversionTrue() {
      Button button = systemUnderTest.inversionButton( true );
      EllipticPolygon graphicPolygon = extractGraphicPolygon( button );
      assertGraphicDisplaysProperty( true, graphicPolygon );
   }//End Method

   /**
    * Prove that the graphic represents the non inverted correctly.
    */
   @Test public void graphicShouldMimicPolygonWithInversionFalse() {
      Button button = systemUnderTest.inversionButton( false );
      EllipticPolygon graphicPolygon = extractGraphicPolygon( button );
      assertGraphicDisplaysProperty( false, graphicPolygon );
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @return the {@link EllipticPolygon} in the graphic of the {@link Button}.
    */
   private EllipticPolygon extractGraphicPolygon( Button button ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      return graphicPolygon;
   }//End Method
   
   /**
    * Method to prove that the associated graphic is not disturbed by the shapes it represents.
    */
   private void assertGraphicDisplaysProperty( boolean inversion, EllipticPolygon graphicPolygon ){
      Assert.assertEquals( inversion, graphicPolygon.inversionProperty().get() );
      if ( inversion ) {
         Assert.assertEquals( PolygonType.Starred, graphicPolygon.polygonTypeProperty().get() );
      } else {
         Assert.assertEquals( PolygonType.Regular, graphicPolygon.polygonTypeProperty().get() );
      }
   }//End Method

}//End Class
