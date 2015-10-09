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
 * Test for the {@link StarredPolygon}.
 */
public class StarredPolygonTest {

   /**
    * {@link StarredPolygon#numberOfSidesProperty()} test.
    */
   @Test public void shouldRecalculateNumberOfSides() {
      EllipticPolygon polygon = new EllipticPolygon( 
            new EllipticPolygonBuilder( PolygonType.Starred, 4 )
               .horizontalRadiusProperty( 50 )
               .verticalRadiusProperty( 50 )
      );
      Assert.assertEquals( 16, polygon.getPoints().size() );
      
      polygon.numberOfSidesProperty().set( 5 );
      Assert.assertEquals( 20, polygon.getPoints().size() );
   }//End Method

}//End Class
