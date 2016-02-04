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
import javafx.scene.control.Button;

/**
 * {@link NumberOfSidesItems} provides a custom extension of {@link GridItemSelection} to 
 * define the options for configuring the number of sides on an {@link EllipticPolygon}.
 */
public class NumberOfSidesItems extends GridItemSelection {

   private static final Object THREE_SIDES_KEY = new Object();
   private static final Object FOUR_SIDES_KEY = new Object();
   private static final Object FIVE_SIDES_KEY = new Object();
   private static final Object SIX_SIDES_KEY = new Object();
   private static final Object SEVEN_SIDES_KEY = new Object();
   private static final Object EIGHT_SIDES_KEY = new Object();
   private static final Object NINE_SIDES_KEY = new Object();
   private static final Object TEN_SIDES_KEY = new Object();

   public NumberOfSidesItems( SelectionController selection ) {
      selection.register( THREE_SIDES_KEY, polygon -> polygon.numberOfSidesProperty().set( 3 ) );
      selection.register( FOUR_SIDES_KEY, polygon -> polygon.numberOfSidesProperty().set( 4 ) );
      selection.register( FIVE_SIDES_KEY, polygon -> polygon.numberOfSidesProperty().set( 5 ) );
      selection.register( SIX_SIDES_KEY, polygon -> polygon.numberOfSidesProperty().set( 6 ) );
      selection.register( SEVEN_SIDES_KEY, polygon -> polygon.numberOfSidesProperty().set( 7 ) );
      selection.register( EIGHT_SIDES_KEY, polygon -> polygon.numberOfSidesProperty().set( 8 ) );
      selection.register( NINE_SIDES_KEY, polygon -> polygon.numberOfSidesProperty().set( 9 ) );
      selection.register( TEN_SIDES_KEY, polygon -> polygon.numberOfSidesProperty().set( 10 ) );
      populateGrid( 
               4, 2,
               new ButtonItemImpl( 
                        "3 Sides", 
                        selection.constructRepresentativeGraphic( 
                                 THREE_SIDES_KEY
                        ),
                        () -> selection.apply( THREE_SIDES_KEY )
               ),
               new ButtonItemImpl( 
                        "4 Sides", 
                        selection.constructRepresentativeGraphic( 
                                 FOUR_SIDES_KEY
                        ),
                        () -> selection.apply( FOUR_SIDES_KEY )
               ),
               new ButtonItemImpl( 
                        "5 Sides", 
                        selection.constructRepresentativeGraphic( 
                                 FIVE_SIDES_KEY
                        ),
                        () -> selection.apply( FIVE_SIDES_KEY )
               ),new ButtonItemImpl( 
                        "6 Sides", 
                        selection.constructRepresentativeGraphic( 
                                 SIX_SIDES_KEY
                        ),
                        () -> selection.apply( SIX_SIDES_KEY )
               ),new ButtonItemImpl( 
                        "7 Sides", 
                        selection.constructRepresentativeGraphic( 
                                 SEVEN_SIDES_KEY
                        ),
                        () -> selection.apply( SEVEN_SIDES_KEY )
               ),new ButtonItemImpl( 
                        "8 Sides", 
                        selection.constructRepresentativeGraphic( 
                                 EIGHT_SIDES_KEY
                        ),
                        () -> selection.apply( EIGHT_SIDES_KEY )
               ),new ButtonItemImpl( 
                        "9 Sides", 
                        selection.constructRepresentativeGraphic( 
                                 NINE_SIDES_KEY
                        ),
                        () -> selection.apply( NINE_SIDES_KEY )
               ),new ButtonItemImpl( 
                        "10 Sides", 
                        selection.constructRepresentativeGraphic( 
                                 TEN_SIDES_KEY
                        ),
                        () -> selection.apply( TEN_SIDES_KEY )
               )
      );
   }//End Constructor

   /**
    * Method to get the {@link Button} for changing the number of sides to the number of sides given.
    * @param numberOfSides the number of sides the {@link Button} changes the {@link EllipticPolygon} to.
    * @return the {@link Button}.
    */
   public Button sidesButton( int numberOfSides ) {
      if ( numberOfSides > 2 && numberOfSides < 11 ) {
         return ( Button ) getChildren().get( numberOfSides - 3 );
      }
      return null;
   }//End Method
   
}//End Class
