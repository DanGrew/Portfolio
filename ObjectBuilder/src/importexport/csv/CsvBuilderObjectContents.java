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

import model.singleton.Singleton;
import javafx.stage.FileChooser;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import annotation.Cali;
import architecture.request.RequestSystem;
import export.csv.CsvFileContents;
import export.csv.SingletonCsvContents;

/**
 * The {@link CsvBuilderObjectContents} is responsible for importing data using
 * {@link CsvFileContents} and converting the data into {@link BuilderObject}s.
 */
@Cali public class CsvBuilderObjectContents extends SingletonCsvContents {
   
   private Map< Integer, PropertyType > columnTypes;
   private Definition definition;
   
   /**
    * Constructs a new {@link CsvBuilderObjectContents}.
    * @param identification the identification of the {@link Singleton}.
    */
   @Cali public CsvBuilderObjectContents( String identification ) {
      super( identification );
      columnTypes = new HashMap< Integer, PropertyType >();
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
   @Cali @Override public void clearImport() {
      columnTypes.clear();
      definition = null;
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
         System.out.println( item );
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
      clearImport();
      if ( !isImportValid() ) {
         return;
      }
      
      createDefinition();
      createBuilderObjects();
   }// End Method

   /**
    * Method to create the {@link Definition} for the data.
    */
   private void createDefinition() {
      String definitionName = getColumnName( getUniqueIdentifierColumn() );
      definition = new DefinitionImpl( definitionName );
      RequestSystem.store( definition, Definition.class );
   }// End Method
   
   /**
    * Method to create the {@link BuilderObject}s from the rows in the data.
    */
   private void createBuilderObjects() {
      for ( int i = 0; i < getNumberOfRows(); i++ ) {
         String identification = getIdentification( i );
         BuilderObject builderObject = new BuilderObjectImpl( identification, definition );
         
         int uniqueColumn = getUniqueIdentifierColumn();
         for ( int j = 0; j < getColumnCount( i ); j++ ) {
            if ( j == uniqueColumn ) {
               continue;
            }
            
            String value = getItem( i, j );
            if ( value == null ) {
               continue;
            }
            
            PropertyType propertyType = getPropertyFor( j );
            builderObject.set( propertyType, value );
         }
         
         RequestSystem.store( builderObject, BuilderObject.class );
      }
   }// End Method
   
   /**
    * Method to get the {@link PropertyType} for the given column in the data.
    * @param column the column number.
    * @return the {@link PropertyType} for the column. One is create if it doesn't already
    * exist.
    */
   private PropertyType getPropertyFor( int column ) {
      PropertyType propertyType = columnTypes.get( column );
      if ( propertyType == null ) {
         String columnName = getColumnName( column );
         propertyType = new PropertyTypeImpl( columnName, ClassParameterTypes.STRING_PARAMETER_TYPE );
         columnTypes.put( column, propertyType );
         RequestSystem.store( propertyType, PropertyType.class );
         definition.addPropertyType( propertyType );
      }
      return propertyType;
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
   
}// End Class
