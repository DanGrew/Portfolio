/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package importexport.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import annotation.Cali;
import export.csv.SingletonCsvContents;
import graphics.tasks.TaskProgressor;
import javafx.concurrent.Task;
import javafx.stage.FileChooser;
import parameter.classparameter.ClassParameterType;
import parameter.classparameter.ClassParameterTypes;
import runnables.ProgressControlledTaskImpl;

/**
 * The {@link CsvBuilderObjectContents} is responsible for importing data using
 * {@link CsvFileContents} and converting the data into {@link BuilderObject}s.
 */
@Cali public class CsvBuilderObjectContents extends SingletonCsvContents {
   
   private Map< Integer, ClassParameterType > columnTypes;
   
   /**
    * Constructs a new {@link CsvBuilderObjectContents}.
    * @param identification the identification of the {@link Singleton}.
    */
   @Cali public CsvBuilderObjectContents( String identification ) {
      super( identification );
      columnTypes = new HashMap< Integer, ClassParameterType >();
   }// End Constructor
   
   /**
    * Method to read a csv {@link File} by asking the user to select the {@link File}.
    * @return true if successfully parsed, false otherwise.
    */
   @Cali public boolean read() {
      if ( isImportValid() ) {
         return false;
      }
      
      FileChooser chooser = new FileChooser();
      chooser.setTitle( "Choose Csv file to import." );
      File file = chooser.showOpenDialog( null );
      if ( file == null ) {
         return false;
      }
      
      try {
         FileReader reader = new FileReader( file );
         return read( reader );
      } catch ( FileNotFoundException e ) {
         return false;
      }
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public boolean isImportValid() {
      if ( getUniqueIdentifierColumn() == null ) {
         return false;
      }
      
      Set< String > uniqueEntries = new HashSet<>();
      for ( int i = 0; i < getNumberOfRows(); i++ ) {
         String item = getItem( i, getUniqueIdentifierColumn() );
         if ( item == null || item.isEmpty() ) {
            return false;
         }
         if ( uniqueEntries.contains( item ) ) {
            return false;
         }
         uniqueEntries.add( item );
      }
      
      return true;
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void importObjects() {
      CsvBuilderObjectImportProcess importTask = new CsvBuilderObjectImportProcess( this );
      Task< ? > task = new ProgressControlledTaskImpl( importTask );
      TaskProgressor progessor = new TaskProgressor( "Importing Csv Records", task );
      progessor.launch();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Cali @Override public String getItem( Integer row, Integer column ) {
      return super.getItem( row, column );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Cali @Override public int getNumberOfRows() {
      return super.getNumberOfRows();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Cali @Override public int getColumnCount( Integer row ) {
      return super.getColumnCount( row );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public String getColumnName( Integer column ) {
      return super.getColumnName( column );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void assignColumnNames( Integer row ) {
      super.assignColumnNames( row );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public void useDefaultColumnNames() {
      super.useDefaultColumnNames();
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Cali @Override public void setUniqueIdentifierColumn( Integer uniqueIdentifierColumn ) {
      super.setUniqueIdentifierColumn( uniqueIdentifierColumn );
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public Integer getUniqueIdentifierColumn() {
      return super.getUniqueIdentifierColumn();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Cali @Override public String getIdentification( Integer row ) {
      return super.getIdentification( row );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Cali @Override public void excludeRow( Integer row ) {
      super.excludeRow( row );
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Cali @Override public void excludeColumn( Integer column ) {
      super.excludeColumn( column );
   }// End Method

   /**
    * Method to set the column type.
    * @param column the column number.
    * @param name the name of the type of data such as "String", "Number".
    */
   @Cali public void setColumnType( Integer column, String name ) {
      ClassParameterType type = ClassParameterTypes.valueOf( name );
      if ( type != null ) {
         columnTypes.put( column, type );
      }
   }// End Method
   
   /**
    * Method to get the {@link ClassParameterType} associated with the given column number.
    * @param column the column number.
    * @return the {@link ClassParameterType} for the column, {@link ClassParameterTypes#STRING_PARAMETER_TYPE}
    * by default.
    */
   public ClassParameterType getColumnType( int column ){
      ClassParameterType type = columnTypes.get( column ); 
      if ( type == null ) {
         return ClassParameterTypes.STRING_PARAMETER_TYPE;
      } else {
         return type;
      }
   }// End Method
   
}// End Class
