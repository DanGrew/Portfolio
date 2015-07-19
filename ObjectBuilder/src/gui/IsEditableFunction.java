/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package gui;

import java.util.List;
import java.util.function.Function;

/**
 * Functional interface used to determine whether a cell is editablein the {@link FunctionalTableModel}.
 */
public interface IsEditableFunction< T > {
   
   /**
    * Method to get the value associated with the given cell reference.
    * @param data the {@link List} of objects associated with the model.
    * @param row the index of the row.
    * @param column the index of column.
    * @return the object determined for the {@link Function}.
    */
   public boolean isCellEditable( int row, int column ) ;

}// End Interface
