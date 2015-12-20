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
 * {@link PolygonTypeItems} test.
 */
public class PolygonTypeItemsTest {
   
   private SelectionScenario scenario;
   private PolygonTypeItems systemUnderTest;
   
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
      
      systemUnderTest = new PolygonTypeItems( scenario.controller );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Type
      ) );   
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Regular}.
    */
   @Test public void shouldMakePolygonRegular() {
      Button button = systemUnderTest.regularButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Regular, scenario.diamond.polygonTypeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Starred}.
    */
   @Test public void shouldMakePolygonStarred() {
      scenario.diamond.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, scenario.diamond.polygonTypeProperty().get() );
      
      Button button = systemUnderTest.starredButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Starred, scenario.diamond.polygonTypeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Fractal}.
    */
   @Test public void shouldMakePolygonFractal() {
      Button button = systemUnderTest.fractalButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Fractal, scenario.diamond.polygonTypeProperty().get() );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Regular} updates.
    */
   @Test public void regularGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.regularButton();
      assertGraphicUpdates( button, PolygonType.Regular );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Starred} updates.
    */
   @Test public void starredGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.starredButton();
      assertGraphicUpdates( button, PolygonType.Starred );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Fractal} updates.
    */
   @Test public void fractalGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.fractalButton();
      assertGraphicUpdates( button, PolygonType.Fractal );
      shouldRetainOriginalConfigurationOfPolygon();
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @param expectedType the expected {@link PolygonType} of the {@link Button}'s graphic.
    */
   private void assertGraphicUpdates( Button button, PolygonType expectedType ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      
      Assert.assertEquals( expectedType, graphicPolygon.polygonTypeProperty().get() );
   }//End Method
   
}//End Class
