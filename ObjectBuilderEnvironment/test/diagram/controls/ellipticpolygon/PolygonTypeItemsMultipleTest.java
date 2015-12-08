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
import diagram.selection.ShapesManagerSelectionControllerImpl;
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
public class PolygonTypeItemsMultipleTest {
   
   private static final int DIAMOND_ROTATE = 189;
   private static final int DIAMOND_NUMBER_OF_SIDES = 4;
   private static final int DIAMOND_FRACTAL = 1;
   private static final boolean DIAMOND_INVERSION = false;
   private static final int DIAMOND_VERTICAL_RADIUS = 564;
   private static final int DIAMOND_HORIZONTAL_RADIUS = 876;
   private static final int DIAMOND_CENTRE_Y = 203;
   private static final int DIAMOND_CENTRE_X = 101;
   
   private static final int TRIANGLE_ROTATE = 25;
   private static final int TRIANGLE_NUMBER_OF_SIDES = 3;
   private static final int TRIANGLE_FRACTAL = 3;
   private static final boolean TRIANGLE_INVERSION = true;
   private static final int TRIANGLE_VERTICAL_RADIUS = 12;
   private static final int TRIANGLE_HORIZONTAL_RADIUS = 34;
   private static final int TRIANGLE_CENTRE_Y = 1000;
   private static final int TRIANGLE_CENTRE_X = 2000;
   
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
   private PolygonTypeItems systemUnderTest;
   
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
               new EllipticPolygonBuilder( PolygonType.Starred, DIAMOND_NUMBER_OF_SIDES )
                  .centreXProperty( DIAMOND_CENTRE_X )
                  .centreYProperty( DIAMOND_CENTRE_Y )
                  .horizontalRadiusProperty( DIAMOND_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( DIAMOND_VERTICAL_RADIUS )
                  .inversionProperty( DIAMOND_INVERSION )
                  .numberOfFractals( DIAMOND_FRACTAL )
                  .rotateProperty( DIAMOND_ROTATE )
                  
      );
      triangle = new EllipticPolygon( 
               new EllipticPolygonBuilder( PolygonType.Starred, TRIANGLE_NUMBER_OF_SIDES )
                  .centreXProperty( TRIANGLE_CENTRE_X )
                  .centreYProperty( TRIANGLE_CENTRE_Y )
                  .horizontalRadiusProperty( TRIANGLE_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( TRIANGLE_VERTICAL_RADIUS )
                  .inversionProperty( TRIANGLE_INVERSION )
                  .numberOfFractals( TRIANGLE_FRACTAL )
                  .rotateProperty( TRIANGLE_ROTATE )
                  
      );
      pentagon = new EllipticPolygon( 
               new EllipticPolygonBuilder( PolygonType.Starred, PENTAGON_NUMBER_OF_SIDES )
                  .centreXProperty( PENTAGON_CENTRE_X )
                  .centreYProperty( PENTAGON_CENTRE_Y )
                  .horizontalRadiusProperty( PENTAGON_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( PENTAGON_VERTICAL_RADIUS )
                  .inversionProperty( PENTAGON_INVERSION )
                  .numberOfFractals( PENTAGON_FRACTAL )
                  .rotateProperty( PENTAGON_ROTATE )
                  
      );
      Assert.assertEquals( PolygonType.Starred, diamond.polygonTypeProperty().get() );
      
      shapes = Mockito.mock( ShapesManager.class );
      Mockito.when( shapes.canvasShapeSelection() ).thenReturn( FXCollections.observableSet( diamond, triangle, pentagon ) );
      Mockito.when( shapes.singletonSelection() ).thenReturn( FXCollections.observableSet() );
      
      controller = new ShapesManagerSelectionControllerImpl( shapes );
      systemUnderTest = new PolygonTypeItems( controller );
      Assert.assertEquals( PolygonType.Starred, diamond.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      Assert.assertEquals( DIAMOND_NUMBER_OF_SIDES, diamond.numberOfSidesProperty().get() );
      Assert.assertEquals( DIAMOND_CENTRE_X, diamond.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_CENTRE_Y, diamond.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_HORIZONTAL_RADIUS, diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_VERTICAL_RADIUS, diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_ROTATE, diamond.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DIAMOND_INVERSION, diamond.inversionProperty().get() );
      Assert.assertEquals( DIAMOND_FRACTAL, diamond.numberOfFractalsProperty().get() );
      
      Assert.assertEquals( TRIANGLE_NUMBER_OF_SIDES, triangle.numberOfSidesProperty().get() );
      Assert.assertEquals( TRIANGLE_CENTRE_X, triangle.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_CENTRE_Y, triangle.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_HORIZONTAL_RADIUS, triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_VERTICAL_RADIUS, triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_ROTATE, triangle.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( TRIANGLE_INVERSION, triangle.inversionProperty().get() );
      Assert.assertEquals( TRIANGLE_FRACTAL, triangle.numberOfFractalsProperty().get() ); 
      
      Assert.assertEquals( PENTAGON_NUMBER_OF_SIDES, pentagon.numberOfSidesProperty().get() );
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
   @Test public void shouldMakePolygonRegular() {
      Button button = systemUnderTest.regularButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Regular, diamond.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Regular, triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Regular, pentagon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Starred}.
    */
   @Test public void shouldMakePolygonStarred() {
      diamond.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, diamond.polygonTypeProperty().get() );
      triangle.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, triangle.polygonTypeProperty().get() );
      pentagon.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, pentagon.polygonTypeProperty().get() );
      
      Button button = systemUnderTest.starredButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Starred, diamond.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Starred, triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Starred, pentagon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Fractal}.
    */
   @Test public void shouldMakePolygonFractal() {
      Button button = systemUnderTest.fractalButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Fractal, diamond.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Fractal, triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PolygonType.Fractal, pentagon.polygonTypeProperty().get() );
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
      diamond.numberOfSidesProperty().set( 7 );
      triangle.numberOfSidesProperty().set( 7 );
      pentagon.numberOfSidesProperty().set( 7 );
      Assert.assertEquals( originalNumberOfSides, graphicPolygon.numberOfSidesProperty().get() );
      
      final double originalRotate = graphicPolygon.rotateProperty().get();
      diamond.rotateProperty().set( 360 );
      triangle.rotateProperty().set( 360 );
      pentagon.rotateProperty().set( 360 );
      Assert.assertEquals( originalRotate, graphicPolygon.rotateProperty().get(), TestCommon.precision() );
      
      final boolean originalInversion = graphicPolygon.inversionProperty().get();
      diamond.inversionProperty().set( !diamond.inversionProperty().get() );
      triangle.inversionProperty().set( !triangle.inversionProperty().get() );
      pentagon.inversionProperty().set( !pentagon.inversionProperty().get() );
      Assert.assertEquals( originalInversion, graphicPolygon.inversionProperty().get() );
      
      final int originalFractals = graphicPolygon.numberOfFractalsProperty().get();
      diamond.numberOfFractalsProperty().set( 0 );
      triangle.numberOfFractalsProperty().set( 0 );
      pentagon.numberOfFractalsProperty().set( 0 );
      Assert.assertEquals( originalFractals, graphicPolygon.numberOfFractalsProperty().get() );
   }//End Method

}//End Class
