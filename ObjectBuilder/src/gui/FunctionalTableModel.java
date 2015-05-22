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

import javax.swing.table.AbstractTableModel;

import model.singleton.Singleton;

/**
 * The {@link FunctionalTableModel} provides an {@link AbstractTableModel} that is configured with
 * {@link Function}s that determine its behaviour.
 */
public class FunctionalTableModel< SingletonT extends Singleton< ? > > extends AbstractTableModel {

   private static final long serialVersionUID = 1L;
   private static final int UNDEFINED = -1;
   private FunctionalTableBuilder< SingletonT > structure;

   /**
    * Class to define the configurables for a {@link FunctionalTableModel}.
    */
   public static class FunctionalTableBuilder< SingletonT > {
      private List< SingletonT > data;
      private Function< List< SingletonT >, Integer > columnCountFunction;
      private GetValueFunction< SingletonT > getValueFunction;
      private GetValueFunction< SingletonT > columnHeaderFunction;
      
      /**
       * Setter for the {@link List} of {@link Singleton}s defining the data.
       * @param data the data for the model.
       */
      public void data( List< SingletonT > data ) {
         this.data = data;
      }//End Method
      
      /**
       * Setter for the {@link Function} used to calculate the number of columns.
       * @param function the {@link Function}.
       */
      public void columnCountFunction( Function< List< SingletonT >, Integer > function ) {
         this.columnCountFunction = function;
      }// End Method
      
      /**
       * Setter for the {@link Function} used to get the value from the model.
       * @param getValueFunction the {@link Function}.
       */
      public void getValueFunction( GetValueFunction< SingletonT > getValueFunction ) {
         this.getValueFunction = getValueFunction;
      }// End Method
      
      /**
       * Setter for the {@link Function} used to get the name of the column.
       * @param function the {@link Function}.
       */
      public void columnHeaderFunction( GetValueFunction< SingletonT > function ) {
         this.columnHeaderFunction = function;
      }// End Method
   }// End Class
   
   /**
    * Constructs a new {@link FunctionalTableModel}.
    * @param builder the {@link FunctionalTableBuilder} used to configure the model.
    */
   public FunctionalTableModel( FunctionalTableBuilder< SingletonT > builder ) {
      structure = builder;
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public int getRowCount() {
      return structure.data.size();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public int getColumnCount() {
      return structure.columnCountFunction.apply( structure.data );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public Object getValueAt( int rowIndex, int columnIndex ) {
      return structure.getValueFunction.getValue( structure.data, rowIndex, columnIndex );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public String getColumnName( int column ) {
      if ( structure.columnHeaderFunction == null ) {
         return super.getColumnName( column );
      } else {
         return structure.columnHeaderFunction.getValue( structure.data, UNDEFINED, column ).toString();
      }
   }// End Method

}// End Class
