/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package importexport.csv;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import architecture.request.RequestSystem;
import export.csv.CsvFileContents;
import export.csv.SingletonCsvContents;

/**
 * The {@link CsvBuilderObjectContents} is responsible for importing data using
 * {@link CsvFileContents} and converting the data into {@link BuilderObject}s.
 */
public class CsvBuilderObjectContents extends SingletonCsvContents {
   
   private Map< Integer, PropertyType > columnTypes;
   private Definition definition;
   
   /**
    * Constructs a new {@link CsvBuilderObjectContents}.
    */
   public CsvBuilderObjectContents() {
      super();
      columnTypes = new HashMap< Integer, PropertyType >();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void clearImport() {
      columnTypes.clear();
      definition = null;
   }// End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean isImportValid() {
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
   @Override public void importObjects() {
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
   
}// End Class
