/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.shapes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Polygon;

/**
 * The {@link MultiLayeredPolygon} is an extension of {@link SidedPolygon} with a sub {@link SidedPolygon}
 * that provides intermediate points along the sides of the {@link SidedPolygon}.
 */
public class MultiLayeredPolygon extends SidedPolygon{
   
   private SidedPolygon innerPolygon;

   /**
    * Constructs a new {@link MultiLayeredPolygon}.
    * @param numberOfSides the number of sides in the {@link Polygon}.
    * @param centrePositionX the centre x of the {@link Polygon}.
    * @param centrePositionY the centre y of the {@link Polygon}.
    */
   public MultiLayeredPolygon( SidedPolygon polygon, double centrePositionX, double centrePositionY ) {
      this( polygon, centrePositionX, centrePositionY, SidedPolygon.getDefaultRadius(), SidedPolygon.getDefaultRadius() );
   }//End Constructor
   
   /**
    * Constructs a new {@link MultiLayeredPolygon}.
    * @param numberOfSides the number of sides in the {@link Polygon}.
    * @param centrePositionX the centre x of the {@link Polygon}.
    * @param centrePositionY the centre y of the {@link Polygon}.
    * @param horizontalRadius the horizontal radius.
    * @param verticalRadius the vertical radius.
    */
   public MultiLayeredPolygon( 
            SidedPolygon polygon, 
            double centrePositionX, 
            double centrePositionY, 
            double horizontalRadius, 
            double verticalRadius 
   ) {
      super( polygon.getNumberOfSides(), centrePositionX, centrePositionY, horizontalRadius, verticalRadius, false );
      this.innerPolygon = polygon;
      calculatePoints();
   }//End Constructor
   
   /**
    * Method to provide the calculation for the points in the {@link Polygon}.
    */
   protected void calculatePoints(){
      getPoints().clear();
      getPoints().addAll( calculateWithInnerPolygon() );
   }//End Method
   
   /**
    * Method to calculate the points in the {@link MultiLayeredPolygon} accounting for the inner {@link SidedPolygon}.
    * @return a {@link List} of combined {@link Double} points that make up the {@link Polygon}.
    */
   protected final List< Double > calculateWithInnerPolygon(){
      List< Double > combinedPoints = new ArrayList<>();
      List< Double > primaryPoints = calculateSidePoints( this, 0 );
      List< Double > secondaryPoints = calculateSidePoints( innerPolygon, 360.0 / getNumberOfSides() / 2 );
      
      for ( int i = 0; i < getNumberOfSides(); i++ ) {
         combinedPoints.add( primaryPoints.remove( 0 ) );
         combinedPoints.add( primaryPoints.remove( 0 ) );
         combinedPoints.add( secondaryPoints.remove( 0 ) );
         combinedPoints.add( secondaryPoints.remove( 0 ) );
      }
      return combinedPoints;
   }//End Method
   
}//End Class
