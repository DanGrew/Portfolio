/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline;

import javafx.scene.control.TreeItem;
import outline.describer.OutlineDescriber;

/**
 * {@link SystemOutlineUtilities} provides common methods for handling the {@link SystemOutline}.
 */
public class SystemOutlineUtilities {
   
   /**
    * Method to refresh the given {@link TreeItem}. This removes and re-adds the {@link OutlineDescriber}
    * forcing the {@link TreeItem} to register as invalid, forcing a redraw.
    * @param item the {@link TreeItem} to refresh.
    */
   public static void refreshTreeItem( TreeItem< OutlineDescriber > item ) {
      OutlineDescriber value = item.getValue();
      item.setValue( null );
      item.setValue( value );
   }// End Method

}// End Class
