/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package javafx;

import javafx.scene.control.TreeTableColumn;

/**
 * {@link Workarounds} provides workaround functions for oddities in JavaFx.
 */
public class Workarounds {
   
   /**
    * Method to disable reordering of columns in the given {@link TreeTableColumn}.
    * @param column the {@link TreeTableColumn} to disable reordering on.
    */
   @SuppressWarnings("deprecation") public static void disableColumnDragging( TreeTableColumn< ?, ? > column ) {
      column.impl_setReorderable( false );
   }// End Method

}// End Class
