/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

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
      List< Double > points = FractalPolygon.calculateFractal( 4, new Point2D( 200, 200 ), new Point2D( 500, 200 ) );
      
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
      List< Double > points = FractalPolygon.calculateFractal( 4, new Point2D( 500, 200 ), new Point2D( 200, 200 ) );
      
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
      List< Double > points = FractalPolygon.calculateFractal( 4, new Point2D( 200, 200 ), new Point2D( 200, 500 ) );
      
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
      List< Double > points = FractalPolygon.calculateFractal( 4, new Point2D( 200, 500 ), new Point2D( 200, 200 ) );
      
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
      List< Double > points = FractalPolygon.calculateFractal( 4, new Point2D( 0, 0 ), new Point2D( 1200, 900 ) );
      
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
      List< Double > points = FractalPolygon.calculateFractal( 4, new Point2D( 0, 0 ), new Point2D( -1200, 900 ) );
      
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
      List< Double > points = FractalPolygon.calculateFractal( 4, new Point2D( 0, 0 ), new Point2D( 1200, -900 ) );
      
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
      List< Double > points = FractalPolygon.calculateFractal( 4, new Point2D( 0, 0 ), new Point2D( -1200, -900 ) );
      
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
      List< Double > points = FractalPolygon.calculateFractal( 3, new Point2D( 0, 0 ), new Point2D( 300, 0 ) );
      
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

}//End Class
