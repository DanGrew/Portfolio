/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import diagram.selection.SelectionController;
import diagram.selection.ShapeManagerSelectionControllerImpl;
import diagram.selection.ShapesManager;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import graphics.JavaFxInitializer;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import utility.TestCommon;

/**
 * {@link PolygonTypeItems} test.
 */
public class NumberOfSidesItemsMultipleTest {
   
   private static final PolygonType DIAMOND_TYPE = PolygonType.Regular;
   private static final int DIAMOND_ROTATE = 189;
   private static final int DIAMOND_NUMBER_OF_SIDES = 4;
   private static final int DIAMOND_FRACTAL = 1;
   private static final boolean DIAMOND_INVERSION = false;
   private static final int DIAMOND_VERTICAL_RADIUS = 564;
   private static final int DIAMOND_HORIZONTAL_RADIUS = 876;
   private static final int DIAMOND_CENTRE_Y = 203;
   private static final int DIAMOND_CENTRE_X = 101;
   
   private static final PolygonType TRIANGLE_TYPE = PolygonType.Starred;
   private static final int TRIANGLE_ROTATE = 25;
   private static final int TRIANGLE_NUMBER_OF_SIDES = 3;
   private static final int TRIANGLE_FRACTAL = 3;
   private static final boolean TRIANGLE_INVERSION = true;
   private static final int TRIANGLE_VERTICAL_RADIUS = 12;
   private static final int TRIANGLE_HORIZONTAL_RADIUS = 34;
   private static final int TRIANGLE_CENTRE_Y = 1000;
   private static final int TRIANGLE_CENTRE_X = 2000;
   
   private static final PolygonType PENTAGON_TYPE = PolygonType.Fractal;
   private static final int PENTAGON_ROTATE = 90;
   private static final int PENTAGON_NUMBER_OF_SIDES = 5;
   private static final int PENTAGON_FRACTAL = 2;
   private static final boolean PENTAGON_INVERSION = false;
   private static final int PENTAGON_VERTICAL_RADIUS = 1000;
   private static final int PENTAGON_HORIZONTAL_RADIUS = 2000;
   private static final int PENTAGON_CENTRE_Y = 1;
   private static final int PENTAGON_CENTRE_X = 2;
   
   private EllipticPolygon diamond;
   private EllipticPolygon triangle;
   private EllipticPolygon pentagon;
   
   private ShapesManager shapes;
   private SelectionController controller;
   private NumberOfSidesItems systemUnderTest;
   
   /**
    * Method to initialise the environment for testing.
    */
   @BeforeClass public static void initialiseScenario(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
   }//End Method
   
   /**
    * Method to initialise the system under test.
    */
   @Before public void initialiseSystemUnderTest(){
      diamond = new EllipticPolygon( 
               new EllipticPolygonBuilder( DIAMOND_TYPE, DIAMOND_NUMBER_OF_SIDES )
                  .centreXProperty( DIAMOND_CENTRE_X )
                  .centreYProperty( DIAMOND_CENTRE_Y )
                  .horizontalRadiusProperty( DIAMOND_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( DIAMOND_VERTICAL_RADIUS )
                  .inversionProperty( DIAMOND_INVERSION )
                  .numberOfFractals( DIAMOND_FRACTAL )
                  .rotateProperty( DIAMOND_ROTATE )
                  
      );
      triangle = new EllipticPolygon( 
               new EllipticPolygonBuilder( TRIANGLE_TYPE, TRIANGLE_NUMBER_OF_SIDES )
                  .centreXProperty( TRIANGLE_CENTRE_X )
                  .centreYProperty( TRIANGLE_CENTRE_Y )
                  .horizontalRadiusProperty( TRIANGLE_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( TRIANGLE_VERTICAL_RADIUS )
                  .inversionProperty( TRIANGLE_INVERSION )
                  .numberOfFractals( TRIANGLE_FRACTAL )
                  .rotateProperty( TRIANGLE_ROTATE )
                  
      );
      pentagon = new EllipticPolygon( 
               new EllipticPolygonBuilder( PENTAGON_TYPE, PENTAGON_NUMBER_OF_SIDES )
                  .centreXProperty( PENTAGON_CENTRE_X )
                  .centreYProperty( PENTAGON_CENTRE_Y )
                  .horizontalRadiusProperty( PENTAGON_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( PENTAGON_VERTICAL_RADIUS )
                  .inversionProperty( PENTAGON_INVERSION )
                  .numberOfFractals( PENTAGON_FRACTAL )
                  .rotateProperty( PENTAGON_ROTATE )
                  
      );
      Assert.assertEquals( DIAMOND_TYPE, diamond.polygonTypeProperty().get() );
      Assert.assertEquals( TRIANGLE_TYPE, triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PENTAGON_TYPE, pentagon.polygonTypeProperty().get() );
      
      shapes = Mockito.mock( ShapesManager.class );
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( diamond, triangle, pentagon ) );
      Mockito.when( shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      controller = new ShapeManagerSelectionControllerImpl( shapes );
      systemUnderTest = new NumberOfSidesItems( controller );
      Assert.assertEquals( DIAMOND_TYPE, diamond.polygonTypeProperty().get() );
      Assert.assertEquals( TRIANGLE_TYPE, triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PENTAGON_TYPE, pentagon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      Assert.assertEquals( DIAMOND_TYPE, diamond.polygonTypeProperty().get() );
      Assert.assertEquals( DIAMOND_CENTRE_X, diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_CENTRE_Y, diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_HORIZONTAL_RADIUS, diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_VERTICAL_RADIUS, diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_ROTATE, diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_INVERSION, diamond.inversionProperty().get() );
      Assert.assertEquals( DIAMOND_FRACTAL, diamond.numberOfFractalsProperty().get() );
      
      Assert.assertEquals( TRIANGLE_TYPE, triangle.polygonTypeProperty().get() );
      Assert.assertEquals( TRIANGLE_CENTRE_X, triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_CENTRE_Y, triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_HORIZONTAL_RADIUS, triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_VERTICAL_RADIUS, triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_ROTATE, triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_INVERSION, triangle.inversionProperty().get() );
      Assert.assertEquals( TRIANGLE_FRACTAL, triangle.numberOfFractalsProperty().get() ); 
      
      Assert.assertEquals( PENTAGON_TYPE, pentagon.polygonTypeProperty().get() );
      Assert.assertEquals( PENTAGON_CENTRE_X, pentagon.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( PENTAGON_CENTRE_Y, pentagon.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( PENTAGON_HORIZONTAL_RADIUS, pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( PENTAGON_VERTICAL_RADIUS, pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( PENTAGON_ROTATE, pentagon.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( PENTAGON_INVERSION, pentagon.inversionProperty().get() );
      Assert.assertEquals( PENTAGON_FRACTAL, pentagon.numberOfFractalsProperty().get() ); 
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Regular}.
    */
   @Test public void shouldMakePolygonChangeSides() {
      for ( int i = 3; i < 11; i++ ) {
         Button button = systemUnderTest.sidesButton( i );
         button.fire();
         
         Assert.assertEquals( i, diamond.numberOfSidesProperty().get() );
         Assert.assertEquals( i, triangle.numberOfSidesProperty().get() );
         Assert.assertEquals( i, pentagon.numberOfSidesProperty().get() );
         shouldRetainOriginalConfigurationOfPolygon();
      }
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Regular} updates.
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
