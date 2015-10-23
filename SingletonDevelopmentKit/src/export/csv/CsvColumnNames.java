/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package export.csv;

/**
 * {@link CsvColumnNames} is responsible for managing the column names being used 
 * for {@link CsvFileContents}.
 */
public class CsvColumnNames {

   private static final String DEFAULT_COLUMN_PREFIX = "Column";
   
   private String[] row;
   
   /**
    * Setter for the row position being used for columns and the row to use.
    * @param rowInFile the position of the row.
    * @param row the row.
    */
   public void setNames( String[] row ) {
      this.row = row;
   }// End Method
   
   /**
    * Method to clear the column names.
    */
   public void clearColumnNames(){
      row = null;
   }// End Method
   
   /**
    * Method to get the column name for the given column.
    * @param column the position of the column in the table.
    * @return the name of the column, or by default {@link #getDefaultColumnName(int)}.
    */
   public String getColumnName( int column ) {
      if ( row == null ) {
         return getDefaultColumnName( column );
      } else if ( row.length <= column ){
         return getDefaultColumnName( column );
      } else if ( column < 0 ) {
         return getDefaultColumnName( column );
      } else {
         return row[ column ];
      }
   }// End Method
   
   /**
    * Method to construct a default column name.
    * @param columnNumber the position of the column.
    * @return the column name.
    */
   public static String getDefaultColumnName( int columnNumber ) {
      return DEFAULT_COLUMN_PREFIX + columnNumber;
   }// End Method
}// End Class
