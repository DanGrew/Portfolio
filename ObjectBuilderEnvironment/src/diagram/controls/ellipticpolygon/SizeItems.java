/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.GridItemSelection;
import diagram.controls.NumberSpinnerItemImpl;
import diagram.selection.SelectionController;
import javafx.scene.control.Spinner;

/**
 * {@link SizeItems} provides the {@link GridItemSelection} for the size properties
 * of an {@link EllipticPolygon}.
 */
public class SizeItems extends GridItemSelection {

   private static final Object HORIZONTAL_KEY = new Object();
   private static final Object VERTICAL_KEY = new Object();
   private static final Object STROKE_WIDTH_KEY = new Object();
   private NumberSpinnerItemImpl horizontalRadiusSpinner;
   private NumberSpinnerItemImpl verticalRadiusSpinner;
   private NumberSpinnerItemImpl strokeWidthSpinner;
   
   /**
    * Constructs a new {@link SizeItems}.
    * @param selection the {@link SelectionController} for controlling the selected.
    */
   public SizeItems( SelectionController selection ) {
      selection.register( HORIZONTAL_KEY, ( polygon, value ) -> polygon.horizontalRadiusProperty().set( ( double )value ) );
      selection.register( VERTICAL_KEY, ( polygon, value ) -> polygon.verticalRadiusProperty().set( ( double )value ) );
      selection.register( STROKE_WIDTH_KEY, ( polygon, value ) -> polygon.strokeWidthProperty().set( ( double )value ) );
      
      horizontalRadiusSpinner = new NumberSpinnerItemImpl( "Horizontal Radius", 1, Integer.MAX_VALUE, value -> selection.apply( HORIZONTAL_KEY, value ) );
      verticalRadiusSpinner = new NumberSpinnerItemImpl( "Vertical Radius", 1, Integer.MAX_VALUE, value -> selection.apply( VERTICAL_KEY, value ) );
      strokeWidthSpinner = new NumberSpinnerItemImpl( "Stroke Thickness", 1, Integer.MAX_VALUE, value -> selection.apply( STROKE_WIDTH_KEY, value ) );
      
      populateGrid( 
               3, 1, 
               horizontalRadiusSpinner,
               verticalRadiusSpinner,
               strokeWidthSpinner
      );
   }//End Constructor
   
   /**
    * Getter for the {@link Spinner} for the horizontal property.
    * @return the {@link Spinner}.
    */
   Spinner< Double > horizontalRadiusSpinner() {
      return horizontalRadiusSpinner.getController();
   }//End Method
   
   /**
    * Getter for the {@link Spinner} for the vertical property.
    * @return the {@link Spinner}.
    */
   Spinner< Double > verticalRadiusSpinner() {
      return verticalRadiusSpinner.getController();
   }//End Method
   
   /**
    * Getter for the {@link Spinner} for the stroke width property.
    * @return the {@link Spinner}.
    */
   Spinner< Double > strokeWidthSpinner() {
      return strokeWidthSpinner.getController();
   }//End Method

}//End Class
