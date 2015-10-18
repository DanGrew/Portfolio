/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package export.csv;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.opencsv.CSVReader;

/**
 * Test to prove that the opencsv library provides the required functionality.
 */
public class OpenCsvLibraryTest {

   /**
    * {@link CSVReader} test.
    */
   @Test public void shouldParseEmpty() {
      List< String[] > lines = readTestData( "" );
      
      Assert.assertNotNull( lines );
      Assert.assertTrue( lines.isEmpty() );
      Assert.assertEquals( 0, lines.size() );
   }// End Method
   
   /**
    * {@link CSVReader} test.
    */
   @Test public void shouldParseSingleWord() {
      List< String[] > lines = readTestData( "some" );
      
      Assert.assertNotNull( lines );
      Assert.assertFalse( lines.isEmpty() );
      Assert.assertEquals( 1, lines.size() );
      
      String[] line = lines.get( 0 );
      Assert.assertEquals( 1, line.length );
      Assert.assertEquals( "some", line[ 0 ] );
   }// End Method
   
   /**
    * {@link CSVReader} test.
    */
   @Test public void shouldParseSingleLine() throws IOException {
      List< String[] > lines = readTestData( "some,values,separated" );
      
      Assert.assertNotNull( lines );
      Assert.assertFalse( lines.isEmpty() );
      Assert.assertEquals( 1, lines.size() );
      
      String[] line = lines.get( 0 );
      Assert.assertEquals( 3, line.length );
      Assert.assertEquals( "some", line[ 0 ] );
      Assert.assertEquals( "values", line[ 1 ] );
      Assert.assertEquals( "separated", line[ 2 ] );
   }// End Method
   
   /**
    * {@link CSVReader} test.
    */
   @Test public void shouldParseMultipleLines() {
      List< String[] > lines = readTestData( 
               "some,values,separated\n"
              + "on,new,line\n"
              + "with,multiple,lines" 
      );
      
      Assert.assertNotNull( lines );
      Assert.assertFalse( lines.isEmpty() );
      Assert.assertEquals( 3, lines.size() );
      
      String[] line = lines.get( 0 );
      Assert.assertEquals( 3, line.length );
      Assert.assertEquals( "some", line[ 0 ] );
      Assert.assertEquals( "values", line[ 1 ] );
      Assert.assertEquals( "separated", line[ 2 ] );
      
      line = lines.get( 1 );
      Assert.assertEquals( 3, line.length );
      Assert.assertEquals( "on", line[ 0 ] );
      Assert.assertEquals( "new", line[ 1 ] );
      Assert.assertEquals( "line", line[ 2 ] );
      
      line = lines.get( 2 );
      Assert.assertEquals( 3, line.length );
      Assert.assertEquals( "with", line[ 0 ] );
      Assert.assertEquals( "multiple", line[ 1 ] );
      Assert.assertEquals( "lines", line[ 2 ] );
   }// End Method
   
   /**
    * {@link CSVReader} test.
    */
   @Test public void shouldParseWithQuotes() {
      List< String[] > lines = readTestData( 
               "\"some\",\"values\",\"separated\"\n"
              + "on,new,line\n"
      );
      
      Assert.assertNotNull( lines );
      Assert.assertFalse( lines.isEmpty() );
      Assert.assertEquals( 2, lines.size() );
      
      String[] line = lines.get( 0 );
      Assert.assertEquals( 3, line.length );
      Assert.assertEquals( "some", line[ 0 ] );
      Assert.assertEquals( "values", line[ 1 ] );
      Assert.assertEquals( "separated", line[ 2 ] );
      
      line = lines.get( 1 );
      Assert.assertEquals( 3, line.length );
      Assert.assertEquals( "on", line[ 0 ] );
      Assert.assertEquals( "new", line[ 1 ] );
      Assert.assertEquals( "line", line[ 2 ] );
   }// End Method
   
   /**
    * {@link CSVReader} test.
    */
   @Test public void shouldParseWithDifferentLengthLines() {
      List< String[] > lines = readTestData( 
               "some,values,separated\n"
             + "some,more,on,another,line,with,more\n"
             + "\n"
             + "single"
      );
      
      Assert.assertNotNull( lines );
      Assert.assertFalse( lines.isEmpty() );
      Assert.assertEquals( 4, lines.size() );
      
      String[] line = lines.get( 0 );
      Assert.assertEquals( 3, line.length );
      Assert.assertEquals( "some", line[ 0 ] );
      Assert.assertEquals( "values", line[ 1 ] );
      Assert.assertEquals( "separated", line[ 2 ] );
      
      line = lines.get( 1 );
      Assert.assertEquals( 7, line.length );
      Assert.assertEquals( "some", line[ 0 ] );
      Assert.assertEquals( "more", line[ 1 ] );
      Assert.assertEquals( "on", line[ 2 ] );
      Assert.assertEquals( "another", line[ 3 ] );
      Assert.assertEquals( "line", line[ 4 ] );
      Assert.assertEquals( "with", line[ 5 ] );
      Assert.assertEquals( "more", line[ 6 ] );

      line = lines.get( 2 );
      Assert.assertEquals( 1, line.length );
      Assert.assertEquals( "", line[ 0 ] );
      
      line = lines.get( 3 );
      Assert.assertEquals( 1, line.length );
      Assert.assertEquals( "single", line[ 0 ] );
   }// End Method
   
   /**
    * {@link CSVReader} test.
    */
   private List< String[] > readTestData( String testInput )  {
      StringReader stringInput = new StringReader( 
               testInput
      );
      CSVReader reader = new CSVReader( stringInput );
      List< String[] > lines = null;
      try {
         lines = reader.readAll();
         reader.close();
      } catch ( IOException e ) {
         e.printStackTrace();
      }
      return lines;
   }// End Method
   
}// End Class
