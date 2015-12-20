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
import utility.TestCommon;

/**
 * {@link PolygonTypeItems} test.
 */
public class PolygonTypeItemsMultipleTest {

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
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( 
               FXCollections.observableSet( scenario.diamond, scenario.triangle, scenario.pentagon ) );
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
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.Type
      ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( 
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
      Assert.assertEquals( PolygonType.Regular, scenario.triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Regular, scenario.pentagon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Starred}.
    */
   @Test public void shouldMakePolygonStarred() {
      scenario.diamond.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, scenario.diamond.polygonTypeProperty().get() );
      scenario.triangle.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, scenario.triangle.polygonTypeProperty().get() );
      scenario.pentagon.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, scenario.pentagon.polygonTypeProperty().get() );
      
      Button button = systemUnderTest.starredButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Starred, scenario.diamond.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Starred, scenario.triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Starred, scenario.pentagon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Fractal}.
    */
   @Test public void shouldMakePolygonFractal() {
      Button button = systemUnderTest.fractalButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Fractal, scenario.diamond.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Fractal, scenario.triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Fractal, scenario.pentagon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Regular} updates.
    */
   @Test public void regularGraphicShouldNotMimicPolygon() {
      Button button = systemUnderTest.regularButton();
      EllipticPolygon graphicPolygon = extractGraphicPolygon( button );
      assertGraphicDoesNotUpdate( graphicPolygon );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Starred} updates.
    */
   @Test public void starredGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.starredButton();
      EllipticPolygon graphicPolygon = extractGraphicPolygon( button );
      assertGraphicDoesNotUpdate( graphicPolygon );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Fractal} updates.
    */
   @Test public void fractalGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.fractalButton();
      EllipticPolygon graphicPolygon = extractGraphicPolygon( button );
      assertGraphicDoesNotUpdate( graphicPolygon );
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
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
   private void assertGraphicDoesNotUpdate( EllipticPolygon graphicPolygon ){
      final int originalNumberOfSides = graphicPolygon.numberOfSidesProperty().get();
      scenario.diamond.numberOfSidesProperty().set( 7 );
      scenario.triangle.numberOfSidesProperty().set( 7 );
      scenario.pentagon.numberOfSidesProperty().set( 7 );
      Assert.assertEquals( originalNumberOfSides, graphicPolygon.numberOfSidesProperty().get() );
      
      final double originalRotate = graphicPolygon.rotateProperty().get();
      scenario.diamond.rotateProperty().set( 360 );
      scenario.triangle.rotateProperty().set( 360 );
      scenario.pentagon.rotateProperty().set( 360 );
      Assert.assertEquals( originalRotate, graphicPolygon.rotateProperty().get(), TestCommon.precision() );
      
      final boolean originalInversion = graphicPolygon.inversionProperty().get();
      scenario.diamond.inversionProperty().set( !scenario.diamond.inversionProperty().get() );
      scenario.triangle.inversionProperty().set( !scenario.triangle.inversionProperty().get() );
      scenario.pentagon.inversionProperty().set( !scenario.pentagon.inversionProperty().get() );
      Assert.assertEquals( originalInversion, graphicPolygon.inversionProperty().get() );
      
      final int originalFractals = graphicPolygon.numberOfFractalsProperty().get();
      scenario.diamond.numberOfFractalsProperty().set( 0 );
      scenario.triangle.numberOfFractalsProperty().set( 0 );
      scenario.pentagon.numberOfFractalsProperty().set( 0 );
      Assert.assertEquals( originalFractals, graphicPolygon.numberOfFractalsProperty().get() );
   }//End Method

}//End Class
