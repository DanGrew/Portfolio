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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * {@link FileFinder} is responsible for finding {@link File}s given criteria from the package
 * structure of the software. Supported by
 * http://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection 24/10/15.
 */
public class FileFinder {
   
   private static final String PACKAGE_SEPARATOR = ".";
   private List< Class< ? > > classes;
   private List< File > files;
   
   /**
    * Constructs a new {@link FileFinder}.
    */
   public FileFinder() {
      classes = new ArrayList<>();
      files = new ArrayList<>();
   }//End Constructor

   /**
    * Method to scan the given package name for all {@link Class}es and {@link File}s within it and sub directories.
    * @param packageName the package name, excluding the source folder.
    * @throws ClassNotFoundException, {@link IOException}.
    */
   public void scan( String packageName ) throws ClassNotFoundException, IOException {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      String path = packageName.replace( PACKAGE_SEPARATOR, "/" );
      Enumeration< URL > resources = classLoader.getResources( path );
      List< File > dirs = new ArrayList< File >();
      while ( resources.hasMoreElements() ) {
         URL resource = resources.nextElement();
         dirs.add( new File( resource.getFile() ) );
      }
      for ( File directory : dirs ) {
         scanDirectoryClasses( directory, packageName );
      }
   }//End Method

   /**
    * Recursive method used to find all classes in a given directory and subdirs.
    * @param directory the base directory.
    * @param packageName the package name for classes found inside the base directory.
    * @throws ClassNotFoundException
    */
   private void scanDirectoryClasses( File directory, String packageName ) throws ClassNotFoundException {
      if ( !directory.exists() ) {
         return;
      }
      File[] files = directory.listFiles();
      for ( File file : files ) {
         if ( file.isDirectory() ) {
            assert !file.getName().contains( PACKAGE_SEPARATOR );
            scanDirectoryClasses( file, packageName + PACKAGE_SEPARATOR + file.getName() );
         } else if ( file.getName().endsWith( ".class" ) ) {
            String className = file.getName().substring( 0, file.getName().length() - 6 );
            Class< ? > clazz = Class.forName( packageName + PACKAGE_SEPARATOR + className );
            classes.add( clazz );
         } else {
            this.files.add( file );
         }
      }
   }//End Method
   
   /**
    * Getter for the {@link Class}es found as an unmodifiable {@link List}.
    * @return a {@link List} of {@link Class}es.
    */
   public List< Class< ? > > getClasses(){
      return Collections.unmodifiableList( classes );
   }//End Method
   
   /**
    * Getter for the {@link File}s found as an unmodifiable {@link List}.
    * @return a {@link List} of {@link File}s.
    */
   public List< File > getFiles(){
      return Collections.unmodifiableList( files );
   }//End Method

}//End Class
