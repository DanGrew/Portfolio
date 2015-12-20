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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * {@link NumberOfSidesItems} test.
 */
public class NumberOfSidesItemsMultipleTest {
   
   private SelectionScenario scenario;
   private NumberOfSidesItems systemUnderTest;
   
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
      systemUnderTest = new NumberOfSidesItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_TYPE, scenario.diamond.polygonTypeProperty().get() );
      Assert.assertEquals( SelectionScenario.TRIANGLE_TYPE, scenario.triangle.polygonTypeProperty().get() );
      Assert.assertEquals( SelectionScenario.PENTAGON_TYPE, scenario.pentagon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.NumberOfSides
      ) );
      scenario.assertTrianglePropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.NumberOfSides
      ) );
      scenario.assertPentagonPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.NumberOfSides
      ) );
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can have the number of sides changed.
    */
   @Test public void shouldMakePolygonChangeSides() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
         button.fire();
         
         Assert.assertEquals( i, scenario.diamond.numberOfSidesProperty().get() );
         Assert.assertEquals( i, scenario.triangle.numberOfSidesProperty().get() );
         Assert.assertEquals( i, scenario.pentagon.numberOfSidesProperty().get() );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method
   
   /**
    * Prove that the graphic does not update.
    */
   @Test public void graphicShouldMimicPolygonWithDifferentSides() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
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
   private void assertGraphicDisplaysProperty( int expectedSides, EllipticPolygon graphicPolygon ){
      Assert.assertEquals( expectedSides, graphicPolygon.numberOfSidesProperty().get() );
   }//End Method

}//End Class
