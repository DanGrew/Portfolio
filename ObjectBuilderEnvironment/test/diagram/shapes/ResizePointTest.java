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

import javafx.scene.shape.Ellipse;
import utility.TestCommon;

/**
 * {@link ResizePoint} test.
 */
public class ResizePointTest {

   /**
    * Test that the centre position updates the horizontal radius.
    */
   @Test public void centreShouldUpdateHorizontalRadius() {
      Ellipse polygon = new Ellipse( 100, 100, 4, 4 );
      ResizePoint point = new ResizePoint( polygon );
      
      Assert.assertEquals( polygon.getBoundsInLocal().getMaxX(), point.getCenterX(), TestCommon.precision() );
      
      point.setCenterX( 300 );
      Assert.assertEquals( 200, polygon.radiusXProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Test that the centre position updates the vertical radius.
    */
   @Test public void translateShouldUpdateHorizontalRadius() {
      Ellipse polygon = new Ellipse( 100, 100, 4, 4 );
      ResizePoint point = new ResizePoint( polygon );
      
      Assert.assertEquals( polygon.getBoundsInLocal().getMaxX(), point.getCenterX(), TestCommon.precision() );
      
      point.setTranslateX( 300 );
      Assert.assertEquals( polygon.getBoundsInLocal().getMaxX(), point.getCenterX() + point.getTranslateX(), TestCommon.precision() );
      Assert.assertEquals( 304, polygon.radiusXProperty().get(), TestCommon.precision() );
   }//End Method

}//End Class
