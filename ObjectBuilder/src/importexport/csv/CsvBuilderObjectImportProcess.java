/*
 * ----------------------------------------
 *            Object Builder
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package importexport.csv;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import export.csv.CsvFileContents;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import object.BuilderObject;
import object.BuilderObjectImpl;
import objecttype.Definition;
import objecttype.DefinitionImpl;
import parameter.classparameter.ClassParameterTypes;
import propertytype.PropertyType;
import propertytype.PropertyTypeImpl;
import runnables.ProgressControlledTask;
import runnables.ProgressControlledTaskBindable;
import runnables.TaskUpdateProcess.TaskProgress;
import runnables.TaskUpdateProcess.TaskState;
import architecture.request.RequestSystem;

/**
 * The {@link CsvBuilderObjectImportProcess} provides the processing for the import of {@link BuilderObject}s
 * from {@link CsvFileContents}.
 */
public class CsvBuilderObjectImportProcess implements ProgressControlledTaskBindable {
   
   private CsvBuilderObjectContents contents;
   private Map< Integer, PropertyType > columnTypes;
   private Definition definition;
   private TaskState state;
   private int expectedProgressLength = 0; 
   
   /**
    * Private class used to control the progress of the task.
    */
   private class CsvBuilderObjectTaskProgress extends TaskState {
      
      public CsvBuilderObjectTaskProgress() {
         TaskProgress creatingObjects = new TaskProgress( contents.getNumberOfRows(), null, ( progress, maximum ) -> {
            return String.format( "Processing %s...", contents.getIdentification( progress ) );
         } );
         TaskProgress creatingDefinition = new TaskProgress( 1, creatingObjects, ( progress, maximum ) -> {
            return String.format( "Creating Definition %s...", contents.getObjectTableName() );
         } );
         TaskProgress clearingSessions = new TaskProgress( 1, creatingDefinition, ( progress, maximum ) -> {
            return String.format( "Clearing session..." );
         } );
         setInitialState( clearingSessions );
      }
   }// End Class
   
   /**
    * Constructs a new {@link CsvBuilderObjectImportProcess}.
    * @param contents the {@link CsvBuilderObjectContents}.
    */
   public CsvBuilderObjectImportProcess( CsvBuilderObjectContents contents ) {
      this.contents = contents;
      this.columnTypes = new HashMap<>();
      state = new CsvBuilderObjectTaskProgress();
   }// End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public void run() {
      expectedProgressLength = 1 + 1 + contents.getNumberOfRows();
      state.setTaskLength( expectedProgressLength );
      state.reset();
      
      clearSession();
      if ( !contents.isImportValid() ) {
         return;
      }
      
      createDefinition();
      
      createBuilderObjects();
   }// End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public void bind( ProgressControlledTask task ) {
      state.bind( task );
   }// End Method
   
   /**
    * Method to create the {@link Definition} for the data.
    */
   private void createDefinition() {
      String definitionName = contents.getColumnName( contents.getUniqueIdentifierColumn() );
      definition = new DefinitionImpl( definitionName );
      RequestSystem.store( definition, Definition.class );
      state.progressMade();
   }// End Method
   
   /**
    * Method to create the {@link BuilderObject}s from the rows in the data.
    */
   private void createBuilderObjects() {
      for ( int i = 0; i < contents.getNumberOfRows(); i++ ) {
         String identification = contents.getIdentification( i );
         BuilderObject builderObject = new BuilderObjectImpl( identification, definition );
         
         int uniqueColumn = contents.getUniqueIdentifierColumn();
         for ( int j = 0; j < contents.getColumnCount( i ); j++ ) {
            if ( j == uniqueColumn ) {
               continue;
            }
            
            String value = contents.getItem( i, j );
            if ( value == null ) {
               continue;
            }
            
            PropertyType propertyType = getPropertyFor( j );
            builderObject.set( propertyType, value );
         }
         
         RequestSystem.store( builderObject, BuilderObject.class );
         state.progressMade();
         try {
            Thread.sleep( 10 );
         } catch ( InterruptedException e ) {
            e.printStackTrace();
         }
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
         String columnName = contents.getColumnName( column );
         propertyType = new PropertyTypeImpl( columnName, ClassParameterTypes.STRING_PARAMETER_TYPE );
         columnTypes.put( column, propertyType );
         RequestSystem.store( propertyType, PropertyType.class );
         definition.addPropertyType( propertyType );
      }
      return propertyType;
   }// End Method
   
   /**
    * Method to clear the previous session data.
    */
   private void clearSession(){
      definition = null;
      columnTypes.clear();
      state.progressMade();
   }// End Method
   
   public static void main( String[] args ) throws InterruptedException {
      new JFXPanel();
      Reader reader = new StringReader(
               "Key,TestObject,Value\n"
             + "SomeKey1,FirstObject,SomeValue\n"
             + "SomeKey2,FirstObject,SomeValue\n"
             + "SomeKey3,FirstObject,SomeValue\n"
             + "SomeKey4,FirstObject,SomeValue\n"
             + "SomeKey5,FirstObject,SomeValue\n"
             + "SomeKey6,FirstObject,SomeValue\n"
             + "SomeKey7,FirstObject,SomeValue\n"
             + "SomeKey8,FirstObject,SomeValue\n"
             + "SomeKey9,FirstObject,SomeValue\n"
             + "SomeKey0,FirstObject,SomeValue\n"
             + "SomeKeya,FirstObject,SomeValue\n"
             + "SomeKeys,FirstObject,SomeValue\n"
             + "SomeKeyd,FirstObject,SomeValue\n"
             + "SomeKeyf,FirstObject,SomeValue\n"
             + "SomeKeyg,FirstObject,SomeValue\n"
             + "SomeKeyh,FirstObject,SomeValue\n"
             + "SomeKeyj,FirstObject,SomeValue\n"
             + "SomeKeyk,FirstObject,SomeValue\n"
             + "SomeKeyl,FirstObject,SomeValue\n"
             + "SomeKeyp,FirstObject,SomeValue\n"
             + "SomeKeyi,FirstObject,SomeValue\n"
             + "SomeKeyu,FirstObject,SomeValue\n"
             + "SomeKeyy,FirstObject,SomeValue\n"
             + "SomeKeyt,FirstObject,SomeValue\n"
             + "SomeKeyr,FirstObject,SomeValue\n"
             + "SomeKeye,FirstObject,SomeValue\n"
             + "SomeKeyw,FirstObject,SomeValue\n"
             + "SomeKeyq,FirstObject,SomeValue\n"
             + "SomeKeyz,FirstObject,SomeValue\n"
             + "SomeKeyx,FirstObject,SomeValue\n"
             + "SomeKeyc,FirstObject,SomeValue\n"
             + "SomeKeyv,FirstObject,SomeValue\n"
             + "SomeKeyb,FirstObject,SomeValue\n"
             + "SomeKeyn,FirstObject,SomeValue\n"
             + "SomeKeym,FirstObject,SomeValue\n"
             + "SomeKey11,FirstObject,SomeValue\n"
             + "SomeKey22,FirstObject,SomeValue\n"
             + "SomeKey33,FirstObject,SomeValue\n"
             + "anything,AnotherObject,else"
      );
      
      CsvBuilderObjectContents contents = new CsvBuilderObjectContents( "anything" );
      contents.read( reader );
      contents.setUniqueIdentifierColumn( 0 );
      contents.assignColumnNames( 0 );
      

      Platform.runLater( () -> {
         contents.importObjects();
      } );
      Thread.sleep( 20000 );
   }
}// End Class
