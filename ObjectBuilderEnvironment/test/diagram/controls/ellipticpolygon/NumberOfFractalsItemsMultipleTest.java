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
 * {@link PolygonTypeItems} test.
 */
public class NumberOfFractalsItemsMultipleTest {
   
   private SelectionScenario scenario;
   private NumberOfFractalsItems systemUnderTest;
   
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
      systemUnderTest = new NumberOfFractalsItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_FRACTAL, scenario.diamond.numberOfFractalsProperty().get() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_FRACTAL, scenario.triangle.numberOfFractalsProperty().get() );
      Assert.assertEquals( SelectionScenario.PENTAGON_FRACTAL, scenario.pentagon.numberOfFractalsProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.NumberOfFractals,
               EllipticPolygonProperties.Type
      ) );  
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.NumberOfFractals,
               EllipticPolygonProperties.Type
      ) );  
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.NumberOfFractals,
               EllipticPolygonProperties.Type
      ) );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can have its fractal number changed.
    */
   @Test public void shouldMakePolygonChangeSides() {
      for ( int i = 0; i < 4; i++ ) {
         Button button = systemUnderTest.fractalsButton( i );
         button.fire();
         
         Assert.assertEquals( i, scenario.diamond.numberOfFractalsProperty().get() );
         Assert.assertEquals( i, scenario.triangle.numberOfFractalsProperty().get() );
         Assert.assertEquals( i, scenario.pentagon.numberOfFractalsProperty().get() );
         Assert.assertEquals( PolygonType.Fractal, scenario.triangle.polygonTypeProperty().get() );
         Assert.assertEquals( PolygonType.Fractal, scenario.diamond.polygonTypeProperty().get() );
         Assert.assertEquals( PolygonType.Fractal, scenario.pentagon.polygonTypeProperty().get() );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method
   
   /**
    * Prove that the {@link EllipticPolygon} shown represents fractals.
    */
   @Test public void graphicShouldMimicPolygonWithDifferentSides() {
      for ( int i = 0; i < 4; i++ ) {
         Button button = systemUnderTest.fractalsButton( i );
         EllipticPolygon graphicPolygon = extractGraphicPolygon( button );
         assertGraphicDisplaysProperty( i, graphicPolygon );
      }
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @return the {@link EllipticPolygon} in the graphic of the {@link Button}.
    */
   private static EllipticPolygon extractGraphicPolygon( Button button ) {
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
   private void assertGraphicDisplaysProperty( int expectedFractals, EllipticPolygon graphicPolygon ){
      Assert.assertEquals( expectedFractals, graphicPolygon.numberOfFractalsProperty().get() );
      Assert.assertEquals( PolygonType.Fractal, graphicPolygon.polygonTypeProperty().get() );
   }//End Method

}//End Class
