/*
 * ----------------------------------------
 *        Singleton Development Kit
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package test;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;
import packages.TestFinder;

/**
 * Specific {@link TestSuite} for scanning for all tests in the project.
 */
@RunWith(AllTests.class) 
public class ProjectSuite {

   /**
    * Method used by JUnit to gather the tests to run.
    * @return the {@link TestSuite} to run.
    */
   public static TestSuite suite(){
      TestSuite suite = new TestSuite();
      
      TestFinder finder = new TestFinder();
      finder.findTests( "" );
      finder.getTests().forEach( clazz -> suite.addTest( new JUnit4TestAdapter( clazz ) ) );
      
      return suite;
   }//End Method
}//End Class
