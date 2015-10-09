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
import javafx.scene.control.Button;

/**
 * The {@link InversionItems} provides a custom extension of {@link GridItemSelection}
 * to specify buttons for controlling whether an {@link EllipticPolygon} is inverted or not.
 */
public class InversionItems extends GridItemSelection {

   /**
    * Constructs a new {@link InversionItems}.
    * @param polygon the {@link EllipticPolygon} associated, being controlled.
    */
   public InversionItems( EllipticPolygon polygon ) {
      super( 
               1, 2,
               new ButtonItemImpl( 
                        "Standard", 
                        preparePolygon( polygon, EllipticPolygonGraphics.getPolygon( polygon ) ), 
                        () -> polygon.inversionProperty().set( false )
               ),
               new ButtonItemImpl( 
                        "Inverted", 
                        preparePolygon( polygon, prepareInvertedPolygon( polygon ) ), 
                        () -> polygon.inversionProperty().set( true )
               )
      );
   }//End Constructor
   
   /**
    * Convenience method to prepare an {@link EllipticPolygon} that is inverted in the 
    * same style as the given.
    * @param polygon the {@link EllipticPolygon} to mimic.
    * @return an {@link EllipticPolygon} that is inverted.
    */
   private static EllipticPolygon prepareInvertedPolygon( EllipticPolygon polygon ) {
      EllipticPolygon inverted = EllipticPolygonGraphics.getPolygon( polygon );
      inverted.inversionProperty().set( true );
      return inverted;
   }//End Method
   
   /**
    * Method to bind the configurable {@link EllipticPolygon} to the graphical representation of the {@link EllipticPolygon}.
    * @param configured the {@link EllipticPolygon} being configured.
    * @param graphic the {@link EllipticPolygon} being shown on the {@link Button}.
    * @return the {@link EllipticPolygon} graphic.
    */
   private static EllipticPolygon preparePolygon( EllipticPolygon configured, EllipticPolygon graphic ) {
      Bindings.bindBidirectional( configured.polygonTypeProperty(), graphic.polygonTypeProperty() );
      Bindings.bindBidirectional( configured.numberOfSidesProperty(), graphic.numberOfSidesProperty() );
      Bindings.bindBidirectional( configured.rotateProperty(), graphic.rotateProperty() );
      Bindings.bindBidirectional( configured.numberOfFractalsProperty(), graphic.numberOfFractalsProperty() );
      return graphic;
   }//End Method

}//End Class
