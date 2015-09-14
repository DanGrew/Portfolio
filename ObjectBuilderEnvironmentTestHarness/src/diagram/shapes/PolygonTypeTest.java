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
      SidedPolygon polygon = PolygonType.Regular.createPolygon( 3, 100, 100 );
      Assert.assertEquals( 3, polygon.getNumberOfSides() );
      Assert.assertEquals( 6, polygon.getPoints().size() );
   }//End Method
   
   /**
    * {@link PolygonType#Starred} test for a triangle.
    */
   @Test public void shouldCreateStarredTriangle() {
      SidedPolygon polygon = PolygonType.Starred.createPolygon( 3, 100, 100 );
      Assert.assertEquals( 3, polygon.getNumberOfSides() );
      Assert.assertEquals( 12, polygon.getPoints().size() );
   }//End Method

}//End Class
