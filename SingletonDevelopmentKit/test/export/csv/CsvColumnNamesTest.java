/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package export.csv;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link CsvColumnNames} test.
 */
public class CsvColumnNamesTest {

   /**
    * Constructor and default value test.
    */
   @Test public void shouldProvideDefault() {
      CsvColumnNames sut = new CsvColumnNames();
      assertDefaultColumnNames( sut, 0, 1, 2, 3, 4, 5, 6, 7, 100 );
   }//End Method
   
   /**
    * {@link CsvColumnNames#setNames(String[])} test.
    */
   @Test public void shouldUseSetColumnNames() {
      CsvColumnNames sut = new CsvColumnNames();
      String[] names = { "first", "column", "another", "column" };
      assertDefaultColumnNames( sut, -1, 0, 1, 2, 3, 4, 5, 6, 7, 100 );
      
      sut.setNames( names );
      Assert.assertEquals( "first", sut.getColumnName( 0 ) );
      Assert.assertEquals( "column", sut.getColumnName( 1 ) );
      Assert.assertEquals( "another", sut.getColumnName( 2 ) );
      Assert.assertEquals( "column", sut.getColumnName( 3 ) );
      assertDefaultColumnNames( sut, -1, 4, 5, 6, 7, 100 );
   }//End Method
   
   /**
    * {@link CsvColumnNames#clearColumnNames()} test.
    */
   @Test public void shouldClearColumnNames() {
      CsvColumnNames sut = new CsvColumnNames();
      String[] names = { "first", "column", "another", "column" };
      assertDefaultColumnNames( sut, -1, 0, 1, 2, 3, 4, 5, 6, 7, 100 );
      
      sut.setNames( names );
      Assert.assertEquals( "first", sut.getColumnName( 0 ) );
      sut.clearColumnNames();
      assertDefaultColumnNames( sut, -1, 0, 1, 2, 3, 4, 5, 6, 7, 100 );
   }//End Method
   
   /**
    * Method to assert the given column numbers have the default name.
    * @param sut the {@link CsvColumnNames} under test.
    * @param columns the column numbers to test.
    */
   private void assertDefaultColumnNames( CsvColumnNames sut, Integer... columns ) {
      for ( Integer integer : columns ) {
         Assert.assertEquals( CsvColumnNames.getDefaultColumnName( integer ), sut.getColumnName( integer ) );   
      }
   }//End Method

}//End Class
