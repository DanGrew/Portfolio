/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.ButtonItemImpl;
import diagram.controls.GridItemSelection;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonGraphics;
import javafx.beans.binding.Bindings;

/**
 * {@link NumberOfSidesItems} provides a custom extension of {@link GridItemSelection} to 
 * define the options for configuring the number of sides on an {@link EllipticPolygon}.
 */
public class NumberOfSidesItems extends GridItemSelection {

   public NumberOfSidesItems( EllipticPolygon polygon ) {
      populateGrid( 
               4, 2,
               new ButtonItemImpl( 
                        "3 Sides", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getTriangle() ), 
                        () -> polygon.numberOfSidesProperty().set( 3 ) 
               ),
               new ButtonItemImpl( 
                        "4 Sides", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getDiamond() ), 
                        () -> polygon.numberOfSidesProperty().set( 4 ) 
               ),
               new ButtonItemImpl( 
                        "5 Sides", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getPentagon() ), 
                        () -> polygon.numberOfSidesProperty().set( 5 ) 
               ),
               new ButtonItemImpl( 
                        "6 Sides", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getHexagon() ), 
                        () -> polygon.numberOfSidesProperty().set( 6 ) 
               ),
               new ButtonItemImpl( 
                        "7 Sides", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getHeptagon() ), 
                        () -> polygon.numberOfSidesProperty().set( 7 ) 
               ),
               new ButtonItemImpl( 
                        "8 Sides", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getOctagon() ), 
                        () -> polygon.numberOfSidesProperty().set( 8 ) 
               ),
               new ButtonItemImpl( 
                        "9 Sides", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getNonagon() ), 
                        () -> polygon.numberOfSidesProperty().set( 9 ) 
               ),
               new ButtonItemImpl( 
                        "10 Sides", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getDecagon() ), 
                        () -> polygon.numberOfSidesProperty().set( 10 ) 
               )
      );
   }//End Constructor
   
   /**
    * Method to bind the configurable {@link EllipticPolygon} to the graphical representation of the {@link EllipticPolygon}.
    * @param configured the {@link EllipticPolygon} being configured.
    * @param graphic the {@link EllipticPolygon} being shown on the {@link Button}.
    * @return the {@link EllipticPolygon} graphic.
    */
   private static EllipticPolygon preparePolygon( EllipticPolygon configured, EllipticPolygon graphic ) {
      Bindings.bindBidirectional( graphic.polygonTypeProperty(), configured.polygonTypeProperty() );
      Bindings.bindBidirectional( configured.rotateProperty(), graphic.rotateProperty() );
      Bindings.bindBidirectional( configured.inversionProperty(), graphic.inversionProperty() );
      Bindings.bindBidirectional( configured.numberOfFractalsProperty(), graphic.numberOfFractalsProperty() );
      return graphic;
   }//End Method
   
}//End Class
