/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import org.junit.Assert;
import org.mockito.Mockito;

import diagram.controls.DiagramAccordion;
import diagram.selection.SelectionController;
import diagram.selection.ShapeManagerSelectionControllerImpl;
import diagram.selection.ShapesManager;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import graphics.JavaFxInitializer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * The {@link SelectionScenario} provides a simple scenario with some {@link EllipticPolygon}s that
 * can be selected, allowing the {@link DiagramAccordion} items to be tested.
 */
public class SelectionScenario {
   
   static final PolygonType DIAMOND_TYPE = PolygonType.Regular;
   static final int DIAMOND_ROTATE = 189;
   static final int DIAMOND_NUMBER_OF_SIDES = 4;
   static final int DIAMOND_FRACTAL = 1;
   static final boolean DIAMOND_INVERSION = false;
   static final int DIAMOND_VERTICAL_RADIUS = 564;
   static final int DIAMOND_HORIZONTAL_RADIUS = 876;
   static final int DIAMOND_CENTRE_Y = 203;
   static final int DIAMOND_CENTRE_X = 101;
   static final Paint DIAMOND_FILL_COLOUR = Color.RED;
   static final Paint DIAMOND_STROKE_COLOUR = Color.ORANGE;
   
   static final PolygonType TRIANGLE_TYPE = PolygonType.Starred;
   static final int TRIANGLE_ROTATE = 25;
   static final int TRIANGLE_NUMBER_OF_SIDES = 3;
   static final int TRIANGLE_FRACTAL = 3;
   static final boolean TRIANGLE_INVERSION = true;
   static final int TRIANGLE_VERTICAL_RADIUS = 12;
   static final int TRIANGLE_HORIZONTAL_RADIUS = 34;
   static final int TRIANGLE_CENTRE_Y = 1000;
   static final int TRIANGLE_CENTRE_X = 2000;
   static final Paint TRIANGLE_FILL_COLOUR = Color.GREEN;
   static final Paint TRIANGLE_STROKE_COLOUR = Color.BLUE;
   
   static final PolygonType PENTAGON_TYPE = PolygonType.Fractal;
   static final int PENTAGON_ROTATE = 90;
   static final int PENTAGON_NUMBER_OF_SIDES = 5;
   static final int PENTAGON_FRACTAL = 2;
   static final boolean PENTAGON_INVERSION = false;
   static final int PENTAGON_VERTICAL_RADIUS = 1000;
   static final int PENTAGON_HORIZONTAL_RADIUS = 2000;
   static final int PENTAGON_CENTRE_Y = 1;
   static final int PENTAGON_CENTRE_X = 2;
   static final Paint PENTAGON_FILL_COLOUR = Color.BROWN;
   static final Paint PENTAGON_STROKE_COLOUR = Color.GRAY;
   
   final EllipticPolygon diamond;
   final EllipticPolygon triangle;
   final EllipticPolygon pentagon;
   
   ShapesManager shapes;
   SelectionController controller;
   
   /**
    * Method to initialise the environment for testing.
    */
   public static void initialiseScenario(){
      JavaFxInitializer.threadedLaunchWithDefaultScene();
   }//End Method
   
   /**
    * Method to initialise the system under test.
    */
   SelectionScenario() { 
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
      diamond.fillProperty().set( DIAMOND_FILL_COLOUR );
      diamond.strokeProperty().set( DIAMOND_STROKE_COLOUR );
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
      triangle.fillProperty().set( TRIANGLE_FILL_COLOUR );
      triangle.strokeProperty().set( TRIANGLE_STROKE_COLOUR );
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
      pentagon.fillProperty().set( PENTAGON_FILL_COLOUR );
      pentagon.strokeProperty().set( PENTAGON_STROKE_COLOUR );
      Assert.assertEquals( DIAMOND_TYPE, diamond.polygonTypeProperty().get() );
      Assert.assertEquals( TRIANGLE_TYPE, triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PENTAGON_TYPE, pentagon.polygonTypeProperty().get() );
      
      shapes = Mockito.mock( ShapesManager.class );
      controller = new ShapeManagerSelectionControllerImpl( shapes );
   }//End Constructor

}//End Class
