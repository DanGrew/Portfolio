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

import utility.TestCommon;

/**
 * Test for the {@link EllipticPolygon}.
 */
public class EllipticPolygonTest {
   
   /**
    * {@link EllipticPolygon} with 3 sides test.
    */
   @Test public void shouldCreateWithThreeSides(){
      EllipticPolygon triangle = new EllipticPolygon( 
            new EllipticPolygonBuilder( PolygonType.Regular, 3 )
               .horizontalRadiusProperty( 100 )
               .verticalRadiusProperty( 100 )
      );
      
      Assert.assertEquals( 100, EllipticPolygon.getDefaultRadius(), TestCommon.precision() );
      
      List< Double > points = triangle.getPoints();
      Assert.assertEquals( 6, points.size() );
   }//End Method
   
   /**
    * Test for calculating the points following the change of the horizontal radius.
    */
   @Test public void shouldHorizontalStretchPoints(){
      EllipticPolygon rectangle = new EllipticPolygon(
            new EllipticPolygonBuilder( PolygonType.Regular, 4 )
               .centreXProperty( 100 )
               .centreYProperty( 100 )
               .horizontalRadiusProperty( 100 )
               .verticalRadiusProperty( 100 )
      );
      
      Assert.assertEquals( 100, EllipticPolygon.getDefaultRadius(), TestCommon.precision() );
      
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
      EllipticPolygon rectangle = new EllipticPolygon(
            new EllipticPolygonBuilder( PolygonType.Regular, 4 )
               .centreXProperty( 100 )
               .centreYProperty( 100 )
               .horizontalRadiusProperty( 100 )
               .verticalRadiusProperty( 100 )
      );
      
      Assert.assertEquals( 100, EllipticPolygon.getDefaultRadius(), TestCommon.precision() );
      
      List< Double > points = rectangle.getPoints();
      Assert.assertEquals( 8, points.size() );
      
      Assert.assertEquals( 200, points.get( 0 ), TestCommon.precision() );
      Assert.assertEquals( 100, points.get( 1 ), TestCommon.precision() );
      
      rectangle.verticalRadiusProperty().set( 300 );
      Assert.assertEquals( 8, points.size() );
      
      Assert.assertEquals( 100, points.get( 2 ), TestCommon.precision() );
      Assert.assertEquals( 400, points.get( 3 ), TestCommon.precision() );
   }//End Method
   
   /**
    * {@link EllipticPolygon#calculateSidePoints(EllipticPolygon, double)} test.
    */
   @Test public void shouldCalculatePolygonPoints(){
      EllipticPolygon polygon = new EllipticPolygon( 
            new EllipticPolygonBuilder( PolygonType.Regular, 4 )
               .horizontalRadiusProperty( 100 )
               .verticalRadiusProperty( 100 )
      );
      List< Double > points = polygon.calculateSidePoints( polygon, 90 );
      
      Assert.assertEquals( 8, points.size() );
      Assert.assertEquals( 0, points.get( 0 ), TestCommon.precision() );
      Assert.assertEquals( 100, points.get( 1 ), TestCommon.precision() );
      Assert.assertEquals( -100, points.get( 2 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.get( 3 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.get( 4 ), TestCommon.precision() );
      Assert.assertEquals( -100, points.get( 5 ), TestCommon.precision() );
      Assert.assertEquals( 100, points.get( 6 ), TestCommon.precision() );
      Assert.assertEquals( 0, points.get( 7 ), TestCommon.precision() );
   }//End Method
   
   /**
    * {@link EllipticPolygon#numberOfSidesProperty()} modifying test.
    */
   @Test public void shouldRecalculatePointsOnSideNumberChange(){
      EllipticPolygon triangle = new EllipticPolygon(
            new EllipticPolygonBuilder( PolygonType.Regular, 3 )
               .horizontalRadiusProperty( 100 )
               .verticalRadiusProperty( 100 )
      );
      
      List< Double > points = triangle.getPoints();
      Assert.assertEquals( 6, points.size() );
      
      triangle.numberOfSidesProperty().set( 4 );
      points = triangle.getPoints();
      Assert.assertEquals( 8, points.size() );
      
      triangle.numberOfSidesProperty().set( 9 );
      points = triangle.getPoints();
      Assert.assertEquals( 18, points.size() );
   }//End Method

}//End Class
