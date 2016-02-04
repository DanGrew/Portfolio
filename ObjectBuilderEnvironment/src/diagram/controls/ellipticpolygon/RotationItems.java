/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.GridItemSelection;
import diagram.controls.SliderItemImpl;
import diagram.selection.SelectionController;
import javafx.scene.control.Slider;

/**
 * {@link RotationItems} provides the {@link GridItemSelection} for the properties
 * controlling the rotation of an {@link EllipticPolygon}.
 */
public class RotationItems extends GridItemSelection {

   private static final Object ROTATE_KEY = new Object();
   private SliderItemImpl sliderItem;
   
   /**
    * Constructs a new {@link RotationItems}.
    * @param selection the {@link SelectionController} for the selection.
    */
   public RotationItems( SelectionController selection ) {
      selection.register( ROTATE_KEY, ( polygon, value ) -> polygon.rotateProperty().set( ( double )value ) );
      sliderItem = new SliderItemImpl( "Degrees", value -> selection.apply( ROTATE_KEY, value ) )
               .setRange( -180, 180 )
               .setRangeLabels( 90, 90, 90 );
      populateGrid(   
               1, 1, sliderItem
      );
   }//End Constructor
   
   /**
    * Getter for the {@link Slider} controlling the selection.
    * @return the {@link Slider}.
    */
   Slider rotateSlider(){
      return sliderItem.getController();
   }//End Method

}//End Class
