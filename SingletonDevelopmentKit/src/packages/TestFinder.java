/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package packages;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * The {@link TestFinder} is respnsible for finding all runnable tests in 
 * given packages.
 */
public class TestFinder {
   
   private List< Class< ? > > testClasses;
   
   /**
    * Constructs a new {@link TestFinder}.
    */
   public TestFinder() {
      testClasses = new ArrayList<>();
   }//End Constructor
   
   /**
    * Method to find all tests in the given package and sub packages.
    */
   public void findTests( String packageName ) {
      FileFinder finder = new FileFinder();
      try {
         finder.scan( packageName );
         identifyTestClasses( finder.getClasses() );
      } catch ( ClassNotFoundException | IOException e ) {
         return;
      }
   }//End Method
   
   /**
    * Method to identify all tests in the given, and use them in this {@link TestFinder}.
    * @param classes the {@link List} of {@link Class}es to scan through.
    */
   void identifyTestClasses( List< Class< ? > > classes ) {
      classes.forEach( clazz -> {
         if ( Modifier.isPrivate( clazz.getModifiers() ) ) {
            return;
         }
         if ( Modifier.isAbstract( clazz.getModifiers() ) ) {
            return;
         }
         if ( clazz.getEnclosingClass() != null ) {
            if ( !Modifier.isStatic( clazz.getModifiers() ) ) {
               return;
            }
         }
         for ( Method method : clazz.getMethods() ) {
            if ( method.getAnnotation( Test.class ) != null ) {
               testClasses.add( clazz );
               break;
            }
         }
         return;
      } );
   }//End Method

   /**
    * Getter for the tests identified.
    * @return an unmodifiable {@link List} of {@link Class}es representing tests.
    */
   public List< Class< ? > > getTests() {
      return Collections.unmodifiableList( testClasses );
   }//End Method

}//End Class
