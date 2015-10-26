/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package packages;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link FileFinder} test.
 */
public class FileFinderTest {

   private static FileFinder finder;
   
   /**
    * Initialise the sut using the current package.
    */
   @BeforeClass public static void initialiseFinder() throws ClassNotFoundException, IOException{
      finder = new FileFinder();
      finder.scan( "packages" );
   }//End Method
   
   /**
    * {@link FileFinder} should find this compiled class.
    */
   @Test public void shouldFindThisClass() throws IOException, ClassNotFoundException {
      List< Class< ? > > classes = finder.getClasses();
      Assert.assertNotEquals( 0, classes.size() );
      Assert.assertTrue( classes.contains( FileFinderTest.class ) );
   }//End Method
   
   /**
    * {@link FileFinder} should identify multiple {@link File}s, in this and sub directories.
    */
   @Test public void shouldFindFiles() {
      List< File > files = finder.getFiles();
      Assert.assertEquals( 4, files.size() );
      assertHasFile( "a_data_file.dat", files );
      assertHasFile( "a_text_file.txt", files );
      assertHasFile( "an_xml_file.xml", files );
      assertHasFile( "a_text_file_in_a_sub_dir.txt", files );
   }//End Method
   
   /**
    * Method to assert that a {@link File} with the given name exists in the given {@link List} of {@link File}s.
    * @param fileName the name of the {@link File}.
    * @param files the {@link List} of {@link File}s to check against.
    */
   private void assertHasFile( String fileName, List< File > files ) {
      for ( File file : files ) {
         if ( file.getName().equals( fileName ) ) {
            return;
         }
      }
      Assert.fail( "File: " + fileName + " not found in " + files.toString() + "." );
   }//End Method

}//End Class
