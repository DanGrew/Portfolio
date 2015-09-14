/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import org.junit.Assert;
import org.junit.Test;

import utility.TestCommon;

/**
 * {@link ResizePoint} test.
 */
public class ResizePointTest {

   /**
    * Test that the centre position updates the horiontal radius.
    */
   @Test public void centreShouldUpdateHorizontalRadius() {
      EllipticPolygon polygon = new EllipticPolygon( 4, 100, 100 );
      ResizePoint point = new ResizePoint( polygon );
      
      Assert.assertEquals( polygon.getBoundsInLocal().getMaxX(), point.getCenterX(), TestCommon.precision() );
      
      point.setCenterX( 300 );
      Assert.assertEquals( 200, polygon.horizontalRadiusProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Test that the centre position updates the vertical radius.
    */
   @Test public void translateShouldUpdateHorizontalRadius() {
      EllipticPolygon polygon = new EllipticPolygon( 4, 100, 100 );
      ResizePoint point = new ResizePoint( polygon );
      
      Assert.assertEquals( polygon.getBoundsInLocal().getMaxX(), point.getCenterX(), TestCommon.precision() );
      
      point.setTranslateX( 300 );
      Assert.assertEquals( polygon.getBoundsInLocal().getMaxX(), point.getCenterX() + point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( polygon.horizontalRadiusProperty().get(), 400, TestCommon.precision() );
   }//End Method

}//End Class
