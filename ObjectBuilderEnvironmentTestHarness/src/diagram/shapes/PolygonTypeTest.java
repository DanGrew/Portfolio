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

/**
 * Test for the {@link PolygonType}.
 */
public class PolygonTypeTest {

   /**
    * {@link PolygonType#Regular} test for a triangle.
    */
   @Test public void shouldCreateRegularTriangle() {
      EllipticPolygon polygon = PolygonType.Regular.createPolygon( 
               3, 100, 100, EllipticPolygon.getDefaultRadius(), EllipticPolygon.getDefaultRadius() 
      );
      Assert.assertEquals( 3, polygon.getNumberOfSides() );
      Assert.assertEquals( 6, polygon.getPoints().size() );
   }//End Method
   
   /**
    * {@link PolygonType#Starred} test for a triangle.
    */
   @Test public void shouldCreateStarredTriangle() {
      EllipticPolygon polygon = PolygonType.Starred.createPolygon( 
               3, 100, 100, EllipticPolygon.getDefaultRadius(), EllipticPolygon.getDefaultRadius() 
      );
      Assert.assertEquals( 3, polygon.getNumberOfSides() );
      Assert.assertEquals( 12, polygon.getPoints().size() );
   }//End Method

}//End Class
