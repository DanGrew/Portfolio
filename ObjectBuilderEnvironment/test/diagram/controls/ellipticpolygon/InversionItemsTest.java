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
public class InversionItemsTest {

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
      
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( scenario.diamond ) );
      Mockito.when( scenario.shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      systemUnderTest = new InversionItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_INVERSION, scenario.diamond.inversionProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Inversion,
               EllipticPolygonProperties.Type
      ) );   
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can be inverted.
    */
   @Test public void shouldMakePolygonsChangeInversionTrue() {
      Assert.assertFalse( scenario.diamond.inversionProperty().get() );
      Button button = systemUnderTest.inversionButton( true );
      button.fire();
      
      Assert.assertTrue( scenario.diamond.inversionProperty().get() );
      Assert.assertEquals( PolygonType.Starred, scenario.triangle.polygonTypeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can not be inverted.
    */
   @Test public void shouldMakePolygonsChangeInversionFalse() {
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( scenario.triangle ) );
      
      Assert.assertTrue( scenario.triangle.inversionProperty().get() );
      Button button = systemUnderTest.inversionButton( false );
      button.fire();
      
      Assert.assertFalse( scenario.triangle.inversionProperty().get() );
      Assert.assertEquals( PolygonType.Regular, scenario.triangle.polygonTypeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that the graphic represents the inverted property.
    */
   @Test public void graphicShouldShowPropertyTrue() {
      Button button = systemUnderTest.inversionButton( true );
      assertGraphicUpdates( button, true );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that the graphic represents the not inverted property.
    */
   @Test public void graphicShouldShowPropertyFalse() {
      Button button = systemUnderTest.inversionButton( false );
      assertGraphicUpdates( button, false );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @param expectedType the expected {@link PolygonType} of the {@link Button}'s graphic.
    */
   private void assertGraphicUpdates( Button button, boolean inverted ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      
      Assert.assertEquals( inverted, graphicPolygon.inversionProperty().get() );
      if ( inverted ) {
         Assert.assertEquals( PolygonType.Starred, graphicPolygon.polygonTypeProperty().get() );
      } else {
         Assert.assertEquals( PolygonType.Regular, graphicPolygon.polygonTypeProperty().get() );
      }
   }//End Method
   
}//End Class
