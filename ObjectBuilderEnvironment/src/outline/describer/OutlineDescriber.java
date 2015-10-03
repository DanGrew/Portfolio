/*
 * ----------------------------------------
 *       Object Builder Environment
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package outline.describer;

import javafx.scene.control.TreeTableView;
import model.singleton.Singleton;

/**
 * The {@link OutlineDescriber} provides an interface for an item that describes a {@link Singleton}
 * in a {@link TreeTableView}.
 */
public interface OutlineDescriber {
   
   /**
    * Method to get the description of the item in the column.
    * @param columnReference the column in the table.
    * @return the {@link String} description, can be null.
    */
   public String getColumnDescription( int columnReference );
   
   /**
    * Method to get the entry for the cell in the column.
    * @param columnReference the column in the table.
    * @return the {@link String} value, can be null.
    */
   public String getColumnEntry( int columnReference );
   
   /**
    * Method to get the subject associated with the {@link OutlineDescriber}.
    * @return the object.
    */
   public Object getSubject();

}// End Interface
