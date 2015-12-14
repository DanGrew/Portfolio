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
 * {@link NumberOfFractalsItems} provides a custom extension of {@link GridItemSelection} to 
 * define the options for configuring the number of fractals on an {@link EllipticPolygon}.
 */
public class NumberOfFractalsItems extends GridItemSelection {

   private static final Object ZERO_FRACTALS_KEY = new Object();
   private static final Object ONE_FRACTAL_KEY = new Object();
   private static final Object TWO_FRACTALS_KEY = new Object();
   private static final Object THREE_FRACTALS_KEY = new Object();
   
   /**
    * Constructs a new {@link NumberOfFractalsItems}.
    * @param selection the {@link SelectionController} for changing the selection.
    */
   public NumberOfFractalsItems( SelectionController selection ) {
      selection.register( ZERO_FRACTALS_KEY, polygon -> polygon.numberOfFractalsProperty().set( 0 ) );
      selection.register( ONE_FRACTAL_KEY, polygon -> polygon.numberOfFractalsProperty().set( 1 ) );
      selection.register( TWO_FRACTALS_KEY, polygon -> polygon.numberOfFractalsProperty().set( 2 ) );
      selection.register( THREE_FRACTALS_KEY, polygon -> polygon.numberOfFractalsProperty().set( 3 ) );
      
      EllipticPolygon zero = selection.constructRepresentativeGraphic( ZERO_FRACTALS_KEY );
      zero.polygonTypeProperty().set( PolygonType.Fractal );
      EllipticPolygon one = selection.constructRepresentativeGraphic( ONE_FRACTAL_KEY );
      one.polygonTypeProperty().set( PolygonType.Fractal );
      EllipticPolygon two = selection.constructRepresentativeGraphic( TWO_FRACTALS_KEY );
      two.polygonTypeProperty().set( PolygonType.Fractal );
      EllipticPolygon three = selection.constructRepresentativeGraphic( THREE_FRACTALS_KEY );
      three.polygonTypeProperty().set( PolygonType.Fractal );
      
      populateGrid( 
               2, 2,
               new ButtonItemImpl( 
                        "0 Fractals", 
                        zero,
                        () -> selection.apply( ZERO_FRACTALS_KEY )
               ),
               new ButtonItemImpl( 
                        "1 Fractal", 
                        one,
                        () -> selection.apply( ONE_FRACTAL_KEY )
               ),
               new ButtonItemImpl( 
                        "2 Fractals", 
                        two,
                        () -> selection.apply( TWO_FRACTALS_KEY )
               ),
               new ButtonItemImpl( 
                        "3 Fractals", 
                        three,
                        () -> selection.apply( THREE_FRACTALS_KEY )
               )
      );
   }//End Constructor

   /**
    * Method to get the {@link Button} for configuring the given number of fractals.
    * @param i the number of fractals 0 <= i <= 3.
    * @return the {@link Button}.
    */
   public Button fractalsButton( int i ) {
      if ( i < 0 || i > 3 ) {
         return null;
      }
      return ( Button )getChildren().get( i );
   }//End Method
   
}//End Class
