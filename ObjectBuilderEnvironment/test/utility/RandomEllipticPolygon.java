/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package utility;

import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonBuilder;

/**
 * The {@link RandomEllipticPolygon} provides a method of creating an {@link EllipticPolygon}
 * with random property values.
 */
public class RandomEllipticPolygon {

   /**
    * Method to create an {@link EllipticPolygon} with random property values, within realistic
    * ranges.
    * @return the created {@link EllipticPolygon}.
    */
   public static EllipticPolygon create(){
      return new EllipticPolygon( 
               new EllipticPolygonBuilder( 
                        randomPropertyType(), 
                        TestCommon.randomInt( 3, 10 ) 
               )
               .centreXProperty( TestCommon.randomDouble( 0, 1000 ) )
               .centreYProperty( TestCommon.randomDouble( 0, 1000 ) )
               .horizontalRadiusProperty( TestCommon.random( 10, 200 ) )
               .verticalRadiusProperty( TestCommon.random( 10, 200 ) )
               .inversionProperty( randomBoolean() )
               .numberOfFractals( TestCommon.randomInt( 0, 3 ) )
               .rotateProperty( TestCommon.randomDouble( -180, 180 ) )
      );
   }//End Method
   
   /**
    * Method to generate a random {@link PolygonType}.
    * @return a {@link PolygonType} at random.
    */
   private static PolygonType randomPropertyType(){
      int random = TestCommon.randomInt( 1, 3 );
      switch ( random ) {
         case 1:
            return PolygonType.Regular;
         case 2:
            return PolygonType.Starred;
         case 3: 
            return PolygonType.Fractal;
         default:
            return null;
      }
   }//End Method
   
   /**
    * Method to generate a random boolean.
    * @return a random true or false.
    */
   private static Boolean randomBoolean(){
      int random = TestCommon.randomInt( 1, 2 );
      switch ( random ) {
         case 1:
            return true;
         case 2:
            return false;
         default:
            return null;
      }
   }//End Method
   
}//End Class
