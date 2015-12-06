/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes.ellipticpolygon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import diagram.shapes.PolygonType;
import utility.TestCommon;

/**
 * {@link EllipticPolygon} test for using {@link EllipticPolygonBuilder}.
 */
public class EllipticPolygonBuilderTest {
   
   private static final int DEFAULT_ROTATE = 189;
   private static final int DEFAULT_NUMBER_OF_SIDES = 4;
   private static final int DEFAULT_FRACTAL = 1;
   private static final boolean DEFAULT_INVERSION = false;
   private static final int DEFAULT_VERTICAL_RADIUS = 564;
   private static final int DEFAULT_HORIZONTAL_RADIUS = 876;
   private static final int DEFAULT_CENTRE_Y = 203;
   private static final int DEFAULT_CENTRE_X = 101;
   private EllipticPolygon systemUnderTest;

   /**
    * Method to initialise the system under test.
    */
   @Before public void initialiseSystemUnderTest(){
      systemUnderTest = new EllipticPolygon( 
               new EllipticPolygonBuilder( PolygonType.Starred, DEFAULT_NUMBER_OF_SIDES )
                  .centreXProperty( DEFAULT_CENTRE_X )
                  .centreYProperty( DEFAULT_CENTRE_Y )
                  .horizontalRadiusProperty( DEFAULT_HORIZONTAL_RADIUS )
                  .verticalRadiusProperty( DEFAULT_VERTICAL_RADIUS )
                  .inversionProperty( DEFAULT_INVERSION )
                  .numberOfFractals( DEFAULT_FRACTAL )
                  .rotateProperty( DEFAULT_ROTATE )
      );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonBuilder#getNumberOfSidesProperty()} is used.
    */
   @Test public void shouldTakeNumberOfSides(){
      Assert.assertEquals( DEFAULT_NUMBER_OF_SIDES, systemUnderTest.numberOfSidesProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonBuilder#getCentreXProperty()} is used.
    */
   @Test public void shouldTakeCentreX(){
      Assert.assertEquals( DEFAULT_CENTRE_X, systemUnderTest.centreXProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonBuilder#getCentreYProperty()} is used.
    */
   @Test public void shouldTakeCentreY(){
      Assert.assertEquals( DEFAULT_CENTRE_Y, systemUnderTest.centreYProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonBuilder#getHorizontalRadiusProperty()} is used.
    */
   @Test public void shouldTakeHorizontalRadius(){
      Assert.assertEquals( DEFAULT_HORIZONTAL_RADIUS, systemUnderTest.horizontalRadiusProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonBuilder#getVerticalRadiusProperty()} is used.
    */
   @Test public void shouldTakeVerticalRadius(){
      Assert.assertEquals( DEFAULT_VERTICAL_RADIUS, systemUnderTest.verticalRadiusProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonBuilder#getRotateProperty()} is used.
    */
   @Test public void shouldTakeRotation(){
      Assert.assertEquals( DEFAULT_ROTATE, systemUnderTest.rotateProperty().get(), TestCommon.precision() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonBuilder#isInversionProperty()} is used.
    */
   @Test public void shouldTakeInversion(){
      Assert.assertEquals( DEFAULT_INVERSION, systemUnderTest.inversionProperty().get() );
   }//End Method
   
   /**
    * Prove that {@link EllipticPolygonBuilder#getNumberOfFractals()} is used.
    */
   @Test public void shouldTakeNumberOfFractals(){
      Assert.assertEquals( DEFAULT_FRACTAL, systemUnderTest.numberOfFractalsProperty().get() );  
   }//End Method

}//End Class
