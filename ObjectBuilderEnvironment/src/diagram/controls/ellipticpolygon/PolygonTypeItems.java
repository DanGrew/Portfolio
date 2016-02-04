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
import javafx.scene.control.Button;

/**
 * {@link GridItemSelection} for the items to configure the {@link PolygonType} of an
 * {@link EllipticPolygon}.
 */
public class PolygonTypeItems extends GridItemSelection {

   private static final Object POLYGON_TYPE_REGULAR_KEY = new Object();
   private static final Object POLYGON_TYPE_STARRED_KEY = new Object();
   private static final Object POLYGON_TYPE_FRACTAL_KEY = new Object();
   
   /**
    * Constructs a new {@link PolygonTypeItems}.
    * @param selection the {@link SelectionController} to modify the current selection.
    */
   public PolygonTypeItems( SelectionController selection ) {
      selection.register( POLYGON_TYPE_REGULAR_KEY, polygon -> polygon.polygonTypeProperty().set( PolygonType.Regular ) );
      selection.register( POLYGON_TYPE_STARRED_KEY, polygon -> polygon.polygonTypeProperty().set( PolygonType.Starred ) );
      selection.register( POLYGON_TYPE_FRACTAL_KEY, polygon -> polygon.polygonTypeProperty().set( PolygonType.Fractal ) );
      populateGrid( 
               2, 2,
               new ButtonItemImpl( 
                        "Regular", 
                        selection.constructRepresentativeGraphic( 
                                 POLYGON_TYPE_REGULAR_KEY
                        ),
                        () -> selection.apply( POLYGON_TYPE_REGULAR_KEY )
               ),
               new ButtonItemImpl( 
                        "Starred", 
                        selection.constructRepresentativeGraphic( 
                                 POLYGON_TYPE_STARRED_KEY
                        ),
                        () -> selection.apply( POLYGON_TYPE_STARRED_KEY ) 
               ),
               new ButtonItemImpl( 
                        "Fractal", 
                        selection.constructRepresentativeGraphic( 
                                 POLYGON_TYPE_FRACTAL_KEY
                        ),
                        () -> selection.apply( POLYGON_TYPE_FRACTAL_KEY ) 
               )
            );
   }//End Constructor

   /**
    * Getter for the {@link Button} responsible for making an {@link EllipticPolygon} {@link PolygonType#Regular}.
    * @return the {@link Button}.
    */
   public Button regularButton() {
      return ( Button )getChildren().get( 0 );
   }//End Method

   /**
    * Getter for the {@link Button} responsible for making an {@link EllipticPolygon} {@link PolygonType#Starred}.
    * @return the {@link Button}.
    */
   public Button starredButton() {
      return ( Button )getChildren().get( 1 );
   }//End Method

   /**
    * Getter for the {@link Button} responsible for making an {@link EllipticPolygon} {@link PolygonType#Fractal}.
    * @return the {@link Button}.
    */
   public Button fractalButton() {
      return ( Button )getChildren().get( 2 );
   }//End Method

}//End Class
