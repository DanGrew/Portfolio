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
 * Test for the {@link SidedPolygon}.
 */
public class SidedPolygonTest {
   
   /**
    * Method to test that {@link SidedPolygon#calculatePointOnCircle(double, double, double, double, double)}
    * functions correctly.
    */
   @Test public void shouldCalculatePointOnCircle(){
      Assert.assertEquals( 
               new Point2D( 200, 100 ), 
               SidedPolygon.calculatePointOnCircle( 100, 100, 100, 100, 0 ) 
      );
      Assert.assertEquals( 
               100.0, 
               SidedPolygon.calculatePointOnCircle( 100, 100, 100, 100, 90 ).getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               200.0, 
               SidedPolygon.calculatePointOnCircle( 100, 100, 100, 100, 90 ).getY(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               0.0, 
               SidedPolygon.calculatePointOnCircle( 100, 100, 100, 100, 180 ).getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               100.0, 
               SidedPolygon.calculatePointOnCircle( 100, 100, 100, 100, 180 ).getY(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               100.0, 
               SidedPolygon.calculatePointOnCircle( 100, 100, 100, 100, 270 ).getX(),
               TestCommon.precision()
      );
      Assert.assertEquals( 
               0.0, 
               SidedPolygon.calculatePointOnCircle( 100, 100, 100, 100, 270 ).getY(),
               TestCommon.precision()
      );
   }//End Method
   
   /**
    * {@link SidedPolygon} with 3 sides test.
    */
   @Test public void shouldCreateWithThreeSides(){
      SidedPolygon triangle = new SidedPolygon( 3, 100, 100 );
      
      Assert.assertEquals( 100, SidedPolygon.getDefaultRadius(), TestCommon.precision() );
      
      List< Double > points = triangle.getPoints();
      Assert.assertEquals( 6, points.size() );
   }//End Method
   
   /**
    * Test for calculating the points following the change of the horizontal radius.
    */
   @Test public void shouldHorizontalStretchPoints(){
      SidedPolygon rectangle = new SidedPolygon( 4, 100, 100 );
      
      Assert.assertEquals( 100, SidedPolygon.getDefaultRadius(), TestCommon.precision() );
      
      List< Double > points = rectangle.getPoints();
      Assert.assertEquals( 8, points.size() );
      
      Assert.assertEquals( 200, points.get( 0 ), TestCommon.precision() );
      Assert.assertEquals( 100, points.get( 1 ), TestCommon.precision() );
      
      rectangle.horizontalRadiusProperty().set( 200 );
      Assert.assertEquals( 8, points.size() );
      
      Assert.assertEquals( 300, points.get( 0 ), TestCommon.precision() );
      Assert.assertEquals( 100, points.get( 1 ), TestCommon.precision() );
   }//End Method
   
   /**
    * Test for calculating the points following the change of the vertical radius.
    */
   @Test public void shouldVerticalStretchPoints(){
      SidedPolygon rectangle = new SidedPolygon( 4, 100, 100 );
      
      Assert.assertEquals( 100, SidedPolygon.getDefaultRadius(), TestCommon.precision() );
      
      List< Double > points = rectangle.getPoints();
      Assert.assertEquals( 8, points.size() );
      
      Assert.assertEquals( 200, points.get( 0 ), TestCommon.precision() );
      Assert.assertEquals( 100, points.get( 1 ), TestCommon.precision() );
      
      rectangle.verticalRadiusProperty().set( 300 );
      Assert.assertEquals( 8, points.size() );
      
      Assert.assertEquals( 100, points.get( 2 ), TestCommon.precision() );
      Assert.assertEquals( 400, points.get( 3 ), TestCommon.precision() );
   }//End Method

}//End Class
