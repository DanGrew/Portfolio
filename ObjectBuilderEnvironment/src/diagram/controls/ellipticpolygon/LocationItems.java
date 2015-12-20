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
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.scene.control.Spinner;

/**
 * {@link LocationItems} provides the {@link GridItemSelection} for the translation properties
 * of an {@link EllipticPolygon}.
 */
public class LocationItems extends GridItemSelection {

   private static final Object X_KEY = new Object();
   private static final Object Y_KEY = new Object();
   private NumberSpinnerItemImpl centreXSpinner;
   private NumberSpinnerItemImpl centreYSpinner;
   
   /**
    * Constructs a new {@link LocationItems}.
    * @param selection the {@link SelectionController} for controlling the selection.
    */
   public LocationItems( SelectionController selection ) {
      selection.register( X_KEY, ( polygon, value ) -> polygon.translateXProperty().set( ( double )value ) );
      selection.register( Y_KEY, ( polygon, value ) -> polygon.translateYProperty().set( ( double )value ) );
      
      centreXSpinner = new NumberSpinnerItemImpl( "X", Integer.MIN_VALUE, Integer.MAX_VALUE, value -> selection.apply( X_KEY, value ) );
      centreYSpinner = new NumberSpinnerItemImpl( "Y", Integer.MIN_VALUE, Integer.MAX_VALUE, value -> selection.apply( Y_KEY, value ) );
      populateGrid( 
               2, 1, 
               centreXSpinner,
               centreYSpinner
      );
   }//End Constructor
   
   /**
    * Getter for the {@link Spinner} controlling the centre X.
    * @return the {@link Spinner}.
    */
   Spinner< Double > centreXSpinner(){
      return centreXSpinner.getController();
   }//End Method
   
   /**
    * Getter for the {@link Spinner} controlling the centre Y.
    * @return the {@link Spinner}.
    */
   Spinner< Double > centreYSpinner(){
      return centreYSpinner.getController();
   }//End Method
   
}//End Class
