/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls.ellipticpolygon;

import diagram.controls.ColourPickerItemImpl;
import diagram.controls.GridItem;
import diagram.controls.GridItemSelection;
import diagram.selection.SelectionController;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * {@link ColourItems} provides the {@link GridItem}s to control the {@link Color} of 
 * an {@link EllipticPolygon}.
 */
public class ColourItems extends GridItemSelection {

   private static final Object FILL_KEY = new Object();
   private static final Object STROKE_KEY = new Object();
   private ColorPicker fillPicker;
   private ColorPicker strokePicker;
   
   /**
    * Constructs a new {@link ColourItems}.
    * @param selection the {@link SelectionController} for applying changes to the selection.
    */
   public ColourItems( SelectionController selection ) {
      populateGrid( 
               2, 1, 
               prepareFill( selection ),
               prepareStroke( selection )
      );
   }//End Constructor
   
   /**
    * Method to prepare the {@link ColourPickerItemImpl} for the {@link EllipticPolygon}'s colour fill.
    * @param selection the {@link SelectionController} for applying changes.
    * @return the {@link ColourPickerItemImpl}.
    */
   private ColourPickerItemImpl prepareFill( SelectionController selection ) {
      selection.register( FILL_KEY, ( polygon, value ) -> { polygon.setFill( ( Paint )value ); } );
      ColourPickerItemImpl item = new ColourPickerItemImpl( "Shape Fill", colour -> selection.apply( FILL_KEY, colour ) );
      fillPicker = ( ColorPicker )item.getController();
      return item;
   }//End Method
   
   /**
    * Method to prepare the {@link ColourPickerItemImpl} for the {@link EllipticPolygon}'s colour stroke.
    * @param selection the {@link SelectionController} for applying changes.
    * @return the {@link ColourPickerItemImpl}.
    */
   private ColourPickerItemImpl prepareStroke( SelectionController selection ) {
      selection.register( STROKE_KEY, ( polygon, value ) -> { polygon.setStroke( ( Paint )value ); } );
      ColourPickerItemImpl item = new ColourPickerItemImpl( "Shape Stroke", colour -> selection.apply( STROKE_KEY, colour ) );
      strokePicker = ( ColorPicker )item.getController();
      return item;
   }//End Method

   /**
    * Getter for the changing the {@link EllipticPolygon#fillProperty()}.
    * @return the {@link ColorPicker}.
    */
   ColorPicker fillColourChooser() {
      return fillPicker;
   }//End Method
   
   /**
    * Getter for the changing the {@link EllipticPolygon#strokeProperty()}.
    * @return the {@link ColorPicker}.
    */
   ColorPicker strokeColourChooser() {
      return strokePicker;
   }//End Method

}//End Class
