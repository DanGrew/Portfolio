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
 * {@link NumberOfSidesItems} test.
 */
public class NumberOfSidesItemsTest {

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
      
      Mockito.when( scenario.shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( scenario.diamond ) );
      Mockito.when( scenario.shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      systemUnderTest = new NumberOfSidesItems( scenario.controller );
      Assert.assertEquals( SelectionScenario.DIAMOND_NUMBER_OF_SIDES, scenario.diamond.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      scenario.assertDiamondPropertiesUnchanged( Arrays.asList( 
               EllipticPolygonProperties.NumberOfSides
      ) );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can have the number of sides changed and nothing else.
    */
   @Test public void shouldMakePolygonsChangeSides() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
         button.fire();
         
         Assert.assertEquals( i, scenario.diamond.numberOfSidesProperty().get() );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method

   /**
    * Prove that numbers outside of the {@link Button} range are ignored.
    */
   @Test public void shouldIgnoreButtonOutsideOfRange() {
      Button button = systemUnderTest.sidesButton( 11 );
      Assert.assertNull( button );
      button = systemUnderTest.sidesButton( 2 );
      Assert.assertNull( button );
   }//End Method
   
   /**
    * Prove that the original {@link EllipticPolygon} does not change when a graphic is constructed.
    */
   @Test public void graphicShouldNotChangeAssociatedPolygons() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
         assertGraphicUpdates( button, i );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    * @param expectedType the expected {@link PolygonType} of the {@link Button}'s graphic.
    */
   private void assertGraphicUpdates( Button button, int numberOfSides ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      
      Assert.assertEquals( numberOfSides, graphicPolygon.numberOfSidesProperty().get() );
   }//End Method
   
}//End Class
