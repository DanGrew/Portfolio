/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import java.util.Collection;

import org.junit.Assert;
import org.mockito.Mockito;

import diagram.controls.DiagramAccordion;
import diagram.selection.SelectionController;
import diagram.selection.ShapeManagerSelectionControllerImpl;
import diagram.selection.ShapesManager;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import diagram.shapes.ellipticpolygon.EllipticPolygonProperties;
import graphics.JavaFxInitializer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import utility.TestCommon;

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
   static final double DIAMOND_STROKE_WIDTH = 1;
   
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
   static final double TRIANGLE_STROKE_WIDTH = 3;
   
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
   static final double PENTAGON_STROKE_WIDTH = 5;
   
   final EllipticPolygon diamond;
   final EllipticPolygon triangle;
   final EllipticPolygon pentagon;
   
   ShapesManager shapes;
   SelectionController controller;
   
   /**
    * Method to initialise the environment for testing.
    */
   public static void initialiseScenario(){
      JavaFxInitializer.startPlatform();
   }//End Method
   
   /**
    * Method to initialise the system under test.
    */
   SelectionScenario() { 
      this( Mockito.mock( ShapesManager.class ) );
   }//End Constructor
   
   /**
    * Constructs a new {@link SelectionScenario} with the given {@link ShapesManager}.
    * @param shapes the {@link ShapesManager}.
    */
   SelectionScenario( ShapesManager shapes ){
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
      diamond.strokeWidthProperty().set( DIAMOND_STROKE_WIDTH );
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
      triangle.strokeWidthProperty().set( TRIANGLE_STROKE_WIDTH );
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
      pentagon.strokeWidthProperty().set( PENTAGON_STROKE_WIDTH );
      Assert.assertEquals( DIAMOND_TYPE, diamond.polygonTypeProperty().get() );
      Assert.assertEquals( TRIANGLE_TYPE, triangle.polygonTypeProperty().get() );
      Assert.assertEquals( PENTAGON_TYPE, pentagon.polygonTypeProperty().get() );
      
      this.shapes = shapes;
      controller = new ShapeManagerSelectionControllerImpl( shapes );
   }
   
   /**
    * Method to assert that the properties of the diamond are as originally configured. 
    * @param propertiesToExclude the {@link EllipticPolygonProperties} to exclude from the assert.
    */
   public void assertDiamondPropertiesUnchanged( Collection< EllipticPolygonProperties > propertiesToExclude ){
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.CentreX ) ) {
         Assert.assertEquals( DIAMOND_CENTRE_X, diamond.centreXProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.CentreY ) ) {
         Assert.assertEquals( DIAMOND_CENTRE_Y, diamond.centreYProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.FillColour ) ) {
         Assert.assertEquals( DIAMOND_FILL_COLOUR, diamond.fillProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.HorizontalRadius ) ) {
         Assert.assertEquals( DIAMOND_HORIZONTAL_RADIUS, diamond.horizontalRadiusProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Inversion ) ) {
         Assert.assertEquals( DIAMOND_INVERSION, diamond.inversionProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.NumberOfFractals ) ) {
         Assert.assertEquals( DIAMOND_FRACTAL, diamond.numberOfFractalsProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.NumberOfSides ) ) {
         Assert.assertEquals( DIAMOND_NUMBER_OF_SIDES, diamond.numberOfSidesProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Rotation ) ) {
         Assert.assertEquals( DIAMOND_ROTATE, diamond.rotateProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.StrokeColour ) ) {
         Assert.assertEquals( DIAMOND_STROKE_COLOUR, diamond.strokeProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.StrokeWidth ) ) {
         Assert.assertEquals( DIAMOND_STROKE_WIDTH, diamond.strokeWidthProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Type ) ) {
         Assert.assertEquals( DIAMOND_TYPE, diamond.polygonTypeProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.VerticalRadius ) ) {
         Assert.assertEquals( DIAMOND_VERTICAL_RADIUS, diamond.verticalRadiusProperty().get(), TestCommon.precision() );
      }
   }//End Method
   
   /**
    * Method to assert that the properties of the triangle are as originally configured. 
    * @param propertiesToExclude the {@link EllipticPolygonProperties} to exclude from the assert.
    */
   public void assertTrianglePropertiesUnchanged( Collection< EllipticPolygonProperties > propertiesToExclude ){
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.CentreX ) ) {
         Assert.assertEquals( TRIANGLE_CENTRE_X, triangle.centreXProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.CentreY ) ) {
         Assert.assertEquals( TRIANGLE_CENTRE_Y, triangle.centreYProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.FillColour ) ) {
         Assert.assertEquals( TRIANGLE_FILL_COLOUR, triangle.fillProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.HorizontalRadius ) ) {
         Assert.assertEquals( TRIANGLE_HORIZONTAL_RADIUS, triangle.horizontalRadiusProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Inversion ) ) {
         Assert.assertEquals( TRIANGLE_INVERSION, triangle.inversionProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.NumberOfFractals ) ) {
         Assert.assertEquals( TRIANGLE_FRACTAL, triangle.numberOfFractalsProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.NumberOfSides ) ) {
         Assert.assertEquals( TRIANGLE_NUMBER_OF_SIDES, triangle.numberOfSidesProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Rotation ) ) {
         Assert.assertEquals( TRIANGLE_ROTATE, triangle.rotateProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.StrokeColour ) ) {
         Assert.assertEquals( TRIANGLE_STROKE_COLOUR, triangle.strokeProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.StrokeWidth ) ) {
         Assert.assertEquals( TRIANGLE_STROKE_WIDTH, triangle.strokeWidthProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Type ) ) {
         Assert.assertEquals( TRIANGLE_TYPE, triangle.polygonTypeProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.VerticalRadius ) ) {
         Assert.assertEquals( TRIANGLE_VERTICAL_RADIUS, triangle.verticalRadiusProperty().get(), TestCommon.precision() );
      }
   }//End Method
   
   /**
    * Method to assert that the properties of the pentagon are as originally configured. 
    * @param propertiesToExclude the {@link EllipticPolygonProperties} to exclude from the assert.
    */
   public void assertPentagonPropertiesUnchanged( Collection< EllipticPolygonProperties > propertiesToExclude ){
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.CentreX ) ) {
         Assert.assertEquals( PENTAGON_CENTRE_X, pentagon.centreXProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.CentreY ) ) {
         Assert.assertEquals( PENTAGON_CENTRE_Y, pentagon.centreYProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.FillColour ) ) {
         Assert.assertEquals( PENTAGON_FILL_COLOUR, pentagon.fillProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.HorizontalRadius ) ) {
         Assert.assertEquals( PENTAGON_HORIZONTAL_RADIUS, pentagon.horizontalRadiusProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Inversion ) ) {
         Assert.assertEquals( PENTAGON_INVERSION, pentagon.inversionProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.NumberOfFractals ) ) {
         Assert.assertEquals( PENTAGON_FRACTAL, pentagon.numberOfFractalsProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.NumberOfSides ) ) {
         Assert.assertEquals( PENTAGON_NUMBER_OF_SIDES, pentagon.numberOfSidesProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Rotation ) ) {
         Assert.assertEquals( PENTAGON_ROTATE, pentagon.rotateProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.StrokeColour ) ) {
         Assert.assertEquals( PENTAGON_STROKE_COLOUR, pentagon.strokeProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.StrokeWidth ) ) {
         Assert.assertEquals( PENTAGON_STROKE_WIDTH, pentagon.strokeWidthProperty().get(), TestCommon.precision() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.Type ) ) {
         Assert.assertEquals( PENTAGON_TYPE, pentagon.polygonTypeProperty().get() );
      }
      if ( !propertiesToExclude.contains( EllipticPolygonProperties.VerticalRadius ) ) {
         Assert.assertEquals( PENTAGON_VERTICAL_RADIUS, pentagon.verticalRadiusProperty().get(), TestCommon.precision() );
      }
   }//End Method

}//End Class
