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
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * {@link ColourItems} provides the {@link GridItem}s to control the {@link Color} of 
 * an {@link EllipticPolygon}.
 */
public class ColourItems extends GridItemSelection {

   /**
    * Constructs a new {@link ColourItems}.
    * @param polygon {@link EllipticPolygon} to colour.
    */
   public ColourItems( EllipticPolygon polygon ) {
      super( 
               2, 1, 
               prepareFill( polygon ),
               prepareStroke( polygon )
      );
   }//End Constructor
   
   /**
    * Method to protected the {@link Paint} when it should be interpreted as a {@link Color}.
    * @param paint the {@link Paint} to convert to {@link Color} if possible.
    * @return the {@link Color} found, or {@link Color#BLACK} by default.
    */
   private static Color protectColour( Paint paint ){
      if ( paint instanceof Color ) {
         return ( Color )paint;
      } else {
         return Color.BLACK;
      }
   }//End Method
   
   /**
    * Method to prepare the {@link ColourPickerItemImpl} for the {@link EllipticPolygon}'s colour fill.
    * @param polygon the {@link EllipticPolygon} the picker is for.
    * @return the {@link ColourPickerItemImpl}.
    */
   private static ColourPickerItemImpl prepareFill( EllipticPolygon polygon ) {
      ColourPickerItemImpl item = new ColourPickerItemImpl( "Shape Fill", colour -> polygon.setFill( colour ) );
      item.selectColour( protectColour( polygon.getFill() ) );
      polygon.fillProperty().addListener( ( change, old, updated ) -> {
         item.selectColour( protectColour( polygon.getFill() ) );
      } );
      return item;
   }//End Method
   
   /**
    * Method to prepare the {@link ColourPickerItemImpl} for the {@link EllipticPolygon}'s colour stroke.
    * @param polygon the {@link EllipticPolygon} the picker is for.
    * @return the {@link ColourPickerItemImpl}.
    */
   private static ColourPickerItemImpl prepareStroke( EllipticPolygon polygon ) {
      ColourPickerItemImpl item = new ColourPickerItemImpl( "Outline Stroke", colour -> polygon.setStroke( colour ) );
      item.selectColour( protectColour( polygon.getStroke() ) );
      polygon.strokeProperty().addListener( ( change, old, updated ) -> {
         item.selectColour( protectColour( polygon.getStroke() ) );
      } );
      return item;
   }//End Method

}//End Class
