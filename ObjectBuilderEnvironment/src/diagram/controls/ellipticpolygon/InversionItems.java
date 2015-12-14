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
import diagram.selection.SelectionController;
import diagram.shapes.PolygonType;
import diagram.shapes.ellipticpolygon.EllipticPolygon;
import javafx.scene.control.Button;

/**
 * The {@link InversionItems} provides a custom extension of {@link GridItemSelection}
 * to specify buttons for controlling whether an {@link EllipticPolygon} is inverted or not.
 */
public class InversionItems extends GridItemSelection {
   
   private static final Object STANDARD_KEY = new Object();
   private static final Object INVERTED_KEY = new Object();

   /**
    * Constructs a new {@link InversionItems}.
    * @param selection the {@link SelectionController} for changing the selection properties.
    */
   public InversionItems( SelectionController selection ) {
      selection.register( STANDARD_KEY, polygon -> { 
         polygon.inversionProperty().set( false );
         polygon.polygonTypeProperty().set( PolygonType.Regular );
      } );
      selection.register( INVERTED_KEY, polygon -> {
         polygon.inversionProperty().set( true );
         polygon.polygonTypeProperty().set( PolygonType.Starred );
      } );
      populateGrid( 
               1, 2,
               new ButtonItemImpl( 
                        "Standard", 
                        selection.constructRepresentativeGraphic( 
                                 STANDARD_KEY
                        ),
                        () -> selection.apply( STANDARD_KEY )
               ),
               new ButtonItemImpl( 
                        "Inverted", 
                        selection.constructRepresentativeGraphic( 
                                 INVERTED_KEY
                        ),
                        () -> selection.apply( INVERTED_KEY )
               )
      );
   }//End Constructor

   /**
    * Method to get the {@link Button} for the given inversion value.
    * @param value the inversion property value.
    * @return the {@link Button}.
    */
   Button inversionButton( boolean value ) {
      if ( value ) {
         return ( Button )getChildren().get( 1 );
      } else  {
         return ( Button )getChildren().get( 0 );
      }
   }//End Method

}//End Class
