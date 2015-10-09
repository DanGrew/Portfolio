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
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import diagram.shapes.ellipticpolygon.EllipticPolygonGraphics;
import javafx.beans.binding.Bindings;

/**
 * {@link GridItemSelection} for the items to configure the {@link PolygonType} of an
 * {@link EllipticPolygon}.
 */
public class PolygonTypeItems extends GridItemSelection {

   /**
    * Constructs a new {@link PolygonTypeItems}.
    * @param polygon the {@link EllipticPolygon} to configure.
    */
   public PolygonTypeItems( EllipticPolygon polygon ) {
      super( 
               2, 2,
               new ButtonItemImpl( 
                        "Regular", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getPolygon( polygon.getNumberOfSides(), PolygonType.Regular ) ), 
                        () -> polygon.polygonTypeProperty().set( PolygonType.Regular ) 
               ),
               new ButtonItemImpl( 
                        "Starred", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getPolygon( polygon.getNumberOfSides(), PolygonType.Starred ) ), 
                        () -> polygon.polygonTypeProperty().set( PolygonType.Starred ) 
               ),
               new ButtonItemImpl( 
                        "Fractal", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getPolygon( polygon.getNumberOfSides(), PolygonType.Fractal ) ), 
                        () -> polygon.polygonTypeProperty().set( PolygonType.Fractal ) 
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
      Bindings.bindBidirectional( configured.numberOfSidesProperty(), graphic.numberOfSidesProperty() );
      Bindings.bindBidirectional( configured.rotateProperty(), graphic.rotateProperty() );
      Bindings.bindBidirectional( configured.inversionProperty(), graphic.inversionProperty() );
      Bindings.bindBidirectional( configured.numberOfFractalsProperty(), graphic.numberOfFractalsProperty() );
      return graphic;
   }//End Method

}//End Class
