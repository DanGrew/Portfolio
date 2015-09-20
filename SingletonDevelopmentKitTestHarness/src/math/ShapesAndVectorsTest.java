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
    * Method to test that {@link ShapesAndVectors#calculatePointOnCircle(double, double, double, double, double)}
    * functions correctly.
    */
   @Test public void shouldCalculatePointOnCircle(){
      Assert.assertEquals( 
               new Point2D( 200, 100 ), 
               ShapesAndVectors.calculatePointOnCircle( 100, 100, 100, 100, 0 ) 
      );
      Assert.assertEquals( 
               100.0, 
               ShapesAndVectors.calculatePointOnCircle( 100, 100, 100, 100, 90 ).getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               200.0, 
               ShapesAndVectors.calculatePointOnCircle( 100, 100, 100, 100, 90 ).getY(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               0.0, 
               ShapesAndVectors.calculatePointOnCircle( 100, 100, 100, 100, 180 ).getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               100.0, 
               ShapesAndVectors.calculatePointOnCircle( 100, 100, 100, 100, 180 ).getY(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               100.0, 
               ShapesAndVectors.calculatePointOnCircle( 100, 100, 100, 100, 270 ).getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               0.0, 
               ShapesAndVectors.calculatePointOnCircle( 100, 100, 100, 100, 270 ).getY(),
               TestCommon.precision()
      );

      Point2D point = ShapesAndVectors.calculatePointOnCircle( 0, 0, 100, 100, 0 ); 
      Assert.assertEquals( 
               100,
               point.getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               0,
               point.getY(),
               TestCommon.precision()
      );
      
      point = ShapesAndVectors.calculatePointOnCircle( 0, 0, 100, 100, 90 ); 
      Assert.assertEquals( 
               0,
               point.getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               100,
               point.getY(),
               TestCommon.precision()
      );
      
      point = ShapesAndVectors.calculatePointOnCircle( 0, 0, 100, 100, 180 ); 
      Assert.assertEquals( 
               -100,
               point.getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               0,
               point.getY(),
               TestCommon.precision()
      );
   }
   
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
