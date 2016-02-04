/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.ButtonPadItemImpl;
import diagram.controls.GridItemSelection;
import diagram.controls.NumberSpinnerItemImpl;
import diagram.controls.ellipticpolygon.composite.ButtonPad;
import diagram.selection.SelectionController;
import images.arrows.ImageArrows;
import javafx.scene.control.Spinner;

/**
 * {@link LocationItems} provides the {@link GridItemSelection} for the translation properties
 * of an {@link EllipticPolygon}.
 */
public class LocationItems extends GridItemSelection {

   static final double STEP = 50;
   private static final Object X_KEY = new Object();
   private static final Object Y_KEY = new Object();
   private static final Object X_MOVE_KEY = new Object();
   private static final Object Y_MOVE_KEY = new Object();
   private NumberSpinnerItemImpl centreXSpinner;
   private NumberSpinnerItemImpl centreYSpinner;
   private ButtonPadItemImpl moverPad;
   
   /**
    * Constructs a new {@link LocationItems}.
    * @param selection the {@link SelectionController} for controlling the selection.
    */
   public LocationItems( SelectionController selection ) {
      selection.register( X_KEY, ( polygon, value ) -> polygon.translateXProperty().set( ( double )value ) );
      selection.register( Y_KEY, ( polygon, value ) -> polygon.translateYProperty().set( ( double )value ) );
      selection.register( X_MOVE_KEY, ( polygon, value ) -> polygon.translateXProperty().set( polygon.getTranslateX() + ( double )value ) );
      selection.register( Y_MOVE_KEY, ( polygon, value ) -> polygon.translateYProperty().set( polygon.getTranslateY() + ( double )value ) );
      
      centreXSpinner = new NumberSpinnerItemImpl( "Align X", Integer.MIN_VALUE, Integer.MAX_VALUE, value -> selection.apply( X_KEY, value ) );
      centreYSpinner = new NumberSpinnerItemImpl( "Align Y", Integer.MIN_VALUE, Integer.MAX_VALUE, value -> selection.apply( Y_KEY, value ) );
      
      ButtonPad pad = new ButtonPad();
      initialisePadFunctions( pad, selection );
      
      moverPad = new ButtonPadItemImpl( "Move", pad );
      populateGrid( 
               3, 1, 
               centreXSpinner,
               centreYSpinner,
               moverPad
      );
   }//End Constructor
   
   /**
    * Method to initialise the {@link ButtonPad} for movement and its functions.
    * @param pad the {@link ButtonPad} to initialise.
    * @param selection the {@link SelectionController} for the function definition.
    */
   private static void initialisePadFunctions( ButtonPad pad, SelectionController selection ){
      pad.setButtonFunction( 0, () -> {
         selection.apply( X_MOVE_KEY, -STEP );
         selection.apply( Y_MOVE_KEY, -STEP );
      } );
      pad.setButtonImage( 0, ImageArrows.getArrowRightImageView( 225 ) );
      
      pad.setButtonFunction( 1, () -> {
         selection.apply( Y_MOVE_KEY, -STEP );
      } );
      pad.setButtonImage( 1, ImageArrows.getArrowRightImageView( 270 ) );
      
      pad.setButtonFunction( 2, () -> {
         selection.apply( X_MOVE_KEY, STEP );
         selection.apply( Y_MOVE_KEY, -STEP );
      } );
      pad.setButtonImage( 2, ImageArrows.getArrowRightImageView( 315 ) );
      
      pad.setButtonFunction( 3, () -> {
         selection.apply( X_MOVE_KEY, -STEP );
      } );
      pad.setButtonImage( 3, ImageArrows.getArrowRightImageView( 180 ) );
      
      pad.setButtonFunction( 5, () -> {
         selection.apply( X_MOVE_KEY, STEP );
      } );
      pad.setButtonImage( 5, ImageArrows.getArrowRightImageView( 0 ) );
      
      pad.setButtonFunction( 6, () -> {
         selection.apply( X_MOVE_KEY, -STEP );
         selection.apply( Y_MOVE_KEY, STEP );
      } );
      pad.setButtonImage( 6, ImageArrows.getArrowRightImageView( 135 ) );
      
      pad.setButtonFunction( 7, () -> {
         selection.apply( Y_MOVE_KEY, STEP );
      } );
      pad.setButtonImage( 7, ImageArrows.getArrowRightImageView( 90 ) );
      
      pad.setButtonFunction( 8, () -> {
         selection.apply( X_MOVE_KEY, STEP );
         selection.apply( Y_MOVE_KEY, STEP );
      } );
      pad.setButtonImage( 8, ImageArrows.getArrowRightImageView( 45 ) );
   }
   
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

   /**
    * Getter for the {@link ButtonPad} controlling the movement.
    * @return the {@link ButtonPad}.
    */
   ButtonPad movePad() {
      return moverPad.getController();
   }//End Method
   
}//End Class
