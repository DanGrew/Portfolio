/*
 * ----------------------------------------
 *                 CALI
 * ----------------------------------------
 *          Produced by Dan Grew
 * ----------------------------------------
 */
package annotation;

import org.junit.Assert;
import org.junit.Test;

import common.TestObjects.TestAnnotatedSingleton;
import common.TestObjects.TestAnnotatedSingletonImpl;
import test.model.TestObjects.TestSingleton;
import test.model.TestObjects.TestSingletonImpl;

/**
 * Test for the {@link CaliAnnotations}.
 */
public class CaliAnnotationsTest {

   /**
    * Method to test that classes are identified with the annotation.
    */
   @Test public void shouldHaveClassAnnotion() {
      Assert.assertTrue( CaliAnnotations.isAnnotationPresent( TestAnnotatedSingleton.class ) );
      
      TestAnnotatedSingleton testSingleton = new TestAnnotatedSingletonImpl( "Anything" );
      Assert.assertTrue( CaliAnnotations.isAnnotationPresent( testSingleton ) );
   }// End Method
   
   /**
    * Method to test that classes without the annotation are correctly ignored.
    */
   @Test public void shouldNotHaveClassAnnotion() {
      Assert.assertFalse( CaliAnnotations.isAnnotationPresent( TestSingleton.class ) );
      
      TestSingleton testSingleton = new TestSingletonImpl( "Anything" );
      Assert.assertFalse( CaliAnnotations.isAnnotationPresent( testSingleton ) );
   }// End Method
   
   /**
    * Method to test that {@link Method}s with the annotation are matched.
    */
   @Test public void shouldHaveMethodAnnotion() throws NoSuchMethodException, SecurityException {
      Assert.assertTrue( CaliAnnotations.isAnnotationPresent( TestAnnotatedSingletonImpl.class.getMethod( "testCaliMethod", String.class ) ) );
      Assert.assertTrue( CaliAnnotations.isAnnotationPresent( TestAnnotatedSingletonImpl.class.getMethod( "overloaded", String.class ) ) );
      Assert.assertTrue( CaliAnnotations.isAnnotationPresent( TestAnnotatedSingletonImpl.class.getMethod( "overloaded", String.class, String.class ) ) );
   }// End Method
   
   /**
    * Method to test that {@link Method}s without the annotation are ignored.
    */
   @Test public void shouldNotHaveMethodAnnotion() throws NoSuchMethodException, SecurityException {
      Assert.assertFalse( CaliAnnotations.isAnnotationPresent( TestAnnotatedSingletonImpl.class.getMethod( "nonCaliMethod", String.class ) ) );
   }// End Method
   
}// End Class
