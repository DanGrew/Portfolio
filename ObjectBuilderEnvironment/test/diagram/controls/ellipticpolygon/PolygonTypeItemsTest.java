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

import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import graphics.JavaFxInitializer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import utility.TestCommon;

/**
 * {@link PolygonTypeItems} test.
 */
public class PolygonTypeItemsTest {
   
   private static final int DEFAULT_ROTATE = 189;
   private static final int DEFAULT_NUMBER_OF_SIDES = 4;
   private static final int DEFAULT_FRACTAL = 1;
   private static final boolean DEFAULT_INVERSION = false;
   private static final int DEFAULT_VERTICAL_RADIUS = 564;
   private static final int DEFAULT_HORIZONTAL_RADIUS = 876;
   private static final int DEFAULT_CENTRE_Y = 203;
   private static final int DEFAULT_CENTRE_X = 101;
   private EllipticPolygon polygon;
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
      polygon = new EllipticPolygon( 
               new EllipticPolygonBuilder( PolygonType.Starred, DEFAULT_NUMBER_OF_SIDES )
                  .centreXProperty( DEFAULT_CENTRE_X )
                  .centreYProperty( DEFAULT_CENTRE_Y )
                  .horizontalRadiusProperty( DEFAULT_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( DEFAULT_VERTICAL_RADIUS )
                  .inversionProperty( DEFAULT_INVERSION )
                  .numberOfFractals( DEFAULT_FRACTAL )
                  .rotateProperty( DEFAULT_ROTATE )
                  
      );
      Assert.assertEquals( PolygonType.Starred, polygon.polygonTypeProperty().get() );
      
      systemUnderTest = new PolygonTypeItems( polygon );
      Assert.assertEquals( PolygonType.Starred, polygon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that the associated {@link EllipticPolygon} is not changed by the creation of the items.
    */
   @Test public void shouldRetainOriginalConfigurationOfPolygon(){
      Assert.assertEquals( DEFAULT_NUMBER_OF_SIDES, polygon.numberOfSidesProperty().get() );
      Assert.assertEquals( DEFAULT_CENTRE_X, polygon.centreXProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_CENTRE_Y, polygon.centreYProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_HORIZONTAL_RADIUS, polygon.horizontalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_VERTICAL_RADIUS, polygon.verticalRadiusProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_ROTATE, polygon.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( DEFAULT_INVERSION, polygon.inversionProperty().get() );
      Assert.assertEquals( DEFAULT_FRACTAL, polygon.numberOfFractalsProperty().get() );  
   }//End Method

   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Regular}.
    */
   @Test public void shouldMakePolygonRegular() {
      Button button = systemUnderTest.regularButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Regular, polygon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Starred}.
    */
   @Test public void shouldMakePolygonStarred() {
      polygon.polygonTypeProperty().set( PolygonType.Regular );
      Assert.assertEquals( PolygonType.Regular, polygon.polygonTypeProperty().get() );
      
      Button button = systemUnderTest.starredButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Starred, polygon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that an {@link EllipticPolygon} can be made {@link PolygonType#Fractal}.
    */
   @Test public void shouldMakePolygonFractal() {
      Button button = systemUnderTest.fractalButton();
      button.fire();
      
      Assert.assertEquals( PolygonType.Fractal, polygon.polygonTypeProperty().get() );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Regular} updates.
    */
   @Test public void regularGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.regularButton();
      assertGraphicUpdates( button );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Starred} updates.
    */
   @Test public void starredGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.starredButton();
      assertGraphicUpdates( button );
   }//End Method
   
   /**
    * Prove that the {@link PolygonType#Fractal} updates.
    */
   @Test public void fractalGraphicShouldMimicPolygon() {
      Button button = systemUnderTest.fractalButton();
      assertGraphicUpdates( button );
   }//End Method
   
   /**
    * Method to obtain the graphic and verify that it and the associated {@link EllipticPolygon}
    * update together.
    * @param button the {@link Button} being tested.
    */
   private void assertGraphicUpdates( Button button ) {
      Node graphic = button.getGraphic();
      Assert.assertTrue( graphic instanceof BorderPane );
      BorderPane pane = ( BorderPane )graphic;
      Assert.assertTrue( pane.getCenter() instanceof EllipticPolygon );
      EllipticPolygon graphicPolygon = ( EllipticPolygon )pane.getCenter();
      
      assertPolygonTypeItemsGraphicPropertiesMatch( polygon, graphicPolygon );
      modifyPolygonTypeItemsGraphicProperties( polygon );
      assertPolygonTypeItemsGraphicPropertiesMatch( polygon, graphicPolygon );
      modifyPolygonTypeItemsGraphicProperties( graphicPolygon );
      assertPolygonTypeItemsGraphicPropertiesMatch( polygon, graphicPolygon );
   }//End Method
   
   /**
    * Method to assert that the given two {@link EllipticPolygon}s match in their properties according to the
    * requirements of the items.
    */
   private static void assertPolygonTypeItemsGraphicPropertiesMatch( EllipticPolygon polygon, EllipticPolygon graphicPolygon ){
      Assert.assertEquals( polygon.numberOfSidesProperty().get(), graphicPolygon.numberOfSidesProperty().get() );
      Assert.assertEquals( polygon.rotateProperty().get(), graphicPolygon.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( polygon.inversionProperty().get(), graphicPolygon.inversionProperty().get() );
      Assert.assertEquals( polygon.numberOfFractalsProperty().get(), graphicPolygon.numberOfFractalsProperty().get() );
   }//End Method
   
   /**
    * Method to modify the contents of the given {@link EllipticPolygon}, randomly.
    * @param polygon the {@link EllipticPolygon} to change.
    */
   private static void modifyPolygonTypeItemsGraphicProperties( EllipticPolygon polygon ) {
      final int sides = TestCommon.randomInt( 3, 10 );
      final double rotate = TestCommon.randomDouble( -180, 180 );
      final boolean inversion = !polygon.inversionProperty().get();
      final int fractals = TestCommon.randomInt( 0, 4 );      
      
      polygon.numberOfSidesProperty().set( sides );    
      polygon.rotateProperty().set( rotate );           
      polygon.inversionProperty().set( inversion );        
      polygon.numberOfFractalsProperty().set( fractals ); 
      
      Assert.assertEquals( sides, polygon.numberOfSidesProperty().get() );
      Assert.assertEquals( rotate, polygon.rotateProperty().get(), TestCommon.precision() );
      Assert.assertEquals( inversion, polygon.inversionProperty().get() );
      Assert.assertEquals( fractals, polygon.numberOfFractalsProperty().get() );
   }//End Method

}//End Class
