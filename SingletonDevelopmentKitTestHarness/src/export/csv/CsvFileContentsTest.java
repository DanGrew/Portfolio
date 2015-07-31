/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package export.csv;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the {@link CsvFileContents}.
 */
public class CsvFileContentsTest {

   /**
    * {@link CsvFileContents#read(Reader)} test.
    */
   @Test public void shouldParseDataIntoRowsOnly() {
      Reader reader = new StringReader( 
               "something,on,the,first,line\n"
             + "with,even,more,on,the,next,line\n"
             + "but,not,the,last" 
      );
      CsvFileContents contents = new CsvFileContents();
      contents.read( reader );
      
      Assert.assertEquals( 3, contents.getNumberOfRows() );
      Assert.assertEquals( 5, contents.getColumnCount( 0 ) );
      Assert.assertEquals( 7, contents.getColumnCount( 1 ) );
      Assert.assertEquals( 4, contents.getColumnCount( 2 ) );
      Assert.assertEquals( 0, contents.getColumnCount( 3 ) );
      
      //Random inspection.
      Assert.assertEquals( "the", contents.getItem( 0, 2 ) );
      Assert.assertNull( contents.getItem( 0, 7 ) );
      Assert.assertEquals( "with", contents.getItem( 1, 0 ) );
      Assert.assertEquals( "next", contents.getItem( 1, 5 ) );
   }// End Method
   
   /**
    * {@link CsvFileContents#getColumnName(int)} test.
    */
   @Test public void shouldAssumeNewColumnNames() {
      CsvFileContents contents = new CsvFileContents();
      for ( int i = 0; i < 5; i++ ) {
         Assert.assertEquals( CsvColumnNames.getDefaultColumnName( i ), contents.getColumnName( i ) );
      }
   }// End Method
   
   /**
    * {@link CsvFileContents#assignColumnNames(int)} test.
    */
   @Test public void shouldReassignColumnNames() {
      Reader reader = new StringReader( 
               "these,should,be,column,names" 
      );
      CsvFileContents contents = new CsvFileContents();
      contents.read( reader );
      contents.assignColumnNames( 0 );
      
      Assert.assertEquals( "these", contents.getColumnName( 0 ) );
      Assert.assertEquals( "should", contents.getColumnName( 1 ) );
      Assert.assertEquals( "be", contents.getColumnName( 2 ) );
      Assert.assertEquals( "column", contents.getColumnName( 3 ) );
      Assert.assertEquals( "names", contents.getColumnName( 4 ) );
      Assert.assertEquals( CsvColumnNames.getDefaultColumnName( 5 ), contents.getColumnName( 5 ) );
   }// End Method
   
   /**
    * {@link CsvFileContents#assignColumnNames(int)} test.
    */
   @Test public void shouldNotReassignColumnNamesForInvalidRow() {
      Reader reader = new StringReader( 
               "these,should,be,column,names" 
      );
      CsvFileContents contents = new CsvFileContents();
      contents.read( reader );
      contents.assignColumnNames( 56 );
      
      for ( int i = 0; i < 5; i++ ) {
         Assert.assertEquals( CsvColumnNames.getDefaultColumnName( i ), contents.getColumnName( i ) );
      }
   }// End Method
   
   /**
    * {@link CsvFileContents#assignColumnNames(int)} test.
    */
   @Test public void shouldReassignColumnNamesForOnceAssigned() {
      Reader reader = new StringReader( 
               "these,should,be,column,names\n"
             + "but,these,should,be,replaced,afterwards" 
      );
      CsvFileContents contents = new CsvFileContents();
      contents.read( reader );
      contents.assignColumnNames( 0 );
      
      Assert.assertEquals( "these", contents.getColumnName( 0 ) );
      Assert.assertEquals( "should", contents.getColumnName( 1 ) );
      Assert.assertEquals( "be", contents.getColumnName( 2 ) );
      Assert.assertEquals( "column", contents.getColumnName( 3 ) );
      Assert.assertEquals( "names", contents.getColumnName( 4 ) );
      Assert.assertEquals( CsvColumnNames.getDefaultColumnName( 5 ), contents.getColumnName( 5 ) );
      
      contents.assignColumnNames( 0 );
      Assert.assertEquals( "but", contents.getColumnName( 0 ) );
      Assert.assertEquals( "these", contents.getColumnName( 1 ) );
      Assert.assertEquals( "should", contents.getColumnName( 2 ) );
      Assert.assertEquals( "be", contents.getColumnName( 3 ) );
      Assert.assertEquals( "replaced", contents.getColumnName( 4 ) );
      Assert.assertEquals( "afterwards", contents.getColumnName( 5 ) );
      Assert.assertEquals( CsvColumnNames.getDefaultColumnName( 6 ), contents.getColumnName( 6 ) );
      
      contents.useDefaultColumnNames();
      for ( int i = 0; i < 5; i++ ) {
         Assert.assertEquals( CsvColumnNames.getDefaultColumnName( i ), contents.getColumnName( i ) );
      }
   }// End Method
   
   /**
    * {@link CsvFileContents#assignColumnNames(int)} test.
    */
   @Test public void shouldExcludeRowUsedForColumnNames(){
      Reader reader = new StringReader( 
               "these,should,be,column,names" 
      );
      CsvFileContents contents = new CsvFileContents();
      contents.read( reader );
      contents.assignColumnNames( 0 );
      
      Assert.assertEquals( 0, contents.getNumberOfRows() );
      Assert.assertEquals( 0, contents.getColumnCount( 0 ) );
   }// End Method
   
   /**
    * {@link CsvFileContents#getUniqueIdentifierColumn()} test.
    */
   @Test public void shouldNotAssumeUniqueIdentifier() {
      CsvFileContents contents = new CsvFileContents();
      Assert.assertNull( contents.getUniqueIdentifierColumn() );
   }// End Method
   
   /**
    * {@link CsvFileContents#setUniqueIdentifierColumn(Integer)} test.
    */
   @Test public void shouldAssignUniqueIdentifier() {
      CsvFileContents contents = new CsvFileContents();
      contents.setUniqueIdentifierColumn( 3 );
      Assert.assertEquals( Integer.valueOf( 3 ), contents.getUniqueIdentifierColumn() );
   }// End Method
   
   /**
    * {@link CsvFileContents#excludeColumn(int)} test.
    */
   @Test public void shouldExcludeColumns() {
      Reader reader = new StringReader( 
               "these,should,be,column,names" 
      );
      CsvFileContents contents = new CsvFileContents();
      contents.read( reader );
      contents.excludeColumn( 1 );
      
      Assert.assertEquals( "these", contents.getItem( 0, 0 ) );
      Assert.assertEquals( "be", contents.getItem( 0, 1 ) );
      Assert.assertEquals( "column", contents.getItem( 0, 2 ) );
      Assert.assertEquals( "names", contents.getItem( 0, 3 ) );
      Assert.assertNull( contents.getItem( 0, 4 ) );
   }// End Method
   
   /**
    * {@link CsvFileContents#excludeRow(int)} test.
    */
   @Test public void shouldExcludeRows() {
      Reader reader = new StringReader( 
               "something,on,the,first,line\n"
             + "with,even,more,on,the,next,line\n"
             + "but,not,the,last" 
      );
      CsvFileContents contents = new CsvFileContents();
      contents.read( reader );
      contents.excludeRow( 1 );
      
      Assert.assertEquals( 2, contents.getNumberOfRows() );
      Assert.assertEquals( "but", contents.getItem( 1, 0 ) );
   }// End Method
}// End Class
