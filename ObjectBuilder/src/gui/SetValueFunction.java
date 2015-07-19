/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.util.List;

/**
 * Functional interface used to set a value int the {@link FunctionalTableModel} using a cell
 * reference.
 */
public interface SetValueFunction< T > {
   
   /**
    * Method to set the value associated with the given cell reference.
    * @param data the {@link List} of objects associated with the model.
    * @param value the new value to set.
    * @param row the index of the row.
    * @param column the index of column.
    */
   public void setValue( List< T > data, Object value, int row, int column ) ;

}// End Interface
