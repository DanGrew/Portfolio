/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package export.csv;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * {@link CsvFileContents} provides the contents of a csv file in the form of
 * rows of {@link String} arrays.
 */
public class CsvFileContents {
   
   private CsvColumnNames columnNames;
   private List< String[] > rows;
   private Integer uniqueIdentifierColumn;

   /**
    * Constructs a new {@link CsvFileContents}.
    */
   public CsvFileContents() {
      rows = new ArrayList<>();
      columnNames = new CsvColumnNames();
   }// End Constructor
   
   /**
    * Method to read from the given {@link Reader},
    * @param reader the {@link Reader} providing the input.
    * @return true if successfully read.
    */
   public boolean read( Reader reader ) {
      CSVReader csvReader = new CSVReader( reader );
      List< String[] > parsedData = null;
      try {
         parsedData = csvReader.readAll();
      } catch ( IOException e ) {
         return false;
      } finally {
         try {
            csvReader.close();
         } catch ( IOException e ) {
            e.printStackTrace();
         }
      }
      if ( parsedData != null ) {
         rows.addAll( parsedData );
         return true;
      } else {
         return false;
      }
   }// End Method
   
   /**
    * Getter for the item at the given row and column position.
    * @param row the row.
    * @param column the column.
    * @return the {@link String} at the position, or null if none.
    */
   public String getItem( int row, int column ) {
      if ( row < rows.size() && row >= 0 ) {
         String[] values = rows.get( row );
         if ( column < values.length && column > -1 ) {
            return values[ column ];
         }
      }
      return null;
   }// End Method

   /**
    * Getter for the number of rows in the data.
    * @return the row count.
    */
   public int getNumberOfRows() {
      return rows.size();
   }// End Method

   /**
    * Getter for the number of columns in the data for the given row.
    * @param row the row index.
    * @return the number of columns in the row.
    */
   public int getColumnCount( int row ) {
      if ( row < 0 ) {
         return 0;
      } else if ( row < rows.size() ) {
         String[] rowValues = rows.get( row );
         if ( rowValues == null ) {
            return 0;
         } else {
            return rowValues.length;
         }
      } else {
         return 0;
      }
   }// End Method
   
   /**
    * Getter for the name of the column for the given column index.
    * @param column the column index.
    * @return {@link CsvColumnNames#getColumnName(int)}.
    */
   public String getColumnName( int column ) {
      return columnNames.getColumnName( column );
   }// End Method
   
   /**
    * Method to assign the given row as column names. Note that this will remove the 
    * given row from the data.
    * @param row the index of the row to use as column names.
    */
   public void assignColumnNames( int row ) {
      if ( row < rows.size() && row >= 0 ) {
         String[] rowValues = rows.get( row );
         rows.remove( row );
         columnNames.setNames( rowValues );
      }
   }// End Method
   
   /**
    * Method to clear the selection of column names, restoring default names.
    */
   public void useDefaultColumnNames() {
      columnNames.clearColumnNames();
   }// End Method

   /**
    * Setter for the index of the column providing the unique identifier for the data.
    * @param uniqueIdentifierColumn the column index, or null to clear.
    */
   public void setUniqueIdentifierColumn( Integer uniqueIdentifierColumn ) {
      this.uniqueIdentifierColumn = uniqueIdentifierColumn;
   }// End Method
   
   /**
    * Getter for the index of the column providing the unqie identifier.
    * @return the column index, can be null.
    */
   public Integer getUniqueIdentifierColumn() {
      return uniqueIdentifierColumn;
   }// End Method

   /**
    * Method to exclude the given row index from the data. Note that this is destructive to the parsed data.
    * @param row the row to exclude.
    */
   public void excludeRow( int row ) {
      if ( row < rows.size() && row >= 0 ) {
         rows.remove( row );
      }
   }// End Method

   /**
    * Method to exclude the column of the given index. Note that this is destructive to the parsed data.
    * @param column the column index to exclude.
    */
   public void excludeColumn( int column ) {
      for ( String[] row : rows ) {
         if ( column < row.length ) {
            for ( int i = column; i < row.length - 1; i++ ) {
               row[ i ] = row[ i + 1 ];
            }
            row[ row.length - 1 ] = null;
         }
      }
   }// End Method

}// End Class
