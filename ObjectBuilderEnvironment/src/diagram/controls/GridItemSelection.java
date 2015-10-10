/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package diagram.controls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * The {@link GridItemSelection} defines a {@link GridPane} that holds {@link GridItem}s.
 */
public class GridItemSelection extends GridPane {

   /**
    * Constructs a new {@link GridItemSelection}.
    * @param rows the number of rows to use.
    * @param columns the number of columns to use.
    * @param items the {@link GridItem}s to display, in order left to right.
    */
   public GridItemSelection( int rows, int columns, GridItem... items ) {
      super();
      setPadding( new Insets( 10 ) );
      setHgap( 5 );
      setVgap( 5 );
      setPrefWidth( 300 );
      populateGrid( rows, columns, items );
   }//End Constructor
   
   /**
    * Method to populate the {@link GridPane} with the items.
    * @param rows the number of rows to use.
    * @param columns the number of columns to use.
    * @param items the {@link GridItem}s to display, in order left to right.
    */
   private void populateGrid( int rows, int columns, GridItem... items ) {
      List< GridItem > itemsToAdd = new ArrayList<>( Arrays.asList( items ) );
      for ( int i = 0; i < rows; i++ ) {
         for ( int j = 0; j < columns; j++ ) {
            if ( itemsToAdd.isEmpty() ) {
               return;
            }
            
            GridItem item = itemsToAdd.remove( 0 );
            Node controller = item.getController();
            add( controller, j, i );
         }
      }
   }//End Method
}//End Class
