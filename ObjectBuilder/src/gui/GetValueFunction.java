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
 * Functional interface used to get a value from a {@link FunctionalTableModel} using a cell
 * reference.
 */
public interface GetValueFunction< T > {
   
   /**
    * Method to get the value associated with the given cell reference.
    * @param data the {@link List} of objects associated with the model.
    * @param row the index of the row.
    * @param column the index of column.
    * @return the object determined for the {@link Function}.
    */
   public Object getValue( List< T > data, int row, int column ) ;

}// End Interface
