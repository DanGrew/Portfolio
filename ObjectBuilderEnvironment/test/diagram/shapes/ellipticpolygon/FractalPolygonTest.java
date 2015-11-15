/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes.ellipticpolygon;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;
import javafx.geometry.Point2D;
import utility.TestCommon;

/**
 * Test for the {@link FractalPolygon} shaping.
 */
public class FractalPolygonTest {

   /**
    * Test for adding a fractal to an increasing horizontal line.
    */
   @Test public void shouldCalculateSquareFractalPositiveHorizontal() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 4, new Point2D( 200, 200 ), new Point2D( 500, 200 ) );
      
      Assert.assertEquals( 12, points.size() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 100, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 100, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 500, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * Test for adding a fractal to a decreasing horizontal line.
    */
   @Test public void shouldCalculateSquareFractalNegativeHorizontal() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 4, new Point2D( 500, 200 ), new Point2D( 200, 200 ) );
      
      Assert.assertEquals( 12, points.size() );
      Assert.assertEquals( 500, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * Test for adding a fractal to an increasing vertical line.
    */
   @Test public void shouldCalculateSquareFractalPositiveVertical() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 4, new Point2D( 200, 200 ), new Point2D( 200, 500 ) );
      
      Assert.assertEquals( 12, points.size() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 500, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * Test for adding a fractal to a decreasing vertical line.
    */
   @Test public void shouldCalculateSquareFractalNegativeVertical() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 4, new Point2D( 200, 500 ), new Point2D( 200, 200 ) );
      
      Assert.assertEquals( 12, points.size() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 500, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 100, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 100, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * Test for adding a fractal to a line moving in the south east direction.
    */
   @Test public void shouldCalculateSquareSouthEastFractal() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 4, new Point2D( 0, 0 ), new Point2D( 1200, 900 ) );
      
      Assert.assertEquals( 12, points.size() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      
      points.remove( 0 );
      points.remove( 0 );
      points.remove( 0 );
      points.remove( 0 );
      
      Assert.assertEquals( 800, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 600, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 1200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 900, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * Test for adding a fractal to a line moving in the south west direction.
    */
   @Test public void shouldCalculateSquareSouthWestFractal() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 4, new Point2D( 0, 0 ), new Point2D( -1200, 900 ) );
      
      Assert.assertEquals( 12, points.size() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( -400, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      
      points.remove( 0 );
      points.remove( 0 );
      points.remove( 0 );
      points.remove( 0 );
      
      Assert.assertEquals( -800, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 600, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( -1200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 900, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * Test for adding a fractal to a line moving in the north east direction.
    */
   @Test public void shouldCalculateSquareNorthEastFractal() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 4, new Point2D( 0, 0 ), new Point2D( 1200, -900 ) );
      
      Assert.assertEquals( 12, points.size() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 400, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( -300, points.remove( 0 ), TestCommon.precision() );
      
      points.remove( 0 );
      points.remove( 0 );
      points.remove( 0 );
      points.remove( 0 );
      
      Assert.assertEquals( 800, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( -600, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 1200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( -900, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * Test for adding a fractal to a line moving in the north west direction.
    */
   @Test public void shouldCalculateSquareNorthWestFractal() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 4, new Point2D( 0, 0 ), new Point2D( -1200, -900 ) );
      
      Assert.assertEquals( 12, points.size() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( -400, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( -300, points.remove( 0 ), TestCommon.precision() );
      
      points.remove( 0 );
      points.remove( 0 );
      points.remove( 0 );
      points.remove( 0 );
      
      Assert.assertEquals( -800, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( -600, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( -1200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( -900, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * Test for adding a fractal to a line for a triangle.
    */
   @Test public void shouldCalculateTriangleFractalPositiveHorizontal() {
      List< Double > points = EllipticPolygonCalculations.calculateFractal( 3, new Point2D( 0, 0 ), new Point2D( 300, 0 ) );
      
      Assert.assertEquals( 10, points.size() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 100, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      
      points.remove( 0 );
      points.remove( 0 );
      
      Assert.assertEquals( 200, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      
      Assert.assertEquals( 300, points.remove( 0 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.remove( 0 ), TestCommon.precision() );
      Assert.assertTrue( points.isEmpty() );
   }//End Method
   
   /**
    * {@link EllipticPolygon#calculateFractals()} test.
    */
   @Test public void shouldApplyFractal(){
      EllipticPolygon polygon = new EllipticPolygon( 
            new EllipticPolygonBuilder( PolygonType.Fractal, 4 )
               .horizontalRadiusProperty( 100 )
               .verticalRadiusProperty( 100 )
               .numberOfFractals( 1 )
      );               
      List< Double > calculatedPoints = EllipticPolygonCalculations.calculateFractals( polygon );
      Assert.assertEquals( 48, calculatedPoints.size() );
      
      polygon.numberOfFractalsProperty().set( 2 );
      calculatedPoints = EllipticPolygonCalculations.calculateFractals( polygon );
      Assert.assertEquals( 276, calculatedPoints.size() );
      
      polygon.numberOfFractalsProperty().set( 3 );
      calculatedPoints = EllipticPolygonCalculations.calculateFractals( polygon );
      Assert.assertEquals( 1644, calculatedPoints.size() );
      
      polygon.numberOfFractalsProperty().set( 4 );
      calculatedPoints = EllipticPolygonCalculations.calculateFractals( polygon );
      Assert.assertEquals( 9852, calculatedPoints.size() );
   }//End Method

}//End Class
