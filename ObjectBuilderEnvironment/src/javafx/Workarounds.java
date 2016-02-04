/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package javafx;

import javafx.scene.control.TableColumnBase;

/**
 * {@link Workarounds} provides workaround functions for oddities in JavaFx.
 */
public class Workarounds {
   
   /**
    * Method to disable reordering of columns in the given {@link TreeTableColumn}.
    * @param column the {@link TableColumnBase} to disable reordering on.
    */
   @SuppressWarnings("deprecation") public static void disableColumnDragging( TableColumnBase< ?, ? > column ) {
      column.impl_setReorderable( false );
   }// End Method

}// End Class
