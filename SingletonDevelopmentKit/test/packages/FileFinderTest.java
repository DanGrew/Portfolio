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
      Assert.assertEquals( "a_data_file.dat", files.get( 0 ).getName() );
      Assert.assertEquals( "a_text_file.txt", files.get( 1 ).getName() );
      Assert.assertEquals( "an_xml_file.xml", files.get( 2 ).getName() );
      Assert.assertEquals( "a_text_file_in_a_sub_dir.txt", files.get( 3 ).getName() );
   }//End Method

}//End Class
