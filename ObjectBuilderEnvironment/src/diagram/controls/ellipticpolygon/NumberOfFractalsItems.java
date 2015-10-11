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
 * {@link NumberOfFractalsItems} provides a custom extension of {@link GridItemSelection} to 
 * define the options for configuring the number of fractals on an {@link EllipticPolygon}.
 */
public class NumberOfFractalsItems extends GridItemSelection {

   /**
    * Constructs a new {@link NumberOfFractalsItems}.
    * @param polygon the {@link EllipticPolygon} to configure.
    */
   public NumberOfFractalsItems( EllipticPolygon polygon ) {
      super( 
               2, 2,
               new ButtonItemImpl( 
                        "0 Fractals", 
                        preparePolygon( polygon, prepareFractalPolygon( polygon, 0 ) ), 
                        () -> polygon.numberOfFractalsProperty().set( 0 ) 
               ),
               new ButtonItemImpl( 
                        "1 Fractal", 
                        preparePolygon( polygon, prepareFractalPolygon( polygon, 1 ) ), 
                        () -> polygon.numberOfFractalsProperty().set( 1 ) 
               ),
               new ButtonItemImpl( 
                        "2 Fractals", 
                        preparePolygon( polygon, prepareFractalPolygon( polygon, 2 ) ), 
                        () -> polygon.numberOfFractalsProperty().set( 2 ) 
               ),
               new ButtonItemImpl( 
                        "3 Fractals", 
                        preparePolygon( polygon, prepareFractalPolygon( polygon, 3 ) ), 
                        () -> polygon.numberOfFractalsProperty().set( 3 ) 
               )
      );
   }//End Constructor

   /**
    * Convenience method to prepare an {@link EllipticPolygon} that has a certain number of fractals.
    * @param polygon the {@link EllipticPolygon} to mimic.
    * @return an {@link EllipticPolygon} that has fractals applied.
    */
   private static EllipticPolygon prepareFractalPolygon( EllipticPolygon polygon, int fractals ) {
      EllipticPolygon fractal = EllipticPolygonGraphics.getPolygon( polygon );
      fractal.numberOfFractalsProperty().set( fractals );
      return fractal;
   }//End Method
   
   /**
    * Method to bind the configurable {@link EllipticPolygon} to the graphical representation of the {@link EllipticPolygon}.
    * @param configured the {@link EllipticPolygon} being configured.
    * @param graphic the {@link EllipticPolygon} being shown on the {@link Button}.
    * @return the {@link EllipticPolygon} graphic.
    */
   private static EllipticPolygon preparePolygon( EllipticPolygon configured, EllipticPolygon graphic ) {
      Bindings.bindBidirectional( configured.polygonTypeProperty(), graphic.polygonTypeProperty() );
      Bindings.bindBidirectional( configured.rotateProperty(), graphic.rotateProperty() );
      Bindings.bindBidirectional( configured.inversionProperty(), graphic.inversionProperty() );
      Bindings.bindBidirectional( configured.numberOfSidesProperty(), graphic.numberOfSidesProperty() );
      return graphic;
   }//End Method
   
}//End Class
