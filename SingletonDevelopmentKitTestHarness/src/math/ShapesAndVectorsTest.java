/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package math;

import org.junit.Assert;
import org.junit.Test;

import javafx.geometry.Point2D;
import utility.TestCommon;

/**
 * {@link ShapesAndVectors} Test.
 */
public class ShapesAndVectorsTest {

   /**
    * {@link ShapesAndVectors#getAngleOfLineBetweenTwoPoints(Point2D, Point2D)} test.
    */
   @Test public void shouldCalculateAngles() {
      Assert.assertEquals( 
               0, 
               ShapesAndVectors.getAngleOfLineBetweenTwoPoints( 
                        new Point2D( 200, 200 ), 
                        new Point2D( 500, 200 )
               ),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               180, 
               ShapesAndVectors.getAngleOfLineBetweenTwoPoints( 
                        new Point2D( 200, 200 ), 
                        new Point2D( 100, 200 )
               ),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               90, 
               ShapesAndVectors.getAngleOfLineBetweenTwoPoints( 
                        new Point2D( 200, 200 ), 
                        new Point2D( 200, 500 )
               ),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               270, 
               ShapesAndVectors.getAngleOfLineBetweenTwoPoints( 
                        new Point2D( 200, 500 ), 
                        new Point2D( 200, 200 )
               ),
               TestCommon.precision()
      );
   }//End Method
   
   /**
    * {@link ShapesAndVectors#extendVectorBy(double, Point2D, Point2D)} test.
    */
   @Test public void shouldExtendVector(){
      Point2D result = ShapesAndVectors.extendVectorBy( 500, new Point2D( 0, 0 ), new Point2D( 300, 400 ) );
      Assert.assertEquals( 600, result.getX(), TestCommon.precision() );
      Assert.assertEquals( 800, result.getY(), TestCommon.precision() );
      
      result = ShapesAndVectors.extendVectorBy( 500, new Point2D( 0, 0 ), new Point2D( 400, 300 ) );
      Assert.assertEquals( 800, result.getX(), TestCommon.precision() );
      Assert.assertEquals( 600, result.getY(), TestCommon.precision() );
      
      result = ShapesAndVectors.extendVectorBy( 300, new Point2D( 0, 0 ), new Point2D( 300, 0 ) );
      Assert.assertEquals( 600, result.getX(), TestCommon.precision() );
      Assert.assertEquals( 0,   result.getY(), TestCommon.precision() );
      
      result = ShapesAndVectors.extendVectorBy( 300, new Point2D( 300, 0 ), new Point2D( 0, 0 ) );
      Assert.assertEquals( -300, result.getX(), TestCommon.precision() );
      Assert.assertEquals( 0,    result.getY(), TestCommon.precision() );
      
      result = ShapesAndVectors.extendVectorBy( 300, new Point2D( 0, 0 ), new Point2D( 0, 300 ) );
      Assert.assertEquals( 0,   result.getX(), TestCommon.precision() );
      Assert.assertEquals( 600, result.getY(), TestCommon.precision() );
      
      result = ShapesAndVectors.extendVectorBy( 300, new Point2D( 0, 300 ), new Point2D( 0, 0 ) );
      Assert.assertEquals( 0,    result.getX(), TestCommon.precision() );
      Assert.assertEquals( -300, result.getY(), TestCommon.precision() );
   }//End Method
   
   /**
    * {@link ShapesAndVectors#rotateAsVector(Point2D, double)} test.
    */
   @Test public void shouldRotateVector(){
      Point2D result = ShapesAndVectors.rotateAsVector( new Point2D( 1, 1 ), 90 );
      Assert.assertEquals( -1, result.getX(), TestCommon.precision() );
      Assert.assertEquals( 1, result.getY(), TestCommon.precision() );
      
      result = ShapesAndVectors.rotateAsVector( new Point2D( -1, -1 ), 180 );
      Assert.assertEquals( 1, result.getX(), TestCommon.precision() );
      Assert.assertEquals( 1, result.getY(), TestCommon.precision() );
   }//End Method
   
   /**
    * {@link ShapesAndVectors#calculateLengthOfSideForRightAngleTriangle(double, double)} test.
    */
   @Test public void shouldCalculateLengthOfRightAngleTriangle(){
      Assert.assertEquals( 4, ShapesAndVectors.calculateLengthOfSideForRightAngleTriangle( 3, 5 ), TestCommon.precision() );
   }//End Method
   
   /**
    * {@link ShapesAndVectors#calculateRadiusOfRegularPolygon(int, double)} test.
    */
   @Test public void shouldCalculateRadiusOfPolygon(){
      Assert.assertEquals( 70.71067811, ShapesAndVectors.calculateRadiusOfRegularPolygon( 4, 100 ), TestCommon.precision() );
      Assert.assertEquals( 57.73502691, ShapesAndVectors.calculateRadiusOfRegularPolygon( 3, 100 ), TestCommon.precision() );
   }//End Method

}//End Class
